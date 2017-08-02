package epi.arrays;

/**
 * Write a function that takes as input an n x n 2D array, and rotates the array by 90 degrees
 * clockwise.
 * 
 * @author minhhoang
 *
 */
public class A618_Rotate {
  
  public static void main(String[] args) {
    int[][] matrix = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
    printMatrix(matrix);
    rotateOuter(matrix);
    printMatrix(matrix);
  }
  
  private static void printMatrix(int[][] matrix) {
    for (int[] row: matrix) {
      for (int n: row) {
        System.out.format("%2d ", n);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void rotateOuter(int[][] matrix) {
    // ith col of the rotated matrix is the ith row of the original matrix.
    for (int row = 0; row < (matrix.length / 2); row++) {
      for (int col = row; col < (matrix.length - row - 1); col++) {
        int temp1 = matrix[matrix.length - col - 1][row];
        int temp2 = matrix[row][col];
        int temp3 = matrix[col][matrix.length - row - 1];
        int temp4 = matrix[matrix.length - row - 1][matrix.length - col - 1];
        matrix[row][col] = temp1;
        matrix[row][matrix.length - row - 1] = temp2;
        matrix[matrix.length - row - 1][matrix.length - row - 1] = temp3;
        matrix[matrix.length - row - 1][col] = temp4;
      }
    }
  }
}
