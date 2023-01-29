package gui.level;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

import solving.AppData;


/**
 * The class GUIOptionsPage holds the pane of the optionspage.
 */
public class GuiOptionsPage {

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
          "Hinweise sind nun freigeschalten.",
          "Erfolg", JOptionPane.INFORMATION_MESSAGE);

      //Level.tipsAllowed(true);
    }
  }

  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the options page
   */
  /*public Container getPane() {
    Container pane = new Container();
    pane.setSize(500, 500);
    pane.setLayout(null);

    JFormattedTextField passwordInput = new JFormattedTextField("");
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
  }*/

  public Container getPane() {
    Container pane = new Container();
    pane.setLayout(new BorderLayout());

    Container subPane = new Container();
    subPane.setLayout(new GridLayout(7,1));

    JFormattedTextField passwordInput = new JFormattedTextField("");
    passwordInput.setPreferredSize(new Dimension(200, 50));
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
    JPanel backPanel = new JPanel();

    passwordInputPanel.add(passwordInput);
    passwordInput.setValue("");

    //erzeuge Buttons
    JButton enterPassword = new JButton("Eingabe");
    enterPasswordPanel.add(enterPassword);
    enterPassword.addActionListener(e ->
      confirmPassword(passwordInput.getText(), pane));

    JButton back = new JButton("Zurück");
    backPanel.add(back);
    back.addActionListener(e -> {
      GuiManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
      GuiManager.openMainMenu();
    });

    //add panels on subpane
    subPane.add(passwordInputPanel);
    subPane.add(enterPasswordPanel);
    subPane.add(backPanel);


    //add panels and subpane on pane
    pane.add(subPane, BorderLayout.CENTER);


    return pane;
  }

}
