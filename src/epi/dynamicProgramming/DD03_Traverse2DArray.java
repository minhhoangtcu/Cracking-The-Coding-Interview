package epi.dynamicProgramming;

/**
 * Write a program that counts how many ways you can go from the top-left to the bottom-right in a
 * 2D array.
 * 
 * @author minhhoang
 *
 */
public class DD03_Traverse2DArray {
  public static void main(String[] args) {
    System.out.println(getNumberOfWays(1, 1) == 1);
    System.out.println(getNumberOfWays(2, 2) == 2);
    System.out.println(getNumberOfWays(2, 1) == 1);
    System.out.println(getNumberOfWays(1, 2) == 1);
    System.out.println(getNumberOfWays(5, 5) == 70);
  }
  
  public static int getNumberOfWays(int rows, int cols) {
    return computeNumWays(new int[rows][cols], rows-1, cols-1);
  }
  
  public static int computeNumWays(int[][] numWays, int row, int col) {
    if (row == 0 || col == 0) {
      return 1;
    }
    
    numWays[row][col] = computeNumWays(numWays, row-1, col) + computeNumWays(numWays, row, col-1);
    return numWays[row][col];
  }
}
