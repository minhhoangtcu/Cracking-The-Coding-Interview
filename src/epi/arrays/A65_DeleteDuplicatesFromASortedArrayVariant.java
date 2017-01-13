package epi.arrays;

import java.util.Arrays;

/**
 * Write a program which takes as input a sorted array A of integers and a
 * positive integer m, and updates A so that if x appears m times in A it
 * appears exactly min(2, m) times in A. The update to A should be performed in
 * one pass, and no additional storage may be allocated.
 */
public class A65_DeleteDuplicatesFromASortedArrayVariant {
	
	public static void main(String[] args) {
		test1();
		test11();
		test12();
		test2();
		test21();
		test22();
		test3();
		test4();
		test41();
		test5();
		test51();
	}
	
	public static void test1() {
		int[] testArray = {2, 3, 5, 5, 7, 11, 11, 11, 13};
		assert(remove(testArray, 3) == 8);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test11() {
		int[] testArray = {2, 3, 5, 5, 7, 11, 11, 11, 13};
		assert(remove(testArray, 2) == 9);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test12() {
		int[] testArray = {2, 3, 5, 5, 7, 11, 11, 11, 13};
		assert(remove(testArray, 1) == 9);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test2() {
		int[] testArray = {1, 1, 1, 1};
		assert(remove(testArray, 3) == 4);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test21() {
		int[] testArray = {1, 1, 1, 1};
		assert(remove(testArray, 4) == 2);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test22() {
		int[] testArray = {1, 1, 1, 1};
		assert(remove(testArray, 0) == 4);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test3() {
		int[] testArray = {};
		assert(remove(testArray, 1) == 0);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test4() {
		int[] testArray = {1, 2, 3, 4, 5};
		assert(remove(testArray, 1) == 5);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test41() {
		int[] testArray = {1, 2, 3, 4, 5};
		assert(remove(testArray, 2) == 5);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test5() {
		int[] testArray = {1};
		assert(remove(testArray, 1) == 1);
		System.out.println(Arrays.toString(testArray));
	}
	
	public static void test51() {
		int[] testArray = {1};
		assert(remove(testArray, 2) == 1);
		System.out.println(Arrays.toString(testArray));
	}

	public static int remove(int[] array, int m) {
		
		if (array == null || array.length == 0) {
			return 0;
		}
		
		int numOfValidElements = 0;
		
		int explorer = 0;
		int lastExplored = array[0] < 0 ? array[0] - 1 : -array[0]; // sentinel
		int currentLength = 0;
		int builder = 0;
		
		while (explorer <= array.length) {
			
			// ending sentinel also
			int exploring = explorer == array.length
							? 0
							: array[explorer];
			
			if (exploring != lastExplored) {
				// Got into new section
				
				if (currentLength == m && m > 2) {
					// If the last section has exactly m elements, we wind back
					// builder so that the section has only 2 elements
					builder -= (m - 2);
				}
				
				currentLength = 0;
			}
			
			if (builder < array.length) array[builder] = exploring;
			lastExplored = exploring;
			
			explorer++;
			builder++;
			currentLength++;
			
		}
		
		numOfValidElements = builder - 1;
		
		for (; builder < array.length; builder++) {
			array[builder] = 0;
		}
		
		return numOfValidElements;
		
	}
	
}
