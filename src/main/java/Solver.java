import java.util.ArrayList;

public class Solver {

    public ArrayList<Item> solveGreedy(ArrayList<Item> items, ArrayList<Integer> amount, int capacity){
        int size = items.size();
        double[] ratio = new double[size];
        int[] indexes = new int[size];

        ArrayList<Item> inBag = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (items.get(i).getWeight() > 0 && items.get(i).getValue() > 0){
                ratio[i] = (double)items.get(i).getValue() / (double)items.get(i).getWeight();
                indexes[i] = i;
            }else if(items.get(i).getValue() <= 0){
                ratio[i] = -1;
            }else if (items.get(i).getWeight() == 0){
                ratio[i] = -1;
                inBag.add(items.get(i));
            }else if (items.get(i).getWeight() < 0){
                ratio[i] = -1;
            }
        }

        for (int i = 0; i < size-1; i++) {
            for (int j = i; j < size; j++) {
                if (ratio[i] < ratio[j]){
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
                if (capacity >= items.get(indexes[i]).getWeight() && ratio[i] >= 0){
                    capacity = capacity - items.get(indexes[i]).getWeight();
                    inBag.add(items.get(indexes[i]));
                }else {
                    break;
                }
            }
        }
        return inBag;
    }
}
