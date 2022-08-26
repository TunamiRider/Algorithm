import java.util.HashMap;
import java.util.Map;

/**
 * CISC 380
 * Algorithms Assignment 4 EXTRA CREDIT
 * 
 * Implements a dynamic programming solution to find the length of the longest palindromic subsequence.
 * 
 * @author Yuki Suzuki
 * Due Date: 3/31/20
 */


public class PalindromicSequence{

    /**
     * Implements a dynamic programming solution to find the length of the longest Palindromic subsequence of the given string.
     * 
     * 
     * @param x the string that may contain a palindromic subsequence
     * @return the length of the longest palindromic subsequence, or 0 if there is none.
     */
    public static int getLengthLongestPalindrome(String x){
        //YOUR CODE HERE
    	int[][] table = new int[x.length()][x.length()];
    	return getLengthLongestPalindrome(x, table);
    }//longestPalindrome
    private static int getLengthLongestPalindrome(String x, int[][] table) {
    	//fill start position with 1 for each row.
    	for( int i=0; i<x.length(); i++) {
    		table[i][i] = 1;
    	}
    	int i, j, len;
    	//iterate the table diagonally in bottom-up fashion.
    	for(len=1; len<x.length(); len++) {
    		//iterate each column.
    		for(i=0; i<x.length()-len; i++) {
    			//get a row position.
    			j = i + len;
    			//if they are same, adds 2 + previous calculated value to the table.
    			if( x.charAt(i) == x.charAt(j) ) {
    				table[i][j] = table[i+1][j-1] + 2;
    			}
    			//otherwise take max value of either left or bottom element.
    			else {
    				table[i][j] = Math.max(table[i+1][j], table[i][j-1] );
    			}
    		}
    	}
    	return table[0][x.length()-1];
    }

    /**
     * Implements a dynamic programming solution to return the longest palindromic subsequence of the given string 
     * @param x the string that may contain a palindromic subsequence
     * @return the string of the longest palindrome, or null if there is none
     */
    public static String getLongestPalindrome(String x) {
        //YOUR EXTRA CREDIT CODE HERE
    	int[][] table = new int[x.length()][x.length()];
    	getLengthLongestPalindrome(x, table);
    	int end = table[0][x.length()-1];
    	char str[] =  new char[end+1];
    	end--;
    	int i=0, j=x.length()-1;
    	while( end>=0 && i<=j ) {
    		if( x.charAt(i) == x.charAt(j) ) {
    			str[end--] = x.charAt(i);
    			i++;
    			j--;
    		}
    		else {
    			if( table[i+1][j] > table[i][j-1] ) {
    				i++;
    			}
    			else {
    				j--;
    			}
    		}
    	}
		i=0;
		int mid = table[0][x.length()-1]/2;
		j=str.length-2;
    	if( mid%2!=0 ) {
    		while( j>mid ) {
    			str[i++] = str[j--];
    		}
    	}
    	else {
    		while( j>=mid ) {
    			str[i++] = str[j--];
    		}
    	}
    	return String.valueOf( str );
    }
}//class