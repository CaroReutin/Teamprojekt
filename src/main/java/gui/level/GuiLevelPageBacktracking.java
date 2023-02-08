package gui.level;

import java.awt.BorderLayout;
import java.awt.Color;
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
import rucksack.BacktrackingItem;
import rucksack.Level;
import solving.ButtonEventHandler;
import solving.ButtonEventHandlerTable;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {
  /**
   * the Button Event Handler.
   */
  private final ButtonEventHandler buttonHandler;
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
   * number of items in Label.
   */
  private JLabel[] trashLabels;
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
    getLevel().turnIntoBacktracking();
    buttonHandler = new ButtonEventHandlerTable(getLevel());
  }

  /**
   * makes the Item Buttons.
   *
   * @param panelAvaible  The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   * @param pannelTrash The trash panel.
   * @param controlPannel The control panel.
   */

  public void itemButtons(final JPanel panelAvaible, final JPanel panelRucksack,
                          final JPanel pannelTrash,
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
    trashLabels = new JLabel[items.size()];
    for (int i = 0; i < items.size(); i++) {
      labels[i] = new JLabel(getLevel().getItemAmountList().get(i).toString());

      Font f = labels[i].getFont();
      labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

      rucksackLabels[i] = new JLabel("0");
      Font fontRucksack = rucksackLabels[i].getFont();
      rucksackLabels[i].setFont(fontRucksack.deriveFont(fontRucksack.getStyle()
          | Font.BOLD));
      // Laufvariable
      int finalI = i;
      BacktrackingItem item = items.get(i);
      //Available Labels
      JLabel itemIcon = new JLabel(items.get(i).getImageIcon());
      JLabel itemLabel = new JLabel(items.get(i)
          .getName() + " (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€), Anzahl: ");
      //JLabel itemLabelAmount = new JLabel(labels[i].getText());
      itemLabel.setBackground(Color.white);

      //buttons rucksack
      JButton putToRucksack = new JButton("lege in den Rucksack");
      putToRucksack.addActionListener(e -> handleButtons(finalI, true));
      //Trash Buttons and Labels
      trashLabels[i] = new JLabel(getLevel().getInTrashAmountList()
          .get(i).toString());

      Font ft = trashLabels[i].getFont();
      trashLabels[i].setFont(ft.deriveFont((f.getStyle() | Font.BOLD)));

      //buttons trash
      JButton putToTrash = new JButton("lege in den Müll");
      putToTrash.addActionListener(e -> handleButtons(finalI, false));
      JButton currentRucksack = new JButton(items.get(i).getImageIcon());
      currentRucksack.addActionListener(e -> handleButtons(finalI, false));

      panelAvaible.add(itemIcon);
      panelAvaible.add(itemLabel);
      panelAvaible.add(labels[i]);
      JLabel itemControlLabelIcon = new JLabel(items.get(i).getImageIcon());
      JLabel itemControlLabel =
          new JLabel(items.get(i).getName() + " ("
              + items.get(i).getWeight() + "g, "
              + items.get(i).getValue() + "€)");
      controlPannel.add(itemControlLabelIcon);
      controlPannel.add(itemControlLabel);
      controlPannel.add(putToRucksack);
      controlPannel.add(putToTrash);

      JButton currentTrash = new JButton(items.get(i).getImageIcon());
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
    // Panel were available items are
    JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    JPanel avaiblePanel = new JPanel(new GridLayout(
        getLevel().getItemList().size() + 1, 2));
    avaiblePanel.add(new JLabel("Verfügbar:"));
    // Panel with buttons were you can add items to rucksack or trash
    JPanel controlPannel = new JPanel();
    controlPannel.add(new JLabel("Kontrollfläche: "));
    rightPanel.add(avaiblePanel, BorderLayout.NORTH);
    rightPanel.add(controlPannel, BorderLayout.SOUTH);


    // Rucksack Panel
    ImageIcon rucksackImage = new ImageIcon(url);
    Image scaledRucksackImage =
        rucksackImage.getImage().getScaledInstance(200, 350,
            java.awt.Image.SCALE_SMOOTH);
    JPanel leftPanel = new JPanel(new GridLayout(2, 1));
    JPanel rucksackPanel = new JbackgroundPanel(scaledRucksackImage, 0);
    rucksackPanel.add(new JLabel("Rucksack:"));
    JPanel trashPanel = new JPanel();

    //Trash Panel
    trashPanel.add(new JLabel("Müll:"));
    leftPanel.add(rucksackPanel, BorderLayout.NORTH);
    leftPanel.add(trashPanel, BorderLayout.SOUTH);

    //Panel where escape-Button is
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    this.escapeButton(centerPanel);

    this.itemButtons(avaiblePanel, rucksackPanel, trashPanel, controlPannel);


    //alles zusammenpuzzeln
    JButton treeButton = new JButton("Zeige Baum");
    treeButton.addActionListener(e -> buttonHandler.show());
    centerPanel.add(treeButton);
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
    trashLabels[i].setText(String.valueOf(getLevel()
        .getInTrashAmountList().get(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel()
        .getItemAmountInRucksack(i)));
    currentWeightLabel.setText(
        getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }

  private void handleButtons(final int index,
                             final boolean toRucksack) {
    if (toRucksack) {
      buttonHandler.addToRucksack(index, getLevel());
      updateLabel(index);
    } else {
      buttonHandler.addToTrash(index, getLevel());
      for (int i = index; i < getLevel()
          .getBacktrackingItemList().size(); i++) {
        updateLabel(i);
      }
    }
  }
}
