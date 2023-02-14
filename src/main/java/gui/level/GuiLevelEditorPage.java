
package gui.level;

import static gui.level.GuiFrontpage.SIZE_FONT_SMALL;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.apache.commons.io.FileUtils;
import rucksack.Item;
import rucksack.Level;
import solving.AppData;
import solving.CustomLevelManager;

/**
 * The Level Editor Class.
 */
public final class GuiLevelEditorPage {

  /**
  * the number of the grid.
  */
  private static final int GRIND_THREE = 3;

  /**
   * the number of the grid.
   */
  private static final int GRID_FIVE = 5;

  /**
   * the size of the font.
   */
  private static final int FONT_TWENTY = 20;

  /**
   * the size of the font.
   */
  private static final int FONT_FIFTEEN = 15;

  /**
   * the rows of the grid.
   */
  private static final int GRID_FOUR = 4;

  /**
   * the rows of the grid.
   */
  private static final int GRID_EIGHT = 8;

  /**
   * The list of icons.
   */
  private static ArrayList<ImageIcon> icons = new ArrayList<>();
  /**
   * The list of name TextFields.
   */
  private static ArrayList<JTextField> nameFields = new ArrayList<>();
  /**
   * The list of weight TextFields.
   */
  private static ArrayList<JTextField> weightFields = new ArrayList<>();
  /**
   * The list of value TextFields.
   */
  private static ArrayList<JTextField> valueFields = new ArrayList<>();
  /**
   * The list of amount TextFields.
   */
  private static ArrayList<JTextField> amountFields = new ArrayList<>();
  /**
   * The list of name labels.
   */
  private static ArrayList<JLabel> nameLabels = new ArrayList<>();
  /**
   * The list of weight labels.
   */
  private static ArrayList<JLabel> weightLabels = new ArrayList<>();
  /**
   * The list of value labels.
   */
  private static ArrayList<JLabel> valueLabels = new ArrayList<>();
  /**
   * The list of amount labels.
   */
  private static ArrayList<JLabel> amountLabels = new ArrayList<>();
  /**
   * The list of weight documents.
   */
  private static ArrayList<AbstractDocument>
          weightFieldDocuments = new ArrayList<>();
  /**
   * The list of amount documents.
   */
  private static ArrayList<AbstractDocument>
          amountFieldDocuments = new ArrayList<>();
  /**
   * The list of value documents.
   */
  private static ArrayList<AbstractDocument>
          valueFieldDocuments = new ArrayList<>();
  /**
   * The list of icon selector buttons.
   */
  private static ArrayList<JButton> iconSelectors = new ArrayList<>();
  /**
   * The amount of currently visible itemPanels.
   */
  private static int panelCounter;

