package solving;

import backtrackingtree.BacktrackingTree;
import rucksack.Level;

/**
 * handels button events.
 */
public abstract class ButtonEventHandler {
  protected Level myLevel;

  protected BacktrackingTree backtrackingTree;

  private int indexOfLastItem;

  public abstract void addToRucksack(int itemButtonIndex);

  public abstract void addToTrash(int itemButtonIndex);

  public abstract void show();
}
