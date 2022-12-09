package GUI_Level;

import Rucksack.LevelManager;

import javax.swing.*;

public class GUIManager {
    private LevelManager lm = new LevelManager();
    public void launch(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Optimal Heist");
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}