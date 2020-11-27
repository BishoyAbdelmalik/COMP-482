import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;
public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        new Project2();
    }
    Project2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input2.txt"));
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        boolean flag = true;
        while (sc.hasNextInt()) {
            if (flag) {
                x.add(sc.nextInt());
                flag = false;
            } else {
                y.add(sc.nextInt());
                flag = true;
            }
        }
        Point L1Point = findL1(x, y);
        printPoint(L1Point);
        int distanceL1= findL1Distance(x, y, L1Point);
        System.out.println(" " + distanceL1);
        Point L2Point = findL2(x,y);
        printPoint(L2Point);
        double distanceL2=findL2Distance(x,y, L2Point);
        if(distanceL2==(int)distanceL2){
            System.out.println(" "+(int)distanceL2);
        }else{
            System.out.println(" "+distanceL2);
        }

    }
    public void printPoint(Point point){
        System.out.print( "(" + (int)point.getX() + "," + (int) point.getY() + ")");
    }

    private static Point findL2(ArrayList<Integer> theXValues, ArrayList<Integer> theYValues) {
        ArrayList<Integer> sortedX = new ArrayList<>();
        ArrayList<Integer> sortedY = new ArrayList<>();
        for (int i = 0; i < theXValues.size(); i++) {
            sortedX.add(theXValues.get(i));
            sortedY.add(theYValues.get(i));
        }
        Collections.sort(sortedX);
        Collections.sort(sortedY);
        int xStart=sortedX.get(0);
        int xEnd=sortedX.get(sortedX.size()-1);
        int yStart=sortedY.get(0);
        int yEnd=sortedY.get(sortedY.size()-1);
        Point L2=new Point(xStart, yStart);
        double distance=findL2Distance(theXValues,theYValues, L2);
        double bestDistance=distance;
        //Pair tempL2=L2;
        
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                Point point=new Point(i, j);
                distance=findL2Distance(theXValues,theYValues,point);
                if(distance<bestDistance){
                    L2=point;
                    bestDistance=distance;
                }
            }
        }
        
        return L2;
    }

    private static int findL1Distance(ArrayList<Integer> x, ArrayList<Integer> y, Point L1Point) {
        int sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum +=Math.abs(x.get(i) - L1Point.getX()) + Math.abs(y.get(i) - L1Point.getY());
        }
        return sum;
    }
    private static double findL2Distance(ArrayList<Integer> theXValues,ArrayList<Integer> theYValues, Point L2Point) {
        double sum = 0;
        for (int i = 0; i < theXValues.size(); i++) {
            sum += Math.sqrt(Math.pow((theXValues.get(i) - L2Point.getX()), 2) + Math.pow((theYValues.get(i) - L2Point.getY()), 2));
        }
        return sum;
    }

    private static Point findL1(ArrayList<Integer> theXValues, ArrayList<Integer> theYValues) {
        ArrayList<Integer> sortedX = new ArrayList<>();
        ArrayList<Integer> sortedY = new ArrayList<>();
        for (int i = 0; i < theXValues.size(); i++) {
            sortedX.add(theXValues.get(i));
            sortedY.add(theYValues.get(i));
        }
        Collections.sort(sortedX);
        Collections.sort(sortedY);

        return new Point(sortedX.get(sortedX.size() / 2), sortedY.get(sortedY.size() / 2));
    }

}