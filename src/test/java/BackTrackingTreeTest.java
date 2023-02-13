import backtrackingtree.BacktrackingTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucksack.BacktrackingItem;
import rucksack.Item;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BackTrackingTreeTest {
  public ArrayList<BacktrackingItem> items = new ArrayList<>();

  @BeforeEach
  public void cleanUp() {
    items = new ArrayList<>();
  }

  /**
   * this test checks if item can be added to rucksack which:
   * - is too heavy and in available
   * - is not the next selectable item
   * and if item can be added to trash which has children
   * which are going to be available again
   * - should be first added to rucksack
   * - the subtree of this item is not full
   */
  @Test
  public void test1() {
    BacktrackingItem crown = new BacktrackingItem(7, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem pearl = new BacktrackingItem(8, 6, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));
    BacktrackingItem coin = new BacktrackingItem(20, 4, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(12, items);

    tree.addToRucksack(crown);
    tree.addToRucksack(pearl);

    tree.addToTrash(pearl);
    tree.addToRucksack(coin);
    //this cannot function
    tree.addToTrash(crown);
    tree.addToRucksack(pearl);
    tree.addToRucksack(coin);
    tree.addToRucksack(crown);
    tree.addToTrash(coin);
    tree.print(System.out);

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
      Assertions.assertEquals(answer, "└──Pearl [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──Crown [akt. Gewicht:12, akt. Wert: 15]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──not Coin [akt. Gewicht:12, akt. Wert: 15]");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * this test tests if an item can be added to rucksack from available
   * which is the lightest
   * which is not the next selectable item
   * which is too heavy for the bag
   */
  @Test
  public void test2() {
    BacktrackingItem crown = new BacktrackingItem(6, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem coin = new BacktrackingItem(4, 3, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));
    BacktrackingItem letter = new BacktrackingItem(4, 1, "Letter", new ImageIcon("src/main/resources/icons/letter.png"));
    BacktrackingItem pearl = new BacktrackingItem(7, 4, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.add(letter);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(11, items);

    tree.addToRucksack(crown);
    tree.addToRucksack(pearl);
    tree.addToRucksack(coin);
    tree.addToRucksack(letter);
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
      Assertions.assertEquals(answer, "└──Crown [akt. Gewicht:6, akt. Wert: 6]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──Pearl [akt. Gewicht:10, akt. Wert: 13]");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * this test tests if an already added item gets added again
   */
  @Test
  public void test3() {
    BacktrackingItem crown = new BacktrackingItem(6, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem coin = new BacktrackingItem(4, 3, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));
    BacktrackingItem letter = new BacktrackingItem(4, 1, "letter", new ImageIcon("src/main/resources/icons/letter.png"));
    BacktrackingItem pearl = new BacktrackingItem(7, 4, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.add(letter);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(crown);
    tree.print(System.out);
    tree.addToRucksack(crown);
    tree.print(System.out);
    tree.addToRucksack(pearl);
    tree.print(System.out);
    tree.addToRucksack(coin);
    tree.print(System.out);
    tree.addToRucksack(letter);
    tree.addToRucksack(crown);
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
      Assertions.assertEquals(answer, "└──Crown [akt. Gewicht:6, akt. Wert: 6]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──Pearl [akt. Gewicht:10, akt. Wert: 13]");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * this test tests
   * -if an item can be added to trash if in this branch no other lighter item can be put to rucksack
   * -if an already added item gets added again
   * and if the lightest item can be added even if it is not
   *
   */
  @Test
  public void test4() {
    BacktrackingItem crown = new BacktrackingItem(6, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem coin = new BacktrackingItem(4, 3, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));
    BacktrackingItem letter = new BacktrackingItem(4, 1, "letter", new ImageIcon("src/main/resources/icons/letter.png"));
    BacktrackingItem pearl = new BacktrackingItem(7, 4, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.add(letter);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(crown);
    tree.addToRucksack(letter);
    tree.addToRucksack(pearl);
    tree.addToTrash(pearl);
    tree.addToTrash(pearl);
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
      Assertions.assertEquals(answer, "└──Crown [akt. Gewicht:6, akt. Wert: 6]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   ├──not Pearl [akt. Gewicht:6, akt. Wert: 6]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──Pearl [akt. Gewicht:10, akt. Wert: 13]");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * this test tests:
   * - try to add an item into trash even through there are heavier items to look at first
   * - try to put an item into trash even through it should be added to the rucksack first
   * - add an item into trash and his subtree is full (should be success)
   */
  @Test
  public void test5() {
    BacktrackingItem crown = new BacktrackingItem(7, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem pearl = new BacktrackingItem(8, 6, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));
    BacktrackingItem coin = new BacktrackingItem(20, 4, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(crown);
    tree.addToRucksack(pearl);

    tree.addToTrash(pearl);
    tree.addToRucksack(coin);
    tree.addToTrash(coin);
    tree.addToTrash(crown);
    tree.addToRucksack(pearl);
    tree.addToTrash(coin);
    tree.addToRucksack(coin);
    tree.addToTrash(coin);
    tree.addToTrash(pearl);
    tree.addToRucksack(coin);
    tree.addToTrash(coin);
    tree.print(System.out);

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
      Assertions.assertEquals(answer, "├──not Pearl [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──Pearl [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──not Crown [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      ├──not Coin [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──Coin [akt. Gewicht:10, akt. Wert: 28]");


    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests
   * - if an item can be added trash again, which already is in trash
   * - try to add an item into trash even through there are same weighted but more valuable items to look at first
   * - try to put an item into trash even through it should be added to the rucksack first
   * - try to put a non-selectable item to rucksack
   *
   */
  @Test
  public void test6() {
    BacktrackingItem crown = new BacktrackingItem(7, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem pearl = new BacktrackingItem(8, 6, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));
   // BacktrackingItem coin = new BacktrackingItem(20, 4, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));

    items.add(crown);
    items.add(pearl);
    //items.add(coin);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToTrash(pearl);
    tree.addToRucksack(crown);
    tree.addToTrash(crown);
    tree.addToRucksack(pearl);
    tree.addToTrash(pearl);
    tree.addToTrash(pearl);
    tree.print(System.out);

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
      Assertions.assertEquals(answer, "├──not Pearl [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──Pearl [akt. Gewicht:6, akt. Wert: 8]");

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests if an item can be added to rucksack again, which already is in trash
   */
  @Test
  public void test7() {
    BacktrackingItem crown = new BacktrackingItem(7, 6, "Crown", new ImageIcon("src/main/resources/icons/Krone.png"));
    BacktrackingItem pearl = new BacktrackingItem(8, 6, "Pearl", new ImageIcon("src/main/resources/icons/Perle.png"));
    BacktrackingItem coin = new BacktrackingItem(20, 4, "Coin", new ImageIcon("src/main/resources/icons/Münze.png"));

    items.add(crown);
    items.add(pearl);
    items.add(coin);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(pearl);
    tree.addToRucksack(crown);
    tree.addToTrash(crown);
    tree.addToRucksack(crown);
    tree.print(System.out);

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
      Assertions.assertEquals(answer, "└──Pearl [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──not Crown [akt. Gewicht:6, akt. Wert: 8]");

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests if in it is possible to add an item with next possible weight but lower value than another possible item
   * -> so in one high in the tree there is look at only one item
   */
  @Test
  public void test8() {
    BacktrackingItem i1 = new BacktrackingItem(7, 6, "i1", new ImageIcon("src/main/resources/icons/DefaultBox.png"));
    BacktrackingItem i2 = new BacktrackingItem(8, 6, "i2", new ImageIcon("src/main/resources/icons/DefaultBox.png"));
    BacktrackingItem i3 = new BacktrackingItem(9, 6, "i3", new ImageIcon("src/main/resources/icons/DefaultBox.png"));

    items.add(i2);
    items.add(i1);
    items.add(i3);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(18, items);

    tree.addToRucksack(i3);
    tree.addToRucksack(i2);
    tree.addToRucksack(i1);
    tree.addToTrash(i1);
    tree.addToTrash(i3);

    tree.addToTrash(i2);
    tree.addToTrash(i3);
    tree. addToTrash(i2);
    tree.addToRucksack(i1);
    tree.addToTrash(i1);

    tree.addToTrash(i3);
    tree.addToRucksack(i1);
    tree.addToRucksack(i2);

    tree.print(System.out);

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
      Assertions.assertEquals(answer, "├──not i3 [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  └──i2 [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──i3 [akt. Gewicht:6, akt. Wert: 9]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   ├──not i2 [akt. Gewicht:6, akt. Wert: 9]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   │  ├──not i1 [akt. Gewicht:6, akt. Wert: 9]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   │  └──i1 [akt. Gewicht:12, akt. Wert: 16]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──i2 [akt. Gewicht:12, akt. Wert: 17]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      ├──not i1 [akt. Gewicht:12, akt. Wert: 17]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──i1 [akt. Gewicht:18, akt. Wert: 24]");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * full tree of BacktrackingLevel 1
   */
  @Test
  public void test9() {
    BacktrackingItem drawing = new BacktrackingItem(8, 6, "Zeichnung", new ImageIcon("src/main/resources/icons/Zeichnung.png"));
    BacktrackingItem letter = new BacktrackingItem(7, 6, "Brief", new ImageIcon("src/main/resources/icons/letter.png"));
    BacktrackingItem diamond = new BacktrackingItem(20, 4, "Diamant", new ImageIcon("src/main/resources/icons/Diamant.png"));

    items.add(diamond);
    items.add(drawing);
    items.add(letter);
    items.sort(Comparator.comparingInt(Item::getWeight).reversed());

    BacktrackingTree tree = new BacktrackingTree(10, items);

    tree.addToRucksack(drawing);
    tree.addToTrash(letter);
    tree.addToRucksack(diamond);
    tree.addToTrash(diamond);
    tree.addToTrash(drawing);
    tree.addToRucksack(letter);
    tree.addToRucksack(diamond);
    tree.addToTrash(diamond);
    tree.addToTrash(letter);
    tree.addToRucksack(diamond);
    tree.addToTrash(diamond);

    tree.print(System.out);

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
      Assertions.assertEquals(answer, "├──not Zeichnung [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  ├──not Brief [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  │  ├──not Diamant [akt. Gewicht:0, akt. Wert: 0]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  │  └──Diamant [akt. Gewicht:4, akt. Wert: 20]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│  └──Brief [akt. Gewicht:6, akt. Wert: 7]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│     ├──not Diamant [akt. Gewicht:6, akt. Wert: 7]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "│     └──Diamant [akt. Gewicht:10, akt. Wert: 27]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "└──Zeichnung [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "   └──not Brief [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      ├──not Diamant [akt. Gewicht:6, akt. Wert: 8]");
      answer = input.nextLine();
      Assertions.assertEquals(answer, "      └──Diamant [akt. Gewicht:10, akt. Wert: 28]");


    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
