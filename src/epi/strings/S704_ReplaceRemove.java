package epi.strings;

import java.util.Arrays;

/**
 * Write a program which takes as input an array of characters, and removes each 'b' and replaces
 * each 'a' by two 'd's. Specifically, along with the array, you are provided an integer-valued
 * size. Size denotes the number of entries of the array that the operation is to be applied to. You
 * do not have to worry preserving about the subsequent entries.
 * 
 * @author minhhoang
 *
 */
public class S704_ReplaceRemove {
  
  public static void main(String[] args) {
    printResult(new char[] {'a', 'b'}, 2);
    printResult(new char[] {'a', 'a', 'x', 'x'}, 2);
    printResult(new char[] {'b', 'b', 'x', 'x'}, 2);
    printResult(new char[] {'a', 'x', 'x', 'x'}, 1);
    printResult(new char[] {'b', 'a', 'e', 'x'}, 3);
  }
  
  private static void printResult(char[] in, int realSize) {
    System.out.println(Arrays.toString(in));
    transform(in, realSize);
    System.out.println(Arrays.toString(in));
  }
  
  public static void transform(char[] arr, int realSize) {
    // Remove all b, and also count the number of 'a' to get the output size. The 
    // output size = builder + countOfA.
    int countOfA = 0;
    int builder = 0;
    for (int scanner = 0; scanner < realSize; scanner++) {
      if (arr[scanner] == 'a') {
        countOfA++;
      }
      if (arr[scanner] != 'b') {
        arr[builder] = arr[scanner];
        builder++;
      }
    }
    
    int scanner = builder - 1;
    builder += countOfA - 1;
    
    // Rebuild array from end to beginning.
    while (scanner >= 0) {
      if (arr[scanner] == 'a') {
        arr[builder] = 'a';
        builder--;
        arr[builder] = 'a';
      } else {
        arr[builder] = arr[scanner];
        builder--;
      }
      scanner--;
    }
  }
}
