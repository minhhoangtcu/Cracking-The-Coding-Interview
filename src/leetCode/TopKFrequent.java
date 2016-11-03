package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopKFrequent {

	public List<Integer> topKFrequent(int[] nums, int k) {

		if (nums == null || nums.length == 0)
			return null;

		Map<Integer, Integer> counts = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (!counts.containsKey(nums[i]))
				counts.put(nums[i], 0);
			counts.put(nums[i], counts.get(nums[i]) + 1);
		}

		List<Element> output = new LinkedList<>();
		for (Map.Entry<Integer, Integer> element : counts.entrySet()) {
			output.add(new Element(element.getKey().intValue(), element.getValue().intValue()));
		}

		output.sort((Element e1, Element e2) -> {
			return e1.value - e1.value;
		});

		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			result.add(output.get(i).value);
		}

		return result;
	}

	class Element {
		int value;
		int occurance;

		public Element(int value, int occurance) {
			this.value = value;
			this.occurance = occurance;
		}
	}

}
