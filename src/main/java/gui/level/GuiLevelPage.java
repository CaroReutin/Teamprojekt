
package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
    JButton flucht = new JButton("Flucht");
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
    } else if (levelNumber == LAST_GREEDY_LEVELNUMBER
        || levelNumber == LAST_BACKTRACKING_LEVELNUMBER || levelNumber == 0) {
      flucht.addActionListener(e -> {
        if (levelNumber >= 0) {
          if (level.getCurrentValue() > UserDataManager.getScore(
              GuiManager.numberLevel)) {
            UserDataManager.newHighScore(GuiManager.numberLevel,
                level.getCurrentValue());
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
            .getScore(GuiManager.numberLevel)) {
          UserDataManager.newHighScore(GuiManager.numberLevel,
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

  /*
  *
  *
  */

  /**
   * Adds the clueButton to the Panel.
   *
   * @param centerPanel the Panel that the escapeButton should be on.
   */
  public void clueButton(final Container centerPanel) {
    JButton hinweis = new JButton("Hinweis");
    int levelNumber = GuiManager.numberLevel;
    if (levelNumber == -1) {
      hinweis.addActionListener(e -> {
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
        switch (chosenButton) {
          case 0 -> {
            //nothing is supposed to happen
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else if (levelNumber == 0) {
      hinweis.addActionListener(e -> {
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
        switch (chosenButton) {
          case 0 -> {
            //nothing is supposed to happen
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else if (0 < levelNumber && levelNumber <= LAST_GREEDY_LEVELNUMBER) {
      hinweis.addActionListener(e -> {
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
        switch (chosenButton) {
          case 0 -> {
            //nothing is supposed to happen
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    } else if (0 < LAST_GREEDY_LEVELNUMBER
            & levelNumber <= LAST_BACKTRACKING_LEVELNUMBER) {
      hinweis.addActionListener(e -> {
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
        switch (chosenButton) {
          case 0 -> {
            //nothing is supposed to happen
          }
          default -> {
          }
          //this case is not possible, all buttons are switched
        }
      });
    }
    centerPanel.add(hinweis);
  }

  /**
   * Method for generating the correct escape message
   * depending on the level and solution.
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
    currentWeightLabel = new JLabel("0/" + level.getCapacity() + "g");
    Font fontCurrentWeightLabel = currentWeightLabel.getFont();
    currentWeightLabel.setFont(
        fontCurrentWeightLabel.deriveFont(fontCurrentWeightLabel
            .getStyle() | Font.BOLD));

    currentValueLabel = new JLabel("0€");
    Font fontCurrentValueLabel = currentValueLabel.getFont();
    currentValueLabel.setFont(
        fontCurrentValueLabel.deriveFont(fontCurrentValueLabel
            .getStyle() | Font.BOLD));

    ArrayList<Item> items = level.getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(level.getItemAmountList().get(i).toString());

      Font f = labels[i].getFont();
      labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

      rucksackLabels[i] = new JLabel("0");
      Font fontRucksack = rucksackLabels[i].getFont();
      rucksackLabels[i].setFont(fontRucksack.deriveFont(
          fontRucksack.getStyle() | Font.BOLD));
      int finalI = i;
      JPanel itemPanel = new JPanel();
      JButton itemIcon = new JButton(items.get(i).getImageIcon());
      itemPanel.add(itemIcon);
      JLabel current = new JLabel(items.get(i).getName()
          + " (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
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
      JButton currentRucksack = new JButton(items.get(i).getImageIcon());
      currentRucksack.addActionListener(e -> {
        if (level.getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (!level.getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          level.moveFromRucksack(finalI);
          updateLabel(finalI);
        }

      });
      panelItems.add(current);
      panelItems.add(itemPanel);
      panelItems.add(labels[i]);
      panelRucksack.add(currentRucksack);
      panelRucksack.add(rucksackLabels[i]);
      panelRucksack.add(currentWeightLabel);
      panelRucksack.add(currentValueLabel);

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
            200, 350, java.awt.Image.SCALE_SMOOTH);


    JPanel leftPanel = new JbackgroundPanel(scaledRucksackImage, 0);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel rightPanel = new JPanel();
    //JPanel rightPanel = new JPanel
    // (new GridLayout(level.getItemList().size(), 1));

    // erzeuge Buttons
    this.escapeButton(centerPanel);
    this.clueButton(centerPanel);
    this.itemButtons(rightPanel, leftPanel);


    //alles zusammenpuzzeln

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


  /* *
   * Returns the level number.
   * @return the level number.
   */
  /*public int getLevelNumber() {
    return this.level.getLevelNumber();
  }*/
}
