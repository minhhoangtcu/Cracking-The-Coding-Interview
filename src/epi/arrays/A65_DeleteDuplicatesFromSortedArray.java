package epi.arrays;

import java.util.Arrays;

/**
 * Write a program which takes as input a sorted array and updates it so that
 * all duplicates have been removed and the remaining elements have been shifted
 * left to fill the emptied indices. Return the number of valid elements.
 * 
 */
public class A65_DeleteDuplicatesFromSortedArray {
	
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}
	
	public static void test1() {
		int[] testArray = {2, 3, 5, 5, 7, 11, 11, 11, 13};
		assert(remove(testArray) == 6);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test2() {
		int[] testArray = {1, 1, 1, 1};
		assert(remove(testArray) == 1);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test3() {
		int[] testArray = {};
		assert(remove(testArray) == 0);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test4() {
		int[] testArray = {1, 2, 3, 4, 5};
		assert(remove(testArray) == 5);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test5() {
		int[] testArray = {1};
		assert(remove(testArray) == 1);
		System.out.println(Arrays.toString(testArray));
	}

	// Use 2 pointers to solve the problem.
	public static int remove(int[] array) {
		
		// Corner case
		if (array == null) return 0;
		else if (array.length <= 1) return array.length;
		
		int numOfValidElements = 0;
		
		int explorer = 0;
		int lastExplored = array[array.length - 1] - array[0]; // initially act as sentinel
		int builder = 0;
		
		// Rebuild the array
		while (explorer < array.length) {
			int exploring = array[explorer];
			
			if (exploring != lastExplored) {
				array[builder++] = exploring;
			}
			
			lastExplored = exploring;
			explorer++;
		}
		
		numOfValidElements = builder;
		
		// Fill remaining with zeros
		for (; builder < array.length; builder++) {
			array[builder] = 0;
		}
		
		return numOfValidElements;
		
	}
	
}
