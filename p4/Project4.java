import java.util.*;

public class Project4 {
	public static void main(String[] args) throws FileNotFoundException  {
		Scanner sc = new Scanner(new File("input4.txt"));
		int r=sc.nextInt();
		int c=sc.nextInt();
		int[][] data=new int[r][c];
		int[][] path=new int[r][c];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j]=sc.nextInt();
				path[i][j]=-1;
			}
		}

	}
}
