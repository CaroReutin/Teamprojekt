
package gui.level;

import rucksack.Item;
import rucksack.Level;
import solving.AppData;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/* TODO: Make a for-loop, that initializes the levels iteratively*/

/**
 * The class GuiLevelDeciderPage confugures the gui of the level decider page.
 */
public class GUILevelDeciderPage {

  /**
   * Size of the font for all levels.
   */
  private final int fontSize = 30;
  /**
   * Amount of levels.
   */
  private final int levelAmount = 15;
  /**
   * Height and width of a standard level button.
   */
  private final int buttonDimension = 50;
  /**
   * Y-coordinate position of the upper row (greedy level).
   */
  private final int upperRowY = 125;
  /**
   * Y-coordinate position of the lower row (backtracking level).
   */
  private final int lowerRowY = 275;
  /**
   * the array GUILevelPage holds all levelpages.
   */
  private final GUILevelPage[] guiLevelPages = new GUILevelPage[levelAmount];
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns Container that contains the pane of level selection page.
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, fontSize);

    JLabel title = new JLabel("Level");
    title.setBounds(200, 20, 300, 40);
    title.setFont(fontStyle);
    pane.add(title);

    //Testlevel 1
    ArrayList<Item> items = new ArrayList<>();
    items.add(AppData.generateItem(0));
    items.add(AppData.generateItem(1));
    items.add(AppData.generateItem(2));
    ArrayList<Integer> amount = new ArrayList<>();
    amount.add(10);
    amount.add(7);
    amount.add(4);
    //

    JButton levelOne = new JButton("1");
    levelOne.setBounds(25, 200, buttonDimension, buttonDimension);
    levelOne.setBackground(Color.cyan);
    pane.add(levelOne);
    guiLevelPages[0] = new GUILevelPage(new Level(items, amount, 0, 60));
    levelOne.addActionListener(e -> GUIManager.openLevel(guiLevelPages[0]));

    JLabel ganove = new JLabel("gieriger Ganove");
    ganove.setBounds(150, 80, 300, 40);
    ganove.setFont(fontStyle);
    pane.add(ganove);

    JButton levelTwoGreedy = new JButton("2");
    levelTwoGreedy.setBounds(60, upperRowY, buttonDimension, buttonDimension);
    levelTwoGreedy.setBackground(Color.cyan);
    pane.add(levelTwoGreedy);
    guiLevelPages[1] = new GUILevelPage(AppData.getLevel(1));
    levelTwoGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[1]);
    });


    JButton levelThreeGreedy = new JButton("3");
    levelThreeGreedy.setBounds(120, upperRowY, buttonDimension, buttonDimension);
    levelThreeGreedy.setBackground(Color.cyan);
    pane.add(levelThreeGreedy);
    levelThreeGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[2]);
    });

    JButton levelFourGreedy = new JButton("4");
    levelFourGreedy.setBounds(180, upperRowY, buttonDimension, buttonDimension);
    levelFourGreedy.setBackground(Color.cyan);
    pane.add(levelFourGreedy);
    levelFourGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[3]);
    });

    JButton levelFiveGreedy = new JButton("5");
    levelFiveGreedy.setBounds(240, upperRowY, buttonDimension, buttonDimension);
    levelFiveGreedy.setBackground(Color.cyan);
    pane.add(levelFiveGreedy);
    levelFiveGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[4]);
    });

    JButton levelSixGreedy = new JButton("6");
    levelSixGreedy.setBounds(300, upperRowY, buttonDimension, buttonDimension);
    levelSixGreedy.setBackground(Color.cyan);
    pane.add(levelSixGreedy);
    levelSixGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[5]);
    });

    JButton levelSevenGreedy = new JButton("7");
    levelSevenGreedy.setBounds(360, upperRowY, buttonDimension, buttonDimension);
    levelSevenGreedy.setBackground(Color.cyan);
    pane.add(levelSevenGreedy);
    levelSevenGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[6]);
    });

    JButton levelEightGreedy = new JButton("8");
    levelEightGreedy.setBounds(420, upperRowY, buttonDimension, buttonDimension);
    levelEightGreedy.setBackground(Color.cyan);
    pane.add(levelEightGreedy);
    levelEightGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[7]);
    });

    JLabel bandit = new JLabel("backtracking Bandit");
    bandit.setBounds(130, 235, 300, 40);
    bandit.setFont(fontStyle);
    pane.add(bandit);

    JButton levelTwoBack = new JButton("2");
    levelTwoBack.setBounds(60, lowerRowY, buttonDimension, buttonDimension);
    levelTwoBack.setBackground(Color.cyan);
    pane.add(levelTwoBack);
    levelTwoBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[8]);
    });

    JButton level3Back = new JButton("3");
    level3Back.setBounds(120, lowerRowY, buttonDimension, buttonDimension);
    level3Back.setBackground(Color.cyan);
    pane.add(level3Back);
    level3Back.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[9]);
    });

    JButton levelFourBack = new JButton("4");
    levelFourBack.setBounds(180, lowerRowY, buttonDimension, buttonDimension);
    levelFourBack.setBackground(Color.cyan);
    pane.add(levelFourBack);
    levelFourBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[10]);
    });

    JButton levelFiveBack = new JButton("5");
    levelFiveBack.setBounds(240, lowerRowY, buttonDimension, buttonDimension);
    levelFiveBack.setBackground(Color.cyan);
    pane.add(levelFiveBack);
    levelFiveBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[11]);
    });

    JButton levelSixBack = new JButton("6");
    levelSixBack.setBounds(300, lowerRowY, buttonDimension, buttonDimension);
    levelSixBack.setBackground(Color.cyan);
    pane.add(levelSixBack);
    levelSixBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[12]);
    });

    JButton levelSevenBack = new JButton("7");
    levelSevenBack.setBounds(360, lowerRowY, buttonDimension, buttonDimension);
    levelSevenBack.setBackground(Color.cyan);
    pane.add(levelSevenBack);
    levelSevenBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[13]);
    });

    JButton levelEightBack = new JButton("8");
    levelEightBack.setBounds(420, lowerRowY, buttonDimension, buttonDimension);
    levelEightBack.setBackground(Color.cyan);
    pane.add(levelEightBack);
    levelEightBack.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[14]);
    });

    JButton backToFrontPage = new JButton("zurück");
    backToFrontPage.setBounds(25, 25, 80, 40);
    pane.add(backToFrontPage);
    backToFrontPage.addActionListener(e -> GUIManager.openMainMenu());

    return pane;
  }

  /**
   * This getter gets all level pages.
   * @return the level page.
   */
  public GUILevelPage[] getGuiLevelPages() {
    return guiLevelPages;
  }

}
