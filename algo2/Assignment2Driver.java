
/**
 * CISC 380
 * Algorithms Assignment 2
 * 
 * Driver file for Max Sum Subarray and Dominant Entry in Assignment2.java
 * 
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */


public class Assignment2Driver {

    public static void main(String[] args){
        
        int[] array = {4, -6, 3, 1, 9, -8, 2, -10, 13};
        System.out.println(Assignment2.maxSubArray(array));

        int arr[] = {2, 3, 4, 5, 7}; 
        System.out.println(Assignment2.maxSubArray(arr));
        
        
        int[] array2 = {1, 3, 5, 3, 3, 6, 3};
        System.out.println(Assignment2.dominant(array2));
        
        int[] array5 = {1, 3 , 4, 5, 6, 7 };
        System.out.println(Assignment2.dominant(array5));
        
        
        int[] array3 = {6, 3, 2, 3, 3, 2, 3};
        System.out.println(Assignment2.divideAndConquerHill(array3));
	}//main

}