package Rucksack;

import java.util.ArrayList;

public class Level {
    public int getLevelNumber() {
        return this.levelindex;
    }

    public void moveToRucksack(int i) {
        availableItemAmountList.set(i,availableItemAmountList.get(i) - 1);
        inRucksackAmountList.set(i,inRucksackAmountList.get(i) + 1);
        currentValue += itemList.get(i).getValue();
        currentWeight += itemList.get(i).getWeight();
    }
    public void moveFromRucksack(int i) {
        availableItemAmountList.set(i,availableItemAmountList.get(i) + 1);
        inRucksackAmountList.set(i,inRucksackAmountList.get(i) - 1);
        currentValue -= itemList.get(i).getValue();
        currentWeight -= itemList.get(i).getWeight();
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }


    /**
     * Greedy -> Gieriger Ganove
     * Backtracking -> Backtracking Bandit
     * Else -> Dr. Meta
     */
    enum Robber{GIERIGER_GANOVE,BACKTRACKING_BANDIT,DR_META}

    private final ArrayList<Item> itemList;
    private final ArrayList<Integer> itemAmountList;
    private ArrayList<Integer> availableItemAmountList;
    private ArrayList<Integer> inRucksackAmountList;
    private int currentValue;
    private int currentWeight;
    private final ArrayList<String> tips;
    private final Robber robber;
    private final int levelindex;
    private final int capacity;

    /**
     * By default, has Dr.Meta as Robber.
     * Has no tips.
     *
     * @param itemList       ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Rucksack.Level
     * @param levelindex     the index of the level
     */
    public Level(ArrayList<Item> itemList, ArrayList<Integer> itemAmountList, int levelindex, int capacity) {
        this.capacity = capacity;
        this.levelindex = levelindex;
        this.itemList = itemList;
        this.tips = new ArrayList<>();
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList =  new ArrayList<>();
        for (int i = 0; i < itemAmountList.size(); i++) {
            availableItemAmountList.add(itemAmountList.get(i));
        }
        this.robber = Robber.DR_META;
        inRucksackAmountList = new ArrayList<>();
        for (int i = 0; i < itemAmountList.size(); i++) {
            inRucksackAmountList.add(0);
        }
        currentWeight = 0;
        currentValue = 0;
    }


    /**
     *
     * @param itemList ArrayList of available Items
     * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is the amount of itemList.get(i) that are present in the Rucksack.Level
     * @param tips ArrayList of String that are the tips
     * @param robber the Robber
     * @param levelindex the index of the level
     */
    public Level(ArrayList<Item> itemList, ArrayList<Integer> itemAmountList, ArrayList<String> tips, Robber robber, int levelindex, int capacity) {
        this.capacity = capacity;
        this.levelindex = levelindex;
        this.tips = tips;
        this.itemList = itemList;
        this.itemAmountList = itemAmountList;
        this.availableItemAmountList = new ArrayList<>();
        for (int i = 0; i < itemAmountList.size(); i++) {
            availableItemAmountList.add(itemAmountList.get(i));
        }
        inRucksackAmountList = new ArrayList<>();
        for (int i = 0; i < itemAmountList.size(); i++) {
            inRucksackAmountList.add(0);
        }
        this.robber = robber;
        currentWeight = 0;
        currentValue = 0;
    }


    /**
     *
     * @return Returns the capacity of the Rucksack
     */
    public int getRucksackCapacity(){
        return capacity;
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
    public ArrayList<Integer> getItemAmountList() {
        return itemAmountList;
    }

    /**
     * @return Returns the amounts of the items that are still available in the Rucksack.Level
     */
    public int getItemAmountAvailable(int i) {
        return availableItemAmountList.get(i);
    }

    public int getItemAmountInRucksack(int i) {
        return inRucksackAmountList.get(i);
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

    public void resetLevel(){
        inRucksackAmountList = new ArrayList<>();
        for (int i = 0; i < itemAmountList.size(); i++) {
            inRucksackAmountList.add(0);
            availableItemAmountList.set(i,itemAmountList.get(i));
        }
        currentWeight = 0;
        currentValue = 0;
    }
}
