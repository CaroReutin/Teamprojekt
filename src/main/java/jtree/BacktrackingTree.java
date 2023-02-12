package jtree;

import backtrackingtree.BacktrackingNode;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import rucksack.Item;
import rucksack.Level;

/**
 * Class of the item tree for a backtracking level.
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
   * the indexes of explored paths matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<Integer>> exploredPaths = new ArrayList<>();
  /**
   * The current node.
   */
  private DefaultMutableTreeNode currentNode;
  /**
   * The tree node that is selected as solution.
   */
  private BacktrackingNode solution = null;
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
   * Maximum amount of items in a backtracking level.
   */
  public static final int MAX_ITEM_AMOUNT = 5;
  /**
   * Size of the frame where the backtracking tree is shown.
   */
  public static final int FRAME_SIZE = 500;
  /**
   * The current backtracking level.
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
    tree.getSelectionModel().setSelectionMode(
            TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(final TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();

        if (node == null) {
          return;
        }
        if (node.getLevel() != level.getBacktrackingItemList().size()) {
          return;
        }

        Object nodeInfo = node.getPath();
        System.out.println(nodeInfo);
      }
    });
    tree.setRootVisible(true);
    tree.setVisible(true);
    frame.setSize(FRAME_SIZE, FRAME_SIZE);
    frame.add(tree);
    frame.setVisible(false);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  private void generateNodes() {
    for (int i = 0; i < itemList.size(); i++) {
      exploredPaths.add(new ArrayList<>());
    }
    //addNodes(0, 0, 0);
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
        + " ".repeat(Math.max(0, MAX_ITEM_AMOUNT
            - String.valueOf(weight).length())) + " | " + "Value: " + value;
    int newWeight = weight + itemList.get(depth).getWeight();
    int newValue = value + itemList.get(depth).getValue();
    rightRes += "Weight: " + newWeight
            + " ".repeat(Math.max(0, MAX_ITEM_AMOUNT
            - String.valueOf(newWeight).length()))
            + " | "
            + "Value: "
            + newValue;
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(leftRes);
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
   *
   * @param currentBacktrackingNode the current node of the backtracking tree
   */
  public void putInBag(final BacktrackingNode currentBacktrackingNode) {
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
      //currentNode.add(nodes.get(currentDepth).get(nextIndex));
      exploredPaths.get(currentDepth).add(nextIndex);
    }
    //currentNode = nodes.get(currentDepth).get(nextIndex);
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
   *
   * @param currentBacktrackingNode the current node of the backtracking tree
   */
  public void putInTrash(final BacktrackingNode currentBacktrackingNode) {
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

  /**
   * Method for closing the tree and disposing the frame.
   */
  public void close() {
    frame.dispose();
  }
}
