package interviewBit;

import java.util.Hashtable;

public class LongestPalindromicSubstring {
	
	public static void main(String[] args) {
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		System.out.println(lps.longestPalindrome("aaaabaaa"));
	}
    
	public String longestPalindrome(String a) {
	    
	    if (a.length() == 0)
	        return a;
	    
	    String longest = "";
	    
	    Hashtable<String, Boolean> palindromeTable = new Hashtable<>();
	    
	    // Go from left to right -> always find occur first
	    for (int left = 0; left < a.length(); left++) {
	        
	        // Go from right to left -> always find largest
	        for (int right = a.length(); right > left && (right-left) > longest.length(); right--) {
	            
	            String sub = a.substring(left, right); // right exclusive, so we start from a.length
	            
	            if (isPalindrone(palindromeTable, sub))
	                longest = sub;
	            
	        }
	        
	    }
	    
	    return longest;
	    
	}
	
	public boolean isPalindrone(Hashtable<String, Boolean> palindromeTable, String a) {
	    
	    if (a.length() <= 1)
	        return true;
	    else if (palindromeTable.containsKey(a))
	        return palindromeTable.get(a);
	    else if (a.charAt(0) == a.charAt(a.length() - 1)) {
	        boolean result = isPalindrone(palindromeTable, a.substring(1, a.length()-1));
	        palindromeTable.put(a, result);
	        return result;
	    } else {
	        palindromeTable.put(a, false);
	        return false;
	    }
	        
	}
	
}
