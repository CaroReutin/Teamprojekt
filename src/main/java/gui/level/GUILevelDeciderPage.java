
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
public class GUILevelDeciderPage {
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns Container that contains the pane of level selection page.
   */
  public Container getPane() {
    GUILevelPage[] guiLevelPages = new GUILevelPage[15];
    Container pane = new Container();
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Level");
    titel.setBounds(200, 20, 300, 40);
    titel.setFont(fontStyle);
    pane.add(titel);

    //Testlevel 1
    ArrayList<Item> items = new ArrayList<>();
    items.add(AppData.generateItem("coin"));
    items.add(AppData.generateItem("crown"));
    items.add(AppData.generateItem("pearl"));
    ArrayList<Integer> amount = new ArrayList<>();
    amount.add(10);
    amount.add(7);
    amount.add(4);
    //

    JButton levelOne = new JButton("1");
    levelOne.setBounds(25, 200, 50, 50);
    levelOne.setBackground(Color.cyan);
    pane.add(levelOne);
    guiLevelPages[0] = new GUILevelPage(new Level(items, amount, 0, 60));
    levelOne.addActionListener(e -> GUIManager.openLevel(guiLevelPages[0]));

    JLabel ganove = new JLabel("gieriger Ganove");
    ganove.setBounds(150, 80, 300, 40);
    ganove.setFont(fontStyle);
    pane.add(ganove);

    JButton levelTwoGreedy = new JButton("2");
    levelTwoGreedy.setBounds(60, 125, 50, 50);
    levelTwoGreedy.setBackground(Color.cyan);
    pane.add(levelTwoGreedy);
    guiLevelPages[1] = new GUILevelPage(AppData.getLevel(1));
    levelTwoGreedy.addActionListener(e -> {
      GUIManager.openLevel(guiLevelPages[1]);
    });

    JButton levelThreeGreedy = new JButton("3");
    levelThreeGreedy.setBounds(120, 125, 50, 50);
    levelThreeGreedy.setBackground(Color.cyan);
    pane.add(levelThreeGreedy);
    levelThreeGreedy.addActionListener(e -> {

    });

    JButton levelFourGreedy = new JButton("4");
    levelFourGreedy.setBounds(180, 125, 50, 50);
    levelFourGreedy.setBackground(Color.cyan);
    pane.add(levelFourGreedy);
    levelFourGreedy.addActionListener(e -> {

    });

    JButton levelFiveGreedy = new JButton("5");
    levelFiveGreedy.setBounds(240, 125, 50, 50);
    levelFiveGreedy.setBackground(Color.cyan);
    pane.add(levelFiveGreedy);
    levelFiveGreedy.addActionListener(e -> {
    });

    JButton levelSixGreedy = new JButton("6");
    levelSixGreedy.setBounds(300, 125, 50, 50);
    levelSixGreedy.setBackground(Color.cyan);
    pane.add(levelSixGreedy);
    levelSixGreedy.addActionListener(e -> {

    });

    JButton levelSevenGreedy = new JButton("7");
    levelSevenGreedy.setBounds(360, 125, 50, 50);
    levelSevenGreedy.setBackground(Color.cyan);
    pane.add(levelSevenGreedy);
    levelSevenGreedy.addActionListener(e -> {

    });

    JButton levelEightGreedy = new JButton("8");
    levelEightGreedy.setBounds(420, 125, 50, 50);
    levelEightGreedy.setBackground(Color.cyan);
    pane.add(levelEightGreedy);
    levelEightGreedy.addActionListener(e -> {

    });

    JLabel bandit = new JLabel("backtracking Bandit");
    bandit.setBounds(130, 235, 300, 40);
    bandit.setFont(fontStyle);
    pane.add(bandit);

    JButton levelTwoBack = new JButton("2");
    levelTwoBack.setBounds(60, 275, 50, 50);
    levelTwoBack.setBackground(Color.cyan);
    pane.add(levelTwoBack);
    levelTwoBack.addActionListener(e -> {

    });

    JButton levelThreeBack = new JButton("3");
    levelThreeBack.setBounds(120, 275, 50, 50);
    levelThreeBack.setBackground(Color.cyan);
    pane.add(levelThreeBack);
    levelThreeBack.addActionListener(e -> {

    });

    JButton levelFourBack = new JButton("4");
    levelFourBack.setBounds(180, 275, 50, 50);
    levelFourBack.setBackground(Color.cyan);
    pane.add(levelFourBack);
    levelFourBack.addActionListener(e -> {

    });

    JButton levelFiveBack = new JButton("5");
    levelFiveBack.setBounds(240, 275, 50, 50);
    levelFiveBack.setBackground(Color.cyan);
    pane.add(levelFiveBack);
    levelFiveBack.addActionListener(e -> {

    });

    JButton levelSixBack = new JButton("6");
    levelSixBack.setBounds(300, 275, 50, 50);
    levelSixBack.setBackground(Color.cyan);
    pane.add(levelSixBack);
    levelSixBack.addActionListener(e -> {

    });

    JButton levelSevenBack = new JButton("7");
    levelSevenBack.setBounds(360, 275, 50, 50);
    levelSevenBack.setBackground(Color.cyan);
    pane.add(levelSevenBack);
    levelSevenBack.addActionListener(e -> {

    });

    JButton levelEightBack = new JButton("8");
    levelEightBack.setBounds(420, 275, 50, 50);
    levelEightBack.setBackground(Color.cyan);
    pane.add(levelEightBack);
    levelEightBack.addActionListener(e -> {

    });

    JButton backToFrontPage = new JButton("zurück");
    backToFrontPage.setBounds(25, 25, 80, 40);
    pane.add(backToFrontPage);
    backToFrontPage.addActionListener(e -> GUIManager.openMainMenu());

    return pane;
  }
}
