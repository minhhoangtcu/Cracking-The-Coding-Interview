package pramp;

import java.util.Arrays;

public class MinHeap {
	
	public static void main(String[] args) {
		
		int[] arr1 = { 3, 5, 8, 4, 9, 8, 10, 15, 12, 9 };
		System.out.println("Raw    : " + Arrays.toString(arr1));
		heapify(arr1); // generate min heap
		System.out.println("Shifted: " + Arrays.toString(arr1));
		
	}

	public static void heapify(int[] arr) {
		int count = arr.length;
		
		// get the parent of last element
		int start = getParent(count - 1); 
		
		while (start > 0) {
			siftDown(arr, start, count - 1);
			start--;
		}
	}
	
	/*
	 * Shift a number down if it is bigger than its children.
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
			if (arr[root] > arr[indexLeftChild])
				swap = indexLeftChild;
			if (indexLeftChild + 1 <= end && arr[root] > arr[indexLeftChild + 1]
					&& arr[indexLeftChild] > arr[indexLeftChild + 1])
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
	
	private static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
}
