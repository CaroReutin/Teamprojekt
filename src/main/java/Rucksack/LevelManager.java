package Rucksack;

import GUI_Level.GUILevelDeciderPage;
import GUI_Level.GUIManager;
import Solving.Solver;

import javax.swing.*;
import java.util.ArrayList;

public class LevelManager {
    private Solver solver = new Solver();
    private ArrayList<Level> levels = new ArrayList<>();

    public Level getLevel(int levelNumber) {
        return levels.get(levelNumber);
    }

    /*
    restarts a level through resetting the item selection and the rucksack. After that the GUI is updated.
     */
    public static void resetLevel(Level level, JFrame frame) {
        level.resetLevel();
        GUILevelDeciderPage.restart(level, frame);
    }
}