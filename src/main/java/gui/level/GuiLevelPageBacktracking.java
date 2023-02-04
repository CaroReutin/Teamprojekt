package gui.level;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backtrackingtree.BacktrackingNode;
import backtrackingtree.BacktrackingTree;
import rucksack.BacktrackingItem;
import rucksack.Item;
import rucksack.Level;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {

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
   * number of items in Label
   */
  private JLabel[] trashLabels;
  /**
   * the current weight of the label.
   */
  private JLabel currentWeightLabel;

  private BacktrackingTree backtrackingTree = new BacktrackingTree(getLevel().getCapacity(), getLevel().getBacktrackingItemList());

  /**
   * make a new backtracking level page.
   *
   * @param level the level
   */
  public GuiLevelPageBacktracking(final Level level) {
    super(level);
    getLevel().turnIntoBacktracking();
  }


  /**
   * makes the Item Buttons.
   *
   * @param panelAvaible    The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   * @param panelRucksack The right pannel where rhe buttons fpr
   *                      the items in trash are
   */

  public void itemButtons(final JPanel panelAvaible, final JPanel panelRucksack, final JPanel pannelTrash,
                          final JPanel controlPannel) {
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

    //getLevel().turnIntoBacktracking();
    ArrayList<BacktrackingItem> items = getLevel().getBacktrackingItemList();
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
      JLabel itemLabel = new JLabel(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
        + items.get(i).getValue() + "€), Anzahl: " + labels[i].getText());
      itemLabel.setBackground(Color.white);
      JButton putToRucksack = new JButton("lege in den Rucksack");
      putToRucksack.addActionListener(e -> {
       // getLevel().setCounter(getLevel().getCounter() + 1);
        backtrackingTree.addToRucksack(items.get(finalI));
        updateLabel(finalI);
      });
      //Trash Buttons and Labels
      trashLabels = new JLabel[items.size()];
      trashLabels[i] = new JLabel(getLevel().getInTrashAmountList().get(i).toString());

      Font ft = trashLabels[i].getFont();
      trashLabels[i].setFont(ft.deriveFont((f.getStyle() | Font.BOLD)));


      JButton putToTrash = new JButton("lege in den Müll");
      putToTrash.addActionListener(e -> {
        //getLevel().setCounter(getLevel().getCounter() + 1);
        backtrackingTree.addToTrash(items.get(finalI));
        updateLabel(finalI);
      });
      /*JButton current = new JButton(items.get(i).getName()
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
      });*/
      JButton currentRucksack = new JButton(items.get(i).getName());
      currentRucksack.addActionListener(e -> {
        if (getLevel().getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (getLevel().getRobber().equals(Level.Robber.BACKTRACKING_BANDIT)) {
          getLevel().moveFromRucksack(finalI);
          updateLabel(finalI);
        }

      });

      JButton currentTrash = new JButton(items.get(i).getName());
      currentRucksack.addActionListener(e -> {
          getLevel().setCounter(getLevel().getCounter() + 1);
          backtrackingTree.addToTrash(items.get(finalI));
          updateLabel(finalI);
        });
      //panelItems.add(current);
      //panelItems.add(labels[i]);
      panelAvaible.add(itemLabel);
      JLabel itemAvaibleLabel = new JLabel(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
        + items.get(i).getValue() + "€)");
      controlPannel.add(itemAvaibleLabel);
      controlPannel.add(putToRucksack);
      controlPannel.add(putToRucksack);
      controlPannel.add(putToTrash);

      pannelTrash.add(currentTrash);
      pannelTrash.add(trashLabels[i]);

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
   // Panel were availble items are
    JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    JPanel avaiblePanel = new JPanel( new GridLayout(getLevel().getItemList().size() + 1, 1));
    avaiblePanel.add(new JLabel("Verfügbar:"));
    // Panel with buttons were you can add items to rucksack or trash
    JPanel controlPannel = new JPanel();
    controlPannel.add(new JLabel("Kontrollfläche: "));
    rightPanel.add(avaiblePanel, BorderLayout.NORTH);
    rightPanel.add(controlPannel, BorderLayout.SOUTH);


    // Rucksack Panel
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(170, 300,
            java.awt.Image.SCALE_SMOOTH);
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    JPanel rucksackPanel =  new JbackgroundPanel(scaledRucksackImage, 0);
    rucksackPanel.add(new JLabel("Rucksack:"));
    JPanel trashPanel = new JPanel();

    //Trash Panel
    trashPanel.add(new JLabel("Müll:"));
    leftPanel.add(rucksackPanel, BorderLayout.NORTH);
    leftPanel.add(trashPanel, BorderLayout.SOUTH);

    //Panel where escape-Button is
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    this.escapeButton(centerPanel);

    //add Counter
   /* JPanel counterPanel = new JPanel();
    counterPanel.add(new JLabel("Schrittzähler: "));
    counterPanel.add(new JLabel(Integer.toString(getLevel().getCounter())));
    centerPanel.add(counterPanel);*/

    this.itemButtons(avaiblePanel, rucksackPanel, trashPanel, controlPannel);




    //alles zusammenpuzzeln

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
    //trashLabels[i].setText(String.valueOf(getLevel().getInTrashAmountList().get(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel()
        .getItemAmountInRucksack(i)));
    currentWeightLabel.setText(
        getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }
}
