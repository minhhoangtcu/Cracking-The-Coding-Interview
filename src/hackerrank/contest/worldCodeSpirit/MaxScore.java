package hackerrank.contest.worldCodeSpirit;

import java.util.HashMap;
import java.util.Map;

public class MaxScore {

  public static void main(String[] args) {
     System.out.println(getMaxScore(new long[] {1, 2}));
     System.out.println(getMaxScore(new long[] {1, 2, 3}));
     System.out.println(getMaxScore(new long[] {1, 2, 3, 4}));
     System.out.println(getMaxScore(new long[] {3, 8, 5}));
     System.out.println(getMaxScore(new long[] {1, 2, 3, 7, 10}));
     System.out.println(getMaxScore(new long[] {7, 15, 50}));
     System.out.println(getMaxScore(new long[] {1, 2, 3, 4, 5, 6}));
     System.out.println(getMaxScore(new long[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
     16, 17, 18, 19}));
  }

  // key is bitmask represents the remaining numbers to consider, and value represents the max score
  // that can be obtained
  private static Map<Integer, Long> dp;
  private static long[] A;

  private static long getMaxScore(long[] a) {
    A = a;
    dp = new HashMap<>();
    int n = a.length;
    int remainingNumbersToConsider = (1 << n) - 1;

    return getScore(0, 0, a.length, remainingNumbersToConsider);
  }

  private static long getScore(long score, long runningSum, int n, int remainingNumbersToConsider) {
    if (count(n, remainingNumbersToConsider) == 1) {
      return runningSum % getFirst(n, remainingNumbersToConsider);
    }

    // Check in dp
    if (dp.containsKey(remainingNumbersToConsider)) {
      return dp.get(remainingNumbersToConsider);
    }

    // Find max
    long localMax = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      if (isSet(i, remainingNumbersToConsider)) {
        long considering = A[i];
        remainingNumbersToConsider = clearIth(i, remainingNumbersToConsider);

        long newScore = runningSum % considering;
        long newSum = runningSum + considering;
        localMax = Math.max(localMax, newScore + getScore(newScore, newSum, n, remainingNumbersToConsider));

        remainingNumbersToConsider = setIth(i, remainingNumbersToConsider);
      }
    }

    // Store in dp
    dp.put(remainingNumbersToConsider, localMax);

    return localMax;
  }

  private static int setIth(int i, int remainingNumbersToConsider) {
    int mask = 1 << i;
    return mask | remainingNumbersToConsider;
  }

  private static int clearIth(int i, int remainingNumbersToConsider) {
    int mask = 1 << i;
    mask = ~mask; // inverse
    return mask & remainingNumbersToConsider;
  }

  private static long getFirst(int n, int remainingNumbersToConsider) {
    for (int i = 0; i < n; i++) {
      if (isSet(i, remainingNumbersToConsider)) {
        return A[i];
      }
    }

    return -1; // nothing
  }

  private static int count(int n, int remainingNumbersToConsider) {
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (isSet(i, remainingNumbersToConsider)) {
        count++;
      }
    }

    return count;
  }

  private static boolean isSet(int i, int remainingNumbersToConsider) {
    return ((remainingNumbersToConsider >> i) & 1) == 1;
  }
}
