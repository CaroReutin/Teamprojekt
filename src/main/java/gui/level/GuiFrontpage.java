
package gui.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class confugures the gui frontpage.
 */
public class GuiFrontpage {
  /**
   * the constant NUM_ROWS_SUBPANE.
   */
  public static final int NUM_ROWS_SUBPANE = 7;

  /**
   * The constant SIZE_FONT_SMALL.
   */
  public static final int SIZE_FONT_SMALL = 30;
  /**
   * The constant SIZE_FONT_BIG.
   */
  public static final int SIZE_FONT_BIG = 50;

  /**
   * The constant HIGHT_RUCKSACK.
   */
  public static final int HIGHT_RUCKSACK = 300;
  /**
   * The constant WIDTH_RUCKSACK.
   */
  public static final int  WIDTH_RUCKSACK = 170;
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the frontpage
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new BorderLayout());
    Container subPane = new Container();
    subPane.setLayout(new GridLayout(NUM_ROWS_SUBPANE, 1));
    //Container subSubPane = new Container();
    //subSubPane.setLayout(new GridLayout(0,3));
    //füge Text und Buttons hinzu
    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, SIZE_FONT_BIG);
    Font fontButtons = new Font("Arial",
      Font.BOLD + Font.ITALIC, SIZE_FONT_SMALL);
    JLabel titel = new JLabel("Optimal Heist");
    titel.setFont(fontStyle);
    JButton levelButton = new JButton("Level");
    levelButton.setFont(fontButtons);
    levelButton.setBackground(Color.cyan);
    JButton ownLevelButton = new JButton("Eigene Level");
    ownLevelButton.setBackground(Color.cyan);
    ownLevelButton.setFont(fontButtons);
    JButton settingsButton = new JButton("Einstellungen");
    settingsButton.setBackground(Color.cyan);
    settingsButton.setFont(fontButtons);
    //add functions
    levelButton.addActionListener(e -> GuiManager.openLevelSelectScreen());
    ownLevelButton.addActionListener(e -> GuiManager.openLevelEditor());
    settingsButton.addActionListener(e -> GuiManager.openOptionsMenu());
    //füge Text und Buttons auf panels hinzu
    JPanel titlePanel = new JPanel();
    titlePanel.add(titel);
    JPanel levelPanel = new JPanel();
    levelPanel.add(levelButton);
    JPanel ownLevelPanel = new JPanel();
    ownLevelPanel.add(ownLevelButton);
    JPanel settingsPanel = new JPanel();
    settingsPanel.add(settingsButton);
    //rucksack image add
    //Füge Rucksack png ein und ändere Größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(
          WIDTH_RUCKSACK, HIGHT_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
    //add panels on subpane
    subPane.add(levelPanel);
    subPane.add(ownLevelPanel);
    subPane.add(settingsPanel);
    JPanel emptyPanel = new JPanel();
    subPane.add(emptyPanel);
    subPane.add(emptyPanel);
    subPane.add(emptyPanel);
    subPane.add(emptyPanel);
    //subSubPane.add(rucksackPanel);
    //puzzle panels and subpane zusammen
    JPanel rucksackPanel = new JbackgroundPanel(scaledRucksackImage);
    pane.add(titlePanel, BorderLayout.NORTH);
    pane.add(subPane, BorderLayout.CENTER);
    pane.add(rucksackPanel, BorderLayout.WEST);
    //pane.add(subSubPane, BorderLayout.PAGE_END);
    return pane;
  }
}
