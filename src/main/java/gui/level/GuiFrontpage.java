
package gui.level;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;


/**
 * This class confugures the gui frontpage.
 */
public class GuiFrontpage {
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the frontpage
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 50);
    Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Optimal Heist");
    titel.setBounds(360, 50, 400, 70);
    titel.setFont(fontStyle);
    pane.add(titel);

    JButton levelButton = new JButton("Level");
    levelButton.setBounds(380, 150, 300, 60);
    levelButton.setFont(fontButtons);
    levelButton.setBackground(Color.cyan);
    pane.add(levelButton);

    levelButton.addActionListener(e -> GuiManager.openLevelSelectScreen());


    JButton ownLevelButton = new JButton("Eigene Level");
    ownLevelButton.setBounds(380, 220, 300, 60);
    ownLevelButton.setBackground(Color.cyan);
    ownLevelButton.setFont(fontButtons);
    pane.add(ownLevelButton);

    ownLevelButton.addActionListener(e -> {
      GuiManager.openLevelEditor();
    });

    JButton settingsButton = new JButton("Einstellungen");
    settingsButton.setBounds(380, 300, 300, 60);
    settingsButton.setBackground(Color.cyan);
    settingsButton.setFont(fontButtons);
    pane.add(settingsButton);

    settingsButton.addActionListener(e -> GuiManager.openOptionsMenu());

    pane.revalidate();
    pane.repaint();

    return pane;
  }
}
