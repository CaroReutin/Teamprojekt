import java.util.ArrayList;

public class Level {
    /**
     * Greedy -> Gieriger Ganove
     * Backtracking -> Backtracking Bandit
     * Else -> Dr. Meta
     */
    enum Robber{GIERIGER_GANOVE,BACKTRACKING_BANDIT,DR_META}

    private Rucksack rucksack;
    private ArrayList<Item> itemList;
    private ArrayList<Item> availableItemList;
    private ArrayList<Integer> itemAmountList;
    private ArrayList<Integer> availableItemAmountList;
    private ArrayList<String> tips;
    private Robber robber;

    /**
     * By default, has Dr.Meta as Robber.
     * Has no tips.
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Level
     */
    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList) {
        this.rucksack = rucksack;
        this.itemList = itemList;
        this.tips = new ArrayList<>();
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = Robber.DR_META;
    }

    /**
     * By default, has Dr.Meta as Robber.
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Level
     * @param tips ArrayList of String that are the tips
     */

    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList,ArrayList<String> tips) {
        this.rucksack = rucksack;
        this.tips = tips;
        this.itemList = itemList;
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = Robber.DR_META;
    }
    /**
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Level
     * @param tips ArrayList of String that are the tips
     * @param robber the Robber
     */
    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList,ArrayList<String> tips,Robber robber) {
        this.rucksack = rucksack;
        this.tips = tips;
        this.itemList = itemList;
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = robber;
    }

    /**
     * Has no tips.
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Level
     * @param robber the Robber
     */
    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList, Robber robber) {
        this.rucksack = rucksack;
        this.tips = new ArrayList<>();
        this.itemList = itemList;
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = robber;
    }

    /**
     *
     * @return Returns the capacity of the Rucksack
     */
    public int getRucksackCapacity(){
        return rucksack.getCapacity();
    }

    /**
     * NOTE: this does not return the items still available in the level (i.e. the ones not in the Backpack)
     *
     * @return Returns the items that exist in the Level
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * NOTE: this does not return the amounts of the items still available in the level (i.e. the ones not in the Backpack)
     *
     * @return Returns the amounts of the items that exist in the Level
     */
    public ArrayList<Integer> getItemAmountList() {
        return itemAmountList;
    }

    /**
     * Can be empty
     *
     * @return Returns the tips
     */
    public ArrayList<String> getTips() {
        return tips;
    }

    /**
     * By default, tips are not allowed
     *
     * @param isAllowed true = tips are allowed, false = tips are locked
     */
    public static void tipsAllowed(boolean isAllowed){
        //TODO
    }
}
