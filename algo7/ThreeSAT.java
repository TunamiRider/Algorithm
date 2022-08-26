/**
 * CISC 380
 * Algorithms Assignment 7
 * 
 * Implementation showing Exact4SAT reduces from 3SAT
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */

public class ThreeSAT{

    /**
     * 
     * Implements 3-SAT, using Exact4SAT as a subroutine.
     * 
     * The indexes of the array of Booleans that represent the solution to the 3-SAT problem should map to the variable labels of the problem.
     * 
     * For example, for the CNF expression of (x1 v x4 v ~x2) ^ (~x1 v ~x2)
     * the input should be formatted as the 2 dimensional integer array: { {1,4,-2},{-1,-2} }.
     * One possible output is: [false, false, false, true]
     * where the satisfying solution is x1 = false, x2 = false, and x4 = true.
     * (Notice how the value of a variable N can be accessed at result[N-1]).
     * 
     * 
     * @param clauses a 3xM array of integers, where M is the number of clauses. Each row is a different clause.
     * @return an array of Booleans that represent a solution to the 3-SAT problem, whose indexes represent the labels of each variable. Null otherwise.
     * 
     * 
     * 
     */
    public static Boolean[] isSatisfiable(int[][] clauses){
		//YOUR CODE HERE
		
        return null;
    }//isSatisfiable


}//class