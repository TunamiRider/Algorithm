import java.util.Arrays;

/**
 * CISC 380
 * 
 * Starter code for Assignment 2
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */


public class Assignment2 {

    /**
     * STARTER CODE FOR PROBLEM 2: Maximum Subarray
     * 
     * @param array the array where the maximum subarray will be found
     * @return the sum of the subarray with the maximum sum
     */
    public static int maxSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        else if (array == null) {
            return 0;
        }
        else {
            return maxSubArrayRecursive(array, 0, array.length-1);
        }
    }

    /**
     * STARTER CODE FOR PROBLEM 2: Maximum Subarray
     * 
     * @param array the array where the maximum subarray will be found
     * @param start the starting index where the recursive method will check
     * @param end the ending index where the recursive method will check
     * @return the sum of the subarray with the maximum sum.
     * Note: this method will return 0 if the length of the array is 0 and will be negative if the
     * array contains only negative numbers
     */
    private static int maxSubArrayRecursive(int[] array, int start, int end) {
        // ADD CODE TO COMPLETE THIS METHOD 
        // HINT: you will need to add a base case and change the return value
    	if( end == start ) {
    		return array[start];
    	}
    	int mid;
        int maxLeft;
        int maxRight;
        
        mid = (start + end) / 2;
        
        maxLeft = maxSubArrayRecursive(array, start, mid);
        maxRight = maxSubArrayRecursive(array, mid+1, end);

        //HINT: you will need to use maxLeft and maxRight in your solution
        
        int bestLeft = 0;
        int sumLeft = 0;
        for(int i=mid; i>=start; i--) {
        	sumLeft += array[i];
        	if( sumLeft > bestLeft ) { bestLeft = sumLeft; }
        }
 
        int bestRight = 0;
        int sumRight = 0;
        for(int i=mid; i<=end; i++) {
        	sumRight += array[i];
        	if( sumRight > bestRight ) { bestRight = sumRight; }
        }
        
        int subMax = bestLeft + bestRight;
        
        return Math.max(Math.max(maxLeft, maxRight), subMax);  // You will want to change this return value
    }

    /**
     * STARTER CODE FOR PROBLEM 3: Dominating Entry
     * 
     * @param array the array which will be evaluated for containing a dominant entry
     * @return the dominating entry, will return null if there is no dominating entry (return type Integer)
     */ 
    public static Integer dominant(int[] array) {
        // ADD CODE TO COMPLETE THIS METHOD
        // HINT: you will need to create a private recursive method (similar to maxSubArray())

        if (array.length == 0) {
            return null;
        }
        else if (array == null) {
            return null;
        }
        //recursivly divide and add 1 if they are not same, otherwise return 0, and if the result is less than <n/2, then it is dominating.
        return private_dominant(array, 0, array.length-1);

    }
    private static Integer private_dominant(int[] array, int start, int end) {
    	
    	if( start == end ) { return array[end]; }
    	
    	int mid = (start + end)/2;
    	Integer left = private_dominant(array, start, mid);
    	if( is_dom(left, array) ) { return left; }
    	
    	Integer right = private_dominant(array, mid+1, end);
    	if( is_dom(right, array) ) { return right; }

    	return null;
    }
    static boolean is_dom(Integer x, int[] array) {
    	if(x == null ) { return false; }
    	int count = 0;
    	for(int i=0; i<array.length; i++) {
    		if( array[i]==x ) { count++; }
    	}
    	if( count >= (array.length)/2 ) { return true; }
    	return false;
    }


    /**
     * STARTER CODE FOR PROBLEM 5a: Brute Force Hill
     * 
     * Implements a brute force approach to finding a hill in an array
     * @param arr - an array of integers
     * @return - the index of a hill within the array
     */
    public static int bruteHill(int[] arr){
        
        // ADD CODE TO COMPLETE THIS METHOD
    	if( arr.length < 1 ) { System.out.println("Invalid array"); return -1; }
    	int pre = 0;
    	int cur = 0;
    	int next = 0;
    	
    	if( arr.length==1 || arr[0]>=arr[1] ) { return 0; }//check if first element is a hill
    	if( arr[arr.length-1]>=arr[arr.length-2] ) { return arr.length-1; }
    	
    	for(int i=1; i<arr.length-1; i++) {
    		pre = arr[i-1];
    		cur = arr[i];
    		next = arr[i+1];
    		//check if cur is hill.
    		if( cur>=pre && cur >= next ) { return i; }
    	}
        return -1;
    }


    /**
     * STARTER CODE FOR PROBLEM 5b: Divide and Conquer Hill
     * 
     * Implements a divide and conquer approach to finding a hill in an array
     * @param arr - an array of integers
     * @return - the index of a hill within the array
     */
    public static int divideAndConquerHill(int[] arr){
        
        // ADD CODE TO COMPLETE THIS METHOD
        if (arr.length == 0) {
            return 0;
        }
        else if (arr == null) {
            return 0;
        }
        else {
            //return divideAndConquerHillRecursive(arr);
        		return divideAndConquerHillRecursive2(arr, 0, arr.length-1);
        }
    }
    private static int divideAndConquerHillRecursive2(int[] array, int start, int end) {
    		if( start == end ) {
    			return 0;
    		}
    		else if( end - start == 1 ) {
    			if(array[start] >= array[end] ) { return 0; }
    			else { return 1; }
    		}
    		else {
    			int mid = (start+end)/2;
    			if( array[mid-1] > array[mid]) { return divideAndConquerHillRecursive2(array, start, mid); }
    			else { return divideAndConquerHillRecursive2(array, mid+1, end) + mid; }
    		}
    }
    private static int divideAndConquerHillRecursive(int[] arr) {
    	if(arr.length <= 2) {
    		
    		if(arr.length==1) { return 0; }
    		else {
    			if( arr[0] >= arr[1] ) { return 0; }
    			else { return 1; }
    		}
    	}
    	else {
    		if( arr[arr.length/2-1] > arr[arr.length/2] ) { 
    			return divideAndConquerHill(Arrays.copyOfRange(arr, 0, arr.length/2));
    			//recurse left half
    		}else {
    			return divideAndConquerHill(Arrays.copyOfRange(arr, arr.length/2, arr.length)) + arr.length/2;
    			//recurse right half
    		}
    	}
    }
}