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

		int[] allDistinct = { 1, 2, 5, 4, 3, 7, 9, 8, 10, 11 };
		System.out.println(gsts.getSmallestToSort(allDistinct));
		System.out.println(gsts.getSmallestToSortSimple(allDistinct));

		int[] containDuplicates = { 1, 2, 5, 4, 8, 9, 8, 9, 5, 10, 11 };
		System.out.println(gsts.getSmallestToSort(containDuplicates));
		System.out.println(gsts.getSmallestToSortSimple(containDuplicates));

		int[] containDuplicates2 = { 1, 2, 8, 5, 9, 9, 10, 11 };
		System.out.println(gsts.getSmallestToSort(containDuplicates2));
		System.out.println(gsts.getSmallestToSortSimple(containDuplicates2));

		int[] containDuplicates3 = { 9, 2, 8, 5, 9, 9, 10, 11 };
		System.out.println(gsts.getSmallestToSort(containDuplicates3));
		System.out.println(gsts.getSmallestToSortSimple(containDuplicates3));

	}

	public Result getSmallestToSortSimple(int[] input) {

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
		for (int i = input.length - 1; i >= 0; i--) {
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

	/**
	 * Get indices m and n such that if you sorted elements m through n, the
	 * entire array would be sorted.
	 * 
	 * @return
	 */
	public Result getSmallestToSort(int[] input) {

		if (input.length < 2)
			return new Result(0, 0);

		int n = -1, m = -1;
		int minRight, maxLeft;

		// Go left-right to find n
		for (int i = 1; i < input.length; i++) {
			if (input[i] < input[i - 1]) {
				n = i - 1;
				break;
			}
		}

		// Go right-left to find m
		for (int i = input.length - 2; i >= 0; i--) {
			if (input[i] > input[i + 1]) {
				m = i + 1;
				break;
			}
		}

		/*
		 * We have three section, left, which is from 0 to n exclusive. Right
		 * from m to end inclusively. And, mid from n to m inclusive.
		 * 
		 * We must ensure that left < mid < right by expanding the range of n
		 * and m until the min element within n and m is bigger than the max
		 * element in left, and the max element in mid is smaller than min
		 * element in right. Because left and right are already sorted, the
		 * rightmost element in left is the max, and leftmost in right is the
		 * min.
		 */
		int minMid = getIndexMinWithin(input, n, m);
		int maxMid = getIndexMaxWithin(input, n, m);

		// Move n to left while the max of left is bigger than min of mid. Fails
		// when max of left is smaller than min of mid.
		while (n - 1 >= 0 && input[n - 1] > minMid) {
			n--;
		}

		// Move m to right while the min of right is smaller than min. Fails
		// when min of right is bigger than max in mid.
		while (m + 1 < input.length && input[m + 1] < maxMid) {
			m++;
		}

		if (n != -1 && m != -1)
			return new Result(n, m);
		else
			return new Result(0, 0);
	}

	/**
	 * Get the index of the minimum number between n and m inclusive.
	 * 
	 * @param input
	 * @param n
	 * @param m
	 * @return
	 */
	public int getIndexMinWithin(int[] input, int n, int m) {

		if (n > m)
			throw new IllegalArgumentException("Invalid range");

		int min = Integer.MAX_VALUE;
		for (int i = n; i <= m; i++) {
			if (input[i] < min)
				min = input[i];
		}
		return min;

	}

	public int getIndexMaxWithin(int[] input, int n, int m) {

		if (n > m)
			throw new IllegalArgumentException("Invalid range");

		int max = Integer.MIN_VALUE;
		for (int i = n; i <= m; i++) {
			if (input[i] > max)
				max = input[i];
		}
		return max;

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
