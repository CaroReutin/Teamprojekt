

package GUI_Level;

import Rucksack.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIManager {
    private LevelManager lm = new LevelManager();

    static GUIFrontpage guiFrontPage = new GUIFrontpage();
    GUIOptionsPage guiOptionsPage = new GUIOptionsPage();
    static GUILevelDeciderPage guiLevelDeciderPage = new GUILevelDeciderPage();

    public void launch(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Optimal Heist");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        guiFrontPage.getFrontPage(frame);
    }

    public static void rePaintFrame(Container pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }

    public static GUIFrontpage getGuiFrontPage() {
        return guiFrontPage;
    }

    public GUIOptionsPage getGuiOptionsPage() {
        return guiOptionsPage;
    }


    public static GUILevelDeciderPage getGuiLevelDeciderPage() {
        return guiLevelDeciderPage;
    }

}
