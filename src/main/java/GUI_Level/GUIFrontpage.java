package GUI_Level;

import javax.swing.*;
import java.awt.*;

public class GUIFrontpage {
    GUIManager guiManager;

    public void getFrontPage(JFrame frame) {
        guiManager = new GUIManager();
        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Optimal Heist");
        titel.setBounds(150, 40, 300, 40);
        titel.setFont(fontStyle);
        pane.add(titel);

        JButton levelButton = new JButton("Level");
        levelButton.setBounds(190, 100, 120, 40);
        levelButton.setBackground(Color.cyan);
        pane.add(levelButton);

        levelButton.addActionListener(e -> {
            guiManager.rePaintFrame(pane);
            guiManager.getGuiLevelDeciderPage().openLevelDeciderPage(frame, guiManager);
        });


        JButton ownLevelButton = new JButton("Eigene Level");
        ownLevelButton.setBounds(190, 180, 120, 40);
        ownLevelButton.setBackground(Color.cyan);
        pane.add(ownLevelButton);

        ownLevelButton.addActionListener(e -> {
            guiManager.rePaintFrame(pane);
        });

        JButton settingsButton = new JButton("Einstellungen");
        settingsButton.setBounds(190, 260, 120, 40);
        settingsButton.setBackground(Color.cyan);
        pane.add(settingsButton);

        settingsButton.addActionListener(e -> {
            guiManager.rePaintFrame(pane);
            guiManager.getGuiOptionsPage().openOptionsPage(frame, guiManager);
        });
    }

}
