package ctcInterview.sortingAndSearching;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of
 * times, write code to find an element in the array. You must assume that the
 * array was originally sorted in increasing order.
 * 
 * EXAMPLE:
 * 
 * Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * 
 * Output: 8 (the index of 5 in the array)
 * 
 * @author minhhoang
 *
 */
public class SS1103_RotateSearch {
	
	public static void main(String[] args) {
		
		SS1103_RotateSearch rs = new SS1103_RotateSearch();
		
		int[] rotatedInts = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		
		System.out.println(rs.search(rotatedInts, 5));
		
	}

	/**
	 * We can linearly search for the point of rotation (from 25 -> 1. So index
	 * = 5 is the start of the array). Then, we can double the array, which will
	 * be {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14, 15, 16, 19, 20, 25, ...}
	 * making 5 the start of the array and last index of old array (12-1) + 5
	 * will be the end of the array. We can then do a binary search on this
	 * range to find the provided number. This algorithm will take O(n)
	 * performance for worst case and 2*n for memory
	 */

	/**
	 * Alternatively, if memory is limited, we can do a normal binary search.
	 * Take these two arrays:
	 * 
	 * A: {5, 6, 1, 2, 3}, B: {5, 5, 5, 1, 4}, and C: {5, 8, 5, 5, 5}
	 * 
	 * Performance a binary search, for A, we get 1 in the middle. Comparing 1
	 * to the leftmost side, which is 5. Because 5 > 1, the point of rotation
	 * occurs on the left side. And, the right side should be normal
	 * (increasing). If we are looking for 2 and the max of right is 3, then it
	 * has to be on the right side. For 5, and the max of right is 3, then it
	 * has to be on the left side. For 0, it has to be on the left side.
	 * 
	 * A case where the left most element is a duplicate of the middle element
	 * will be much more complicated. For B, if mid = left -> the left side can
	 * either be all 5, or {5, 1, 5} (so it may have point of rotation or not).
	 * However, if we look at the right most element, which is 4. Because 5 > 4,
	 * we know for sure that the point of rotation must be on the right, thus,
	 * the left has all 5. But, if both left = mid = right like in case C. We
	 * must recursively search for both side.
	 * 
	 * This algorithm will take O(logn) on average or O(n) in worst case or O(1)
	 * for memory (no addition storage).
	 */
	
	public int search(int[] rotatedInts, int num) {
		return searchHelper(rotatedInts, num, 0, rotatedInts.length-1);
	}
	
	private int searchHelper(int[] rotatedInts, int num, int left, int right) {
		
		if (left > right) {
			return -1; // left right crossed, found nothing
		}
		
		int mid = (left + right)/2;
		
		if (rotatedInts[mid] == num)
			return mid;
		
		if (rotatedInts[left] > rotatedInts[mid]) {
			// point of rotation must be on the left. So right is ascending
			if (rotatedInts[mid] < num && num < rotatedInts[right])
				// num is within the range of the right side
				return searchHelper(rotatedInts, num, mid+1, right);
			else
				return searchHelper(rotatedInts, num, left, mid-1);
		}
		else if (rotatedInts[left] < rotatedInts[mid]) {
			// left is ascending
			if (rotatedInts[mid] > num && num > rotatedInts[right])
				// num is within the range of the left side
				return searchHelper(rotatedInts, num, left, mid-1);
			else
				return searchHelper(rotatedInts, num, mid+1, right);
		}
		else {
			// We have duplicate left and mid
			if (rotatedInts[right] != rotatedInts[mid])
				// right is ascending. All elements from left -> mid must all be the same! 
				return searchHelper(rotatedInts, num, mid+1, right);
			else
				// left = mid = right -> we must explore both left and right
				return Math.max(searchHelper(rotatedInts, num, left, mid-1), searchHelper(rotatedInts, num, mid+1, right));
				
		}
	}
}
