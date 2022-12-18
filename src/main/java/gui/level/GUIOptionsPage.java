package gui.level;

import Solving.AppData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUIOptionsPage {

    /**
     * Compares the given String with the set of implemented passwords
     * @param pw the password that should be checked
     * @param parent the Container that dictates where the dialog should appear (center of the container)
     */
    private static void confirmPassword(String pw, Container parent){
        if (pw.matches(AppData.getPassword(0))){
            JOptionPane.showMessageDialog(parent,"Hinweise sind nun freigeschalten.","Erfolg",JOptionPane.INFORMATION_MESSAGE);

            //Level.tipsAllowed(true);
        }
    }

    /**
     * To be used with frame.setContentPane()
     * @return returns the Container that contains the content of the options page
     */
    public Container getPane() {
        Container pane = new Container();
        pane.setSize(500,500);
        pane.setLayout(null);

        JButton back = new JButton("Zurück");
        JFormattedTextField passwordInput = new JFormattedTextField("");
        JButton enterPassword = new JButton("Eingabe");
        GUIManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"callConfirmPassword");
        GUIManager.getRootPane().getActionMap().put("callConfirmPassword", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmPassword(passwordInput.getText(),pane);
            }
        });
        passwordInput.setBounds(3*pane.getWidth()/10,pane.getHeight()/10, 4*pane.getWidth()/10,pane.getHeight()/10);
        enterPassword.setBounds(4*pane.getWidth()/10,2*pane.getHeight()/10 + passwordInput.getHeight(), 2*pane.getWidth()/10,pane.getHeight()/10);
        enterPassword.addActionListener(e -> confirmPassword(passwordInput.getText(),pane));
        back.setBounds(4*pane.getWidth()/10,3*pane.getHeight()/10 + passwordInput.getHeight() + enterPassword.getHeight(), 2*pane.getWidth()/10,pane.getHeight()/10);
        back.addActionListener(e -> {
            GUIManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
            GUIManager.openMainMenu();
        });
        passwordInput.setValue("");
        pane.add(passwordInput);
        pane.add(enterPassword);
        pane.add(back);

        return pane;
    }
}
