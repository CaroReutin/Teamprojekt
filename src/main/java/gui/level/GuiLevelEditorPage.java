
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
   * The path for the default Icon.
   */
  private static final String DEFAULTICONPATH = "icons/DefaultBox.png";

  /**
   * The list of icons.
   */
  private static final ArrayList<ImageIcon> ICONS = new ArrayList<>();
  /**
   * The list of name TextFields.
   */
  private static final ArrayList<JTextField> NAME_FIELDS = new ArrayList<>();
  /**
   * The list of weight TextFields.
   */
  private static final ArrayList<JTextField> WEIGHT_FIELDS = new ArrayList<>();
  /**
   * The list of value TextFields.
   */
  private static final ArrayList<JTextField> VALUE_FIELDS = new ArrayList<>();
  /**
   * The list of amount TextFields.
   */
  private static final ArrayList<JTextField> AMOUNT_FIELDS = new ArrayList<>();
  /**
   * The list of name labels.
   */
  private static final ArrayList<JLabel> NAME_LABELS = new ArrayList<>();
  /**
   * The list of weight labels.
   */
  private static final ArrayList<JLabel> WEIGHT_LABELS = new ArrayList<>();
  /**
   * The list of value labels.
   */
  private static final ArrayList<JLabel> VALUE_LABELS = new ArrayList<>();
  /**
   * The list of amount labels.
   */
  private static final ArrayList<JLabel> AMOUNT_LABELS = new ArrayList<>();
  /**
   * The list of weight documents.
   */
  private static final ArrayList<AbstractDocument>
      WEIGHT_FIELD_DOCUMENTS = new ArrayList<>();
  /**
   * The list of amount documents.
   */
  private static final ArrayList<AbstractDocument>
      AMOUNT_FIELD_DOCUMENTS = new ArrayList<>();
  /**
   * The list of value documents.
   */
  private static final ArrayList<AbstractDocument>
      VALUE_FIELD_DOCUMENTS = new ArrayList<>();
  /**
   * The list of icon selector buttons.
   */
  private static final ArrayList<JButton> ICON_SELECTORS = new ArrayList<>();
  /**
   * The amount of currently visible itemPanels.
   */
  private static int panelCounter = 0;

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
    pane.setLayout(new GridLayout(1, 3));

    Container leftSide = new Container();
    leftSide.setLayout(new GridLayout(5, 2));

    JLabel titel = new JLabel("Titel: ");
    titel.setFont(AppData.FONT_STYLE);
    leftSide.add(titel);

    JTextField titleField = new JTextField("");
    AbstractDocument titleDocument = (AbstractDocument)
        titleField.getDocument();
    titleDocument.setDocumentFilter(new FieldListener());
    leftSide.add(titleField);

    JLabel capacity = new JLabel("Kapazität: ");
    capacity.setFont(AppData.FONT_STYLE);
    leftSide.add(capacity);


    JTextField capacityField = new JTextField("");
    AbstractDocument capacityDocument = (AbstractDocument)
        capacityField.getDocument();
    capacityDocument.setDocumentFilter(new NumberListener());
    leftSide.add(capacityField);

    JLabel modus = new JLabel("Modus: ");
    modus.setFont(AppData.FONT_STYLE);
    leftSide.add(modus);

    String[] robberOptions = new String[Level.Robber.values().length];
    robberOptions[0] = Level.Robber.DR_META.name();
    robberOptions[1] = Level.Robber.GIERIGER_GANOVE.name();
    robberOptions[2] = Level.Robber.BACKTRACKING_BANDIT.name();
    JComboBox<String> modeDropdown = new JComboBox<>(robberOptions);
    leftSide.add(modeDropdown);

    Font fontButtons = new Font("Arial",
        Font.BOLD + Font.ITALIC, SIZE_FONT_SMALL / 2);

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

    JPanel resetPanel = new JPanel();
    JButton reset = new JButton("Reset");
    reset.addActionListener(e -> {
      panelCounter = 0;
      GuiManager.openLevelEditor();
    });
    reset.setFont(fontButtons);
    resetPanel.add(reset);

    leftSide.add(resetPanel);
    pane.add(leftSide);

    addMiddleAndRightPart(pane);

    return pane;
  }

  private void addMiddleAndRightPart(final Container pane) {
    Container middle = new Container();
    middle.setLayout(new GridLayout(8, 1));

    Container right = new Container();
    right.setLayout(new GridLayout(8, 1));

    ArrayList<Container> itemPanels = new ArrayList<>();
    ArrayList<Container> fieldPanels = new ArrayList<>();
    ArrayList<Container> labelPanels = new ArrayList<>();

    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      itemPanels.add(new Container());
      itemPanels.get(i).setLayout(new GridLayout(1, 3));
      fieldPanels.add(new Container());
      fieldPanels.get(i).setLayout(new GridLayout(4, 1));
      labelPanels.add(new Container());
      labelPanels.get(i).setLayout(new GridLayout(4, 1));
      ICONS.add(new ImageIcon(DEFAULTICONPATH));
      Image image = ICONS.get(i).getImage();
      ICONS.set(i, new ImageIcon(image.getScaledInstance(
          AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH)));
      ICON_SELECTORS.add(new JButton());
      ICON_SELECTORS.get(i).setIcon(ICONS.get(i));
      JFileChooser chooseIcon = new JFileChooser();
      chooseIcon.setCurrentDirectory(new File("."));
      chooseIcon.setDialogTitle("Speicherordner");
      chooseIcon.setFileSelectionMode(JFileChooser.FILES_ONLY);
      FileFilter imageFilter = new FileNameExtensionFilter(
          "Image files", ImageIO.getReaderFileSuffixes());
      chooseIcon.setFileFilter(imageFilter);
      int finalI = i;
      ICON_SELECTORS.get(i).addActionListener(e -> {
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
              ICONS.set(finalI, new ImageIcon(readImage));
              //geht kaputt, wenn nicht png-Datei
              File destination = new File(AppData
                  .getCustomLevelPictureFolder()
                  + "/picture" + finalI + ".png");
              if (destination.exists()) {
                FileUtils.delete(destination);
              }
              FileUtils.copyFile(chooseIcon.getSelectedFile(), destination);
              ICON_SELECTORS.get(finalI).setIcon(new ImageIcon(ICONS
                  .get(finalI).getImage().getScaledInstance(AppData
                      .ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH)));
            }
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      });
      itemPanels.get(i).add(ICON_SELECTORS.get(i));

      NAME_LABELS.add(new JLabel("Bezeichnung: "));
      labelPanels.get(i).add(NAME_LABELS.get(i));

      NAME_FIELDS.add(new JTextField());
      fieldPanels.get(i).add(NAME_FIELDS.get(i));

      VALUE_LABELS.add(new JLabel("Wert: "));
      labelPanels.get(i).add(VALUE_LABELS.get(i));

      VALUE_FIELDS.add(new JTextField());
      VALUE_FIELD_DOCUMENTS.add((AbstractDocument)
          VALUE_FIELDS.get(i).getDocument());
      VALUE_FIELD_DOCUMENTS.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(VALUE_FIELDS.get(i));

      WEIGHT_LABELS.add(new JLabel("Gewicht: "));
      labelPanels.get(i).add(WEIGHT_LABELS.get(i));

      WEIGHT_FIELDS.add(new JTextField());
      WEIGHT_FIELD_DOCUMENTS.add((AbstractDocument)
          WEIGHT_FIELDS.get(i).getDocument());
      WEIGHT_FIELD_DOCUMENTS.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(WEIGHT_FIELDS.get(i));

      AMOUNT_LABELS.add(new JLabel("Anzahl: "));
      labelPanels.get(i).add(AMOUNT_LABELS.get(i));

      AMOUNT_FIELDS.add(new JTextField());
      AMOUNT_FIELD_DOCUMENTS.add((AbstractDocument)
          AMOUNT_FIELDS.get(i).getDocument());
      AMOUNT_FIELD_DOCUMENTS.get(i).setDocumentFilter(new NumberListener());
      fieldPanels.get(i).add(AMOUNT_FIELDS.get(i));

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
    NAME_FIELDS.get(i).setVisible(b);
    WEIGHT_FIELDS.get(i).setVisible(b);
    VALUE_FIELDS.get(i).setVisible(b);
    AMOUNT_FIELDS.get(i).setVisible(b);
    NAME_LABELS.get(i).setVisible(b);
    WEIGHT_LABELS.get(i).setVisible(b);
    VALUE_LABELS.get(i).setVisible(b);
    AMOUNT_LABELS.get(i).setVisible(b);
    ICON_SELECTORS.get(i).setVisible(b);
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
            || Integer.parseInt(AMOUNT_FIELDS.get(i).getText()) == 0) {
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
        itemAmountList.add(Integer.parseInt(AMOUNT_FIELDS.get(i).getText()));
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
    if (NAME_FIELDS.get(i).getText().equals("")
        || AMOUNT_FIELDS.get(i).getText().equals("")
        || WEIGHT_FIELDS.get(i).getText().equals("")
        || VALUE_FIELDS.get(i).getText().equals("")) {
      throw new NullPointerException("No field may be empty");
    }
    // Cannot be empty because of the if above
    // => Must be an Integer because of the formatter
    return new Item(Integer.parseInt(VALUE_FIELDS.get(i).getText()),
        Integer.parseInt(WEIGHT_FIELDS.get(i).getText()),
        NAME_FIELDS.get(i).getText(), null);
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
