
package GUI_Level;

import Rucksack.Item;
import Rucksack.Level;
import Rucksack.Rucksack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUILevelDeciderPage {

    GUILevelPage[] guiLevelPages = new GUILevelPage[15];


    public void openLevelDeciderPage(JFrame frame, GUIManager guiManager) {

        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Level");
        titel.setBounds(200, 20, 300, 40);
        titel.setFont(fontStyle);
        pane.add(titel);

        //Testlevel 1
        Item coin = new Item(5, 1, "coin");
        Item crown = new Item(50, 8, "crown");
        Item pearl = new Item(11, 2, "pearl");
        Rucksack rucksack = new Rucksack(60);

        ArrayList<Item> items = new ArrayList<>();
        items.add(coin);
        items.add(crown);
        items.add(pearl);
        ArrayList<Integer> amount = new ArrayList<>();
        amount.add(10);
        amount.add(7);
        amount.add(4);
        //

        JButton levelOne = new JButton("1");
        levelOne.setBounds(25, 200, 50, 50);
        levelOne.setBackground(Color.cyan);
        pane.add(levelOne);
        int levelindex = 0;
        guiLevelPages[levelindex] = new GUILevelPage(new Level(rucksack,items, amount,levelindex));
        levelOne.addActionListener(e -> {
            guiManager.rePaintFrame(pane);
            guiLevelPages[levelindex].startLevelFrame(frame, guiManager);
        });

        JLabel ganove = new JLabel("gieriger Ganove");
        ganove.setBounds(150, 80, 300, 40);
        ganove.setFont(fontStyle);
        pane.add(ganove);

        JButton levelTwoGreedy = new JButton("2");
        levelTwoGreedy.setBounds(60, 125, 50, 50);
        levelTwoGreedy.setBackground(Color.cyan);
        pane.add(levelTwoGreedy);
        levelTwoGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelThreeGreedy = new JButton("3");
        levelThreeGreedy.setBounds(120, 125, 50, 50);
        levelThreeGreedy.setBackground(Color.cyan);
        pane.add(levelThreeGreedy);
        levelThreeGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });


        JButton levelFourGreedy = new JButton("4");
        levelFourGreedy.setBounds(180, 125, 50, 50);
        levelFourGreedy.setBackground(Color.cyan);
        pane.add(levelFourGreedy);
        levelFourGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelFiveGreedy = new JButton("5");
        levelFiveGreedy.setBounds(240, 125, 50, 50);
        levelFiveGreedy.setBackground(Color.cyan);
        pane.add(levelFiveGreedy);
        levelFiveGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelSixGreedy = new JButton("6");
        levelSixGreedy.setBounds(300, 125, 50, 50);
        levelSixGreedy.setBackground(Color.cyan);
        pane.add(levelSixGreedy);
        levelSixGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelSevenGreedy = new JButton("7");
        levelSevenGreedy.setBounds(360, 125, 50, 50);
        levelSevenGreedy.setBackground(Color.cyan);
        pane.add(levelSevenGreedy);
        levelSevenGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelEightGreedy = new JButton("8");
        levelEightGreedy.setBounds(420, 125, 50, 50);
        levelEightGreedy.setBackground(Color.cyan);
        pane.add(levelEightGreedy);
        levelEightGreedy.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

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
            guiManager.rePaintFrame(pane);

        });

        JButton levelThreeBack = new JButton("3");
        levelThreeBack.setBounds(120, 275, 50, 50);
        levelThreeBack.setBackground(Color.cyan);
        pane.add(levelThreeBack);
        levelThreeBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelFourBack = new JButton("4");
        levelFourBack.setBounds(180, 275, 50, 50);
        levelFourBack.setBackground(Color.cyan);
        pane.add(levelFourBack);
        levelFourBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelFiveBack = new JButton("5");
        levelFiveBack.setBounds(240, 275, 50, 50);
        levelFiveBack.setBackground(Color.cyan);
        pane.add(levelFiveBack);
        levelFiveBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelSixBack = new JButton("6");
        levelSixBack.setBounds(300, 275, 50, 50);
        levelSixBack.setBackground(Color.cyan);
        pane.add(levelSixBack);
        levelSixBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelSevenBack = new JButton("7");
        levelSevenBack.setBounds(360, 275, 50, 50);
        levelSevenBack.setBackground(Color.cyan);
        pane.add(levelSevenBack);
        levelSevenBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton levelEightBack = new JButton("8");
        levelEightBack.setBounds(420, 275, 50, 50);
        levelEightBack.setBackground(Color.cyan);
        pane.add(levelEightBack);
        levelEightBack.addActionListener(e -> {
            guiManager.rePaintFrame(pane);

        });

        JButton backToFrontPage = new JButton("zurück");
        backToFrontPage.setBounds(25, 25, 80, 40);
        pane.add(backToFrontPage);
        backToFrontPage.addActionListener(e -> {
            guiManager.rePaintFrame(pane);
            guiManager.getGuiFrontPage().getFrontPage(frame);
        });

    }
}
