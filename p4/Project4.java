import java.util.*;
import java.io.*;

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
		path[data.length-1][data[0].length-1]=1;
		Project4 p4=new Project4(data,path);
		System.out.println(p4.start());
	}
	int[][] data;
	int[][] path;
	int max;
	Project4(int[][] data,int[][] path){
		this.data=data;
		this.path=path;
		this.max=1;
	}
	public int start(){
		for (int row = data.length-1 ; row >=0; row--) {
			for (int col = data[0].length-1; col >= 0; col--) {
				calculate(row, col);
			}
		}
		return max;
	}
	public void calculate(int row,int col){
		boolean rightExist=col+1<data[0].length && data[row][col]>data[row][col+1];
		boolean downExist=row+1<data.length && data[row][col]>data[row+1][col];
		
		if(rightExist&&downExist){
			path[row][col]=Math.max(path[row+1][col], path[row][col+1])+1;
		}else if (rightExist){
			path[row][col]=path[row][col+1]+1;		
		}else if(downExist){
			path[row][col]=path[row+1][col]+1;
		}else{
			path[row][col]=1;
		}
		max=(path[row][col]>max)?path[row][col]:max;
	}
}
