package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import solving.SolverBacktracking;
import solving.SolverGreedy;
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
   * the size 20 font.
   */
  private static final int FONT_TWENTY = 20;
  /**
   * the size 15 font.
   */
  private static final int FONT_FIFTEEN = 15;
  /**
   * the size 30 font.
   */
  private static final int FONT_THIRTY = 30;
  /**
   * the number of grid rows.
   */
  private static final int GRID_FOUR = 4;
  /**
   * the number of grid rows.
   */
  private static final int GRID_THREE = 3;
  /**
   * the width of the rucksack image.
   */
  private static final int WIDTH_RUCKSACK = 300;
  /**
   * the height of the rucksack image.
   */
  private static final int HEIGHT_RUCKSACK = 500;
  /**
   * the x position of the robber on the screen.
   */
  private static final int X_POS_ROBBER = 120;
  /**
   * the y position of the robber on the screen.
   */
  private static final int Y_POS_ROBBER = 50;
  /**
   * the width of the robber image.
   */
  private static final int WIDTH_ROBBER = 100;
  /**
   * the height of the robber image.
   */
  private static final int HEIGHT_ROBBER = 200;
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
   * Adds the escapeButton to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  public void escapeButton(final Container centerPanel) {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_TWENTY);
    JButton flucht = new JButton("Flucht");
    flucht.setFont(font);
    int levelNumber = GuiManager.getNumberLevel();
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
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
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
        if (level.getCurrentValue() > UserDataManager.getScore(
                GuiManager.getNumberLevel())) {
          UserDataManager.newHighScore(GuiManager.getNumberLevel(),
              level.getCurrentValue());
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
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
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
        if (level.getCurrentValue() > UserDataManager
            .getScore(GuiManager.getNumberLevel())) {
          UserDataManager.newHighScore(GuiManager.getNumberLevel(),
              level.getCurrentValue());
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
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
          }
          case 1 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
              updateLabel(i);
            }
            GuiManager.openLevel(
                GuiManager.getGuiLevelDeciderPage().getGuiLevelPages()
                    [GuiManager.getNumberLevel() + 1],
                    GuiManager.getNumberLevel() + 1);
            System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
          }
          case 2 -> {
            level.resetLevel();
            for (int i = 0; i < level.getItemList().size(); i++) {
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
   * Adds the clueButton to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  public void clueButton(final Container centerPanel) {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_TWENTY);
    JButton clue = new JButton("Hinweis");
    clue.setFont(font);
    int levelNumber = GuiManager.getNumberLevel();
    if (levelNumber == -1) {
      clue.addActionListener(e -> {
        String[] buttons = {"Schließen"};
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/levelTexts/X1.txt");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
                message,
                "Hinweis", JOptionPane.DEFAULT_OPTION, JOptionPane
                        .INFORMATION_MESSAGE, null, buttons,
                buttons[0]);
        //this case is not possible, all buttons are switched
        if (chosenButton == 0) { //nothing is supposed to happen
        }
      });
    } else if (levelNumber == 0) {
      clue.addActionListener(e -> {
        String[] buttons = {"Schließen"};
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/levelTexts/E1.txt");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
                message,
                "Hinweis", JOptionPane.DEFAULT_OPTION, JOptionPane
                        .INFORMATION_MESSAGE, null, buttons,
                buttons[0]);
        //this case is not possible, all buttons are switched
        if (chosenButton == 0) { //nothing is supposed to happen
        }
      });
    } else if (0 < levelNumber && levelNumber <= LAST_GREEDY_LEVELNUMBER) {
      clue.addActionListener(e -> {
        String[] buttons = {"Schließen"};
        String message = null;
        try {
          int fileNumber = levelNumber + 1;
          message = fileToString(
                  "src/main/resources/texts/levelTexts/G"
                          + fileNumber + ".txt");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
                message,
                "Hinweis", JOptionPane.DEFAULT_OPTION, JOptionPane
                        .INFORMATION_MESSAGE, null, buttons,
                buttons[0]);
        //this case is not possible, all buttons are switched
        if (chosenButton == 0) { //nothing is supposed to happen
        }
      });
    } else if (0 < LAST_GREEDY_LEVELNUMBER
            & levelNumber <= LAST_BACKTRACKING_LEVELNUMBER) {
      clue.addActionListener(e -> {
        String[] buttons = {"Schließen"};
        String message = null;
        try {
          int fileNumber = levelNumber - LAST_GREEDY_LEVELNUMBER + 1;
          message = fileToString(
                  "src/main/resources/texts/levelTexts/B"
                          + fileNumber + ".txt");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        int chosenButton = JOptionPane.showOptionDialog(centerPanel,
                message,
                "Hinweis", JOptionPane.DEFAULT_OPTION, JOptionPane
                        .INFORMATION_MESSAGE, null, buttons,
                buttons[0]);
        //this case is not possible, all buttons are switched
        if (chosenButton == 0) { //nothing is supposed to happen
        }
      });
    }
    centerPanel.add(clue);
  }

  /**
   * Method for generating the correct escape message
   * depending on the level and solution.
   *
   * @return String of the generated escape message
   */
  public String generateEscapeMessage() {
    SolverBacktracking s = new SolverBacktracking();
    SolverGreedy sg = new SolverGreedy();
    ArrayList<Item> solution = new ArrayList<>();
    for (int i = 0; i < level.getItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountInRucksack(i); j++) {
        solution.add(level.getItemList().get(i));
      }
    }
    SolverGreedy.sortLikeGreedy(solution);
    if (this.level.getRobber().equals(Level.Robber.DR_META)) {
      if (solution.equals(SolverGreedy
              .sortLikeGreedy(s.solveAlgorithm(this.level)))) {
        //case: correct solution
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/2_1_1_OptimalSolution.txt");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return message;
      } else {
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/2_1_2_SuboptimalSolution.txt");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return message;
      }
    } else if (this.level.getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
      if (solution.equals(SolverGreedy
              .sortLikeGreedy(sg.solveAlgorithm(this.level)))) {
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/2_1_1_OptimalSolution.txt");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return message;
      } else if (solution.equals(SolverGreedy
              .sortLikeGreedy(s.solveAlgorithm(this.level)))) {
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/2_1_3_OptimalButWrongSolution.txt");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return message;
      } else {
        String message = null;
        try {
          message = fileToString(
                  "src/main/resources/texts/2_1_2_SuboptimalSolution.txt");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return message;
      }
    } else {
      return "Das solltest du nicht sehen können, es lief etwas schief.";
    }
  }

  /**
   * Method for formatting a TXT-file into a single String instance.
   *
   * @param fileName name of the TXT-file supposed to be shown
   * @return the String of the given TXT-file
   */
  public static String fileToString(final String fileName) throws IOException {
    FileReader reader = new FileReader(fileName);
    BufferedReader inBuffer = new BufferedReader(reader);
    StringBuilder message = new StringBuilder();
    String line = inBuffer.readLine();

    while (line != null) {
      message.append(line);
      message.append("\n");
      line = inBuffer.readLine();
    }
    return message.toString();
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
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_FIFTEEN);
    Font bigFont = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_THIRTY);
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
      JPanel itemPanel = new JPanel(new GridLayout(1, GRID_FOUR));
      ImageIcon imageIcon = items.get(i).getImageIcon();
      JButton itemIcon = new JButton(imageIcon);
      itemPanel.add(itemIcon);
      JLabel current = new JLabel(" (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
      current.setFont(font);
      itemPanel.add(current);
      int finalI = i;
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
      itemPanel.add(labels[i]);
      panelItems.add(itemPanel);
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
    pane.setLayout(new GridLayout(1, GRID_THREE));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(
            WIDTH_RUCKSACK, HEIGHT_RUCKSACK, java.awt.Image.SCALE_SMOOTH);

    //füge Räuber ein
    URL urlRobber = getClass().getClassLoader().getResource("DiebRot.png");
    assert urlRobber != null;
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(
        WIDTH_ROBBER, HEIGHT_ROBBER, Image.SCALE_SMOOTH);
    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage,
        X_POS_ROBBER, Y_POS_ROBBER);
    JPanel rightPanel = new JPanel();
    JPanel leftPanel = new JbackgroundPanel(scaledRucksackImage, 0, 0);
    // erzeuge Buttons
    this.escapeButton(centerPanel);
    this.clueButton(centerPanel);
    this.itemButtons(rightPanel, leftPanel);

    //alles zusammenpuzzeln
    pane.add(leftPanel);
    pane.add(centerPanel);
    pane.add(rightPanel);

    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);

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
}
