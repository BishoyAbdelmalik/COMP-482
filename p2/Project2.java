import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input2.txt"));
        ArrayList<Integer> x=new ArrayList<>();
        ArrayList<Integer> y=new ArrayList<>();
        boolean isX=true;
        while(sc.hasNextInt()){
            if(isX){
                x.add(sc.nextInt());
                isX=false;
            }else{
                y.add(sc.nextInt());
                isX=true;
            }
        }
        System.out.println(x.toString());
        System.out.println(y.toString());
        Pair L1Point=findL1(x,y);
        System.out.print(L1Point);
        int L1distance = findL1Distance(x,y,L1Point);
        System.out.println(" "+L1distance);

    }

    private static int findL1Distance(ArrayList<Integer> x, ArrayList<Integer> y, Pair L1Point) {
        int sum=0;
        for (int i = 0; i < x.size(); i++) {
            sum+=L1Point.L1Distance(x.get(i),y.get(i));
        }
        return sum;
    }

    private static Pair findL1(ArrayList<Integer> X, ArrayList<Integer> Y) {
        ArrayList<Integer> x=new ArrayList<>();
        ArrayList<Integer> y=new ArrayList<>();
        for (Integer val : X) {
            x.add(val);
        }
        for (Integer val : Y) {
            y.add(val);
        }
        Collections.sort(x);
        Collections.sort(y);
        return new Pair(x.get(x.size()/2),y.get(y.size()/2));
    }
    
}


class Pair {
    int x;
    int y;
    Pair(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
    public int L1Distance(int x,int y){
        return Math.abs(x-this.x)+Math.abs(y-this.y);
    }
    public double L2Distance(int x,int y){
        return Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
    }
}
