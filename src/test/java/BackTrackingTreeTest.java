import backtrackingtree.BacktrackingTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucksack.BacktrackingItem;
import rucksack.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BackTrackingTreeTest {
  public ArrayList<BacktrackingItem> items = new ArrayList<>();

  @BeforeEach
  public void cleanUp() {
    items = new ArrayList<>();
  }

  @Test
  public void test1() throws UnexpectedException {
    BacktrackingItem crown = new BacktrackingItem(7, 6, "Crown");
    BacktrackingItem pearl = new BacktrackingItem(8, 6, "Pearl");
    BacktrackingItem coin = new BacktrackingItem(20, 4, "Coin");

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(crown);
    tree.addToRucksack(pearl);

    tree.addToTrash(pearl);
    tree.addToRucksack(coin);
    tree.addToTrash(crown);
    tree.addToRucksack(pearl);
    tree.addToRucksack(coin);
    tree.print(System.out);

    // Ihr könnt eine Datei als PrintStream übergeben und den inhalt
    // der Datei dann mit dem erwarteten vergleichen
    File treeFileFolder = new File("./src/test/resources/");
    treeFileFolder.mkdirs();
    File treeFile = new File("./src/test/resources/treeOutput.txt");
    try {
      tree.print(new PrintStream(treeFile));
      FileInputStream fis = new FileInputStream("./src/test/resources/treeOutput.txt");
      Scanner input = new Scanner(fis);

      String answer = input.nextLine();
      Assertions.assertEquals(answer, "root");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "├──not Crown");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  └──Pearl");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│     └──Coin");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──Crown");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──not Pearl");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──Coin");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test2() throws UnexpectedException {
    BacktrackingItem crown = new BacktrackingItem(6, 6, "Crown");
    BacktrackingItem pearl = new BacktrackingItem(4, 3, "Coin");
    BacktrackingItem coin = new BacktrackingItem(4, 1, "Feather");
    BacktrackingItem feather = new BacktrackingItem(7, 4, "Pearl");

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.add(feather);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(crown);
    tree.print(System.out);
    tree.addToRucksack(pearl);
    tree.print(System.out);
    tree.addToRucksack(coin);
    tree.print(System.out);
    tree.addToRucksack(feather);

    // Ihr könnt eine Datei als PrintStream übergeben und den inhalt
    // der Datei dann mit dem erwarteten vergleichen
    File treeFileFolder = new File("./src/test/resources/");
    treeFileFolder.mkdirs();
    File treeFile = new File("./src/test/resources/treeOutput.txt");
    try {
      tree.print(new PrintStream(treeFile));
      FileInputStream fis = new FileInputStream("./src/test/resources/treeOutput.txt");
      Scanner input = new Scanner(fis);

      /*String answer = input.nextLine();
      Assertions.assertEquals(answer, "root");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "├──not Crown");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  └──Pearl");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│     └──Coin");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──Crown");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──not Pearl");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──Coin");*/
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
