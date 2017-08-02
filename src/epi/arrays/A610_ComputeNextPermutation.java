package epi.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * There exist exactly n! permutations of n elements. These can be totally ordered using the
 * dictionary ordering - define permutation p to appear before permutation q if in the first place
 * where p and q differ in their array representations, starting from index 0, the corresponding
 * entry for p is less than that for q.
 * 
 * Write a program that takes as input a permutation, and returns the next permutation under
 * dictionary ordering. If the permutation is the last permutation, return the empty array. For
 * example, if the inputs is (1, 0, 3, 2), your function should return (1, 2, 0, 3). If the input is
 * (3, 2, 1, 0), return none.
 * 
 * @author minhhoang
 *
 */
public class A610_ComputeNextPermutation {
  
  public static void main(String[] args) {
    printResult(Arrays.asList(1, 2, 3));
    printResult(Arrays.asList(3, 2, 1));
    printResult(Arrays.asList(2, 5, 3, 1));
    printResult(Arrays.asList(5, 2, 3, 5, 6));
    printResult(Arrays.asList(5, 2, 8, 7, 6));
  }
  
  private static void printResult(List<Integer> in) {
    for (int e: in) {
      System.out.format("%d ", e);
    }
    System.out.print(" => ");
    for (int e: nextPermutation(in)) {
      System.out.format("%d ", e);
    }
    System.out.println();
  }
  
  // To get the next bigger number, the prefix should be as close to the original number as 
  // possible. Thus, we will consider the the permutation from right to left. Consider an 
  // continuous array of decreasing numbers, there is no way we can arrange to get the next bigger
  // number. Thus, we need to find the first number from right the left that breaks the pattern.
  // Then swap the breaking pattern number with the number that is closest bigger.
  public static List<Integer> nextPermutation(List<Integer> original) {
    List<Integer> out = new ArrayList<>();
    
    // Find the first number that breaks the pattern.
    int lastNumber = 0;
    int i = original.size() - 1;
    while (i >= 0) { 
      int currentNumber = original.get(i--); 
      out.add(0, currentNumber);
      if (currentNumber < lastNumber) {
        break;
      }
      lastNumber = currentNumber;
    }
    if (i < 0) {
      return original; // max permu already.
    }
    
    // Swap the pattern breaking number with next bigger.
    int nextBigger = Integer.MAX_VALUE;
    int nextBiggerIndex = out.size() - 1;
    for (int j = 0;j < out.size(); j++) {
      if (out.get(0) < out.get(j) && out.get(j) < nextBigger) {
        nextBigger = out.get(j);
        nextBiggerIndex = j;
      }
    }
    Collections.swap(out, 0, nextBiggerIndex);
    int temp = out.remove(0);
    Collections.reverse(out);
    out.add(0, temp);
    
    // Add the remaining numbers at the beginning.
    while (i >= 0) {
      out.add(0, original.get(i--));
    }
    
    return out;
  }
}
