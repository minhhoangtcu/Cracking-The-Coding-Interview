package epi.arrays;

import java.util.Arrays;

/**
 * Permute
 * 
 * @author minhhoang
 *
 */
public class A69_PermuteElements {
  
  public static void main(String[] args) {
    
    test(new int[] {1, 5, 2, 6}, 
        new int[] {1, 2, 0, 3}, 
        new int[] {2, 1, 5, 6});
    
    test(new int[] {1, 0}, 
        new int[] {1, 0}, 
        new int[] {0, 1});
    
    test(new int[] {1, 0}, 
        new int[] {0, 1}, 
        new int[] {1, 0});
    
    test(new int[] {1}, 
        new int[] {0}, 
        new int[] {1});
    
    test(new int[] {1, 2, 3, 4}, 
        new int[] {3, 2, 1, 0}, 
        new int[] {4, 3, 2, 1});
    
    test(new int[] {1, 0, 2, 3}, 
        new int[] {1, 0, 3, 2}, 
        new int[] {0, 1, 3, 2});
    
  }
  
  private static void test(int[] original, int[] permutations, int[] expected) {
    applyPermutation(original, permutations);
    assert(Arrays.equals(Arrays.stream(original).map(Math::abs).toArray(), expected));
  }

  /*
   * To find and apply the cycle that includes entry i we just keep going forward (from i to P[i])
   * till we get back to i. After we are done with that cycle, we need to find another cycle that
   * has not yet been applied. It is trivial to do this by storing a Boolean for each array element.
   * 
   * Example:
   * 
   * o: [a, b, c, d]
   * p: [1, 2, 0, 3]
   * n: [c, a, b, d]
   * 
   */
  public static void applyPermutation(int[] original, int[] permutations) {
    int startOfCycle = 0;

    while (startOfCycle < original.length) {
      if (original[startOfCycle] < 0) {
        startOfCycle++;
        continue; // already in correct place
      }
      
      int destination = permutations[startOfCycle];
      
      do {
        swap(original, startOfCycle, destination);
        original[destination] = -original[destination]; // mark as in correct place
        
        destination = permutations[destination];
      } while (destination!= startOfCycle); // keep going forward till a final swap
      
      startOfCycle++;
    }
  }
  
  private static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
}
