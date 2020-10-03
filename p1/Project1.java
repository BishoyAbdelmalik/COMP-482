import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Target;
import java.util.*;

public class Project1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input1.txt"));
        ArrayList<Integer> targets = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        while (sc.hasNext()) {
            int num = sc.nextInt();
            if (num == 0) {
                break;
            }
            // map.put(num,map.get(num)+1);
            values.add(num);
        }
        while (sc.hasNext()) {
            int num = sc.nextInt();
            targets.add(num);
        }
        for (Integer target : targets) {
            System.out.print(target + " ");
            if (checkBad(values, target)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");

            }

        }
    }

    private static boolean checkBad(ArrayList<Integer> values, int target) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < values.size(); k++) {
                    if (i == k || k == j)
                        continue;
                    if (values.get(i) + values.get(j) + values.get(k) == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // private static boolean check(HashMap<Integer, Integer> map,
    // ArrayList<Integer> targets) {

    // for (Integer target : targets) {
    // for (int i = 0; i < map.size(); i++) {

    // }

    // }
    // }

}