package epi.arrays;

/**
 * Write a program that takes an array denoting the daily stock price, and
 * returns the maximum profit that could be made by buying and then selling one
 * share of that stack.
 * 
 * @author minhhoang
 *
 */
public class A66_Stock {
	
	public static void main(String[] args) {
		
		assert(getMaxProfit(new int[] {310, 315, 275, 295, 260, 270, 290, 230, 255, 250}) == 30);
		assert(getMaxProfit(new int[] {100, 100, 100}) == 0);
		assert(getMaxProfit(new int[] {0, 1}) == 1);
		assert(getMaxProfit(new int[] {0, 1, 2, 3, 4}) == 4);
		assert(getMaxProfit(new int[] {1, 0, 2, 0, 4}) == 4);
		assert(getMaxProfit(new int[] {0, 0, 0, 1}) == 1);
		assert(getMaxProfit(new int[] {1, 0}) == 0);
		assert(getMaxProfit(new int[] {1, 1, 1, 0}) == 0);
		assert(getMaxProfit(new int[] {1, 1, 1, 0, 1}) == 1);
		assert(getMaxProfit(new int[] {0}) == 0);
		assert(getMaxProfit(new int[] {1}) == 0);
		assert(getMaxProfit(null) == 0);
		
	}
	
	public static int getMaxProfit(int[] stockPrices) {
		
		if (stockPrices == null || stockPrices.length <= 1) {
			return 0;
		}
		
		// Max profit = highest relative max - lowest relative min
		// We keep track of the min as soon as we get into an increasing section
		
		int i = skipequal(stockPrices, 0);
		if (i == -1) return 0; // all prices are equal
		
		int minPrice = stockPrices[i - 1]; // i is always >= 1
		int maxProfit = 0;
		boolean isIncreasing = (stockPrices[i] - stockPrices[i - 1]) > 0;
		
		while (i < stockPrices.length) {
			
			int compare = stockPrices[i] - stockPrices[i - 1];
			
			if (compare > 0 && !isIncreasing) {
				
				minPrice = stockPrices[i - 1]; // i - 1 is relative min
				isIncreasing = true;
				
			} else if (compare < 0 && isIncreasing) {
				
				int profit = stockPrices[i - 1] - minPrice; // i - 1 is relative max
				maxProfit = Math.max(profit, maxProfit);
				isIncreasing = false;
				
			} // ignore equal
			
			i++;
		}
		
		if (isIncreasing) maxProfit = Math.max(stockPrices[i - 1] - minPrice, maxProfit); 
		
		return maxProfit;
		
	}
	
	// Returns the index of the number that is not equal to the number with 
	// provided index or -1 if we cannot find it
	public static int skipequal(int[] stockPrices, int index) {
		
		int firstNum = stockPrices[index];
		
		while (index < stockPrices.length && stockPrices[index] == firstNum) {
			index++;
		}
		
		return index == stockPrices.length ? -1 : index;
	}

}
