package backtrackingtree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import rucksack.BacktrackingItem;
import rucksack.Item;


public class TestTree {

  private BacktrackingTree tree;

  public TestTree() {
    ArrayList<BacktrackingItem> list = new ArrayList<>();

    BacktrackingItem item1 = new BacktrackingItem(7, 6, "Crown");
    BacktrackingItem item2 = new BacktrackingItem(8, 6, "Pearl");
    BacktrackingItem item3 = new BacktrackingItem(20, 4, "Coin");

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
    tree.addToTrash(list.get(0));
    tree.addToRucksack(list.get(1));
    tree.addToRucksack(list.get(2));
    tree.print(System.out);
    //tree.addToTrash(list.get(0));


    // Ihr könnt eine Datei als PrintStream übergeben und den inhalt
    // der Datei dann mit dem erwarteten vergleichen
    File treeFileFolder = new File("./src/test/resources/");
    treeFileFolder.mkdirs();
    File treeFile = new File("./src/test/resources/treeOutput.txt");
    try {
      tree.print(new PrintStream(treeFile));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

  }
}
