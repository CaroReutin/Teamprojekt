package solving;

import backtrackingtree.BacktrackingTree;
import betatree.Tree;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import rucksack.BacktrackingItem;
import rucksack.Level;

public class ButtonEventHandlerTable extends ButtonEventHandler {
  //TODO remove myLevel
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
  private final ArrayList<BacktrackingItem> itemList = new ArrayList<>();
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
    myLevel = level;
    myLevel.turnIntoBacktracking();
    itemList.add(new BacktrackingItem(0, 0, "root", new ImageIcon()));
    ArrayList<BacktrackingItem> backtrackingTreeItemList = new ArrayList<>();
    for (int i = 0; i < level.getBacktrackingItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        itemList.add(level.getBacktrackingItemList().get(i));
        backtrackingTreeItemList.add(level.getBacktrackingItemList().get(i));
      }
    }
    backtrackingTree = new BacktrackingTree(myLevel.getCapacity(),
        backtrackingTreeItemList);
    generateNodes();
    int itemAmount = itemList.size();
    tree = new Tree(itemAmount, isSmallTree, leftCentric);
  }


  /**
   * adds the path where the next heaviest item is added to the trash.
   * bag to trash interaction has to be modeled with back
   * and then putInTrash.
   */
  @Override
  public void addToTrash(final int itemButtonIndex, final Level level) {
    boolean fromRucksackToTrash = level.getBacktrackingItemList().get(itemButtonIndex)
        .getState().equals(BacktrackingItem.StateBacktracking.RUCKSACK);
    ArrayList<Integer> fromTrashToRucksackSubsequent = new ArrayList<>();
    for (int i = itemButtonIndex + 1; i < level.getBacktrackingItemList().size(); i++) {
      if (level.getBacktrackingItemList().get(i).getState().equals(
          BacktrackingItem.StateBacktracking.TRASH)) {
        fromTrashToRucksackSubsequent.add(0);
      } else if (level.getBacktrackingItemList().get(i).getState().equals(
          BacktrackingItem.StateBacktracking.RUCKSACK)) {
        fromTrashToRucksackSubsequent.add(1);
      } else {
        fromTrashToRucksackSubsequent.add(2);
      }
    }
    if (this.backtrackingTree.addToTrash(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {
      int difference = Math.abs(itemButtonIndex - currentDepth);
      for (int i = 0; i < difference; i++) {
        this.back();
      }
      currentPath += "1";
      ImageIcon crossedOut = backtrackingTree.getCurrentNode().getItem().getImageIcon();
      BufferedImage crossedOutBuffered = new BufferedImage(
          crossedOut.getIconWidth(),
          crossedOut.getIconHeight(),
          BufferedImage.TYPE_INT_RGB);
      Graphics g = crossedOutBuffered.createGraphics();
      crossedOut.paintIcon(null, g, 0, 0);
      g.dispose();
      ImageIcon not = new ImageIcon("src/main/resources/icons/Not.png");
      not = new ImageIcon(not.getImage()
          .getScaledInstance(AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH));
      BufferedImage notBuffered = new BufferedImage(
          not.getIconWidth(),
          not.getIconHeight(),
          BufferedImage.TYPE_INT_RGB);
      Graphics g2 = notBuffered.createGraphics();
      not.paintIcon(null, g2, 0, 0);
      g2.dispose();
      for (int y = 0; y < crossedOutBuffered.getHeight(); y++) {
        for (int x = 0; x < crossedOutBuffered.getWidth(); x++) {
          int clr;
          if (notBuffered.getRGB(x, y) == -16777216) {
            clr = crossedOutBuffered.getRGB(x, y);
          } else {
            clr = notBuffered.getRGB(x, y);
          }
          crossedOutBuffered.setRGB(x, y, clr);
        }
      }
      addNode(new ImageIcon(crossedOutBuffered),
          backtrackingTree.getCurrentNode().getCurrentWeight() + "/"
              + backtrackingTree.getCurrentNode().getCurrentValue());
      if (fromRucksackToTrash) {
        level.setInRucksackAmountList(itemButtonIndex,
            level.getItemAmountInRucksack(itemButtonIndex) - 1);
        level.setCurrentWeight(level.getCurrentWeight()
            - level.getBacktrackingItemList().get(itemButtonIndex).getWeight());
        level.setCurrentValue(level.getCurrentValue()
            - level.getBacktrackingItemList().get(itemButtonIndex).getValue());
      } else {
        level.setAvailableItemAmountList(itemButtonIndex,
            level.getItemAmountAvailable(itemButtonIndex) - 1);
      }
      for (int i = itemButtonIndex + 1; i < level.getBacktrackingItemList().size(); i++) {
        if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1) == 0) {
          level.setInTrashAmountList(i,
              level.getInTrashAmountList().get(i) - 1);
          level.setAvailableItemAmountList(i, level.getItemAmountAvailable(i) + 1);
          level.getBacktrackingItemList().get(i)
              .setState(BacktrackingItem.StateBacktracking.AVAILABLE);
        } else if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1) == 1) {
          level.setInRucksackAmountList(i,
              level.getItemAmountInRucksack(i) - 1);
          level.setAvailableItemAmountList(i, level.getItemAmountAvailable(i) + 1);
          level.getBacktrackingItemList().get(i)
              .setState(BacktrackingItem.StateBacktracking.AVAILABLE);
          level.setCurrentWeight(level.getCurrentWeight()
              - level.getBacktrackingItemList().get(i).getWeight());
          level.setCurrentValue(level.getCurrentValue()
              - level.getBacktrackingItemList().get(i).getValue());
        }
      }
      level.setInTrashAmountList(itemButtonIndex,
          level.getInTrashAmountList().get(itemButtonIndex) + 1);
      level.getBacktrackingItemList().get(itemButtonIndex).setState(
          BacktrackingItem.StateBacktracking.TRASH);
    }
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
  @Override
  public void addToRucksack(final int itemButtonIndex, final Level level) {
    boolean fromTrashToRucksack =
        level.getBacktrackingItemList().get(itemButtonIndex).getState().equals(
            BacktrackingItem.StateBacktracking.TRASH);
    if (this.backtrackingTree.addToRucksack(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {
      currentPath += "0";
      addNode(backtrackingTree.getCurrentNode().getItem().getImageIcon(),
          backtrackingTree.getCurrentNode().getCurrentWeight() + "/"
              + backtrackingTree.getCurrentNode().getCurrentValue());
      if (fromTrashToRucksack) {
        level.setInTrashAmountList(itemButtonIndex,
            level.getInTrashAmountList().get(itemButtonIndex) - 1);
      } else {
        level.setAvailableItemAmountList(itemButtonIndex,
            level.getItemAmountAvailable(itemButtonIndex) - 1);
      }
      level.setInRucksackAmountList(itemButtonIndex,
          level.getItemAmountInRucksack(itemButtonIndex) + 1);
      level.setCurrentValue(level.getCurrentValue()
          + level.getBacktrackingItemList().get(itemButtonIndex).getValue());
      level.setCurrentWeight(level.getCurrentWeight()
          + level.getBacktrackingItemList().get(itemButtonIndex).getWeight());
      level.getBacktrackingItemList().get(itemButtonIndex).setState(
          BacktrackingItem.StateBacktracking.RUCKSACK);
    }
  }

  private void addNode(final ImageIcon labelIcon, final String buttonText) {
    int nextIndex = Integer.parseInt(currentPath, 2);
    currentDepth++;
    if (!exploredPaths.get(currentDepth).contains(nextIndex)) {
      exploredPaths.get(currentDepth).add(nextIndex);
      this.tree.addNode(currentDepth, nextIndex, labelIcon, buttonText);
    }
  }
}
