package rucksack;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rucksack {
  /**
   * the list with all items.
   */
  @XmlElement
  private ArrayList<Item> itemList;
  /**
   * the list with the amount of the items.
   */
  @XmlElement
  private final ArrayList<Integer> itemAmountList;
  /**
   * the list with the amount of available items.
   */
  private ArrayList<Integer> availableItemAmountList;
  /**
   * the list of the amount in the rucksack.
   */
  private ArrayList<Integer> inRucksackAmountList;
  /**
   * the current value.
   */
  private int currentValue;
  /**
   * the current weight.
   */
  private int currentWeight;
  /**
   * the capacity of the rucksack.
   */
  @XmlElement
  private final int capacity;

  public Rucksack(final ArrayList<Item> myItemList,
                  final ArrayList<Integer> myItemAmountList,
                  final int myCapacity) {
    this.capacity = myCapacity;
    this.itemList = myItemList;
    this.itemAmountList = myItemAmountList;
    this.availableItemAmountList = new ArrayList<>();
    availableItemAmountList.addAll(myItemAmountList);
    inRucksackAmountList = new ArrayList<>();
    for (int i = 0; i < myItemAmountList.size(); i++) {
      inRucksackAmountList.add(0);
    }
    currentWeight = 0;
    currentValue = 0;
  }

  private Rucksack() {
    this.itemList = new ArrayList<>();
    this.itemAmountList = new ArrayList<>();
    this.availableItemAmountList = new ArrayList<>();
    this.inRucksackAmountList = new ArrayList<>();
    currentWeight = 0;
    currentValue = 0;
    capacity = -1;
  }

  public void reset() {
    inRucksackAmountList = new ArrayList<>();
    availableItemAmountList = new ArrayList<>();
    for (Integer amount : itemAmountList) {
      inRucksackAmountList.add(0);
      availableItemAmountList.add(amount);
    }
    currentWeight = 0;
    currentValue = 0;
  }

  public void moveToRucksack(final int i) {
    availableItemAmountList.set(i, availableItemAmountList.get(i) - 1);
    inRucksackAmountList.set(i, inRucksackAmountList.get(i) + 1);
    currentValue += itemList.get(i).getValue();
    currentWeight += itemList.get(i).getWeight();
  }

  public void moveFromRucksack(final int i) {
    availableItemAmountList.set(i, availableItemAmountList.get(i) + 1);
    inRucksackAmountList.set(i, inRucksackAmountList.get(i) - 1);
    currentValue -= itemList.get(i).getValue();
    currentWeight -= itemList.get(i).getWeight();
  }

  public void turnIntoBacktracking() {
    ArrayList<Item> temp = new ArrayList<>();
    for (int i = 0; i < itemList.size(); i++) {
      Item tempItem = itemList.get(i);
      BacktrackingItem newItem =
          new BacktrackingItem(tempItem.getValue(), tempItem.getWeight(), tempItem.getName());
      temp.add(newItem);
    }
    itemList = temp;
  }

  public ArrayList<Integer> getItemAmountList() {
    return itemAmountList;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public int getCurrentWeight() {
    return currentWeight;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getInRucksackAmount(int i) {
    return inRucksackAmountList.get(i);
  }

  public int getAvailableItemAmount(int i) {
    return availableItemAmountList.get(i);
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }
}
