package solving;

import backtrackingtree.BacktrackingTree;
import rucksack.Level;

/**
 * handles button events.
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
   * @param level the new level
   */
  public void setMyLevel(final Level level) {
    this.myLevel = level;
  }

  /**
   * The tree for the backtracking level.
   */
  private BacktrackingTree backtrackingTree;

  /**
   * Getter method for the backtracking tree.
   *
   * @return the tree
   */
  public BacktrackingTree getBacktrackingTree() {
    return backtrackingTree;
  }

  /**
   * Setter method for the backtracking tree.
   *
   * @param myBacktrackingTree the new tree
   */
  public void setBacktrackingTree(final BacktrackingTree myBacktrackingTree) {
    this.backtrackingTree = myBacktrackingTree;
  }

  /**
   * Abstract method for adding items to a rucksack.
   *
   * @param itemButtonIndex index of the item that is supposed to be added
   * @param level           in which level the adding takes place
   */
  public abstract void addToRucksack(int itemButtonIndex, Level level);

  /**
   * Abstract method for throwing items to the trash.
   *
   * @param itemButtonIndex index of the item that is
   *                        supposed to be thrown away
   * @param level           in which level the throwing away takes place
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
   * @param level       which is supposed to be reset
   * @param levelNumber number of the level
   */
  public abstract void resetLevel(Level level, int levelNumber);
}
