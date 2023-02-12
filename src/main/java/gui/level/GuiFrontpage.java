
package gui.level;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  @SuppressWarnings("checkstyle:TodoComment")
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new GridLayout(5, 1));

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


    /*JButton clueButton = new JButton();
    try {
      BufferedImage bi = ImageIO.read(new File("icons/clueSymbol.png"));
      bi.getScaledInstance(WIDTH_RUCKSACK, WIDTH_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
      ImageIcon clueIcon = new ImageIcon(bi);
      clueButton.add(clueIcon);

    } catch () {

    }*/


   /* String imgName = "icons/clueSymbol.png";
    URL imgURL = GuiFrontpage.class.getResource(imgName);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = tk.getImage(imgURL);
    img.getScaledInstance(WIDTH_RUCKSACK, WIDTH_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
    ImageIcon newClueSymbol = new ImageIcon(img);
    JButton clueButton = new JButton(newClueSymbol);*/

    ImageIcon clueSymbol = new ImageIcon(
      "src/main/resources/icons/clueSymbol.png");
    Image clueSymbolImage = clueSymbol.getImage().getScaledInstance(
      WIDTH_RUCKSACK, WIDTH_RUCKSACK, java.awt.Image.SCALE_SMOOTH);
    ImageIcon newClueSymbol = new ImageIcon(clueSymbolImage);
    JButton clueButton = new JButton(newClueSymbol);

    //add functions
    levelButton.addActionListener(e -> GuiManager.openLevelSelectScreen());
    ownLevelButton.addActionListener(e -> GuiManager.openLevelEditor());
    settingsButton.addActionListener(e -> GuiManager.openOptionsMenu());
    clueButton.addActionListener(e -> {
      try {
        // TODO Alternative Buttons und Icons im Bild!
        String message = GuiLevelPage.fileToStringFromFile("texts/1_Introtext.txt");
        String[] buttons = {"Schließen",
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
            String levelMessage = GuiLevelPage.fileToStringFromFile("texts/2_1_HowToPlay.txt");
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
                String greedyMessage = GuiLevelPage.fileToStringFromFile("texts/2_2_HowToGreedy.txt");
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
                GuiManager.openLevelSelectScreen();;
                String backtrackingMessage = GuiLevelPage.fileToStringFromFile("texts/2_3_HowToBacktracking.txt");

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
            String playOwnMessage = GuiLevelPage.fileToStringFromFile("texts/3_2_PlayOwnLevel.txt");

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
                String editorMessage = GuiLevelPage.fileToStringFromFile("texts/3_1_GenerateOwnLevel.txt");

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
    });

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
    //TODO Un-fuck up the frontpage
    //add panels on subpane
    pane.add(titlePanel);
    pane.add(levelPanel);
    pane.add(ownLevelPanel);
    pane.add(settingsPanel);
    pane.add(cluePanel);

    return pane;
  }
}