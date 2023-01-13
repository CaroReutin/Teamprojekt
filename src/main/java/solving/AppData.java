package solving;

import java.util.ArrayList;
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
  /**
   * the level.
   */
  private static Level[] level = new Level[15];

  public static void InitializeBeginningLevel() {
    items.add(new Item(5, 1, "coin"));
    items.add(new Item(50, 8, "crown"));
    items.add(new Item(11, 2, "pearl"));
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    currentItems.add(generateItem(0));
    currentAmount.add(10);
    currentItems.add(generateItem(1));
    currentAmount.add(7);
    currentItems.add(generateItem(2));
    currentAmount.add(4);
    level[0] = new Level(currentItems, currentAmount, Level.Robber.DR_META, 1, 60);

  }
  /**
   * Initialize.
   */
  public static void initializeGreedy() {
    passwords.add("Gr33dy");
    // Greedy Level Items
    items.add(new Item(2, 2, "")); //1
    items.add(new Item(2, 3, "")); //2
    items.add(new Item(2, 4, "")); //3
    items.add(new Item(3, 2, "")); //4
    items.add(new Item(3, 3, "")); //5
    items.add(new Item(3, 4, "1")); //6
    items.add(new Item(4, 3, "")); //7
    items.add(new Item(4, 4, "2")); //8
    items.add(new Item(5, 5, "")); //9
    items.add(new Item(6, 2, "3")); //10
    items.add(new Item(6, 4, "")); //11
    items.add(new Item(6, 5, "")); //12
    items.add(new Item(6, 7, "")); //13
    items.add(new Item(7, 4, "")); //14
    items.add(new Item(8, 9, "")); //15
    items.add(new Item(9, 5, "")); //16
    items.add(new Item(14, 7, "")); //17
    items.add(new Item(14, 12, "")); //18
    items.add(new Item(15, 7, "")); //19
    items.add(new Item(16, 8, "")); //20
    items.add(new Item(18, 6, "")); //21
    items.add(new Item(18, 8, "")); //22
    items.add(new Item(20, 8, "")); //23
    items.add(new Item(26, 10, "")); //24
    items.add(new Item(36, 12, "")); //25
    items.add(new Item(38, 16, "")); //26
    items.add(new Item(40, 16, "")); //27
    items.add(new Item(42, 15, "")); //28
    items.add(new Item(50, 20, "")); //29
    items.add(new Item(51, 20, "")); //30
    items.add(new Item(60, 24, "")); //31
    items.add(new Item(84, 21, "")); //32
    items.add(new Item(88, 22, "")); //33
    items.add(new Item(120, 30, "")); //34
    items.add(new Item(128, 32, "")); //35
    items.add(new Item(210, 70, "")); //36
    items.add(new Item(245, 70, "")); //37
    items.add(new Item(350, 100, "")); //38
    items.add(new Item(426, 426, "")); //39
    items.add(new Item(882, 252, "")); //40
    items.add(new Item(1127, 322, "")); //41
    items.add(new Item(2155, 431, "")); //42

    //Einfühungslevel
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    // Greedy Level 1
    currentItems.add(generateItem(8));
    currentAmount.add(1);
    currentItems.add(generateItem(10));
    currentAmount.add(1);
    currentItems.add(generateItem(12));
    currentAmount.add(1);
    level[1] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 1, 6);
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    // Greedy Level 2 ...
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
    return level[i];
  }
}
