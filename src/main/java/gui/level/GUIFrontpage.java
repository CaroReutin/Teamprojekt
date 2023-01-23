package gui.level;

import java.awt.*;
import javax.swing.*;


/**
 * This class confugures the gui frontpage.
 */
public class GUIFrontpage {
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the frontpage
   */
  public Container getPane() {
    Container pane = new Container();
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

    levelButton.addActionListener(e -> GUIManager.openLevelSelectScreen());


    JButton ownLevelButton = new JButton("Eigene Level");
    ownLevelButton.setBounds(190, 180, 120, 40);
    ownLevelButton.setBackground(Color.cyan);
    pane.add(ownLevelButton);

    ownLevelButton.addActionListener(e -> {
      GUIManager.openLevelEditor();
    });

    JButton settingsButton = new JButton("Einstellungen");
    settingsButton.setBounds(190, 260, 120, 40);
    settingsButton.setBackground(Color.cyan);
    pane.add(settingsButton);

    settingsButton.addActionListener(e -> GUIManager.openOptionsMenu());

    pane.revalidate();
    pane.repaint();

    return pane;
  }
}
