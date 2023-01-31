import rucksack.*;
import solving.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestGreedy {
    SolverGreedy s = new SolverGreedy();
    ArrayList<Integer> amount;
    ArrayList<Item> items;
    ArrayList<Item> expected;
    Level level;

    @BeforeEach
    public void cleanup(){
        amount = new ArrayList<>();
        items = new ArrayList<>();
        expected = new ArrayList<>();
    }

    @Test
    public void optimalSolutions(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        // Sum of Weights = Capacity
        items.add(new Item(10,1,"1"));
        items.add(new Item(15,2,"2"));
        items.add(new Item(40,3,"3"));
        level = new Level(items,amount,0,6);
        expected.add(items.get(2));
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,6));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // None Fit
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(10,7,"1"));
        items.add(new Item(15,8,"2"));
        items.add(new Item(40,9,"3"));
        level = new Level(items,amount,0,6);
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,6));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Best does not Fit
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(10,3,"1"));
        items.add(new Item(15,4,"2"));
        items.add(new Item(999999,9,"3"));
        level = new Level(items,amount,0,6);
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,6));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Random Numbers
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(2));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
    }

    @Test
    public void suboptimalSolutions(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        // Random Numbers
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(120,30,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
    }

    @Test
    public void Epic3(){
        // Suboptimal Order
        amount.add(1);
        amount.add(1);
        amount.add(2);
        items.add(new Item(5,5,"Goldbarren"));
        items.add(new Item(4,4,"Besen"));
        items.add(new Item(3,3,"Apfel"));
        level = new Level(items,amount,0,10);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,10));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Optimal Order
        amount = new ArrayList<>();
        items = new ArrayList<>();
        expected = new ArrayList<>();
        amount.add(1);
        amount.add(2);
        amount.add(1);
        items.add(new Item(4,4,"Besen"));
        items.add(new Item(3,3,"Apfel"));
        items.add(new Item(5,5,"Goldbarren"));
        level = new Level(items,amount,0,10);
        /*
        This was expected with the old implementation of greedy
        Now that items are sorted by value when tied the same result should be the same regardless of order
        expected.add(items.get(0));
        expected.add(items.get(1));
        expected.add(items.get(1));
         */
        expected.add(items.get(0));
        expected.add(items.get(2));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,10));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
    }

    @Test
    public void Epic1(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        items.add(new Item(4,4,"Besen"));
        items.add(new Item(3,3,"Apfel"));
        items.add(new Item(2,2,"Kaugummi"));
        level = new Level(items,amount,0,5);
        expected.add(items.get(0));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,5));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
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
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(2));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Value 0
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,30,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Weight and Value 0
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,0,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Weight and Value Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-10,-20,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Weight Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(5000,-3,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Value Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-4,1,"3"));
        level = new Level(items,amount,0,50);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,50));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Capacity Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(items,amount,0,-10);
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,-10));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Capacity Zero
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(items,amount,0,0);
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,0));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
        // Randomly Sorted with 0 Weights
        items = new ArrayList<>();
        expected = new ArrayList<>();
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
        expected.add(items.get(0));
        expected.add(items.get(1));
        expected.add(items.get(2));
        expected.add(items.get(4));
        expected.add(items.get(3));
        expected.add(items.get(5));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected), SolverGreedy.solveGreedy(items,amount,80));
        Assertions.assertEquals(SolverGreedy.sortLikeGreedy(expected),s.solveAlgorithm(level));
    }
}
