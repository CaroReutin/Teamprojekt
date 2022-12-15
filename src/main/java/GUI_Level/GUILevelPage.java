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

    public void startLevelFrame(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(1,3));

        //Füge Rucksack png ein und ändere größe
        URL url = getClass().getClassLoader().getResource("RucksackPNG.png");
        ImageIcon rucksackImage = new ImageIcon(url);
        Image scaledRucksackImage = rucksackImage.getImage().getScaledInstance(170,300,java.awt.Image.SCALE_SMOOTH);


        JPanel leftPanel = new JBackgroundPanel(scaledRucksackImage);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel();
        //JPanel rightPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));

        // erzeuge Buttons
        this.escapeButton(centerPanel, frame);
        this.itemButtons(rightPanel, leftPanel);


        //alles zusammenpuzzeln

        pane.add(leftPanel, BorderLayout.WEST);
        pane.add(centerPanel, BorderLayout.CENTER);
        pane.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void escapeButton(JPanel panel, JFrame frame){
        JButton flucht = new JButton("Flucht");
        flucht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level.getCurrentValue() > UserDataManager.getScore(level.getLevelNumber())){
                    UserDataManager.newHighScore(level.getLevelNumber(),level.getCurrentValue());
                    UserDataManager.save();
                }
                String[] buttons = {"Erneut Spielen","Nächstes Level","Levelauswahl"};
                int chosenButton = JOptionPane.showOptionDialog(panel,"Hier steht Tips / Feedback","Geflohen",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,buttons,buttons[0]);
                switch (chosenButton) {
                    case 0:
                        LevelManager.resetLevel(level, frame);
                        System.out.println("Es wurde auf " + buttons[0] + " geklickt.");
                        break;
                    case 1:
                        //LevelManager.nextLevel();
                        System.out.println("Es wurde auf " + buttons[1] + " geklickt.");
                        break;
                    case 2:
                        GUIManager.rePaintFrame(frame.getContentPane());
                        GUIManager.getGuiLevelDeciderPage().openLevelDeciderPage(frame);

                        System.out.println("Es wurde auf " + buttons[2] + " geklickt.");
                        break;
                }
                level.endOfLevel();
            }
        });
        panel.add(flucht);
    }

    JLabel[] labels;
    JLabel[] rucksackLabels;
    JLabel currentValueLabel;
    JLabel currentWeightLabel;

    /**
     * fügt zur Verfügung stehende Items als Button ein
     * @param
     * @return
     */
    private void itemButtons(JPanel panelItems, JPanel panelRucksack) {
        currentWeightLabel = new JLabel("0/" + level.getCapacity() + "g");
        Font fCurrentWeightLabel = currentWeightLabel.getFont();
        currentWeightLabel.setFont(fCurrentWeightLabel.deriveFont(fCurrentWeightLabel.getStyle() | Font.BOLD));

        currentValueLabel = new JLabel("0€");
        Font fcurrentValueLabel = currentValueLabel.getFont();
        currentValueLabel.setFont(fcurrentValueLabel.deriveFont(fcurrentValueLabel.getStyle() | Font.BOLD));

        ArrayList<Item> items = level.getItemList();
        labels = new JLabel[items.size()];
        rucksackLabels = new JLabel[items.size()];
        for (int i = 0; i<items.size(); i++) {
            int finalI = i;
            JButton current = new JButton(items.get(i).getName() + " (" + items.get(i).getWeight() + "g, " + items.get(i).getValue() + "€)");
            labels[i] = new JLabel(level.getItemAmountList().get(i).toString());

            Font f = labels[i].getFont();
            labels[i].setFont(f.deriveFont((f.getStyle() | Font.BOLD)));

            JButton currentRucksack = new JButton(items.get(i).getName());
            rucksackLabels[i] = new JLabel("0");
            Font fRucksack = rucksackLabels[i].getFont();
            rucksackLabels[i].setFont(fRucksack.deriveFont(fRucksack.getStyle() | Font.BOLD));
            current.addActionListener(e -> {
                if (level.getItemAmountAvailable(finalI) <= 0){
                    return;
                }
                if((level.getCurrentWeight() + level.getItemList().get(finalI).getWeight() )<= level.getCapacity()) {
                    level.moveToRucksack(finalI);
                    updateLabels(finalI);
                }
            });
            currentRucksack.addActionListener(e -> {
                if (level.getItemAmountInRucksack(finalI) <= 0){
                    return;
                }
                level.moveFromRucksack(finalI);
                updateLabels(finalI);
            });
            panelItems.add(current);
            panelItems.add(labels[i]);
            panelRucksack.add(currentRucksack);
            panelRucksack.add(rucksackLabels[i]);
            panelRucksack.add(currentWeightLabel);
            panelRucksack.add(currentValueLabel);
        }
    }

    private void updateLabels(int i){
        labels[i].setText(String.valueOf(level.getItemAmountAvailable(i)));
        rucksackLabels[i].setText(String.valueOf(level.getItemAmountInRucksack(i)));
        currentWeightLabel.setText(level.getCurrentWeight() + "/" + level.getCapacity() + "g");
        currentValueLabel.setText((level.getCurrentValue() + "€"));
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