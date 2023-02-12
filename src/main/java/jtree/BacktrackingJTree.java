package jtree;

import backtrackingtree.BacktrackingNode;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class BacktrackingJTree {
  private final JFrame treeframe;
  private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
  private JTree tree = new JTree(root);
  private DefaultMutableTreeNode currentNode;

  public BacktrackingJTree() {
    JScrollPane treeView = new JScrollPane(tree);
    currentNode = root;
    treeframe = new JFrame();
    treeframe.setSize(800, 600);
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
}