  /**
   * The Level Editor.
   *
   * @return returns the Page containing the Level Editor.
   */
  public Container getPane() {
    try {
      FileUtils.cleanDirectory(new File(AppData.getCustomLevelPictureFolder()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Container pane = new Container();
    pane.setLayout(new GridLayout(1, GRIND_THREE));

    Container leftSide = new Container();
    leftSide.setLayout(new GridLayout(GRID_FIVE, 2));

    Font leveleditorFont = new Font("Arial",
        Font.BOLD + Font.ITALIC, FONT_TWENTY);
    JLabel titel = new JLabel("Titel: ");
    titel.setFont(leveleditorFont);
    leftSide.add(titel);

    JTextField titleField = new JTextField("");
    AbstractDocument titleDocument = (AbstractDocument)
        titleField.getDocument();
    titleDocument.setDocumentFilter(new FieldListener());
    leftSide.add(titleField);

    JLabel capacity = new JLabel("Kapazität: ");
    capacity.setFont(leveleditorFont);
    leftSide.add(capacity);


    JTextField capacityField = new JTextField("");
    AbstractDocument capacityDocument = (AbstractDocument)
        capacityField.getDocument();
    capacityDocument.setDocumentFilter(new NumberListener());
    leftSide.add(capacityField);

    JLabel modus = new JLabel("Modus: ");
    modus.setFont(leveleditorFont);
    leftSide.add(modus);

    String[] robberOptions = new String[Level.Robber.values().length];
    robberOptions[0] = Level.Robber.DR_META.name();
    robberOptions[1] = Level.Robber.GIERIGER_GANOVE.name();
    robberOptions[2] = Level.Robber.BACKTRACKING_BANDIT.name();
    JComboBox<String> modeDropdown = new JComboBox<>(robberOptions);
    leftSide.add(modeDropdown);

    Font fontButtons = new Font("Arial",
        Font.BOLD + Font.ITALIC, SIZE_FONT_SMALL / 2 - 2);

    JButton save = new JButton("Speichern");
    save.addActionListener(e -> saveLevel(pane,
        titleField, capacityField, modeDropdown));
    save.setFont(fontButtons);
    JButton back = new JButton("Abbrechen");
    back.addActionListener(e -> {
      if (JOptionPane.YES_OPTION
          == showConfirmDialog(pane,
          "Sicher, dass Sie ohne zu Speichern zurück wollen ?",
          "Abbrechen", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE)) {
        GuiManager.openMainMenu();
      }
    });

    back.setFont(fontButtons);

    JPanel savePanel = new JPanel();
    savePanel.add(save);
    leftSide.add(savePanel);
    JPanel backPanel = new JPanel();
    backPanel.add(back);
    leftSide.add(backPanel);

    JPanel loadPanel = new JPanel();
    JButton load = new JButton("Level öffnen");
    load.addActionListener(e -> {
      JFileChooser chooseIcon = new JFileChooser();
      chooseIcon.setCurrentDirectory(new java.io.File("."));
      chooseIcon.setDialogTitle("Speicherordner");
      chooseIcon.setFileSelectionMode(JFileChooser.FILES_ONLY);
      FileFilter zipFilter = new FileNameExtensionFilter("zip files", "zip");
      chooseIcon.setFileFilter(zipFilter);
      if (chooseIcon.showOpenDialog(pane) == JFileChooser.APPROVE_OPTION) {
        File customLevel = new File(chooseIcon.getSelectedFile()
            .getAbsolutePath());
        CustomLevelManager.load(customLevel);
      }
    });
    load.setFont(fontButtons);
    loadPanel.add(load);
    leftSide.add(loadPanel);

    JButton reset = new JButton("Reset");
    URL defaultIconUrl = getClass().getResource("/icons/DefaultBox.png");
    assert defaultIconUrl != null;
    ImageIcon wrongSizedDefaultIcon = new ImageIcon(defaultIconUrl);
    Image defaultImage = wrongSizedDefaultIcon.getImage().getScaledInstance(
        AppData.ICON_SIZE, AppData.ICON_SIZE, java.awt.Image.SCALE_SMOOTH);
    reset.addActionListener(e -> {
      panelCounter = 0;
      for (int i = 0; i < icons.size(); i++) {
        icons.set(i, new ImageIcon(defaultImage));
        nameFields.get(i).setText("");
        weightFields.get(i).setText("");
        valueFields.get(i).setText("");
        amountFields.get(i).setText("");
        iconSelectors.get(i).setIcon(icons.get(i));
      }
      GuiManager.openLevelEditor();
    });
    JPanel resetPanel = new JPanel();
    reset.setFont(fontButtons);
    resetPanel.add(reset);
    leftSide.add(resetPanel);
    pane.add(leftSide);
    addMiddleAndRightPart(pane);

    return pane;
  }

  private void addMiddleAndRightPart(final Container pane) {
    panelCounter = 0;
    Container middle = new Container();
    middle.setLayout(new GridLayout(GRID_EIGHT, 1));
    Container right = new Container();
    right.setLayout(new GridLayout(GRID_EIGHT, 1));
    ArrayList<Container> itemPanels = new ArrayList<>();
    ArrayList<Container> fieldPanels = new ArrayList<>();
    ArrayList<Container> labelPanels = new ArrayList<>();
    icons = new ArrayList<>();
    iconSelectors = new ArrayList<>();
    amountFields = new ArrayList<>();
    amountLabels = new ArrayList<>();
    amountFieldDocuments = new ArrayList<>();
    valueFields = new ArrayList<>();
    valueLabels = new ArrayList<>();
    valueFieldDocuments = new ArrayList<>();
    weightFields = new ArrayList<>();
    weightLabels = new ArrayList<>();
    weightFieldDocuments = new ArrayList<>();
    nameFields = new ArrayList<>();
    nameLabels = new ArrayList<>();
    Font panelFont = new Font("Arial",
        Font.BOLD + Font.ITALIC, FONT_FIFTEEN);
    URL defaultIconUrl = getClass().getResource("/icons/DefaultBox.png");
    assert defaultIconUrl != null;
    ImageIcon wrongSizedDefaultIcon = new ImageIcon(defaultIconUrl);
    Image defaultImage = wrongSizedDefaultIcon.getImage().getScaledInstance(
        AppData.ICON_SIZE, AppData.ICON_SIZE, java.awt.Image.SCALE_SMOOTH);
    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      itemPanels.add(new Container());
      itemPanels.get(i).setLayout(new GridLayout(1, GRIND_THREE));
      fieldPanels.add(new Container());
      fieldPanels.get(i).setLayout(new GridLayout(GRID_FOUR, 1));
      labelPanels.add(new Container());
      labelPanels.get(i).setLayout(new GridLayout(GRID_FOUR, 1));
      icons.add(new ImageIcon(defaultImage));
      Image image = icons.get(i).getImage();
      icons.set(i, new ImageIcon(image.getScaledInstance(
          AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH)));
      iconSelectors.add(new JButton());
      iconSelectors.get(i).setIcon(icons.get(i));
      JFileChooser chooseIcon = new JFileChooser();
      chooseIcon.setCurrentDirectory(new File("."));
      chooseIcon.setDialogTitle("Speicherordner");
      chooseIcon.setFileSelectionMode(JFileChooser.FILES_ONLY);
      FileFilter imageFilter = new FileNameExtensionFilter(
          "Image files", ImageIO.getReaderFileSuffixes());
      chooseIcon.setFileFilter(imageFilter);
      int finalI = i;
      iconSelectors.get(i).addActionListener(e -> {
        if (chooseIcon.showOpenDialog(pane) == JFileChooser
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
              JOptionPane.showMessageDialog(pane,
                  "Es gab ein Problem beim einfügen des Bildes.\n"
                      + "Stellen Sie sicher, dass es sich um ein "
                      + "PNG/JPG/JPEG handelt.");
            } else {
              icons.set(finalI, new ImageIcon(readImage));
              //geht kaputt, wenn nicht png-Datei
              File destination = new File(AppData
                  .getCustomLevelPictureFolder()
                  + "/picture" + finalI + ".png");
              if (destination.exists()) {
                FileUtils.delete(destination);
              }
              FileUtils.copyFile(chooseIcon.getSelectedFile(), destination);
              iconSelectors.get(finalI).setIcon(new ImageIcon(icons
                  .get(finalI).getImage().getScaledInstance(AppData
                      .ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH)));
            }
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      });
      itemPanels.get(i).add(iconSelectors.get(i));
      nameLabels.add(new JLabel("Name: "));
      nameLabels.get(i).setFont(panelFont);
      labelPanels.get(i).add(nameLabels.get(i));
      nameFields.add(new JTextField());
      fieldPanels.get(i).add(nameFields.get(i));
      valueLabels.add(new JLabel("Wert: "));
      valueLabels.get(i).setFont(panelFont);
      labelPanels.get(i).add(valueLabels.get(i));
      valueFields.add(new JTextField());
      valueFieldDocuments.add((AbstractDocument)
          valueFields.get(i).getDocument());
      valueFieldDocuments.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(valueFields.get(i));
      weightLabels.add(new JLabel("Gewicht: "));
      weightLabels.get(i).setFont(panelFont);
      labelPanels.get(i).add(weightLabels.get(i));
      weightFields.add(new JTextField());
      weightFieldDocuments.add((AbstractDocument)
          weightFields.get(i).getDocument());
      weightFieldDocuments.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(weightFields.get(i));
      amountLabels.add(new JLabel("Anzahl: "));
      amountLabels.get(i).setFont(panelFont);
      labelPanels.get(i).add(amountLabels.get(i));
      amountFields.add(new JTextField());
      amountFieldDocuments.add((AbstractDocument)
          amountFields.get(i).getDocument());
      amountFieldDocuments.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(amountFields.get(i));
      itemPanels.get(i).add(labelPanels.get(i));
      itemPanels.get(i).add(fieldPanels.get(i));
      if (i % 2 == 1) {
        right.add(itemPanels.get(i));
      } else {
        middle.add(itemPanels.get(i));
      }
      if (i < AppData.DEFAULT_ITEMS_IN_CUSTOM_LEVEL) {
        setItemPanelVisibility(true, i);
        panelCounter++;
      } else {
        setItemPanelVisibility(false, i);
      }
    }
    JButton moreItemsButton = new JButton("");
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass()
        .getResource("/icons/Plus.png")));
    Image image = icon.getImage();
    icon = new ImageIcon(image.getScaledInstance(
        AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH));
    moreItemsButton.setIcon(icon);
    moreItemsButton.addActionListener(e -> {
      if (panelCounter < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL) {
        setItemPanelVisibility(true, panelCounter);
        panelCounter++;
      }
    });
    right.add(moreItemsButton);
    pane.add(middle);
    pane.add(right);
  }

