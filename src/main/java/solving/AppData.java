package solving;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * The type App data.
 */
public final class AppData {
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
  public static final int MAXIMUM_ITEMS_IN_CUSTOM_LEVEL = 16;
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
   * Initialize beginning level.
   */
  public static void initializeBeginningLevel() {
    //Einführungslevel
    ITEMS.add(new Item(3, 5, "coin")); //0
    ITEMS.add(new Item(4, 5, "crown")); //1
    ITEMS.add(new Item(2, 2, "book")); //2
    ITEMS.add(new Item(4, 3, "pearl")); //3
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    currentItems.add(generateItem(0));
    currentAmount.add(1);
    currentItems.add(generateItem(1));
    currentAmount.add(1);
    currentItems.add(generateItem(2));
    currentAmount.add(1);
    currentItems.add(generateItem(3));
    currentAmount.add(1);
    LEVEL_ZERO[0] = new Level(currentItems, currentAmount,
        Level.Robber.DR_META, 0, 5);

  }

  /**
   * Initialize items. initialize greedy and backtracking level.
   */
  public static void initializeItems() {
    ITEMS.add(new Item(2, 2, "gem")); //4
    ITEMS.add(new Item(2, 3, "gold bars")); //5
    ITEMS.add(new Item(2, 4, "cup")); //6
    ITEMS.add(new Item(3, 2, "piggy bank")); //7
    ITEMS.add(new Item(3, 3, "dinosaur bones")); //8
    ITEMS.add(new Item(3, 4, "painting")); //9
    ITEMS.add(new Item(4, 3, "statue")); //10
    ITEMS.add(new Item(4, 4, "vase")); //11
    ITEMS.add(new Item(5, 5, "dagger")); //12
    ITEMS.add(new Item(6, 2, "knife")); //13
    ITEMS.add(new Item(6, 4, "compass")); //14
    ITEMS.add(new Item(6, 5, "bust")); //15
    ITEMS.add(new Item(6, 7, "banknote")); //16
    ITEMS.add(new Item(7, 4, "safe")); //17
    ITEMS.add(new Item(8, 9, "mummy")); //18
    ITEMS.add(new Item(9, 5, "hieroglyphs")); //19
    ITEMS.add(new Item(14, 7, "chains")); //20
    ITEMS.add(new Item(14, 12, "bracelets")); //21
    ITEMS.add(new Item(15, 7, "ring")); //22
    ITEMS.add(new Item(16, 8, "headband")); //23
    ITEMS.add(new Item(18, 6, "copper bars")); //24
    ITEMS.add(new Item(18, 8, "silver bars")); //25
    ITEMS.add(new Item(20, 8, "copper bracelet")); //26
    ITEMS.add(new Item(26, 10, "silver bracelet")); //27
    ITEMS.add(new Item(36, 12, "copper headband")); //28
    ITEMS.add(new Item(38, 16, "silver headband")); //29
    ITEMS.add(new Item(40, 16, "copper chain")); //30
    ITEMS.add(new Item(42, 15, "silver chain")); //31
    ITEMS.add(new Item(50, 20, "coffin")); //32
    ITEMS.add(new Item(51, 20, "pharaoh")); //33
    ITEMS.add(new Item(60, 24, "orb")); //34
    ITEMS.add(new Item(84, 21, "scepter")); //35
    ITEMS.add(new Item(88, 22, "ermine coat")); //36
    ITEMS.add(new Item(120, 30, "rake")); //37
    ITEMS.add(new Item(128, 32, "axe")); //38
    ITEMS.add(new Item(210, 70, "sword")); //39
    ITEMS.add(new Item(245, 70, "shield")); //40
    ITEMS.add(new Item(350, 100, "pistol")); //41
    ITEMS.add(new Item(426, 426, "chain mail")); //42
    ITEMS.add(new Item(882, 252, "knight armor")); //43
    ITEMS.add(new Item(1127, 322, "clothes")); //44
    ITEMS.add(new Item(2155, 431, "quill")); //45
    ITEMS.add(new Item(127, 322, "scroll")); //46
    ITEMS.add(new Item(18, 10, "papyrus")); //47
    ITEMS.add(new Item(7, 6, "mirror")); //48
    ITEMS.add(new Item(8, 6, "love letter")); //49
    ITEMS.add(new Item(20, 4, "letter")); //50
    ITEMS.add(new Item(6, 6, "basket")); //51
    ITEMS.add(new Item(4, 1, "bed")); //52
    ITEMS.add(new Item(31, 19, "chamber pot")); //53
    ITEMS.add(new Item(14, 8, "computer")); //54
    ITEMS.add(new Item(8, 6, "laptop")); //55
    ITEMS.add(new Item(8, 2, "video game")); //56
    ITEMS.add(new Item(12, 12, "printing press")); //57
    ITEMS.add(new Item(2, 7, "garment")); //58
    ITEMS.add(new Item(7, 3, "gown")); //59
    ITEMS.add(new Item(9, 8, "stuffed animals")); //60
    ITEMS.add(new Item(8, 11, "animals")); //61
    ITEMS.add(new Item(7, 12, "bones")); //62
    ITEMS.add(new Item(4, 2, "skin")); //63
    ITEMS.add(new Item(5, 5, "clay pot")); //64
    ITEMS.add(new Item(10, 8, "automobile")); //65
    ITEMS.add(new Item(16, 18, "motorcycle")); //66
    ITEMS.add(new Item(10, 7, "microscope")); //67
    ITEMS.add(new Item(7, 3, "bacteria")); //68
    ITEMS.add(new Item(12, 14, "viruses")); //69
    ITEMS.add(new Item(25, 15, "preparations")); //70
    ITEMS.add(new Item(26, 25, "torture tools")); //71
    ITEMS.add(new Item(21, 21, "globe")); //72
    ITEMS.add(new Item(10, 6, "key")); //73
    ITEMS.add(new Item(16, 11, "frame")); //74

    GreedyLevel.initializeGreedy();
    BacktrackingLevel.initializeBacktracking();

  }

  /**
   * Initialize.
   *
   * @param level the level
   * @return the level
   */
  public static Level initializeGreedy(final int level) {
    return GreedyLevel.getLevelGreedy(level);
  }

  /**
   * Initialize backtracking level.
   *
   * @param level the level
   * @return the level
   */
  public static Level initializeBacktracking(final int level) {
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
   *      is in the ArrayList else it returns null
   */
  public static Item generateItem(final int index) {
    return new Item(ITEMS.get(index).getValue(), ITEMS.get(index).getWeight(),
        ITEMS.get(index).getName());
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
