package backtrackingtree;

import java.util.ArrayList;

import rucksack.BacktrackingItem;
import rucksack.Item;


/**
 * this class is part of the design pattern composite and
 * represents the composite in that pattern.
 */
public class BacktrackingNode {

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
  private final BacktrackingItem item;

  /**
   * the parent of this instance.
   */
  private final BacktrackingNode parent;

  /**
   * the left child of this node.
   */
  private BacktrackingNode leftChild;

  /**
   * the right child of this node.
   */
  private BacktrackingNode rightChild;

  /**
   * the maximum capacity of the rucksack.
   */
  private final int capacity;

  /**
   * a list where every item from the level is saved + empty item.
   * The order is descending (absteigend).
   */
  private final ArrayList<BacktrackingItem> itemList;

  /**
   * the constructor for this  class.
   *
   * @param bagItem        the item of the composite
   * @param oldWeight      the weight before the new one gets calculated
   * @param oldValue       the value before the new one
   * @param bagCapacity    the maximum capacity of the bag
   * @param sortedItemList list of the items of the level
   * @param myParent       parent of this node
   */
  public BacktrackingNode(final BacktrackingItem bagItem, final int oldWeight,
                          final int oldValue, final int bagCapacity,
                          final ArrayList<BacktrackingItem> sortedItemList,
                          final BacktrackingNode myParent) {
    item = bagItem;
    if (item.getState() == BacktrackingItem.StateBacktracking.TRASH) {
      currentValue = oldValue;
      currentWeight = oldWeight;
    } else {
      currentValue = oldValue + bagItem.getValue();
      currentWeight = oldWeight + bagItem.getWeight();
    }
    capacity = bagCapacity;
    parent = myParent;

    itemList = sortedItemList;
  }

  /**
   * adds an item to the trash bin from the rucksack or the available selection.
   *
   * @param childItem the item in the new node
   * @return true if item got added successfully
   */
  public boolean addToTrash(final BacktrackingItem childItem) {
    if (!isNextHeavyItem(childItem)) {
      return false;
    } else if (childItem.getState()
            == BacktrackingItem.StateBacktracking.TRASH) {
      return false;
    }
    childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
    leftChild = new BacktrackingNode(childItem, currentWeight,
            currentValue, capacity, itemList, this);
    return true;
  }

  /**
   * method adds a child to the rucksack.
   *
   * @param childItem is added as next to this instance
   * @return true if child got added successfully
   */

  public boolean addToRucksack(final BacktrackingItem childItem) {
    if (itemFitsInRucksack(childItem)) {
      childItem.setState(BacktrackingItem.StateBacktracking.RUCKSACK);
      rightChild = new BacktrackingNode(childItem, currentWeight,
              currentValue, capacity, itemList, this);
      System.out.println(childItem.getName() + " wurde hinzugefügt");
      return true;

    } else {
      System.out.println("Item "
              + childItem.getName() + "kann nicht hinzugefügt werden.");
      return false;
    }
  }

  private boolean itemFitsInRucksack(final BacktrackingItem bagItem) {
    if (!isNextHeavyItem(bagItem)) {
      return false;
    }
    // item can only be added if it is available
    if (bagItem.getState() != BacktrackingItem.StateBacktracking.AVAILABLE) {
      return false;
    }

    // query in case that the item is not empty and is the next
    // more lightweight item after the item this instance
    if (capacity >= currentWeight + bagItem.getWeight()) {
      return true;
    } else {
      System.out.println("Item "
              + bagItem.getName() + " macht Rucksack zu schwer");
      return false;
    }
  }

  private boolean isNextHeavyItem(final BacktrackingItem bagItem) {
    int itemIndex = itemList.indexOf(bagItem);
    if (itemIndex != itemList.indexOf(this.item) + 1) {
      System.out.println("Item " + bagItem.getName()
              + " ist nicht das Nächstschwerere.");
      return false;
    }
    return true;
  }

  private boolean isEmptyItem(final Item bagItem) {
    return bagItem.getValue() == 0 && bagItem.getWeight() == 0;
  }

  /**
   * gets the current weight of this node.
   *
   * @return said weight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * gets the current value of this node.
   *
   * @return said value
   */
  public int getCurrentValue() {
    return currentValue;
  }

  /**
   * gets the item of the node.
   *
   * @return said item
   */
  public BacktrackingItem getItem() {
    return item;
  }

  /**
   * gets the left child of this node.
   *
   * @return left child
   */
  public BacktrackingNode getLeftChild() {
    return leftChild;
  }

  /**
   * gets the right child of this node.
   *
   * @return right child
   */
  public BacktrackingNode getRightChild() {
    return rightChild;
  }
}
