package hackerrank.contest.worldCodeSpirit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationHappiness {
  
  public static void main(String[] args) {
    for (int h = 1; h < 8; h++) {
      int last = 1;
      for (int n = 1; n < 11; n++) {
        int result = query(n, h);
        System.out.print(result / last + " ");
        last = result != 0 ? result : 1;
      }
      System.out.println();
    }
  }
  
  static int queryFast(int n, int k) {
    long[][] numOfPermutations = new long[n + 1][k + 1];
    
    return 0;
  }
  
  private static long numberOfHappyPermute;
  
  static int query(int n, int k){
      if (n == 1 || k >= n) {
        // cannot be happy standing alone
        // cannot have more happy people than the number of people in line
        return 0;
      } else if (k == 1) {
        return (int) getNumberOfPermutation(n); // all permutations are happy
      }
    
      numberOfHappyPermute = 0;
      
      List<Integer> A = new ArrayList<>(n + 1);
      for (int i = 1; i <= n; i++) {
          A.add(i);
      }
      
      permute(0, k, A, 0);
      return (int) (numberOfHappyPermute % (Math.pow(10, 9) + 7));
  }
  
  // currentNumberOfHappy denotes the number of happy people in the range [0, i - 1).
  // It does not care what i is, because only i - 1 is dependent on i. Since, i - 1 will
  // not change, currentNumberOfHappy will never be violated when i change
  private static void permute(int i, int k, List<Integer> A, int currentNumberOfHappy) {
      if (i == A.size() - 1) {
          // We need to check the two last, because the our currentNumberOfHappy does not account for
          // i - 1 and i, only [0, i - 1)
          currentNumberOfHappy += isHappy(A, i - 1) ? 1 : 0;
          currentNumberOfHappy += isHappy(A, i) ? 1 : 0;
          
          numberOfHappyPermute += currentNumberOfHappy >= k ? 1 : 0;
          return;
      }
      
      for (int j = i; j < A.size(); j++) {
          Collections.swap(A, i, j);
          
          int tempHappy = currentNumberOfHappy;
          tempHappy += isHappy(A, i - 1) ? 1 : 0; // is i - 1 happy with the swap?
          
          if (tempHappy >= k) {
              // We have enough happy people from [0, i), consider if we do not change i,
              // then whatever we have after i does not matter, the permutation has to
              // be valid. So, we just need to add numberOfHappyPermute with the number
              // of permutations after i (size length - (i + 1)).
              numberOfHappyPermute += getNumberOfPermutation(A.size() - i - 1);
          } else {
              permute(i + 1, k, A, tempHappy);
          }
          
          Collections.swap(A, i, j);
      }
  }
  
  private static boolean isHappy(List<Integer> A, int personIndex) {
      if (personIndex < 0) {
          return false;
      }
      
      if (personIndex == 0) {
          return A.get(personIndex + 1) > A.get(personIndex);
      } else if (personIndex == A.size() - 1) {
          return A.get(personIndex - 1) > A.get(personIndex);
      } else {
          return A.get(personIndex - 1) > A.get(personIndex) || 
              A.get(personIndex + 1) > A.get(personIndex);
      }
  }
  
  private static Map<Integer, Long> factorial = new HashMap<>();
  
  private static long getNumberOfPermutation(int size) {
      long result = 1;
      
      for (int i = size; i > 0; i--) {
          if (factorial.containsKey(i)) {
              result *= factorial.get(i);
              break;
          }
          
          result *= i;
      }
      
      factorial.put(size, result);
      return result;
  }

}
