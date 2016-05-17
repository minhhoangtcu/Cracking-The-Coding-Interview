package ctcInterview.moderate;

import java.util.Arrays;

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence).
 * 
 * EXAMPLE
 * 
 * Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
 * 
 * Output: (3, 9)
 * 
 * @author minhhoang
 *
 */
public class M1706_GetSmallestToSort {
	
	public static void main(String[] args) {
		
		M1706_GetSmallestToSort gsts = new M1706_GetSmallestToSort();
		
		int[] allDistinct = {1, 2, 5, 4, 3, 7, 9, 8, 10, 11};
		System.out.println(gsts.getSmallestToSort(allDistinct));
		
		int[] containDuplicates = {1, 2, 5, 4, 8, 9, 8, 9, 5, 10, 11};
		System.out.println(gsts.getSmallestToSort(containDuplicates));
		
		int[] containDuplicates2 = {1, 2, 8, 5, 9, 9, 10, 11};
		System.out.println(gsts.getSmallestToSort(containDuplicates2));
		
	}

	/**
	 * Get indices m and n such that if you sorted elements m through n, the
	 * entire array would be sorted.
	 * 
	 * @return
	 */
	public Result getSmallestToSort(int[] input) {
		
		int[] sorted = input.clone();
		Arrays.sort(sorted);
		int n = -1, m = -1;
		
		// Go left-right to find n
		for (int i = 0; i < input.length; i++) {
			if (input[i] != sorted[i]) {
				n = i;
				break;
			}
		}
		
		// Go right-left to find m
		for (int i = input.length-1; i >= 0; i--) {
			if (input[i] != sorted[i]) {
				m = i;
				break;
			}
		}
		
		if (n != -1 && m != -1)
			return new Result(n, m);
		else
			throw new IllegalArgumentException("Already sorted!");
	}

	class Result {
		int n, m;

		public Result(int n, int m) {
			this.n = n;
			this.m = m;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("n: %d\tm: %d", n, m);
		}
	}

}
