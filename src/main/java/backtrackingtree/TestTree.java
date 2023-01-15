package backtrackingtree;

import java.util.ArrayList;
import java.util.Comparator;

import rucksack.BacktrackingItem;
import rucksack.Item;


public class TestTree {

  private BacktrackingTree tree;

  public TestTree() {
    ArrayList<BacktrackingItem> list = new ArrayList<>();

    BacktrackingItem item1 = new BacktrackingItem(7, 6, "Coin");
    BacktrackingItem item2 = new BacktrackingItem(8, 6, "Pearl");
    BacktrackingItem item3 = new BacktrackingItem(20, 4, "Crown");

    list.add(item1);
    list.add(item2);
    list.add(item3);

    list.sort(Comparator.comparingInt(Item::getWeight).reversed());
    final int cap = 10;

    tree = new BacktrackingTree(cap, list);

    tree.addToRucksack(list.get(0));
    tree.addToRucksack(list.get(1));
    tree.addToTrash(list.get(1));
    tree.addToRucksack(list.get(2));

    tree.print(System.out);
  }
}
