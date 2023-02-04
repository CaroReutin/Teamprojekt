package rucksack;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class that holds the level information.
 */
@XmlRootElement
public class Level implements Serializable {
  /**
   * Greedy -> Gieriger Ganove
   * Backtracking -> Backtracking Bandit
   * Else -> Dr. Meta
   */
  public enum Robber {
    /**
     * Gieriger ganove robber.
     */
    GIERIGER_GANOVE,
    /**
     * Backtracking bandit robber.
     */
    BACKTRACKING_BANDIT,
    /**
     * Dr meta robber.
     */
    DR_META
  }

  /**
   * the list with all items.
   */
  @XmlElement
  private ArrayList<Item> itemList;
  /**
   * the list with the amout of the items.
   */
  @XmlElement
  private final ArrayList<Integer> itemAmountList;
  /**
   * the lsit with the amout of available items.
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
   * the current robber.
   */
  @XmlElement
  private final Robber robber;
  /**
   * the level index.
   */
  private final int levelindex;
  /**
   * the capacity of the rucksack.
   */
  @XmlElement
  private final int capacity;

  /**
   * By default, has Dr.Meta as Robber.
   * Has no tips.
   *
   * @param myItemList       ArrayList of available Items
   * @param myItemAmountList ArrayList of Integers where itemAmountList.get(i)
   *                         is the amount of itemList.get(i)
   *                         that are present in the Rucksack.Level
   * @param levelIndex       the index of the level
   * @param myCapacity       the capacity
   */
  public Level(final ArrayList<Item> myItemList,
               final ArrayList<Integer> myItemAmountList,
               final int levelIndex, final int myCapacity) {
    this.capacity = myCapacity;
    this.levelindex = levelIndex;
    this.itemList = myItemList;
    this.itemAmountList = myItemAmountList;
    this.availableItemAmountList = new ArrayList<>();
    availableItemAmountList.addAll(myItemAmountList);
    this.robber = Robber.DR_META;
    inRucksackAmountList = new ArrayList<>();
    for (int i = 0; i < myItemAmountList.size(); i++) {
      inRucksackAmountList.add(0);
    }
    currentWeight = 0;
    currentValue = 0;
  }

  /**
   * amount of steps needed until now
   * a step is either "putting item in rucksack"
   * or "removing items from rucksack".
   */
  private int counter = 0;


  /**
   * counts the number of steps needed;
   * adding and removing items both count as a step.
   *
   * @return current value of the counter
   */
  public int getCounter() {
    return counter;
  }


  /**
   * sets the counter to a new value.
   *
   * @param myCounter the new value of the counter
   *                  which overwrites the current one
   */
  public void setCounter(final int myCounter) {
    this.counter = myCounter;
  }

  /**
   * Instantiates a new Level.
   *
   * @param myItemList       ArrayList of available Items
   * @param myItemAmountList ArrayList of Integers where
   *                         itemAmountList.get(i) is
   *                         the amount of itemList.get(i) that are present
   *                         in the Rucksack.Level
   * @param myRobber         the Robber
   * @param levelIndex       the index of the level
   * @param myCapacity       the capacity
   */
  public Level(final ArrayList<Item> myItemList,
               final ArrayList<Integer> myItemAmountList,
               final Robber myRobber, final int levelIndex,
               final int myCapacity) {
    this.capacity = myCapacity;
    this.levelindex = levelIndex;
    this.itemList = myItemList;
    this.itemAmountList = myItemAmountList;
    this.availableItemAmountList = new ArrayList<>();
    availableItemAmountList.addAll(myItemAmountList);
    inRucksackAmountList = new ArrayList<>();
    for (int i = 0; i < myItemAmountList.size(); i++) {
      inRucksackAmountList.add(0);
    }
    this.robber = myRobber;
    currentWeight = 0;
    currentValue = 0;
  }

  /**
   * Only for level editor.
   */
  private Level() {
    this.capacity = -1;
    this.levelindex = -1;
    this.itemList = new ArrayList<>();
    this.itemAmountList = new ArrayList<>();
    this.availableItemAmountList = new ArrayList<>();
    this.inRucksackAmountList = new ArrayList<>();
    this.robber = null;
    currentWeight = 0;
    currentValue = 0;
  }

  /**
   * NOTE: this does not return the items still available in the level
   * (i.e. the ones not in the Backpack)
   *
   * @return Returns the items that exist in the Rucksack.Level
   */
  public ArrayList<Item> getItemList() {
    return itemList;
  }

  /**
   * NOTE: this does not return the amounts of items still available
   * in the level (i.e. the ones not in the Backpack)
   *
   * @return Returns the amounts of the items that exist in the Rucksack.Level
   */
  public ArrayList<Integer> getItemAmountList() {
    return itemAmountList;
  }

  /**
   * Gets item amount available.
   *
   * @param i the
   * @return Returns the amounts of the items that are still available
   *      in the Rucksack.Level
   */
  public int getItemAmountAvailable(final int i) {
    return availableItemAmountList.get(i);
  }

  /**
   * Gets item amount in rucksack.
   *
   * @param i the
   * @return the item amount in rucksack
   */
  public int getItemAmountInRucksack(final int i) {
    return inRucksackAmountList.get(i);
  }

  /**
   * Reset level.
   */
  public void resetLevel() {
    inRucksackAmountList = new ArrayList<>();
    availableItemAmountList = new ArrayList<>();
    for (Integer amount : itemAmountList) {
      inRucksackAmountList.add(0);
      availableItemAmountList.add(amount);
    }
    currentWeight = 0;
    currentValue = 0;
  }

  /**
   * Gets level number.
   *
   * @return the level number
   */
  public int getLevelNumber() {
    return this.levelindex;
  }

  /**
   * Move to rucksack.
   *
   * @param i the
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
   * @param i the
   */
  public void moveFromRucksack(final int i) {
    availableItemAmountList.set(i, availableItemAmountList.get(i) + 1);
    inRucksackAmountList.set(i, inRucksackAmountList.get(i) - 1);
    currentValue -= itemList.get(i).getValue();
    currentWeight -= itemList.get(i).getWeight();
  }

  /**
   * Gets current value.
   *
   * @return the current value
   */
  public int getCurrentValue() {
    return currentValue;
  }

  /**
   * Gets capacity.
   *
   * @return the capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Gets current weight.
   *
   * @return the current weight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * gets the Robber.
   *
   * @return returns the Robber
   */
  public Robber getRobber() {
    return robber;
  }

  /**
   * Turns level into backtracking level if needed.
   */
  public void turnIntoBacktracking() {
    if (this.robber.equals(Robber.BACKTRACKING_BANDIT)) {
      ArrayList<Item> temp = new ArrayList<Item>();
      for (int i = 0; i < itemList.size(); i++) {
        Item tempItem = itemList.get(i);
        BacktrackingItem newItem = new BacktrackingItem(tempItem.getValue(), tempItem.getWeight(), tempItem.getName(), tempItem.getImageIcon());
        temp.add(newItem);
      }
      itemList = temp;
    }
  }

}
