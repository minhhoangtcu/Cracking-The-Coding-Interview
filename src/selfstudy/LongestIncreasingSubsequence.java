package selfstudy;

import java.util.Arrays;

/**
 * 
 * LeetCode: https://leetcode.com/problems/longest-increasing-subsequence/discuss/
 * 
 * @author minhhoang
 *
 */
public class LongestIncreasingSubsequence {
  
  public static void main(String[] args) {
    testBinarySearch();
  }
  
  private static void testBinarySearch() {
    int[] nums0 = {0, 1, 2};
    System.out.println(getInsertionPoint(nums0, 0, 3, 2));
    System.out.println(getInsertionPoint(nums0, 0, 3, 3));
    System.out.println(getInsertionPoint(nums0, 0, 3, 4));
    System.out.println(getInsertionPoint(nums0, 0, 3, -1));
    System.out.println(getInsertionPoint(nums0, 0, 3, 0));
    System.out.println(getInsertionPoint(nums0, 0, 3, 1));
    System.out.println();
    
    int[] nums1 = {0, 3, 5, 0, 0};
    System.out.println(getInsertionPoint(nums1, 0, 3, -1));
    System.out.println(getInsertionPoint(nums1, 0, 3, 0));
    System.out.println(getInsertionPoint(nums1, 0, 3, 1));
    System.out.println(getInsertionPoint(nums1, 0, 3, 4));
    System.out.println(getInsertionPoint(nums1, 0, 3, 6));
    System.out.println();
    
    int[] nums2 = {2, 5, 0, 0, 0, 0};
    System.out.println(getInsertionPoint(nums2, 0, 2, 3));
    System.out.println(getInsertionPoint(nums2, 0, 2, 4));
  }
  
  private static int getInsertionPoint(int[] nums, int l, int h, int key) {
    int i = Arrays.binarySearch(nums, l, h, key);
    return i < 0 ? -(i + 1) : i;
  }
}
