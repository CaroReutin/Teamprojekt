package jtree;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import rucksack.Item;
import rucksack.Level;

/**
 * .
 */
public class BacktrackingTree {
  /**
   * the tree.
   */
  private final JTree tree;
  /**
   * the frame that shows the tree.
   */
  private final JFrame frame = new JFrame();
  /**
   * all possible (and impossible) nodes.
   */
  private final ArrayList<ArrayList<DefaultMutableTreeNode>>
      nodes = new ArrayList<>();
  /**
   * the indexes of explored paths matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<Integer>> exploredPaths = new ArrayList<>();
  /**
   * the current node.
   */
  private DefaultMutableTreeNode currentNode;
  /**
   * list of items.
   */
  private final ArrayList<Item> itemList = new ArrayList<>();
  /**
   * what depth the currentNode is located at.
   */
  private int currentDepth = 0;
  /**
   * current path represented by 0 for not in bag and 1 for in bag
   * if new items are always appended at the end then the binary sequence
   * converted to decimal is equal to the index of the row of the item
   * at the end of that path.
   */
  private String currentPath = "";

  /**
   * .
   *
   * @param level the level
   */
  public BacktrackingTree(final Level level) {
    for (int i = 0; i < level.getItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        itemList.add(level.getItemList().get(i));
      }
    }
    generateNodes();
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
    currentNode = root;
    tree = new JTree(root);
    tree.setRootVisible(true);
    tree.setVisible(true);
    frame.setSize(500, 500);
    frame.add(tree);
    frame.setVisible(false);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  private void generateNodes() {
    for (int i = 0; i < itemList.size(); i++) {
      nodes.add(new ArrayList<>());
      exploredPaths.add(new ArrayList<>());
    }
    addNodes(0, 0, 0);
  }

  private void addNodes(final int depth, final int value, final int weight) {
    if (depth >= itemList.size()) {
      return;
    }
    String leftRes = "not ";
    String rightRes = itemList.get(depth).getBacktrackingName(
        itemList.size() - depth) + "  ";
    leftRes += rightRes;
    rightRes += "    ";
    leftRes += "Weight: " + weight
        + " ".repeat(Math.max(0, 5 - String
        .valueOf(weight).length())) + " | " + "Value: " + value;
    int newWeight = weight + itemList.get(depth).getWeight();
    int newValue = value + itemList.get(depth).getValue();
    rightRes += "Weight: " + newWeight
        + " ".repeat(Math.max(0, 5 - String
        .valueOf(newWeight).length())) + " | " + "Value: " + newValue;
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(leftRes);
    nodes.get(depth).add(node);
    nodes.get(depth).add(new DefaultMutableTreeNode(rightRes));
    addNodes(depth + 1, value, weight);
    addNodes(depth + 1, newValue, newWeight);
  }

  /**
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  public void back() {
    if (currentNode.getParent() != null) {
      currentNode = (DefaultMutableTreeNode) currentNode.getParent();
      currentDepth--;
      currentPath = currentPath.substring(0, currentPath.length() - 1);
    }
  }

  /**
   * adds the path where the next heaviest item is added to the bag.
   */
  public void putInBag() {
    if (itemList.size() - currentDepth <= 0) {
      return;
    }
    currentPath += "1";
    addNode();
  }

  private void addNode() {
    int nextIndex = Integer.parseInt(currentPath, 2);
    boolean update = false;
    if (!exploredPaths.get(currentDepth).contains(nextIndex)) {
      update = true;
      currentNode.add(nodes.get(currentDepth).get(nextIndex));
      exploredPaths.get(currentDepth).add(nextIndex);
    }
    currentNode = nodes.get(currentDepth).get(nextIndex);
    currentDepth++;
    if (update) {
      DefaultMutableTreeNode pastNode =
          (DefaultMutableTreeNode) currentNode.getParent();
      tree.updateUI();
      tree.expandPath(new TreePath(pastNode.getPath()));
      frame.getContentPane().revalidate();
      frame.getContentPane().repaint();
      frame.revalidate();
      frame.repaint();
    }
  }

  /**
   * adds the path where the next heaviest item is added to the trash.
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  public void putInTrash() {
    if (itemList.size() - currentDepth <= 0) {
      return;
    }
    currentPath += "0";
    addNode();
  }

  /**
   * show the tree.
   */
  public void show() {
    frame.setVisible(true);
  }
}
