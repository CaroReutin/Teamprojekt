import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUIOptionsPage {
    private JFormattedTextField passwordInput;
    GUIFrontpage guiFrontpage;

    public void openOptionsPage(JFrame frame, GUIManager guiManager) {
        Container pane = frame.getContentPane();
        pane.setLayout(null);

        JButton back = new JButton("Zurück");
        passwordInput = new JFormattedTextField("");
        JButton enterPassword = new JButton("Eingabe");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"callConfirmPassword");
        frame.getRootPane().getActionMap().put("callConfirmPassword", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmPassword(passwordInput.getText(),frame);
            }
        });
        passwordInput.setBounds(3*frame.getWidth()/10,frame.getHeight()/10, 4*frame.getWidth()/10,frame.getHeight()/10);
        enterPassword.setBounds(4*frame.getWidth()/10,2*frame.getHeight()/10 + passwordInput.getHeight(), 2*frame.getWidth()/10,frame.getHeight()/10);
        enterPassword.addActionListener(e -> confirmPassword(passwordInput.getText(),frame));
        back.setBounds(4*frame.getWidth()/10,3*frame.getHeight()/10 + passwordInput.getHeight() + enterPassword.getHeight(), 2*frame.getWidth()/10,frame.getHeight()/10);
        guiFrontpage = new GUIFrontpage();
        back.addActionListener(e -> {
            frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
            guiManager.rePaintFrame(pane);
            guiFrontpage.getFrontPage(frame);
        });
        passwordInput.setValue("");
        pane.add(passwordInput);
        pane.add(enterPassword);
        pane.add(back);
    }
    private static void confirmPassword(String pw, JFrame parent){
        if (pw.matches(GUIManager.getPasswords().get(0))){
            JOptionPane.showMessageDialog(parent,"Hinweise sind nun freigeschalten.","Erfolg",JOptionPane.INFORMATION_MESSAGE);

            //Level.tipsAllowed(true);
        }
    }
}
