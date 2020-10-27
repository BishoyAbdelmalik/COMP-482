import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input2.txt"));
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<Pair> points = new ArrayList<>();

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
        for (int i = 0; i < x.size(); i++) {
            points.add(new Pair(x.get(i), y.get(i)));
        }
        // System.out.println(x.toString());
        // System.out.println(y.toString());
        Pair L1Point = findL1(x, y);
        System.out.print(L1Point);
        int L1distance = findL1Distance(x, y, L1Point);
        System.out.println(" " + L1distance);
        Pair L2Point = findL2(x,y,points);
        System.out.print(L2Point);
        System.out.println(" "+findL2Distance(points, L2Point));

    }

    private static Pair findL2(ArrayList<Integer> X, ArrayList<Integer> Y, ArrayList<Pair> points) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < X.size(); i++) {
            x.add(X.get(i));
            y.add(Y.get(i));
        }
        Collections.sort(x);
        Collections.sort(y);
        int xStart=x.get(0);
        int xEnd=x.get(x.size()-1);
        int yStart=y.get(0);
        int yEnd=y.get(y.size()-1);
        Pair L2=new Pair(xStart, yStart);
        double distance=findL2Distance(points, L2);
        double bestDistance=distance;
        //Pair tempL2=L2;
        
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

    private static int findL1Distance(ArrayList<Integer> x, ArrayList<Integer> y, Pair L1Point) {
        int sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum += L1Point.L1Distance(x.get(i), y.get(i));
        }
        return sum;
    }
    private static double findL2Distance(ArrayList<Pair> points, Pair L2Point) {
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            sum += L2Point.L2Distance(points.get(i));
        }
        return sum;
    }

    private static Pair findL1(ArrayList<Integer> X, ArrayList<Integer> Y) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < X.size(); i++) {
            x.add(X.get(i));
            y.add(Y.get(i));
        }
        Collections.sort(x);
        Collections.sort(y);
        return new Pair(x.get(x.size() / 2), y.get(y.size() / 2));
    }

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

        public int L1Distance(int x, int y) {
            return Math.abs(x - this.x) + Math.abs(y - this.y);
        }
        public int L1Distance(Pair point) {
            return L1Distance(point.x,point.y);
        }

        public double L2Distance(Pair point) {
            return L2Distance(point.x,point.y);
        }
        
        public double L2Distance(double x, double y) {
            return Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
        }
    }
}