package jtree;

import backtrackingtree.BacktrackingNode;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import rucksack.Level;
import solving.AppData;

/**
 * Class for the item selection tree given as a visual aid
 * in a backtracking level. It is a JTree.
 * The tree is called backtracking tree.
 */
public class BacktrackingJtree {
  /**
   * The new frame for the tree.
   */
  private final JFrame treeFrame;
  /**
   * The tree's root node.
   */
  private final DefaultMutableTreeNode root
      = new DefaultMutableTreeNode("Root");
  /**
   * The new tree, starting with the root node.
   */
  private final JTree tree = new JTree(root);
  /**
   * The current node of the tree.
   */
  private DefaultMutableTreeNode currentNode;
  /**
   * The selected node of the tree.
   */
  private DefaultMutableTreeNode selectedNode = null;
  /**
   * The number of items that are part of the tree.
   */
  private final int itemAmount;

  /**
   * The constructor for the tree of a backtracking level.
   *
   * @param level the backtracking level whose tree shall be drawn
   */
  public BacktrackingJtree(final Level level) {
    itemAmount = level.getBacktrackingItemList().size();
    tree.addTreeSelectionListener(treeSelectionEvent
        -> selectedNode = (DefaultMutableTreeNode)
        tree.getLastSelectedPathComponent());
    currentNode = root;
    treeFrame = new JFrame();
    treeFrame.setSize(AppData.MINIMUM_WIDTH, AppData.MINIMUM_HEIGHT);
    JScrollPane treeView = new JScrollPane(tree);
    treeFrame.add(treeView);
    treeFrame.setVisible(false);
  }

  /**
   * Method for creating the node when an item gets put into the rucksack.
   *
   * @param currentBacktrackingNode the current node of the tree
   */
  public void putInBag(final BacktrackingNode currentBacktrackingNode) {
    addNode(currentBacktrackingNode, "      ");
  }

  private void update() {
    tree.updateUI();
    for (int i = 0; i < tree.getRowCount(); i++) {
      tree.expandRow(i);
    }
    treeFrame.revalidate();
    treeFrame.repaint();
  }

  /**
   * Method that shows the tree.
   */
  public void show() {
    treeFrame.setVisible(true);
  }

  /**
   * Method for closing the backtracking tree.
   */
  public void close() {
    treeFrame.dispose();
  }

  /**
   * Method for creating the node when an item gets put into the trash can.
   *
   * @param currentBacktrackingNode the current node of the tree
   */
  public void putInTrash(final BacktrackingNode currentBacktrackingNode) {
    int depth = currentNode.getLevel() - currentBacktrackingNode.getLevel();
    for (int i = 0; i <= depth; i++) {
      currentNode = (DefaultMutableTreeNode) currentNode.getParent();
    }
    addNode(currentBacktrackingNode, "");
  }

  private void addNode(final BacktrackingNode currentBacktrackingNode,
                       final String suffix) {
    StringBuilder res = new StringBuilder(currentBacktrackingNode.getName()
        + suffix);
    final int maxSpacingDepth = 10;
    res.append("    ".repeat(Math.max(0, maxSpacingDepth
        - currentNode.getLevel())));
    final int maxNameLength = 20;
    res.append(" ".repeat(maxNameLength
        - currentBacktrackingNode.getName().length()));
    res.append(currentBacktrackingNode.getCurrentWeight());
    res.append(" / ").append(currentBacktrackingNode.getCurrentValue());
    DefaultMutableTreeNode child = new DefaultMutableTreeNode(res);
    currentNode.add(child);
    currentNode = (DefaultMutableTreeNode) currentNode.getLastChild();
    update();
  }

  /**
   * Method for creating the String of the solution.
   *
   * @return the String of the solution
   */
  public String getSolution() {
    if (selectedNode == null || selectedNode.getLevel() != itemAmount) {
      return null;
    }
    StringBuilder res = new StringBuilder();
    while (selectedNode.getParent() != null) {
      if (selectedNode.getUserObject().toString().startsWith("not ")) {
        res.append("1");
      } else {
        res.append("0");
      }
      selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
    }
    res.reverse();
    return res.toString();
  }
}
