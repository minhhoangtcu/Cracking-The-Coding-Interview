package epi.arrays;

import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Write a program that computes the max profit that can be made by buying and selling a share at
 * most twice. The second buy must be made after the first sale.
 * 
 * @author minhhoang
 *
 */
public class A67_StockTwice {
  public static void main(String[] args) {
    // For testing max profit
    // assert(getMaxProfit(new int[] {310, 315, 275, 295, 260, 270, 290, 230, 255, 250}, -1) == 30);
    // assert(getMaxProfit(new int[] {100, 100, 100}, -1) == 0);
    // assert(getMaxProfit(new int[] {0, 1}, -1) == 1);
    // assert(getMaxProfit(new int[] {0, 1, 2, 3, 4}, -1) == 4);
    // assert(getMaxProfit(new int[] {1, 0, 2, 0, 4}, -1) == 4);
    // assert(getMaxProfit(new int[] {0, 0, 0, 1}, -1) == 1);
    // assert(getMaxProfit(new int[] {1, 0}, -1) == 0);
    // assert(getMaxProfit(new int[] {1, 1, 1, 0}, -1) == 0);
    // assert(getMaxProfit(new int[] {1, 1, 1, 0, 1}, -1) == 1);
    // assert(getMaxProfit(new int[] {0}, -1) == 0);
    // assert(getMaxProfit(new int[] {1}, -1) == 0);
    // assert(getMaxProfit(null, -1) == 0);

    // For testing double max profit
    // assert(getDoubleMaxProfit(new int[] {0, 1, 0, 2}) == 4); // buy both at 0, sell both at 2
    // assert(getDoubleMaxProfit(new int[] {1, 1, 0, 2}) == 3);
    // assert(getDoubleMaxProfit(new int[] {0, 1}) == 1);
    // assert(getDoubleMaxProfit(new int[] {0, 1, 1, 1}) == 1);
    // assert(getDoubleMaxProfit(new int[] {4, 3, 2, 1}) == 0);
    // assert(getDoubleMaxProfit(new int[] {4, 3, 2, 1}) == 0);

    assert(getDoubleMaxProfitVariant(new int[] {12, 11, 13, 9, 12, 8, 14, 13, 15}) == 10);
  }
  
  public static int getDoubleMaxProfitVariant(int[] prices) {
    // Populate arrays keeping track of profit moving forward so far at ith.
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    int[] profitForward = new int[prices.length];
    for (int i = 0; i < prices.length; i++) {
      minPrice = Math.min(minPrice, prices[i]);
      maxProfit = Math.max(maxProfit, prices[i] - minPrice);
      profitForward[i] = maxProfit;
    }
//    System.out.println(Arrays.toString(profitForward));

    // Populate arrays keeping track of profit moving backward so far at ith.
    int maxPrice = Integer.MIN_VALUE;
    maxProfit = 0;
    int[] profitBackward = new int[prices.length + 1]; // last element as sentinel.
    for (int i = prices.length - 1; i >= 0; i--) {
      maxPrice = Math.max(maxPrice, prices[i]);
      maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
      profitBackward[i] = maxProfit;
    }
//    System.out.println(Arrays.toString(profitBackward));

    int result = 0;
    for (int i = 1; i <= prices.length; i++) {
      result = Math.max(result, profitForward[i - 1] + profitBackward[i]);
    }
    return result;
  }

  // Buying and selling twice means having 2 different buying price, but can
  // have either 1 selling or 2 selling price
  // We can treat this as 2 act of buying/selling stock like last problem,
  // but we exclude the index of last bought stock on the second run.
  public static int getDoubleMaxProfit(int[] prices) {
    int firstMaxIndex = getMaxProfitModified(prices, -1);
    // int secondMaxIndex = getMaxProfitModified(prices, firstMaxIndex);
    // System.out.println(firstMaxIndex + "\t" + secondMaxIndex);
    // System.out.println(getMaxProfit(prices, firstMaxIndex));
    return getMaxProfit(prices, -1) + getMaxProfit(prices, firstMaxIndex);
  }

  public static int getMaxProfit(int[] prices, int indexToExclude) {
    // corner cases
    if (prices == null || prices.length <= 1) {
      return 0;
    }

    int minSoFar = Integer.MAX_VALUE;
    int maxProfit = 0;

    // finding the max profit is the same as finding the different between
    // a min and a max. But, the min has to come before the max, and there
    // can be multiple maxes after a min
    for (int i = 0; i < prices.length; i++) {
      maxProfit = Math.max(prices[i] - minSoFar, maxProfit);
      if (i != indexToExclude)
        minSoFar = Math.min(prices[i], minSoFar);
    }

    return maxProfit;
  }

  // Return the index of the min buying price
  public static int getMaxProfitModified(int[] prices, int indexToExclude) {
    // corner cases
    if (prices == null || prices.length <= 1) {
      return 0;
    }

    int minSoFar = Integer.MAX_VALUE;
    int minSoFarIndex = 0;
    int maxProfit = 0;
    int maxProfitIndex = 0;

    // finding the max profit is the same as finding the different between
    // a min and a max. But, the min has to come before the max, and there
    // can be multiple maxes after a min
    for (int i = 0; i < prices.length; i++) {
      if ((prices[i] - minSoFar) > maxProfit) {
        maxProfit = prices[i] - minSoFar;
        // System.out.printf("Max: %d\tBuy at: %d (%d)\tSell at: %d (%d)\n",
        // maxProfit,
        // prices[minSoFarIndex], minSoFarIndex,
        // prices[i], i);

        maxProfitIndex = minSoFarIndex;
      }

      if (i != indexToExclude && prices[i] < minSoFar) {
        minSoFar = prices[i];
        minSoFarIndex = i;
      }
    }

    return maxProfitIndex;
  }
}
