package solving;

import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * The type Backtracking level.
 */
public final class BacktrackingLevel {
  /**
   * do not make.
   */
  private BacktrackingLevel() {

  }

  /**
   * The Backtracking level.
   */
  private static final Level[] LEVEL_BACKTRACKING = new Level[7];

  /**
   * Initialize backtracking.
   */
  public static void initializeBacktracking() {
    //Backtracking Level
    //Backtracking Level 1
    ArrayList<Item> currentItems = new ArrayList<>();
    ArrayList<Integer> currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(48));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(49));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(50));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[0] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 8, 10);


    //Backtracking Level 2
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(51));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(10));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(52));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(17));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[1] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 9, 10);


    //Backtracking Level 3
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(53));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(54));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(55));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(56));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(57));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[2] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 10, 20);

    //Backtracking Level 4
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(58));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(59));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(14));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(60));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(61));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[3] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 11, 14);

    //Backtracking Level 5
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(62));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(55));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(63));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(64));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(65));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[4] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 12, 20);

    //Backtracking Level 6
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(66));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(67));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(68));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(17));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(69));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[5] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 13, 25);

    //Backtracking Level 7
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(70));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(71));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(72));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(73));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(66));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(74));
    currentAmount.add(1);
    LEVEL_BACKTRACKING[6] =
        new Level(currentItems, currentAmount,
            Level.Robber.BACKTRACKING_BANDIT, 14, 42);
  }

  /**
   * Gets level backtracking.
   *
   * @param level the level
   * @return the level backtracking
   */
  public static Level getLevelBacktracking(final int level) {
    return LEVEL_BACKTRACKING[level];
  }

}
