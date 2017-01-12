package epi.arrays;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * In a particular board game, a player has to try to advance through a sequence
 * of positions. Each position has a nonnegative integer associated with it,
 * representing the maximum you can advance from that position in one move. You
 * begin at the first position, and win by getting to the last position.
 * 
 * Write a program which takes an array of n integers, where A[i] denotes the
 * maximum you can advance from index i, and returns whether it is possible to
 * advance to the last index starting from the beginning of the array.
 * 
 */
public class A64_AdvancingThroughAnArray {

	@Test
	public void testSimpleCanReach() {
		A64_AdvancingThroughAnArray ataa = new A64_AdvancingThroughAnArray();
		assertTrue(ataa.canReachEnd(new int[] {3, 3, 1, 0, 2, 0, 1}));
	}
	
	@Test
	public void testSimpleCannotReach() {
		A64_AdvancingThroughAnArray ataa = new A64_AdvancingThroughAnArray();
		assertFalse(ataa.canReachEnd(new int[] {3, 2, 0, 0, 2, 0, 1}));
	}
	
	@Test
	public void testEdgesCanReach() {
		A64_AdvancingThroughAnArray ataa = new A64_AdvancingThroughAnArray();
		assertTrue(ataa.canReachEnd(new int[] {0}));
		assertTrue(ataa.canReachEnd(new int[] {1}));
		assertTrue(ataa.canReachEnd(new int[] {1, 0}));
	}
	
	@Test
	public void testEdgesCannotReach() {
		A64_AdvancingThroughAnArray ataa = new A64_AdvancingThroughAnArray();
		assertFalse(ataa.canReachEnd(new int[] {0, 1}));
		assertFalse(ataa.canReachEnd(new int[] {0, 2}));
	}
	
	public boolean canReachEnd(int[] reaches) {
		
		int lastIndex = reaches.length - 1;
		int currentPosition = 0;
		int furthestReach = 0;
		
		// As long as we can move to the furthest position.
		while (currentPosition <= furthestReach && currentPosition < lastIndex) {
			// The furthest position we can reach at i is equal to that position + its reaches
			furthestReach = Math.max(furthestReach, currentPosition + reaches[currentPosition]);
			currentPosition++;
		}
		
		return furthestReach >= lastIndex;
		
	}

}
