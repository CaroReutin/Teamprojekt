package gui.level;

import rucksack.Item;
import rucksack.Level;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class GuiLevelPageBacktracking extends GuiLevelPage{
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

  public GuiLevelPageBacktracking(Level level) {
    super(level);
  }

  @Override
  public void itemButtons(JPanel panelItems, JPanel panelRucksack) {
    currentWeightLabel = new JLabel("0/" + getLevel().getCapacity() + "g");
    Font fontCurrentWeightLabel = currentWeightLabel.getFont();
    currentWeightLabel.setFont(fontCurrentWeightLabel.deriveFont(fontCurrentWeightLabel.getStyle() | Font.BOLD));

    currentValueLabel = new JLabel("0€");
    Font fontCurrentValueLabel = currentValueLabel.getFont();
    currentValueLabel.setFont(fontCurrentValueLabel.deriveFont(fontCurrentValueLabel.getStyle() | Font.BOLD));

    ArrayList<Item> items = getLevel().getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      int finalI = i;
      JButton current = new JButton(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
        + items.get(i).getValue() + "€)");
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());

      Font f = labels[i].getFont();
      labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

      JButton currentRucksack = new JButton(items.get(i).getName());
      rucksackLabels[i] = new JLabel("0");
      Font fontRucksack = rucksackLabels[i].getFont();
      rucksackLabels[i].setFont(fontRucksack.deriveFont(fontRucksack.getStyle() | Font.BOLD));
      current.addActionListener(e -> {
        if (getLevel().getItemAmountAvailable(finalI) <= 0) {
          return;
        }
        if ((getLevel().getCurrentWeight() + getLevel().getItemList().get(finalI).getWeight()) <= getLevel().getCapacity()) {
          getLevel().moveToRucksack(finalI);
          updateLabel(finalI);
        }
      });
      currentRucksack.addActionListener(e -> {
        if (getLevel().getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (!getLevel().getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          getLevel().moveFromRucksack(finalI);
          updateLabel(finalI);
        }

      });
      panelItems.add(current);
      panelItems.add(labels[i]);
      panelRucksack.add(currentRucksack);
      panelRucksack.add(rucksackLabels[i]);
      panelRucksack.add(currentWeightLabel);
      panelRucksack.add(currentValueLabel);

    }
  }

  @Override
  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, 3));

    //Füge Rucksack png ein und ändere größe
    URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage = rucksackImage.getImage().getScaledInstance(170, 300, java.awt.Image.SCALE_SMOOTH);


    JPanel leftPanel = new JBackgroundPanel(scaledRucksackImage);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel rightPanel = new JPanel();
    //JPanel rightPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));

    // erzeuge Buttons
    this.escapeButton(centerPanel);
    this.itemButtons(rightPanel, leftPanel);


    //alles zusammenpuzzeln

    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);

    return pane;

  }

  @Override
  public void updateLabel(int i) {
    labels[i].setText(String.valueOf(getLevel().getItemAmountAvailable(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel().getItemAmountInRucksack(i)));
    currentWeightLabel.setText(getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }
}
