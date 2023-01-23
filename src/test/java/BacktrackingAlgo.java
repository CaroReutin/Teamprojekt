import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucksack.Item;
import rucksack.Level;
import solving.SolverBacktracking;
import solving.SolverGreedy;

public class BacktrackingAlgo {

  SolverBacktracking solverBacktracking = new SolverBacktracking();
  ArrayList<Integer> amount = new ArrayList<>();
  ArrayList<Item> items = new ArrayList<>();
  ArrayList<Item> expectedItems = new ArrayList<>();

  @BeforeEach
  public void cleanup(){
    amount = new ArrayList<>();
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
  }

  @Test
  public void levelOne() {
    items.add(new Item(7, 6, "coin"));
    items.add(new Item(8, 6, "crown"));
    items.add(new Item(20, 4, "pearl"));

    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(1));
    expectedItems.add(items.get(2));

    int capacity = 10;

    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }


  @Test
  public void levelTwo() {
    items.add(new Item(6, 6, "coin"));
    items.add(new Item(4, 3, "crown"));
    items.add(new Item(4, 1, "pearl"));
    items.add(new Item(7, 4, "picture"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(1));
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(3));

    int capacity = 10;
    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void levelThree() {
    items.add(new Item(31, 19, "coin"));
    items.add(new Item(14, 8, "crown"));
    items.add(new Item(8, 6, "pearl"));
    items.add(new Item(8, 2, "picture"));
    items.add(new Item(12, 12, "vase"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(0));

    int capacity = 20;
    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void levelFour() {
    items.add(new Item(2, 79, "coin"));
    items.add(new Item(7, 3, "crown"));
    items.add(new Item(6, 4, "pearl"));
    items.add(new Item(9, 8, "picture"));
    items.add(new Item(8, 11, "vase"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(1));
    expectedItems.add(items.get(3));

    int capacity = 14;

    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void levelFive() {
    items.add(new Item(7, 12, "coin"));
    items.add(new Item(8, 6, "crown"));
    items.add(new Item(4, 2, "pearl"));
    items.add(new Item(5, 5, "picture"));
    items.add(new Item(10, 8, "vase"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(1));
    expectedItems.add(items.get(3));
    expectedItems.add(items.get(4));

    int capacity = 20;
    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void levelSix() {
    items.add(new Item(16, 18, "coin"));
    items.add(new Item(10, 7, "crown"));
    items.add(new Item(7, 3, "pearl"));
    items.add(new Item(7, 4, "picture"));
    items.add(new Item(12, 14, "vase"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(0));
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(3));

    int capacity = 25;
    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void levelSeven() {
    items.add(new Item(25, 15, "coin"));
    items.add(new Item(26, 25, "crown"));
    items.add(new Item(21, 21, "pearl"));
    items.add(new Item(10, 6, "picture"));
    items.add(new Item(18, 16, "vase"));
    items.add(new Item(16, 11, "gold"));

    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);

    expectedItems.add(items.get(0));
    expectedItems.add(items.get(4));
    expectedItems.add(items.get(5));

    int capacity = 42;

    Assertions.assertEquals(expectedItems, solverBacktracking.solveBacktracking(items,amount,capacity));
  }

  @Test
  public void whereGreedyHadOptimalSolutions(){
    amount.add(1);
    amount.add(1);
    amount.add(1);
    // Sum of Weights = Capacity
    items.add(new Item(10,1,"1"));
    items.add(new Item(15,2,"2"));
    items.add(new Item(40,3,"3"));
    Level level = new Level(items,amount,0,6);
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // None Fit
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(10,7,"1"));
    items.add(new Item(15,8,"2"));
    items.add(new Item(40,9,"3"));
    level = new Level(items,amount,0,6);
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Best does not Fit
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(10,3,"1"));
    items.add(new Item(15,4,"2"));
    items.add(new Item(999999,9,"3"));
    level = new Level(items,amount,0,6);
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Random Numbers
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(130,20,"2"));
    items.add(new Item(200,30,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
  }

  @Test
  public void whereGreedyHadSuboptimalSolutions(){
    amount.add(1);
    amount.add(1);
    amount.add(1);
    // Random Numbers
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(120,30,"3"));
    Level level = new Level(items,amount,0,50);
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
  }

  @Test
  public void badInputs(){
    amount.add(1);
    amount.add(1);
    amount.add(1);
    // Weight 0
    items.add(new Item(60,0,"1"));
    items.add(new Item(100,40,"2"));
    items.add(new Item(120,30,"3"));
    Level level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(2));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Value 0
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(0,30,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Weight and Value 0
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(0,0,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Weight and Value Negative
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(-10,-20,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Weight Negative (Not allowed)
    /*
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(5000,-3,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
     */
    // Value Negative
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(100,20,"2"));
    items.add(new Item(-4,1,"3"));
    level = new Level(items,amount,0,50);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Capacity Negative
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(130,20,"2"));
    items.add(new Item(200,30,"3"));
    level = new Level(items,amount,0,-10);
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Capacity Zero
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    items.add(new Item(60,10,"1"));
    items.add(new Item(130,20,"2"));
    items.add(new Item(200,30,"3"));
    level = new Level(items,amount,0,0);
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
    // Randomly Sorted with 0 Weights
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    amount = new ArrayList<>();
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    amount.add(1);
    items.add(new Item(60,0,"1"));
    items.add(new Item(100,40,"2"));
    items.add(new Item(120,30,"3"));
    items.add(new Item(50,0,"4"));
    items.add(new Item(70,0,"5"));
    items.add(new Item(80,10,"6"));
    items.add(new Item(80,80,"7"));
    level = new Level(items,amount,0,80);
    expectedItems.add(items.get(0));
    expectedItems.add(items.get(1));
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(4));
    expectedItems.add(items.get(3));
    expectedItems.add(items.get(5));
    Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expectedItems),SolverGreedy.sortLikeGreedy(solverBacktracking.solveAlgorithm(level)));
  }

}
