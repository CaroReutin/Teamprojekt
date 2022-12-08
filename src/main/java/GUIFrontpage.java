import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUIFrontpage {

    public void openProgrammWindow(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Frontpage");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font schriftArtTitel = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Optimal Heist");
        titel.setBounds(150, 40, 300, 40);
        titel.setFont(schriftArtTitel);
        pane.add(titel);

        JButton levelButton = new JButton("Level");
        levelButton.setBounds(190,100,120,40);
        pane.add(levelButton);

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Page Levelauswahl
            }
        });

        JButton ownLevelButton = new JButton("Eigene Level");
        ownLevelButton.setBounds(190, 180, 120, 40);
        pane.add(ownLevelButton);

        ownLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton settingsButton = new JButton("Einstellungen");
        settingsButton.setBounds(190, 260, 120, 40);
        pane.add(settingsButton);
        createOptionsmenu(frame);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.setLocation(frame.getLocation());
                passwordInput.setValue("");
                options.setVisible(true);
            }
        });
    }

    private JDialog options;
    private JFormattedTextField passwordInput;
    private void createOptionsmenu(JFrame parent) {
        options = new JDialog(parent,true);
        options.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container optionsPane = options.getContentPane();
        optionsPane.setLayout(null);
        JButton close = new JButton("Close");
        passwordInput = new JFormattedTextField("");
        JButton enterPassword = new JButton("Enter");
        options.setTitle("Einstellungen");
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

    private static void confirmPassword(String pw,JDialog parent){
        if (pw.matches(GUIManager.getPasswords().get(0))){
            JOptionPane.showMessageDialog(parent,"Hinweise sind nun freigeschalten.","Erfolg",JOptionPane.INFORMATION_MESSAGE);

            //Level.tipsAllowed(true);
        }
    }
}


