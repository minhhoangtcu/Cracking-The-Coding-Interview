package epi.search;

/**
 * Design an algorithm for finding the position of the smallest element in a cyclically sorted
 * array. Assume all elements are distinct.
 * 
 * @author minhhoang
 *
 */
public class S123_CyclicallySorted {

  public static void main(String[] args) {
    System.out
        .println(findStartingIndex(new int[] {378, 478, 550, 631, 103, 203, 220, 234, 279, 368}));

    System.out
        .println(findEndingIndex(new int[] {378, 478, 550, 631, 368, 279, 234, 220, 203, 103}));

    System.out.println(findEndingIndex(new int[] {0, 1, 2, 3, -1}));
    
    System.out.println(findEndingIndex(new int[] {0, 1, 2, 3}));

    System.out.println(findEndingIndex(new int[] {3, 2, 1, 0}));
  }

  // assuming that the array is cyclically sorted with only distinct numbers
  public static int findStartingIndex(int[] arr) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] < arr[right]) {
        right = mid; // the index we are looking for, cannot be inside this range
      } else { // arr[mid] > arr[right]
        left = mid + 1; // +1 because if arr[mid] is the lowest, it cannot be greater than any
                        // number. Thus, we can exclude mid + 1
      } // cannot have equal because all numbers are distinct
    }

    return arr[left]; // when left == right, it has to be the minimum number
  }

  /*
   * Design an algorithm to find the max in A, for strictly ascending then descending sequence
   */
  public static int findEndingIndex(int[] arr) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
      int mid = getMiddle(left, right);

      // Check to see if we are in ascending or descending part
      // If ascending, we have to go to the right, else go to left
      if (arr[mid] < arr[mid + 1]) {
        left = mid + 1; // ascending
      } else { // arr[mid] > add[mid + 1], descending
        right = mid;
      }
    }

    return arr[left]; // when left -- right, it has to be the maximum number
  }

  private static int getMiddle(int a, int b) {
    return a + (b - a) / 2;
  }
}
