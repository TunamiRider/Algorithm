
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * 
 * Implements an iterative dynamic programming solution to find the subarray of maximum sum, of a given array of numbers.
 * 
 * @author Yuki Suzuki
 * Due Date: 03/14/20
 * 
 */


public class MaxSum{

    /**
     * Returns the sum of the subarray with the maximum sum from the given array of integers.
     * 
     * 
     * @param a an array of integers
     * @return the sum of the subarray with the maximum sum.
     */
    public static int maxSumSubarray(int[] a){
    	if(a.length == 0 ) {
    		throw new IllegalArgumentException("should not be empty");
    	}	
        //YOUR CODE HERE
    	int T[] = new int[a.length];
    	int beststart = 0;
    	T[0] = a[0];
    	for(int i=1; i<a.length; i++) {
        	if( T[i-1]+a[i] > a[i] ) {        		
        		T[i] = T[i-1] + a[i];        		
        	}
        	else {
        		T[i] = a[i];
        		beststart = i;
        	}
    		//T[i] = Math.max( T[i-1]+a[i] , a[i] );
    	}
    	int max = T[0];
    	int bestend = 0;
    	for(int i=1; i<a.length; i++) {
    		if(T[i] > max ) {
    			max = T[i];
    			bestend = i;
    		}
    	}
    	return max;
    }//maxSubArray

}//class