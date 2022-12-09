import javax.swing.*;
import java.awt.*;

public class GUIFrontpage {
    GUILevelDeciderPage guiLevelDeciderPage;
    GUIOptionsPage guiOptionsPage;

    public void openProgrammWindow(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Frontpage");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        Container pane = frame.getContentPane();
        pane.setLayout(null);

        Font fontStyle = new Font("Arial", Font.BOLD + Font.ITALIC, 30);

        JLabel titel = new JLabel("Optimal Heist");
        titel.setBounds(150, 40, 300, 40);
        titel.setFont(fontStyle);
        pane.add(titel);

        JButton levelButton = new JButton("Level");
        levelButton.setBounds(190,100,120,40);
        levelButton.setBackground(Color.cyan);
        pane.add(levelButton);
        guiLevelDeciderPage = new GUILevelDeciderPage();

        levelButton.addActionListener(e -> guiLevelDeciderPage.openLevelDeciderPage());

        JButton ownLevelButton = new JButton("Eigene Level");
        ownLevelButton.setBounds(190, 180, 120, 40);
        ownLevelButton.setBackground(Color.cyan);
        pane.add(ownLevelButton);


        ownLevelButton.addActionListener(e -> {

        });

        JButton settingsButton = new JButton("Einstellungen");
        settingsButton.setBounds(190, 260, 120, 40);
        settingsButton.setBackground(Color.cyan);
        pane.add(settingsButton);
        guiOptionsPage = new GUIOptionsPage(frame);

        settingsButton.addActionListener(e -> guiOptionsPage.openOptionsPage());

    }


}
