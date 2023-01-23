
import gui.level.GuiManager;
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
    AppData.initialize();
    AppData.InitializeBeginningLevel();
    AppData.initializeItems();
    UserDataManager.load();

    GuiManager guiManager = new GuiManager();
    guiManager.launch();
  }
}