package ctcInterview.hard;

import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * Write a method to randomly generate a set of m integers from an array of size
 * n. Each element must have equal probability of being chosen.
 * 
 * @author minhhoang
 *
 */
public class PerfectChoose {
	
	public static void main(String[] args) {
		
		PerfectChoose pc = new PerfectChoose();
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Hashtable<Integer, Integer> counts = new Hashtable<>();
		
		for (int loop = 0; loop < 1000; loop++) {
			for (int i : pc.chooseSetOfM(array, 5)) {
				if (counts.containsKey(i)) {
					counts.put(i, counts.get(i) + 1);
				} else {
					counts.put(i, 1);
				}
				//System.out.print(i + " ");
			}
			//System.out.println();
		}
		
		for (Entry<Integer, Integer> count: counts.entrySet()) {
			System.out.printf("%d: %d\n", count.getKey(), count.getValue());
		}
		
	}

	/**
	 * Get a set of m integers from an array of size n with each element has
	 * equal probability of being chosen. The algorithm for this method follows:
	 * take m first elements from the array to the set. For each left-over
	 * elements, we will randomly select a number. If that number is within the
	 * m first elements in the array, we can substitute the left-over element
	 * with the mth element within the set.
	 * 
	 * @param nums
	 * @param m
	 * @return
	 */
	public int[] chooseSetOfM(int[] nums, int m) {
		
		int[] set = new int[m];
		
		for (int i = 0; i < m; i++) {
			set[i] = nums[i];
		}
		
		for (int j = m; j < nums.length; j++) {
			int random = PerfectShuffle.rand(0, j);
			if (random < m)
				set[random] = nums[j];
		}
		
		return set;
	}

}
