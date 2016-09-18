package hackerrank.contest.worldCodeSpirit;

import java.io.*;
import java.util.*;

public class KthZero {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();

		// populate list of indexes of zeros
		ArrayList<Integer> indexesOfZeros = new ArrayList<>();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			if (nums[i] == 0)
				indexesOfZeros.add(i);
		}

		// execute queries
		for (int i = 0; i < q; i++) {
			int command = sc.nextInt();
			int index = sc.nextInt();
			switch (command) {
			case 1:
				if (index - 1 >= indexesOfZeros.size()) // 1st 0 means 0th 0, so
														// we need to -1.
					System.out.println("NO");
				else
					// O(1)
					System.out.println(indexesOfZeros.get(index - 1));
				break;
			case 2:
				int replacing = sc.nextInt();
				if (nums[index] == 0 && replacing != 0) {
					removeIndex(indexesOfZeros, index); // O(n)
				} else if (nums[index] != 0 && replacing == 0) {
					indexesOfZeros.add(i);
					Collections.sort(indexesOfZeros); // to keep the index
														// ascendingly. O(nlogn)
				}
				nums[index] = replacing;
				break;
			}
		}
	}

	private static void removeIndex(ArrayList<Integer> list, int num) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == num) {
				list.remove(i);
				return;
			}
		}
		throw new IllegalArgumentException("No such number within the list");
	}
}
