package backtrackingtree;

import java.util.ArrayList;
import java.util.Objects;
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
    BacktrackingItem.StateBacktracking childState = childItem.getState();

    //adds depending on if an item is in available, rucksack or trash
    if (childState == BacktrackingItem.StateBacktracking.TRASH) {
      return false;
    } else if (childState == BacktrackingItem.StateBacktracking.RUCKSACK) {
      moveItemsIntoAvailable(childItem);
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      BacktrackingNode currentParent = parent;
      //getting up the tree so we can set the new left child correctly
      while (!Objects.equals(currentParent.getItem().getName(),
              childItem.getName())) {
        currentParent = currentParent.parent;
      }
      currentParent = currentParent.parent;
      currentParent.leftChild = new BacktrackingNode(childItem, currentWeight,
              currentValue, capacity, itemList, currentParent);
      return true;

    } else if (childState == BacktrackingItem.StateBacktracking.AVAILABLE) {
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      leftChild = new BacktrackingNode(childItem, currentWeight,
              currentValue, capacity, itemList, this);
      return true;
    }

    childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
    leftChild = new BacktrackingNode(childItem, currentWeight,
            currentValue, capacity, itemList, this);
    return true;
  }

  /**
   * puts all items which weights the same or less into available.
   *
   * @param trashItem which is thrown from rucksack into trash.
   */
  private void moveItemsIntoAvailable(final BacktrackingItem trashItem) {
    int weight = trashItem.getWeight();

    for (BacktrackingItem currentItem : itemList) {
      if (currentItem.getWeight() <= weight) {
        currentItem.setState(BacktrackingItem.StateBacktracking.AVAILABLE);
      }
    }
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
              + childItem.getName() + " kann nicht hinzugefügt werden.");
      return false;
    }
  }

  private boolean itemFitsInRucksack(final BacktrackingItem bagItem) {
    // query if item can be selected or not
    if (!isNextSelectableItemForBag(bagItem)) {
      System.out.println(bagItem.getName() + " ist nicht selectable.");
      return false;
    }
    // item can only be added if it is available
    if (bagItem.getState() != BacktrackingItem.StateBacktracking.AVAILABLE) {
      System.out.println(bagItem.getName() + " ist nicht verfügbar.");
      return false;
    }

    // query in case that the item is not empty and is the next
    // more lightweight item after the item this instance
    if (capacity >= currentWeight + bagItem.getWeight()) {
      return true;
    } else {
      System.out.println("Item "
              + bagItem.getName() + " macht Rucksack zu schwer "
              + "und kann deswegen nicht hinzugefügt werden.");
      return false;
    }
  }

  private boolean isNextSelectableItemForBag(final BacktrackingItem
                                                     newBagItem) {
    final int indexNewBagItem = itemList.indexOf(newBagItem);
    final int weightNewBagItem = newBagItem.getWeight();
    final int indexThis = itemList.indexOf(this.getItem());
    final int weightThis = this.item.getWeight();

    //if item is in available
    if (newBagItem.getState() != BacktrackingItem.StateBacktracking.AVAILABLE) {
      System.out.println(newBagItem.getName() + " ist nicht available");
      return false;
    }

    //first item to add to the tree
    if (itemList.get(0).getWeight() == weightNewBagItem) {
      return true;
    }

    // if new item has the same weight as this node
    if (weightNewBagItem == weightThis) {
      return true;
    }

    //case if left item has the same weight as the thisItem
    if (indexThis != 0) {
      if (itemList.get(indexThis - 1).getWeight() == weightThis) {
        for (int i = indexThis - 1; i >= 0; i--) {
          if (itemList.get(i).getWeight() == weightThis) {
            if (itemList.get(i).getState()
                    == BacktrackingItem.StateBacktracking.AVAILABLE) {
              System.out.println("Item " + newBagItem.getName()
                      + " ist nicht das nächstverfügbare.");
              return false;
            }
          } else {
            break;
          }
        }
        return true;
      }
    }

    //case if right item has the same weight as thisItem
    if (indexThis != itemList.size() - 1) {
      if (itemList.get(indexThis + 1).getWeight() == weightThis) {
        for (int i = indexThis + 1; i <= itemList.size() - 1; i++) {
          if (itemList.get(i).getWeight() == weightThis) {
            if (itemList.get(i).getState()
                    == BacktrackingItem.StateBacktracking.AVAILABLE) {
              System.out.println("Item " + newBagItem.getName()
                      + " ist nicht verfügbar.");
              return false;
            }
          } else {
            break;
          }
        }
        return true;
      }
    }

    //case when we want to add a lighter item.
    // item is at a middle position in the list.
    if (indexThis != 0 && indexThis != itemList.size() - 1) {
      //case if left and right does not have the same weight as this
      if (itemList.get(indexThis - 1).getWeight() != weightThis
              && itemList.get(indexNewBagItem + 1).getWeight() != weightThis) {

        int indexMostRightItem = indexThis;
        for (int i = indexThis; i < itemList.size() - 1; i++) {
          if (itemList.get(i).getWeight() == weightThis) {
            indexMostRightItem = i;
          } else {
            break;
          }
        }
        if (itemList.get(indexMostRightItem + 1).getWeight()
                == weightNewBagItem) {
          return true;
        } else {
          System.out.println("Item " + newBagItem.getName()
                  + " ist nicht das Nächstleichtere!");
          return false;
        }
      }
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
