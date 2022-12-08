import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestGreedy {
    Solver s = new Solver();
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
        level = new Level(new Rucksack(6),items,amount);
        expected.add(items.get(2));
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,6));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // None Fit
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(10,7,"1"));
        items.add(new Item(15,8,"2"));
        items.add(new Item(40,9,"3"));
        level = new Level(new Rucksack(6),items,amount);
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,6));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Best does not Fit
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(10,3,"1"));
        items.add(new Item(15,4,"2"));
        items.add(new Item(999999,9,"3"));
        level = new Level(new Rucksack(6),items,amount);
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,6));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Random Numbers
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(2));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
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
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
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
        level = new Level(new Rucksack(10),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,10));
        Assertions.assertEquals(expected,s.solveGreedy(level));
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
        level = new Level(new Rucksack(10),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,10));
        Assertions.assertEquals(expected,s.solveGreedy(level));
    }

    @Test
    public void Epic1(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        items.add(new Item(4,4,"Besen"));
        items.add(new Item(3,3,"Apfel"));
        items.add(new Item(2,2,"Kaugummi"));
        level = new Level(new Rucksack(5),items,amount);
        expected.add(items.get(0));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,5));
        Assertions.assertEquals(expected,s.solveGreedy(level));
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
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(2));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Value 0
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,30,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Weight and Value 0
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,0,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Weight and Value Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-10,-20,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Weight Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(5000,-3,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Value Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-4,1,"3"));
        level = new Level(new Rucksack(50),items,amount);
        expected.add(items.get(0));
        expected.add(items.get(1));
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,50));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Capacity Negative
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(new Rucksack(-10),items,amount);
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,-10));
        Assertions.assertEquals(expected,s.solveGreedy(level));
        // Capacity Zero
        items = new ArrayList<>();
        expected = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        level = new Level(new Rucksack(0),items,amount);
        Assertions.assertEquals(expected,s.solveGreedy(items,amount,0));
        Assertions.assertEquals(expected,s.solveGreedy(level));
    }
}
