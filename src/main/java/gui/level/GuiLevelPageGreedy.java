package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
    Font smallFont = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
    Font bigFont = new Font("Arial", Font.BOLD + Font.ITALIC, 30);
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
      JPanel availableItems = new JPanel(new GridLayout(1, 2));
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
    pane.setLayout(new GridLayout(1, 3));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    assert url != null;
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(300, 700,
            java.awt.Image.SCALE_SMOOTH);

    //füge Räuber ein
    URL urlRobber = getClass().getClassLoader().getResource("DiebGrauMitSack.png");
    assert urlRobber != null;
    ImageIcon robberImage = new ImageIcon(urlRobber);
    Image scaledRobberImage = robberImage.getImage().getScaledInstance(100, 200,
      Image.SCALE_SMOOTH);

    JPanel centerPanel = new JbackgroundPanel(scaledRobberImage, 120, 50);
    JPanel leftPanel = new JbackgroundPanel(scaledRucksackImage, 0, 0);
    JPanel rightPanel = new JPanel();

    // erzeuge Buttons
    this.escapeButton(centerPanel);
    if (GuiOptionsPage.greedyTipsAllowed) {
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
