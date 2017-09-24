package epi.dynamicProgramming;

/**
 * Design an efficient algorithm for computing (n, k) which has the property that it never overflows
 * if the final result fits in the integer word size.
 * 
 * @author minhhoang
 *
 */
public class DD04_BinomialCoefficients {
  public static void main(String[] args) {
    System.out.println(binomialCoefficients(2, 1) == 2);
    System.out.println(binomialCoefficients(3, 2) == 3);
    System.out.println(binomialCoefficients(5, 2) == 10);
  }
  
  public static int binomialCoefficients(int n, int k) {
    return binomialCoefficients(new int[n+1][k+1], n, k);
  }
  
  /*
   * The result for dp[n][k], number of ways to choose k elements from n elements, can be computed
   * like following. Considering that we are choosing the kth element, and we are at ith element.
   * We can choose the ith element as the kth element, or not choose it and consider the ith+1
   * instead. Thus, dp[n][k] = dp[n - 1][k - 1] + dp[n - 1][k].
   * 
   * k = 0 1 2 3 4 5
   * 0   1 0 0 0 0 0
   * 1   1 1 0 0 0 0
   * 2   1 2 1 0 0 0
   * 3   1 3 3 1 0 0
   * 4   1 4
   * 5   1 5
   * 
   */
  private static int binomialCoefficients(int[][] dp, int n, int k) {
    if (n == k || k == 0) {
      return 1;
    }
    
    dp[n][k] = binomialCoefficients(dp, n-1, k-1) + binomialCoefficients(dp, n-1, k);
    return dp[n][k];
  }
}
