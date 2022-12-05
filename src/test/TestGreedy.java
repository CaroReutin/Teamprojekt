import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestGreedy {
    Solver s = new Solver();
    ArrayList<Integer> amount;
    ArrayList<Item> items;

    @BeforeEach
    public void cleanup(){
        amount = new ArrayList<>();
        items = new ArrayList<>();
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
        Assertions.assertEquals("2,0,1",s.solveGreedy(items,amount,6));
        // None Fit
        items = new ArrayList<>();
        items.add(new Item(10,7,"1"));
        items.add(new Item(15,8,"2"));
        items.add(new Item(40,9,"3"));
        Assertions.assertEquals("",s.solveGreedy(items,amount,6));
        // Random Numbers
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(130,20,"2"));
        items.add(new Item(200,30,"3"));
        Assertions.assertEquals("2,1",s.solveGreedy(items,amount,50));
    }

    @Test
    public void suboptimalSolutions(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        // Random Numbers
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(120,30,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
    }

    @Test
    public void badInputs(){
        amount.add(1);
        amount.add(1);
        amount.add(1);
        // Weight 0
        items = new ArrayList<>();
        items.add(new Item(60,0,"1"));
        items.add(new Item(100,40,"2"));
        items.add(new Item(120,30,"3"));
        Assertions.assertEquals("0,2",s.solveGreedy(items,amount,50));
        // Value 0
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,30,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
        // Weight and Value 0
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(0,0,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
        // Weight and Value Negative
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-10,-20,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
        // Weight Negative
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(5000,-3,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
        // Value Negative
        items = new ArrayList<>();
        items.add(new Item(60,10,"1"));
        items.add(new Item(100,20,"2"));
        items.add(new Item(-4,1,"3"));
        Assertions.assertEquals("0,1",s.solveGreedy(items,amount,50));
    }

    //TODO tests for amount not just 1s
}
