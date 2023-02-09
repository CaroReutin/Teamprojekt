package solving;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;
import rucksack.Item;
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
   * do not make.
   */
  private AppData() {

  }

  /**
   * The size of Icons in the Leveleditor.
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
   * The font to use for text.
   */
  public static final Font FONT_STYLE = new Font("Arial",
      Font.BOLD + Font.ITALIC, 30);
  /**
   * Max amount of items in custom level, used to make the GUI.
   */
  public static final int MAXIMUM_ITEMS_IN_CUSTOM_LEVEL = 15;
  /**
   * The constant LEVELAMOUNT.
   */
  public static final int LEVELAMOUNT = 15;
  /**
   * the passwords that are used.
   */
  private static final ArrayList<String> PASSWORDS = new ArrayList<>();
  /**
   * the items.
   */
  private static final ArrayList<Item> ITEMS = new ArrayList<>();

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
    boolean ignoreResult = new File(customLevelPictureFolder).mkdirs();
    PASSWORDS.add("Gr33dy");
    PASSWORDS.add("B4cktr4cking");
  }


  /**
   * Load level level.
   *
   * @param zippedLevel the zipped level
   * @return the level
   */
  public static Level loadLevel(final File zippedLevel) {
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
      return null;
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
        for (int i = 0; i < level.getItemList().size(); i++) {
          File picture = new File(destDir + "/picture" + i + ".png");
          if (picture.exists()) {
            level.setItemIcon(i, new ImageIcon(new ImageIcon(picture.getAbsolutePath())
              .getImage().getScaledInstance(AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH)));
          }
        }
        return level;
      }



    } catch (IOException | JAXBException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Initialize beginning level.
   */
  public static void initializeBeginningLevel() {
    LEVEL_ZERO[0] = loadLevel(new File("src/main/resources/"
        + "Level/Startlevel.zip"));
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
   * Gets greedy level.
   *
   * @param level the level
   * @return the greedy level
   */
  public static Level getGreedyLevel(final int level) {
    return GreedyLevel.getLevelGreedy(level);
  }

  /**
   * Gets backtracking level.
   *
   * @param level the level
   * @return the backtracking level
   */
  public static Level getBacktrackingLevel(final int level) {
    return BacktrackingLevel.getLevelBacktracking(level);
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
   * Get password amount int.
   *
   * @return the int
   */
  public static int getPasswordAmount() {
    return PASSWORDS.size();
  }

  /**
   * Generate item.
   *
   * @param index the unique index of the item
   * @return returns a new Instance of the wanted item if it
   *          is in the ArrayList else it returns null
   */
  public static Item generateItem(final int index) {
    return new Item(ITEMS.get(index).getValue(), ITEMS.get(index).getWeight(),
        ITEMS.get(index).getName(), ITEMS.get(index).getImageIcon());
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
