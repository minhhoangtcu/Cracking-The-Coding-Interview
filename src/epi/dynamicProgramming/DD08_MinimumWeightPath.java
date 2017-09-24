package epi.dynamicProgramming;

import java.util.Arrays;

/**
 * Write a program that takes as input a triangle of numbers and returns the weight of a minimum
 * weight path.
 * 
 * @author minhhoang
 *
 */
public class DD08_MinimumWeightPath {
  
  
  public static void main(String[] args) {
    System.out.println(getMinimumWeight(
        new int[][] {
          {2},
          {4, 4},
          {8, 5, 6},
          {4, 2, 6, 2},
          {1, 5, 2, 3, 4},
    }));
  }
  
  public static int getMinimumWeight(int[][] triangle) {
    int depth = triangle.length;
    int[] minimumWeight = new int[depth];
    minimumWeight[0] = triangle[0][0];
    
    for (int row = 1; row < depth; row++) {
      System.out.println(Arrays.toString(minimumWeight));
      int[] temp = new int[depth];
      for (int col = 0; col < triangle[row].length; col++) {
        int minFromTopLeft = col == 0 ? Integer.MAX_VALUE : minimumWeight[col - 1];
        int minFromTopRight = col >= triangle[row].length - 1 ? Integer.MAX_VALUE : minimumWeight[col];
        temp[col] = Math.min(minFromTopLeft, minFromTopRight) + triangle[row][col];
      }
      minimumWeight = temp;
    }
    
    int minWeight = Integer.MAX_VALUE;
    for (int num: minimumWeight) {
      minWeight = Math.min(minWeight, num);
    }
    return minWeight;
  }
}
