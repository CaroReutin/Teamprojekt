package solving;

import java.util.ArrayList;
import jtree.BacktrackingTree;
import rucksack.BacktrackingItem;
import rucksack.Item;
import rucksack.Level;

public class ButtonEventHandlerJTree extends ButtonEventHandler {

  private final BacktrackingTree tree;
  private int lastDepth;

  public ButtonEventHandlerJTree(final Level level) {
    lastDepth = 0;
    tree = new BacktrackingTree(level);
    myLevel = level;
    ArrayList<Item> oldList = level.getItemList();
    ArrayList<BacktrackingItem> logicItemList = new ArrayList<>();
    for (int i = 0; i < oldList.size(); i++) {
      for (int j = 0; j < level.getItemAmountAvailable(i); j++) {
        logicItemList.add(
            new BacktrackingItem(oldList.get(i).getValue(), oldList.get(i).getWeight(),
                oldList.get(i).getName()));
      }
    }
    backtrackingTree = new backtrackingtree.BacktrackingTree(level.getCapacity(), logicItemList);
  }

  @Override
  public void addToRucksack(final int itemButtonIndex) {
    //if (this.backtrackingTree.addToRucksack((BacktrackingItem)
    //    this.myLevel.getItemList().get(itemButtonIndex))){


    this.myLevel.moveToRucksack(itemButtonIndex);
    this.tree.putInBag();
    lastDepth++;


    //}
  }

  @Override
  public void addToTrash(final int itemButtonIndex) {
    //if (this.backtrackingTree.addToTrash((BacktrackingItem)
    //    this.myLevel.getItemList().get(itemButtonIndex))){
    //this.myLevel.moveToTrash(itemButtonIndex);
    int difference = Math.abs(itemButtonIndex - lastDepth);
    for (int i = 0; i < difference; i++) {
      this.tree.back();
      lastDepth--;
    }
    this.tree.putInTrash();
    lastDepth++;
    //}
  }

  @Override
  public void show() {
    tree.show();
  }
}
