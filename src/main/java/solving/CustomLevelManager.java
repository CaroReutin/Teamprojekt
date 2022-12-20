package solving;

import java.io.File;
import java.io.FileOutputStream;
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
  public static void save(String path, String identifier, Level level) {
    new File(path + "/temp").mkdirs();
    String levelPath = path + "/" + identifier + ".xml";
    try {
      FileOutputStream fos = new FileOutputStream(levelPath);
      JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
      Marshaller marsh = jaxbContext.createMarshaller();

      marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      marsh.marshal(level, fos);

      fos.close();
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
