package epi.arrays;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Assuming that keys take one of three values, reorder the array so that all
 * objects with the same key appear together. The order of the subarrays is not
 * important. Use O(1) additional space and O(n) time.
 */
public class A61_DutchNationalFlagVariant1 {
	
	@Test
	public void single() {
		A61_DutchNationalFlagVariant1 dnfv = new A61_DutchNationalFlagVariant1();
		assertTrue(dnfv.assertColor(new int[] {1, 1, 1, 1}, 1));
		assertTrue(dnfv.assertColor(new int[] {1, 1, 1, 1}, 0));
		assertTrue(dnfv.assertColor(new int[] {1, 1, 1, 1}, 2));
	}
	
	@Test
	public void binary() {
		A61_DutchNationalFlagVariant1 dnfv = new A61_DutchNationalFlagVariant1();
		assertTrue(dnfv.assertColor(new int[] {1, 1, 1, 0}, 1));
		assertTrue(dnfv.assertColor(new int[] {1, 1, 0, 0}, 1));
		assertTrue(dnfv.assertColor(new int[] {1, 0, 0, 0}, 1));
		assertTrue(dnfv.assertColor(new int[] {1, 0, 1, 0}, 0));
		assertTrue(dnfv.assertColor(new int[] {1, 1, 1, 1}, 0));
	}
	
	@Test
	public void triple() {
		A61_DutchNationalFlagVariant1 dnfv = new A61_DutchNationalFlagVariant1();
		assertTrue(dnfv.assertColor(new int[] {1, 2, 2, 0, 1, 1, 0}, 1));
		assertTrue(dnfv.assertColor(new int[] {1, 2, 2, 0, 1, 1, 0}, 0));
		assertTrue(dnfv.assertColor(new int[] {1, 2, 2, 0, 1, 1, 0}, 2));
		
		assertTrue(dnfv.assertColor(new int[] {0, 1, 2}, 0));
		assertTrue(dnfv.assertColor(new int[] {0, 1, 2}, 1));
		assertTrue(dnfv.assertColor(new int[] {0, 1, 2}, 2));
		
		assertTrue(dnfv.assertColor(new int[] {2, 1, 0}, 0));
		assertTrue(dnfv.assertColor(new int[] {2, 1, 0}, 1));
		assertTrue(dnfv.assertColor(new int[] {2, 1, 0}, 2));
	}
	
//	public static void main(String[] args) {
//		A61_DutchNationalFlagVariant1 dnfv = new A61_DutchNationalFlagVariant1();
//		dnfv.assertColor(new int[] {1, 2, 2, 0, 1, 1, 0}, 1);
//	}

	public static enum Color {RED, WHITE, BLUE}
	
	// Immitate a bucket with 3 sections
	public class Bucket {
		
		private List<Color> bucket;
		private Color pivot;
		private int firstSectionAfterEnd, thirdSectionBeforeStart, unknownStart;
		
		public Bucket(List<Color> bucket, Color pivot) {
			this.bucket = bucket;
			this.pivot = pivot;
			firstSectionAfterEnd = unknownStart = 0;
			thirdSectionBeforeStart = bucket.size() - 1;
		}
		
		// Remove the first unknown number and put in the first section
		public void putInFirst() {
			// we don't do a condition check, because going from left -> right,
			// everything before firstSectionAfterEnd has to be in correct order
			Collections.swap(bucket, unknownStart++, firstSectionAfterEnd++);
		}
		
		// Remove the first unknown number and put in the third section 
		public void putInLast() {
			Collections.swap(bucket, unknownStart, thirdSectionBeforeStart);
			
			// only remove unknown if we make a valid swap
			if (bucket.get(unknownStart) == pivot) unknownStart++;
			
			thirdSectionBeforeStart--;
		}
		
		public void putInMid() {
			unknownStart++; // don't need to swap, because it is in correct place already
		}
		
		public boolean isOutOfUnknown() {
			return unknownStart > thirdSectionBeforeStart;
		}
		
		public Color peekUnknow() {
			return bucket.get(unknownStart);
		}
		
		public void print() {
			for (Color e: bucket) { System.out.print(e.ordinal() + " "); }
			System.out.printf("\ts: %d\tu: %d\te: %d\n", firstSectionAfterEnd, unknownStart, thirdSectionBeforeStart);
		}
		
	}
	
	// Arrange all the elements, so that pivot will be in middle.
	// Make only 1 pass. Keep track of Left, Mid, Unknown, Right
	public List<Color> rearrange(List<Color> allElements, Color pivot) {
		
		Bucket bucket = new Bucket(allElements, pivot);
		
		// Build map to make the pivot in the middle
		Map<Integer, Integer> colorToNewPosition = new HashMap<>();
		
		int pivotIndex = pivot.ordinal();
		int newPosition = 1;
		int oldPosition = pivotIndex;
		
		do {
			colorToNewPosition.put(oldPosition, newPosition); // always at mid
			oldPosition = (oldPosition + 1) % Color.values().length;
			newPosition = (newPosition + 1) % Color.values().length;
		} while (oldPosition != pivotIndex);
		
		// Rebuild the flag
		while (!bucket.isOutOfUnknown()) {
			
			Color unknown = bucket.peekUnknow();
			int mapping = colorToNewPosition.get(unknown.ordinal());
			
			switch(mapping) {
			case 0:
				bucket.putInFirst();
				break;
			case 1:
				bucket.putInMid();
				break;
			case 2:
				bucket.putInLast();
				break;
			}
			
		}
		
		return allElements;
	}
	
	public void print(List<Color> input) {
		for (Color e: input) {
			System.out.print(e.ordinal() + " ");
		}
		System.out.println();
	}
	
	public boolean assertColor(int[] input, int colorIndex) {
		return isInCorrectOrder(rearrange(generate(input), Color.values()[colorIndex]));
	}
	
	public List<Color> generate(int[] input) {
		List<Color> output = new ArrayList<>();
		
		for (int index: input) {
			Color[] allColors = Color.values();
			output.add(allColors[index]);
		}
		
		return output;
	}
	
	public boolean isInCorrectOrder(List<Color> allElements) {
		
		int numOfBreaks = 0;
		int length = allElements.size();
		
		// To be in the correct order, each element has to always be bigger than the last one		
		for (int i = 0; i < length; i++) {
			if (allElements.get(i).ordinal() > allElements.get((i + 1) % length).ordinal()) {
				numOfBreaks++;
			}
		}
		
		return numOfBreaks <= 1;
	}
	
}
