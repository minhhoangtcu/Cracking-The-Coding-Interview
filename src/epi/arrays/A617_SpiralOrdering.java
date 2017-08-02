package epi.arrays;

import java.util.Arrays;

/**
 * Compute the spiral order for an m x n 2D array A.
 * 
 * @author minhhoang
 *
 */
public class A617_SpiralOrdering {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(getNumbersInSpiralOrdering(
        new int[][] {{1,2,3},
                     {8,9,4},
                     {7,6,5}})));
    
    System.out.println(Arrays.toString(getNumbersInSpiralOrdering(
        new int[][] {{1,2,3},
                     {6,5,4}})));
    
    System.out.println(Arrays.toString(getNumbersInSpiralOrdering(
        new int[][] {{1,2},
                     {6,3},
                     {5,4}})));
    
    System.out.println(Arrays.toString(getNumbersInSpiralOrdering(
        new int[][] {{1,2},
                     {4,3}})));
  }
  
  public static int[] getNumbersInSpiralOrdering(int[][] matrix) {
    int numRows = matrix.length;
    int numCols = numRows > 0 ? matrix[0].length : 0; 
    int[] out = new int[numRows*numCols];
    int[][] directionVectors = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] visitedMatrix = new boolean[numRows][numCols];
    int currentDirection = 0;
    int nextRow = 0;
    int nextCol = 0;
    
    for (int i = 0; i < numRows*numCols; i++) {
      visitedMatrix[nextRow][nextCol] = true;
      out[i] = matrix[nextRow][nextCol];
      
      // If reaches boundary or a position that has been visited before, change direction.
      int nextRowTrial = nextRow + directionVectors[currentDirection][0];
      int nextColTrial = nextCol + directionVectors[currentDirection][1];
      if (nextRowTrial >= numRows || nextRowTrial < 0 || nextColTrial >= numCols || nextColTrial < 0
          || visitedMatrix[nextRowTrial][nextColTrial]) {
        currentDirection = (currentDirection + 1) % 4;
      }
      
      nextRow += directionVectors[currentDirection][0];
      nextCol += directionVectors[currentDirection][1];
    }
    
    return out;
  }
}
