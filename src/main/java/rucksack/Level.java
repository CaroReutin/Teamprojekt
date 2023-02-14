package rucksack;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * The class that holds the level information.
 */
@XStreamAlias("Level")
public class Level implements Serializable {
  /**
   * Setter method for the current value held in the rucksack.
   *
   * @param i integer the value shall be set to
   */
  public void setCurrentValue(final int i) {
    this.myRucksack.setCurrentValue(i);
  }

  /**
   * Setter method for the current weight held in the rucksack.
   *
   * @param i integer the weight shall be set to
   */
  public void setCurrentWeight(final int i) {
    this.myRucksack.setCurrentWeight(i);
  }

  /**
   * Setter method for assigning an image icon to an item.
   *
   * @param i integer value of the item's position in the item list.
   * @param imageIcon the image icon to be assigned
   */
  public void setItemIcon(final int i, final ImageIcon imageIcon) {
    Item oldItem = this.myRucksack.getItemList().get(i);
    this.myRucksack.getItemList().set(i,
      new Item(oldItem.getValue(), oldItem.getWeight(),
        oldItem.getName(), imageIcon));
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
  @XStreamAlias("Robber")
  private final Robber robber;

  /**
   * The rucksack of this level.
   */
  @XStreamAlias("Rucksack")
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
    this.robber = myRobber;
  }

  /**
   * Only for level editor.
   */
  private Level() {
    this.myRucksack = new Rucksack(new ArrayList<>(), new ArrayList<>(), -1);
    this.robber = null;
  }

  /**
   * NOTE: this does not return the items still available in the level
   * (i.e. the ones not in the rucksack)
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
   *         in the Rucksack.Level
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
    assert this.robber != null;
    if (this.robber.equals(Robber.BACKTRACKING_BANDIT)) {
      myRucksack.turnIntoBacktracking();
    }
  }

  /**
   * Getter method for the array list containing the trash amount list.
   *
   * @return array list containing the trash amount list
   */
  public ArrayList<Integer> getInTrashAmountList() {
    return myRucksack.getInTrashAmountList();
  }

  /**
   * Setter method for an element in the trash amount list.
   *
   * @param index where to set a new value in the list
   * @param newAmount new value to set in the list
   */
  public void setInTrashAmountList(final int index, final int newAmount) {
    myRucksack.setInTrashAmountList(index, newAmount);
  }

  /**
   * Setter method for an element in the available item amount list.
   *
   * @param index where to set a new value in the list
   * @param newAmount new value to set in the list
   */
  public void setAvailableItemAmountList(final int index, final int newAmount) {
    myRucksack.setAvailableItemAmountList(index, newAmount);
  }

  /**
   * Setter method for an element in the rucksack amount list.
   *
   * @param index where to set a new value in the list
   * @param newAmount new value to set in the list
   */
  public void setInRucksackAmountList(final int index, final int newAmount) {
    myRucksack.setInRucksackAmountList(index, newAmount);
  }

  /**
   * Getter method for the list of backtracking items.
   *
   * @return the list of backtracking items
   */
  public ArrayList<BacktrackingItem> getBacktrackingItemList() {
    return myRucksack.getBacktrackingItemList();
  }

}
