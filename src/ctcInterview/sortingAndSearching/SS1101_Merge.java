package ctcInterview.sortingAndSearching;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer
 * at the end to hold B. Write a method to merge B into A in sorted order.
 * 
 * @author minhhoang
 *
 */
public class SS1101_Merge {

	/**
	 * If we are allowed to use an additional array C, we can just loop through
	 * A and B and put the element into C. This would take speed O(N) and memory
	 * O(N)
	 */

	/**
	 * Assume that we cannot use any additional array, we can shift the whole
	 * right side of A when we need to append any element into the array. This
	 * would take O(N^2) and memory O(1)
	 */

	/**
	 * Without additional array, we can go from right to left and put elements
	 * into the array. This would take O(n) and memory O(1)
	 */
	
	public static void main(String[] args) {
		
		SS1101_Merge m = new SS1101_Merge();
		
		int[] a = {1, 2, 0, 0, 0};
		int[] b = {0, 1, 5};
		
		m.merge(a, b);
		
		System.out.println(Arrays.toString(a));
		
		int[] a2 = {1, 2, 7, 8, 10, 12, 0, 0, 0, 0, 0, 0, 0};
		int[] b2 = {-5, -3, 1, 1, 1, 1, 15};
		
		m.merge(a2, b2);
		
		System.out.println(Arrays.toString(a2));
		
	}

	public void merge(int[] a, int[] b) {

		if (a.length < b.length)
			throw new IllegalArgumentException("Array B is bigger than array A");

		// because A has a large enough buffer -> the length of last part must
		// be equal to the length of B. For example, A.length is 5, and B.length
		// is 3, thus the last element of A has index 1, which is 5-3-1
		int lastOfA = a.length - b.length - 1;
		int runnerA = lastOfA, runnerB = b.length-1, index = a.length-1;

		// we go backward, finding the larger element
		while (runnerA >= 0 || runnerB >= 0) {
			if (runnerA == -1) // Out of elements in a -> just append remaining elements in B 
				a[index--] = b[runnerB--];
			else if (runnerB == -1)
				a[index--] = a[runnerA--];
			else if (a[runnerA] > b[runnerB])
				a[index--] = a[runnerA--];
			else
				a[index--] = b[runnerB--];
		}
	}
}
