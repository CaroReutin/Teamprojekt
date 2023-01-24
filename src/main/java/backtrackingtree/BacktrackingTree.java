package backtrackingtree;

import java.io.PrintStream;
import java.util.ArrayList;
import rucksack.BacktrackingItem;


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
   * constructor for backtracking tree.
   *
   * @param bagCapacity   maximum capacity of the rucksack.
   * @param itemArrayList sorted item list in descending order by weight.
   */
  public BacktrackingTree(final int bagCapacity,
                          final ArrayList<BacktrackingItem> itemArrayList) {
    root = new BacktrackingNode(new BacktrackingItem(0, 0, "root"),
        0, 0, bagCapacity, itemArrayList, null, true);
    currentNode = root;
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
          .append(", akt. Wert: ").append(node.getCurrentValue()).append("]");

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
   */
  public void addToRucksack(final BacktrackingItem item) {
    System.out.println(item.getName()
        + " soll dem Rucksack hinzugefügt werden.");
    boolean addedSuccessfully = currentNode.addToRucksack(item);

    if (addedSuccessfully) {
      currentNode = currentNode.getRightChild();
      System.out.println("Der current Node ist nun " + currentNode.getName());
    }

    System.out.println("-----------------");
  }

  /**
   * adds an item to the trash bin (left child).
   *
   * @param item said item
   */
  public void addToTrash(final BacktrackingItem item) {
    System.out.println(item.getName() + " soll in den Müll geworfen werden.");
    BacktrackingNode newCurrent = currentNode.addToTrash(item);
    if (newCurrent != null) {
      currentNode = newCurrent;
      System.out.println("Item " + item.getName()
          + " wurde in den Müll gelegt.");
      System.out.println("Der current Node ist nun "
          + currentNode.getName());
    }
    System.out.println("-----------------");
  }
}
