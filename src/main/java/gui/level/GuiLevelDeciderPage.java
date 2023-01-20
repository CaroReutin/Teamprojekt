
package gui.level;

import rucksack.Item;
import rucksack.Level;
import solving.AppData;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * The class GuiLevelDeciderPage confugures the gui of the level decider page.
 */
public class GuiLevelDeciderPage {

  /**
   * the array GUILevelPage holds all levelpages.
   */
  private final GuiLevelPage[] guiLevelPages = new GuiLevelPage[15];
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns Container that contains the pane of level selection page.
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 50);
    Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Level");
    titel.setBounds(430, 50, 250, 60);
    titel.setFont(fontStyle);
    pane.add(titel);

    JButton levelOne = new JButton("1");
    levelOne.setBounds(120, 300, 60, 60);
    levelOne.setBackground(Color.cyan);
    levelOne.setFont(fontButtons);
    pane.add(levelOne);
    guiLevelPages[0] = new GuiLevelPage(AppData.getLevel(0));
    levelOne.addActionListener(e -> GuiManager.openLevel(guiLevelPages[0]));

    JLabel ganove = new JLabel("gieriger Ganove");
    ganove.setBounds(280, 120, 400, 60);
    ganove.setFont(fontStyle);
    pane.add(ganove);

    JButton levelTwoGreedy = new JButton("2");
    levelTwoGreedy.setBounds(260, 210, 60, 60);
    levelTwoGreedy.setBackground(Color.cyan);
    levelTwoGreedy.setFont(fontButtons);
    pane.add(levelTwoGreedy);
    guiLevelPages[1] = new GuiLevelPageGreedy(AppData.initializeGreedy(0));
    levelTwoGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[1]);
    });


    JButton levelThreeGreedy = new JButton("3");
    levelThreeGreedy.setBounds(340, 210, 60, 60);
    levelThreeGreedy.setBackground(Color.cyan);
    levelThreeGreedy.setFont(fontButtons);
    pane.add(levelThreeGreedy);
    guiLevelPages[2] = new GuiLevelPage(AppData.initializeGreedy(1));
    levelThreeGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[2]);
    });

    JButton levelFourGreedy = new JButton("4");
    levelFourGreedy.setBounds(420, 210, 60, 60);
    levelFourGreedy.setBackground(Color.cyan);
    levelFourGreedy.setFont(fontButtons);
    pane.add(levelFourGreedy);
    guiLevelPages[3] = new GuiLevelPage(AppData.initializeGreedy(2));
    levelFourGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[3]);
    });

    JButton levelFiveGreedy = new JButton("5");
    levelFiveGreedy.setBounds(500, 210, 60, 60);
    levelFiveGreedy.setBackground(Color.cyan);
    levelFiveGreedy.setFont(fontButtons);
    pane.add(levelFiveGreedy);
    guiLevelPages[4] = new GuiLevelPage(AppData.initializeGreedy(3));
    levelFiveGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[4]);
    });

    JButton levelSixGreedy = new JButton("6");
    levelSixGreedy.setBounds(580, 210, 60, 60);
    levelSixGreedy.setBackground(Color.cyan);
    levelSixGreedy.setFont(fontButtons);
    pane.add(levelSixGreedy);
    guiLevelPages[5] = new GuiLevelPage(AppData.initializeGreedy(4));
    levelSixGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[5]);
    });

    JButton levelSevenGreedy = new JButton("7");
    levelSevenGreedy.setBounds(660, 210, 60, 60);
    levelSevenGreedy.setBackground(Color.cyan);
    levelSevenGreedy.setFont(fontButtons);
    pane.add(levelSevenGreedy);
    guiLevelPages[6] = new GuiLevelPage(AppData.initializeGreedy(5));
    levelSevenGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[6]);
    });

    JButton levelEightGreedy = new JButton("8");
    levelEightGreedy.setBounds(740, 210, 60, 60);
    levelEightGreedy.setBackground(Color.cyan);
    levelEightGreedy.setFont(fontButtons);
    pane.add(levelEightGreedy);
    guiLevelPages[7] = new GuiLevelPage(AppData.initializeGreedy(6));
    levelEightGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[7]);
    });

    JLabel bandit = new JLabel("backtracking Bandit");
    bandit.setBounds(250, 300, 500, 60);
    bandit.setFont(fontStyle);
    pane.add(bandit);

    JButton levelTwoBack = new JButton("2");
    levelTwoBack.setBounds(260, 390, 60, 60);
    levelTwoBack.setBackground(Color.cyan);
    levelTwoBack.setFont(fontButtons);
    pane.add(levelTwoBack);
    guiLevelPages[8] = new GuiLevelPageBacktracking(AppData.initializeBacktracking(0));
    levelTwoBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[8]);
    });

    JButton levelThreeBack = new JButton("3");
    levelThreeBack.setBounds(340, 390, 60, 60);
    levelThreeBack.setBackground(Color.cyan);
    levelThreeBack.setFont(fontButtons);
    pane.add(levelThreeBack);
    guiLevelPages[9] = new GuiLevelPage(AppData.initializeBacktracking(1));
    levelThreeBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[9]);
    });

    JButton levelFourBack = new JButton("4");
    levelFourBack.setBounds(420, 390, 60, 60);
    levelFourBack.setBackground(Color.cyan);
    levelFourBack.setFont(fontButtons);
    pane.add(levelFourBack);
    guiLevelPages[10] = new GuiLevelPage(AppData.initializeBacktracking(2));
    levelFourBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[10]);
    });

    JButton levelFiveBack = new JButton("5");
    levelFiveBack.setBounds(500, 390, 60, 60);
    levelFiveBack.setBackground(Color.cyan);
    levelFiveBack.setFont(fontButtons);
    pane.add(levelFiveBack);
    guiLevelPages[11] = new GuiLevelPage(AppData.initializeBacktracking(3));
    levelFiveBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[11]);
    });

    JButton levelSixBack = new JButton("6");
    levelSixBack.setBounds(580, 390, 60, 60);
    levelSixBack.setBackground(Color.cyan);
    levelSixBack.setFont(fontButtons);
    pane.add(levelSixBack);
    guiLevelPages[12] = new GuiLevelPage(AppData.initializeBacktracking(4));
    levelSixBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[12]);
    });

    JButton levelSevenBack = new JButton("7");
    levelSevenBack.setBounds(660, 390, 60, 60);
    levelSevenBack.setBackground(Color.cyan);
    levelSevenBack.setFont(fontButtons);
    pane.add(levelSevenBack);
    guiLevelPages[13] = new GuiLevelPage(AppData.initializeBacktracking(5));
    levelSevenBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[13]);
    });

    JButton levelEightBack = new JButton("8");
    levelEightBack.setBounds(740, 390, 60, 60);
    levelEightBack.setBackground(Color.cyan);
    levelEightBack.setFont(fontButtons);
    pane.add(levelEightBack);
    guiLevelPages[14] = new GuiLevelPage(AppData.initializeBacktracking(6));
    levelEightBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[14]);
    });

    JButton backToFrontPage = new JButton("zurück");
    backToFrontPage.setBounds(25, 25, 80, 40);
    pane.add(backToFrontPage);
    backToFrontPage.addActionListener(e -> GuiManager.openMainMenu());

    return pane;
  }

  /**
   * this getter gets all levelpages.
   * @return the levelpage.
   */
  public GuiLevelPage[] getGuiLevelPages() {
    return guiLevelPages;
  }

}
