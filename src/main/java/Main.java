
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
    Item coin = new Item(5, 1,"coin");
    Item crown = new Item(50, 8, "crown");
    Item pearl = new Item(11, 2, "pearl");
    items.add(coin);
    items.add(crown);
    items.add(pearl);
    ArrayList<Integer> amount = new ArrayList<>();
    amount.add(10);
    amount.add(7);
    amount.add(4);

    Solver solver = new Solver();
    solver.solveBacktracking(items, amount, 15);

   /* AppData.initialize();
    UserDataManager.load();

    GUIManager guiManager = new GUIManager();
    guiManager.launch();*/
  }
}