package solving;

import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

public class SolverBacktracking extends Solver {
  //private ArrayList<Item> bestSelectedItems;
  //private int bestValue = 0;
  // private int bestWeight = 0;


  @Override
  public ArrayList solveAlgorithm(final Level level) {
    // TODO solveBacktracking aufrufen
    return null;
  }

  public ArrayList<Item> solveBacktracking(final ArrayList<Item> items,
                                           final ArrayList<Integer> amount,
                                           int capacity) {
    // Vorbereitung für die Rekursion
    ArrayList<Item> remainingItems = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        remainingItems.add(items.get(i));
      }
    }

    // Ich verstehe die Nodes nicht wirklich

    //Node root = new Node(0, 0, new Item(0, 0, "Wurzel"), false);
    //Node currentNode = root;
    //Node parent = null;


    // Bei Rekursion eigentlich keine for Schleifen nach dem Initialisieren
    // (das oben ist okay)

    //for (Item currentItem:allItemsOfLevel) {
    // Node newParent = currentNode;
    //currentNode = this.backtrackingRekursion(currentItem, currentNode,parent, capacity, new ArrayList<>());
    //this.backtrackingRekursion(currentItem, currentNode,parent, capacity, new ArrayList<>());
    //parent = newParent;
    //}

    // TODO Fall wenn es keine Items gibt (gibt aktuell Error)
    // Alles andere wird in der Rekursion behandelt
    return nextStep(new ArrayList<>(), remainingItems, capacity);

    // Momentan wird die Liste sortiert nach der Reihenfolge wie
    // sie in Level sind ausgegeben, zum Vergleichen von Lösungen sollten
    // sowohl diese Lösung als auch die zum Vergleichen sortiert werden
  }

  private ArrayList<Item> nextStep(final ArrayList<Item> itemsInRucksack,
                                   final ArrayList<Item> remainingItems,
                                   final int capacity) {
    // Default Case
    if (remainingItems.isEmpty()) {
      return itemsInRucksack;
    }

    // remainingItems ist final darum hier die extra Liste
    ArrayList<Item> nextRemainingItem = new ArrayList<>(remainingItems);
    Item currentItem = nextRemainingItem.get(0);
    nextRemainingItem.remove(0);

    // Fall aktuelles Item ist nicht in der Lösung
    ArrayList<Item> excluded = nextStep(itemsInRucksack, nextRemainingItem, capacity);

    // Wenn das Item nicht mehr reinpasst, kann es nicht in der Lösung sein
    if (currentItem.getWeight() > capacity) {
      return excluded;
    }

    // itemInRucksack ist final darum hier die extra Liste
    ArrayList<Item> rucksackWithCurrent = new ArrayList<>(itemsInRucksack);
    rucksackWithCurrent.add(currentItem);
    // capacity ist final darum hier extra
    int reducedCapacity = capacity - currentItem.getWeight();
    // Fall aktuelles Item ist in der Lösung
    ArrayList<Item> included =
        nextStep(rucksackWithCurrent, nextRemainingItem, reducedCapacity);

    // Werte zum Vergleichen was besser ist
    int excludedValue = getValue(excluded);
    int incudedValue = getValue(included);

    // Momentan wird ein Item mit Wert 0 nicht in den Rucksack getan,
    // kommt bei unseren leveln nicht vor
    if (excludedValue >= incudedValue) {
      return excluded;
    } else {
      return included;
    }
  }

  private int getValue(final ArrayList<Item> included) {
    int res = 0;
    for (Item item : included) {
      res += item.getValue();
    }
    return res;
  }

  /**
   * Add a new Item recursiv in the binaryTree
   * left children are Items that are put in rucksack
   * right childern are Items that not put in rucksack
   *
   * @param currentItem
   * @param currentNode
   * @param capacity
   * @return
   */
  /*
  public Node backtrackingRekursion(Item currentItem, Node currentNode, Node parent, int capacity,
                                    ArrayList<Item> selectedItems) {
    int newWeight;
    int newValue;

    if (currentNode == null) {
      newWeight = parent.getCurrentWeight() + currentItem.getWeight();
      newValue = parent.getCurrentValue() + currentItem.getValue();
      return new Node(newWeight, newValue, currentItem, true);
    }
    // if(currentNode.isPutInRucksack()) {
    newWeight = currentItem.getWeight() + currentNode.getCurrentWeight();
    newValue = currentItem.getValue() + currentNode.getCurrentValue();
    } else {
      newWeight = currentNode.getCurrentWeight() ;//parent.getCurrentWeight() + currentItem.getWeight();//currentNode.getCurrentWeight();
      newValue = currentNode.getCurrentValue() ;//parent.getCurrentValue() + currentItem.getValue();//currentNode.getCurrentValue();
    }


    if (newWeight > capacity) {
      currentNode.setPutInRucksack(false);
      if (parent != null) {
        currentNode.setCurrentValue(parent.getCurrentValue());
        currentNode.setCurrentWeight(parent.getCurrentWeight());

      } else {
        currentNode.setCurrentWeight(0);
        currentNode.setCurrentValue(0);
      }
      currentNode.setRightChildren(
          backtrackingRekursion(currentItem, currentNode.getRightChildren(), currentNode, capacity,
              selectedItems));
      return currentNode.getRightChildren();
    } else {

      //Test
      if (currentNode.getRightChildren() == null) {
        Node notNode = new Node(currentNode.getCurrentWeight(), currentNode.getCurrentValue(),
            currentNode.getItem(), false);
        currentNode.setRightChildren(
            backtrackingRekursion(currentItem, notNode.getRightChildren(), notNode, capacity,
                selectedItems));
      } else {
        backtrackingRekursion(currentItem, currentNode.getRightChildren(), currentNode, capacity,
            selectedItems);
      }

      //
      if (currentNode.getLeftChildren() == null) {
        currentNode.setPutInRucksack(true);
        currentNode.setCurrentWeight(newWeight);
        currentNode.setCurrentValue(newValue);
        selectedItems.add(currentNode.getItem());
        currentNode.setLeftChildren(
            backtrackingRekursion(currentItem, currentNode.getLeftChildren(), currentNode, capacity,
                selectedItems));
        if (newValue > bestValue) {
          bestValue = newValue;
          bestWeight = newWeight;
          //bestSelectedItems = selectedItems;
        }
      } else {
        backtrackingRekursion(currentItem, currentNode.getLeftChildren(), currentNode, capacity,
            selectedItems);
      }
      return currentNode.getLeftChildren();
    }
    // return currentNode;


  }

  public Integer getValueCorrect(ArrayList<Item> items, ArrayList<Integer> amount, int capacity) {
    //TODO gesamtgewicht aller items berechnen und prüfen ob dieses < capacity ist
    ArrayList<Item> allItemsOfLevel = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        allItemsOfLevel.add(items.get(i));
      }
    }
    Node root = new Node(0, 0, new Item(0, 0, "Wurzel"), false);
    Node currentNode = root;
    Node parent = null;
    for (Item currentItem : allItemsOfLevel) {
      Node newParent = currentNode;
      currentNode =
          this.backtrackingRekursion(currentItem, currentNode, parent, capacity, new ArrayList<>());
      parent = newParent;
    }

    System.out.println("Value: " + bestValue + " Weight: " + bestWeight);
    //for (Item current : bestSelectedItems) {
    //  System.out.println(
    //      current.getName() + " Weight: " + current.getWeight() + " Value: " + current.getValue());
    //}
    return bestValue;
  }


  public Integer getWeightCorrect(ArrayList<Item> items, ArrayList<Integer> amount, int capacity) {
    //TODO gesamtgewicht aller items berechnen und prüfen ob dieses < capacity ist
    ArrayList<Item> allItemsOfLevel = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        allItemsOfLevel.add(items.get(i));
      }
    }
    Node root = new Node(0, 0, new Item(0, 0, "Wurzel"), false);
    Node currentNode = root;
    Node parent = null;
    for (Item currentItem : allItemsOfLevel) {
      Node newParent = currentNode;
      currentNode =
          this.backtrackingRekursion(currentItem, currentNode, parent, capacity, new ArrayList<>());
      parent = newParent;
    }

    System.out.println("Value: " + bestValue + " Weight: " + bestWeight);
    //for (Item current : bestSelectedItems) {
    //  System.out.println(
    //      current.getName() + " Weight: " + current.getWeight() + " Value: " + current.getValue());
    //}
    return bestWeight;
  }
 */
}
