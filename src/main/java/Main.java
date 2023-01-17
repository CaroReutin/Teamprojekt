
import backtrackingtree.BacktrackingTree;
import backtrackingtree.TestTree;
import gui.level.GUIManager;
import rucksack.BacktrackingItem;
import rucksack.Item;
import solving.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Main.
 */
public class Main {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {

   // TestTree tree = new TestTree();
//    AppData.initialize();
//    UserDataManager.load();
//
//    GUIManager guiManager = new GUIManager();
//    guiManager.launch();
    ArrayList<Item> items = new ArrayList<>();
    Item coin = new Item(7, 6,"coin");
    Item crown = new Item(8, 6, "crown");
    Item pearl = new Item(20, 4, "pearl");


    items.add(coin);
    items.add(crown);
    items.add(pearl);

    ArrayList<Integer> amount = new ArrayList<>();
    amount.add(1);
    amount.add(1);
    amount.add(1);


    SolverBacktracking solver = new SolverBacktracking();
    solver.solveBacktracking(items, amount, 10);
/*
    ArrayList<BacktrackingItem>backtrackingItems =new ArrayList<>();
    BacktrackingItem item1 = new BacktrackingItem(7, 6, "Coin");
    BacktrackingItem item2 = new BacktrackingItem(8, 6, "Pearl");
    BacktrackingItem item3 = new BacktrackingItem(20, 4, "Crown");
    backtrackingItems.add(item1);
    backtrackingItems.add(item2);
    backtrackingItems.add(item3);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());
    BacktrackingTree treeTest = new BacktrackingTree(10, backtrackingItems);
    treeTest.print(System.out);*/

   /* AppData.initialize();
    UserDataManager.load();

    GUIManager guiManager = new GUIManager();
    guiManager.launch();*/

  }
}