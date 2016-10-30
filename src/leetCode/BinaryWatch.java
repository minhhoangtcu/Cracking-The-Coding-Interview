package leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BinaryWatch {

	public static void main(String[] args) {

		BinaryWatch bw = new BinaryWatch();

		String[] expected = { "0:03", "0:05", "0:06", "0:09", "0:10", "0:12", "0:17", "0:18", "0:20", "0:24", "0:33",
				"0:34", "0:36", "0:40", "0:48", "1:01", "1:02", "1:04", "1:08", "1:16", "1:32", "2:01", "2:02", "2:04",
				"2:08", "2:16", "2:32", "3:00", "4:01", "4:02", "4:04", "4:08", "4:16", "4:32", "5:00", "6:00", "8:01",
				"8:02", "8:04", "8:08", "8:16", "8:32", "9:00", "10:00" };
		
		Set<String> set = new HashSet<>();
		for (String expectingTime: expected) {
			set.add(expectingTime);
		}
		
		for (String time : bw.readBinaryWatch(2)) {
			System.out.println(time);
			if (!set.contains(time))
				System.out.println("> does not contains: " + time);
		}

	}

	public List<String> readBinaryWatch(int numOfOnes) {

		List<String> time = new LinkedList<>();

		for (int num : getPossibleArrangements(numOfOnes, 10)) {
			time.add(decodeToString(num));
		}

		return time;

	}

	public int[] getPossibleArrangements(int numOfOnes, int maxSlot) {

		Set<Integer> set = new HashSet<>();
		addNumber(set, 0, maxSlot, 0, numOfOnes);

		Integer[] result = new Integer[set.size()];
		int i = 0;

		for (int num : set) {
			result[i++] = num;
		}
		
		Arrays.sort(result, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				// check hour
				if (decodeHour(o1.intValue()) < decodeHour(o2.intValue()))
					return -1;
				else if (decodeHour(o1.intValue()) > decodeHour(o2.intValue()))
					return 1;
				else {
					// check minute
					if (decodeMinute(o1.intValue()) < decodeMinute(o2.intValue()))
						return -1;
					else if (decodeMinute(o1.intValue()) > decodeMinute(o2.intValue()))
						return 1;
					else
						return 0;
				}
			}
		});

		return Arrays.stream(result).mapToInt(Integer::intValue).toArray();

	}

	public void addNumber(Set<Integer> set, int currentNum, int maxSlot, int rightSlot, int numOfOnes) {

		// out of 1 to put, we just return
		if (numOfOnes == 0) {
			int minute = decodeMinute(currentNum);
			int hour = decodeHour(currentNum);
			if (minute < 60 && hour < 12) {
				// System.out.printf("> h: %d \tm: %d\n", hour, minute);
				set.add(currentNum);
			}
			return;
		}

		// if rightSlot already reached max [0-7] out of bound.
		if (rightSlot >= maxSlot)
			return;

		for (int i = rightSlot; i < maxSlot; i++) {
			int tempNum = currentNum + (1 << i);
			addNumber(set, tempNum, maxSlot, i + 1, numOfOnes - 1); // tail
																	// recursive
		}
	}

	private int decodeMinute(int num) {
		return num & 0b0000111111;
	}

	private int decodeHour(int num) {
		return (num & 0b1111000000) >>> 6;
	}

	private String decodeToString(int num) {
		int minute = decodeMinute(num);
		int hour = decodeHour(num);
		if (minute < 10) {
			return String.format("%d:0%d", hour, minute);
		} else {
			return String.format("%d:%d", hour, minute);
		}
	}
}
