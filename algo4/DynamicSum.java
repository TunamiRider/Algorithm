import java.util.HashMap;
import java.util.Map;

/**
 * CISC 380
 * Algorithms Assignment 4
 * 
 * Implements dynamic programming solutions to see if a subset adds up to a value.
 * 
 * @author Yuki Suzuki
 * Due Date: 3/31/20
 */



public class DynamicSum{

    public DynamicSum(){
        //YOUR CODE HERE
    }//constructor

    /**
     *Checks to see if a subset of arr adds up to exactly t with an iterative solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return True, if a subset adds up to t, false otherwise.
     * 
     */
    /*
     * Time complexity is O(n * t), n is the size of the array.
     * Since this checks each sum from 0 to the given sum (t) for each element in the array.
     */
    public boolean isSum(int[] arr, int t){
        //YOUR CODE HERE
    	boolean[][] T = new boolean[arr.length+1][t+1];
    	
    	return isSum(arr, t, T);
    }//isSum
    private boolean isSum(int[] arr, int t, boolean[][] T){
    	//Set left-most column to true.
    	for(int i=0; i<=arr.length; i++) {
    		T[i][0] = true;
    	}
    	for(int i=1; i<=arr.length; i++) {
    		
    		for(int j=1; j<=t; j++) {
    			//This tracks whether there is a subset.
    			T[i][j] = T[i-1][j];
    			//If a value is greater than or equal to the previous value in the array
    			if( T[i][j]==false && j>=arr[i-1] ) {
    				//Then checks a position at a value subtracted from the previous value in the array on the previous row
    				T[i][j] = T[i][j] || T[i-1][j-arr[i-1]];
    			}
    		}
    	}
    	//A last boolean tells whether it has a subset that adds up to t. 
    	return T[arr.length][t];
    }
    /**
     *Checks to see if a subset of arr adds up to exactly t with a memoizied solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return    True, if a subset adds up to t, false otherwise.
     * 
     */
    /*
     * Time complexity is O(n * t), n is the size of the array -1.
     * Since this checks each possible sum (0 to t) from the size of the array to 0.
     * The base case should be whether the size or the sum gets negative, or the sum gets zero.
     * 
     */
    public boolean isSumMem(int[] arr, int t ){
        //YOUR CODE HERE
    	Map<String, Boolean> T = new HashMap<>();
    	int n = arr.length - 1;
    	return isSumMem(arr, n, t, T);
    }//isSumMem
    private boolean isSumMem(int[] arr, int n, int s, Map<String, Boolean> T) {
    	//Return true if the sum gets zero
    	if( s == 0 ) {
    		return true;
    	}
    	//Return false if there is no element or no subset of the sum
    	if( n<0 || s<0 ) {
    		return false;
    	}
    	//Make a new key for the table
    	String key = n+" "+s;
    	//Every key has to be unique, so it has to go through, and check both the sum itself and the subtraction of it.
    	if( !T.containsKey(key) ) {
    		T.put(key, isSumMem(arr, n-1, s-arr[n], T) ||
    				isSumMem(arr, n-1, s, T) );
    	}
    	return T.get(key);
    }
    /**
     * Recovers the subset of arr that adds up to t, if it exists.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return a subset of arr that adds up to t, null otherwise.
     * 
     */
    /*
     * This traverses the table filled by isSum() to find a subset that adds up to t.
     * The running time is O(n) since it checks every element in a given array whether or not it composes the subset.
     */
    public int[] getSubset(int[] arr, int t){
        //YOUR CODE HERE
    	boolean[][] T = new boolean[arr.length+1][t+1];
    	if( isSum(arr, t, T) ) {
    		
    		int[] result = new int[arr.length];
    		int i=arr.length, j=t, size=0;
    		
    		while(i>0 && j>0 ) {
    			//Found an element that composes a subset.
    			if( T[i][j]==true && T[i-1][j]==false ) {
    				result[size] = arr[i-1];
    				j = j - arr[i-1];
    				i--;
    				size++;
    			}
    			//Found a sum that adds up to t, but not an element at i, that doesn't compose a subset.
    			else if( T[i][j]==true && T[i-1][j]==true ) {
    				i--;
    			}
    			//Otherwise decrement row until it finds an element that may compose a subset.
    			else {
    				j--;
    			}
    		}
    		int[] result2 = new int[size];
    		size -= 1;
    		for( i=0; i<result2.length; i++ ) {
    			result2[i] = result[size--];
    		}
    		return result2;
    	}
        return null;
    }//getSubset

}//class