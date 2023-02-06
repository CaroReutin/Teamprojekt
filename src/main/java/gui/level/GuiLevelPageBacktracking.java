package gui.level;

import backtrackingtree.BacktrackingTree;
import betatree.Tree;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import rucksack.BacktrackingItem;
import rucksack.Level;
import solving.ButtonEventHandler;
import solving.ButtonEventHandlerTable;


/**
 * backtracking level pages.
 */
public class GuiLevelPageBacktracking extends GuiLevelPage {
  /**
   * the beta tree.
   */
  private Tree betaTree;
  /**
   * the Button Event Handler.
   */
  private ButtonEventHandler buttonHandler;
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
   * number of items in Label
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
    buttonHandler = new ButtonEventHandlerTable(level);
  }

  /**
   * makes the Item Buttons.
   *
   * @param panelAvaible  The right panel where the buttons for
   *                      the items NOT IN the bag should go to.
   * @param panelRucksack The left panel where the buttons for
   *                      the items IN the bag should go to.
   * @param panelRucksack The right pannel where rhe buttons fpr
   *                      the items in trash are
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
      // State of current item
      BacktrackingItem.StateBacktracking stateItem = items.get(finalI).getState();
      //Avaible Labels
      JLabel itemLabel = new JLabel(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
          + items.get(i).getValue() + "€), Anzahl: ");
      //JLabel itemLabelAmount = new JLabel(labels[i].getText());
      itemLabel.setBackground(Color.white);

      //buttons rucksack
      JButton putToRucksack = new JButton("lege in den Rucksack");
      putToRucksack.addActionListener(e -> {
        // getLevel().setCounter(getLevel().getCounter() + 1);
        /*
        boolean sucsessRucksack = backtrackingTree.addToRucksack(items.get(finalI));
        if(sucsessRucksack) {
          getLevel().setInRucksackAmountList(finalI, getLevel().getInRucksackAmountList().get(finalI) + 1);
          getLevel().setAvailableItemAmountList(finalI, getLevel().getItemAmountAvailable(finalI) - 1);
        }*/
        buttonHandler.addToRucksack(finalI);
        updateLabel(finalI);
      });
      //Trash Buttons and Labels
      trashLabels[i] = new JLabel(getLevel().getInTrashAmountList().get(i).toString());

      Font ft = trashLabels[i].getFont();
      trashLabels[i].setFont(ft.deriveFont((f.getStyle() | Font.BOLD)));

//buttons trash
      JButton putToTrash = new JButton("lege in den Müll");
      putToTrash.addActionListener(e -> {
        //getLevel().setCounter(getLevel().getCounter() + 1);
        /*
        boolean sucsessTrash = backtrackingTree.addToTrash(items.get(finalI));
        if (sucsessTrash) {
          getLevel().setInTrashAmountList(finalI,
              (getLevel().getInTrashAmountList().get(finalI) + 1));
          if (stateItem.equals(BacktrackingItem.StateBacktracking.RUCKSACK)) {
            getLevel().setInRucksackAmountList(finalI,
                (getLevel().getItemAmountInRucksack(finalI) - 1));
          } else if (stateItem.equals(BacktrackingItem.StateBacktracking.AVAILABLE)) {
            getLevel().setAvailableItemAmountList(finalI,
                getLevel().getItemAmountAvailable(finalI) - 1);
          }
        }
         */
        buttonHandler.addToTrash(finalI);
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
        // TODO Never does anything ? (not a todo but todo highlights)
        if (getLevel().getItemAmountInRucksack(finalI) <= 0) {
          return;
        }
        if (!getLevel().getRobber().equals(Level.Robber.GIERIGER_GANOVE)) {
          getLevel().moveFromRucksack(finalI);
          updateLabel(finalI);
        }

      });

      JButton currentTrash = new JButton(items.get(i).getName());
      currentRucksack.addActionListener(e -> {
        getLevel().setCounter(getLevel().getCounter() + 1);
        buttonHandler.addToTrash(finalI);
        updateLabel(finalI);
      });
      //panelItems.add(current);
      //panelItems.add(labels[i]);
      panelAvaible.add(itemLabel);
      panelAvaible.add(labels[i]);
      JLabel itemControlLabel =
          new JLabel(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, "
              + items.get(i).getValue() + "€)");
      controlPannel.add(itemControlLabel);
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
    JPanel avaiblePanel = new JPanel(new GridLayout(getLevel().getItemList().size() + 1, 2));
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

    //add Counter
   /* JPanel counterPanel = new JPanel();
    counterPanel.add(new JLabel("Schrittzähler: "));
    counterPanel.add(new JLabel(Integer.toString(getLevel().getCounter())));
    centerPanel.add(counterPanel);*/

    this.itemButtons(avaiblePanel, rucksackPanel, trashPanel, controlPannel);


    //alles zusammenpuzzeln

    JButton treeButton = new JButton("Zeige Baum");
    treeButton.addActionListener(e -> buttonHandler.show());
    // To Test until GUI works.
    GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
            "add");
    GuiManager.getRootPane().getActionMap()
        .put("add", new AbstractAction() {
          @Override
          public void actionPerformed(final ActionEvent e) {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Index:"));
            buttonHandler.addToRucksack(index);
            updateLabel(index);
          }
        });
    GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
            "trash");
    GuiManager.getRootPane().getActionMap()
        .put("trash", new AbstractAction() {
          @Override
          public void actionPerformed(final ActionEvent e) {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Index:"));
            buttonHandler.addToTrash(index);
            updateLabel(index);
          }
        });
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
    trashLabels[i].setText(String.valueOf(getLevel().getInTrashAmountList().get(i)));
    rucksackLabels[i].setText(String.valueOf(getLevel()
        .getItemAmountInRucksack(i)));
    currentWeightLabel.setText(
        getLevel().getCurrentWeight() + "/" + getLevel().getCapacity() + "g");
    currentValueLabel.setText((getLevel().getCurrentValue() + "€"));
  }
}
