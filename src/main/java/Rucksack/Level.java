package Rucksack;

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
    private final ArrayList<Integer> defaultItemAmountList;
    private ArrayList<Integer> currentItemAmountList;
    private ArrayList<Integer> availableItemAmountList;
    private ArrayList<String> tips;
    private Robber robber;
    private int levelindex;

    /**
     * By default, has Dr.Meta as Robber.
     * Has no tips.
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Rucksack.Level
     * @param levelindex the index of the level
     */
    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList, int levelindex) {
        this.levelindex = levelindex;
        this.rucksack = rucksack;
        this.itemList = itemList;
        this.tips = new ArrayList<>();
        this.currentItemAmountList = new ArrayList<Integer>(itemAmountList);
        this.defaultItemAmountList = new ArrayList<Integer>(itemAmountList);
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = Robber.DR_META;

        for (int i = 0; i < itemList.size(); i++) {
            rucksack.getItems().add(itemList.get(i));
            rucksack.getAmountList().add(0);
        }
    }


    /**
     *
     * @param rucksack the Rucksack
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Rucksack.Level
     * @param tips ArrayList of String that are the tips
     * @param robber the Robber
     * @param levelindex the index of the level
     */
    public Level(Rucksack rucksack, ArrayList<Item> itemList, ArrayList<Integer> itemAmountList, ArrayList<String> tips, Robber robber, int levelindex) {
        this.levelindex = levelindex;
        this.rucksack = rucksack;
        this.tips = tips;
        this.itemList = itemList;
        this.currentItemAmountList = itemAmountList;
        this.defaultItemAmountList = itemAmountList;
        this.availableItemAmountList = itemAmountList;
        this.availableItemList = itemList;
        this.robber = robber;

        for (int i = 0; i < itemList.size(); i++) {
            rucksack.getItems().add(itemList.get(i));
            rucksack.getAmountList().add(0);
        }
    }

public void setCurrentItemAmountList(ArrayList<Integer> currentItemAmountList) {
        this.currentItemAmountList = currentItemAmountList;
        System.out.println("Item amount was changed");
}
    /**
     *
     * @return Returns the capacity of the Rucksack
     */
    public int getRucksackCapacity(){
        return rucksack.getCurrentCapacity();
    }

    public Rucksack getRucksack() {
        return rucksack;
    }

    /**
     * NOTE: this does not return the items still available in the level (i.e. the ones not in the Backpack)
     *
     * @return Returns the items that exist in the Rucksack.Level
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * NOTE: this does not return the amounts of the items still available in the level (i.e. the ones not in the Backpack)
     *
     * @return Returns the amounts of the items that exist in the Rucksack.Level
     */
    public ArrayList<Integer> getCurrentItemAmountList() {
        return currentItemAmountList;
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

    public void endOfLevel() {
        if(this.robber.equals(Robber.DR_META)) {
            //TODO zu Levelauswahl führen - muss gemerged werden
        } else if(this.robber.equals(Robber.GIERIGER_GANOVE)){
            //TODO brauche Solver - muss gemerged werden
        } else {
            //TODO
        }
    }

    /*
    resets the level through resetting the rucksack first and after that the ItemAmounts are reset
     */
    public void resetLevel() {
        rucksack.resetRucksack();
        currentItemAmountList = new ArrayList<Integer>(defaultItemAmountList);
        System.out.println("zur Verfügung stehende Itemanzahlen:" + getCurrentItemAmountList());
    }

    public int getLevelNumber() {
        return this.levelindex;
    }

    public Robber getRobber() {
        return this.robber;
    }
}
