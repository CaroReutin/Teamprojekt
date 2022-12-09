import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUIOptionsPage {
    private final JDialog options;
    private final JFormattedTextField passwordInput;


    public GUIOptionsPage(JFrame frame){
        options = new JDialog(frame,true);
        options.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container optionsPane = options.getContentPane();
        optionsPane.setLayout(null);
        JButton close = new JButton("Close");
        passwordInput = new JFormattedTextField("");
        JButton enterPassword = new JButton("Enter");
        options.setTitle("Einstellungen");
        options.setResizable(false);
        // Bugged: The first time Enter is pressed is always ignored, every other time the program works
        options.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"callConfirmPassword");
        options.getRootPane().getActionMap().put("callConfirmPassword", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmPassword(passwordInput.getText(),options);
            }
        });
        options.setSize(new Dimension(500,500));
        passwordInput.setBounds(3*options.getWidth()/10,options.getHeight()/10, 4*options.getWidth()/10,options.getHeight()/10);
        enterPassword.setBounds(4*options.getWidth()/10,2*options.getHeight()/10 + passwordInput.getHeight(), 2*options.getWidth()/10,options.getHeight()/10);
        enterPassword.addActionListener(e -> confirmPassword(passwordInput.getText(),options));
        close.setBounds(4*options.getWidth()/10,3*options.getHeight()/10 + passwordInput.getHeight() + enterPassword.getHeight(), 2*options.getWidth()/10,options.getHeight()/10);
        close.addActionListener(e -> options.dispose());

        optionsPane.add(passwordInput);
        optionsPane.add(enterPassword);
        optionsPane.add(close);
    }
    public void openOptionsPage(JFrame frame) {
        options.setLocation(frame.getLocation());
        passwordInput.setValue("");
        options.setVisible(true);
    }
    private static void confirmPassword(String pw,JDialog parent){
        if (pw.matches(GUIManager.getPasswords().get(0))){
            JOptionPane.showMessageDialog(parent,"Hinweise sind nun freigeschalten.","Erfolg",JOptionPane.INFORMATION_MESSAGE);

            //Level.tipsAllowed(true);
        }
    }
}