  private void setItemPanelVisibility(final boolean b, final int i) {
    nameFields.get(i).setVisible(b);
    weightFields.get(i).setVisible(b);
    valueFields.get(i).setVisible(b);
    amountFields.get(i).setVisible(b);
    nameLabels.get(i).setVisible(b);
    weightLabels.get(i).setVisible(b);
    valueLabels.get(i).setVisible(b);
    amountLabels.get(i).setVisible(b);
    iconSelectors.get(i).setVisible(b);
  }

  private static void saveLevel(final Container pane,
                                final JTextField titleField,
                                final JTextField capacityField,
                                final JComboBox<String> modeDropdown) {
    if (Integer.parseInt(capacityField.getText()) == 0) {
      showMessageDialog(pane, "Kapazität darf nicht 0 sein.");
      return;
    }
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Integer> validItems = new ArrayList<>();
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    for (int i = 0; i < panelCounter; i++) {
      try {
        Item nextItem = generateItem(i);
        if (nextItem.getWeight() == 0 || nextItem.getValue() == 0
            || Integer.parseInt(amountFields.get(i).getText()) == 0) {
          showMessageDialog(pane, "Wert, Gewicht und "
              + "Anzahl müssen mindestens 1 sein.");
          return;
        }
        for (Item item : itemList) {
          if (item.getName().equals(nextItem.getName())) {
            showMessageDialog(pane, "Kein Item darf denselben Namen"
                + " wie ein anderes haben.");
            return;
          }
          if (item.getValue() == nextItem.getValue()
              && item.getWeight() == nextItem.getWeight()) {
            showMessageDialog(pane, "Kein Item darf denselben Wert"
                + " und dasselbe Gewicht wie ein anderes haben.");
            return;
          }
        }
        itemList.add(nextItem);
        validItems.add(i);
        itemAmountList.add(Integer.parseInt(amountFields.get(i).getText()));
      } catch (NullPointerException n) {
        n.printStackTrace();
      }
      if (itemList.size()
          > AppData.MAXIMUM_ITEMS_IN_CUSTOM_BACKTRACKING_LEVEL
          && Level.Robber.valueOf(Objects.requireNonNull(modeDropdown
          .getSelectedItem()).toString()).equals(
          Level.Robber.BACKTRACKING_BANDIT)) {
        showMessageDialog(pane, "Backtracking Level dürfen Maximal "
            + AppData.MAXIMUM_ITEMS_IN_CUSTOM_BACKTRACKING_LEVEL
            + " verschiedene Items haben.");
        return;
      }
    }

    if (titleField.getText().equals("")
        || capacityField.getText().equals("")) {
      showMessageDialog(pane, "Titel und Kapazität darf nicht leer sein!");
      return;
    }

    Level customLevel = new Level(itemList, itemAmountList,
        Level.Robber.valueOf(Objects.requireNonNull(modeDropdown
            .getSelectedItem()).toString()), -1,
        Integer.parseInt(capacityField.getText()));
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    chooser.setDialogTitle("Speicherordner");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    if (chooser.showSaveDialog(pane) == JFileChooser.APPROVE_OPTION) {
      if (CustomLevelManager.save(chooser.getSelectedFile().toString(),
          titleField.getText(), customLevel, validItems)) {
        showMessageDialog(pane, "Level mit " + itemList.size()
                + " verschiedenen Gegenständen gespeichert.\n\n"
                + "Lade das Level entweder durch den Level öffnen"
                + " Knopf im Level-editor\noder ziehe die Zip Datei"
                + " irgendwo (außer auf ein Textfeld) in das Programm.\n\n"
                + "Falls eine höhere Anzahl an Gegenständen erwartet wurde,"
                + " überprüfe ob\njedes der 4 Felder aller"
                + " Items, welche in das Level"
                + " sollen ausgefüllt wurden.",
            "Erfolgreich gespeichert", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  private static Item generateItem(final int i) {
    if (nameFields.get(i).getText().equals("")
        || amountFields.get(i).getText().equals("")
        || weightFields.get(i).getText().equals("")
        || valueFields.get(i).getText().equals("")) {
      throw new NullPointerException("No field may be empty");
    }
    // Cannot be empty because of the if above
    // => Must be an Integer because of the formatter
    return new Item(Integer.parseInt(valueFields.get(i).getText()),
        Integer.parseInt(weightFields.get(i).getText()),
        nameFields.get(i).getText(), null);
  }

  private static class FieldListener extends DocumentFilter {
    /**
     * accepted characters.
     */
    private final String acceptedCharacters = "0123456789 ABCDEFGHIJKLMNO"
        + "PQRSTUVWXYZabcdefghijklmnopqrstuvwxyzäÄöÖüÜßẞ";

    @Override
    public void insertString(final DocumentFilter.FilterBypass fb,
                             final int offset, final String string,
                             final AttributeSet attr) throws
        BadLocationException {
      for (int i = 0; i < string.length(); i++) {
        if (!acceptedCharacters.contains(String.valueOf(string.charAt(i)))) {
          return;
        }
      }

      fb.insertString(offset, string, attr);

    }

    @Override
    public void replace(final DocumentFilter.FilterBypass fb, final int offset,
                        final int length, final String text,
                        final AttributeSet attrs) throws BadLocationException {


      for (int i = 0; i < text.length(); i++) {
        if (!acceptedCharacters.contains(String.valueOf(text.charAt(i)))) {
          return;
        }
      }

      fb.insertString(offset, text, attrs);
    }
  }
}
