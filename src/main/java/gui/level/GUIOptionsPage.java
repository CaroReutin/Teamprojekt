package gui.level;

import solving.AppData;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


/**
 * The class GUIOptionsPage holds the pane of the optionspage.
 */
public class GUIOptionsPage {

  /**
   * Compares the given String with the set of implemented passwords.
   *
   * @param pw the password that should be checked.
   *
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
   * Height and width (dimensions) of the options menu pane.
   */
  private final int paneDim = 500;
  /**
   * Number to divide the parameters of the password related pane by 10.
   */
  private final int ten = 10;
  /**
   * Number to multiply the bounds of the password related pane with 3.
   */
  private final int three = 3;
  /**
   * Number to multiply the bounds of the password related pane with 4.
   */
  private final int four = 4;
  /**
   * To be used with frame.setContentPane().
   *
   * @return returns the Container that contains the content of the options page
   */
  public Container getPane() {
    Container pane = new Container();
    pane.setSize(paneDim, paneDim);
    pane.setLayout(null);

    JButton back = new JButton("Zurück");
    JFormattedTextField passwordInput = new JFormattedTextField("");
    JButton enterPassword = new JButton("Eingabe");
    GUIManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                    "callConfirmPassword");
    GUIManager.getRootPane().getActionMap().put(
            "callConfirmPassword", new AbstractAction() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        confirmPassword(passwordInput.getText(), pane);
      }
    });
    passwordInput.setBounds(three * pane.getWidth() / ten,
            pane.getHeight() / ten,
            four * pane.getWidth() / ten,
            pane.getHeight() / ten);
    enterPassword.setBounds(four * pane.getWidth() / ten,
            2 * pane.getHeight() / ten + passwordInput.getHeight(),
            2 * pane.getWidth() / ten,
            pane.getHeight() / ten);
    enterPassword.addActionListener(e ->
            confirmPassword(passwordInput.getText(), pane));
    back.setBounds(four * pane.getWidth() / ten,
            three * pane.getHeight() / ten + passwordInput.getHeight()
                    + enterPassword.getHeight(),
            2 * pane.getWidth() /  ten,
            pane.getHeight() / ten);
    back.addActionListener(e -> {
      GUIManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
              remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
      GUIManager.openMainMenu();
    });
    passwordInput.setValue("");
    pane.add(passwordInput);
    pane.add(enterPassword);
    pane.add(back);

    return pane;
  }
}
