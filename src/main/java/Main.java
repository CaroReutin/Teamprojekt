import gui.level.GuiManager;
import solving.AppData;
import solving.UserDataManager;

/**
 * The type Main.
 */
public final class Main {
  /**
   * Do not construct.
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
    UserDataManager.load();
    GuiManager guiManager = new GuiManager();
    guiManager.launch();
  }
}
