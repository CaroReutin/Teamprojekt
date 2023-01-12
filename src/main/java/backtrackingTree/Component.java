package backtrackingTree;

import rucksack.Item;

/**
 * this class represents a component in the backtracking tree.
 * It is a part of the design patter composite.
 */
public abstract class Component {
  /**
   *  represents the current weight that is the rucksack at this stage
   */
  private int currentWeight;

  /**
   * represents the current value that is the rucksack at this stage
   */
  private int currentValue;

  /**
   * the item which assigned to this node
   */
  private Item item;

  /**
   * the maximum capacity of the rucksack
   */
  private final int capacity;

  /**
   * the parent of this instance
   */
  private final Component parent;

  /**
   * constructor for this class
   * @param item sets the item for this instance
   * @param weightBefore the weight before the new current weight gets calculated
   * @param valueBefore the value before the new value gets calculated
   * @param capacity the maximum capacity of the rucksack
   * @param parent the parent of this instance
   */
  public Component (Item item, int weightBefore, int valueBefore, int capacity, Component parent) {
    this.item = item;
    this.currentValue = valueBefore + item.getValue();
    this.currentWeight = weightBefore + item.getWeight();
    this.capacity = capacity;
    this.parent = parent;
  }

  /**
   * this method adds a component to this node as its next
   *
   * @param item is added as next to this instance
   */
  public abstract boolean add(Item item);

  /**
   * this method deletes a component which comes after this instance
   *
   * @param component to be deleted
   */
  public abstract boolean delete(Component component);

  /**
   * returns the child object of this instance at its index
   * @param index the index where child can be found
   */
  public void getChild(int index) {

  }

  /**
   * return item of this instance
   * @return said item
   */
  public Item getItem() {
    return item;
  }

  /**
   * returns the current weight
   * @return currentWeight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * returns the current value
   * @return currentValue
   */
  public int getCurrentValue() {
    return currentValue;
  }


  /**
   * returns the maximum capacity of the rucksack
   * @return maximum capacity
   */
  public int getCapacity() {
    return capacity;
  }
}
