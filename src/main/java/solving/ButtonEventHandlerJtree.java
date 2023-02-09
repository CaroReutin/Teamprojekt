package solving;

import java.util.ArrayList;
import jtree.BacktrackingTree;
import rucksack.BacktrackingItem;
import rucksack.Level;

public class ButtonEventHandlerJtree extends ButtonEventHandler {

  private final BacktrackingTree tree;
  private int lastDepth;

  public ButtonEventHandlerJtree(final Level level) {
    // TODO not tested might be broken
    lastDepth = 0;
    tree = new BacktrackingTree(level);
    myLevel = level;
    myLevel.turnIntoBacktracking();
    ArrayList<BacktrackingItem> oldList = level.getBacktrackingItemList();
    ArrayList<BacktrackingItem> logicItemList = new ArrayList<>();
    for (int i = 0; i < oldList.size(); i++) {
      for (int j = 0; j < level.getItemAmountAvailable(i); j++) {
        logicItemList.add(
            new BacktrackingItem(oldList.get(i).getValue(),
                oldList.get(i).getWeight(),
                oldList.get(i).getName(), oldList.get(i).getImageIcon()));
      }
    }
    backtrackingTree = new backtrackingtree.BacktrackingTree(
        level.getCapacity(), logicItemList);
  }

  @Override
  public void addToRucksack(final int itemButtonIndex, final Level level) {
    if (this.backtrackingTree.addToRucksack(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {

      this.myLevel.moveToRucksack(itemButtonIndex);
      this.tree.putInBag();
      lastDepth++;
    }
  }

  @Override
  public void addToTrash(final int itemButtonIndex, final Level level) {
    if (this.backtrackingTree.addToTrash(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {
      //this.myLevel.moveToTrash(itemButtonIndex);
      int difference = Math.abs(itemButtonIndex - lastDepth);
      for (int i = 0; i <= difference; i++) {
        this.tree.back();
        lastDepth--;
      }
      this.tree.putInTrash();
      lastDepth++;
    }
  }

  @Override
  public void show() {
    tree.show();
  }

  @Override
  public String getSolution() {
    return null;
  }
}
