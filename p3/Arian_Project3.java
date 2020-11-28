import java.util.*;
import java.io.*;


public class Project3 {
    public static void main(String args[]) throws FileNotFoundException {
	new Project3();
    }

    Project3() throws FileNotFoundException {
	Scanner sc = new Scanner(new File("input3.txt"));
	int n = sc.nextInt();
	int[] list1 = new int[n],
	    list2 = new int[n];
	for (int i = 0; i < n; ++i)
	    list1[i] = sc.nextInt();
	for (int i = 0; i < n; ++i)
	    list2[i] = sc.nextInt();
	System.out.println(findAverage(list1, list2));
	System.out.println(countInversions(list1, list2));
    }

    // Find the average
    double findAverage(int[] list1, int[] list2) {
	int n = list1.length;
	if (n == 2)
	    return averageOfSmallerTuple(list1, list2);
	if (list1[n / 2] > list2[n / 2])
	    return findAverage(Arrays.copyOfRange(list1, 0, (n / 2) + 1),
			       Arrays.copyOfRange(list2, n / 2, n));
	else
	    return findAverage(Arrays.copyOfRange(list1, n / 2, n),
			       Arrays.copyOfRange(list2, 0, (n / 2) + 1));
    }

    // Merge, but also count the inversions (swaps)
    int countInversions(int[] list1, int[] list2) {
	int i = 0,
	    j = 0,
	    k = 0,
	    n = list1.length;
	int swaps = 0;
	int[] newArray = new int[2 * n];
	while (i < n && j < n) {
	    if (list1[i] <= list2[j])
		newArray[k++] = list1[i++];
	    else {
		newArray[k++] = list2[j++];
		swaps += n - i;
	    }
	}
	while (i < n)
	    newArray[k++] = list1[i++];
	while (j < n)
	    newArray[k++] = list2[j++];
	return swaps;
    }

    // sum(min(list1, list2)) / 2
    double averageOfSmallerTuple(int[] list1, int[] list2) {
	if (list1[0] > list2[0])
	    return (double) (list1[0] + list1[1]) / 2;
	else
	    return (double) (list2[0] + list2[1]) / 2;
    }

    // Debug tool to look at the entire array
    void printAll(int[] list) {
	System.out.print("> ");
	for (int l : list) {
	    System.out.print(l);
	    System.out.print(" ");
	}
	System.out.println();
    }
}
