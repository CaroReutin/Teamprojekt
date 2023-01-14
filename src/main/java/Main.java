
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
    ArrayList<Item> items = new ArrayList<>();
    /*Item coin = new Item(7, 6,"coin");
    Item crown = new Item(8, 6, "crown");
    Item pearl = new Item(20, 4, "pearl");*/
    Item coin = new Item(6, 6,"coin");
    Item crown = new Item(4, 3, "crown");
    Item pearl = new Item(4, 1, "pearl");
    Item picture = new Item(7,4, "picture");


    items.add(coin);
    items.add(crown);
    items.add(pearl);
    items.add(picture);
    ArrayList<Integer> amount = new ArrayList<>();
    amount.add(1);
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