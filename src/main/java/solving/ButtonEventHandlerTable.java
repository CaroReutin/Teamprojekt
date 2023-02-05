package solving;

import betatree.Tree;
import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

public class ButtonEventHandlerTable extends ButtonEventHandler {
  private Tree tree;
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

  public ButtonEventHandlerTable(final Level level) {
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
    tree = new Tree(itemAmount, isSmallTree, leftCentric);
  }


  /**
   * adds the path where the next heaviest item is added to the trash.
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  public void addToTrash(final int itemButtonIndex) {
    if (itemList.size() - currentDepth <= 1) {
      return;
    }
    int difference = Math.abs(itemButtonIndex - currentDepth);
    for (int i = 0; i < difference; i++) {
      this.back();
    }
    currentPath += "0";
    addNode();
  }

  @Override
  public void show() {
    tree.show();
  }


  private void generateNodes() {
    for (int i = 0; i < itemList.size(); i++) {
      nodes.add(new ArrayList<>());
      exploredPaths.add(new ArrayList<>());
    }
    //addNodes(0, 0, 0);
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
  private void back() {
    if (currentDepth > 0) {
      currentDepth--;
      currentPath = currentPath.substring(0, currentPath.length() - 1);
    }
  }

  /**
   * adds the path where the next heaviest item is added to the bag.
   */
  public void addToRucksack(final int itemButtonIndex) {
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
      this.tree.addNode(currentDepth, nextIndex);
    }
  }
}