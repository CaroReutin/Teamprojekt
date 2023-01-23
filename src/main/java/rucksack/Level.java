package rucksack;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

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
  private final ArrayList<Item> itemList;
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
   * @param itemList       ArrayList of available Items
   * @param itemAmountList ArrayList of Integers where itemAmountList.get(i)
   *                       is the amount of itemList.get(i)
   *                       that are present in the Rucksack.Level
   * @param levelIndex     the index of the level
   * @param capacity       the capacity
   */
  public Level(final ArrayList<Item> itemList,
               final ArrayList<Integer> itemAmountList,
               final int levelIndex, final int capacity) {
    this.capacity = capacity;
    this.levelindex = levelIndex;
    this.itemList = itemList;
    this.itemAmountList = itemAmountList;
    this.availableItemAmountList = new ArrayList<>();
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
   * amount of steps needed until now
   * a step is either "putting item in rucksack" or "removing items from rucksack"
   */
  private int counter = 0;


  /**
   * counts the number of steps needed; adding and removing items both count as a step
   * @return current value of the counter
   */
  public int getCounter() {
    return counter;
  }


  /**
   * sets the counter to a new value
   * @param counter the new value of the counter which overwrites the current one
   */
  public void setCounter(int counter) {
    this.counter = counter;
  }

 /** updates value and weight of the rucksack if item is added
   * @param item to be added
   */
  /*public void addItem(Item item) {
    if((currentWeight + item.getWeight()) <= currentCapacity) {
      if(items.contains(item)) {
        int index = items.indexOf(item);
        amount.set(index, amount.get(index) + 1);
      } else{
        items.add(item);
        amount.add(1);
      }
      currentValue += item.getValue();
      currentWeight += item.getWeight();
      counter++;
    }

  }*/

  /**
   * updates value and weight of the rucksack if item is removed
   * @param item to be removed
   */
 /* public void removeItem(Item item) {
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
    counter++;
  }*/



  /**
   * Instantiates a new Level.
   *
   * @param itemList       ArrayList of available Items
   * @param itemAmountList ArrayList of Integers where itemAmountList.get(i) is
   *                       the amount of itemList.get(i) that are present
   *                       in the Rucksack.Level
   * @param robber         the Robber
   * @param levelIndex     the index of the level
   * @param capacity       the capacity
   */
  public Level(final ArrayList<Item> itemList,
               final ArrayList<Integer> itemAmountList,
               final Robber robber, final int levelIndex, final int capacity) {
    this.capacity = capacity;
    this.levelindex = levelIndex;
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
   * Get rucksack capacity int.
   *
   * @return Returns the capacity of the Rucksack
   */
  public int getRucksackCapacity() {
    return capacity;
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
   * in the Rucksack.Level
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

  public Robber getRobber() {
    return robber;
  }
}
