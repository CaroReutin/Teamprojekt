package backtrackingtree;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.ImageIcon;
import rucksack.BacktrackingItem;
import rucksack.Item;

/**
 * this class represents the backtracking tree.
 */
public class BacktrackingTree {
  /**
   * root of the backtracking tree.
   */
  private final BacktrackingNode root;

  /**
   * current node where we can add children to.
   */
  private BacktrackingNode currentNode;

  /**
   * Integer number of the current rucksack's capacity.
   */
  private final int bagCapacity;

  /**
   * Array list of all the items available in the level.
   */
  private final ArrayList<BacktrackingItem> itemArrayList;

  /**
   * Boolean value whether the subtree is complete (full) or not.
   */
  private boolean isSubtreeFull = true;


  /**
   * constructor for backtracking tree.
   *
   * @param myBagCapacity   maximum capacity of the rucksack.
   * @param myItemArrayList sorted item list in descending order by weight.
   */
  public BacktrackingTree(final int myBagCapacity,
                          final ArrayList<BacktrackingItem> myItemArrayList) {
    myItemArrayList.sort(
            Comparator.comparingInt(Item::getWeight)
                    .thenComparingInt(Item::getValue).reversed());
    this.itemArrayList = myItemArrayList;
    root = new BacktrackingNode(new BacktrackingItem(
            0,
        0, "root", new ImageIcon()), 0, 0,
            myBagCapacity, myItemArrayList,
        null, true, 0);
    currentNode = root;
    this.bagCapacity = myBagCapacity;
  }


  /**
   * prints the tree.
   *
   * @param os the print stream
   */
  public void print(final PrintStream os) {
    os.print(traversePreOrder(root));
    System.out.println(" ");
    System.out.println("------------------");
  }


  private String traversePreOrder(final BacktrackingNode myRoot) {

    if (myRoot == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(myRoot.getName());

    String pointerRight = "└──";
    String pointerLeft = (myRoot.getRightChild() != null) ? "├──" : "└──";

    traverseNodes(sb, "", pointerLeft, myRoot.getLeftChild(),
            myRoot.getRightChild() != null);
    traverseNodes(sb, "", pointerRight, myRoot.getRightChild(), false);

    return sb.toString();
  }

  private void traverseNodes(final StringBuilder sb, final String padding,
                             final String pointer,
                             final BacktrackingNode node,
                             final boolean hasRightSibling) {
    if (node != null) {
      sb.append("\n");
      sb.append(padding);
      sb.append(pointer);
      sb.append(node.getName()).append(" [akt. Gewicht:")
              .append(node.getCurrentWeight())
              .append(", akt. Wert: ").append(node.getCurrentValue())
              .append("]");

      StringBuilder paddingBuilder = new StringBuilder(padding);
      if (hasRightSibling) {
        paddingBuilder.append("│  ");
      } else {
        paddingBuilder.append("   ");
      }

      String paddingForBoth = paddingBuilder.toString();
      String pointerRight = "└──";
      String pointerLeft = (node.getRightChild() != null) ? "├──" : "└──";

      traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeftChild(),
              node.getRightChild() != null);
      traverseNodes(sb, paddingForBoth, pointerRight,
              node.getRightChild(), false);
    }
  }

  /**
   * adds an item to the rucksack (right child).
   *
   * @param item said item
   * @return boolean value whether adding the item was successful or not.
   */
  public boolean addToRucksack(final BacktrackingItem item) {
    System.out.println(item.getName()
            + " soll dem Rucksack hinzugefügt werden.");
    boolean addedSuccessfully = currentNode.addToRucksack(item);

    if (addedSuccessfully) {
      currentNode = currentNode.getRightChild();
      System.out.println("Der current Node ist nun " + currentNode.getName());
      System.out.println("-----------------");
      return true;
    }

    System.out.println("-----------------");
    return false;
  }

  /**
   * adds an item to the trash bin (left child).
   *
   * @param item said item
   * @return boolean value whether adding the item was successful or not.
   */
  public boolean addToTrash(final BacktrackingItem item) {
    System.out.println(item.getName() + " soll in den Müll geworfen werden.");
    BacktrackingNode newCurrent = helpAddToTrash(item);
    if (newCurrent != null) {
      currentNode = newCurrent;
      System.out.println("Item " + item.getName()
              + " wurde in den Müll gelegt.");
      System.out.println("Der current Node ist nun "
              + currentNode.getName());
      System.out.println("-----------------");
      return true;
    }
    System.out.println("-----------------");
    return false;
  }

