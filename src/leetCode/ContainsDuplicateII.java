package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContainsDuplicateII {
	
	public static void main(String[] args) {
		
		ContainsDuplicateII cd = new ContainsDuplicateII();
		
		System.out.println(cd.containsNearbyDuplicate(new int[] {1, 1, 3, 4, 5}, 2));
		
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {

		// corner cases

		Map<Integer, List<Integer>> valueToIndexes = new HashMap<>();
		// 1 -> 0 1
		// 3 -> 2
		// 4 -> 3
		// 5 -> 4

		// populate the map with indexes of all numbers
		for (int i = 0; i < nums.length; i++) {

			if (!valueToIndexes.containsKey(nums[i])) {
				valueToIndexes.put(nums[i], new LinkedList<Integer>());
			}

			List<Integer> indexes = valueToIndexes.get(nums[i]);
			indexes.add(i);
		}

		// iterate through the map, and check for duplicate
		for (Integer key : valueToIndexes.keySet()) {

			List<Integer> indexes = valueToIndexes.get(key);

			if (indexes.size() < 2) {
				continue;
			} else {

				// look for 2 indexes that are less than k distance from each
				// other
				for (int i = 1; i < indexes.size(); i++) {
					if (Math.abs(indexes.get(i) - indexes.get(i - 1)) <= k) {
						return true;
					}
				}
			}
		}

		return false;

	}

}
