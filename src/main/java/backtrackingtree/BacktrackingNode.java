package backtrackingtree;

import java.util.ArrayList;
import java.util.Objects;
import rucksack.BacktrackingItem;


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
   * represents the name of the node, e.g.
   * crown is selected --> "crown"
   * crown was thrown into trash --> "not crown"
   */
  private final String name;

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
   * @param isInBag        true if item is in the bag, false if
   *                      it's in the trash
   */
  public BacktrackingNode(final BacktrackingItem bagItem, final int oldWeight,
                          final int oldValue, final int bagCapacity,
                          final ArrayList<BacktrackingItem> sortedItemList,
                          final BacktrackingNode myParent,
                          final boolean isInBag) {
    item = bagItem;
    if (item.getState() == BacktrackingItem.StateBacktracking.TRASH) {
      currentValue = oldValue;
      currentWeight = oldWeight;
    } else {
      currentValue = oldValue + bagItem.getValue();
      currentWeight = oldWeight + bagItem.getWeight();
    }

    if (isInBag) {
      name = bagItem.getName();
    } else {
      name = "not " + bagItem.getName();
    }

    capacity = bagCapacity;
    parent = myParent;

    itemList = sortedItemList;
  }

  /**
   * adds an item to the trash bin from the rucksack or the available selection.
   *
   * @param childItem the item in the new node (trash)
   * @return the new current node of the tree
   */
  public BacktrackingNode addToTrash(final BacktrackingItem childItem) {
    BacktrackingItem.StateBacktracking childState = childItem.getState();
    //adds depending on if an item is in available, rucksack or trash
    if (childState == BacktrackingItem.StateBacktracking.TRASH) {
      System.out.println(childItem.getName() + " ist schon im Müll.");
      return null;

    } else if (childState == BacktrackingItem.StateBacktracking.RUCKSACK) {

      //getting up the tree, so we can set the new left child correctly
      BacktrackingNode currentParent = parent;
      if (this.getItem().getName().equals(childItem.getName())) {
        currentParent = this;
      } else {
        if (this.getItem().getState() != BacktrackingItem.StateBacktracking.TRASH) {
          System.out.println(this.getItem().getName() + " muss zuerst in den Müll bewegt werden.");
          return null;
        }
        while (!Objects.equals(currentParent.getItem().getName(),
                childItem.getName())) {
          if (currentParent.getName().equals("root")) {
            break;
          } else if (currentParent.getItem().getState() != BacktrackingItem.StateBacktracking.TRASH) {
            System.out.println(currentParent.getItem().getName() + " muss zuerst in den Müll bewegt werden.");
            return null;
          }
          currentParent = currentParent.getParent();
        }
      }
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);

      currentParent = currentParent.getParent();
      int newCurrentWeight = currentParent.getCurrentWeight();
      int newCurrentValue = currentParent.getCurrentValue();
      currentParent.setLeftChild(new BacktrackingNode(childItem,
          newCurrentWeight, newCurrentValue, capacity, itemList,
          currentParent, false));
      moveItemsIntoAvailable(currentParent.rightChild);
      return currentParent.getLeftChild();

    } else if (childState == BacktrackingItem.StateBacktracking.AVAILABLE) {
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      leftChild = new BacktrackingNode(childItem, currentWeight,
              currentValue, capacity, itemList, this, false);
      return leftChild;
    }
    System.out.println(childItem.getName()
        + " konnte nicht in den Müll geworfen werden.");
    return null;
  }

  /**
   * puts all items which weights the same or less into available.
   * But already established parents do not get set to the state TRASH.
   *
   * @param gotIntoTrashNode which is thrown from rucksack into trash.
   */
  private void moveItemsIntoAvailable(final BacktrackingNode gotIntoTrashNode) {
    BacktrackingNode currentNode = gotIntoTrashNode;
    int weight = currentNode.item.getWeight();

    ArrayList<BacktrackingItem> parentItems = new ArrayList<>();
    currentNode = currentNode.parent;
    while (!Objects.equals(currentNode.getName(), "root")) {
      parentItems.add(currentNode.item);
      currentNode = currentNode.parent;
    }

    StringBuilder sb = new StringBuilder();
    for (BacktrackingItem items : parentItems) {
      sb.append(items.getName()).append(", ");
    }
    System.out.println("Liste der Items mit gleichem Gewicht"
        + ", dessen Status nicht verändert werden darf: " + sb);

    /*ArrayList<BacktrackingItem> sameWeightParentList = new ArrayList<>();
    BacktrackingNode sameWeightParentNode = gotIntoTrashNode.getParent();
    while (sameWeightParentNode.getItem().getWeight() == gotIntoTrashNode.getItem().getWeight()) {
      sameWeightParentList.add(sameWeightParentNode.getItem());
      sameWeightParentNode = sameWeightParentNode.getParent();
    }*/

    for (BacktrackingItem currentItem : itemList) {
      //lower weight gets into available
      if (currentItem.getWeight() <= weight && !currentItem
          .getName().equals(gotIntoTrashNode.item.getName())) {
        //items only get set to available if item is not a parent
        if (!parentItems.contains(currentItem)) {
          currentItem.setState(BacktrackingItem.StateBacktracking.AVAILABLE);
        }
      }
      /*//
      if (currentItem.getWeight() == weight && !currentItem
              .getName().equals(gotIntoTrashNode.item.getName())) {
        //items only get set to available if item is not a parent
        if (!sameWeightParentList.contains(currentItem)) {
          currentItem.setState(BacktrackingItem.StateBacktracking.AVAILABLE);
        }
      }*/


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
      rightChild = new BacktrackingNode(childItem, currentWeight,
              currentValue, capacity, itemList, this, true);
      System.out.println(childItem.getName() + " wurde hinzugefügt");
      childItem.setState(BacktrackingItem.StateBacktracking.RUCKSACK);
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
                      + " ist nicht das nächstverfügbare,"
                      + " weil es noch verfügbare "
                      + "schwerere Items gibt. Dieses ist zB "
                      + itemList.get(i).getName());
              return false;
            }
          } else {
            break;
          }
        }
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
      }
    }

    //case when we want to add a lighter item.
    //index of this node's item is not the first one
    // or the last one of the itemList
    //if (indexThis != 0 && indexThis != itemList.size() - 1) {
    // thisItem is at a middle position in the list.
    //case if left and right does not have the same weight as this
    //if (itemList.get(indexThis - 1).getWeight() != weightThis
    //        && itemList.get(indexThis + 1).getWeight() != weightThis) {

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
    //return true;
    //  }
    // }
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

  /**
   * returns the name of this node.
   *
   * @return said name
   */
  public String getName() {
    return name;
  }

  /**
   * sets the left child of this node.
   *
   * @param myLeftChild saif child which is going to be set
   */
  public void setLeftChild(final BacktrackingNode myLeftChild) {
    this.leftChild = myLeftChild;
  }

  /**
   * return the current value.
   *
   * @return said value
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * returns the current weight.
   *
   * @return said weight
   */
  public int getCurrentValue() {
    return currentValue;
  }

  /**
   * returns the parent of this class.
   *
   * @return the parent of this instance
   */
  public BacktrackingNode getParent() {
    return parent;
  }
}
