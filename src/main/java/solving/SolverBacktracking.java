package solving;

import rucksack.Item;
import rucksack.Level;

import java.util.ArrayList;

public class SolverBacktracking extends Solver {
  private ArrayList<Item> bestSelectedItems;
  private int bestValue = 0;
  private int bestWeight = 0;


  @Override
  public ArrayList solveAlgorithm(Level level) {
    return null;
  }

  public ArrayList<Item> solveBacktracking(ArrayList<Item> items, ArrayList<Integer> amount, int capacity) {

    //TODO gesamtgewicht aller items berechnen und prüfen ob dieses < capacity ist
    ArrayList<Item> allItemsOfLevel = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        allItemsOfLevel.add(items.get(i));
      }
    }
    Node root = new Node(0,0,new Item(0,0,"Wurzel"), false);
    for (Item currentItem:allItemsOfLevel) {
      this.backtrackingRekursion(currentItem, root,null, capacity, new ArrayList<>());
    }

    System.out.println("Value: " + bestValue + " Weight: " + bestWeight);
    for (Item current : bestSelectedItems) {
      System.out.println(current.getName() + " Weight: " + current.getWeight() + " Value: " + current.getValue());
    }
    return bestSelectedItems;
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
  public Node backtrackingRekursion(Item currentItem, Node currentNode, Node parent, int capacity, ArrayList<Item> selectedItems) {
    int newWeight;
    int newValue;

    if (currentNode == null) {
      newWeight = parent.getCurrentWeight() + currentItem.getWeight();
      newValue = parent.getCurrentValue() + currentItem.getValue();
      return new Node(newWeight, newValue, currentItem, true);
    }
    newWeight = currentItem.getWeight() + currentNode.getCurrentWeight();
    newValue = currentItem.getValue() + currentNode.getCurrentValue();

    if (newWeight > capacity) {
      currentNode.setRightChildren(backtrackingRekursion(currentItem, currentNode.getRightChildren(), currentNode, capacity, selectedItems));
    } else {
      selectedItems.add(currentItem);
      currentNode.setLeftChildren(backtrackingRekursion(currentItem, currentNode.getLeftChildren(),currentNode, capacity, selectedItems));
      if (newValue > bestValue) {
        bestValue = newValue;
        bestWeight = newWeight;
        bestSelectedItems = selectedItems;
      }
    }
    return currentNode;

    /* for (int i = 0; i < items.size(); i++) {
      for(int a = ammountsCopy.get(i); a>0; a--) {
=======
  public ArrayList<Item> backtrackingRekursion(ArrayList<Item> items, ArrayList<Integer> amount, int capacity, int currentWeight,
                                    int currentValue, ArrayList<Item> selectedItems) {

    for (int i = 0; i < items.size(); i++) {
      for (int a = ammountsCopy.get(i); a > 0; a--) {
>>>>>>> 503e577e75d7e278d2625babbd1c438733bf3746
        if (currentWeight <= capacity) {
          int newWeight = currentWeight + items.get(i).getWeight();
          if (newWeight <= capacity) {
            currentWeight = newWeight;
            currentValue = currentValue + items.get(i).getValue();
            selectedItems.add(items.get(i));
            ammountsCopy.set(i, ammountsCopy.get(i) - 1);

            //TEST
            System.out.println(
              " -----------  Weight: " + currentWeight + " Value: " + currentValue + "------------");
            System.out.println("selectedItems: " + selectedItems.size());
            //
            backtrackingRekursion(items, amount, capacity, currentWeight, currentValue, selectedItems);
          } else {
            if (currentValue > bestValue) {
              bestValue = currentValue;
              bestWeight = currentWeight;
              bestSelectedItems = selectedItems;
            }
            break;
          }

        } else {
          if (currentValue > bestValue) {
            bestValue = currentValue;
            bestWeight = currentWeight;
            bestSelectedItems = selectedItems;
          }
          currentWeight = currentWeight - items.get(i).getWeight();
          currentValue = currentValue - items.get(i).getValue();
          ammountsCopy.set(i, ammountsCopy.get(i) + 1);
          selectedItems.remove(selectedItems.size() - 1);
          break;
        }
      }
<<<<<<< HEAD

    }*/


  }

}
