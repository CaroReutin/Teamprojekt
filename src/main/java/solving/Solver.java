package solving;

import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * The class Solver holds the solving algorithms (greedy and backtracking).
 */
public abstract class Solver {
  /**
   * This is abstract why does it need a comment ?.
   *
   * @param level level
   * @return return
   */
  public abstract ArrayList<Item> solveAlgorithm(Level level);

}

