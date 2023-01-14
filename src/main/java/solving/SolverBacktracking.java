package solving;

import rucksack.Item;
import rucksack.Level;

import java.util.ArrayList;

public class SolverBacktracking extends Solver {
  private ArrayList<Item> bestSelectedItems;
  private int bestValue = 0;
  private int bestWeight = 0;
  private ArrayList<Integer> ammountsCopy;

  @Override
  public ArrayList solveAlgorithm(Level level) {
    return null;
  }

  public ArrayList<Item> solveBacktracking(ArrayList<Item> items, ArrayList<Integer> amount, int capacity) {
    //TODO gesamtgewicht aller items berechnen und prüfen ob dieses < capacity ist.
    ammountsCopy = amount;
    ArrayList<ArrayList<Item>> bestItems = new ArrayList<>();
    for (int k = 0; k < items.size(); k++) {
      bestItems.add(this.backtrackingRekursion(items, amount, capacity, 0, 0, new ArrayList<Item>()));
    }
    System.out.println("Value: " + bestValue + " Weight: " + bestWeight);
    for (Item current : bestSelectedItems) {
      System.out.println(current.getName() + " Weight: " + current.getWeight() + " Value: " + current.getValue());
    }
    return bestSelectedItems;
  }

  public ArrayList<Item> backtrackingRekursion(ArrayList<Item> items, ArrayList<Integer> amount, int capacity, int currentWeight,
                                    int currentValue, ArrayList<Item> selectedItems) {

    for (int i = 0; i < items.size(); i++) {
      for (int a = ammountsCopy.get(i); a > 0; a--) {
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
    }
    return bestSelectedItems;

  }


    /*public void backtrackingRekursion(ArrayList<Item> items, ArrayList<Integer> amount, int capacity, int currentWeight,
                                    int currentValue, int index, ArrayList<Item> selectedItems) {
    if(currentWeight <= capacity) {

      if(index >= (items.size())) {
        index = 0;
      }
      int newWeight = currentWeight + items.get(index).getWeight();
      if( newWeight <= capacity) {
        currentWeight = newWeight;
        currentValue = currentValue + items.get(index).getValue();
        selectedItems.add(items.get(index));
        //TEST
        System.out.println(" -----------  Weight: " +currentWeight + " Value: " + currentValue + "------------");
        for (Item current: selectedItems) {
          System.out.println(current.getName() + " Weight: " + current.getWeight() + " Value: "+ current.getValue());
        }
        //
        index ++;

        backtrackingRekursion(items, amount, capacity, currentWeight, currentValue, index, selectedItems);
      } else {
        if(currentValue > bestValue) {
          bestValue = currentValue;
          bestSelectedItems = selectedItems;
          selectedItems.remove(selectedItems.size() - 1);
        }
        return;
      }


      }else {
      if(currentValue > bestValue) {
        bestValue = currentValue;
        bestSelectedItems = selectedItems;
        selectedItems.remove(selectedItems.size() - 1);
      }
        return;
    }

    }*/
}
