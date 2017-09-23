package epi.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a program that takes an array and returns the length of a longest subarray with the
 * property that all its elements are distinct.
 * 
 * @author minhhoang
 *
 */
public class HT1309_LongestSubarrayDistinct {
  public static void main(String[] args) {
    System.out.println(getLongestDistinct("fsfetwenwe"));
    System.out.println(getLongestDistinct("abc"));
    System.out.println(getLongestDistinct("aabbc"));
  }
  
  private static String getLongestDistinct(String str) {
    int start = 0;
    int startMax = 0;
    int endMax = 1; // exclusive.
    Map<Character, Integer> charToLastOccurance = new HashMap<>();
    
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (charToLastOccurance.containsKey(c)) {
        start = charToLastOccurance.get(c) + 1;
      }
      if (i + 1 - start> endMax - startMax) {
        startMax = start;
        endMax = i + 1;
      }
      charToLastOccurance.put(c, i);
    }
    
    return str.substring(startMax, endMax);
  }
}
