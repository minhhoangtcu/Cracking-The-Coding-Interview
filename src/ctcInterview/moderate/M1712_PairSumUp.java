package ctcInterview.moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * Design an algorithm to find all pairs of integers within an array which sum
 * to a specified value.
 * 
 * @author minhhoang
 *
 */
public class M1712_PairSumUp {
	
	public static void main(String[] args) {
		
		M1712_PairSumUp psu = new M1712_PairSumUp();
		
		int[] small = {1, 5, -1, 3, 9};
		for (Pair pair: psu.getPairSumUpTo(small, 4)) {
			System.out.println(pair);
		}
		
	}

	/*
	 * Questions before the implementing the algorithm:
	 * 
	 * Are the integers within the array all distinct/unique?
	 * 
	 * Are the integers within the array all positive or if they are mixed?
	 * 
	 * Is the method find will be called once or multiple times with different
	 * sum
	 * 
	 * After picking a pair, will it disappear? If I have (1, 2), will the
	 * integer 1 and 2 still be consider for another pair?
	 */

	/**
	 * Return a set of pairs that sum up to the specified sum.
	 * 
	 * Algorithm: Let's see an array
	 * 
	 * {1, 5, -1, 3, 9} and we are looking for pair that add up to 4.
	 * 
	 * We look at 1, and the only way for 1 to become 4 is that there exist 3 in
	 * the array. Next, we look at 5, and the only way for 5 to become 4 is that
	 * there exist -1 in the array. So, naturally we can have a hash set that
	 * contains all integers within the array.
	 * 
	 * @param nums
	 * @param sum
	 * @return
	 */
	public Set<Pair> getPairSumUpTo(int[] nums, int sum) {

		// Build up the hash inside table
		HashSet<Integer> inside = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (!inside.contains(nums[i]))
				inside.add(nums[i]);
		}

		// Check for pair
		HashSet<Pair> pairs = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			int b = sum - a;
			if (inside.contains(b)) {
				inside.remove(a);
				inside.remove(b);
				pairs.add(new Pair(a, b));
			}
		}

		return pairs;

	}

	class Pair {
		int a, b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return String.format("a: %d\tb: %d", a, b);
		}
	}

}
