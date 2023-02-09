package solving;

import gui.level.GuiLevelPage;
import gui.level.GuiLevelPageBacktracking;
import gui.level.GuiLevelPageGreedy;
import gui.level.GuiManager;
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
   * @param path       the folder where it should be saved
   * @param identifier the unique identifier that will be the
   *                   name of the zippedLevel
   * @param level      the level to save
   * @param validItems the list of valid Items
   */
  public static void save(final String path,
                          final String identifier, final Level level,
                          final ArrayList<Integer> validItems) {
    String customLevelFolder;
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      customLevelFolder = appdataPath + "/Optimal Heist/customLevel/temp";
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      customLevelFolder = homePath + "/Optimal Heist/customLevel/temp";
    }
    save(customLevelFolder, path, identifier, level, validItems);
  }

  /**
   * saves the level (as zip) to the path with identifier as name.
   *
   * @param pictureFolder the folder where the pictures are
   * @param path          the path where the zip should be saved
   * @param identifier    the unique identifier that will be the
   *                      name of the zippedLevel
   * @param level         the level to save
   * @param validItems    the list of valid Items
   */
  public static void save(final String pictureFolder,
                          final String path, final String identifier,
                          final Level level,
                          final ArrayList<Integer> validItems) {
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

      zipLevel(pictureFolder, levelPath, validItems);
      boolean ignoreResult2 = new File(levelPath).delete();
      boolean ignoreResult3 = new File(path + "/temp").delete();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void zipLevel(final String customLevelPictureFolder,
                               final String levelPath,
                               final ArrayList<Integer> validItems)
      throws IOException {
    int pictureIndexOffset = 0;
    ArrayList<String> srcFiles = new ArrayList<>();
    srcFiles.add(levelPath);
    // Check if there is a Picture associated with item
    // See ItemPanel.java for more info
    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      if (!validItems.contains(i)) {
        if (new File(customLevelPictureFolder + "/picture" + i + ".png")
            .exists()) {
          FileUtils.delete(new File(customLevelPictureFolder
              + "/picture" + i + ".png"));
        }
        pictureIndexOffset++;
      } else {
        if (new File(customLevelPictureFolder + "/picture" + i + ".png")
            .exists()) {
          int newI = i - pictureIndexOffset;
          if (pictureIndexOffset != 0) {
            FileUtils.copyFile(new File(customLevelPictureFolder
                    + "/picture" + i + ".png"),
                new File(customLevelPictureFolder
                    + "/picture" + newI + ".png"));
            FileUtils.delete(new File(customLevelPictureFolder
                + "/picture" + i + ".png"));
          }
          srcFiles.add(customLevelPictureFolder + "/picture" + newI + ".png");
        }
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
      e.printStackTrace();
      return;
    }
    String levelName = null;

    try {
      byte[] buffer = new byte[AppData.ZIP_BYTE_SIZE];
      ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
      ZipEntry zipEntry = zis.getNextEntry();
      while (zipEntry != null) {
        if (zipEntry.getName().endsWith(".xml")) {
          levelName = zipEntry.getName();
        }
        File newFile = new File(destDir, zipEntry.getName());
        // https://security.snyk.io/research/zip-slip-vulnerability
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
      if (levelName == null) {
        throw new IOException("Level not found");
      } else {
        // TODO Sprint 4: Bilder in Level einfügen
        File levelFile = new File(destDir + "/" + levelName);
        JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
        Unmarshaller marsh = jaxbContext.createUnmarshaller();

        Level level = (Level) marsh.unmarshal(levelFile);
        level.turnIntoBacktracking();
        level.resetLevel();
        if (level.getRobber().equals(Level.Robber.DR_META)) {
          GuiManager.openLevel(new GuiLevelPage(level), -1);
        } else if (level.getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          GuiManager.openLevel(new GuiLevelPageGreedy(level), -1);
        } else {
          GuiManager.openLevel(new GuiLevelPageBacktracking(level), -1);
        }
      }

    } catch (IOException | JAXBException e) {
      e.printStackTrace();
    }

  }
}
