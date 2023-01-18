
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

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Level");
    titel.setBounds(200, 20, 300, 40);
    titel.setFont(fontStyle);
    pane.add(titel);

    JButton levelOne = new JButton("1");
    levelOne.setBounds(25, 200, 50, 50);
    levelOne.setBackground(Color.cyan);
    pane.add(levelOne);
    levelOne.addActionListener(e -> GuiManager.openLevel(guiLevelPages[0]));

    JLabel ganove = new JLabel("gieriger Ganove");
    ganove.setBounds(150, 80, 300, 40);
    ganove.setFont(fontStyle);
    pane.add(ganove);

    JButton levelTwoGreedy = new JButton("2");
    levelTwoGreedy.setBounds(60, 125, 50, 50);
    levelTwoGreedy.setBackground(Color.cyan);
    pane.add(levelTwoGreedy);
    guiLevelPages[1] = new GuiLevelPage(AppData.getLevel(1));
    levelTwoGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[1]);
    });


    JButton levelThreeGreedy = new JButton("3");
    levelThreeGreedy.setBounds(120, 125, 50, 50);
    levelThreeGreedy.setBackground(Color.cyan);
    pane.add(levelThreeGreedy);
    guiLevelPages[2] = new GuiLevelPage(AppData.getLevel(2));
    levelThreeGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[2]);
    });

    JButton levelFourGreedy = new JButton("4");
    levelFourGreedy.setBounds(180, 125, 50, 50);
    levelFourGreedy.setBackground(Color.cyan);
    pane.add(levelFourGreedy);
    guiLevelPages[3] = new GuiLevelPage(AppData.getLevel(3));
    levelFourGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[3]);
    });

    JButton levelFiveGreedy = new JButton("5");
    levelFiveGreedy.setBounds(240, 125, 50, 50);
    levelFiveGreedy.setBackground(Color.cyan);
    pane.add(levelFiveGreedy);
    guiLevelPages[4] = new GuiLevelPage(AppData.getLevel(4));
    levelFiveGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[4]);
    });

    JButton levelSixGreedy = new JButton("6");
    levelSixGreedy.setBounds(300, 125, 50, 50);
    levelSixGreedy.setBackground(Color.cyan);
    pane.add(levelSixGreedy);
    guiLevelPages[5] = new GuiLevelPage(AppData.getLevel(6));
    levelSixGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[5]);
    });

    JButton levelSevenGreedy = new JButton("7");
    levelSevenGreedy.setBounds(360, 125, 50, 50);
    levelSevenGreedy.setBackground(Color.cyan);
    pane.add(levelSevenGreedy);
    guiLevelPages[6] = new GuiLevelPage(AppData.getLevel(6));
    levelSevenGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[6]);
    });

    JButton levelEightGreedy = new JButton("8");
    levelEightGreedy.setBounds(420, 125, 50, 50);
    levelEightGreedy.setBackground(Color.cyan);
    pane.add(levelEightGreedy);
    guiLevelPages[7] = new GuiLevelPage(AppData.getLevel(7));
    levelEightGreedy.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[7]);
    });

    JLabel bandit = new JLabel("backtracking Bandit");
    bandit.setBounds(130, 235, 300, 40);
    bandit.setFont(fontStyle);
    pane.add(bandit);

    JButton levelTwoBack = new JButton("2");
    levelTwoBack.setBounds(60, 275, 50, 50);
    levelTwoBack.setBackground(Color.cyan);
    pane.add(levelTwoBack);
    guiLevelPages[8] = new GuiLevelPage(AppData.getLevel(8));
    levelTwoBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[8]);
    });

    JButton levelThreeBack = new JButton("3");
    levelThreeBack.setBounds(120, 275, 50, 50);
    levelThreeBack.setBackground(Color.cyan);
    pane.add(levelThreeBack);
    guiLevelPages[9] = new GuiLevelPage(AppData.getLevel(9));
    levelThreeBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[9]);
    });

    JButton levelFourBack = new JButton("4");
    levelFourBack.setBounds(180, 275, 50, 50);
    levelFourBack.setBackground(Color.cyan);
    pane.add(levelFourBack);
    guiLevelPages[10] = new GuiLevelPage(AppData.getLevel(10));
    levelFourBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[10]);
    });

    JButton levelFiveBack = new JButton("5");
    levelFiveBack.setBounds(240, 275, 50, 50);
    levelFiveBack.setBackground(Color.cyan);
    pane.add(levelFiveBack);
    guiLevelPages[11] = new GuiLevelPage(AppData.getLevel(11));
    levelFiveBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[11]);
    });

    JButton levelSixBack = new JButton("6");
    levelSixBack.setBounds(300, 275, 50, 50);
    levelSixBack.setBackground(Color.cyan);
    pane.add(levelSixBack);
    guiLevelPages[12] = new GuiLevelPage(AppData.getLevel(12));
    levelSixBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[12]);
    });

    JButton levelSevenBack = new JButton("7");
    levelSevenBack.setBounds(360, 275, 50, 50);
    levelSevenBack.setBackground(Color.cyan);
    pane.add(levelSevenBack);
    guiLevelPages[13] = new GuiLevelPage(AppData.getLevel(13));
    levelSevenBack.addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[13]);
    });

    JButton levelEightBack = new JButton("8");
    levelEightBack.setBounds(420, 275, 50, 50);
    levelEightBack.setBackground(Color.cyan);
    pane.add(levelEightBack);
    guiLevelPages[14] = new GuiLevelPage(AppData.getLevel(14));
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
