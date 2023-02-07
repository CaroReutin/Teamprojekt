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
  public void setCurrentValue(final int i) {
    this.myRucksack.setCurrentValue(i);
  }

  public void setCurrentWeight(final int i) {
    this.myRucksack.setCurrentWeight(i);
  }

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
   * the current robber.
   */
  @XmlElement
  private final Robber robber;
  /**
   * the level index.
   */
  private final int levelindex;
  /**
   * the rucksack of this level
   */
  @XmlElement
  private final Rucksack myRucksack;

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
    this.myRucksack = new Rucksack(myItemList, myItemAmountList, myCapacity);
    this.levelindex = levelIndex;
    this.robber = Robber.DR_META;
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
    this.myRucksack = new Rucksack(myItemList, myItemAmountList, myCapacity);
    this.levelindex = levelIndex;
    this.robber = myRobber;
  }

  /**
   * Only for level editor.
   */
  private Level() {
    this.myRucksack = new Rucksack(new ArrayList<>(), new ArrayList<>(), -1);
    this.levelindex = -1;
    this.robber = null;
  }

  /**
   * NOTE: this does not return the items still available in the level
   * (i.e. the ones not in the Backpack)
   *
   * @return Returns the items that exist in the Rucksack.Level
   */
  public ArrayList<Item> getItemList() {
    return myRucksack.getItemList();
  }

  /**
   * NOTE: this does not return the amounts of items still available
   * in the level (i.e. the ones not in the Backpack)
   *
   * @return Returns the amounts of the items that exist in the Rucksack.Level
   */
  public ArrayList<Integer> getItemAmountList() {
    return myRucksack.getItemAmountList();
  }

  /**
   * Gets item amount available.
   *
   * @param i the
   * @return Returns the amounts of the items that are still available
   * in the Rucksack.Level
   */
  public int getItemAmountAvailable(final int i) {
    return myRucksack.getAvailableItemAmount(i);
  }

  /**
   * Gets item amount in rucksack.
   *
   * @param i the
   * @return the item amount in rucksack
   */
  public int getItemAmountInRucksack(final int i) {
    return myRucksack.getInRucksackAmount(i);
  }

  /**
   * Reset level.
   */
  public void resetLevel() {
    myRucksack.reset();
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
    myRucksack.moveToRucksack(i);
  }

  /**
   * Move from rucksack.
   *
   * @param i the index of the item to be moved
   */
  public void moveFromRucksack(final int i) {
    myRucksack.moveFromRucksack(i);
  }

  /**
   * Gets current value.
   *
   * @return the current value
   */
  public int getCurrentValue() {
    return myRucksack.getCurrentValue();
  }

  /**
   * Gets capacity.
   *
   * @return the capacity
   */
  public int getCapacity() {
    return myRucksack.getCapacity();
  }

  /**
   * Gets current weight.
   *
   * @return the current weight
   */
  public int getCurrentWeight() {
    return myRucksack.getCurrentWeight();
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
      myRucksack.turnIntoBacktracking();
    }
  }

  public ArrayList<Integer> getInTrashAmountList() {
    return myRucksack.getInTrashAmountList();
  }

  public void setInTrashAmountList(final int index, final int newAmount) {
    myRucksack.setInTrashAmountList(index, newAmount);
  }

  public void setAvailableItemAmountList(final int index, final int newAmount) {
    myRucksack.setAvailableItemAmountList(index, newAmount);
  }

  public void setInRucksackAmountList(final int index, final int newAmount) {
    myRucksack.setInRucksackAmountList(index, newAmount);
  }

  public ArrayList<BacktrackingItem> getBacktrackingItemList() {
    return myRucksack.getBacktrackingItemList();
  }

}
