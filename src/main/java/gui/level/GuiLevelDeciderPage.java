
package gui.level;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    pane.setLayout(null);

    Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 50);
    Font fontButtons = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

    JLabel titel = new JLabel("Level");
    titel.setBounds(430, 50, 250, 60);
    titel.setFont(fontStyle);
    pane.add(titel);

    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      if (i == 0) {
        guiLevelPages[i] = new GuiLevelPage(AppData.getLevel(i));
        guiLevelButtons[i] = new JButton("1");
      } else if (i <= 7) {
        guiLevelPages[i] = new GuiLevelPageGreedy(AppData
            .initializeGreedy(i - 1));
        guiLevelButtons[i] = new JButton(Integer.toString(i + 1));
      } else {
        guiLevelPages[i] = new GuiLevelPageBacktracking(AppData
            .initializeBacktracking(i - 8));
        guiLevelButtons[i] = new JButton(Integer.toString(i - 6));
      }
      guiLevelButtons[i].setBackground(Color.cyan);
      guiLevelButtons[i].setFont(fontButtons);
    }

    guiLevelButtons[0].setBounds(120, 300, 60, 60);
    pane.add(guiLevelButtons[0]);
    guiLevelButtons[0].addActionListener(e ->
        GuiManager.openLevel(guiLevelPages[0]));

    JLabel ganove = new JLabel("gieriger Ganove");
    ganove.setBounds(280, 120, 400, 60);
    ganove.setFont(fontStyle);
    pane.add(ganove);

    guiLevelButtons[1].setBounds(260, 210, 60, 60);
    pane.add(guiLevelButtons[1]);
    guiLevelButtons[1].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[1]);
    });

    guiLevelButtons[2].setBounds(340, 210, 60, 60);
    pane.add(guiLevelButtons[2]);
    guiLevelButtons[2].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[2]);
    });

    guiLevelButtons[3].setBounds(420, 210, 60, 60);
    pane.add(guiLevelButtons[3]);
    guiLevelButtons[3].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[3]);
    });

    guiLevelButtons[4].setBounds(500, 210, 60, 60);
    pane.add(guiLevelButtons[4]);
    guiLevelButtons[4].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[4]);
    });

    guiLevelButtons[5].setBounds(580, 210, 60, 60);
    pane.add(guiLevelButtons[5]);
    guiLevelButtons[5].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[5]);
    });

    guiLevelButtons[6].setBounds(660, 210, 60, 60);
    pane.add(guiLevelButtons[6]);
    guiLevelButtons[6].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[6]);
    });

    guiLevelButtons[7].setBounds(740, 210, 60, 60);
    pane.add(guiLevelButtons[7]);
    guiLevelButtons[7].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[7]);
    });

    JLabel bandit = new JLabel("backtracking Bandit");
    bandit.setBounds(250, 300, 500, 60);
    bandit.setFont(fontStyle);
    pane.add(bandit);

    guiLevelButtons[8].setBounds(260, 390, 60, 60);
    pane.add(guiLevelButtons[8]);
    guiLevelButtons[8].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[8]);
    });

    guiLevelButtons[9].setBounds(340, 390, 60, 60);
    pane.add(guiLevelButtons[9]);
    guiLevelButtons[9].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[9]);
    });

    guiLevelButtons[10].setBounds(420, 390, 60, 60);
    pane.add(guiLevelButtons[10]);
    guiLevelButtons[10].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[10]);
    });

    guiLevelButtons[11].setBounds(500, 390, 60, 60);
    pane.add(guiLevelButtons[11]);
    guiLevelButtons[11].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[11]);
    });

    guiLevelButtons[12].setBounds(580, 390, 60, 60);
    pane.add(guiLevelButtons[12]);
    guiLevelButtons[12].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[12]);
    });

    guiLevelButtons[13].setBounds(660, 390, 60, 60);
    pane.add(guiLevelButtons[13]);
    guiLevelButtons[13].addActionListener(e -> {
      GuiManager.openLevel(guiLevelPages[13]);
    });

    guiLevelButtons[14].setBounds(740, 390, 60, 60);
    pane.add(guiLevelButtons[14]);
    guiLevelButtons[14].addActionListener(e -> {
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
   *
   * @return the levelpage.
   */
  public GuiLevelPage[] getGuiLevelPages() {
    return guiLevelPages;
  }

}
