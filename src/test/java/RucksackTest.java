import Rucksack.Item;
import Rucksack.Rucksack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RucksackTest {
    private Item coin;
    private Item crown;
    private Item pearl ;
    private Rucksack rucksack;

    @BeforeAll
    void configuration() {
        coin = new Item(5, 1, "coin");
        crown = new Item(50, 8, "crown");
        pearl = new Item(11, 2, "pearl");
        rucksack = new Rucksack(60);
    }
    @BeforeEach
    void reset(){
        rucksack.clearRucksack();
    }


    @Test
    void testAddItem() {
        rucksack.addItem(coin);
        rucksack.addItem(pearl);
        rucksack.addItem(crown);
        assertEquals(11, rucksack.getCurrentWeight());
        assertEquals(66, rucksack.getCurrentValue());
    }
    @Test
    void testAddToManyItem() {
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        rucksack.addItem(crown);
        assertEquals(56, rucksack.getCurrentWeight());
        assertEquals(350, rucksack.getCurrentValue());
    }
    @Test
    void testRemoveItem() {
        rucksack.addItem(coin);
        rucksack.addItem(pearl);
        rucksack.addItem(crown);
        rucksack.removeItem(crown);
        assertEquals(3, rucksack.getCurrentWeight());
        assertEquals(16, rucksack.getCurrentValue());
    }
    @Test
    void testRemoveItemNotInRucksack() {
        rucksack.addItem(coin);
        rucksack.addItem(pearl);
        rucksack.removeItem(crown);
        assertEquals(3, rucksack.getCurrentWeight());
        assertEquals(16, rucksack.getCurrentValue());
    }

}