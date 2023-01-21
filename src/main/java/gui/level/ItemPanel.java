package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.text.NumberFormat;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    // TODO Sprint 4, Currently you cannot delete a number
    // if you type it into a field
    // So "4" -> "" -> "5" is not possible
    // You have to go "4" -> "45" -> "5"
    // Or select the 4 and overwrite it with 5
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(1);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);

    JLabel name = new JLabel("Bezeichnung: ");
    itemInfoPane.add(name);
    nameField = new JFormattedTextField("");
    itemInfoPane.add(nameField);
    JLabel weight = new JLabel("Gewicht: ");
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
        .getResource("/stern.png")));
    Image image = icon.getImage();
    icon = new ImageIcon(image.getScaledInstance(
        AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH));
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
          // Set image as this Panels Icon then move a copy to temp
          // The Index of the panel determines the pictures name
          // If Item 1 and 3 are filled out and have pictures
          // picture0 and picture2 will exist
          // accounting for the skipped item has to be handled when zipping
          Image readImage = ImageIO.read(new File(chooseIcon
              .getSelectedFile().getAbsolutePath()));
          if (readImage == null) {
            JOptionPane.showMessageDialog(parent,
                "Es gab ein Problem beim einfügen des Bildes.\n"
                    + "Stellen Sie sicher, dass es sich um ein "
                    + "PNG/JPG/JPEG handelt.");
          } else {
            icon = new ImageIcon(readImage);
            //geht kaputt, wenn nicht png-Datei
            File destination = new File(AppData
                .getCustomLevelPictureFolder() + "/picture" + index + ".png");
            if (destination.exists()) {
              FileUtils.delete(destination);
            }
            FileUtils.copyFile(chooseIcon.getSelectedFile(), destination);
            iconSelector.setIcon(new ImageIcon(icon.getImage()
                .getScaledInstance(AppData.ICON_SIZE, AppData.ICON_SIZE,
                    Image.SCALE_SMOOTH)));
          }
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    });
    myContainer.add(iconSelector, BorderLayout.WEST);
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
    // Cannot be empty because of the if above
    // => Must be an Integer because of the formatter
    return new Item(Integer.parseInt(valueField.getValue().toString()),
        Integer.parseInt(weightField.getValue().toString()),
        nameField.getValue().toString());
  }

  /**
   * gets the amountField.
   * amount could be "" so only call after generateItem()
   *
   * @return returns the amount specified in the AmountField
   */
  public int getAmount() {
    return Integer.parseInt(amountField.getValue().toString());
  }
}
