package solving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import rucksack.Level;

/**
 * Manages Custom Levels do not make an instance all methods are static.
 */
public class CustomLevelManager {

  /**
   * @param identifier the unique identifier that will be the name of the zippedLevel
   * @param level      the level to save
   */
  public static void save(String identifier, Level level) {
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
   * @param path       the path where the zip should be saved
   * @param identifier the unique identifier that will be the name of the zippedLevel
   * @param level      the level to save
   */
  public static void save(final String path, final String identifier,
                          final Level level) {
    boolean ignoreResult = new File(path + "/temp").mkdirs();
    String levelPath = path + "/" + identifier + ".xml";
    try {
      FileOutputStream fos = new FileOutputStream(levelPath);
      JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
      Marshaller marsh = jaxbContext.createMarshaller();

      marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      marsh.marshal(level, fos);

      fos.close();

      ArrayList<String> srcFiles = new ArrayList<>();
      srcFiles.add(levelPath);
      for (int i = 0; i < level.getItemList().size(); i++) {
        if (new File(AppData
            .getCustomLevelPictureFolder() + "/picture" + i).exists()) {
          srcFiles.add(AppData
              .getCustomLevelPictureFolder() + "/picture" + i);
        }
      }
      String zipPath = Paths.get(levelPath).toAbsolutePath().toString();
      final FileOutputStream fos2 = new FileOutputStream(
          zipPath.substring(0, zipPath.length() - 4) + ".zip");
      ZipOutputStream zipOut = new ZipOutputStream(fos2);
      for (String srcFile : srcFiles) {
        File fileToZip = new File(srcFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
          zipOut.write(bytes, 0, length);
        }
        fis.close();
      }

      zipOut.close();
      fos2.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void load(String path) {
    // String vs. File depends on file picker I guess
  }

  public static void load(File zippedLevel) {

  }

  public static void cleanCustomLevelFolder() {
    // Delete all non Zip files/folder in folder ?
  }
}
