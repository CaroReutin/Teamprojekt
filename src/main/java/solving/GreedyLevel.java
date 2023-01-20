package solving;

import rucksack.Item;
import rucksack.Level;

import java.util.ArrayList;

public class GreedyLevel {

  private static Level[] levelGreedy = new Level[7];

  public static void initializeGreedy(ArrayList<Item> items, ArrayList<Item> currentItems, ArrayList<Integer> currentAmount) {
    // Greedy Level 1
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(9));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(11));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(13));
    currentAmount.add(1);
    levelGreedy[0] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 1, 6);

    // Greedy Level 2
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(13));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(6));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(12));
    currentAmount.add(1);
    levelGreedy[1] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 2, 9);

    //Greedy Level 3
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(4));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(18));
    currentAmount.add(1);
    levelGreedy[2] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 3, 9);


    //Greedy Level 4
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(15));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(16));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(21));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(10));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(7));
    currentAmount.add(2);
    levelGreedy[3] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 4, 20);


    //Greedy Level 5
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(29));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(19));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(22));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(17));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(14));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(8));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(5));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(32));
    currentAmount.add(1);
    levelGreedy[4] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 5, 35);


    //Greedy Level 6
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(31));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(27));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(33));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(26));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(34));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(30));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(26));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(20));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(47));
    currentAmount.add(1);
    levelGreedy[5] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 6, 100);


    //Greedy Level 7
    currentItems = new ArrayList<>();
    currentAmount = new ArrayList<>();
    currentItems.add(AppData.generateItem(45));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(42));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(38));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(35));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(36));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(37));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(44));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(40));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(43));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(41));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(39));
    currentAmount.add(2);
    currentItems.add(AppData.generateItem(28));
    currentAmount.add(1);
    currentItems.add(AppData.generateItem(24));
    currentAmount.add(2);
    levelGreedy[6] = new Level(currentItems, currentAmount, Level.Robber.GIERIGER_GANOVE, 7, 426);

  }

  public static Level getLevelGreedy(int level) {
    return levelGreedy[level];
  }


}
