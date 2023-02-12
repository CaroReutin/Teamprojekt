package gui.level;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import solving.AppData;

/**
 * The class GUIOptionsPage holds the pane of the optionspage.
 */
public class GuiOptionsPage {
  /**
   * The boolean value whether the tips for backtracking levels are unlocked.
   */
  private static boolean backtrackingTipsAllowed = false;

  /**
   * Setter method for the value of backtracking clues being allowed.
   *
   * @param backtrackingTipsAllowed boolean value whether clues are allowed
   */
  public static void setBacktrackingTipsAllowed(
          final boolean backtrackingTipsAllowed) {
    GuiOptionsPage.backtrackingTipsAllowed = backtrackingTipsAllowed;
  }
  /**
   * Getter method for the value of backtracking clues being allowed.
   *
   * @return the value of backtrackingTipsAllowed
   */
  public static boolean getBacktrackingTipsAllowed() {
    return backtrackingTipsAllowed;
  }

  /**
   * The boolean value whether the clues for the greedy levels are unlocked.
   */
  private static boolean greedyTipsAllowed = false;
  /**
   * Setter method for the value of greedy clues being allowed.
   *
   * @param greedyTipsAllowed boolean value whether clues are allowed
   */
  public static void setGreedyTipsAllowed(
          final boolean greedyTipsAllowed) {
    GuiOptionsPage.greedyTipsAllowed = greedyTipsAllowed;
  }
  /**
   * Getter method for the value of backtracking clues being allowed.
   *
   * @return the value of backtrackingTipsAllowed
   */
  public static boolean getGreedyTipsAllowed() {
    return greedyTipsAllowed;
  }
  /**
   * The boolean value whether the alternate version of
   * the tree depiction is necessary.
   */
  private static boolean altTreeSelected = false;
  /**
   * Setter method for selection of the alternate tree version.
   *
   * @param altTreeSelected boolean value whether the alternate version
   *                        of the backtracking tree is selected
   */
  public static void setAltTreeSelected(
          final boolean altTreeSelected) {
    GuiOptionsPage.altTreeSelected = altTreeSelected;
  }
  /**
   * Getter method for the boolean value whether
   * the alternate tree version is selected.
   *
   * @return the value of altTreeSelected
   */
  public static boolean getAltTreeSelected() {
    return altTreeSelected;
  }
  /**
   * the number of rows on the pane.
   */
  public static final int ROWS_ON_PANE = 4;

  /**
   * the preferred length of the field.
   */
  public static final int LENGTH_OF_FIELD = 200;

  /**
   * the preferred width of the field.
   */
  public static final int HEIGHT_OF_FIELD = 50;

  /**
   * Compares the given String with the set of implemented passwords.
   *
   * @param pw     the password that should be checked.
   * @param parent the Container that dictates where the dialog should appear
   *               (center of the container).
   */
  private static void confirmPassword(final String pw, final Container parent) {
    if (pw.matches(AppData.getPassword(0))) {
      JOptionPane.showMessageDialog(parent,
          "Hinweise für Greedy-Level sind nun freigeschaltet.",
          "Erfolg", JOptionPane.INFORMATION_MESSAGE);
      GuiOptionsPage.greedyTipsAllowed = true;
    } else if (pw.matches(AppData.getPassword(1))) {
      JOptionPane.showMessageDialog(parent,
          "Hinweise für Backtracking-Level sind nun freigeschaltet.",
          "Erfolg", JOptionPane.INFORMATION_MESSAGE);
      GuiOptionsPage.backtrackingTipsAllowed = true;
    } else {
      JOptionPane.showMessageDialog(parent,
          "Das eingegebene Passwort war nicht korrekt.",
          "Falsches Passwort", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the options page
   */
  public Container getPane() {
    Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
    Container pane = new Container();
    pane.setLayout(new BorderLayout());

    Container subPane = new Container();
    subPane.setLayout(new GridLayout(ROWS_ON_PANE, 1));

    JFormattedTextField passwordInput = new JFormattedTextField("");
    passwordInput.setPreferredSize(
        new Dimension(LENGTH_OF_FIELD, HEIGHT_OF_FIELD));
    GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            "callConfirmPassword");
    GuiManager.getRootPane().getActionMap()
        .put("callConfirmPassword", new AbstractAction() {
          @Override
          public void actionPerformed(final ActionEvent e) {
            confirmPassword(passwordInput.getText(), pane);
          }
        });

    //erzeuge JPanels
    JPanel enterPasswordPanel = new JPanel();
    JPanel passwordInputPanel = new JPanel();
    JPanel descriptionPanel = new JPanel();
    passwordInputPanel.add(passwordInput);
    passwordInput.setValue("");
    JPanel treeModePanel = new JPanel();
    JButton treeModeButton = new JButton();
    treeModeButton.setFont(font);
    if (altTreeSelected) {
      treeModeButton.setText("Zeige Standard-Backtrackingbaum");
    } else {
      treeModeButton.setText("Zeige alternativen Backtrackingbaum");
    }
    treeModeButton.addActionListener(e -> {
      if (!altTreeSelected) {
        treeModeButton.setText("Zeige Standard-Backtrackingbaum");
      } else {
        treeModeButton.setText("Zeige alternativen Backtrackingbaum");
      }
      altTreeSelected = !altTreeSelected;
    });
    treeModePanel.add(treeModeButton);

    //erzeuge Buttons
    JButton enterPassword = new JButton("Eingabe");
    enterPassword.setFont(font);
    enterPasswordPanel.add(enterPassword);
    enterPassword.addActionListener(e ->
        confirmPassword(passwordInput.getText(), pane));

    JPanel backPanel = new JPanel();
    JButton back = new JButton("Zurück");
    back.setFont(font);
    backPanel.add(back);
    back.addActionListener(e -> {
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
      GuiManager.openMainMenu();
    });

    ImageIcon clueSymbol = new ImageIcon(
        "src/main/resources/icons/clueSymbol.png");
    Image clueSymbolImage = clueSymbol.getImage().getScaledInstance(
        60, 60, java.awt.Image.SCALE_SMOOTH);
    ImageIcon newClueSymbol = new ImageIcon(clueSymbolImage);
    JButton clueButton = new JButton(newClueSymbol);
    descriptionPanel.add(clueButton);
    clueButton.addActionListener(e -> {
      String editorMessage = null;
      try {
        editorMessage = GuiLevelPage.fileToString(
            "src/main/resources/texts/4_Options.txt");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      String[] editorButtons = {"Verstanden"};
      int chosenEditorButton = JOptionPane.showOptionDialog(null,
          editorMessage,
          "Passworteingabe",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          editorButtons, editorButtons[0]);
      switch (chosenEditorButton) {
        case 0 -> {
          GuiManager.openOptionsMenu();
        }
        default -> { //should not happen...
        }
      }
    });

    JPanel emptyPanel = new JPanel();

    //add panels on sub-pane
    subPane.add(passwordInputPanel);
    subPane.add(enterPasswordPanel);
    subPane.add(backPanel);
    subPane.add(treeModePanel);
    subPane.add(descriptionPanel);
    //add panels and sub-pane on pane
    pane.add(subPane, BorderLayout.CENTER);
    return pane;
  }

}
