
package gui.level;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class configures the gui frontpage.
 */
public class GuiFrontpage {
  /**
   * The constant NUM_ROWS_SUBPANE, the number of rows in the pane.
   */
  public static final int NUM_ROWS_SUBPANE = 5;

  /**
   * The constant SIZE_FONT_SMALL.
   */
  public static final int SIZE_FONT_SMALL = 30;
  /**
   * The constant SIZE_FONT_BIG.
   */
  public static final int SIZE_FONT_BIG = 50;

  /**
   * The constant HEIGHT_RUCKSACK.
   */
  public static final int HEIGHT_RUCKSACK = 80;
  /**
   * The constant WIDTH_RUCKSACK.
   */
  public static final int WIDTH_RUCKSACK = 57;

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the frontpage
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new GridLayout(NUM_ROWS_SUBPANE, 1));

    //füge Text und Buttons hinzu
    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, SIZE_FONT_BIG);
    Font fontButtons = new Font("Arial",
        Font.BOLD + Font.ITALIC, SIZE_FONT_SMALL);
    JLabel title = new JLabel("Optimal Heist");
    title.setFont(fontStyle);
    JButton levelButton = new JButton("Level");
    levelButton.setFont(fontButtons);
    levelButton.setBackground(Color.LIGHT_GRAY);
    JButton ownLevelButton = new JButton("Eigene Level");
    ownLevelButton.setBackground(Color.LIGHT_GRAY);
    ownLevelButton.setFont(fontButtons);
    JButton settingsButton = new JButton("Einstellungen");
    settingsButton.setBackground(Color.LIGHT_GRAY);
    settingsButton.setFont(fontButtons);
    ImageIcon clueSymbol = new ImageIcon(
        "src/main/resources/icons/clueSymbol.png");
    Image clueSymbolImage = clueSymbol.getImage().getScaledInstance(
        WIDTH_RUCKSACK, WIDTH_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
    ImageIcon newClueSymbol = new ImageIcon(clueSymbolImage);
    //add functions
    JButton clueButton = new JButton(newClueSymbol);
    clueButton.addActionListener(e -> clueButtonEvent());
    levelButton.addActionListener(e -> GuiManager.openLevelSelectScreen());
    ownLevelButton.addActionListener(e -> GuiManager.openLevelEditor());
    settingsButton.addActionListener(e -> GuiManager.openOptionsMenu());

    //Füge Rucksack und Hinweis png ein und ändere Größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(
            WIDTH_RUCKSACK, HEIGHT_RUCKSACK, java.awt.Image.SCALE_SMOOTH);

    JLabel picture = new JLabel(new ImageIcon(scaledRucksackImage));

    //füge Text und Buttons auf panels hinzu
    JPanel titlePanel = new JPanel();
    titlePanel.add(title);
    titlePanel.add(picture);
    JPanel levelPanel = new JPanel();
    levelPanel.add(levelButton);
    JPanel ownLevelPanel = new JPanel();
    ownLevelPanel.add(ownLevelButton);
    JPanel settingsPanel = new JPanel();
    settingsPanel.add(settingsButton);
    JPanel cluePanel = new JPanel();
    cluePanel.add(clueButton);
    //add panels on sub-pane
    pane.add(titlePanel);
    pane.add(levelPanel);
    pane.add(ownLevelPanel);
    pane.add(settingsPanel);
    pane.add(cluePanel);

    return pane;
  }

  private static void clueButtonEvent() {
    try {
      String message = GuiLevelPage.fileToString(
          "src/main/resources/texts/1_Introtext.txt");
      String[] buttons = {
          "Schließen",
          "Wie spiele ich?",
          "Eigene Level spielen?"};
      int chosenButton = JOptionPane.showOptionDialog(null,
          message,
          "Begrüßung",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          buttons, buttons[0]);
      switch (chosenButton) {
        case 0 -> {
          GuiManager.openMainMenu();
        }
        case 1 -> {
          String levelMessage = GuiLevelPage.fileToString(
              "src/main/resources/texts/2_1_HowToPlay.txt");
          String[] levelTypeButtons = {
              "Zur Levelauswahl",
              "Greedy-Ganove?",
              "Backtracking-Bandit?"};
          int chosenLevelButton = JOptionPane.showOptionDialog(null,
              levelMessage,
              "Wie spiele ich?",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.INFORMATION_MESSAGE,
              null,
              levelTypeButtons, levelTypeButtons[0]);
          switch (chosenLevelButton) {
            case 0 -> {
              GuiManager.openLevelSelectScreen();
            }
            case 1 -> {
              GuiManager.openLevelSelectScreen();
              String greedyMessage = GuiLevelPage.fileToString(
                  "src/main/resources/texts/2_2_HowToGreedy.txt");
              String[] greedyButtons = {"Los geht's!"};
              int chosenGreedyButton = JOptionPane.showOptionDialog(null,
                  greedyMessage,
                  "Greedy-Ganove",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.INFORMATION_MESSAGE,
                  null,
                  greedyButtons, greedyButtons[0]);
              switch (chosenGreedyButton) {
                case 0 -> {
                  GuiManager.openLevelSelectScreen();
                }
                default -> { //should not happen...
                }
              }
            }
            case 2 -> {
              GuiManager.openLevelSelectScreen();
              String backtrackingMessage = GuiLevelPage.fileToString(
                  "src/main/resources/texts/2_3_HowToBacktracking.txt");
              String[] greedyButtons = {"Los geht's!"};
              int chosenGreedyButton = JOptionPane.showOptionDialog(null,
                  backtrackingMessage,
                  "Backtracking-Bandit",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.INFORMATION_MESSAGE,
                  null,
                  greedyButtons, greedyButtons[0]);
              switch (chosenGreedyButton) {
                case 0 -> {
                  GuiManager.openLevelSelectScreen();
                }
                default -> { //should not happen...
                }
              }
            }
            default -> { //should not happen...
            }
          }
        }
        case 2 -> {
          String playOwnMessage = GuiLevelPage.fileToString(
              "src/main/resources/texts/3_2_PlayOwnLevel.txt");
          String[] playOwnButtons = {
              "Museumswerkstatt betreten",
              "Was ist die Museumswerkstatt?"};
          int chosenGreedyButton = JOptionPane.showOptionDialog(null,
              playOwnMessage,
              "Eigene Level",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.INFORMATION_MESSAGE,
              null,
              playOwnButtons, playOwnButtons[0]);
          switch (chosenGreedyButton) {
            case 0 -> {
              GuiManager.openLevelEditor();
            }
            case 1 -> {
              GuiManager.openLevelEditor();
              String editorMessage = GuiLevelPage.fileToString(
                  "src/main/resources/texts/3_1_GenerateOwnLevel.txt");
              String[] editorButtons = {"Los geht's!"};
              int chosenEditorButton = JOptionPane.showOptionDialog(null,
                  editorMessage,
                  "Museumswerkstatt",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.INFORMATION_MESSAGE,
                  null,
                  editorButtons, editorButtons[0]);
              switch (chosenEditorButton) {
                case 0 -> {
                  GuiManager.openLevelEditor();
                }
                default -> { //should not happen...
                }
              }
            }
            default -> { //should not happen...
            }
          }
        }
        default -> { //should not happen...
        }
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
