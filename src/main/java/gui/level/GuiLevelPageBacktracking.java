package gui.level;

import betatree.Tree;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import jtree.BacktrackingTree;
import rucksack.Item;
import rucksack.Level;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {
  /**
   * the beta tree.
   */
  private Tree betaTree;
  /**
   * the backtrackingTree (JTree).
   */
  private BacktrackingTree tree;

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
   * make a new backtracking level page.
   *
   * @param level the level
   */
  public GuiLevelPageBacktracking(final Level level) {
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
  public void itemButtons(final JPanel panelItems, final JPanel panelRucksack) {
    currentWeightLabel = new JLabel("0/" + getLevel().getCapacity() + "g");
    Font fontCurrentWeightLabel = currentWeightLabel.getFont();
    currentWeightLabel.setFont(
        fontCurrentWeightLabel.deriveFont(fontCurrentWeightLabel.getStyle()
            | Font.BOLD));

    currentValueLabel = new JLabel("0€");
    Font fontCurrentValueLabel = currentValueLabel.getFont();
    currentValueLabel.setFont(
        fontCurrentValueLabel.deriveFont(fontCurrentValueLabel.getStyle()
            | Font.BOLD));

    ArrayList<Item> items = getLevel().getItemList();
    labels = new JLabel[items.size()];
    rucksackLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());

      Font f = labels[i].getFont();
      labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

      rucksackLabels[i] = new JLabel("0");
      Font fontRucksack = rucksackLabels[i].getFont();
      rucksackLabels[i].setFont(fontRucksack.deriveFont(fontRucksack.getStyle()
          | Font.BOLD));
      int finalI = i;
      JButton current = new JButton(items.get(i).getName()
          + " (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€)");
      current.addActionListener(e -> {
        if (getLevel().getItemAmountAvailable(finalI) <= 0) {
          return;
        }
        if ((getLevel().getCurrentWeight() + getLevel().getItemList()
            .get(finalI).getWeight()) <= getLevel().getCapacity()) {
          getLevel().moveToRucksack(finalI);
          updateLabel(finalI);
        }
      });
      JButton currentRucksack = new JButton(items.get(i).getName());
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

  /**
   * returns the pane for the backtracking Gui level page.
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

    JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    JPanel avaiblePanel = new JPanel();
    avaiblePanel.add(new JLabel("Verfügbar:"));
    JPanel trashPanel = new JPanel();
    trashPanel.add(new JLabel("Müll:"));
    rightPanel.add(avaiblePanel, BorderLayout.NORTH);
    rightPanel.add(trashPanel, BorderLayout.SOUTH);


    //JPanel rightPanel = new JPanel(new GridLayout(
    // level.getItemList().size(), 1));

    // erzeuge Buttons
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(170, 300,
            java.awt.Image.SCALE_SMOOTH);
    JPanel leftPanel = new JbackgroundPanel(scaledRucksackImage);
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    this.escapeButton(centerPanel);
    this.itemButtons(avaiblePanel, leftPanel);


    //alles zusammenpuzzeln

    if (!GuiOptionsPage.BETA_TREE.isSelected()) {
      betaTree = new Tree(super.getLevel());
      JButton treeButton = new JButton("Zeige Baum");
      treeButton.addActionListener(e -> betaTree.show());
      // To Test until GUI works.
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
              "add");
      GuiManager.getRootPane().getActionMap()
          .put("add", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              betaTree.putInBag();
              System.out.println("Add");
            }
          });
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
              "rem");
      GuiManager.getRootPane().getActionMap()
          .put("rem", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              betaTree.back();
              System.out.println("Remove");
            }
          });
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
              "trash");
      GuiManager.getRootPane().getActionMap()
          .put("trash", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              betaTree.putInTrash();
              System.out.println("Trash");
            }
          });
      centerPanel.add(treeButton);
    } else {
      tree = new BacktrackingTree(super.getLevel());
      JButton treeButton = new JButton("Zeige Baum");
      treeButton.addActionListener(e -> tree.show());
      // To Test until GUI works.
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
              "add2");
      GuiManager.getRootPane().getActionMap()
          .put("add2", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              tree.putInBag();
              System.out.println("Add");
            }
          });
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
              "rem2");
      GuiManager.getRootPane().getActionMap()
          .put("rem2", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              tree.back();
              System.out.println("Remove");
            }
          });
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
              "trash2");
      GuiManager.getRootPane().getActionMap()
          .put("trash2", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              tree.putInTrash();
              System.out.println("Trash");
            }
          });
      centerPanel.add(treeButton);
    }
    pane.add(leftPanel, BorderLayout.WEST);
    pane.add(centerPanel, BorderLayout.CENTER);
    pane.add(rightPanel, BorderLayout.EAST);


    return pane;

  }

  /**
   * update labels.
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
