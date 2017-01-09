package epi.arrays;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Rearrange the array so that all even numbers appear first. Using, only O(1) space.
 * 
 * @author minhhoang
 *
 */
public class A60_BootCamp {

	/*
	 * - Do we have to keep the order of the array?
	 * 
	 * 1 2 4 1 5
	 * 2 4 1 1 5 
	 * 
	 * Algorithm:
	 * User 2 pointers to swap elements
	 * 
	 * - Keep a while loop for both pointer
	 *   + Pointer even: go right until find an even num
	 *   + same for pointer odd
	 *   + when both is satisfied -> swap.
	 *   
	 * - If odd reached the end -> no more odd nums -> we are finished.
	 * - If even reached the end -> also finished
	 * - If odd is faster than even -> we need to increase even because that even number is already in good place
	 * 
	 */
	
	@Test
	public void simple() {
		A60_BootCamp bc = new A60_BootCamp();
		assertArrayEquals(new int[] {2, 4, 1, 1, 5}, bc.rearrange(new int[] {1, 2, 4, 1, 5}));
		assertArrayEquals(new int[] {2, 1}, bc.rearrange(new int[] {1, 2}));
		assertArrayEquals(new int[] {2, 1}, bc.rearrange(new int[] {2, 1}));
		assertArrayEquals(new int[] {1, 1}, bc.rearrange(new int[] {1, 1}));
		assertArrayEquals(new int[] {2, 2}, bc.rearrange(new int[] {2, 2}));
		assertArrayEquals(new int[] {2, 3, 3}, bc.rearrange(new int[] {3, 2, 3}));
		assertArrayEquals(new int[] {2, 4, 3, 1}, bc.rearrange(new int[] {1, 2, 3, 4}));
	}
	
	@Test
	public void edges() {
		A60_BootCamp bc = new A60_BootCamp();
		assertArrayEquals(new int[] {}, bc.rearrange(new int[] {}));
		assertArrayEquals(new int[] {1}, bc.rearrange(new int[] {1}));
		assertArrayEquals(new int[] {2}, bc.rearrange(new int[] {2}));
	}
	
	// Rearrange the even numbers to the front - order does not matter
	// IF ORDER MATTERS, JUST DO INSERTION SORT
	// Time: O(n)
	public int[] rearrange(int[] array) {
		
		int length = array.length;
		int evenPointer = 0;
		int oddPointer = 0;
		
		while (evenPointer < length && oddPointer < length) {
			
			if (evenPointer < oddPointer || array[evenPointer] % 2 != 0) evenPointer++;
			else if (array[oddPointer] % 2 != 1) oddPointer++;
			else swap(array, evenPointer++, oddPointer++);
			
		}
		
		return array;
		
	}
	
	public void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
}
