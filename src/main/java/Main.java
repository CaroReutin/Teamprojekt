import GUI_Level.GUIBasic;
import Rucksack.Item;
import Rucksack.Rucksack;
import Rucksack.Level;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
       /* JFrame frame = new JFrame();
        frame.setTitle("Teamprojekt");
        frame.setSize(400,400);
        frame.setVisible(true);*/

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
        GUIBasic gui = new GUIBasic(new Level(rucksack,items, amount));

    }
}