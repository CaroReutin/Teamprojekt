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
    Node currentNode = root;
    Node parent = null;
    for (Item currentItem:allItemsOfLevel) {
     // Node newParent = currentNode;
      //currentNode = this.backtrackingRekursion(currentItem, currentNode,parent, capacity, new ArrayList<>());
      this.backtrackingRekursion(currentItem, currentNode,parent, capacity, new ArrayList<>());
      //parent = newParent;
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
   // if(currentNode.isPutInRucksack()) {
      newWeight = currentItem.getWeight() + currentNode.getCurrentWeight();
      newValue = currentItem.getValue() + currentNode.getCurrentValue();
   /* } else {
      newWeight = currentNode.getCurrentWeight() ;//parent.getCurrentWeight() + currentItem.getWeight();//currentNode.getCurrentWeight();
      newValue = currentNode.getCurrentValue() ;//parent.getCurrentValue() + currentItem.getValue();//currentNode.getCurrentValue();
    }*/



    if (newWeight > capacity) {
      currentNode.setPutInRucksack(false);
      if(parent != null) {
        currentNode.setCurrentValue(parent.getCurrentValue());
        currentNode.setCurrentWeight(parent.getCurrentWeight());

      } else {
        currentNode.setCurrentWeight(0);
        currentNode.setCurrentValue(0);
      }
      currentNode.setRightChildren(backtrackingRekursion(currentItem, currentNode.getRightChildren(), currentNode, capacity, selectedItems));
      return currentNode.getRightChildren();
    } else {

      //Test
      if(currentNode.getRightChildren() == null){
        Node notNode = new Node(currentNode.getCurrentWeight(), currentNode.getCurrentValue(), currentNode.getItem(), false);
        currentNode.setRightChildren(backtrackingRekursion(currentItem, notNode.getRightChildren(), notNode, capacity, selectedItems));
      } else{
        backtrackingRekursion(currentItem, currentNode.getRightChildren(), currentNode, capacity, selectedItems);
      }

      //
      if(currentNode.getLeftChildren() == null){
        currentNode.setPutInRucksack(true);
        currentNode.setCurrentWeight(newWeight);
        currentNode.setCurrentValue(newValue);
        selectedItems.add(currentNode.getItem());
        currentNode.setLeftChildren(backtrackingRekursion(currentItem, currentNode.getLeftChildren(),currentNode, capacity, selectedItems));
        if (newValue > bestValue) {
          bestValue = newValue;
          bestWeight = newWeight;
          bestSelectedItems = selectedItems;
        }
      } else {
        backtrackingRekursion(currentItem, currentNode.getLeftChildren(),currentNode, capacity, selectedItems);
      }
      return currentNode.getLeftChildren();
    }
   // return currentNode;

  }

}
