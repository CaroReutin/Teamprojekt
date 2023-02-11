package gui.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rucksack.BacktrackingItem;
import rucksack.Item;
import rucksack.Level;
import solving.ButtonEventHandler;
import solving.ButtonEventHandlerTable;
import solving.SolverBacktracking;
import solving.SolverGreedy;
import solving.UserDataManager;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {
  /**
   * the Button Event Handler.
   */
  private final ButtonEventHandler buttonHandler;
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
   * number of items in Label.
   */
  private JLabel[] trashLabels;
  /**
   * the current weight of the label.
   */
  private JLabel currentWeightLabel;

  /**
   * make a new backtracking level page.
   *
   * @param level the level
   */
  public GuiLevelPageBacktracking(final Level level) {
    super(level);
    getLevel().turnIntoBacktracking();
    buttonHandler = new ButtonEventHandlerTable(getLevel());
  }

  /**
   * makes the Item Buttons.
   *
   * @param panelAvailable  The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   * @param panelTrash   The trash panel.
   * @param controlPanel The control panel.
   */

  public void itemButtons(final JPanel panelAvailable, final JPanel panelRucksack,
                          final JPanel panelTrash,
                          final JPanel controlPanel) {
    Font smallFont = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
    Font mediumFont = new Font("Arial", Font.BOLD + Font.ITALIC, 25);
    Font bigFont = new Font("Arial", Font.BOLD + Font.ITALIC, 30);
    currentWeightLabel = new JLabel("0/" + getLevel().getCapacity() + "g");
    currentWeightLabel.setFont(bigFont);

    currentValueLabel = new JLabel("0€");
    currentValueLabel.setFont(bigFont);

    ArrayList<BacktrackingItem> items = getLevel().getBacktrackingItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    trashLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());
      labels[i].setFont(smallFont);

      rucksackLabels[i] = new JLabel("0");
      rucksackLabels[i].setFont(smallFont);

      // Laufvariable
      int finalI = i;

      //Available Labels
      ImageIcon imageIcon = items.get(i).getImageIcon();
      JLabel itemIcon = new JLabel(imageIcon);

      JLabel itemLabel = new JLabel(" (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€), ");
      itemLabel.setFont(smallFont);

      itemLabel.setBackground(Color.white);

      //buttons rucksack
      JButton putToRucksack = new JButton("mitnehmen");
      putToRucksack.setFont(smallFont);
      putToRucksack.addActionListener(e -> handleButtons(finalI, true));

      //Trash Buttons and Labels
      trashLabels[i] = new JLabel(getLevel().getInTrashAmountList()
          .get(i).toString());

      trashLabels[i].setFont(mediumFont);

      //buttons trash
      JButton putToTrash = new JButton("wegwerfen");
      putToTrash.setFont(smallFont);
      putToTrash.addActionListener(e -> handleButtons(finalI, false));
      JLabel currentRucksack = new JLabel(imageIcon);

      JPanel availableTempPanel = new JPanel(new GridLayout(1, 4));
      availableTempPanel.add(itemIcon);
      availableTempPanel.add(itemLabel);
      availableTempPanel.add(labels[i]);
      panelAvailable.add(availableTempPanel);
      JLabel itemControlLabelIcon = new JLabel(imageIcon);
      JLabel itemControlLabel =
          new JLabel(" ("
              + items.get(i).getWeight() + "g, "
              + items.get(i).getValue() + "€)");
      itemControlLabel.setFont(smallFont);

      JPanel tempControlPanel = new JPanel(new GridLayout(2, 5));

      tempControlPanel.add(itemControlLabelIcon);
      tempControlPanel.add(itemControlLabel);
      tempControlPanel.add(putToRucksack);
      tempControlPanel.add(putToTrash);
      controlPanel.add(tempControlPanel);

      JLabel currentTrash = new JLabel(imageIcon);
      panelTrash.add(currentTrash);
      panelTrash.add(trashLabels[i]);

      panelRucksack.add(currentRucksack);
      panelRucksack.add(rucksackLabels[i]);
      panelRucksack.add(currentWeightLabel);
      panelRucksack.add(currentValueLabel);

    }
  }

  /**
   * returns the pane for the backtracking Gui level page.
   *
   * @return the pane
   */
  @Override
  public Container getPane() {
    Font mediumFont = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, 3));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    // Panel were available items are
    JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    JPanel avaiblePanel = new JPanel(new GridLayout(
        getLevel().getItemList().size() + 1, 2));
    JLabel available = new JLabel("Verfügbar:");
    available.setFont(mediumFont);
    avaiblePanel.add(available);
    // Panel with buttons were you can add items to rucksack or trash
    JPanel controlPannel = new JPanel();
    JLabel control = new JLabel("Kontrollfläche: ");
    control.setFont(mediumFont);
    controlPannel.add(control);
    rightPanel.add(avaiblePanel, BorderLayout.NORTH);
    rightPanel.add(controlPannel, BorderLayout.SOUTH);

    // Rucksack Panel
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(200, 350,
            java.awt.Image.SCALE_SMOOTH);
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    JPanel rucksackPanel = new JbackgroundPanel(scaledRucksackImage, 0, 0);
    JLabel rucksack = new JLabel("Rucksack:");
    rucksack.setFont(mediumFont);
    rucksackPanel.add(rucksack);

    //füge Räuber ein
    URL urlRobber = getClass().getClassLoader().getResource("DiebGrauMitSack.png");
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(100, 200,
      Image.SCALE_SMOOTH);

    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage, 120, 100);

    //Trash Panel
    URL urlTrash = getClass().getClassLoader().getResource("Müll.png");
    ImageIcon trashImage = new ImageIcon(urlTrash);
    Image scaledTrashImage = trashImage.getImage().getScaledInstance(200, 350,
      Image.SCALE_SMOOTH);
    JPanel trashPanel = new JbackgroundPanel(scaledTrashImage, 20, 0);

    JLabel trash = new JLabel("Müll:");
    trash.setFont(mediumFont);
    trashPanel.add(trash);
    leftPanel.add(rucksackPanel, BorderLayout.NORTH);
    leftPanel.add(trashPanel, BorderLayout.SOUTH);

    //Panel where escape-Button and clue-Button are
    //JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //Panel where escape-Button is
    this.escapeButton(centerPanel);
    if (GuiOptionsPage.backtrackingTipsAllowed) {
      this.clueButton(centerPanel);
    }
    this.itemButtons(avaiblePanel, rucksackPanel, trashPanel, controlPannel);


    //alles zusammenpuzzeln
    JButton treeButton = new JButton("Zeige Baum");
    treeButton.setFont(mediumFont);
    treeButton.addActionListener(e -> buttonHandler.show());
    centerPanel.add(treeButton);
    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);

    return pane;

  }

  /**
   * update labels.
   */
  @Override
  public void updateLabel(final int i) {
    labels[i].setText(String.valueOf(getLevel().getItemAmountAvailable(i)));
    trashLabels[i].setText(String.valueOf(getLevel()
        .getInTrashAmountList().get(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel()
        .getItemAmountInRucksack(i)));
    currentWeightLabel.setText(
        getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }

  private void handleButtons(final int index,
                             final boolean toRucksack) {
    if (toRucksack) {
      buttonHandler.addToRucksack(index, getLevel());
      updateLabel(index);
    } else {
      buttonHandler.addToTrash(index, getLevel());
      for (int i = index; i < getLevel()
          .getBacktrackingItemList().size(); i++) {
        updateLabel(i);
      }
    }
  }

  /**
   * Adds the escapeButton to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  @Override
  public void escapeButton(final Container centerPanel) {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
    JButton flucht = new JButton("Flucht");
    flucht.setFont(font);
    int levelNumber = GuiManager.numberLevel;
    if (levelNumber == -1) {
      flucht.addActionListener(e -> {
        String[] buttons = {"Erneut Spielen", "Levelauswahl"};
        String message = generateEscapeMessage();
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            message,
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
            GuiManager.openMainMenu();
            System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else if (levelNumber == LAST_GREEDY_LEVELNUMBER
        || levelNumber == LAST_BACKTRACKING_LEVELNUMBER || levelNumber == 0) {
      flucht.addActionListener(e -> {
        if (levelNumber >= 0) {
          if (getLevel().getCurrentValue() > UserDataManager.getScore(
              GuiManager.numberLevel)) {
            UserDataManager.newHighScore(GuiManager.numberLevel,
                getLevel().getCurrentValue());
            UserDataManager.save();
          }
        }
        String[] buttons = {"Erneut Spielen", "Levelauswahl"};
        String message = null;
        message = generateEscapeMessage();
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            message,
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
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
        if (getLevel().getCurrentValue() > UserDataManager
            .getScore(GuiManager.numberLevel)) {
          UserDataManager.newHighScore(GuiManager.numberLevel,
              getLevel().getCurrentValue());
          UserDataManager.save();
        }
        String[] buttons = {"Erneut Spielen", "Nächstes Level", "Levelauswahl"};
        String message = null;
        message = generateEscapeMessage();
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
            message,
            "Geflohen", JOptionPane.DEFAULT_OPTION, JOptionPane
                .INFORMATION_MESSAGE, null, buttons,
            buttons[0]);
        switch (chosenButton) {
          case 0 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
            GuiManager.openLevel(
                GuiManager.getGuiLevelDeciderPage().getGuiLevelPages()
                    [GuiManager.numberLevel + 1], GuiManager.numberLevel + 1);
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
   * Method for generating the message when clicking the escape button.
   * @return String of the escape message
   */
  @Override
  public String generateEscapeMessage() {
    SolverBacktracking s = new SolverBacktracking();
    String solutionString = this.buttonHandler.getSolution();
    if (solutionString == null) {
      return "Drücke vor beenden des Levels den Knopf im Baum der,"
              + "der richtigen Lösung entspricht.";
    }
    ArrayList<Item> solution = new ArrayList<>();
    for (int i = 0; i < getLevel().getBacktrackingItemList().size(); i++) {
      System.out.println(solutionString);
      if (solutionString.charAt(i) == '0') {
        solution.add(getLevel().getItemList().get(i));
      }
    }
    String res = "";
    SolverGreedy.sortLikeGreedy(solution);
    if (this.getLevel().getRobber().equals(Level.Robber.BACKTRACKING_BANDIT)) {
      ArrayList<Item> rightSolution = s.solveAlgorithm(this.getLevel());
      if (solution.equals(SolverGreedy.sortLikeGreedy(rightSolution))) {
        res += "Es wurde die bestmögliche Lösung gefunden.";
      } else {
        res += "Das geht noch besser.";
      }
      boolean hasExploredEverything = true;
      for (int i = 0; i < getLevel().getBacktrackingItemList().size(); i++) {
        if (getLevel().getItemAmountInRucksack(i) != 0
            || getLevel().getItemAmountAvailable(i) != 0) {
          hasExploredEverything = false;
        }
      }
      if (!hasExploredEverything) {
        res += "\nAber es gibt noch mehr mögliche Kombinationen.";
      }
      return res;
    } else {
      return "Das solltest du nicht sehen können, es lief etwas schief.";
    }
  }
}
