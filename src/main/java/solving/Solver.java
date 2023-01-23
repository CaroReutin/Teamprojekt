package solving;

import java.util.ArrayList;
import rucksack.*;

/**
 * The class Solver holds the solving algorithms (greedy and backtracking).
 */
public abstract class Solver {
  private ArrayList<Item> bestSelectedItems;
  private int bestValue=0;
  private int bestWeight = 0;

  public abstract ArrayList solveAlgorithm(Level level);

  }

