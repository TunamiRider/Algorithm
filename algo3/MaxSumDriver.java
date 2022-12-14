
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * Driver file for the MaxSum class
 * Tests the maxSumSubarray method.
 * 
 * 
 *  This driver file contains a single example test case to get you started.
 *  You should include more test cases to ensure that your implemenetation works correctly.
 *  You do NOT need to submit this file.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */



public class MaxSumDriver{


    public static void main(String[] args){
        try{


            int[] array = {10,-5,40,10};
                                                      
            System.out.println( MaxSum.maxSumSubarray(array) );
            
            int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
            System.out.println( MaxSum.maxSumSubarray(a) );
            
            int [] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            System.out.println( MaxSum.maxSumSubarray(A) );
            
            int [] B = {0, 1, 3, 0, 0, 2, 9, 7, 10};
            System.out.println( MaxSum.maxSumSubarray(B) );
            
            int [] C = {-1,-2,-3};
            System.out.println( MaxSum.maxSumSubarray(C) );
            
        }catch( Exception e){
            e.printStackTrace();
        }
       
    }//main


}