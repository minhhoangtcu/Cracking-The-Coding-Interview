package epi.arrays;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Write a program that takes an array A and an index i into A, and rearranges
 * the elements such that all elements less than A[i] (the 'pivot') appear
 * first, followed by elements equal to the pivot followed by elements greater
 * than the pivot.
 * 
 */

public class A61_DutchNationalFlag {
	
	@Test
	public void simple() {
		A61_DutchNationalFlag dnf = new A61_DutchNationalFlag();
		assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5}, dnf.rearrange(new int[] {0, 1, 2, 3, 4, 5},  0));
		assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5}, dnf.rearrange(new int[] {0, 1, 2, 3, 4, 5},  1));
		assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5}, dnf.rearrange(new int[] {0, 1, 2, 3, 4, 5},  2));
		assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5}, dnf.rearrange(new int[] {0, 1, 2, 3, 4, 5},  3));
		assertArrayEquals(new int[] {4, 3, 2, 1, 0, 5}, dnf.rearrange(new int[] {5, 4, 3, 2, 1, 0},  0));
		assertArrayEquals(new int[] {3, 2, 1, 0, 4, 5}, dnf.rearrange(new int[] {5, 4, 3, 2, 1, 0},  1));
		assertArrayEquals(new int[] {2, 1, 0, 3, 5, 4}, dnf.rearrange(new int[] {5, 4, 3, 2, 1, 0},  2));
	}
	
	@Test
	public void edge() {
		A61_DutchNationalFlag dnf = new A61_DutchNationalFlag();
		assertArrayEquals(new int[] {}, dnf.rearrange(new int[] {},  0));
		assertArrayEquals(new int[] {}, dnf.rearrange(new int[] {},  -1));
		assertArrayEquals(new int[] {}, dnf.rearrange(new int[] {},  1));
		assertArrayEquals(new int[] {0}, dnf.rearrange(new int[] {0},  0));
		assertArrayEquals(new int[] {0, 1}, dnf.rearrange(new int[] {0, 1},  0));
		assertArrayEquals(new int[] {0, 1}, dnf.rearrange(new int[] {0, 1},  1));
		assertArrayEquals(new int[] {0, 1}, dnf.rearrange(new int[] {1, 0},  0));
		assertArrayEquals(new int[] {0, 1}, dnf.rearrange(new int[] {1, 0},  1));
	}
	
	public int[] rearrange(int[] array, int pivotIndex) {
		
		// corner case
		if (pivotIndex > array.length - 1 || pivotIndex < 0) {
			return array; // cannot do anything. Or, throw new IllegalArgumentException.
		}
		
		int pivot = array[pivotIndex];
		
		// Find all smaller elements, and but to top
		int searcher = 0;
		int builder = 0;
		while (searcher < array.length) {
			if (array[searcher] < pivot) {
				swap(array, searcher, builder++);
			}
			
			searcher++;
		}
		
		// Find all bigger elements, and but to bot
		searcher = array.length - 1;
		builder = array.length - 1;
		while (searcher >= 0) {
			if (array[searcher] > pivot) {
				swap(array, searcher, builder--);
			}
			
			searcher--;
		}
		
		return array;
	}
	
	public void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
