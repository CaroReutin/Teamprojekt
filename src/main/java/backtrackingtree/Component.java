package backtrackingtree;

import rucksack.Item;

/**
 * this class represents a component in the backtracking tree.
 * It is a part of the design patter composite.
 */
public abstract class Component {
  /**
   * represents the current weight that is the rucksack at this stage.
   */
  private final int currentWeight;

  /**
   * represents the current value that is the rucksack at this stage.
   */
  private final int currentValue;

  /**
   * the item which assigned to this node.
   */
  private final Item item;

  /**
   * the maximum capacity of the rucksack.
   */
  private final int capacity;

  /**
   * the parent of this instance.
   */
  private final Component parent;

  /**
   * constructor for this class.
   *
   * @param bagItem     sets the item for this instance
   * @param oldWeight   the weight before the new current weight
   * @param oldValue    the value before the new value gets calculated
   * @param bagCapacity the maximum capacity of the rucksack
   * @param myParent    the parent of this instance
   */
  public Component(final Item bagItem, final int oldWeight,
                   final int oldValue, final int bagCapacity,
                   final Component myParent) {
    item = bagItem;
    currentValue = oldValue + bagItem.getValue();
    currentWeight = oldWeight + bagItem.getWeight();
    capacity = bagCapacity;
    parent = myParent;
  }

  /**
   * this method adds a component to this node as its next.
   *
   * @param childItem is added as next to this instance
   * @return true if child was added successfully
   */
  public abstract boolean add(Item childItem);

  /**
   * returns the child object of this instance at its index.
   *
   * @param index the index where child can be found
   */
  public void getChild(final int index) {

  }

  /**
   * returns item of this instance.
   *
   * @return said item
   */
  public Item getItem() {
    return item;
  }

  /**
   * returns the current weight.
   *
   * @return currentWeight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * returns the current value.
   *
   * @return currentValue
   */
  public int getCurrentValue() {
    return currentValue;
  }


  /**
   * returns the maximum capacity of the rucksack.
   *
   * @return maximum capacity
   */
  public int getCapacity() {
    return capacity;
  }
}
