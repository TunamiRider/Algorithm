import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 7
 * 
 * Driver file for ThreeSAT.java
 * Tests the isSatisfiable() method. 
 *
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 *
 *
 *	This driver file contains a single example test case to get you started.
 *  You should include more test cases to ensure that your implementation works correctly.
 *  You do NOT need to submit this file.
 *			
 */

public class ThreeSATDriver{


    public static void main(String[] args){

		//implementation of the CNF boolean expression: (x1 v x4 v x2) ^ (~x1 v ~x2)
		int[][] example = {
							{ 1, 4, 2},
							{-1,-2   }
					};

		Boolean[] result = ThreeSAT.isSatisfiable(example);
		System.out.println(Arrays.toString(result));

    }



}