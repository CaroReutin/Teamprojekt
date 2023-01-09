package solving;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * The type App data.
 */
public class AppData {
  /**
   * The location where the images to be zipped are stored.
   */
  private static String customLevelPictureFolder;
  /**
   * The font to use for text.
   */
  public static final Font FONT_STYLE = new Font("Arial",
      Font.BOLD + Font.ITALIC, 30);
  /**
   * Max amount of items in custom level, used to make the GUI.
   */
  public static final int MAXIMUM_ITEMS_IN_CUSTOM_LEVEL = 16;
  /**
   * The constant LEVELAMOUNT.
   */
  public static final int LEVELAMOUNT = 15;
  /**
   * the passwords that are used.
   */
  private static ArrayList<String> passwords = new ArrayList<>();
  /**
   * the items.
   */
  private static ArrayList<Item> items = new ArrayList<>();
  /**
   * the level.
   */
  private static Level[] level = new Level[15];

  /**
   * Initialize.
   */
  public static void initialize() {
    if (System.getProperty("os.name").contains("Windows")) {
      String appdataPath = System.getenv("APPDATA");
      customLevelPictureFolder = appdataPath + "/Optimal Heist/customLevel/temp";
    } else {
      String homePath = System.getProperty("user.home", "Desktop");
      customLevelPictureFolder = homePath + "/Optimal Heist/customLevel/temp";
    }
    boolean ignoreResult = new File(customLevelPictureFolder).mkdirs();
    passwords.add("Gr33dy");
    items.add(new Item(5, 1, "coin"));
    items.add(new Item(50, 8, "crown"));
    items.add(new Item(11, 2, "pearl"));
    // Greedy Level Items
    items.add(new Item(2, 2, ""));
    items.add(new Item(2, 3, ""));
    items.add(new Item(2, 4, ""));
    items.add(new Item(3, 2, ""));
    items.add(new Item(3, 3, ""));
    items.add(new Item(3, 4, "1"));
    items.add(new Item(4, 3, ""));
    items.add(new Item(4, 4, "2"));
    items.add(new Item(5, 5, ""));
    items.add(new Item(6, 2, "3"));
    items.add(new Item(6, 4, ""));
    items.add(new Item(6, 5, ""));
    items.add(new Item(6, 7, ""));
    items.add(new Item(7, 4, ""));
    items.add(new Item(8, 9, ""));
    items.add(new Item(9, 5, ""));
    items.add(new Item(14, 7, ""));
    items.add(new Item(14, 12, ""));
    items.add(new Item(15, 7, ""));
    items.add(new Item(16, 8, ""));
    items.add(new Item(18, 6, ""));
    items.add(new Item(18, 8, ""));
    items.add(new Item(20, 8, ""));
    items.add(new Item(26, 10, ""));
    items.add(new Item(36, 12, ""));
    items.add(new Item(38, 16, ""));
    items.add(new Item(40, 16, ""));
    items.add(new Item(42, 15, ""));
    items.add(new Item(50, 20, ""));
    items.add(new Item(51, 20, ""));
    items.add(new Item(60, 24, ""));
    items.add(new Item(84, 21, ""));
    items.add(new Item(88, 22, ""));
    items.add(new Item(120, 30, ""));
    items.add(new Item(128, 32, ""));
    items.add(new Item(210, 70, ""));
    items.add(new Item(245, 70, ""));
    items.add(new Item(350, 100, ""));
    items.add(new Item(426, 426, ""));
    items.add(new Item(882, 252, ""));
    items.add(new Item(1127, 322, ""));
    items.add(new Item(2155, 431, ""));
    // Greedy Level 1
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    currentItems.add(generateItem("1"));
    currentAmount.add(1);
    currentItems.add(generateItem("2"));
    currentAmount.add(1);
    currentItems.add(generateItem("3"));
    currentAmount.add(1);
    level[1] = new Level(currentItems, currentAmount, 1, 6);
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    // Greedy Level 2 ...
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
  public static String getPassword(int i) {
    return passwords.get(i);
  }

  /**
   * Get password amount int.
   *
   * @return the int
   */
  public static int getPasswordAmount() {
    return passwords.size();
  }

  /**
   * Generate item item.
   *
   * @param name the unique name of the item
   * @return returns a new Instance of the wanted item if it is in the ArrayList else it returns null
   */
  public static Item generateItem(String name) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getName().matches(name)) {
        return new Item(items.get(i).getValue(), items.get(i).getWeight(), items.get(i).getName());
      }
    }
    return null;
  }

  /**
   * Get level level.
   *
   * @param i the
   * @return the level
   */
  public static Level getLevel(int i) {
    return level[i];
  }
}
