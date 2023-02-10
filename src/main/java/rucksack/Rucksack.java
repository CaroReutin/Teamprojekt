package rucksack;

import java.util.ArrayList;
import java.util.Comparator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * the rucksack.
 */
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

  /**
   * makes a new Rucksack.
   *
   * @param myItemList       the item list
   * @param myItemAmountList the item amount list
   * @param myCapacity       the capacity
   */
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

  /**
   * level editor only.
   */
  private Rucksack() {
    this.itemList = new ArrayList<>();
    this.itemAmountList = new ArrayList<>();
    this.availableItemAmountList = new ArrayList<>();
    this.inRucksackAmountList = new ArrayList<>();
    currentWeight = 0;
    currentValue = 0;
    capacity = -1;
  }

  /**
   * resets the content of the Rucksack to its original content.
   */
  public void reset() {
    inRucksackAmountList = new ArrayList<>();
    availableItemAmountList = new ArrayList<>();
    inTrashAmountList = new ArrayList<>();
    for (Integer amount : itemAmountList) {
      inRucksackAmountList.add(0);
      inTrashAmountList.add(0);
      availableItemAmountList.add(amount);
    }
    currentWeight = 0;
    currentValue = 0;
  }

  /**
   * Move to rucksack.
   *
   * @param i the index of the item to be moved
   */
  public void moveToRucksack(final int i) {
    availableItemAmountList.set(i, availableItemAmountList.get(i) - 1);
    inRucksackAmountList.set(i, inRucksackAmountList.get(i) + 1);
    currentValue += itemList.get(i).getValue();
    currentWeight += itemList.get(i).getWeight();
  }

  /**
   * Move from rucksack.
   *
   * @param i the index of the item to be moved
   */
  public void moveFromRucksack(final int i) {
    availableItemAmountList.set(i, availableItemAmountList.get(i) + 1);
    inRucksackAmountList.set(i, inRucksackAmountList.get(i) - 1);
    currentValue -= itemList.get(i).getValue();
    currentWeight -= itemList.get(i).getWeight();
  }

  /**
   * returns the amount list.
   *
   * @return the amount list
   */
  public ArrayList<Integer> getItemAmountList() {
    return itemAmountList;
  }

  /**
   * returns the current value.
   *
   * @return the current value
   */
  public int getCurrentValue() {
    return currentValue;
  }

  /**
   * returns the current Weight.
   *
   * @return the current weight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * returns the capacity.
   *
   * @return the capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * returns the rucksack amount list at index i.
   *
   * @param i the index
   * @return the rucksack amount list
   */
  public int getInRucksackAmount(final int i) {
    return inRucksackAmountList.get(i);
  }

  /**
   * returns the available amount at index i.
   *
   * @param i the index
   * @return the available amount list
   */
  public int getAvailableItemAmount(final int i) {
    return availableItemAmountList.get(i);
  }

  /**
   * returns the item list.
   *
   * @return the item list
   */
  public ArrayList<Item> getItemList() {
    return itemList;
  }

  private ArrayList<BacktrackingItem> backtrackingItemList = new ArrayList<>();
  private ArrayList<Integer> inTrashAmountList = new ArrayList<>();

  /**
   * Turns level into backtracking level if needed.
   */
  public void turnIntoBacktracking() {
    ArrayList<BacktrackingItem> temp = new ArrayList<>();
    for (int i = 0; i < itemList.size(); i++) {
      Item tempItem = itemList.get(i);
      BacktrackingItem newItem = new BacktrackingItem(
          tempItem.getValue(), tempItem.getWeight(), tempItem.getName(), tempItem.getImageIcon());
      temp.add(newItem);
      //set Item is in trash to 0
      inTrashAmountList.add(0);
    }
    backtrackingItemList = temp;
    //backtrackingItemList has to be sorted for BacktrackingNode
    backtrackingItemList.sort(Comparator.comparingInt(
        Item::getWeight).reversed());
  }

  public ArrayList<Integer> getInTrashAmountList() {
    return inTrashAmountList;
  }

  public void setInTrashAmountList(final int index, final int newAmount) {
    inTrashAmountList.set(index, newAmount);
  }

  public void setAvailableItemAmountList(final int index, final int newAmount) {
    availableItemAmountList.set(index, newAmount);
  }

  public void setInRucksackAmountList(final int index, final int newAmount) {
    inRucksackAmountList.set(index, newAmount);
  }

  public ArrayList<Integer> getInRucksackAmountList() {
    return inRucksackAmountList;
  }

  public ArrayList<BacktrackingItem> getBacktrackingItemList() {
    return backtrackingItemList;
  }

  public void setCurrentValue(int i) {
    currentValue = i;
  }

  public void setCurrentWeight(int i) {
    currentWeight = i;
  }
}
