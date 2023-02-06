import gui.level.GuiManager;
import solving.AppData;
import solving.UserDataManager;

import javax.swing.*;

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
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
      e.printStackTrace();
    }
    AppData.initialize();
    AppData.initializeBeginningLevel();
    AppData.initializeGreedy();
    AppData.initializeBacktrackingLevel();
    UserDataManager.load();
    GuiManager guiManager = new GuiManager();
    guiManager.launch();
  }
}
