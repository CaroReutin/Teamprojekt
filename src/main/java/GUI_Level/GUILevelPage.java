package GUI_Level;

import Rucksack.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;


public class GUILevelPage {
    private Level level;

    public GUILevelPage(Level level) {
        this.level = level;

        JFrame frame = new JFrame();
        frame.setTitle("Level");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new GridLayout(1,3));

        //Füge Rucksack png ein und ändere größe
        URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
        ImageIcon rucksackImage = new ImageIcon(url);
        Image scaledRucksackImage = rucksackImage.getImage().getScaledInstance(170,300,java.awt.Image.SCALE_SMOOTH);


        JPanel leftPanel = new JBackgroundPanel(scaledRucksackImage);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel();
        //JPanel rightPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));

        // erzeuge Buttons
        this.escapeButton(centerPanel);
        this.itemButtoms(rightPanel, leftPanel);


        //alles zusammenpuzzeln

        frame.add(leftPanel, BorderLayout.WEST);
        //frame.add(background, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        //layeredPane.setVisible(true);
        frame.setVisible(true);

    }

    private void escapeButton(JPanel panel){
        JButton flucht = new JButton("Flucht");
        flucht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = {"Erneut Spielen","Nächstes Level","Levelauswahl"};
                int chosenButton = JOptionPane.showOptionDialog(panel,"Hier steht Tips / Feedback","Geflohen",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,buttons,buttons[0]);
                switch (chosenButton) {
                    case 0:
                        //LevelManager.restart();
                        System.out.println("Es wurde auf " + buttons[0] + " geklickt.");
                        break;
                    case 1:
                        //LevelManager.nextLevel();
                        System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
                        break;
                    case 2:
                        /*
                        GuiLevelDeciderPage guiLevelDeciderPage = new guiLevelDeciderPage();
                        back.addActionListener(e -> {
                            guiManager.rePaintFrame(pane);
                            guiLevelDeciderPage.getFrontPage(frame);
                        });
                         */
                        System.out.println("Es wurde auf " + buttons[2] + " geklickt.");
                        break;
                }
                level.endOfLevel();
            }
        });
        panel.add(flucht);
    }

    /**
     * fügt zur Verfügung stehende Items als Button ein
     * @param
     * @return
     */
    private void itemButtoms(JPanel panelItems, JPanel panelRucksack) {
        ArrayList<Item> items = level.getItemList();
        for (int i = 0; i<items.size(); i++) {
            JReferencingButton current = new JReferencingButton(items.get(i).getName(), level,  i);
            JLabel label = new JLabel(level.getItemAmountList().get(i).toString());

            JReferencingButton currentRucksack = new JReferencingButton(items.get(i).getName(), level, i);
            JLabel labelRucksack = new JLabel("0");
            current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current.setItemAmount();
                    label.setText(String.valueOf(current.getAmountLevelItem()));
                    labelRucksack.setText(String.valueOf(currentRucksack.getAmountRucksackItem()));


                }
            });
            currentRucksack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentRucksack.setRucksackItemAmount();
                    labelRucksack.setText(String.valueOf(currentRucksack.getAmountRucksackItem()));
                    label.setText(String.valueOf(current.getAmountLevelItem()));
                }
            });
            panelItems.add(current);
            panelItems.add(label);
            panelRucksack.add(currentRucksack);
            panelRucksack.add(labelRucksack);

        }

    }




}