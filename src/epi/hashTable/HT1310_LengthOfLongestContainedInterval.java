package epi.hashTable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Write a program which takes as input a set of integers represented by an array, and returns the
 * size of a largest subset of integers in the array having the property that if two integers are in
 * the subset, then so are all integers between them.
 * 
 * @author minhhoang
 *
 */
public class HT1310_LengthOfLongestContainedInterval {
  
  public static void main(String[] args) {
    System.out.println(getLengthOfLogestContainedInterval(new int[] {0, 1, 2, -5, -4, 4, 7, 10, 5}));
  }

  public static int getLengthOfLogestContainedInterval(int[] arr) {
    Set<Integer> unprocessedNumbers = Arrays.stream(arr).boxed().collect(Collectors.toSet());
    int longestInterval = Integer.MIN_VALUE;
    
    while (!unprocessedNumbers.isEmpty()) {
      int next = unprocessedNumbers.iterator().next();
      unprocessedNumbers.remove(next);
      
      // check left
      int nextLeft = next - 1;
      while (unprocessedNumbers.contains(nextLeft)) {
        unprocessedNumbers.remove(nextLeft);
        nextLeft--;
      }
      nextLeft++; // always -1 extra
      
      // check right
      int nextRight = next + 1;
      while (unprocessedNumbers.contains(nextRight)) {
        unprocessedNumbers.remove(nextRight);
        nextRight++;
      }
      nextRight--; // always +1 extra
      
      int currentSize = nextRight - nextLeft + 1; // length inclusive
      longestInterval = Math.max(longestInterval, currentSize);
    }
    
    return longestInterval;
  }
}
