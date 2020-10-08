/**
* Project 1
* reads in a list of numbers and target values and 
* repeatedlydetermines whether any 3 items from 
* the data set sum to a target value.
*
* @author  Bishoy Abdelmalik
* @version 1.0
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input1.txt"));
        ArrayList<Integer> targets = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        //read the input and store it in the data structures
        while (sc.hasNext()) {
            int num = sc.nextInt();
            if (num == 0) {
                break;
            }
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            values.add(num);
        }
        while (sc.hasNext()) {
            int num = sc.nextInt();
            targets.add(num);
        }
        //call check if sum exist for each target value and print YES and NO based on result
        for (Integer target : targets) {
            System.out.print(target + " ");
            if (checkIfSumExist(map, values, target)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * Method checkIfSumExist will return true 
     * if the sum exist using 3 numbers 
     * in the input. otherwise return false.
     * @param map map is being used to check if the 3rd value is in the values
     * @param values the full list of values 
     * @param sum the target sum 
     * @return returns a boolean if the sum exist using three values in the arraylist
     */
    private static boolean checkIfSumExist(HashMap<Integer, Integer> map, ArrayList<Integer> values, int sum) {
        boolean stop=false;
        boolean returnValue=false;
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                if (i == j)
                    continue;
                int thirdValue = sum - (values.get(i) + values.get(j));
                //save the old values that was in the HashMap
                int tempIvalue = map.get(values.get(i));
                int tempJvalue = map.get(values.get(j));
                //subtract one from saved values in the HashMap to handle duplicate values in the list
                if (map.get(values.get(i)) > 0) {
                    map.replace(values.get(i), map.get(values.get(i)) - 1);
                }
                if (map.get(values.get(j)) > 0) {
                    map.replace(values.get(j), map.get(values.get(j)) - 1);
                }
                //check if third value exist
                if (map.containsKey(thirdValue) && map.get(thirdValue) != 0) {
                    returnValue=true;
                    stop=true;
                }
                //return the hash map to the original values
                map.replace(values.get(i), tempIvalue);
                map.replace(values.get(j), tempJvalue);
                if(stop){
                    break;
                }
            }
            if(stop){
                break;
            }
        }
        return returnValue;
    }

}