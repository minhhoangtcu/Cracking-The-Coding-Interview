package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ContainerWithMostWater {

	public static void main(String[] args) {

		Integer[] a = { 2569, 3063, 2665, 5324, 5208, 3289, 4909, 2169, 8573, 4189, 6137, 9753, 4733, 9870, 3844, 9221,
				4136, 7891, 5130, 3853, 1593, 2148, 9096, 8031, 5493, 3871, 287, 2076, 1341, 9571, 9497, 6222, 6392,
				9715, 3915, 1287, 4308, 2086, 4141, 7083, 8629, 400, 5087, 443, 640, 6008, 102, 5440, 9992, 1972, 5574,
				7947, 6348, 1074, 572, 289, 1687, 4375, 9907, 6181, 4026, 7620, 6414, 6768, 6534, 6574, 5097, 2508, 824,
				8784, 8450, 6044, 583, 7598, 6323, 121, 8475, 9199, 6585, 1555, 7958, 4411, 5902, 2557, 2806, 5780,
				9763, 7558, 282, 8009, 9552, 432, 2602, 4829, 7073, 9526, 9820, 2784, 2120, 8533, 9134, 6613, 2788,
				4524, 9628, 4315, 2836, 5131, 5571, 3529, 5113, 9571, 2596, 8926, 6228, 1963, 8383, 2457, 4585, 7785,
				9424, 8750, 3259, 1818, 5786, 6404, 8166, 7966, 7235, 887, 3570, 724, 2719, 4197, 8369, 9554, 274, 7618,
				8311, 6570, 6858, 2527, 3393, 9583, 4839, 6683, 4259, 7568, 7815, 2066, 8742, 2530, 4353, 9736, 6767,
				5690, 9279, 8468, 5093, 5236, 8739, 8373, 6211, 9467, 9672, 1845, 7944, 7079, 3703, 6900, 2914, 4355,
				5392, 7540, 7531, 5577, 1447, 5088, 920, 6419, 4243, 5441, 4750, 5485, 8721, 7376, 6431, 2911, 3506,
				5406, 2675, 4472, 957, 4048, 5308, 5571, 6076, 2925, 2122, 9859, 8147, 4648, 1506, 787, 2250, 8597,
				4912, 3070, 5623, 5563, 568, 5784, 3167, 9522, 2132, 7262, 931, 3495, 3979, 1549, 1175, 264, 3322, 7722,
				8920, 5635, 636, 9507, 3958, 3857, 7855, 4767, 8119, 667, 6344, 9111, 3906, 9993, 9832, 600, 8937, 7700,
				4742, 4224, 3636, 2757, 7343, 2558, 1573, 8699, 318, 3312, 102, 897, 875, 6351, 6953, 2873, 7277, 9767,
				4367, 460, 8913, 4487, 8771, 9522, 8319, 5616, 5741, 5525, 3120, 8775, 2621, 4927, 2064, 755, 5089,
				6961, 1901, 2007, 7549, 3482, 2176, 6464, 8576, 3607, 6253, 4484, 55, 3681, 3483, 6243, 484, 9512, 896,
				7612, 6231, 5204, 3764, 3566, 5793, 5533, 6620, 9595, 6547, 8030, 6890, 9426, 259, 6995, 7619, 5623,
				3037, 6833, 7815, 3992, 4468, 9939, 2979, 7949, 1991, 4460, 4510, 5718, 6159, 9281, 3593, 4232, 1999,
				1361, 5330, 3504, 2877, 7593, 7875, 6311, 1000, 8128, 7611, 4388, 9396, 1246, 6497, 6779, 6123, 1611,
				6239, 6136, 9710, 6779, 4624, 8151, 8362, 4818, 2373, 4081, 3435, 6136, 1559, 1515, 6054, 5865, 1323,
				2846, 8407, 6538, 3352, 8973, 2581, 6477, 9919, 1052, 8765, 6682, 7971, 2933, 7361, 5958, 1644, 5982,
				5604, 7811, 646, 9587, 2459, 9178, 2490, 1853, 6434, 2342, 7294, 9007, 8521, 1656, 397, 8556, 5174,
				2152, 8774, 5501, 1958, 4560, 5260, 3619, 8801, 7284, 3568, 4902, 1049, 1089, 4551, 9330, 3369, 4603,
				1482, 1366, 167, 1810, 2481, 7760, 630, 3215, 4504, 5537, 489, 3399, 816, 2290, 6398, 7971, 1140, 2908,
				1764, 170, 4186, 2231, 4233, 6393, 9495, 8077, 3881, 7013, 1814, 6226, 9883, 9345, 2498, 1455, 4063,
				476, 3809, 4418, 242, 83, 2202, 563, 5350, 1705, 6505, 7321, 6727, 4187, 694, 4848, 5086, 9755, 4086,
				4207, 6094, 6648, 2523, 6728, 2841, 5984, 2080, 954, 534, 191, 9575, 6579, 9783, 2183, 6586, 1295, 7303,
				226, 546, 5136, 9317, 2537, 8429, 9426, 4580, 5996, 705, 1894, 2249, 156, 2670, 4880, 8776, 9256, 1149,
				6406, 2018, 9870, 8281, 2900, 6177, 6944, 7558, 1131, 9386, 2838, 5972, 5271, 7068, 404, 9152, 1085,
				8303, 4002, 8494, 7826, 3504, 8515, 5102, 9342, 1583, 6437, 7898, 196, 2517, 5150, 8661, 4014, 845,
				8335, 1883, 3468 };

		List<Integer> list = Arrays.asList(a);

		ContainerWithMostWater cwmw = new ContainerWithMostWater();

		System.out.println(cwmw.maxAreaBruteforce(list));
		System.out.println(cwmw.maxArea(list));

	}

	public int maxAreaBruteforce(List<Integer> list) {

		if (list == null || list.size() <= 1)
			return 0;

		int maxArea = Integer.MIN_VALUE;
		int maxHeight1 = 0;
		int maxHeight2 = 0;
		int maxIndex1 = 0;
		int maxIndex2 = 0;

		// O(n^2) approach. Brute force.
		for (int i = 0; i < list.size() - 1; i++) {

			for (int j = i + 1; j < list.size(); j++) {

				int width = j - i;
				int height = Math.min(list.get(i), list.get(j));
				int area = width * height;
				if (area > maxArea) {
					maxHeight1 = list.get(i);
					maxHeight2 = list.get(j);
					maxIndex1 = i;
					maxIndex2 = j;
					maxArea = area;
				}
			}
		}

		System.out.printf("h1: %d\th2: %d\ti1: %d\ti2: %d\n", maxHeight1, maxHeight2, maxIndex1, maxIndex2);
		return maxArea;

	}

	public int maxArea(List<Integer> a) {

		if (a == null || a.size() <= 1)
			return 0;

		int maxArea = Integer.MIN_VALUE;
		int maxWidth = 0;
		int maxHeight1 = 0, maxHeight2 = 0;

		Hashtable<Integer, ArrayList<Integer>> table = new Hashtable<>();

		// build height postitions table
		for (int pos = 0; pos < a.size(); pos++) {
			int height = a.get(pos);
			if (!table.containsKey(height))
				table.put(height, new ArrayList<>());
			table.get(height).add(pos);
		}

		ArrayList<Integer> sortedHeights = new ArrayList<Integer>(table.keySet());
		Collections.sort(sortedHeights);

		for (int i = 0; i < sortedHeights.size(); i++) {
			int previousHeight = sortedHeights.get(i);
			for (int j = i; j < sortedHeights.size(); j++) {
				int currentHeight = sortedHeights.get(j);

				ArrayList<Integer> currentPos = table.get(currentHeight);
				ArrayList<Integer> previousPos = table.get(previousHeight);

				int width = Math.max(Math.abs(currentPos.get(0) - previousPos.get(previousPos.size() - 1)),
						Math.abs(previousPos.get(0) - currentPos.get(currentPos.size() - 1)));
				int height = Math.min(currentHeight, previousHeight);
				int area = width * height;

				if (area > maxArea) {
					maxHeight1 = currentHeight;
					maxHeight2 = previousHeight;
					maxWidth = width;
					maxArea = area;
				}
			}
		}

		System.out.printf("h1: %d\th2: %d\tw: %d\n", maxHeight1, maxHeight2, maxWidth);
		return maxArea;

	}

}
