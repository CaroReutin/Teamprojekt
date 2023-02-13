package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rucksack.Item;
import rucksack.Level;

/**
 * greedy level pages.
 */
public class GuiLevelPageGreedy extends GuiLevelPage {
  /**
   * the font size 15.
   */
  private static final int FONT_FIFTEEN = 15;
  /**
   * the font size 30.
   */
  private static final int FONT_THIRTY = 30;
  /**
   * the grid size 3.
   */
  private static final int GRID_THREE = 3;
  /**
   * the width of the rucksack.
   */
  private static final int WIDTH_RUCKSACK = 300;
  /**
   * the height of the rucksack.
   */
  private static final int HEIGHT_RUCKSACK = 700;
  /**
   * the width of the robber.
   */
  private static final int WIDTH_ROBBER = 100;
  /**
   * the height of the robber.
   */
  private static final int HEIGHT_ROBBER = 200;
  /**
   * the x position of the robber on the window.
   */
  private static final int X_POS_ROBBER = 120;
  /**
   * the y position of the robber on the window.
   */
  private static final int Y_POS_ROBBER = 50;
  /**
   * the labels of the page.
   */
  private JLabel[] labels;
  /**
   * the label of the rucksack.
   */
  private JLabel[] rucksackLabels;
  /**
   * the current value of the label.
   */
  private JLabel currentValueLabel;
  /**
   * the current weight of the label.
   */
  private JLabel currentWeightLabel;

  /**
   * make a new greedy level page.
   *
   * @param level the level
   */
  public GuiLevelPageGreedy(final Level level) {
    super(level);
  }

  /**
   * makes the Item Buttons.
   *
   * @param panelItems    The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   */
  @Override
  public void itemButtons(final JPanel panelItems,
                          final JPanel panelRucksack) {
    Font smallFont = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_FIFTEEN);
    Font bigFont = new Font("Arial", Font.BOLD + Font.ITALIC, FONT_THIRTY);
    currentWeightLabel = new JLabel("0/" + getLevel().getCapacity() + "g");
    currentWeightLabel.setFont(bigFont);

    currentValueLabel = new JLabel("0€");
    currentValueLabel.setFont(bigFont);

    ArrayList<Item> items = getLevel().getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());
      labels[i].setFont(smallFont);

      rucksackLabels[i] = new JLabel("0");
      rucksackLabels[i].setFont(smallFont);
      int finalI = i;
      ImageIcon currentIcon = items.get(i).getImageIcon();
      JButton currentItemIcon = new JButton(currentIcon);
      JLabel current = new JLabel(" (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
      current.setFont(smallFont);
      currentItemIcon.addActionListener(e -> {
        if (getLevel().getItemAmountAvailable(finalI) <= 0) {
          return;
        }
        if ((getLevel().getCurrentWeight() + getLevel().getItemList()
            .get(finalI).getWeight()) <= getLevel().getCapacity()) {
          getLevel().moveToRucksack(finalI);
          updateLabel(finalI);
        }
      });
      JButton currentRucksackIcon = new JButton(currentIcon);
      currentRucksackIcon.addActionListener(e -> {
        if (getLevel().getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (!getLevel().getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          getLevel().moveFromRucksack(finalI);
          updateLabel(finalI);
        }

      });

      JPanel availableItems = new JPanel(new GridLayout(1, 2));
      availableItems.add(currentItemIcon);
      availableItems.add(current);
      availableItems.add(labels[i]);
      panelItems.add(availableItems);
      panelRucksack.add(currentRucksackIcon);
      panelRucksack.add(rucksackLabels[i]);
      panelRucksack.add(currentWeightLabel);
      panelRucksack.add(currentValueLabel);

    }
  }

  /**
   * returns the pane for the greedy Gui level page.
   *
   * @return the pane
   */
  @Override
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, GRID_THREE));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(WIDTH_RUCKSACK,
          HEIGHT_RUCKSACK, java.awt.Image.SCALE_SMOOTH);

    //füge Räuber ein
    URL urlRobber = getClass().getClassLoader().getResource(
            "DiebGrauMitSack.png");
    assert urlRobber != null;
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(
            WIDTH_ROBBER, HEIGHT_ROBBER, Image.SCALE_SMOOTH);

    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage, X_POS_ROBBER,
        Y_POS_ROBBER);
    JPanel leftPanel = new JbackgroundPanel(scaledRucksackImage, 0, 0);
    JPanel rightPanel = new JPanel();

    // erzeuge Buttons
    this.escapeButton(centerPanel);
    if (GuiOptionsPage.getGreedyTipsAllowed()) {
      this.clueButton(centerPanel);
    }
    this.itemButtons(rightPanel, leftPanel);

    //alles zusammenpuzzeln

    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);

    return pane;

  }

  /**
   * updates Labels.
   *
   * @param i the index of the labels to update
   */
  @Override
  public void updateLabel(final int i) {
    labels[i].setText(String.valueOf(getLevel().getItemAmountAvailable(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel()
        .getItemAmountInRucksack(i)));
    currentWeightLabel.setText(
        getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }
}
