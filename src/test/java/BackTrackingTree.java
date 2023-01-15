import org.junit.jupiter.api.BeforeAll;
import rucksack.Item;

import java.util.ArrayList;
import java.util.Comparator;

public class BackTrackingTree {

  @BeforeAll
  public void initialize() {
    ArrayList<Item> items = new ArrayList<>();
    items.add(new Item(5, 1, "coin"));
    items.add(new Item(50, 8, "crown"));
    items.add(new Item(11, 2, "pearl"));

    items.sort(Comparator.comparingInt(Item::getWeight).reversed());
  }

  @Test


}
