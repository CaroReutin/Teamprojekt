package solving;

import backtrackingtree.BacktrackingTree;
import rucksack.Level;

/**
 * handels button events.
 */
public abstract class ButtonEventHandler {
  /**
   * The level.
   */
  private Level myLevel;

  /**
   * Getter method for the level.
   *
   * @return the level
   */
  public Level getMyLevel() {
    return myLevel;
  }

  /**
   * Setter method for the level.
   *
   * @param myLevel the new level
   */
  public void setMyLevel(final Level myLevel) {
    this.myLevel = myLevel;
  }

  /**
   * The tree for the backtracking level.
   */
  protected BacktrackingTree backtrackingTree;
  /**
   * Getter method for the backtracking tree.
   *
   * @return the tree
   */
  public Level getBacktrackingTree() {
    return myLevel;
  }

  /**
   * Setter method for the backtracking tree.
   *
   * @param backtrackingTree the new tree
   */
  public void setBacktrackingTree(final BacktrackingTree backtrackingTree) {
    this.backtrackingTree = backtrackingTree;
  }

  /**
   * The index of a level's last item .
   */
  private int indexOfLastItem;

  /**
   * Abstract method for adding items to a rucksack.
   *
   * @param itemButtonIndex index of the item that is supposed to be added
   * @param level in which level the adding takes place
   */
  public abstract void addToRucksack(int itemButtonIndex, Level level);

  /**
   * Abstract method for throwing items to the trash.
   *
   * @param itemButtonIndex index of the item that is
   *                        supposed to be thrown away
   * @param level in which level the throwing away takes place
   */
  public abstract void addToTrash(int itemButtonIndex, Level level);

  /**
   * Abstract level for showing something.
   */
  public abstract void show();

  /**
   * Abstract method for getting a solution.
   *
   * @return the String of a solution.
   */
  public abstract String getSolution();

  /**
   * Abstract method for resetting a level.
   *
   * @param level which is supposed to be reset
   * @param levelNumber number of the level
   */
  public abstract void resetLevel(Level level, int levelNumber);
}
