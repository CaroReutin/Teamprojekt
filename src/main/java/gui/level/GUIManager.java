

package gui.level;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The class GUIManager combines the gui pages.
 */
public class GUIManager {
  /**
   * the current gui frontpage.
   */
  private static final GUIFrontpage GUI_FRONTPAGE = new GUIFrontpage();
  /**
   * the current gui optionspage.
   */
  private static final GUIOptionsPage GUI_OPTIONS_PAGE = new GUIOptionsPage();
  /**
   * the current gui leveldeciderpage.
   */
  private static final GUILevelDeciderPage GUI_LEVEL_DECIDER_PAGE
      = new GUILevelDeciderPage();
  /**
   * the current gui Level editor page.
   */
  private static final GuiLevelEditorPage GUI_LEVEL_EDITOR_PAGE
      = new GuiLevelEditorPage();


  /**
   * the frame of all windows.
   */
  private static JFrame frame;

  /**
   * Opens the Leveleditor.
   */
  public static void openLevelEditor() {
    frame.setContentPane(GUI_LEVEL_EDITOR_PAGE.getPane());
    frame.setTitle("Level-editor");
    paint();
  }

  /**
   * Opens the main Menu.
   */
  public void launch() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setTitle("Optimal Heist");
    frame.setSize(1000, 750);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
    openMainMenu();
  }

  /**
   * Must be called after every pane change.
   */
  private static void paint() {
    frame.getContentPane().revalidate();
    frame.getContentPane().repaint();
    frame.revalidate();
    frame.repaint();
  }

  /**
   * Opens the Options Menu.
   * (Currently after exiting the Options menu the Main-menu will always be opened
   * regardless where the options menu was opened from).
   */
  public static void openOptionsMenu() {
    frame.setContentPane(GUI_OPTIONS_PAGE.getPane());
    frame.setTitle("Optionsmenü");
    paint();
  }

  /**
   * Opens the LevelSelectionScreen.
   */
  public static void openLevelSelectScreen() {
    frame.setContentPane(GUI_LEVEL_DECIDER_PAGE.getPane());
    frame.setTitle("Levelauswahl");
    paint();
  }

  /**
   * Opens the main Menu.
   */
  public static void openMainMenu() {
    frame.setContentPane(GUI_FRONTPAGE.getPane());
    frame.setTitle("Hauptmenü");
    paint();
  }


  /**
   * Opens the Level.
   *
   * @param levelPage the GUILevel page of the level that should be opened.
   */
  public static void openLevel(GUILevelPage levelPage) {
    frame.setContentPane(levelPage.getPane());

    String title = "Level ";
    int levelNumber = levelPage.getLevelNumber();
    if (levelNumber >= 8) {
      title = "Backtracking-" + title + (levelNumber - 7);
    } else if (levelNumber >= 1) {
      title = "Greedy-" + title + levelNumber;
    } else if (levelNumber == 0) {
      title = "Einführungs-" + title;
    } else {
      title = "Benutzerdefiniertes ";
    }
    frame.setTitle(title);
    paint();
  }

  /**
   * Intended for Keyboard interactions with getInputMap and getActionMap.
   *
   * @return returns the RootPane of frame
   */
  public static JComponent getRootPane() {
    return frame.getRootPane();
  }

  /**
   * gets the GUILevelDeciderPage
   * @return the level decider page
   */
  public static GUILevelDeciderPage getGuiLevelDeciderPage() {
    return GUI_LEVEL_DECIDER_PAGE;
  }

}
