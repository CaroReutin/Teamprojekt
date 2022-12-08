import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


}
