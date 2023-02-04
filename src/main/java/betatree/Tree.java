package betatree;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import rucksack.Item;
import rucksack.Level;

/**
 * the Tree.
 */
public class Tree {
  /**
   * the frame that shows the tree.
   */
  private final JFrame treeFrame = new JFrame();
  /**
   * all possible (and impossible) nodes.
   */
  private final ArrayList<ArrayList<String>>
      nodes = new ArrayList<>();
  /**
   * the indexes of explored paths matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<Integer>> exploredPaths = new ArrayList<>();
  /**
   * the buttons matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<JButton>> buttons = new ArrayList<>();
  /**
   * the labels matching to the buttons in buttons Arraylist.
   */
  private final ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();
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
   * makes a new tree for a specific level.
   *
   * @param level the level the tree will represent.
   */
  public Tree(final Level level) {
    final boolean isSmallTree = false;
    final boolean leftCentric = false;
    itemList.add(new Item(0, 0, "root"));
    for (int i = 0; i < level.getItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        itemList.add(level.getItemList().get(i));
      }
    }
    generateNodes();
    int itemAmount = itemList.size();
    int rowAmount = 2 * itemAmount - 1;
    int colAmount;
    if (isSmallTree) {
      colAmount = (int) Math.pow(2, itemAmount - 1);
    } else {
      colAmount = (int) Math.pow(2, itemAmount) - 1;
    }
    Panel panel = new Panel(new GridLayout(rowAmount, colAmount));
    ArrayList<Integer> columnsWithButtons;
    int rowCounter = 0;
    int rowCounter2 = 0;
    for (int i = 0; i < rowAmount; i++) {
      int stepDistance;
      if (i % 2 == 0) {
        columnsWithButtons = new ArrayList<>();
        stepDistance = (colAmount + 1) / (int) Math.pow(2, i / 2 + 1);
        if (stepDistance == 0) {
          for (int j = 0; j < colAmount; j++) {
            columnsWithButtons.add(j);
          }
        } else {
          for (int j = 1; j * stepDistance <= colAmount; j += 2) {
            if (leftCentric) {
              columnsWithButtons.add((j - 1) * stepDistance);
            } else {
              columnsWithButtons.add(j * stepDistance - 1);
            }
          }
        }
        int colCounter = 0;
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j)) {
            buttons.get(rowCounter).add(new JButton(nodes.get(rowCounter).get(colCounter)));
            buttons.get(rowCounter).get(colCounter).setVisible(false);
            panel.add(buttons.get(rowCounter).get(colCounter));
            colCounter++;
          } else {
            panel.add(new JLabel(""));
          }
        }
        rowCounter++;
      } else {
        columnsWithButtons = new ArrayList<>();
        stepDistance = (colAmount + 1) / (int) Math.pow(2, (i + 1) / 2 + 1);
        if (stepDistance == 0) {
          for (int j = 0; j < colAmount; j++) {
            columnsWithButtons.add(j);
          }
        } else {
          for (int j = 1; j * stepDistance <= colAmount; j += 2) {
            if (leftCentric) {
              columnsWithButtons.add((j - 1) * stepDistance);
            } else {
              columnsWithButtons.add(j * stepDistance - 1);
            }
          }
        }
        int colCounter = 0;
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j)) {
            labels.get(rowCounter2).add(new JLabel(itemList.get((i + 1) / 2).getIcon()));
            labels.get(rowCounter2).get(colCounter).setVisible(false);
            panel.add(labels.get(rowCounter2).get(colCounter));
            colCounter++;
          } else {
            panel.add(new JLabel(""));
          }
        }
        rowCounter2++;
      }
    }
    buttons.get(currentDepth).get(0).setVisible(true);
    treeFrame.add(panel);
    treeFrame.setSize(1000, 750);
    treeFrame.setVisible(false);
    treeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  private void generateNodes() {
    for (int i = 0; i < itemList.size(); i++) {
      nodes.add(new ArrayList<>());
      exploredPaths.add(new ArrayList<>());
      buttons.add(new ArrayList<>());
      labels.add(new ArrayList<>());
    }
    addNodes(0, 0, 0);
  }

  private void addNodes(final int depth, final int value, final int weight) {
    if (depth >= itemList.size()) {
      return;
    }
    String leftRes = value + "/" + weight;
    int newWeight = weight + itemList.get(depth).getWeight();
    int newValue = value + itemList.get(depth).getValue();
    String rightRes = newValue + "/" + newWeight;
    nodes.get(depth).add(leftRes);
    nodes.get(depth).add(rightRes);
    addNodes(depth + 1, value, weight);
    addNodes(depth + 1, newValue, newWeight);
  }

  /**
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  public void back() {
    if (currentDepth > 0) {
      currentDepth--;
      currentPath = currentPath.substring(0, currentPath.length() - 1);
    }
  }

  /**
   * adds the path where the next heaviest item is added to the bag.
   */
  public void putInBag() {
    if (itemList.size() - currentDepth <= 1) {
      return;
    }
    currentPath += "1";
    addNode();
  }

  private void addNode() {
    int nextIndex = Integer.parseInt(currentPath, 2);
    currentDepth++;
    if (!exploredPaths.get(currentDepth).contains(nextIndex)) {
      exploredPaths.get(currentDepth).add(nextIndex);
      buttons.get(currentDepth).get(nextIndex).setVisible(true);
      labels.get(currentDepth - 1).get(nextIndex).setVisible(true);
    }
    treeFrame.getContentPane().revalidate();
    treeFrame.getContentPane().repaint();
    treeFrame.revalidate();
    treeFrame.repaint();
  }

  /**
   * adds the path where the next heaviest item is added to the trash.
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  public void putInTrash() {
    if (itemList.size() - currentDepth <= 1) {
      return;
    }
    currentPath += "0";
    addNode();
  }

  /**
   * show the tree.
   */
  public void show() {
    treeFrame.setVisible(true);
  }
}
