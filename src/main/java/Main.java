
import backtrackingtree.TestTree;
import gui.level.GUIManager;
import rucksack.Item;
import solving.*;

import java.util.ArrayList;

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

    TestTree tree = new TestTree();
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

   /* AppData.initialize();
    UserDataManager.load();

    GUIManager guiManager = new GUIManager();
    guiManager.launch();*/

  }
}