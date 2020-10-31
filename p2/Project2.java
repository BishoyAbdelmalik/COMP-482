/**
* Project 2
* reads a set of data (lattice points - ordered pairs with both coordinates integer) from a file,
* stores that data into a data structure, and finds a lattice point which is most central to
* the data set and the total distance to a lattice point which is most central to the data 
* using both the L1(ie Manhattan) and L2 (ie normal distance) metrics.
*
* @author  Bishoy Abdelmalik
* @version 1.0
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input2.txt"));
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<Pair> points = new ArrayList<>();
        //read the input file and store the Xs and Ys in arraylist
        boolean isX = true;
        while (sc.hasNextInt()) {
            if (isX) {
                x.add(sc.nextInt());
                isX = false;
            } else {
                y.add(sc.nextInt());
                isX = true;
            }
        }
        //add points an arraylist 
        for (int i = 0; i < x.size(); i++) {
            points.add(new Pair(x.get(i), y.get(i)));
        }
        
        Pair L1Point = findL1(x, y);
        System.out.print(L1Point);
        int L1distance = findL1Distance(x, y, L1Point);
        System.out.println(" " + L1distance);
        Pair L2Point = findL2(x,y,points);
        System.out.print(L2Point);
        double L2distance = findL2Distance(points, L2Point);
        if(L2distance==(int)L2distance){
            System.out.println(" "+(int)L2distance);
        }else{
            System.out.println(" "+L2distance);
        }

    }
    /**
     * calculate the L2 point 
     * @param X an ArrayList of X axis of the point
     * @param Y an ArrayList of Y axis of the point
     * @param points an ArrayList of points
     * @return a Pair object representing the L2 point
     */
    private static Pair findL2(ArrayList<Integer> X, ArrayList<Integer> Y, ArrayList<Pair> points) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        //copy the arrays to sort them
        for (int i = 0; i < X.size(); i++) {
            x.add(X.get(i));
            y.add(Y.get(i));
        }
        //sort
        Collections.sort(x);
        Collections.sort(y);
        //get the starting and ending x and y values
        int xStart=x.get(0);
        int xEnd=x.get(x.size()-1);
        int yStart=y.get(0);
        int yEnd=y.get(y.size()-1);
        //consider L1 as the first point
        Pair L2=new Pair(xStart, yStart);
        //find distance and consider it best distance
        double distance=findL2Distance(points, L2);
        double bestDistance=distance;
        //loop through the rest of the points and keep the best one        
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                Pair point=new Pair(i, j);
                distance=findL2Distance(points,point);
                if(distance<bestDistance){
                    L2=point;
                    bestDistance=distance;
                }
            }
        }
        
        return L2;
    }
    /**
     * calculate the L1 distance from all the points to L1
     * @param X an ArrayList of X axis of the point
     * @param Y an ArrayList of Y axis of the point
     * @param L1Point a pair representing the L1 Point
     * @return return an int representing the L1 distance from all the points
     */
    private static int findL1Distance(ArrayList<Integer> x, ArrayList<Integer> y, Pair L1Point) {
        int sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum += L1Point.L1Distance(x.get(i), y.get(i));
        }
        return sum;
    }
    /**
     * calculate the L2 distance from all the points to L1
     * @param points an ArrayList of points 
     * @param L2Point a pair representing the L2 Point
     * @return return a double representing the L2 distance from all the points
     */
    private static double findL2Distance(ArrayList<Pair> points, Pair L2Point) {
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            sum += L2Point.L2Distance(points.get(i));
        }
        return sum;
    }


    /**
     * calculate the L1 point 
     * @param X an ArrayList of X axis of the point
     * @param Y an ArrayList of Y axis of the point
     * @return a Pair object representing the L1 point
     */
    private static Pair findL1(ArrayList<Integer> X, ArrayList<Integer> Y) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        //copy the arrays to sort them
        for (int i = 0; i < X.size(); i++) {
            x.add(X.get(i));
            y.add(Y.get(i));
        }
        //sort the array 
        Collections.sort(x);
        Collections.sort(y);
        return new Pair(x.get(x.size() / 2), y.get(y.size() / 2));
    }

    /**
     * Pair class used to represent a point   * 
     */
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
        /**
         * @param x
         * @param y 
         * @return L1 distance between passed x and y and current point 
         */
        public int L1Distance(int x, int y) {
            return Math.abs(x - this.x) + Math.abs(y - this.y);
        }
        /**
         * @param point
         * @return L1 distance between passed point and current point 
         */
        public int L1Distance(Pair point) {
            return L1Distance(point.x,point.y);
        }
        /**
         * 
         * @param point
         * @return  L2 distance between passed point and current point 
         */
        public double L2Distance(Pair point) {
            return L2Distance(point.x,point.y);
        }
        /**
         * 
         * @param x
         * @param y
         * @return L2 distance between passed x and y and current point 
         */
        public double L2Distance(double x, double y) {
            return Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
        }
    }
}