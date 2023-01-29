
package gui.level;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    pane.setLayout(new BorderLayout());

    Container leftPane = new Container();
    leftPane.setLayout(new GridLayout(5, 2));

    JLabel titel = new JLabel("Titel: ");
    titel.setFont(AppData.FONT_STYLE);
    JLabel capacity = new JLabel("Kapazität: ");
    capacity.setFont(AppData.FONT_STYLE);
    JLabel modus = new JLabel("Modus: ");
    modus.setFont(AppData.FONT_STYLE);

    String[] robberOptions = new String[Level.Robber.values().length];
    // Unless changed (which it currently is not) the option
    // with Index 0 will be the default
    robberOptions[0] = Level.Robber.DR_META.name();
    robberOptions[1] = Level.Robber.GIERIGER_GANOVE.name();
    robberOptions[2] = Level.Robber.BACKTRACKING_BANDIT.name();

    JTextField titleField = new JTextField("");
    AbstractDocument titleDocument = (AbstractDocument)
        titleField.getDocument();
    titleDocument.setDocumentFilter(new FieldListener());

    JTextField capacityField = new JTextField("");
    AbstractDocument capacityDocument = (AbstractDocument)
        capacityField.getDocument();
    capacityDocument.setDocumentFilter(new NumberListener());
    JComboBox<String> modeDropdown = new JComboBox<>(robberOptions);
    leftPane.add(titel);
    leftPane.add(titleField);
    leftPane.add(capacity);
    leftPane.add(capacityField);
    leftPane.add(modus);
    leftPane.add(modeDropdown);

    ArrayList<ItemPanel> itemPanels = new ArrayList<>();
    JButton save = new JButton("Speichern");
    save.addActionListener(e -> {
      ArrayList<Item> itemList = new ArrayList<>();
      ArrayList<Integer> itemAmountList = new ArrayList<>();
      for (ItemPanel itemPanel : itemPanels) {
        try {
          Item nextItem = itemPanel.generateItem();
          for (Item item : itemList) {
            if (item.getValue() == nextItem.getValue()
                && item.getWeight() == nextItem.getWeight()) {
              showMessageDialog(pane, "Kein Item darf denselben Wert"
                  + " und dasselbe Gewicht wie ein anderes haben.");
              return;
            }
          }
          itemList.add(nextItem);
          itemAmountList.add(itemPanel.getAmount());
        } catch (NullPointerException n) {
          // This just means that item was not filled out fully
          // Can be ignored
        }
      }

      if (titleField.getText().equals("")
          || capacityField.getText().equals("")) {
        showMessageDialog(pane, "Titel und Kapazität darf nicht leer sein!");
      }

      Level customLevel = new Level(itemList, itemAmountList,
          Level.Robber.valueOf(Objects.requireNonNull(modeDropdown
              .getSelectedItem()).toString()), -1,
          Integer.parseInt(capacityField.getText()));
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new java.io.File("."));
      chooser.setDialogTitle("Speicherordner");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
      if (chooser.showOpenDialog(pane) == JFileChooser.APPROVE_OPTION) {
        CustomLevelManager.save(chooser.getSelectedFile().toString(),
            titleField.getText(), customLevel);
      }
    });
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

    leftPane.add(save);
    leftPane.add(back);

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

    leftPane.add(load);

    pane.add(leftPane, BorderLayout.WEST);

    Container rightPane = new Container();
    // TODO Sprint 4, update this part of the GUI
    rightPane.setLayout(new GridLayout((AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL
        + 1) / 2, 2));

    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      itemPanels.add(new ItemPanel(i, pane));
      rightPane.add(itemPanels.get(i).getPanel());
    }

    pane.add(rightPane, BorderLayout.CENTER);

    return pane;
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
