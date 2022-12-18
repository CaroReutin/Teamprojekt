

package gui.level;

import javax.swing.*;

public class GUIManager {
    private static final GUIFrontpage guiFrontPage = new GUIFrontpage();
    private static final GUIOptionsPage guiOptionsPage = new GUIOptionsPage();
    private static final GUILevelDeciderPage guiLevelDeciderPage = new GUILevelDeciderPage();
    private static JFrame frame;

    /**
     * Opens the main Menu
     */
    public void launch(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Optimal Heist");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        openMainMenu();
    }

    /**
     * Must be called after every pane change
     */
    private static void paint() {
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Opens the Options Menu
     * (Currently after exiting the Options menu the Main-menu will always be opened
     * regardless where the options menu was opened from)
     */
    public static void openOptionsMenu() {
        frame.setContentPane(guiOptionsPage.getPane());
        paint();
    }

    /**
     * Opens the LevelSelectionScreen
     */
    public static void openLevelSelectScreen() {
        frame.setContentPane(guiLevelDeciderPage.getPane());
        paint();
    }

    /**
     * Opens the main Menu
     */
    public static void openMainMenu(){
        frame.setContentPane(guiFrontPage.getPane());
        paint();
    }


    /**
     * Opens the Level
     * @param levelPage the GUILevel page of the level that should be opened
     */
    public static void openLevel(GUILevelPage levelPage) {
        frame.setContentPane(levelPage.getPane());
        paint();
    }

    /**
     * Intended for Keyboard interactions with getInputMap and getActionMap
     * @return returns the RootPane of frame
     */
    public static JComponent getRootPane() {
        return frame.getRootPane();
    }
}
