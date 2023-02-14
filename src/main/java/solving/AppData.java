package solving;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;
import rucksack.Level;

/**
 * The type App data.
 */
public final class AppData {
  /**
   * the default amount of item panels.
   */
  public static final int DEFAULT_ITEMS_IN_CUSTOM_LEVEL = 1;
  /**
   * the maximum amount of different items in backtracking levels.
   */
  public static final int MAXIMUM_ITEMS_IN_CUSTOM_BACKTRACKING_LEVEL = 5;
  /**
   * The minimum width a game frame must have.
   */
  public static final int MINIMUM_WIDTH = 800;
  /**
   * The minimum height a game frame must have.
   */
  public static final int MINIMUM_HEIGHT = 600;

  /**
   * The height and width of an image icon.
   */
  public static final int IMAGE_ICON_SIZE = 30;

  /**
   * do not make.
   */
  private AppData() {

  }

  /**
   * The size of icons in the level editor.
   */
  public static final int ICON_SIZE = 75;
  /**
   * Byte size to be used when zipping files.
   */
  public static final int ZIP_BYTE_SIZE = 1024;
  /**
   * The location where the images to be zipped are stored.
   */
  private static String customLevelPictureFolder;

  /**
   * The location where the images to be zipped are stored.
   *
   * @return returns the string with the folder path
   */
  public static String getCustomLevelUnzipFolder() {
    return customLevelUnzipFolder;
  }

  /**
   * The location where the level gets unzipped.
   */
  private static String customLevelUnzipFolder;
  /**
   * Max amount of items in custom level, used to make the GUI.
   */
  public static final int MAXIMUM_ITEMS_IN_CUSTOM_LEVEL = 15;
  /**
   * The constant LEVEL_AMOUNT.
   */
  public static final int LEVEL_AMOUNT = 15;
  /**
   * the passwords that are used.
   */
  private static final ArrayList<String> PASSWORDS = new ArrayList<>();

  /**
   * the level.
   */
  private static final Level[] LEVEL_ZERO = new Level[1];

  /**
   * Initialize.
   */
  public static void initialize() {
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      customLevelPictureFolder = appdataPath
          + "/Optimal Heist/customLevel/temp";
      customLevelUnzipFolder = appdataPath
          + "/Optimal Heist/customLevel/unzip/";
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      customLevelPictureFolder = homePath + "/Optimal Heist/customLevel/temp";
      customLevelUnzipFolder = homePath + "/Optimal Heist/customLevel/unzip/";
    }
    PASSWORDS.add("Gr33dy");
    PASSWORDS.add("B4cktr4cking");

    initializeBeginningLevel();
    initializeGreedy();
    initializeBacktrackingLevel();
  }

  /**
   * Load level.
   *
   * @param zippedLevel the zipped level
   * @return the level
   */
  public static Level loadLevel(final File zippedLevel) {
    File destDir = new File(AppData.getCustomLevelUnzipFolder());
    String levelName = CustomLevelManager.unzip(zippedLevel, destDir);
    if (levelName == null) {
      return null;
    }
    File levelFile = new File(destDir + "/" + levelName);
    Level level = CustomLevelManager.convertLevelfileToLevel(levelFile);
    assert level != null;
    for (int i = 0; i < level.getItemList().size(); i++) {
      File currentPicture = new File(destDir
          .getAbsolutePath() + "/picture" + i + ".png");
      if (currentPicture.exists()) {
        level.setItemIcon(i, new ImageIcon(new ImageIcon(currentPicture
            .getAbsolutePath()).getImage()
            .getScaledInstance(IMAGE_ICON_SIZE,
                    IMAGE_ICON_SIZE,
                    Image.SCALE_SMOOTH)));
      }
    }
    return level;
  }

  /**
   * Initialize beginning level.
   */
  public static void initializeBeginningLevel() {
    try {
      InputStream is = AppData.class.getClassLoader().getResourceAsStream(
              "Level/Startlevel.zip");
      File file = File.createTempFile("Startlevel", "zip");
      assert is != null;
      FileUtils.copyInputStreamToFile(is, file);
      LEVEL_ZERO[0] = loadLevel(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Initialize greedy.
   */
  public static void initializeGreedy() {
    GreedyLevel.initializeGreedy();
  }

  /**
   * Initialize backtracking level.
   */
  public static void initializeBacktrackingLevel() {
    BacktrackingLevel.initializeBacktracking();
  }


  /**
   * based on os.
   *
   * @return returns the path for the pictures
   */
  public static String getCustomLevelPictureFolder() {
    return customLevelPictureFolder;
  }

  /**
   * Get password string.
   *
   * @param i the
   * @return the string
   */
  public static String getPassword(final int i) {
    return PASSWORDS.get(i);
  }

  /**
   * Get level.
   *
   * @param i the
   * @return the level
   */
  public static Level getLevel(final int i) {
    return LEVEL_ZERO[i];
  }
}
