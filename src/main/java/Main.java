
import gui.level.GUIManager;
import solving.*;

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
    AppData.InitializeBeginningLevel();
    AppData.initializeGreedy();
    UserDataManager.load();

    GUIManager guiManager = new GUIManager();
    guiManager.launch();
  }
}