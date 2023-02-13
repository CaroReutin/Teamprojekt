
package gui.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
   * the number of the rows of the subpane.
   */
  public static final int NUM_ROWS_SUBPANE = 8;

  /**
   * the number of the colums on the algorithm panes.
   */
  public static final int NUM_COLS_SUBALGO = 7;

  /**
   * The constant SIZE_FONT_SMALL.
   */
  public static final int SIZE_FONT_SMALL = 30;

  /**
   * The constant SIZE_FONT_MEDIUM.
   */
  public static final int SIZE_FONT_MEDIUM = 40;

  /**
   * The constant SIZE_FONT_BIG.
   */
  public static final int SIZE_FONT_BIG = 50;

  /**
   * The constant NUM_LEVEL_Three.
   */
  public static final int NUM_LEVEL_THREE = 3;

  /**
   * The constant NUM_LEVEL_FOUR.
   */
  public static final int NUM_LEVEL_FOUR = 4;

  /**
   * The constant NUM_LEVEL_FIVE.
   */
  public static final int NUM_LEVEL_FIVE = 5;

  /**
   * The constant NUM_LEVEL_SIX.
   */
  public static final int NUM_LEVEL_SIX = 6;

  /**
   * The constant NUM_LEVEL_SEVEN.
   */
  public static final int NUM_LEVEL_SEVEN = 7;

  /**
   * The constant NUM_LEVEL_EIGHT.
   */
  public static final int NUM_LEVEL_EIGHT = 8;

  /**
   * The constant NUM_LEVEL_NINE.
   */
  public static final int NUM_LEVEL_NINE = 9;

  /**
   * The constant NUM_LEVEL_TEN.
   */
  public static final int NUM_LEVEL_TEN = 10;

  /**
   * The constant NUM_LEVEL_ELEVEN.
   */
  public static final int NUM_LEVEL_ELEVEN = 11;

  /**
   * The constant NUM_LEVEL_TWELVE.
   */
  public static final int NUM_LEVEL_TWELVE = 12;

  /**
   * The constant NUM_LEVEL_THIRTEEN.
   */
  public static final int NUM_LEVEL_THIRTEEN = 13;

  /**
   * The constant NUM_LEVEL_FOURTEEN.
   */
  public static final int NUM_LEVEL_FOURTEEN = 14;

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns Container that contains the pane of level selection page.
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new BorderLayout());
    Container subPane = new Container();
    subPane.setLayout(new GridLayout(NUM_ROWS_SUBPANE, 1));
    Container subPaneGreedy = new Container();
    subPaneGreedy.setLayout(new GridLayout(1, NUM_COLS_SUBALGO));
    Container subPaneBacktracking = new Container();
    subPaneBacktracking.setLayout(new GridLayout(1, NUM_COLS_SUBALGO));
    Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC,
        SIZE_FONT_SMALL);

    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      if (i == 0) {
        guiLevelPages[i] = new GuiLevelPage(AppData.getLevel(i));
        guiLevelButtons[i] = new JButton("1");
      } else if (i <= NUM_LEVEL_SEVEN) {
        guiLevelPages[i] = new GuiLevelPageGreedy(GreedyLevel
            .getLevelGreedy(i - 1));
        guiLevelButtons[i] = new JButton(Integer.toString(i + 1));
      } else {
        guiLevelPages[i] = new GuiLevelPageBacktracking(BacktrackingLevel
            .getLevelBacktracking(i - NUM_LEVEL_EIGHT));
        guiLevelButtons[i] = new JButton(Integer.toString(i - NUM_LEVEL_SIX));
      }
      guiLevelButtons[i].setBackground(Color.LIGHT_GRAY);
      guiLevelButtons[i].setFont(fontButtons);
    }

    //füge Level hinzu
    JPanel levelOne = new JPanel();
    levelOne.add(guiLevelButtons[0]);
    guiLevelButtons[0].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[0], 0));

    JPanel greedyLevel = new JPanel();

    greedyLevel.add(guiLevelButtons[1]);
    guiLevelButtons[1].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[1], 1));

    greedyLevel.add(guiLevelButtons[2]);
    guiLevelButtons[2].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[2], 2));

    greedyLevel.add(guiLevelButtons[NUM_LEVEL_THREE]);
    guiLevelButtons[NUM_LEVEL_THREE].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_THREE], NUM_LEVEL_THREE));

    greedyLevel.add(guiLevelButtons[NUM_LEVEL_FOUR]);
    guiLevelButtons[NUM_LEVEL_FOUR].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_FOUR], NUM_LEVEL_FOUR));

    greedyLevel.add(guiLevelButtons[NUM_LEVEL_FIVE]);
    guiLevelButtons[NUM_LEVEL_FIVE].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_FIVE], NUM_LEVEL_FIVE));

    greedyLevel.add(guiLevelButtons[NUM_LEVEL_SIX]);
    guiLevelButtons[NUM_LEVEL_SIX].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_SIX], NUM_LEVEL_SIX));

    greedyLevel.add(guiLevelButtons[NUM_LEVEL_SEVEN]);
    guiLevelButtons[NUM_LEVEL_SEVEN].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_SEVEN], NUM_LEVEL_SEVEN));

    JPanel backtrackingLevel = new JPanel();

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_EIGHT]);
    guiLevelButtons[NUM_LEVEL_EIGHT].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_EIGHT], NUM_LEVEL_EIGHT));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_NINE]);
    guiLevelButtons[NUM_LEVEL_NINE].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_NINE], NUM_LEVEL_NINE));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_TEN]);
    guiLevelButtons[NUM_LEVEL_TEN].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_TEN], NUM_LEVEL_TEN));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_ELEVEN]);
    guiLevelButtons[NUM_LEVEL_ELEVEN].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_ELEVEN], NUM_LEVEL_ELEVEN));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_TWELVE]);
    guiLevelButtons[NUM_LEVEL_TWELVE].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_TWELVE], NUM_LEVEL_TWELVE));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_THIRTEEN]);
    guiLevelButtons[NUM_LEVEL_THIRTEEN].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_THIRTEEN], NUM_LEVEL_THIRTEEN));

    backtrackingLevel.add(guiLevelButtons[NUM_LEVEL_FOURTEEN]);
    guiLevelButtons[NUM_LEVEL_FOURTEEN].addActionListener(
        e -> GuiManager.openLevel(guiLevelPages[NUM_LEVEL_FOURTEEN], NUM_LEVEL_FOURTEEN));

    //erzeuge Buttons
    Font fontBig = new Font("Arial", Font.BOLD + Font.ITALIC, SIZE_FONT_BIG);
    Font fontStyle = new Font("Arial",
        Font.BOLD + Font.ITALIC, SIZE_FONT_MEDIUM);
    JLabel titel = new JLabel("Level");
    titel.setFont(fontBig);

    JLabel levelOneTitle = new JLabel("Einführungslevel");
    levelOneTitle.setFont(fontButtons);

    JLabel greedy = new JLabel("Gieriger Ganove");
    greedy.setFont(fontStyle);

    JLabel backtracking = new JLabel("Backtracking Bandit");
    backtracking.setFont(fontStyle);

    JButton backToFrontPage = new JButton("zurück");
    backToFrontPage.setFont(fontButtons);
    backToFrontPage.setBackground(Color.LIGHT_GRAY);
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
    subPane.add(titlePanel);
    subPane.add(levelOneTitlePanel);
    subPane.add(levelOne);
    subPane.add(greedyTitle);
    subPane.add(greedyLevel);
    subPane.add(backtrackingTitle);
    subPane.add(backtrackingLevel);
    subPane.add(backToFrontPagePanel);

    return subPane;
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
