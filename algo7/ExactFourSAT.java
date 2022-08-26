import java.util.HashMap;

/**
 * CISC 380
 * Algorithms Assignment 7
 * 
 * Implementation of Exact4SAT.
 * You may assume that this implementation runs in polynomial time when using this as a subroutine in ThreeSAT.java
 *
 * Do not modify the contents of this file! Your assignment will be graded with this version of the file!
 */

public class ExactFourSAT{

    /**
     * Implementation of Exact4SAT.
	 * To be used as a subroutine in another NP complete implementation.
     * 
     * @param clauses a 4xM array of integers, where M is the number of clauses. Each row is a different clause.
     * @returns an array of Booleans, where the value of each variable is stored in the index of the array, or null if the expression is unsatisfiable.
	 * @throws IllegalArgumentException if clauses is null.
	 * @throws IllegalArgumentException if a clause in clauses is not length 4.
	 * @throws IllegalArgumentException if a literal is assigned a value of 0 or Integer.MAX_VALUE
     * 
     */
    public static Boolean[] isSatisfiable(int[][] clauses){
		
		if(clauses == null){
			throw new IllegalArgumentException("Exact4SAT.isSatisfiable: clauses variable is null.");
		}
		
		HashMap<Integer, Boolean> assignment = new HashMap<Integer, Boolean>();
		int maxLiteralLabel = -1;
		Boolean[] result;
		
		
		//for every clause
		for(int i = 0; i < clauses.length; i++){
			//check for length 4
			if(clauses[i].length != 4){
				throw new IllegalArgumentException("Exact4SAT.isSatisfiable: Clause must be length 4. Row " + i + " has " + clauses[i].length);
			}
					
			//for ever literal
			for(int j = 0; j < clauses[i].length; j++){
				
				//find the maximum variable label
				if(Math.abs(clauses[i][j]) > maxLiteralLabel){ 
					maxLiteralLabel = Math.abs(clauses[i][j]);
				}
	
				//if the literal is not currently in the assignment, add it and assign it a default value
				if( clauses[i][j] > 0 && !assignment.containsKey(clauses[i][j]) ){
					assignment.put(clauses[i][j], false);
				}else if( clauses[i][j] < 0 && !assignment.containsKey(Math.abs(clauses[i][j])) ){
					assignment.put(Math.abs(clauses[i][j]),false);
				//check for illegal literal labels
				}else if(clauses[i][j] == 0){
					throw new IllegalArgumentException("Exact4SAT.isSatisfiable: Clause must have 4 nonZero literals. Location [" + i + "][" + j + "] is zero" );
				}else if(Math.abs(clauses[i][j]) == Integer.MAX_VALUE){
					throw new IllegalArgumentException("ExactFourSAT.isSatisfiable: cannot have a litral with the label Integer.MAX_VALUE. Location [" + i + "][" + j + "] is Integer.MAX_VALUE");
				}
				

			}//for every literal
		}//for every clause
		
		//for every possible assignment of variables
		for(int i = 0; i < Math.pow(2,assignment.size()); i++){
			//remap the assignment in the hashmap
			remapAssignment( getBinary(i,assignment.size()) , assignment );
			
			//check for satisfied condition
			if(isSatisfied(clauses, assignment)){
				
				//convert the assignment to a Boolean[]
				result = new Boolean[maxLiteralLabel + 1];
				
				for(Integer key: assignment.keySet()){
					result[key] = assignment.get(key);
				}
				
				return result;
			}//if
		}//for
		
		//return null if no assignment satisfies the expression
        return null;
    }//exact4SAT
	
	
	/**
	*remaps the assignment of the variables in the hashmap with the given solution.
	*
	*@param solution an array of booleans that contain a potential solution 
	*@param assignment a hashmap of Integer:Booleans indicating the variable / value assignment.
	*
	*/
	private static void remapAssignment(boolean[] solution, HashMap<Integer,Boolean> assignment){
		if(solution.length != assignment.size()){
			throw new IllegalArgumentException("Exact4SAT.remapAssignment: solution and assignment length differ.");
		}
		
		int i = 0;
		for(Integer key: assignment.keySet()){
			assignment.put(key, solution[i]);
			i++;
		}
		
	}//remapAssignment
	
	/**
	*
	*converts an integer into a boolean array of the integer's binary representation, with padding.
	*
	*@param x the positive integer to be converted
	*@param solutionSize the number of bits in the array to represent. 
	*@return a boolean array of the binary representation of the integer, of size solutionSize
	*
	*/
	private static boolean[] getBinary(int x,int solutionSize){
		if(solutionSize < 1){
			throw new IllegalArgumentException("Exact4SAT getBinary: solution size must be at least one. actual: " + solutionSize);
		}
		if(x < 0){
			throw new IllegalArgumentException("Exact4SAT getBinary: x must be positive. actual: " + x);
		}
		
		//pads the binary string
		StringBuilder sb = new StringBuilder();
		String binaryString = Integer.toBinaryString(x);
		int toPad = solutionSize - binaryString.length();
		
		for(int i = 0; i < toPad; i++){
			sb.append("0");
		}
		sb.append(binaryString);
		binaryString = sb.toString();
		
		
		boolean[] result = new boolean[solutionSize];
		//create the solution from the binary string
		for(int i = 0; i < binaryString.length(); i++){
			if(binaryString.charAt(i) == '1'){
				result[i] = true;
			}//if
		}//for
		
		return result;
	}//getBinary
	
	
	/**
	*Checks to see if the given assignment satisfies the boolean expression in CNF.
	*
	*@param clauses the clauses to be satisfied
	*@param assignment the mapping of variable names to values
	*@returns true, if the assignment satisifies the expression, false otherwise
	*
	*/
	private static boolean isSatisfied(int[][] clauses, HashMap<Integer, Boolean> assignment){
		boolean result = true;
		
		//for every clause
		for(int i = 0; i < clauses.length; i++){
			//check is the assignment satisfies the clause, AND with result
			result = result && isSatisfied(clauses[i], assignment);
		}
		return result;
		
	}//isSatisfied [][]
	
	/**
	*Checks to see if the given assignment satisfies the given clause.
	*
	*@param clause the clause to be satisfied
	*@param assignment the mapping of variable names to values
	*@returns true, if the assignment satisifies the clause, false otherwise
	*
	*/
	private static boolean isSatisfied(int[] clause, HashMap<Integer, Boolean> assignment){
		boolean result = false;
		for(int i = 0; i < clause.length; i++ ){
			
			//check for variable that does not have a mapping
			if(clause[i] != 0 && !assignment.containsKey(clause[i]) && !assignment.containsKey(Math.abs(clause[i])) ){
				throw new IllegalArgumentException("Exact4SAT.isSatisfied: assignment does not contain variable "+ clause[i]);
			}//if
		
			//if positive literal
			if(clause[i] > 0){
				result = result || assignment.get(clause[i]);
			//if negative literal
			}else if(clause[i] < 0){
				result = result || !(assignment.get(Math.abs(clause[i])));
			}//elseif
		}//for
		
		return result;
		
	}//isSatisfied

}//class