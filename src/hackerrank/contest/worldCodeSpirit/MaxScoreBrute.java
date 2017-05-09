package hackerrank.contest.worldCodeSpirit;

import java.util.*;
import java.util.stream.Collectors;

public class MaxScoreBrute {

  public static void main(String[] args) {
    // System.out.println(getMaxScore(new long[] {1, 2}));
    // System.out.println(getMaxScore(new long[] {1, 3, 5, 5}));


    System.out.println(getMaxScore(new long[] {1, 2, 3}));
    System.out.println(getMaxScoreModified(new long[] {1, 2, 3}));

    System.out.println(getMaxScore(new long[] {1, 2, 3, 4}));
    System.out.println(getMaxScoreModified(new long[] {1, 2, 3, 4}));

    System.out.println(getMaxScore(new long[] {3, 8, 5}));
    System.out.println(getMaxScoreModified(new long[] {3, 8, 5}));


    System.out.println(getMaxScore(new long[] {1, 2, 3, 7, 10}));
    System.out.println(getMaxScoreModified(new long[] {1, 2, 3, 7, 10}));
    
    System.out.println(getMaxScore(new long[] {7, 15, 50}));
    System.out.println(getMaxScoreModified(new long[] {7, 15, 50}));
    
    System.out.println(getMaxScore(new long[] {1, 2, 3, 4, 5, 6}));
    System.out.println(getMaxScoreModified(new long[] {1, 2, 3, 4, 5, 6}));
  }

  private static long getMaxScore(long[] a) {
    return getScore(0, 0, Arrays.stream(a).boxed().collect(Collectors.toList()));
  }

  private static long getScore(long score, long runningSum, List<Long> remainingNumbersToConsider) {
    if (remainingNumbersToConsider.size() == 1) {
      return score + (runningSum % remainingNumbersToConsider.get(0));
    }

    long localMax = score;

    for (int i = 0; i < remainingNumbersToConsider.size(); i++) {
      long considering = remainingNumbersToConsider.remove(i);

      long newScore = score + (runningSum % considering);
      long newSum = runningSum + considering;
      localMax = Math.max(localMax, getScore(newScore, newSum, remainingNumbersToConsider));

      remainingNumbersToConsider.add(i, considering);
    }

    return localMax;
  }

  private static long getMaxScoreModified(long[] a) {
    return getScoreModified(0, 0, Arrays.stream(a).boxed().collect(Collectors.toList()));
  }

  private static long getScoreModified(long score, long runningSum,
      List<Long> remainingNumbersToConsider) {
    if (remainingNumbersToConsider.size() == 1) {
      return runningSum % remainingNumbersToConsider.get(0);
    }

    long localMax = Integer.MIN_VALUE;

    for (int i = 0; i < remainingNumbersToConsider.size(); i++) {
      long considering = remainingNumbersToConsider.remove(i);

      long newScore = runningSum % considering;
      long newSum = runningSum + considering;
      localMax =
          Math.max(localMax, newScore + getScore(newScore, newSum, remainingNumbersToConsider));

      remainingNumbersToConsider.add(i, considering);
    }

    return score + localMax;
  }
}
