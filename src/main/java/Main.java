import gui.level.GUIManager;
import solving.AppData;
import solving.UserDataManager;

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