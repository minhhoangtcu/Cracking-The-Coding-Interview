package epi.dynamicProgramming;

/**
 * You are climbing stairs. You can advance 1 to k steps at a time. Your destination is exactly n
 * steps up. Write a program which takes as inputs n and k and returns the number of ways in which
 * you can get to your destination. For example, if n = 4 and k = 2, there are five ways in which to
 * go to the destination:
 * 
 * - four single stair advances
 * - two single stair advances followed by a double stair advance
 * ...
 * 
 * @author minhhoang
 *
 */
public class DD01_VariantClimbStairs {
  public static void main(String[] args) {
    System.out.println(getNumberOfCombination(4, 2));
  }
  
  public static int getNumberOfCombination(int height, int reach) {
    // numCombinations[reach][height] stores the number of ways to get to height using up to reach
    // steps. For example, numCombinations[1][5] is the number of ways to get to height=5 with 1
    // step at a time, numCombinations[2][8] is the number of ways to get height=8 with by either
    // step once or twice at a time.
    int[][] numCombinations = new int[reach + 1][height + 1];
    for (int step = 0; step <= reach; step++) {
      numCombinations[step][0] = 1;
    }
    
    for (int step = 1; step <= reach; step++) {
      for (int h = 1; h <= height; h++) {
        numCombinations[step][h] = numCombinations[step - 1][h] + ((h - step >= 0) ? numCombinations[step][h - step] : 0); 
      }
    }
    printMatrix(numCombinations);
    
    return numCombinations[reach][height];
  }
  
  private static void printMatrix(int[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      System.out.print("step=" + row + ": ");
      for (int col = 0; col < matrix[row].length; col++) {
        System.out.print(matrix[row][col] + " ");
      }
      System.out.println();
    }
  }
}
