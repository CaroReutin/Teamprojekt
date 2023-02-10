package gui.level;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rucksack.Item;
import rucksack.Level;
import solving.UserDataManager;


/**
 * This class holds the gui pane of the levelpage.
 */
public class GuiLevelPage {
  /**
   * last backtracking level index.
   */
  public static final int LAST_BACKTRACKING_LEVELNUMBER = 14;
  /**
   * last greedy level index.
   */
  public static final int LAST_GREEDY_LEVELNUMBER = 7;
  /**
   * the level which is currently played.
   */
  private final Level level;
  /**
   * the labels of the page.
   */
  private JLabel[] labels;
  /**
   * the label of the rucksack.
   */
  private JLabel[] rucksackLabels;
  /**
   * the current value of the label.
   */
  private JLabel currentValueLabel;
  /**
   * the current weight of the label.
   */
  private JLabel currentWeightLabel;

  /**
   * Instantiates a new Gui level page.
   *
   * @param mylevel the level that can be played in this page
   */
  public GuiLevelPage(final Level mylevel) {
    if (mylevel.getRobber().equals(Level.Robber.BACKTRACKING_BANDIT)) {
      mylevel.getItemList().sort(
          Comparator.comparingInt(Item::getWeight)
              .thenComparingInt(Item::getValue).reversed());
    }
    this.level = mylevel;
  }

  /**
   * Adds the Button to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  public void escapeButton(final Container centerPanel) {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
    JButton flucht = new JButton("Flucht");
    flucht.setFont(font);
    int levelNumber = GuiManager.NumberLevel;
    if (levelNumber == -1) {
      flucht.addActionListener(e -> {
        String[] buttons = {"Erneut Spielen", "Levelauswahl"};
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            "Hier steht Tips / Feedback",
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            GuiManager.openMainMenu();
            System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else if (levelNumber == 7
        || levelNumber == 14 || levelNumber == 0) {
      flucht.addActionListener(e -> {
        if (levelNumber >= 0) {
          if (level.getCurrentValue() > UserDataManager.getScore(
            GuiManager.NumberLevel)) {
            UserDataManager.newHighScore(GuiManager.NumberLevel,
                level.getCurrentValue());
            UserDataManager.save();
          }
        }
        String[] buttons = {"Erneut Spielen", "Levelauswahl"};
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            "Hier steht Tips / Feedback",
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            GuiManager.openLevelSelectScreen();
            System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else {
      flucht.addActionListener(e -> {
        if (level.getCurrentValue() > UserDataManager
            .getScore(GuiManager.NumberLevel)) {
          UserDataManager.newHighScore(GuiManager.NumberLevel,
              level.getCurrentValue());
          UserDataManager.save();
        }
        String[] buttons = {"Erneut Spielen", "Nächstes Level", "Levelauswahl"};
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            "Hier steht Tips / Feedback",
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            GuiManager.openLevel(
                GuiManager.getGuiLevelDeciderPage().getGuiLevelPages()
                    [GuiManager.NumberLevel + 1], GuiManager.NumberLevel + 1);
            System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          }
          case 2 -> {
            GuiManager.openLevelSelectScreen();
            System.out.println("Es wurde auf " + buttons[2] + " geklickt.");
          }
          default -> {
          }
          //this case is not possible, all buttons are switched

        }
      });
    }
    centerPanel.add(flucht);
  }

  /**
   * Adds the Buttons representing the items in and out of the
   * Rucksack to the 2 panels.
   *
   * @param panelItems    The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   */
  public void itemButtons(final JPanel panelItems, final JPanel panelRucksack) {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
    Font bigFont = new Font("Arial", Font.BOLD + Font.ITALIC, 30);
    currentWeightLabel = new JLabel("0/" + level.getCapacity() + "g");
    currentWeightLabel.setFont(bigFont);

    currentValueLabel = new JLabel("0€");
    currentValueLabel.setFont(bigFont);

    ArrayList<Item> items = level.getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(level.getItemAmountList().get(i).toString());

      labels[i].setFont(font);

      rucksackLabels[i] = new JLabel("0");
      rucksackLabels[i].setFont(font);
      int finalI = i;
      JPanel itemPanel = new JPanel();
      ImageIcon imageIcon = items.get(i).getImageIcon();
      JButton itemIcon = new JButton(imageIcon);
      itemPanel.add(itemIcon);
      JLabel current = new JLabel(" (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
      current.setFont(font);
      itemPanel.add(current);
      itemIcon.addActionListener(e -> {
        if (level.getItemAmountAvailable(finalI) <= 0) {
          return;
        }
        if ((level.getCurrentWeight() + level.getItemList().get(finalI)
            .getWeight()) <= level.getCapacity()) {
          level.moveToRucksack(finalI);
          updateLabel(finalI);
        }
      });
      JButton currentRucksack = new JButton(imageIcon);
      currentRucksack.addActionListener(e -> {
        if (level.getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (!level.getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          level.moveFromRucksack(finalI);
          updateLabel(finalI);
        }
      });
      panelItems.add(itemPanel);
      panelItems.add(labels[i]);
      panelRucksack.add(currentRucksack, BorderLayout.CENTER);
      panelRucksack.add(rucksackLabels[i], BorderLayout.CENTER);
      panelRucksack.add(currentWeightLabel, BorderLayout.SOUTH);
      panelRucksack.add(currentValueLabel, BorderLayout.SOUTH);

    }
  }

  /**
   * update the Labels of item i.
   *
   * @param i the index of the labels to update
   */
  public void updateLabel(final int i) {
    labels[i].setText(String.valueOf(level.getItemAmountAvailable(i)));
    rucksackLabels[i].setText(String.valueOf(level.getItemAmountInRucksack(i)));
    currentWeightLabel.setText(level.getCurrentWeight()
        + "/" + level.getCapacity() + "g");
    currentValueLabel.setText((level.getCurrentValue() + "€"));
  }

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the level page
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, 3));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(
            300, 500, java.awt.Image.SCALE_SMOOTH);
    JPanel leftPanel = new JbackgroundWithItems(scaledRucksackImage, 0, 0);


    //füge Räuber ein
    URL urlRobber = getClass().getClassLoader().getResource("DiebRot.png");
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(100, 200,
      Image.SCALE_SMOOTH);

    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage, 120, 50);
    JPanel rightPanel = new JPanel();


    // erzeuge Buttons
    this.escapeButton(centerPanel);
    this.itemButtons(rightPanel, leftPanel);

    JPanel emptyPanel = new JPanel();

    //alles zusammenpuzzeln
    pane.add(leftPanel);
    pane.add(centerPanel);
    pane.add(rightPanel);


    return pane;
  }

  /**
   * returns the level of this GUI-Page.
   *
   * @return level
   */
  public Level getLevel() {
    return level;
  }


  /**
   * returns the level number.
   *
   * @return the level number.
   */
  /*public int getLevelNumber() {
    return this.level.getLevelNumber();
  }*/
}
