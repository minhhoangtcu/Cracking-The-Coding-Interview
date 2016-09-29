package pramp;

import java.util.Arrays;

/**
 * Given an array arr of length n where each element is at most k places away
 * from its sorted position, Plan and code an efficient algorithm to sort arr.
 * Analyze the runtime and space complexity of your solution.
 * 
 * Example: n=10, k=2. The element belonging to index 6 in the sorted array, may
 * be at indices 4, 5, 6, 7 or 8 on the given array.
 * 
 * TIP:
 * 
 * - Ask about the constraints of n and k. Can k be greater than n? Logically,
 * it must not right? Can k be 0?
 * 
 * - Ask if it can go over. For example, if a number NUM1 be -1 at the end of
 * the list. Would it loop back to the beginning of the loop?
 * 
 * @author minhhoang
 *
 */
public class KMessedArraySort {

	public static void main(String[] args) {

		int[] arr = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };
		int k = 3;

		System.out.println(">> Test sort O(n*k)");
		System.out.println("Raw   : " + Arrays.toString(arr));
		sort(arr, k);
		System.out.println("Sorted: " + Arrays.toString(arr));

		int[] arr2 = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };

		System.out.println(">>> Test sift down function O(logn)");
		System.out.println("Raw    : " + Arrays.toString(arr2));
		siftDown(arr2, 9, 9);
		System.out.println("Shifted: " + Arrays.toString(arr2));
		siftDown(arr2, 3, 9);
		System.out.println("Shifted: " + Arrays.toString(arr2));

		int[] arr3 = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };
		System.out.println("Raw    : " + Arrays.toString(arr3));
		siftDown(arr3, 1, 9);
		System.out.println("Shifted: " + Arrays.toString(arr3));

		int[] arr4 = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };
		System.out.println("Raw      : " + Arrays.toString(arr4));
		heapify(arr4, arr4.length);
		System.out.println("Heapified: " + Arrays.toString(arr4));

		int[] arr5 = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };
		System.out.println("Raw   : " + Arrays.toString(arr5));
		sortHeap(arr5);
		System.out.println("Sorted: " + Arrays.toString(arr5));

	}

	public static void sort(int[] arr, int k) {
		int n = arr.length;

		if (k == 0 || n == 0)
			return; // already sorted

		// loop n times
		for (int i = 0; i < n; i++) {

			// worst case, loop n times again -> O(n^2).
			int min = arr[i];
			int minIndex = i;
			for (int j = i + 1, distance = 1; j < n && distance <= k; j++, distance++) {
				if (arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}

			if (minIndex != i)
				swap(arr, i, minIndex);
		}
	}

	/**
	 * We define a virtual 'sliding window' of from the first k+1 elements of
	 * arr. First we build a min heap from the elements in the window. Then, we
	 * start sliding: on each step we extract the minimum from the heap, move
	 * the window one place right, place the min we've extracted into index that
	 * is now left to the window and insert the new element at the end of the
	 * window to the heap. We repeat that until the window reaches the end of
	 * the array, then we extract the minimum from the heap and place it on the
	 * next index of arr, until the heap is empty.
	 * 
	 * @param arr
	 * @param k
	 */
	public static void sortMinHeap(int[] arr, int k) {
		
	}
	
	public static void sortHeap(int[] arr) {
		// get max heap
		heapify(arr, arr.length);

		// sort, swaping biggest to the back
		int end = arr.length - 1;
		while (end > 0) {
			swap(arr, 0, end);
			end--;
			siftDown(arr, 0, end);
		}
	}

	public static void heapify(int[] arr, int count) {
		// start from the last element in the last
		int start = getParent(count - 1);

		// start from the first parent node -> sift everything down in correct
		// order, so everything under start is good. Node that
		while (start >= 0) {
			siftDown(arr, start, count - 1);
			start--;
		}
	}

	/**
	 * Shift a number down if it is smaller than its children. 
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void siftDown(int[] arr, int start, int end) {
		int root = start;

		while (getLeftChild(root) <= end) {
			int indexLeftChild = getLeftChild(root);

			int swap = root;
			if (arr[root] < arr[indexLeftChild])
				swap = indexLeftChild;
			if (indexLeftChild + 1 <= end && arr[root] < arr[indexLeftChild + 1]
					&& arr[indexLeftChild] < arr[indexLeftChild + 1])
				swap = indexLeftChild + 1;

			if (swap == root) {
				// child is not bigger -> root is already biggest -> done
				return;
			} else {
				swap(arr, root, swap);
				root = swap;
			}
		}

	}

	public static int getLeftChild(int i) {
		return 2 * i + 1;
	}

	public static int getParent(int i) {
		return (int) Math.floor((i - 1) / 2);
	}

	// assume that you have a method to swap. so, don't waste time writing this
	// out.
	private static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
}
