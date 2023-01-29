
package gui.level;

import java.awt.*;
import javax.swing.*;

import solving.AppData;
import solving.BacktrackingLevel;
import solving.GreedyLevel;

/**
 * The class GuiLevelDeciderPage confugures the gui of the level decider page.
 */
public class GuiLevelDeciderPage {

  /**
   * the array GUILevelPage holds all levelpages.
   */
  private final GuiLevelPage[] guiLevelPages =
      new GuiLevelPage[AppData.LEVELAMOUNT];

  /**
   * the array GUILevelButtons holds all buttons that start level.
   */
  private final JButton[] guiLevelButtons = new JButton[AppData.LEVELAMOUNT];

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns Container that contains the pane of level selection page.
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new BorderLayout());

    Container subPane = new Container();
    subPane.setLayout(new GridLayout(11, 1));

    Container subPaneGreedy = new Container();
    subPaneGreedy.setLayout(new GridLayout(1, 7));

    Container subPaneBacktracking = new Container();
    subPaneBacktracking.setLayout(new GridLayout(1, 7));


    Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      if (i == 0) {
        guiLevelPages[i] = new GuiLevelPage(AppData.getLevel(i));
        guiLevelButtons[i] = new JButton("1");
      } else if (i <= 7) {
        guiLevelPages[i] = new GuiLevelPageGreedy(GreedyLevel
          .getLevelGreedy(i - 1));
        guiLevelButtons[i] = new JButton(Integer.toString(i + 1));
      } else {
        guiLevelPages[i] = new GuiLevelPageBacktracking(BacktrackingLevel
          .getLevelBacktracking(i - 8));
        guiLevelButtons[i] = new JButton(Integer.toString(i - 6));
      }
      guiLevelButtons[i].setBackground(Color.cyan);
      guiLevelButtons[i].setFont(fontButtons);
    }

    //füge Level hinzu
    JPanel levelOne = new JPanel();
    levelOne.add(guiLevelButtons[0]);
    guiLevelButtons[0].addActionListener(e -> GuiManager.openLevel(guiLevelPages[0]));

    JPanel greedyLevel = new JPanel();

    greedyLevel.add(guiLevelButtons[1]);
    guiLevelButtons[1].addActionListener(e -> GuiManager.openLevel(guiLevelPages[1]));

    greedyLevel.add(guiLevelButtons[2]);
    guiLevelButtons[2].addActionListener(e -> GuiManager.openLevel(guiLevelPages[2]));

    greedyLevel.add(guiLevelButtons[3]);
    guiLevelButtons[3].addActionListener(e -> GuiManager.openLevel(guiLevelPages[3]));

    greedyLevel.add(guiLevelButtons[4]);
    guiLevelButtons[4].addActionListener(e -> GuiManager.openLevel(guiLevelPages[4]));

    greedyLevel.add(guiLevelButtons[5]);
    guiLevelButtons[5].addActionListener(e -> GuiManager.openLevel(guiLevelPages[5]));

    greedyLevel.add(guiLevelButtons[6]);
    guiLevelButtons[6].addActionListener(e -> GuiManager.openLevel(guiLevelPages[6]));

    greedyLevel.add(guiLevelButtons[7]);
    guiLevelButtons[7].addActionListener(e -> GuiManager.openLevel(guiLevelPages[7]));

    JPanel backtrackingLevel = new JPanel();

    backtrackingLevel.add(guiLevelButtons[8]);
    guiLevelButtons[8].addActionListener(e -> GuiManager.openLevel(guiLevelPages[8]));

    backtrackingLevel.add(guiLevelButtons[9]);
    guiLevelButtons[9].addActionListener(e -> GuiManager.openLevel(guiLevelPages[9]));

    backtrackingLevel.add(guiLevelButtons[10]);
    guiLevelButtons[10].addActionListener(e -> GuiManager.openLevel(guiLevelPages[10]));

    backtrackingLevel.add(guiLevelButtons[11]);
    guiLevelButtons[11].addActionListener(e -> GuiManager.openLevel(guiLevelPages[11]));

    backtrackingLevel.add(guiLevelButtons[12]);
    guiLevelButtons[12].addActionListener(e -> GuiManager.openLevel(guiLevelPages[12]));

    backtrackingLevel.add(guiLevelButtons[13]);
    guiLevelButtons[13].addActionListener(e -> GuiManager.openLevel(guiLevelPages[13]));

    backtrackingLevel.add(guiLevelButtons[14]);
    guiLevelButtons[14].addActionListener(e -> GuiManager.openLevel(guiLevelPages[14]));

    //erzeuge Buttons
    Font fontBig = new Font("Arial", Font.BOLD + Font.ITALIC, 50);
    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 40);
    Font fontSmall = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Level");
    titel.setFont(fontBig);

    JLabel levelOneTitle = new JLabel("Einführungslevel");
    levelOneTitle.setFont(fontSmall);

    JLabel greedy = new JLabel("Gieriger Ganove");
    greedy.setFont(fontStyle);

    JLabel backtracking = new JLabel("Backtracking Bandit");
    backtracking.setFont(fontStyle);

    JButton backToFrontPage = new JButton("zurück");
    backToFrontPage.setFont(fontButtons);
    backToFrontPage.setBackground(Color.cyan);
    backToFrontPage.addActionListener(e -> GuiManager.openMainMenu());
    JPanel backToFrontPagePanel = new JPanel();
    backToFrontPagePanel.add(backToFrontPage);


    JPanel titlePanel = new JPanel();
    titlePanel.add(titel);
    JPanel greedyTitle = new JPanel();
    greedyTitle.add(greedy);
    JPanel backtrackingTitle = new JPanel();
    backtrackingTitle.add(backtracking);
    JPanel levelOneTitlePanel = new JPanel();
    levelOneTitlePanel.add(levelOneTitle);


    //add panels on subpane
    subPane.add(levelOneTitlePanel);
    subPane.add(levelOne);
    subPane.add(greedyTitle);
    subPane.add(greedyLevel);
    JPanel emptyPanel = new JPanel();
    subPane.add(emptyPanel);
    subPane.add(backtrackingTitle);
    subPane.add(backtrackingLevel);
    subPane.add(emptyPanel);
    subPane.add(emptyPanel);
    subPane.add(backToFrontPagePanel);


    //puzzle panels und subpane zusammen
    pane.add(titlePanel, BorderLayout.NORTH);
    pane.add(subPane, BorderLayout.CENTER);

    return pane;
  }

  /**
   * this getter gets all levelpages.
   *
   * @return the levelpage.
   */
  public GuiLevelPage[] getGuiLevelPages() {
    return guiLevelPages;
  }

}
