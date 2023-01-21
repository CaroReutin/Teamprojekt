package solving;

import gui.level.GUILevelPage;
import gui.level.GUIManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;
import rucksack.Level;

/**
 * Manages Custom Levels do not make an instance all methods are static.
 */
public final class CustomLevelManager {
  /**
   * do not make.
   */
  private CustomLevelManager() {
  }

  /**
   * saves the level (as zip) to the default path with identifier as name.
   *
   * @param identifier the unique identifier that will be the
   *                   name of the zippedLevel
   * @param level      the level to save
   */
  public static void save(final String identifier, final Level level) {
    String customLevelFolder;
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      customLevelFolder = appdataPath + "/Optimal Heist/customLevel";
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      customLevelFolder = homePath + "/Optimal Heist/customLevel";
    }
    save(customLevelFolder, identifier, level);
  }

  /**
   * saves the level (as zip) to the path with identifier as name.
   *
   * @param path       the path where the zip should be saved
   * @param identifier the unique identifier that will be the
   *                   name of the zippedLevel
   * @param level      the level to save
   */
  public static void save(final String path, final String identifier,
                          final Level level) {
    // Make path if it does not exist already
    boolean ignoreResult = new File(path + "/temp").mkdirs();
    // Use jaxb to turn Level into xml file
    String levelPath = path + "/" + identifier + ".xml";
    try {
      FileOutputStream fos = new FileOutputStream(levelPath);
      JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
      Marshaller marsh = jaxbContext.createMarshaller();

      marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      marsh.marshal(level, fos);

      fos.close();

      // The pictures are not passed via code but
      // instead by being in path+"/temp"
      // see ItemPanel.java for more Info
      zipLevel(levelPath);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void zipLevel(final String levelPath) throws IOException {
    ArrayList<String> srcFiles = new ArrayList<>();
    srcFiles.add(levelPath);
    // Check if there is a Picture associated with item
    // See ItemPanel.java for more info
    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      if (new File(AppData
          .getCustomLevelPictureFolder() + "/picture" + i + ".png").exists()) {
        srcFiles.add(AppData
            .getCustomLevelPictureFolder() + "/picture" + i + ".png");
      }
    }
    // The code below zips everything in srcFiles
    // Source: https://www.baeldung.com/java-compress-and-uncompress
    String zipPath = Paths.get(levelPath).toAbsolutePath().toString();
    final FileOutputStream fos = new FileOutputStream(
        zipPath.substring(0, zipPath.length() - 4) + ".zip");
    ZipOutputStream zipOut = new ZipOutputStream(fos);
    for (String srcFile : srcFiles) {
      File fileToZip = new File(srcFile);
      FileInputStream fis = new FileInputStream(fileToZip);
      ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
      zipOut.putNextEntry(zipEntry);

      // I do not know why this is needed, but it is
      byte[] bytes = new byte[AppData.ZIP_BYTE_SIZE];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
      fis.close();
    }

    zipOut.close();
    fos.close();
  }

  /**
   * unzips the level and opens it.
   *
   * @param zippedLevel the zip that contains the level
   */
  public static void load(final File zippedLevel) {
    // Source: https://www.baeldung.com/java-compress-and-uncompress
    File destDir = new File(AppData.getCustomLevelUnzipFolder());
    File zip;
    try {
      if (destDir.exists()) {
        FileUtils.cleanDirectory(destDir);
      } else {
        Boolean ignoreResult = destDir.mkdirs();
      }
      zip = new File(destDir + "/" + zippedLevel.getName());
      FileUtils.copyFile(zippedLevel, zip);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String levelname = null;

    try {

      byte[] buffer = new byte[AppData.ZIP_BYTE_SIZE];
      ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
      ZipEntry zipEntry = zis.getNextEntry();
      while (zipEntry != null) {
        if (zipEntry.getName().endsWith(".xml")) {
          levelname = zipEntry.getName();
        }
        File newFile = new File(destDir, zipEntry.getName());
        // https://security.snyk.io/research/zip-slip-vulnerability
        System.out.println(newFile.getCanonicalPath());
        if (!newFile.getCanonicalPath().startsWith(destDir.getCanonicalPath()
            + File.separator)) {
          throw new IOException("Entry is outside of the target dir: "
              + zipEntry.getName());
        }
        if (zipEntry.isDirectory()) {
          if (!newFile.isDirectory() && !newFile.mkdirs()) {
            throw new IOException("Failed to create directory " + newFile);
          }
        } else {
          File parent = newFile.getParentFile();
          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to create directory " + parent);
          }

          FileOutputStream fos = new FileOutputStream(newFile);
          int len;
          while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
          }
          fos.close();
        }
        zipEntry = zis.getNextEntry();
      }
      zis.closeEntry();
      zis.close();
      if (levelname == null) {
        throw new IOException("Level not found");
      } else {
        // TODO bilder in Level einfügen
        File levelFile = new File(destDir + "/" + levelname);
        JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
        Unmarshaller marsh = jaxbContext.createUnmarshaller();

        Level level = (Level) marsh.unmarshal(levelFile);
        level.resetLevel();
        GUIManager.openLevel(new GUILevelPage(level));
      }

    } catch (IOException ioException) {
      ioException.printStackTrace();
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }

  }

  /*
  public static void load(String path) {
    // TODO String vs. File depends on file picker I guess

    // Items with no picture associated to it
    // should get the default picture
  }



  private static void unzip(File zip){

  }

  public static void cleanCustomLevelFolder() {
    // TODO? Delete all non Zip files/folder in folder ?
  }
   */
}
