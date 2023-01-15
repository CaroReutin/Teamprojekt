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
   * constructor for backtracking tree.
   *
   * @param bagCapacity   maximum capacity of the rucksack.
   * @param itemArrayList sorted item list in descending order by weight.
   */
  public BacktrackingTree(final int bagCapacity,
                          final ArrayList<BacktrackingItem> itemArrayList) {
    root = new BacktrackingNode(new BacktrackingItem(0, 0, "root"),
            0, 0, bagCapacity, itemArrayList, null);
  }

  /**
   * prints the tree.
   *
   * @param ps the print stream
   */
  public void print(final PrintStream ps) {
    StringBuilder sb = new StringBuilder();
    traversePreOrder(sb, "", "", root);
    ps.print(sb.toString());
  }


  /**
   * traverses the tree.
   *
   * @param sb      the StringBuilder
   * @param padding the Padding
   * @param pointer the Pointer
   * @param node    the current node
   */
  public void traversePreOrder(final StringBuilder sb, final String padding, final String pointer,
                               final BacktrackingNode node) {
    if (node != null) {
      sb.append(padding);
      sb.append(pointer);
      sb.append(node.getItem().getName());
      sb.append("\n");

      StringBuilder paddingBuilder = new StringBuilder(padding);
      paddingBuilder.append("│  ");

      String paddingForBoth = paddingBuilder.toString();
      String pointerForRight = "└──";
      String pointerForLeft = (node.getRightChild() != null) ? "├──" : "└──";

      traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeftChild());
      traversePreOrder(sb, paddingForBoth, pointerForRight,
              node.getRightChild());
    }
  }

  /**
   * gets the root of this tree.
   *
   * @return said root
   */
  public BacktrackingNode getRoot() {
    return root;
  }
}
