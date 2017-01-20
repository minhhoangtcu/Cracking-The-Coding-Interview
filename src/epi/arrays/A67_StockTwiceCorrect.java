package epi.arrays;

import java.util.Arrays;

/**
 * Same problem as stated in A67, but this is more correct:
 * 
 * Buy -> Sell -> Buy -> Sell
 * 
 * @author minhhoang
 *
 */
public class A67_StockTwiceCorrect {
	
	public static void main(String[] args) {
		
		assert(getMaxProfit(new int[] {12, 11, 13, 9, 12, 8, 14, 13, 15}) == 10);
		assert(getMaxProfit(new int[] {1}) == 0);
		assert(getMaxProfit(new int[] {1, 2}) == 1);
		assert(getMaxProfit(new int[] {1, 2, 2}) == 1);
		assert(getMaxProfit(new int[] {1, 2, 2, 3}) == 2);
		assert(getMaxProfit(new int[] {1, 2, 3, 4}) == 3);
		assert(getMaxProfit(new int[] {0, 10, 5, 10}) == 15);
		assert(getMaxProfit(new int[] {0, 10, 5, 8, 2, 3, 6}) == 14);
		
		// Passed all test cases on LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
		
	}
	
	/*
	 * This problem becomes finding the Sell -> Buy point to maximize the profit
	 * of buying and selling a stock on each side
	 */
	public static int getMaxProfit(int[] stockPrices) {
		
		if (stockPrices == null || stockPrices.length < 2) {
			return 0; // To sell then buy another stock, we need at least 2 days
		}
		
		int numOfDays = stockPrices.length;
		
		// Get max profit from [0:j] by buying/selling only 1 stock
		int[] maxProfitFromLeft = new int[numOfDays];
		int minSoFar = Integer.MAX_VALUE;
		int maxProfitleft = 0;
		
		for (int i = 0; i < numOfDays; i++) {
			minSoFar = Math.min(minSoFar, stockPrices[i]);
			maxProfitleft = Math.max(maxProfitleft, stockPrices[i] - minSoFar);
			maxProfitFromLeft[i] = maxProfitleft;
		}
		
		// Get max profit from [j:n-1] by buying/selling only 1 stock
		int[] maxProfitFromRight = new int[numOfDays];
		int maxSoFar = Integer.MIN_VALUE;
		int maxProfitRight = 0;
		
		for (int i = numOfDays - 1; i >= 0; i--) {
			maxSoFar = Math.max(maxSoFar, stockPrices[i]);
			maxProfitRight = Math.max(maxProfitRight, maxSoFar - stockPrices[i]);
			maxProfitFromRight[i] = maxProfitRight;
		}
		
		// Max profit is in the jth day where we sell the first stock on j-1th 
		// day and buy the second one on jth day
		int maxProfit = 0;
		
		for (int i = 0; i <= numOfDays; i++) {
			
			int firstStock = i != 0 ? maxProfitFromLeft[i - 1] : 0;
			int secondStock = i != numOfDays ? maxProfitFromRight[i] : 0;
			int currentProfit = firstStock + secondStock;
			
			maxProfit = Math.max(maxProfit, currentProfit);
		}
		
		System.out.println(Arrays.toString(maxProfitFromLeft));
		System.out.println(Arrays.toString(maxProfitFromRight));
		System.out.println();
		
		return maxProfit;
		
	}

}
