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
    if (item == null) {
      currentValue = 0;
      currentWeight = 0;
    } else {
      currentValue = oldValue + bagItem.getValue();
      currentWeight = oldWeight + bagItem.getWeight();
    }
    capacity = bagCapacity;
    parent = myParent;

    itemList = sortedItemList;
  }

  /**
   * method adds a child.
   * the index 0 is chosen if an item is not chosen to be in the bag (trash).
   * the index 1 is chosen if an item is chosen to be in the bag (rucksack).
   *
   * @param childItem is added as next to this instance
   * @return true if child got added successfully
   */

  public boolean add(final BacktrackingItem childItem) {
    if (isItemAllowed(childItem)) {
      if (isEmptyItem(childItem)) {
        //item is throws in the trash
        leftChild = new BacktrackingNode(childItem, currentWeight,
                currentValue, capacity, itemList, parent);
        childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      } else {
        //item gets added to the rucksack
        rightChild = new BacktrackingNode(childItem, currentWeight,
                currentValue, capacity, itemList, parent);
        childItem.setState(BacktrackingItem.StateBacktracking.RUCKSACK);
      }
      System.out.println(childItem.getName() + " wurde hinzugefügt");
      return true;
    } else {
      System.out.println("Item darf nicht hinzugefügt werden");
      return false;
    }

  }

  private boolean isItemAllowed(final BacktrackingItem bagItem) {
    // if you want to put an item from the rucksack to the bin
    if (isEmptyItem(bagItem)) {
      return true;
    }

    // item can only be added if it is available
    if (bagItem.getState() != BacktrackingItem.StateBacktracking.AVAILABLE) {
      return false;
    }

    // query in case that the item is not empty and is the next
    // more lightweight item after the item this instance
    int itemIndex = itemList.indexOf(bagItem);
    if (itemIndex == itemList.indexOf(this.item) + 1
            && capacity >= currentWeight + bagItem.getWeight()) {
      return true;
    } else {
      System.out.println("Item macht Rucksack zu schwer oder"
              + "Item ist das nicht das schwerste Verfügbare");
      return false;
    }

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
