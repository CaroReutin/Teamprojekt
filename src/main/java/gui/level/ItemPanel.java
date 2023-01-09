package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.text.NumberFormat;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import org.apache.commons.io.FileUtils;
import rucksack.Item;
import solving.AppData;

/**
 * Item Panel have 1 Container with a Button that opens the
 * Image Selection if pressed.
 * As well as 1 Container with the 4 JPanel, JFormattedTextField pairs for
 * name , weight, value, amount
 */
public class ItemPanel extends Container {
  /**
   * the index for the picture.
   */
  private final int index;
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
   * the parent panel.
   */
  private final Container parent;

  /**
   * Makes a new Item Panel.
   * Item Panel have 1 Container with a Button that opens the
   * Image Selection if pressed
   * As well as 1 Container with the 4 JPanel, JFormattedTextField pairs for
   * name , weight, value, amount
   *
   * @param myIndex  the index of the panel
   * @param myParent the parent container
   */
  public ItemPanel(final int myIndex, final Container myParent) {
    parent = myParent;
    index = myIndex;
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
    icon = new ImageIcon(Objects.requireNonNull(getClass()
        .getResource("/RucksackPNG.png")));
    iconSelector.setIcon(icon);
    JFileChooser chooseIcon = new JFileChooser();
    chooseIcon.setCurrentDirectory(new java.io.File("."));
    chooseIcon.setDialogTitle("Speicherordner");
    chooseIcon.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileFilter imageFilter = new FileNameExtensionFilter(
        "Image files", ImageIO.getReaderFileSuffixes());
    chooseIcon.setFileFilter(imageFilter);
    iconSelector.addActionListener(e -> {
      if (chooseIcon.showOpenDialog(parent) == JFileChooser
          .APPROVE_OPTION) {
        try {
          icon = new ImageIcon(chooseIcon.getSelectedFile().getAbsolutePath());
          File destination = new File(AppData
              .getCustomLevelPictureFolder() + "/picture" + index);
          if (destination.exists()) {
            FileUtils.delete(destination);
          }
          FileUtils.copyFile(chooseIcon.getSelectedFile(), destination);
          iconSelector.setIcon(icon);
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    });
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
    if (nameField.getValue().toString().equals("")
        || amountField.getValue().toString().equals("")
        || weightField.getValue().toString().equals("")) {
      throw new NullPointerException("No field may be empty");
    }
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
