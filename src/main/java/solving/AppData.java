package solving;

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
  private static Level[] level = new Level[15];

  public static void initializeItems() {
    allItems.put(new Key(3, 5),"Münze");
    allItems.put(new Key(4, 5),"Krone");
    allItems.put(new Key(2, 2),"Buch");
    allItems.put(new Key(4, 3),"Perle");
  }

  public static void InitializeBeginningLevel() {

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
    level[0] = new Level(currentItems, currentAmount, Level.Robber.DR_META, 0, 5);

  }
  /**
   * Initialize.
   */
  public static void initializeGreedy() {
    passwords.add("Gr33dy");
    // Greedy Level Items

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

    //Backtracking


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

