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
import solving.ButtonEventHandlerJtree;
import solving.ButtonEventHandlerTable;
import solving.SolverBacktracking;
import solving.SolverGreedy;
import solving.UserDataManager;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {
  /**
   * the size of the font.
   */
  private static final int FONT_FIFTEEN = 15;
  /**
   * the size of the font.
   */
  private static final int FONT_TWENTY = 20;
  /**
   * the size of the font.
   */
  private static final int FONT_TWENTY_FIVE = 25;
  /**
   * the cols of the grid.
   */
  private static final int GRID_FOUR = 4;
  /**
   * the cols of the grid.
   */
  private static final int GRID_FIVE = 5;
  /**
   * the cols of the grid.
   */
  private static final int GRID_THREE = 3;
  /**
   * the width of the rucksack image.
   */
  private static final int WIDTH_RUCKSACK = 50;
  /**
   * the height of the rucksack image.
   */
  private static final int HEIGHT_RUCKSACK = 100;
  /**
   * the width of the robber image.
   */
  private static final int WIDTH_ROBBER = 100;
  /**
   * the height of the robber image.
   */
  private static final int HEIGHT_ROBBER = 200;
  /**
   * the x position of the robber on the screen.
   */
  private static final int X_POS_ROBBER = 120;
  /**
   * the y position of the robber on the screen.
   */
  private static final int Y_POS_ROBBER = 100;
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
    if (GuiOptionsPage.getAltTreeSelected()) {
      buttonHandler = new ButtonEventHandlerJtree(getLevel());
    } else {
      buttonHandler = new ButtonEventHandlerTable(getLevel());
    }
  }

  /**
   * makes the Item Buttons.
   *
   * @param panelAvailable The right panel where the buttons for
   *                       the items NOT IN the bag should go to.
   * @param panelRucksack  The left panel where the buttons for
   *                       the items IN the bag should go to.
   * @param panelTrash     The trash panel.
   * @param controlPanel   The control panel.
   */

  public void itemButtons(final JPanel panelAvailable,
                          final JPanel panelRucksack,
                          final JPanel panelTrash,
                          final JPanel controlPanel) {
    Font smallFont = new Font("Arial", Font.BOLD
        + Font.ITALIC, FONT_FIFTEEN);
    Font mediumFont = new Font("Arial", Font.BOLD
        + Font.ITALIC, FONT_TWENTY_FIVE);
    Font infoFont = new Font("Arial", Font.BOLD
        + Font.ITALIC, FONT_TWENTY);
    currentWeightLabel = new JLabel("0/" + getLevel().getCapacity() + "g");
    currentWeightLabel.setFont(infoFont);

    currentValueLabel = new JLabel("0€");
    currentValueLabel.setFont(infoFont);

    ArrayList<BacktrackingItem> items = getLevel().getBacktrackingItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    trashLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());
      labels[i].setFont(smallFont);

      rucksackLabels[i] = new JLabel("0");
      rucksackLabels[i].setFont(smallFont);


      JLabel itemLabel = new JLabel(" (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€), ");
      itemLabel.setFont(smallFont);

      itemLabel.setBackground(Color.white);

      //buttons rucksack
      JButton putToRucksack = new JButton("mitnehmen");
      putToRucksack.setBackground(Color.lightGray);
      putToRucksack.setFont(smallFont);
      int finalI = i;
      putToRucksack.addActionListener(e -> handleButtons(finalI, true));

      //Trash Buttons and Labels
      trashLabels[i] = new JLabel(getLevel().getInTrashAmountList()
        .get(i).toString());

      trashLabels[i].setFont(smallFont);

      //buttons trash
      JButton putToTrash = new JButton("wegwerfen");
      putToTrash.setBackground(Color.lightGray);
      putToTrash.setFont(smallFont);
      putToTrash.addActionListener(e -> handleButtons(finalI, false));

      JPanel availableTempPanel = new JPanel(new GridLayout(1, GRID_FOUR));
      ImageIcon imageIcon = items.get(i).getImageIcon();
      JLabel itemIcon = new JLabel(imageIcon);
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

      JPanel tempControlPanel = new JPanel(new GridLayout(2, GRID_FIVE));

      tempControlPanel.add(itemControlLabelIcon);
      tempControlPanel.add(itemControlLabel);
      tempControlPanel.add(putToRucksack);
      tempControlPanel.add(putToTrash);
      controlPanel.add(tempControlPanel);

      JLabel currentTrash = new JLabel(imageIcon);
      JPanel trashPanel = new JPanel(new GridLayout(2, 1));
      trashPanel.add(currentTrash);
      trashPanel.add(trashLabels[i]);
      panelTrash.add(trashPanel);

      JLabel currentRucksack = new JLabel(imageIcon);
      JPanel rucksackPanel = new JPanel(new GridLayout(2, 1));
      rucksackPanel.add(currentRucksack);
      rucksackPanel.add(rucksackLabels[i]);

      JPanel infosPanel = new JPanel();
      infosPanel.add(currentWeightLabel);
      infosPanel.add(currentValueLabel);

      panelRucksack.add(rucksackPanel);
      panelRucksack.add(infosPanel);

    }
  }

  /**
   * returns the pane for the backtracking Gui level page.
   *
   * @return the pane
   */
  @Override
  public Container getPane() {
    Font mediumFont = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_TWENTY);
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, GRID_THREE));
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    // Panel were available items are
    JPanel avaiblePanel = new JPanel(new GridLayout(
        getLevel().getItemList().size() + 1, 2));
    JLabel available = new JLabel("Verfügbar:");
    available.setFont(mediumFont);
    avaiblePanel.add(available);
    // Panel with buttons were you can add items to rucksack or trash
    JPanel controlPanel = new JPanel();
    JLabel control = new JLabel("Kontrollfläche: ");
    control.setFont(mediumFont);
    controlPanel.add(control);
    JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    rightPanel.add(avaiblePanel, BorderLayout.NORTH);
    rightPanel.add(controlPanel, BorderLayout.SOUTH);

    // Rucksack Panel
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(WIDTH_RUCKSACK,
          HEIGHT_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
    ImageIcon rucksackIcon = new ImageIcon(scaledRucksackImage);
    JLabel rucksackIconLabel = new JLabel(rucksackIcon);

    JPanel rucksackPanel = new JPanel(new GridLayout(2, 1));
    rucksackPanel.add(rucksackIconLabel);
    //add robber
    URL urlRobber = getClass().getClassLoader().getResource(
        "DiebGrauMitSack.png");
    assert urlRobber != null;
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(
        WIDTH_ROBBER, HEIGHT_ROBBER, Image.SCALE_SMOOTH);

    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage, X_POS_ROBBER,
        Y_POS_ROBBER);

    //Trash Panel
    URL urlTrash = getClass().getClassLoader().getResource("Müll.png");
    assert urlTrash != null;
    ImageIcon trashImage = new ImageIcon(urlTrash);
    Image scaledTrashImage = trashImage.getImage().getScaledInstance(
        WIDTH_RUCKSACK, HEIGHT_RUCKSACK,
        Image.SCALE_SMOOTH);
    ImageIcon trashIcon = new ImageIcon(scaledTrashImage);
    JLabel trashIconLabel = new JLabel(trashIcon);
    JPanel trashPanel = new JPanel(new GridLayout(2, 1));
    trashPanel.add(trashIconLabel);

    //Panel where escape-Button and clue-Button are
    //Panel where escape-Button is
    this.escapeButton(centerPanel);
    if (GuiOptionsPage.getBacktrackingTipsAllowed()) {
      this.clueButton(centerPanel);
    }

    JPanel rucksackPanelItems = new JPanel();
    JPanel trashPanelItems = new JPanel();
    this.itemButtons(avaiblePanel, rucksackPanel, trashPanel, controlPanel);
    rucksackPanel.add(rucksackPanelItems);
    trashPanel.add(trashPanelItems);

    //alles zusammenpuzzeln
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    leftPanel.add(rucksackPanel);
    leftPanel.add(trashPanel);

    JButton treeButton = new JButton("Zeige Baum");
    treeButton.setBackground(Color.lightGray);
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
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_TWENTY);
    JButton flucht = new JButton("Flucht");
    flucht.setBackground(Color.lightGray);
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
        if (getLevel().getCurrentValue() > UserDataManager.getScore(
            GuiManager.numberLevel)) {
          UserDataManager.newHighScore(GuiManager.numberLevel,
              getLevel().getCurrentValue());
          UserDataManager.save();
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
            buttonHandler.resetLevel(getLevel(), levelNumber);
            for (int i = 0; i < getLevel().getItemList().size(); i++) {
              updateLabel(i);
            }
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
   *
   * @return String of the escape message
   */
  @Override
  public String generateEscapeMessage() {
    SolverBacktracking s = new SolverBacktracking();
    String solutionString = this.buttonHandler.getSolution();
    if (solutionString == null) {
      return "Drücke vor beenden des Levels das Blatt im Baum, welches"
        + "der richtigen Lösung entspricht.";
    }
    ArrayList<Item> solution = new ArrayList<>();
    for (int i = 0; i < getLevel().getBacktrackingItemList().size(); i++) {
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