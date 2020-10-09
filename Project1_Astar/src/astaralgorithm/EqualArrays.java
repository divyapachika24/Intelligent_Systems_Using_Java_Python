package astaralgorithm;
import java.util.Arrays;

public class EqualArrays {
	public static boolean checkequal (int[][] a1,int[][] a2) {
	 
	  if (equal(a1, a2)) {
	 	return true;
	  } else {
	 	return false;
	  }
	    }
	 
	    public static boolean equal(final int[][] arr1, final int[][] arr2) {
	 
	  if (arr1 == null) {
		  return (arr2 == null);
	 
	  }
	 
	 
	  if (arr2 == null) {
		  return false;
	  }
	 
	 
	  if (arr1.length != arr2.length) {
		  	return false;
	  }
	 
	 
	  for (int i = 0; i < arr1.length; i++) {
			if (!Arrays.equals(arr1[i], arr2[i])) {
			    return false;
			}
	  }
	  return true;
	    }
}
