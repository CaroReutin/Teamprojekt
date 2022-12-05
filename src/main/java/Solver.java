import java.util.ArrayList;

public class Solver {

    public String solveGreedy(ArrayList<Item> items, ArrayList<Integer> amount, int capacity){
        int size = items.size();
        double[] ratio = new double[size];
        int[] indexes = new int[size];

        StringBuilder result = new StringBuilder();
        String tmpString;

        for (int i = 0; i < size; i++) {
            if (items.get(i).getWeight() > 0 && items.get(i).getValue() > 0){
                ratio[i] = (double)items.get(i).getValue() / (double)items.get(i).getWeight();
                indexes[i] = i;
            }else if(items.get(i).getValue() <= 0){
                ratio[i] = -1;
            }else if (items.get(i).getWeight() == 0){
                ratio[i] = -1;
                tmpString = i + ",";
                result.append(tmpString.repeat(amount.get(i)));
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
            tmpString = indexes[i] + ",";
            for (int j = 0; j < amount.get(indexes[i]); j++) {
                if (capacity >= items.get(indexes[i]).getWeight() && ratio[i] >= 0){
                    capacity = capacity - items.get(indexes[i]).getWeight();
                    result.append(tmpString);
                }else {
                    break;
                }
            }
        }
        if (result.length() > 0){
            return result.substring(0,result.length()-1);
        }
        return "";
    }
}
