package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;
import rucksack.Item;

/**
 * Item Panel have 1 Container with a Button that opens the
 * Image Selection if pressed.
 * As well as 1 Container with the 4 JPanel, JFormattedTextField pairs for
 * name , weight, value, amount
 */
public class ItemPanel extends Container {
  /**
   * the current Icon.
   */
  private ImageIcon icon;
  /**
   * the name field.
   */
  private final JFormattedTextField nameField;
  /**
   * the weight field.
   */
  private final JFormattedTextField weightField;
  /**
   * the value field.
   */
  private final JFormattedTextField valueField;
  /**
   * the amount field.
   */
  private final JFormattedTextField amountField;
  /**
   * the container.
   */
  private final Container myContainer;

  /**
   * Makes a new Item Panel.
   * Item Panel have 1 Container with a Button that opens the
   * Image Selection if pressed
   * As well as 1 Container with the 4 JPanel, JFormattedTextField pairs for
   * name , weight, value, amount
   */
  public ItemPanel() {
    myContainer = new Container();
    myContainer.setLayout(new BorderLayout());
    Container itemInfoPane = new Container();
    itemInfoPane.setLayout(new GridLayout(4, 2));

    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);

    JLabel name = new JLabel("Bezeichnung: ");
    itemInfoPane.add(name);
    nameField = new JFormattedTextField("");
    itemInfoPane.add(nameField);
    JLabel weight = new JLabel("Weight: ");
    itemInfoPane.add(weight);
    weightField = new JFormattedTextField(formatter);
    itemInfoPane.add(weightField);
    JLabel value = new JLabel("Wert: ");
    itemInfoPane.add(value);
    valueField = new JFormattedTextField(formatter);
    itemInfoPane.add(valueField);
    JLabel amount = new JLabel("Anzahl: ");
    itemInfoPane.add(amount);
    amountField = new JFormattedTextField(formatter);
    itemInfoPane.add(amountField);

    myContainer.add(itemInfoPane, BorderLayout.EAST);

    JButton iconSelector = new JButton();
    iconSelector.setIcon(new ImageIcon("Rucksack.jpeg"));
    myContainer.add(iconSelector);
  }

  /**
   * gets the Panel.
   *
   * @return returns the panel
   */
  public Container getPanel() {
    return myContainer;
  }

  /**
   * turns the itemPanel into an Item.
   *
   * @return returns the Item as described by the textFields
   * @throws NullPointerException throws a Null Pointer Exception if any of the
   *                              fields are empty
   */
  public Item generateItem() throws NullPointerException {
    int ignoreResult = Integer.parseInt(amountField.getValue().toString());
    return new Item(Integer.parseInt(valueField.getValue().toString()),
        Integer.parseInt(weightField.getValue().toString()),
        nameField.getValue().toString());
  }

  /**
   * gets the amountField.
   *
   * @return returns the amount specified in the AmountField
   */
  public int getAmount() {
    return Integer.parseInt(amountField.getValue().toString());
  }
}
