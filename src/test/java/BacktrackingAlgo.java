import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucksack.Item;
import solving.SolverBacktracking;

import java.util.ArrayList;

public class BacktrackingAlgo {

  SolverBacktracking solverBacktracking = new SolverBacktracking();
  ArrayList<Integer> amount;
  ArrayList<Item> items;
  ArrayList<Item> expectedItems;
  Integer expectedWeight;
  Integer expectedValue;

  @BeforeEach
  public void cleanup(){
    amount = new ArrayList<>();
    items = new ArrayList<>();
    expectedItems = new ArrayList<>();
    expectedValue = 0;
    expectedWeight = 0;
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

    expectedValue = 28;
    expectedWeight = 10;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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

    expectedWeight = 8;
    expectedValue = 15;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
      //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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

    expectedValue = 31;
    expectedWeight = 19;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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

    expectedValue = 18;
    expectedWeight = 11;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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
    expectedItems.add(items.get(2));
    expectedItems.add(items.get(4));

    expectedValue = 22;
    expectedWeight = 16;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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

    expectedValue = 30;
    expectedWeight = 25;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
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

    expectedValue = 59;
    expectedWeight = 42;

    //Assertions.assertEquals(SolverBacktracking.sortLikeBacktracking(expectedItems),
    //solverBacktracking.solveBacktracking(items, amount, 10));
    Assertions.assertEquals(expectedValue, solverBacktracking.getValueCorrect(items, amount, 10));
    Assertions.assertEquals(expectedWeight, solverBacktracking.getWeightCorrect(items, amount, 10));
  }



}
