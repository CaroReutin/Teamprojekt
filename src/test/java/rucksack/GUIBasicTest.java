package rucksack;
import gui.level.GuiLevelPage;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

class GUIBasicTest{
    @Test
    void runBasicGui(){
        Item coin = new Item(5, 1, "coin", new ImageIcon("src/main/resources/icons/Münze.png"));
        Item crown = new Item(50, 8, "crown", new ImageIcon("src/main/resources/icons/Krone.png"));
        Item pearl = new Item(11, 2, "pearl", new ImageIcon("src/main/resources/icons/Perle.png"));

        ArrayList<Item> items = new ArrayList<>();
        items.add(coin);
        items.add(crown);
        items.add(pearl);
        ArrayList<Integer> amount = new ArrayList<>();
        amount.add(10);
        amount.add(7);
        amount.add(4);
        //
        GuiLevelPage gui = new GuiLevelPage(new Level(items, amount,0,60));

    }


}
