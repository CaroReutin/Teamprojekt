import betaTree.Tree;
import gui.level.GuiManager;
import solving.AppData;
import solving.BacktrackingLevel;
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
    GuiManager guiManager = new GuiManager();
    Tree t = new Tree(BacktrackingLevel.getLevelBacktracking(0));
    //guiManager.launch();
  }
}
