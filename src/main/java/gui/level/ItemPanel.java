package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
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
  private final JTextField nameField;
  /**
   * the weight field.
   */
  private final JTextField weightField;
  /**
   * the value field.
   */
  private final JTextField valueField;
  /**
   * the amount field.
   */
  private final JTextField amountField;
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

    JLabel name = new JLabel("Bezeichnung: ");
    itemInfoPane.add(name);
    nameField = new JTextField("");
    itemInfoPane.add(nameField);
    JLabel weight = new JLabel("Gewicht: ");
    itemInfoPane.add(weight);
    weightField = new JTextField("");
    AbstractDocument weightDocument = (AbstractDocument)
        weightField.getDocument();
    weightDocument.setDocumentFilter(new NumberListener());
    itemInfoPane.add(weightField);
    JLabel value = new JLabel("Wert: ");
    itemInfoPane.add(value);
    valueField = new JTextField("");
    AbstractDocument valueDocument = (AbstractDocument)
        valueField.getDocument();
    valueDocument.setDocumentFilter(new NumberListener());
    itemInfoPane.add(valueField);
    JLabel amount = new JLabel("Anzahl: ");
    itemInfoPane.add(amount);
    amountField = new JTextField("");
    AbstractDocument amountDocument = (AbstractDocument)
        amountField.getDocument();
    amountDocument.setDocumentFilter(new NumberListener());
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
    if (nameField.getText().equals("")
        || amountField.getText().equals("")
        || weightField.getText().equals("")) {
      throw new NullPointerException("No field may be empty");
    }
    // Cannot be empty because of the if above
    // => Must be an Integer because of the formatter
    return new Item(Integer.parseInt(valueField.getText()),
        Integer.parseInt(weightField.getText()),
        nameField.getText());
  }

  /**
   * gets the amountField.
   * amount could be "" so only call after generateItem()
   *
   * @return returns the amount specified in the AmountField
   */
  public int getAmount() {
    return Integer.parseInt(amountField.getText());
  }
}
