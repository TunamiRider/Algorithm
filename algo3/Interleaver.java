/**
 * CISC 380
 * Algorithms Assignment 3
 *
 * Implements a dynamic programming solution to the Interleaving Strings problem.
 * 
 * @author Yuki Suzuki
 * Due Date: 03/14/20
 */

public class Interleaver{

    
    public Interleaver(){
        //YOUR CODE HERE

       
    }//constructor

    /**
     * Finds if the two strings x and y are an interleaving of string z
     * 
     * The string Z is an interleaving of X and Y if it can be obtained
     * by interleaving the characters in X and Y in a way that
     * maintains the left-to-right order of the c in X and Y:
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return True, if the string z is an interleaving of x and y. False otherwise.
     */
    /**
     * Comments:
     * The nested loop goes through the 2d array which is the size of n*m. 
     * This says that the running time is O(nm) since their lengths are predefined,
     * and only iterating through the 2d array one time.
     */
    public Boolean isInterleaved(String x, String y, String z){

        //YOUR CODE HERE  
    	if( x.length()+y.length() != z.length() ) {
    		throw new IllegalArgumentException("This can't be interleaved.");
    	}
    	//create 2d table
    	boolean t[][] = new boolean[x.length()+1][y.length()+1];

    	return isInterleaved( x,  y,  z,  t );

    }//isInterleaved
    private Boolean isInterleaved(String x, String y, String z, boolean t[][]){
    	for( int i=0; i<= x.length(); i++) {
    		for(int j=0; j<= y.length(); j++) {
    			//add true to first row 
    			if( i==0 && j==0 ) 
    			{
    				t[i][j] = true;
    			}
    			//check if a character of y and z matches
    			else if( i==0 )
    			{
    				t[i][j] = t[i][j-1] && y.charAt(j-1) == z.charAt(i+j-1);
    			}
    			//check if a character of x and z matches
    			else if( j==0 )
    			{
    				t[i][j] = t[i-1][j] && x.charAt(i-1) == z.charAt(i+j-1);		
    			}
    			//check if either a character of x or y matches with a character of z
    			else
    			{
    				t[i][j] = ( t[i-1][j] && x.charAt(i-1) == z.charAt(i+j-1) )||
    						  ( t[i][j-1] && y.charAt(j-1) == z.charAt(i+j-1) );
    			}
    		}
    	}
    	return t[x.length()][y.length()];
    }

    /**
     * Returns a string representation of the solution of the interleaved string problem.
     * 
     * The return value is a string whose length is equal to z. 
     * All characters in z are replaced by character "x" if they come from the string "x",
     * and all characters in z are replaced by the character "y" if they come from the string y.
     * 
     * For example, on an input of x = "ab", y = "cd", and z = "abcd", then the output shall be the string "xxyy".
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return A string representation of the solution
     */
    public String getSolution(String x, String y, String z){

        //YOUR CODE HERE  
    	if( x.length()+y.length() != z.length() )
    	{
    		throw new IllegalArgumentException("This can't be interleaved.");
    	}
    	//create 2d table
    	boolean t[][] = new boolean[x.length()+1][y.length()+1];

		String result = "";

    	if( isInterleaved( x,  y,  z,  t ) )
    	{
    		int path = x.length() + y.length();
    		int row = x.length();
    		int col = y.length();

    		for(int i=0; i<path; i++)
    		{
    			if( col == 0 ) 
    			{
    				result = "x" + result;
    				row--;
    			}
    			else if( row == 0 )
    			{
    				result = "y" + result;
    				col--;
    			}
    			else if( t[row][col-1] == true ) 
    			{ 
    				result = "y" + result;
    				col--;
    			}
    			else if( t[row-1][col] == true )
    			{
    				result = "x" + result;
    				row--;
    			}
    		}
    	}
        return result;
    }
}//class