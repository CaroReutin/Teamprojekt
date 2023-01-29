
package gui.level;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import solving.CustomLevelManager;

/**
 * The class GUIManager combines the gui pages.
 */
public class GuiManager {
  /**
   * the current gui frontpage.
   */
  private static final GuiFrontpage GUI_FRONTPAGE = new GuiFrontpage();
  /**
   * the current gui optionspage.
   */
  private static final GuiOptionsPage GUI_OPTIONS_PAGE = new GuiOptionsPage();

  /**
   * the current gui Level editor page.
   */
  private static final GuiLevelEditorPage GUI_LEVEL_EDITOR_PAGE
      = new GuiLevelEditorPage();
  /**
   * the current gui leveldeciderpage.
   */
  private static final GuiLevelDeciderPage GUI_LEVEL_DECIDER_PAGE
      = new GuiLevelDeciderPage();
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
    frame.setDropTarget(new DropTarget() {
      public synchronized void drop(final DropTargetDropEvent evt) {
        try {
          evt.acceptDrop(DnDConstants.ACTION_COPY);
          List<File> droppedFiles = (List<File>)
              evt.getTransferable().getTransferData(DataFlavor
                  .javaFileListFlavor);
          for (File file : droppedFiles) {
            CustomLevelManager.load(file);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
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
   * (Currently after exiting the Options menu the Main-menu will
   * always be opened, regardless where the options menu was opened from).
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
  public static void openLevel(final GuiLevelPage levelPage) {
    frame.setContentPane(levelPage.getPane());
    final int lastGreedyNumber = 7;
    String title = "Level ";
    int levelNumber = levelPage.getLevelNumber();
    if (levelNumber > lastGreedyNumber) {
      title = "Backtracking-" + title + (levelNumber - lastGreedyNumber);
    } else if (levelNumber >= 1) {
      title = "Greedy-" + title + levelNumber;
    } else if (levelNumber == 0) {
      title = "Einführungs-" + title;
    } else {
      title = "Benutzerdefiniertes " + title;
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
   * Gets the GUILevelDeciderPage.
   *
   * @return the level decider page
   */
  public static GuiLevelDeciderPage getGuiLevelDeciderPage() {
    return GUI_LEVEL_DECIDER_PAGE;
  }
}
