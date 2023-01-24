package solving;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * The type User data manager.
 */
public final class UserDataManager {

  /**
   * do not make.
   */
  private UserDataManager() {

  }


  /**
   * the new user data.
   */
  private static UserData data = new UserData();

  /**
   * Set every current high score to 0.
   */
  public static void newUser() {
    data.newUser();
  }

  /**
   * Tries to get default save location
   * based on OS then calls save(String saveFolder) with that location.
   * Always Overwrites old save file if it exists in default save location.
   */
  public static void save() {
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      String saveFolder = appdataPath + "/Optimal Heist/savedata";
      save(saveFolder);
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      String saveFolder = homePath + "/Optimal Heist/savedata";
      save(saveFolder);
    }
  }

  /**
   * Always Overwrites old save file if it exists in saveFolder.
   *
   * @param saveFolder the Folder in which userData.xml should be saved in
   */
  public static void save(final String saveFolder) {
    Boolean ignoreResult = new File(saveFolder).mkdirs();
    String saveFilePath = saveFolder + "/userData.xml";
    try {
      FileOutputStream fos = new FileOutputStream(saveFilePath);
      JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
      Marshaller marsh = jaxbContext.createMarshaller();

      marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      marsh.marshal(data, fos);

      fos.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Tries to get default save location based on OS,
   * then calls load(String saveFolder) with that location.
   * If the userData.xml file is not in the specified directory,
   * a new User will be created and saved in that directory.
   */
  public static void load() {
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      String saveFolder = appdataPath + "/Optimal Heist/savedata";
      load(saveFolder);
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      String saveFolder = homePath + "/Optimal Heist/savedata";
      load(saveFolder);
    }
  }

  /**
   * If the userData.save file is not in the specified directory,
   * a new User will be created and saved in that directory.
   *
   * @param saveFolder the Folder that contains userData.save
   */
  public static void load(final String saveFolder) {
    Boolean ignoreResult = new File(saveFolder).mkdirs();
    String saveFilePath = saveFolder + "/userData.xml";
    File saveFile = new File(saveFilePath);
    if (!saveFile.exists()) {
      newUser();
      save(saveFolder);
      return;
    }
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
      Unmarshaller marsh = jaxbContext.createUnmarshaller();

      data = (UserData) marsh.unmarshal(saveFile);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets score.
   *
   * @param level the index of the level
   * @return returns the current high score
   * @throws IndexOutOfBoundsException if there is no entry for level
   */
  public static int getScore(final int level) throws IndexOutOfBoundsException {
    return data.getScore(level);
  }

  /**
   * There is no check whether the newHighScore is actually higher than before.
   *
   * @param level the index of the level
   * @param score the new high score
   * @throws IndexOutOfBoundsException if there is no entry for level
   */
  public static void newHighScore(final int level, final int score)
      throws IndexOutOfBoundsException {
    data.newHighScore(level, score);
  }

  /**
   * Resets User Data and sets all current high scores to 0.
   */
  public static void reset() {
    data = new UserData();
    data.newUser();
  }

  /**
   * Data to string string.
   *
   * @return returns the Scores in format x1|x2|...|x14|x15
   *      where xn is the score of the nth Level
   */
  public static String dataToString() {
    return data.toString();
  }
}
