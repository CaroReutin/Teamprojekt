import gui.level.GuiManager;
import jtree.BacktrackingTree;
import solving.AppData;
import solving.GreedyLevel;
import solving.UserDataManager;

/**
 * The type Main.
 */
public final class Main {
  /**
   * do not make.
   */
  private Main() {

  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    AppData.initialize();
    AppData.initializeBeginningLevel();
    AppData.initializeItems();
    UserDataManager.load();
    BacktrackingTree test = new BacktrackingTree(GreedyLevel.getLevelGreedy(3));
    GuiManager guiManager = new GuiManager();
    //guiManager.launch();
  }
}
