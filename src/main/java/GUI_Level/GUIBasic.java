package GUI_Level;

import Rucksack.Item;
import Rucksack.Level;
import Rucksack.Rucksack;

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

        //Test ItemButtons
        Item coin = new Item(5, 1, "coin");
        Item crown = new Item(50, 8, "crown");
        Item pearl = new Item(11, 2, "pearl");
        Rucksack rucksack = new Rucksack(60);

        ArrayList<Item> items = new ArrayList<>();
        items.add(coin);
        items.add(crown);
        items.add(pearl);
        ArrayList<Integer> amount = new ArrayList<>();
        amount.add(10);
        amount.add(7);
        amount.add(4);
        //

        ArrayList<JButton> itemButtons = this.itemButtoms(rightPanel, new Level(rucksack,items, amount));
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
        panel.add(flucht);
    }

    /**
     * fügt zur Verfügung stehende Items als Button ein
     * @param panel
     * @param level
     * @return
     */
    private ArrayList<JButton> itemButtoms(JPanel panel, Level level) {
        ArrayList<Item> items = level.getItemList();
        ArrayList<JButton> itemButtons = new ArrayList();
        for (int i = 0; i<items.size(); i++) {
            JButton current = new JButton(items.get(i).getName());
            itemButtons.add(current);
            panel.add(current);
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
            buttons.add(current);
            panel.add(current);
        }
        return  buttons;
    }


}