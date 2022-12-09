import GUI_Level.GUILevelPage;
import Rucksack.Item;
import Rucksack.Rucksack;
import Rucksack.Level;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

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
        GUILevelPage gui = new GUILevelPage(new Level(rucksack,items, amount));

    }
}