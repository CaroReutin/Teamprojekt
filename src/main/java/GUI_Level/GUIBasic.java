package GUI_Level;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class GUIBasic{
    public GUIBasic() {
        JFrame frame = new JFrame();
        frame.setTitle("Level");
        frame.setSize(4000,2500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new GridLayout(1,3));

        JPanel leftPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.escapeButton(centerPanel);
        this.itemButtoms(rightPanel);

        //Füge Rucksack png ein und ändere größe
        URL url = getClass().getClassLoader().getResource("rucksack.png");
        ImageIcon rucksackImage = new ImageIcon(url);
        Image scaledRucksackImage = rucksackImage.getImage().getScaledInstance(800,800,java.awt.Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledRucksackImage));
        leftPanel.add(picLabel);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void escapeButton(JPanel panel){
        JButton flucht = new JButton("Flucht");
        panel.add(flucht);
    }

    private void itemButtoms(JPanel panel) {
        String[] testItems = {"1","2", "3", "4", "5","6"};
        ArrayList<JButton> items = new ArrayList();
        for (int i = 0; i<testItems.length; i++) {
            JButton current = new JButton(testItems[i]);
            items.add(current);
            panel.add(current);
        }

    }


}