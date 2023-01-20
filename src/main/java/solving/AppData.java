package solving;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import rucksack.*;

/**
 * The type App data.
 */
public class AppData {
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
  private static HashMap<Key, String> allItems = new HashMap<>();
  /**
   * the level.
   */
  private static Level[] levelZero = new Level[1];

  public static void InitializeBeginningLevel() {
    //Einführungslevel
    items.add(new Item(3, 5, "coin")); //0
    items.add(new Item(4, 5, "crown")); //1
    items.add(new Item(2, 2, "buch")); //2
    items.add(new Item(4, 3, "pearl")); //3
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
    levelZero[0] = new Level(currentItems, currentAmount, Level.Robber.DR_META, 0, 5);

  }

  public static void initializeItems() {
    items.add(new Item(2, 2, "Muschel")); //4
    items.add(new Item(2, 3, "")); //5
    items.add(new Item(2, 4, "")); //6
    items.add(new Item(3, 2, "")); //7
    items.add(new Item(3, 3, "")); //8
    items.add(new Item(3, 4, "1")); //9
    items.add(new Item(4, 3, "")); //10
    items.add(new Item(4, 4, "2")); //11
    items.add(new Item(5, 5, "")); //12
    items.add(new Item(6, 2, "3")); //13
    items.add(new Item(6, 4, "")); //14
    items.add(new Item(6, 5, "")); //15
    items.add(new Item(6, 7, "")); //16
    items.add(new Item(7, 4, "")); //17
    items.add(new Item(8, 9, "")); //18
    items.add(new Item(9, 5, "")); //19
    items.add(new Item(14, 7, "")); //20
    items.add(new Item(14, 12, "")); //21
    items.add(new Item(15, 7, "")); //22
    items.add(new Item(16, 8, "")); //23
    items.add(new Item(18, 6, "")); //24
    items.add(new Item(18, 8, "")); //25
    items.add(new Item(20, 8, "")); //26
    items.add(new Item(26, 10, "")); //27
    items.add(new Item(36, 12, "")); //28
    items.add(new Item(38, 16, "")); //29
    items.add(new Item(40, 16, "")); //30
    items.add(new Item(42, 15, "")); //31
    items.add(new Item(50, 20, "")); //32
    items.add(new Item(51, 20, "")); //33
    items.add(new Item(60, 24, "")); //34
    items.add(new Item(84, 21, "")); //35
    items.add(new Item(88, 22, "")); //36
    items.add(new Item(120, 30, "")); //37
    items.add(new Item(128, 32, "")); //38
    items.add(new Item(210, 70, "")); //39
    items.add(new Item(245, 70, "")); //40
    items.add(new Item(350, 100, "")); //41
    items.add(new Item(426, 426, "")); //42
    items.add(new Item(882, 252, "")); //43
    items.add(new Item(1127, 322, "")); //44
    items.add(new Item(2155, 431, "")); //45
    items.add(new Item(127, 322, "")); //46
    items.add(new Item(18, 10, "")); //47
    items.add(new Item(7,6,"")); //48
    items.add(new Item(8, 6, "")); //49
    items.add(new Item(20, 4, "")); //50
    items.add(new Item(6, 6, "")); //51
    items.add(new Item(4, 1, "")); //52
    items.add(new Item(31, 19, "")); //53
    items.add(new Item(14, 8, "")); //54
    items.add(new Item(8, 6, "")); //55
    items.add(new Item(8, 2, "")); //56
    items.add(new Item(12, 12, "")); //57
    items.add(new Item(2, 7, "")); //58
    items.add(new Item(7, 3, "")); //59
    items.add(new Item(9, 8, "")); //60
    items.add(new Item(8, 11, "")); //61
    items.add(new Item(7, 12, "")); //62
    items.add(new Item(4, 2, "")); //63
    items.add(new Item(5, 5, "")); //64
    items.add(new Item(10, 8, "")); //65
    items.add(new Item(16, 18, "")); //66
    items.add(new Item(10, 7, "")); //67
    items.add(new Item(7, 3, "")); //68
    items.add(new Item(12, 14, "")); //69
    items.add(new Item(25, 15, "")); //70
    items.add(new Item(26, 25, "")); //71
    items.add(new Item(21, 21, "")); //72
    items.add(new Item(10, 6, "")); //73
    items.add(new Item(16, 11, "")); //74

    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();

    GreedyLevel.initializeGreedy(items, currentItems, currentAmount);
    BacktrackingLevel.initializeBacktracking(items, currentItems, currentAmount);

  }

  /**
   * Initialize.
   */
  public static Level initializeGreedy(int level) {
    passwords.add("Gr33dy");
    return GreedyLevel.getLevelGreedy(level);

  }

  public static Level initializeBacktracking(int level) {
    passwords.add("");
    return BacktrackingLevel.getLevelBacktracking(level);
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
   * @param index the unique index of the item
   * @return returns a new Instance of the wanted item if it is in the ArrayList else it returns null
   */
  public static Item generateItem(int index) {

    return new Item(items.get(index).getValue(), items.get(index).getWeight(), items.get(index).getName());
  }

  /**
   * Get level level.
   *
   * @param i the
   * @return the level
   */
  public static Level getLevel(int i) {
    return levelZero[i];
  }

}

