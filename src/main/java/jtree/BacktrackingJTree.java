package jtree;

import backtrackingtree.BacktrackingNode;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import rucksack.Level;

public class BacktrackingJTree {
  private final JFrame treeframe;
  private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
  private JTree tree = new JTree(root);
  private DefaultMutableTreeNode currentNode;
  private DefaultMutableTreeNode selectedNode = null;
  private int ITEM_AMOUNT;

  public BacktrackingJTree(final Level level) {
    ITEM_AMOUNT = level.getBacktrackingItemList().size();
    tree.addTreeSelectionListener(treeSelectionEvent -> {
      selectedNode = (DefaultMutableTreeNode)
          tree.getLastSelectedPathComponent();
    });
    currentNode = root;
    treeframe = new JFrame();
    treeframe.setSize(800, 600);
    JScrollPane treeView = new JScrollPane(tree);
    treeframe.add(treeView);
    treeframe.setVisible(false);
  }

  public void putInBag(final BacktrackingNode currentBacktrackingNode) {
    addNode(currentBacktrackingNode, "      ");
  }

  private void update() {
    tree.updateUI();
    for (int i = 0; i < tree.getRowCount(); i++) {
      tree.expandRow(i);
    }
    treeframe.revalidate();
    treeframe.repaint();
  }

  public void show() {
    treeframe.setVisible(true);
  }

  public void close() {
    treeframe.dispose();
  }

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
    res.append("    ".repeat(Math.max(0, 10 - currentNode.getLevel())));
    res.append(" ".repeat(20 - currentBacktrackingNode.getName().length()));
    res.append(currentBacktrackingNode.getCurrentWeight());
    res.append(" / " + currentBacktrackingNode.getCurrentValue());
    DefaultMutableTreeNode child = new DefaultMutableTreeNode(res);
    currentNode.add(child);
    currentNode = (DefaultMutableTreeNode) currentNode.getLastChild();
    update();
  }

  public String getSolution() {
    if (selectedNode == null || selectedNode.getLevel() != ITEM_AMOUNT) {
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
