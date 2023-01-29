
package gui.level;

import java.awt.*;
import java.net.URL;
import javax.swing.*;


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
    pane.setLayout(new BorderLayout());

    Container subPane = new Container();
    subPane.setLayout(new GridLayout(7, 1));

    //Container subSubPane = new Container();
    //subSubPane.setLayout(new GridLayout(0,3));

    //erzeuge Panels
   JPanel titlePanel = new JPanel();
   JPanel levelPanel = new JPanel();
   JPanel ownLevelPanel = new JPanel();
   JPanel settingsPanel = new JPanel();
   JPanel emptyPanel = new JPanel();

   //füge Text und Buttons hinzu
   Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 50);
   Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

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
   titlePanel.add(titel);
   levelPanel.add(levelButton);
   ownLevelPanel.add(ownLevelButton);
   settingsPanel.add(settingsButton);

   //rucksack image add
   //Füge Rucksack png ein und ändere Größe
   URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
   assert url != null;
   ImageIcon rucksackImage = new ImageIcon(url);
   Image scaledRucksackImage =
     rucksackImage.getImage().getScaledInstance(
       170, 300, java.awt.Image.SCALE_SMOOTH);


   JPanel rucksackPanel = new JbackgroundPanel(scaledRucksackImage);

   //add panels on subpane
   subPane.add(levelPanel);
   subPane.add(ownLevelPanel);
   subPane.add(settingsPanel);
   subPane.add(emptyPanel);
   subPane.add(emptyPanel);
   subPane.add(emptyPanel);
   subPane.add(emptyPanel);


   //subSubPane.add(rucksackPanel);

   //puzzle panels and subpane zusammen
   pane.add(titlePanel, BorderLayout.NORTH);
   pane.add(subPane, BorderLayout.CENTER);
   pane.add(rucksackPanel, BorderLayout.WEST);
   //pane.add(subSubPane, BorderLayout.PAGE_END);

   return pane;
  }
}
