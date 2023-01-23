package solving;

import rucksack.Item;
import rucksack.Level;

import java.util.ArrayList;

public class SolverGreedy extends Solver{
    @Override
    public ArrayList solveAlgorithm(Level level) {
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
}
