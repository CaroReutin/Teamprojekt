package Solving;

import Rucksack.Item;

import java.util.ArrayList;

public class AppData {
    public static final int LEVELAMOUNT = 15;
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    public static void initialize(){
        passwords.add("Gr33dy");
        items.add(new Item(5, 1, "coin"));
        items.add(new Item(50, 8, "crown"));
        items.add(new Item(11, 2, "pearl"));
    }

    public static String getPassword(int i){
        return passwords.get(i);
    }

    public static int getPasswordAmount(){
        return passwords.size();
    }

    /**
     *
     * @param name the unique name of the item
     * @return returns a new Instance of the wanted item if it is in the ArrayList else it returns null
     */
    public static Item generateItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().matches(name)){
                return new Item(items.get(i).getValue(),items.get(i).getWeight(),items.get(i).getName());
            }
        }
        return null;
    }
}
