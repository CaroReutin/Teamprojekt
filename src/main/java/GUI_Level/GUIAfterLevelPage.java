package GUI_Level;

import javax.swing.*;
import java.awt.*;

public class GUIAfterLevelPage {
    public void getAfterLevelPage(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Level beendet");
        titel.setBounds(150, 40, 300, 40);
        titel.setFont(fontStyle);
        pane.add(titel);

        JButton resetButton = new JButton("Level wiederholen");
        resetButton.setBounds(190,100,120,40);
        resetButton.setBackground(Color.cyan);
        pane.add(resetButton);

        resetButton.addActionListener(e -> {

        });

        JButton nextLevelButton = new JButton("nächstes Level");
        nextLevelButton.setBounds(300,100,120,40);
        nextLevelButton.setBackground(Color.cyan);
        pane.add(nextLevelButton);

        nextLevelButton.addActionListener(e -> {

        });

    }

}
