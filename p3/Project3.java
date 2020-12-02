/**
* Project 3
* read 2 sorted lists 
* find the average of the 2 middle elements in O(logn)
* find the number of inversions in the list in O(n)
* @author  Bishoy Abdelmalik
* @version 1.0
*/
import java.util.*;
import java.io.*;

public class Project3 {
    public static void main(String[] args) throws FileNotFoundException {
        //read the input file and store it in 2 lists
        Scanner sc = new Scanner(new File("input3.txt"));
        int n = sc.nextInt();
        int[] list1 = new int[n];
        int[] list1Ends = { 0, n };
        int[] list2 = new int[n];
        int[] list2Ends = { 0, n };

        for (int i = 0; i < n; ++i) {
            list1[i] = sc.nextInt();
        }
        for (int i = 0; i < n; ++i) {
            list2[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));
        //call the 2 methods
        System.out.println(findMedianAverage(list1, list2, list1Ends, list2Ends));
        System.out.println(countInversions(list1, list2));
    }
    /**
     * used to merge the 2 lists with 2 elements 
     * @param list1
     * @param list2
     * @return merged list
     */
    private static int[] merge(int[] list1, int[] list2) {
        int[] newList = new int[list1.length + list2.length];
        int index = 0;
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] < list2[i]) {
                newList[index++] = list1[i];
                newList[index++] = list2[i];
            } else {
                newList[index++] = list2[i];
                newList[index++] = list1[i];

            }
        }
        Arrays.sort(newList);
        return newList;
    }
    /**
     * find the average of middle elements
     * @param list1 first list
     * @param list2 second list
     * @param list1Ends array of start and end of the section in the first list 
     * @param list2Ends array of start and end of the section in the second list 
     * @return the average of the middle elements
     */
    private static double findMedianAverage(int[] list1, int[] list2, int[] list1Ends, int[] list2Ends) {
        int n1 = list1Ends[1] - list1Ends[0];
        int n2 = list2Ends[1] - list2Ends[0];
        int middle1 = list1Ends[0] + (n1 / 2);
        int middle2 = list2Ends[0] + (n2 / 2);
        boolean isEven=list1.length%2==0;
        int even=(isEven)?0:1;
        if (list1Ends[1] - list1Ends[0] <= 2) {
            int[] newList1 = { list1[list1Ends[0]], list1[list1Ends[1]-even ] };
            int[] newList2 = { list2[list2Ends[0]], list2[list2Ends[1]-even ] };
            int[] newList = merge(newList1, newList2);
            return ((double) (newList[newList.length / 2] + newList[(newList.length / 2) - 1])) / 2;
        }
        
        if (list1[middle1] > list2[middle2]) {
            int[] newlist1Ends = { list1Ends[0], middle1+even  };
            int[] newlist2Ends = { middle2, list2Ends[1] };
            return findMedianAverage(list1, list2, newlist1Ends, newlist2Ends);
        } else {
            int[] newlist1Ends = { middle1, list1Ends[1] };
            int[] newlist2Ends = { list2Ends[0], middle2+even};
            return findMedianAverage(list1, list2, newlist1Ends, newlist2Ends);
        }
    }
    /**
     * counts inversions by pretending to merge the lists
     * @param list1
     * @param list2
     * @return int the number of inversions
     */
    private static int countInversions(int[] list1, int[] list2) {
        int firstList = 0;
        int secondList = 0;
        int n = list1.length;
        int swaps = 0;
        while (firstList < n && secondList < n) {
            if (list1[firstList] <= list2[secondList])
                firstList++;
            else {
                secondList++;
                swaps += n - firstList;
            }
        }
        return swaps;
    }
}
