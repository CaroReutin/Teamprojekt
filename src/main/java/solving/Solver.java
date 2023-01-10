package solving;

import java.util.ArrayList;
import rucksack.*;

/**
 * The class Solver holds the solving algorithms (greedy and backtracking).
 */
public class Solver {
  private ArrayList<Item> bestSelectedItems;
  private int bestValue=0;
  private int bestWeight = 0;

  /**
   * Solve greedy array list.
   *
   * @param level the level to solve
   * @return Returns an ArrayList of Items that a Greedy Algorithm would put in the Rucksack
   */
  public static ArrayList<Item> solveGreedy(Level level) {
    ArrayList<Item> items = level.getItemList();
    ArrayList<Integer> amount = level.getItemAmountList();
    int capacity = level.getRucksackCapacity();
    return solveGreedy(items, amount, capacity);
  }


  /**
   * Solve greedy array list.
   *
   * @param items    ArrayList of Items available
   * @param amount   Amount of Items available (amount.get(i) must be the amount of the item items.get(i))
   * @param capacity The capacity of the Rucksack
   * @return Returns an ArrayList of Items that a Greedy Algorithm would put in the Rucksack
   */
  public static ArrayList<Item> solveGreedy(ArrayList<Item> items, ArrayList<Integer> amount, int capacity) {
    int size = items.size();
    double[] ratio = new double[size];
    int[] indexes = new int[size];

    ArrayList<Item> inBag = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      indexes[i] = i;
      if (items.get(i).getWeight() > 0 && items.get(i).getValue() > 0) {
        ratio[i] = (double) items.get(i).getValue() / (double) items.get(i).getWeight();
      } else {
        ratio[i] = -1;
      }
    }

    for (int i = 0; i < items.size() - 1; i++) {
      for (int j = i + 1; j < items.size(); j++) {
        if (items.get(indexes[i]).getValue() < items.get(indexes[j]).getValue()) {
          int temp = indexes[i];
          indexes[i] = indexes[j];
          indexes[j] = temp;

          double tmp = ratio[i];
          ratio[i] = ratio[j];
          ratio[j] = tmp;
        }
      }
    }
    for (int i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < size; j++) {
        if (ratio[i] < ratio[j] || (ratio[j] == -1 && items.get(indexes[j]).getWeight() == 0 && items.get(indexes[i]).getValue() < items.get(indexes[j]).getValue())) {
          double tmp = ratio[i];
          ratio[i] = ratio[j];
          ratio[j] = tmp;

          int temp = indexes[i];
          indexes[i] = indexes[j];
          indexes[j] = temp;
        }
      }
    }

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < amount.get(indexes[i]); j++) {
        if ((capacity >= items.get(indexes[i]).getWeight() && ratio[i] >= 0) || (items.get(indexes[i]).getWeight() == 0 && items.get(indexes[i]).getValue() > 0)) {
          capacity = capacity - items.get(indexes[i]).getWeight();
          inBag.add(items.get(indexes[i]));
        } else {
          break;
        }
      }
    }
    return inBag;
  }

  /**
   * Use this to sort your solution before comparing with the result of solver.
   *
   * @param items an ArrayList of Items
   * @return returns the same items ordered by ratio, if tied ordered by value
   */
  public static ArrayList<Item> sortLikeGreedy(ArrayList<Item> items) {
    int size = items.size();
    double[] ratio = new double[size];

    for (int i = 0; i < size; i++) {
      if (items.get(i).getWeight() > 0 && items.get(i).getValue() > 0) {
        ratio[i] = (double) items.get(i).getValue() / (double) items.get(i).getWeight();
      } else {
        ratio[i] = -1;
      }
    }

    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        if (items.get(i).getValue() < items.get(j).getValue()) {
          Item tmp = items.get(i);
          items.set(i, items.get(j));
          items.set(j, tmp);

          double temp = ratio[i];
          ratio[i] = ratio[j];
          ratio[j] = temp;
        }
      }
    }
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        if (ratio[i] < ratio[j] || (ratio[j] == -1 && items.get(j).getWeight() == 0 && items.get(i).getValue() < items.get(j).getValue())) {
          Item tmp = items.get(i);
          items.set(i, items.get(j));
          items.set(j, tmp);

          double temp = ratio[i];
          ratio[i] = ratio[j];
          ratio[j] = temp;
        }
      }
    }
    return items;
  }


  public ArrayList<Item> solveBacktracking(ArrayList<Item> items, ArrayList<Integer> amount, int capacity){
    //TODO gesamtgewicht aller items berechnen und prüfen ob dieses < capacity ist
    this.backtrackingRekursion(items, amount, capacity, 0, 0, new ArrayList<Item>());
    System.out.println("Value: " + bestValue + " Weight: " + bestWeight);
    for (Item current:bestSelectedItems) {
      System.out.println(current.getName() + " Weigt: " + current.getWeight() + " Value: " + current.getValue());
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

  public void backtrackingRekursion(ArrayList<Item> items, ArrayList<Integer> amount, int capacity, int currentWeight,
                                    int currentValue, ArrayList<Item> selectedItems) {
    if(currentWeight <= capacity) {

      for(int i =0; i<items.size(); i++) {
        int newWeight = currentWeight + items.get(i).getWeight();
        if( newWeight <= capacity) {
          currentWeight = newWeight;
          currentValue = currentValue + items.get(i).getValue();
          selectedItems.add(items.get(i));
          //TEST
          System.out.println(" -----------  Weight: " +currentWeight + " Value: " + currentValue + "------------");
          //for (Item current: selectedItems) {
          //  System.out.println(current.getName() + " Weight: " + current.getWeight() + " Value: "+ current.getValue());
          //}
          System.out.println("selectedItems: " + selectedItems.size());
          //
          backtrackingRekursion(items, amount, capacity, currentWeight, currentValue, selectedItems);
        } else {
          if(currentValue > bestValue) {
            bestValue = currentValue;
            bestWeight = currentWeight;
            bestSelectedItems = selectedItems;
            selectedItems.remove(selectedItems.size() - 1);
          }
          return;
        }
      }
    }else {
      if(currentValue > bestValue) {
        bestValue = currentValue;
        bestWeight = currentWeight;
        bestSelectedItems = selectedItems;
        selectedItems.remove(selectedItems.size() - 1);
      }
      return;
    }

  }

  }