  /**
   * adds an item to the trash bin from the rucksack or the available selection.
   *
   * @param childItem the item in the new node (trash)
   * @return the new current node of the tree
   */
  private BacktrackingNode helpAddToTrash(final BacktrackingItem childItem) {
    BacktrackingItem.StateBacktracking childState = childItem.getState();
    //adds depending on if an item is in available, rucksack or trash
    if (childState == BacktrackingItem.StateBacktracking.TRASH) {
      System.out.println(childItem.getName() + " ist schon im Müll.");
      return null;

    } else if (childState == BacktrackingItem.StateBacktracking.RUCKSACK) {

      //getting up the tree, so we can set the new left child correctly
      BacktrackingNode currentParent = currentNode.getParent();
      if (currentNode.getItem().getName().equals(childItem.getName())) {
        currentParent = currentNode;
      } else {
        while (!Objects.equals(currentParent.getItem().getName(),
                childItem.getName())) {
          if (currentParent.getName().equals("root")) {
            break;
          } else if (currentParent.getItem().getState()
                  != BacktrackingItem.StateBacktracking.TRASH) {
            System.out.println(
                    currentParent.getItem().getName()
                            + " muss zuerst in den Müll bewegt werden.");
            return null;
          }
          currentParent = currentParent.getParent();
        }
      }
      //childItem.setState(BacktrackingItem.StateBacktracking.TRASH);

      // is subtree full?
      if (!isSubtreeFull(currentParent)) {
        System.out.println(
                childItem.getName() + " kann noch nicht in"
                        + " den Müll geworfen werden, weil der "
                        + "Subtree noch nicht voll ist.");
        return null;
      }
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      currentParent = currentParent.getParent();


      int newCurrentWeight = currentParent.getCurrentWeight();
      int newCurrentValue = currentParent.getCurrentValue();
      currentParent.setLeftChild(new BacktrackingNode(childItem,
              newCurrentWeight, newCurrentValue, bagCapacity, itemArrayList,
              currentParent, false, currentParent.getLevel() + 1));
      currentNode.moveItemsIntoAvailable(currentParent.getRightChild());
      return currentParent.getLeftChild();

    } else if (childState == BacktrackingItem.StateBacktracking.AVAILABLE) {
      //see if another item should be taken first
      if (itemArrayList.indexOf(currentNode.getItem())
              + 1 != itemArrayList.indexOf(childItem)) {
        System.out.println("Vorher solltest du ein"
                + " schwereres oder wertvolleres Item betrachten");
        return null;
      }
      //see if item should be put to rucksack first
      if (currentNode.getCurrentWeight()
              + childItem.getWeight() <= bagCapacity) {
        System.out.println(childItem.getName()
                + " sollte zuerst in den Rucksack gelegt werden");
        return null;
      }
      childItem.setState(BacktrackingItem.StateBacktracking.TRASH);
      currentNode.setLeftChild(new BacktrackingNode(
              childItem, currentNode.getCurrentWeight(),
              currentNode.getCurrentValue(), bagCapacity,
              itemArrayList, currentNode, false, currentNode.getLevel() + 1));
      return currentNode.getLeftChild();
    }
    System.out.println(childItem.getName()
            + " konnte nicht in den Müll geworfen werden.");
    return null;
  }

  /**
   * checks if the subtree is full.
   * root of the subtree is the Node of the Item
   * (state: Rucksack) witch should be added to trash
   *
   * @param nodeItemToTrash the Node of the Item witch should be to add to trash
   * @return boolean value whether the subtree is full or not.
   */
  public boolean isSubtreeFull(final BacktrackingNode nodeItemToTrash) {
    this.moveDownTheSubtree(nodeItemToTrash);
    if (isSubtreeFull) {
      return true;
    } else {
      //reset isSubtreeFull
      isSubtreeFull = true;
      return false;
    }
  }

  private void moveDownTheSubtree(final BacktrackingNode nodeItemToTrash) {
    if (currentNode.getCurrentWeight() >= bagCapacity) {
      isSubtreeFull = allLeftChildrenAreThere();
      return;
    }
    // be on leaves
    if (currentNode.getRightChild() == null
            && currentNode.getLeftChild() == null) {
      // last item which can be chosen is used
      // (in other words the highest high of the tree is reached)
      if (itemArrayList.indexOf(currentNode.getItem()) != itemArrayList.size()
              - 1 && currentNode.getCurrentWeight()
              + itemArrayList.get(itemArrayList.size() - 1).getWeight()
              <= bagCapacity) {
        isSubtreeFull = false;
      }
    } else if (currentNode.getRightChild() == null) {
      if (currentNode.getCurrentWeight()
              + itemArrayList.get(itemArrayList.size() - 1)
              .getWeight() <= bagCapacity) {
        isSubtreeFull = false;
      } else {
        this.moveDownTheSubtree(nodeItemToTrash.getLeftChild());
      }
    } else if (currentNode.getLeftChild() == null) {
      if (currentNode.getCurrentWeight()
              + itemArrayList.get(itemArrayList.size() - 1)
              .getWeight() <= bagCapacity) {
        isSubtreeFull = false;
      } else {
        this.moveDownTheSubtree(nodeItemToTrash.getRightChild());
      }
    } else {
      this.moveDownTheSubtree(nodeItemToTrash.getLeftChild());
      this.moveDownTheSubtree(nodeItemToTrash.getRightChild());
    }
  }

  private boolean allLeftChildrenAreThere() {
    boolean res = true;
    String currentNodeName = currentNode.getName();
    while (currentNode.getLevel() < itemArrayList.size()) {
      if (currentNode.getLeftChild() == null) {
        res = false;
        break;
      }
      currentNode = currentNode.getLeftChild();
    }
    while (!Objects.equals(currentNode.getName(), currentNodeName)) {
      currentNode = currentNode.getParent();
    }
    return res;
  }

  /**
   * returns the Node, where we actually are in the tree.
   *
   * @return Node, where we actually are in the tree
   */
  public BacktrackingNode getCurrentNode() {
    return currentNode;
  }
}
