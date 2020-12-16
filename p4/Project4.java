/**
* Project 4
* read 2 dimensional array of integers
* find the number of entries in the longest path you can take 
* @author  Bishoy Abdelmalik
* @version 1.0
*/
import java.util.*;
import java.io.*;

public class Project4 {
	public static void main(String[] args) throws FileNotFoundException  {
		
		//read the input file and store it 2D array
		Scanner sc = new Scanner(new File("input4.txt"));
		int row=sc.nextInt();
		int col=sc.nextInt();
		//2D array will contain the actual values
		int[][] data=new int[row][col];
		//2D array will contain the values of the longest path from each point
		int[][] path=new int[row][col];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j]=sc.nextInt();
				path[i][j]=-1;
			}
		}
	
		//create object and path the data and path array to it
		Project4 p4=new Project4(data,path);
		System.out.println(p4.start());
	}
	private int[][] data;
	private int[][] path;
	private int max;
	Project4(int[][] data,int[][] path){
		this.data=data;
		this.path=path;
		//set the lower right node path to 1 
		this.path[data.length-1][data[0].length-1]=1;
		//set max path to 1
		this.max=1;
	}
	/**
	 * start checking for every point in the 2D array
	 * @return the max path
	 */
	public int start(){
		for (int row = data.length-1 ; row >=0; row--) {
			for (int col = data[0].length-1; col >= 0; col--) {
				calculate(row, col);
				//update the max point
				max=(path[row][col]>max)?path[row][col]:max;
			}
		}
		return max;
	}
	/**
	 * calculate the max path length for a given point
	 * @param row
	 * @param col
	 */
	public void calculate(int row,int col){
		// can we go right
		boolean rightExist=col+1<data[0].length && data[row][col]>data[row][col+1];
		// can we go down
		boolean downExist=row+1<data.length && data[row][col]>data[row+1][col];
		
		if(rightExist && downExist){
			path[row][col]=Math.max(path[row+1][col], path[row][col+1])+1;
		}else if (rightExist){
			path[row][col]=path[row][col+1]+1;		
		}else if(downExist){
			path[row][col]=path[row+1][col]+1;
		}else{
			path[row][col]=1;
		}
	}
}
