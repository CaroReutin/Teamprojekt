package rucksack;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * the rucksack.
 */
@XStreamAlias("Rucksack")
public class Rucksack {
  /**
   * the list with all items.
   */
  @XStreamAlias("ItemList")
  private final ArrayList<Item> itemList;
  /**
   * the list with the amount of the items.
   */
  @XStreamAlias("ItemAmountList")
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
  @XStreamAlias("capacity")
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

  /**
   * Array list containing all items of a backtracking level.
   */
  private ArrayList<BacktrackingItem> backtrackingItemList = new ArrayList<>();
  /**
   * Array list containing all items that were put into the trash
   * in a backtracking level.
   */
  private ArrayList<Integer> inTrashAmountList = new ArrayList<>();

  /**
   * Turns level into backtracking level if needed.
   */
  public void turnIntoBacktracking() {
    ArrayList<BacktrackingItem> temp = new ArrayList<>();
    for (Item tempItem : itemList) {
      BacktrackingItem newItem = new BacktrackingItem(
              tempItem.getValue(), tempItem.getWeight(),
              tempItem.getName(), tempItem.getImageIcon());
      temp.add(newItem);
      //set Item is in trash to 0
      inTrashAmountList.add(0);
    }
    backtrackingItemList = temp;
    //backtrackingItemList has to be sorted for BacktrackingNode
    backtrackingItemList.sort(Comparator.comparingInt(
        Item::getWeight).reversed());
  }

  /**
   * Getter method for the trash amount list.
   *
   * @return the trash amount list
   */
  public ArrayList<Integer> getInTrashAmountList() {
    return inTrashAmountList;
  }

  /**
   * Setter method for an element in the trash amount list.
   *
   * @param index where to set a new value
   * @param newAmount new value to be set
   */
  public void setInTrashAmountList(final int index, final int newAmount) {
    inTrashAmountList.set(index, newAmount);
  }

  /**
   * Setter method for an element in the list of available items.
   *
   * @param index where to set a new value
   * @param newAmount new value to be set
   */
  public void setAvailableItemAmountList(final int index, final int newAmount) {
    availableItemAmountList.set(index, newAmount);
  }

  /**
   * Setter method for an element in the rucksack amount list.
   *
   * @param index where to set a new value
   * @param newAmount new value to be set
   */
  public void setInRucksackAmountList(final int index, final int newAmount) {
    inRucksackAmountList.set(index, newAmount);
  }

  /**
   * Getter method for the rucksack amount list.
   *
   * @return the rucksack amount list
   */
  public ArrayList<Integer> getInRucksackAmountList() {
    return inRucksackAmountList;
  }

  /**
   * Getter method for the list of items in a backtracking level.
   *
   * @return the backtracking item list
   */
  public ArrayList<BacktrackingItem> getBacktrackingItemList() {
    return backtrackingItemList;
  }

  /**
   * Setter method for the current value held in the rucksack.
   *
   * @param i new value of the rucksack
   */
  public void setCurrentValue(final int i) {
    currentValue = i;
  }

  /**
   * Setter method for the current weight held in the rucksack.
   *
   * @param i new weight of the rucksack
   */
  public void setCurrentWeight(final int i) {
    currentWeight = i;
  }
}
