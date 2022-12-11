package Rucksack;

import java.util.ArrayList;

public class Rucksack {
    /**
     * maximal weight that can be put into the rucksack
     */
    //TODO capacity final machen
    private int capacity;
    /**
     * list of items inside the rucksack
     */
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * contains amount of the corresponding items at the same position
     */
    private ArrayList<Integer> amount = new ArrayList<>();
    private int currentWeight = 0;
    private int currentValue = 0;

    public Rucksack(int capacity) {
        this.capacity = capacity;

    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * adds up the weight of all items
     * @return current weight in the rucksack
     */
    public int getCurrentWeight() {
        ArrayList<Item> items = this.getItems();
        int sum = 0;
        for(int i = 0; i < items.size(); i++) {
            sum += items.get(i).getWeight() * amount.get(i);
        }
        return sum;
    }

    /**
     * adds up the value of all items
     * @return current value in the rucksack
     */
    public int getCurrentValue() {
        ArrayList<Item> items = this.getItems();
        int sum = 0;
        for(int i = 0; i < items.size(); i++) {
            sum += items.get(i).getValue() * amount.get(i);
        }
        return sum;
    }

    /**
     * updates value and weight of the rucksack if item is added
     * @param item to be added
     */
    public void addItem(Item item) {
        if((currentWeight + item.getWeight()) <= capacity ) {
            if(items.contains(item)) {
                int index = items.indexOf(item);
                amount.set(index, amount.get(index) + 1);
            } else{
                items.add(item);
                amount.add(1);
            }
            currentValue += item.getValue();
            currentWeight += item.getWeight();
        }

    }

    /**
     * updates value and weight of the rucksack if item is removed
     * @param item to be removed
     */
    public void removeItem(Item item) {
        if(items.contains(item)) {
            int index = items.indexOf(item);
            if(index == 1) {
                items.remove(index);
                amount.remove(index);
            } else {
                amount.set(index, amount.get(index) - 1);
            }
            currentValue -= item.getValue();
            currentWeight -= item.getWeight();
        }
    }

    public ArrayList<Integer> getAmountList() {
        return amount;
    }

    public void clearRucksack(){
        items.clear();
        amount.clear();
        currentWeight = 0;
        currentValue = 0;
    }
}
