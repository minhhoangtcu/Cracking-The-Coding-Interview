package epi.search;

/**
 * Call a 2D array sorted if its rows and its columns are nondecreasing. Design an algorithm that
 * takes a 2D sorted array and a number and checks whether that number appears in the array.
 * 
 * @author minhhoang
 *
 */
public class S126_2DSorted {

  // assume non empty, non null array
  public boolean search(int[][] arr, int num) {
    /*
     * A characteristic of a 2D sorted array is that if we consider the top right most element of
     * the matrix, if the number we are looking for is smaller, then we can exclude that column
     * entirely. Else, if the number we are looking for is bigger, then we can exclude that row
     * entirely. 
     */
    
    int row = 0;
    int col = arr[0].length - 1;
    
    while (row < arr.length && col > 0) {
      int topRight = arr[row][col];
      
      if (num < topRight) {
        col--;
      } else if (num > topRight) {
        row++;
      } else {
        return true;
      }
    }

    return false;
  }
}
