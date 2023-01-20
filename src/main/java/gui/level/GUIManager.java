

package gui.level;

import javax.swing.*;

/**
 * The class GUIManager combines the gui pages.
 */
public class GUIManager {
  /**
   * the current gui frontpage.
   */
  private static final GUIFrontpage guiFrontPage = new GUIFrontpage();
  /**
   * the current gui optionspage.
   */
  private static final GUIOptionsPage guiOptionsPage = new GUIOptionsPage();


  /**
   * the current gui leveldeciderpage.
   */
  private static GUILevelDeciderPage guiLevelDeciderPage = new GUILevelDeciderPage();
  /**
   * the frame of all windows.
   */
  private static JFrame frame;

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
    frame.setContentPane(guiOptionsPage.getPane());
    frame.setTitle("Optionsmenü");
    paint();
  }

  /**
   * Opens the LevelSelectionScreen.
   */
  public static void openLevelSelectScreen() {
    frame.setContentPane(guiLevelDeciderPage.getPane());
    frame.setTitle("Levelauswahl");
    paint();
  }

  /**
   * Opens the main Menu.
   */
  public static void openMainMenu() {
    frame.setContentPane(guiFrontPage.getPane());
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
   * @return the leveldeciderpage
   */
  public static GUILevelDeciderPage getGuiLevelDeciderPage() {
    return guiLevelDeciderPage;
  }

}
