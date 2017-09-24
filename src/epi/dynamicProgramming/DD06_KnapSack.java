package epi.dynamicProgramming;

/**
 * Write a program for the snapsack problem that selects a subset of items that has maximum value
 * and satisfies the weight constraint. All items have integer weights and values. Return the value
 * of the subset.
 * 
 * @author minhhoang
 *
 */
public class DD06_KnapSack {
  public static void main(String[] args) {
    int[] values = {65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
    int[] weights = {20, 8, 60, 55, 40, 70, 85, 25, 30, 65, 75, 10, 95, 50, 40, 10};
    System.out.println(maxValue(values, weights, 130));
  }
  
  public static int maxValue(int[] values, int[] weights, int maxWeight) {
    return maxValue(values, weights, 0, 0, 0, maxWeight);
  }
  
  private static int maxValue(int[] values, int[] weights, 
      int itemIdx, int currentValue, int currentWeight, int maxWeight) {
    if (itemIdx == values.length) {
      return currentValue; // No more item to select.
    }
    
    int excludeItem = maxValue(values, weights, itemIdx+1, currentValue, currentWeight, maxWeight);
    int includeItem = 0;
    if (currentWeight+weights[itemIdx] <= maxWeight) {
      includeItem = maxValue(values, weights, itemIdx+1, currentValue+values[itemIdx], currentWeight+weights[itemIdx], maxWeight);
    }
    return Math.max(excludeItem, includeItem);
  }
}
