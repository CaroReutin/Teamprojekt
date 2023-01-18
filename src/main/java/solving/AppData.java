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


    //Einführungslevel
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    // Greedy Level 1
    currentItems.add(generateItem(9));
    currentAmount.add(1);
    currentItems.add(generateItem(11));
    currentAmount.add(1);
    currentItems.add(generateItem(13));
    currentAmount.add(1);
    level[1] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 1, 6);

    // Greedy Level 2
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(13));
    currentAmount.add(1);
    currentItems.add(generateItem(6));
    currentAmount.add(1);
    currentItems.add(generateItem(12));
    currentAmount.add(1);
    level[2] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 2, 9);

    //Greedy Level 3
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(4));
    currentAmount.add(1);
    currentItems.add(generateItem(18));
    currentAmount.add(1);
    level[3] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 3, 9);


    //Greedy Level 4
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(15));
    currentAmount.add(1);
    currentItems.add(generateItem(16));
    currentAmount.add(1);
    currentItems.add(generateItem(21));
    currentAmount.add(1);
    currentItems.add(generateItem(10));
    currentAmount.add(1);
    currentItems.add(generateItem(7));
    currentAmount.add(2);
    level[4] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 4, 20);


    //Greedy Level 5
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(29));
    currentAmount.add(1);
    currentItems.add(generateItem(19));
    currentAmount.add(1);
    currentItems.add(generateItem(22));
    currentAmount.add(1);
    currentItems.add(generateItem(17));
    currentAmount.add(1);
    currentItems.add(generateItem(14));
    currentAmount.add(1);
    currentItems.add(generateItem(8));
    currentAmount.add(1);
    currentItems.add(generateItem(5));
    currentAmount.add(1);
    currentItems.add(generateItem(32));
    currentAmount.add(1);
    level[5] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 5, 35);


    //Greedy Level 6
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(31));
    currentAmount.add(1);
    currentItems.add(generateItem(27));
    currentAmount.add(1);
    currentItems.add(generateItem(33));
    currentAmount.add(1);
    currentItems.add(generateItem(26));
    currentAmount.add(1);
    currentItems.add(generateItem(34));
    currentAmount.add(1);
    currentItems.add(generateItem(30));
    currentAmount.add(1);
    currentItems.add(generateItem(26));
    currentAmount.add(1);
    currentItems.add(generateItem(20));
    currentAmount.add(1);
    currentItems.add(generateItem(47));
    currentAmount.add(1);
    level[6] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 6, 100);


    //Greedy Level 7
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(45));
    currentAmount.add(1);
    currentItems.add(generateItem(42));
    currentAmount.add(1);
    currentItems.add(generateItem(38));
    currentAmount.add(1);
    currentItems.add(generateItem(35));
    currentAmount.add(1);
    currentItems.add(generateItem(36));
    currentAmount.add(1);
    currentItems.add(generateItem(37));
    currentAmount.add(1);
    currentItems.add(generateItem(44));
    currentAmount.add(1);
    currentItems.add(generateItem(40));
    currentAmount.add(1);
    currentItems.add(generateItem(43));
    currentAmount.add(1);
    currentItems.add(generateItem(41));
    currentAmount.add(1);
    currentItems.add(generateItem(39));
    currentAmount.add(2);
    currentItems.add(generateItem(28));
    currentAmount.add(1);
    currentItems.add(generateItem(24));
    currentAmount.add(2);
    level[7] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 7, 426);


    //Backtracking Level
    //Backtracking Level 1
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(48));
    currentAmount.add(1);
    currentItems.add(generateItem(49));
    currentAmount.add(1);
    currentItems.add(generateItem(50));
    currentAmount.add(1);
    level[8] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 8, 10);


    //Backtracking Level 2
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(51));
    currentAmount.add(1);
    currentItems.add(generateItem(10));
    currentAmount.add(1);
    currentItems.add(generateItem(52));
    currentAmount.add(1);
    currentItems.add(generateItem(17));
    currentAmount.add(1);
    level[9] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 9, 10);


    //Backtracking Level 3
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(53));
    currentAmount.add(1);
    currentItems.add(generateItem(54));
    currentAmount.add(1);
    currentItems.add(generateItem(55));
    currentAmount.add(1);
    currentItems.add(generateItem(56));
    currentAmount.add(1);
    currentItems.add(generateItem(57));
    currentAmount.add(1);
    level[10] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 10, 20);

    //Backtracking Level 4
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(58));
    currentAmount.add(1);
    currentItems.add(generateItem(59));
    currentAmount.add(1);
    currentItems.add(generateItem(14));
    currentAmount.add(1);
    currentItems.add(generateItem(60));
    currentAmount.add(1);
    currentItems.add(generateItem(61));
    currentAmount.add(1);
    level[11] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 11, 14);

    //Backtracking Level 5
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(62));
    currentAmount.add(1);
    currentItems.add(generateItem(55));
    currentAmount.add(1);
    currentItems.add(generateItem(63));
    currentAmount.add(1);
    currentItems.add(generateItem(64));
    currentAmount.add(1);
    currentItems.add(generateItem(65));
    currentAmount.add(1);
    level[12] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 12, 20);

    //Backtracking Level 6
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(66));
    currentAmount.add(1);
    currentItems.add(generateItem(67));
    currentAmount.add(1);
    currentItems.add(generateItem(68));
    currentAmount.add(1);
    currentItems.add(generateItem(17));
    currentAmount.add(1);
    currentItems.add(generateItem(69));
    currentAmount.add(1);
    level[13] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 13, 25);

    //Backtracking Level 7
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(generateItem(70));
    currentAmount.add(1);
    currentItems.add(generateItem(71));
    currentAmount.add(1);
    currentItems.add(generateItem(72));
    currentAmount.add(1);
    currentItems.add(generateItem(73));
    currentAmount.add(1);
    currentItems.add(generateItem(66));
    currentAmount.add(1);
    currentItems.add(generateItem(74));
    currentAmount.add(1);
    level[14] = new Level(currentItems, currentAmount, Level.Robber.BACKTRACKING_BANDIT, 14, 42);
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

