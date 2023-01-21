
package gui.level;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;


/**
 * This class confugures the gui frontpage.
 */
public class GUIFrontpage {

  /**
   * Size of the font.
   */
  private final int fontSize = 30;
  /**
   * X-coordinate position of the title.
   */
  private final int titleXPosition = 150;
  /**
   * Y-coordinate position of the title.
   */
  private final int titleYPosition = 40;
  /**
   * X-coordinate position of the JLabelButtons.
   */
  private final int labelXPosition = 190;
  /**
   * Y-coordinate position of the JLabelButton "Level".
   */
  private final int levelLabelYPosition = 100;
  /**
   * Y-coordinate position of the JLabelButton "Eigene Level".
   */
  private final int levelEditorLabelYPosition = 180;
  /**
   * Y-coordinate position of the JLabelButton "Einstellungen".
   */
  private final int optionsLabelYPosition = 260;
  /**
   * Width of a greater JLabel.
   */
  private final int bigLabelWidth = 300;
  /**
   * Width of a smaller JLabel.
   */
  private final int smallLabelWidth = 120;
  /**
   * Height of a JLabel.
   */
  private final int labelHeight = 40;
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the frontpage
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, fontSize);

    JLabel titel = new JLabel("Optimal Heist");
    titel.setBounds(titleXPosition, titleYPosition, bigLabelWidth, labelHeight);
    titel.setFont(fontStyle);
    pane.add(titel);

    JButton levelButton = new JButton("Level");
    levelButton.setBounds(labelXPosition, levelLabelYPosition,
            smallLabelWidth, labelHeight);
    levelButton.setBackground(Color.cyan);
    pane.add(levelButton);

    levelButton.addActionListener(e -> GUIManager.openLevelSelectScreen());


    JButton ownLevelButton = new JButton("Eigene Level");
    ownLevelButton.setBounds(labelXPosition, levelEditorLabelYPosition,
            smallLabelWidth, labelHeight);
    ownLevelButton.setBackground(Color.cyan);
    pane.add(ownLevelButton);

    ownLevelButton.addActionListener(e -> {
      GUIManager.openLevelEditor();
    });

    JButton settingsButton = new JButton("Einstellungen");
    settingsButton.setBounds(labelXPosition, optionsLabelYPosition,
            smallLabelWidth, labelHeight);
    settingsButton.setBackground(Color.cyan);
    pane.add(settingsButton);

    settingsButton.addActionListener(e -> GUIManager.openOptionsMenu());

    pane.revalidate();
    pane.repaint();

    return pane;
  }
}
