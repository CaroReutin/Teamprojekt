package solving;

import backtrackingtree.BacktrackingTree;
import betatree.Tree;
import gui.level.GuiLevelPageBacktracking;
import gui.level.GuiManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import rucksack.BacktrackingItem;
import rucksack.Level;

/**
 * handel button presses.
 */
public class ButtonEventHandlerTable extends ButtonEventHandler {

  /**
   * The tree of a backtracking level.
   */
  private final Tree tree;
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

  /**
   * makes a new button handler with a jtree window.
   *
   * @param level the level the jtree should show
   */
  public ButtonEventHandlerTable(final Level level) {
    final boolean isSmallTree = true;
    final boolean leftCentric = false;
    setMyLevel(level);
    getMyLevel().turnIntoBacktracking();
    itemList.add(new BacktrackingItem(0, 0, "root", new ImageIcon()));
    ArrayList<BacktrackingItem> backtrackingTreeItemList = new ArrayList<>();
    for (int i = 0; i < level.getBacktrackingItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        itemList.add(level.getBacktrackingItemList().get(i));
        backtrackingTreeItemList.add(level.getBacktrackingItemList().get(i));
      }
    }
    setBacktrackingTree(new BacktrackingTree(getMyLevel().getCapacity(),
        backtrackingTreeItemList));
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
    boolean fromRucksackToTrash = level.getBacktrackingItemList()
        .get(itemButtonIndex)
        .getState().equals(BacktrackingItem.StateBacktracking.RUCKSACK);
    ArrayList<Integer> fromTrashToRucksackSubsequent = new ArrayList<>();
    for (int i = itemButtonIndex + 1;
         i < level.getBacktrackingItemList().size(); i++) {
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
    if (this.getBacktrackingTree().addToTrash(
        this.getMyLevel().getBacktrackingItemList().get(itemButtonIndex))) {
      int difference = Math.abs(itemButtonIndex - currentDepth);
      for (int i = 0; i < difference; i++) {
        this.back();
      }
      currentPath += "1";
      ImageIcon crossedOut =
          getBacktrackingTree().getCurrentNode().getItem().getImageIcon();
      BufferedImage crossedOutBuffered = new BufferedImage(
          crossedOut.getIconWidth(),
          crossedOut.getIconHeight(),
          BufferedImage.TYPE_INT_RGB);
      Graphics g = crossedOutBuffered.createGraphics();
      crossedOut.paintIcon(null, g, 0, 0);
      g.dispose();
      URL urlNot = getClass().getResource("/icons/Not.png");
      assert urlNot != null;
      ImageIcon not = new ImageIcon(new ImageIcon(urlNot).getImage()
          .getScaledInstance(
          crossedOut.getIconWidth(),
          crossedOut.getIconHeight(),
          Image.SCALE_SMOOTH));
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
          final int colorRed = -16777216;
          if (notBuffered.getRGB(x, y) == colorRed) {
            clr = crossedOutBuffered.getRGB(x, y);
          } else {
            clr = notBuffered.getRGB(x, y);
          }
          crossedOutBuffered.setRGB(x, y, clr);
        }
      }
      addNode(new ImageIcon(crossedOutBuffered),
          getBacktrackingTree().getCurrentNode().getCurrentWeight() + "/"
              + getBacktrackingTree().getCurrentNode().getCurrentValue());
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
      for (int i = itemButtonIndex + 1;
           i < level.getBacktrackingItemList().size();
           i++) {
        if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1) == 0) {
          level.setInTrashAmountList(i,
              level.getInTrashAmountList().get(i) - 1);
          level.setAvailableItemAmountList(i,
              level.getItemAmountAvailable(i) + 1);
          level.getBacktrackingItemList().get(i)
              .setState(BacktrackingItem.StateBacktracking.AVAILABLE);
        } else if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1)
            == 1) {
          level.setInRucksackAmountList(i,
              level.getItemAmountInRucksack(i) - 1);
          level.setAvailableItemAmountList(i,
              level.getItemAmountAvailable(i) + 1);
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

  /**
   * Method for showing the backtracking tree.
   */
  @Override
  public void show() {
    tree.show();
  }


  /**
   * Method for generating the nodes of a tree.
   */
  private void generateNodes() {
    for (int i = 0; i < itemList.size(); i++) {
      exploredPaths.add(new ArrayList<>());
    }
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
    if (this.getBacktrackingTree().addToRucksack(
        this.getMyLevel().getBacktrackingItemList().get(itemButtonIndex))) {
      currentPath += "0";
      addNode(getBacktrackingTree().getCurrentNode().getItem().getImageIcon(),
          getBacktrackingTree().getCurrentNode().getCurrentWeight() + "/"
              + getBacktrackingTree().getCurrentNode().getCurrentValue());
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

  /**
   * Method for getting the solution as a String.
   *
   * @return String of the solution
   */
  @Override
  public String getSolution() {
    return tree.getSolution();
  }

  /**
   * Method for resetting the level.
   *
   * @param level       which is supposed to be reset
   * @param levelNumber number of the level
   */
  @Override
  public void resetLevel(final Level level, final int levelNumber) {
    level.resetLevel();
    tree.close();
    GuiManager.openLevel(new GuiLevelPageBacktracking(level), levelNumber);
  }
}
