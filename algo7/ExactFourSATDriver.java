import java.util.Arrays;


 /**
 * CISC 380
 * Algorithms Assignment 7
 * 
 * Driver file for ExactFourSAT.java
 * Gives an example of the isSatisfiable() method. 
 *
 *
 *	This driver file contains an example of using the ExactFourSAT.isSatisfiable() method.
 *  You do NOT need to submit or Modify this file.
 * 
 * To input the CNF Expression (x1 v ~x2 v x3 v x5) ^ (x1 v x2 v x2 v x1),
 * it should be represented as the 2d integer array  {{1,-2,3,5}, {1,2,2,1}}.
 * One possible output is: [false, true, false, false, true]
 * Where x1 = false, x2 = true, x3 = false, and x5 = true.
 * (Notice how the value of a variable N can be accessed at result[N-1]).
 *			
 */

public class ExactFourSATDriver{


    public static void main(String[] args){

        //implementation of the CNF boolean expression: (x1 v ~x2 v x3 v x5) ^ (x1 v x2 v x2 vx1)
        int[][] test_array = {  
                                {1,-2,3,5},
                                {1, 2,2,1 }
                            };

        Boolean[] result = ExactFourSAT.isSatisfiable(test_array);

        System.out.println(Arrays.toString(result));
    }

}