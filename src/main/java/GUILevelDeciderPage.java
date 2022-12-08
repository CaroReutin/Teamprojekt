import javax.swing.*;
import java.awt.*;

public class GUILevelDeciderPage {

    public void openLevelDeciderPage() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Frontpage");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Level");
        titel.setBounds(200, 20, 300, 40);
        titel.setFont(fontStyle);
        pane.add(titel);

        JButton levelOne = new JButton("1");
        levelOne.setBounds(25, 200, 50, 50);
        pane.add(levelOne);
        levelOne.addActionListener(e -> {

        });

        JLabel ganove = new JLabel("gieriger Ganove");
        ganove.setBounds(150, 80, 300, 40);
        ganove.setFont(fontStyle);
        pane.add(ganove);

        JButton levelTwoGreedy = new JButton("2");
        levelTwoGreedy.setBounds(100, 125, 50, 50);
        pane.add(levelTwoGreedy);
        levelTwoGreedy.addActionListener(e -> {

        });

        JButton levelThreeGreedy = new JButton("3");
        levelThreeGreedy.setBounds(160, 125, 50, 50);
        pane.add(levelThreeGreedy);
        levelThreeGreedy.addActionListener(e -> {

        });


        JButton levelFourGreedy = new JButton("4");
        levelFourGreedy.setBounds(220, 125, 50, 50);
        pane.add(levelFourGreedy);
        levelFourGreedy.addActionListener(e -> {

        });

        JButton levelFiveGreedy = new JButton("5");
        levelFiveGreedy.setBounds(280, 125, 50, 50);
        pane.add(levelFiveGreedy);
        levelFiveGreedy.addActionListener(e -> {

        });

        JButton levelSixGreedy = new JButton("6");
        levelSixGreedy.setBounds(340, 125, 50, 50);
        pane.add(levelSixGreedy);
        levelSixGreedy.addActionListener(e -> {

        });

        JButton levelSevenGreedy = new JButton("7");
        levelSevenGreedy.setBounds(400, 125, 50, 50);
        pane.add(levelSevenGreedy);
        levelSevenGreedy.addActionListener(e -> {

        });

        JLabel bandit = new JLabel("backtracking Bandit");
        bandit.setBounds(130, 235, 300, 40);
        bandit.setFont(fontStyle);
        pane.add(bandit);

        JButton levelTwoBack = new JButton("2");
        levelTwoBack.setBounds(100, 275, 50, 50);
        pane.add(levelTwoBack);
        levelTwoBack.addActionListener(e -> {

        });

        JButton levelThreeBack = new JButton("3");
        levelThreeBack.setBounds(160, 275, 50, 50);
        pane.add(levelThreeBack);
        levelThreeBack.addActionListener(e -> {

        });

        JButton levelFourBack = new JButton("4");
        levelFourBack.setBounds(220, 275, 50, 50);
        pane.add(levelFourBack);
        levelFourBack.addActionListener(e -> {

        });

        JButton levelFiveBack = new JButton("2");
        levelFiveBack.setBounds(280, 275, 50, 50);
        pane.add(levelFiveBack);
        levelFiveBack.addActionListener(e -> {

        });

        JButton levelSixBack = new JButton("2");
        levelSixBack.setBounds(340, 275, 50, 50);
        pane.add(levelSixBack);
        levelSixBack.addActionListener(e -> {

        });

        JButton levelSevenBack = new JButton("2");
        levelSevenBack.setBounds(400, 275, 50, 50);
        pane.add(levelSevenBack);
        levelSevenBack.addActionListener(e -> {

        });


        //Test Sternbutton
        JButton levelTestStern = new JButton("1");
        levelTestStern.setIcon(new ImageIcon("src/main/resources/stern.png"));
        levelTestStern.setBounds(400, 400, 50, 50);
        levelTestStern.setBorder(null);
        levelTestStern.setBackground(Color.getHSBColor(0,0,93));
        pane.add(levelTestStern);
        levelTestStern.addActionListener(e -> {
            //Methode die aufgerufen werden soll
        });








    }
}
