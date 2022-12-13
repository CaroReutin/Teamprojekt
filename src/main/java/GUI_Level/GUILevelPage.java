package GUI_Level;

import Rucksack.*;
import Solving.UserDataManager;

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
    }


    public void startLevelFrame(JFrame frame, GUIManager guiManager) {
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
        this.escapeButton(centerPanel, guiManager, frame, leftPanel, rightPanel, scaledRucksackImage);
        this.itemButtoms(rightPanel, leftPanel);


        //alles zusammenpuzzeln

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void escapeButton(JPanel centerPanel, GUIManager guiManager, JFrame frame, JPanel leftPanel, JPanel rightPanel, Image imageIcon){
        JButton flucht = new JButton("Flucht");
        flucht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level.getRucksack().getCurrentValue() > UserDataManager.getScore(level.getLevelNumber())){
                    UserDataManager.newHighScore(level.getLevelNumber(),level.getRucksack().getCurrentValue());
                    UserDataManager.save();
                }
                String[] buttons = {"Erneut Spielen","Nächstes Level","Levelauswahl"};
                int chosenButton = JOptionPane.showOptionDialog(centerPanel,"Hier steht Tips / Feedback","Geflohen",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,buttons,buttons[0]);
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
                        clearPage(leftPanel);
                        frame.remove(leftPanel);
                        clearPage(centerPanel);
                        clearPage(rightPanel);
                        clearRucksack(imageIcon);
                        frame.revalidate();
                        frame.repaint();
                        guiManager.getGuiLevelDeciderPage().openLevelDeciderPage(frame, guiManager);

                        System.out.println("Es wurde auf " + buttons[2] + " geklickt.");
                        break;
                }
                level.endOfLevel();
            }
        });
        centerPanel.add(flucht);
    }

    /**
     * fügt zur Verfügung stehende Items als Button ein
     * @param
     * @return
     */
    private void itemButtoms(JPanel panelItems, JPanel panelRucksack) {
        JLabel currentWeightLabel = new JLabel("0/" + level.getRucksack().getCapacity() + "g");
        Font fCurrentWeightLabel = currentWeightLabel.getFont();
        currentWeightLabel.setFont(fCurrentWeightLabel.deriveFont(fCurrentWeightLabel.getStyle() | Font.BOLD));

        JLabel currentValueLabel = new JLabel("0€");
        Font fcurrentValueLabel = currentValueLabel.getFont();
        currentValueLabel.setFont(fcurrentValueLabel.deriveFont(fcurrentValueLabel.getStyle() | Font.BOLD));

        ArrayList<Item> items = level.getItemList();
        for (int i = 0; i<items.size(); i++) {
            JReferencingButton current = new JReferencingButton(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, " + items.get(i).getValue() + "€)", level,  i);
            JLabel label = new JLabel(level.getItemAmountList().get(i).toString());

            Font f = label.getFont();
            label.setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

            JReferencingButton currentRucksack = new JReferencingButton(items.get(i).getName(), level, i);
            JLabel labelRucksack = new JLabel("0");
            Font fRucksack = labelRucksack.getFont();
            labelRucksack.setFont(fRucksack.deriveFont(fRucksack.getStyle() | Font.BOLD));
            current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((level.getRucksack().getCurrentWeight() + current.getLevel().getRucksack().getItems().get(current.getPosition()).getWeight() )<= level.getRucksack().getCapacity()) {
                        current.setItemAmount();
                        label.setText(String.valueOf(current.getAmountLevelItem()));
                        labelRucksack.setText(String.valueOf(currentRucksack.getAmountRucksackItem()));
                        currentWeightLabel.setText(level.getRucksack().getCurrentWeight() + "/" + level.getRucksack().getCapacity() + "g");
                        currentValueLabel.setText((level.getRucksack().getCurrentValue() + "€"));
                    }



                }
            });
            currentRucksack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentRucksack.setRucksackItemAmount();
                    labelRucksack.setText(String.valueOf(currentRucksack.getAmountRucksackItem()));
                    label.setText(String.valueOf(current.getAmountLevelItem()));
                    currentWeightLabel.setText(level.getRucksack().getCurrentWeight() + "/" + level.getRucksack().getCapacity() + "g");
                    currentValueLabel.setText((level.getRucksack().getCurrentValue() + "€"));
                }
            });
            panelItems.add(current);
            panelItems.add(label);
            panelRucksack.add(currentRucksack);
            panelRucksack.add(labelRucksack);
            panelRucksack.add(currentWeightLabel);
            panelRucksack.add(currentValueLabel);

        }

    }

    private void clearRucksack(Image image) {
        //todo remove image method
    }

    private void clearPage(JPanel panel) {
     panel.removeAll();
     panel.revalidate();
     panel.repaint();
    }



}