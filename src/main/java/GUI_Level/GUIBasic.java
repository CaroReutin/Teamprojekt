package GUI_Level;

import Rucksack.Item;
import Rucksack.Level;
import Rucksack.Rucksack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class GUIBasic{
    private Level level;
    public GUIBasic(Level level) {
        this.level = level;

    }

    public void startLevelFrame(JFrame frame) {
        frame.setLayout(new GridLayout(1,3));

        JPanel leftPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new GridLayout(level.getItemList().size(), 1));

        this.escapeButton(centerPanel);

        ArrayList<JButton> itemButtons = this.itemButtoms(rightPanel);
        this.itemRucksackButtons(leftPanel,itemButtons );

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
        flucht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level.endOfLevel();
            }
        });
        panel.add(flucht);
    }

    /**
     * fügt zur Verfügung stehende Items als Button ein
     * @param panel
     * @return
     */
    private ArrayList<JButton> itemButtoms(JPanel panel) {
        ArrayList<Item> items = level.getItemList();
        ArrayList<JButton> itemButtons = new ArrayList();
        for (int i = 0; i<items.size(); i++) {
            JReferencingButton current = new JReferencingButton(items.get(i).getName(), level.getItemAmountList().get(i));
            JLabel label = new JLabel(level.getItemAmountList().get(i).toString());
            current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(current.getAmount() > 0){
                        current.setAmount(current.getAmount() - 1);
                        label.setText(String.valueOf(current.getAmount()));

                    }

                }
            });
            itemButtons.add(current);
            panel.add(current);
            panel.add(label);
        }
        return  itemButtons;

    }

    /**
     * fügt Item-Buttons in Rucksack ein
     * @param panel
     * @param itemButtons
     * @return
     */
    private ArrayList<JButton> itemRucksackButtons(JPanel panel, ArrayList<JButton> itemButtons) {
        ArrayList<JButton> buttons = new ArrayList<>();
        for (int i = 0; i<itemButtons.size(); i++) {
            JButton current = new JButton(itemButtons.get(i).getText());
            JLabel label = new JLabel("0");
            buttons.add(current);
            panel.add(current);
            panel.add(label);
        }
        return  buttons;
    }


}