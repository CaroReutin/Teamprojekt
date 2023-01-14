package gui.level;

import rucksack.*;
import solving.UserDataManager;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;


/**
 * This class holds the gui pane of the levelpage.
 */
public class GuiLevelPage {
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
   * @param level the level that can be played in this page
   */
  public GuiLevelPage(Level level) {
    this.level = level;
  }

  /**
   * Adds the Button to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  private void escapeButton(Container centerPanel) {
    JButton flucht = new JButton("Flucht");
    flucht.addActionListener(e -> {
      if (level.getCurrentValue() > UserDataManager.getScore(level.getLevelNumber())) {
        UserDataManager.newHighScore(level.getLevelNumber(), level.getCurrentValue());
        UserDataManager.save();
      }
      String[] buttons = {"Erneut Spielen", "Nächstes Level", "Levelauswahl"};
      int chosenButton = JOptionPane.showOptionDialog(centerPanel, "Hier steht Tips / Feedback",
          "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);
      switch (chosenButton) {
        case 0:
          level.resetLevel();
          for (int i = 0; i < level.getItemList().size(); i++) {
            updateLabel(i);
          }
          break;
        case 1:
          GuiManager.openLevel(GuiManager.getGuiLevelDeciderPage().getGuiLevelPages()[level.getLevelNumber() + 1]);
          System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          break;
        case 2:
          GuiManager.openLevelSelectScreen();
          System.out.println("Es wurde auf " + buttons[2] + " geklickt.");
          break;
        default:
          //this case is not possible, all buttons are switched

      }
    });
    centerPanel.add(flucht);
  }

  /**
   * Adds the Buttons representing the items in and out of the Rucksack to the 2 panels.
   *
   * @param panelItems The right panel where the buttons for the items NOT IN the bag should go to.
   *
   * @param panelRucksack The left panel where the buttons for the items IN the bag should go to.
   */
  private void itemButtons(JPanel panelItems, JPanel panelRucksack) {
    currentWeightLabel = new JLabel("0/" + level.getCapacity() + "g");
    Font fontCurrentWeightLabel = currentWeightLabel.getFont();
    currentWeightLabel.setFont(fontCurrentWeightLabel.deriveFont(fontCurrentWeightLabel.getStyle() | Font.BOLD));

    currentValueLabel = new JLabel("0€");
    Font fontCurrentValueLabel = currentValueLabel.getFont();
    currentValueLabel.setFont(fontCurrentValueLabel.deriveFont(fontCurrentValueLabel.getStyle() | Font.BOLD));

    ArrayList<Item> items = level.getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      int finalI = i;
      JButton current = new JButton(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
      labels[i] = new JLabel(level.getItemAmountList().get(i).toString());

      Font f = labels[i].getFont();
      labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

      JButton currentRucksack = new JButton(items.get(i).getName());
      rucksackLabels[i] = new JLabel("0");
      Font fontRucksack = rucksackLabels[i].getFont();
      rucksackLabels[i].setFont(fontRucksack.deriveFont(fontRucksack.getStyle() | Font.BOLD));
      current.addActionListener(e -> {
        if (level.getItemAmountAvailable(finalI) <= 0) {
          return;
        }
        if ((level.getCurrentWeight() + level.getItemList().get(finalI).getWeight()) <= level.getCapacity()) {
          level.moveToRucksack(finalI);
          updateLabel(finalI);
        }
      });
      currentRucksack.addActionListener(e -> {
        if (level.getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        level.moveFromRucksack(finalI);
        updateLabel(finalI);
      });
      panelItems.add(current);
      panelItems.add(labels[i]);
      panelRucksack.add(currentRucksack);
      panelRucksack.add(rucksackLabels[i]);
      panelRucksack.add(currentWeightLabel);
      panelRucksack.add(currentValueLabel);

    }
  }

  private void updateLabel(int i) {
    labels[i].setText(String.valueOf(level.getItemAmountAvailable(i)));
    rucksackLabels[i].setText(String.valueOf(level.getItemAmountInRucksack(i)));
    currentWeightLabel.setText(level.getCurrentWeight() + "/" + level.getCapacity() + "g");
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
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage = rucksackImage.getImage().getScaledInstance(170, 300, java.awt.Image.SCALE_SMOOTH);


    JPanel leftPanel = new JBackgroundPanel(scaledRucksackImage);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel rightPanel = new JPanel();
    //JPanel rightPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));

    // erzeuge Buttons
    this.escapeButton(centerPanel);
    this.itemButtons(rightPanel, leftPanel);


    //alles zusammenpuzzeln

    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);

    return pane;
  }
}