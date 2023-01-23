package solving;

import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * solver class for backtracking.
 */
public class SolverBacktracking extends Solver {

  /**
   * solves an instance of the rucksack problem.
   * Order of the returned list ist the same as the order
   * of the itemList in level
   *
   * @param level level
   * @return returns the list of items that are included in the optimal solution
   */
  @Override
  public ArrayList<Item> solveAlgorithm(final Level level) {
    return solveBacktracking(level.getItemList(), level.getItemAmountList(),
        level.getCapacity());
  }

  /**
   * solves an instance of the rucksack problem.
   * Order of the returned list ist the same as the order of the itemList
   *
   * @param items    the available items
   * @param amount   the amount of the items
   * @param capacity the capacity of the level
   * @return returns the list of items that are included in the optimal solution
   */
  public ArrayList<Item> solveBacktracking(final ArrayList<Item> items,
                                           final ArrayList<Integer> amount,
                                           final int capacity) {
    // Vorbereitung für die Rekursion
    ArrayList<Item> remainingItems = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        remainingItems.add(items.get(i));
      }
    }

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
    ArrayList<Item> excluded = nextStep(itemsInRucksack,
        nextRemainingItem, capacity);

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

}
