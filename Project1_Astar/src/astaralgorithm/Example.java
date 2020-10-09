package astaralgorithm;
import java.util.Arrays;

public class Example {
	static int[][] goalstate = {
		      {1,2,3},
		      {4,5,6}, 
		      {7,8,0}, 
		};
	static int[][] inputstate = {
		      {1,2,3},
		      {4,0,8}, 
		      {7,6,5}, 
		};
	public static void main(String[] args) {
//		int a[]= {2,1};
//		int[] b = Arrays.copyOf(a, a.length);
//		b[0]=1;
//		System.out.print(a[0]);
//		System.out.print(b[0]);
		System.out.print(getManhattanCost(inputstate));
		
	}
	private static int getManhattanCost(int[][] arr) {
		// TODO Auto-generated method stub
		int searchelement[]=new int[2];
		int cost=0;
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(arr[i][j]!=0) {
					searchelement=getSearchIndex(arr[i][j]);
					int k=searchelement[0];
					int l=searchelement[1];
					//int heuristic=(int) Math.sqrt(Math.pow(k-i, 2)+Math.pow(l-j, 2));
					int heuristic= Math.abs(k-i)+Math.abs(l-j);
					cost=cost+heuristic;
				}
				}
		}
		return cost;
		
	}
	private static int[] getSearchIndex(int value) {
		// TODO Auto-generated method stub
		int[] valueindex=new int[2];
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(goalstate[i][j]==value)
				{
					valueindex[0]=i;
					valueindex[1]=j;
				}	
			}
		}		
		return valueindex;
	}
}
