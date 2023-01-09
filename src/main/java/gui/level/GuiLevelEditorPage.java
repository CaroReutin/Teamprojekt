package gui.level;

import static javax.swing.JOptionPane.showConfirmDialog;

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
    leftPane.setLayout(new GridLayout(4, 2));

    JLabel titel = new JLabel("Titel: ");
    titel.setFont(AppData.FONT_STYLE);
    JLabel capacity = new JLabel("Kapazität: ");
    capacity.setFont(AppData.FONT_STYLE);
    JLabel modus = new JLabel("Modus: ");
    modus.setFont(AppData.FONT_STYLE);

    String[] robberOptions = new String[Level.Robber.values().length];
    robberOptions[0] = Level.Robber.DR_META.name();
    robberOptions[1] = Level.Robber.GIERIGER_GANOVE.name();
    robberOptions[2] = Level.Robber.BACKTRACKING_BANDIT.name();

    JTextField titelField = new JTextField("");
    JTextField capacityField = new JTextField("");
    JComboBox<String> modusDropdown = new JComboBox<>(robberOptions);
    leftPane.add(titel);
    leftPane.add(titelField);
    leftPane.add(capacity);
    leftPane.add(capacityField);
    leftPane.add(modus);
    leftPane.add(modusDropdown);

    ArrayList<ItemPanel> itemPanels = new ArrayList<>();
    JButton save = new JButton("Speichern");
    save.addActionListener(e -> {
      ArrayList<Item> itemList = new ArrayList<>();
      ArrayList<Integer> itemAmountList = new ArrayList<>();
      for (ItemPanel itemPanel : itemPanels) {
        try {
          itemList.add(itemPanel.generateItem());
          itemAmountList.add(itemPanel.getAmount());
        } catch (NullPointerException n) {
          //This just means that item was not filled out
        }
      }
      Level customLevel = new Level(itemList, itemAmountList,
          Level.Robber.valueOf(Objects.requireNonNull(modusDropdown
              .getSelectedItem()).toString()), -1,
          Integer.parseInt(capacityField.getText()));
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new java.io.File("."));
      chooser.setDialogTitle("Speicherordner");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
      if (chooser.showOpenDialog(pane) == JFileChooser.APPROVE_OPTION) {
        CustomLevelManager.save(chooser.getSelectedFile().toString(),
            titelField.getText(), customLevel);
      } else {
        CustomLevelManager.save(titelField.getText(), customLevel);
      }
    });
    JButton back = new JButton("Abbrechen");
    back.addActionListener(e -> {
      if (JOptionPane.YES_OPTION
          == showConfirmDialog(pane,
          "Sicher, dass Sie ohne zu Speichern zurück wollen ?",
          "Abbrechen", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE)) {
        GUIManager.openMainMenu();
      }
    });

    leftPane.add(save);
    leftPane.add(back);

    pane.add(leftPane, BorderLayout.WEST);

    Container rightPane = new Container();
    rightPane.setLayout(new GridLayout((AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL
        + 1) / 2, 2));

    for (int i = 0; i < AppData.MAXIMUM_ITEMS_IN_CUSTOM_LEVEL; i++) {
      itemPanels.add(new ItemPanel(i, pane));
      rightPane.add(itemPanels.get(i).getPanel());
    }

    pane.add(rightPane, BorderLayout.CENTER);

    return pane;
  }
}
