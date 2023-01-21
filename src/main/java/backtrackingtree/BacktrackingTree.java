package backtrackingtree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;
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
   * @param ps the print stream
   */
  public void print(final PrintStream ps) {
    StringBuilder sb = new StringBuilder();
    traversePreOrder(sb, "", "", root);
    ps.print(sb);
  }


  /**
   * traverses the tree.
   *
   * @param sb      the StringBuilder
   * @param padding the Padding
   * @param pointer the Pointer
   * @param node    the current node
   */
  public void traversePreOrder(final StringBuilder sb,
                               final String padding, final String pointer,
                               final BacktrackingNode node) {
    if (node != null) {
      sb.append(padding);
      sb.append(pointer);

      sb.append(node.getName()).append(" [cWeight:")
              .append(node.getCurrentWeight())
              .append(", cValue:")
              .append(node.getCurrentValue())
              .append("]");
      sb.append("\n");

      String paddingForBoth = padding + "│  ";
      String pointerForRight = "└──";
      String pointerForLeft = (node.getRightChild() != null) ? "├──" : "└──";

      traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeftChild());
      traversePreOrder(sb, paddingForBoth, pointerForRight,
              node.getRightChild());
    }
  }

  /**
   * adds an item to the rucksack (right child).
   *
   * @param item said item
   */
  public void addToRucksack(final BacktrackingItem item) {
    boolean addedSuccessfully = currentNode.addToRucksack(item);

    if (addedSuccessfully) {
      currentNode = currentNode.getRightChild();
      System.out.println("Der current Node ist nun " + currentNode.getName());
    }
  }

  /**
   * adds an item to the trash bin (left child).
   *
   * @param item said item
   */
  public void addToTrash(final BacktrackingItem item) {
    BacktrackingNode newCurrent = currentNode.addToTrash(item);
    if (newCurrent != null) {
      currentNode = newCurrent;
      System.out.println("Item " + item.getName()
              + " wurde in den Müll gelegt.");
      System.out.println("Der current Node ist nun "
              + currentNode.getName());
    }
  }
}
