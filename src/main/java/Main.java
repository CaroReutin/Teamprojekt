
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

   AppData.initialize();
   UserDataManager.load();

   GUIManager guiManager = new GUIManager();
   guiManager.launch();


  }
}