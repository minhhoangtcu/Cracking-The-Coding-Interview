package epi.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a program to test whether the letters forming a string can be permuted to form a
 * palindrome. For example, "edified" can be permuted to form "deified".
 * 
 * @author minhhoang
 *
 */
public class HT131_Palindromic {

  private static final char OFFSET = 'a';
  
  public boolean testPalindromeHT(String s) {
    Map<Character, Integer> charFrequencies = new HashMap<>();
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      charFrequencies.merge(c, 1, Integer::sum);
    }
    
    int numOfOddFrequency = 0;

    for (Integer freq: charFrequencies.values()) {
      if (freq % 2 != 0 && ++numOfOddFrequency > 1) {
        return false;
      }
    }

    return true;
  }

  // Assume that s is not null, not empty, and does not contain non alphabetic characters
  public boolean testPalindrome(String s) {
    int[] count = new int[26]; // for 26 alphabetic characters

    // Count frequency of each character
    for (int i = 0; i < s.length(); i++) {
      count[getIndexWithBaseZero(s.charAt(i))]++;
    }

    // Check the frequency. If a string has more than one character that appears an odd number of
    // times, then it cannot be palindromic
    int numOfOddFrequency = 0;

    for (int i = 0; i < s.length(); i++) {
      if (count[i] % 2 != 0 && ++numOfOddFrequency > 1) {
        return false;
      }
    }

    return true;
  }

  private int getIndexWithBaseZero(char ch) {
    return Character.toLowerCase(ch) - OFFSET;
  }
}
