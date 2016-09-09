package interviewBit;

import java.util.ArrayList;
import java.util.List;

public class RotatedSearchArraySearch {

	public static void main(String[] args) {

		int[] a = { 180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202, 203, 204, 3, 4, 5,
				6, 7, 8, 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36, 37, 38, 39, 41, 42, 43, 45, 48, 51,
				52, 53, 54, 56, 62, 63, 64, 67, 69, 72, 73, 75, 77, 78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101,
				102, 104, 105, 106, 107, 108, 109, 111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130,
				135, 137, 138, 139, 143, 144, 145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170, 171,
				172, 173, 174, 175, 176, 177 };

		List<Integer> list = new ArrayList<Integer>();
		for (int e : a)
			list.add(e);

		System.out.println(search(list, 42));

	}

	public static int search(final List<Integer> a, int b) {
		return searchRecursive(a, b, 0, a.size() - 1);
	}

	public static int searchRecursive(final List<Integer> list, int num, int start, int end) {

		int mid = (end + start) / 2;

		if (start > end) {
			return -1;
		} else if (list.get(mid) == num) {
			return mid;
		} else if (list.get(start) == num) {
			return start;
		} else if (list.get(end) == num) {
			return end;
		}

		System.out.printf("Start: %d(%d)\tMid: %d(%d)\tEnd: %d(%d)\n", list.get(start), start, list.get(mid), mid, list.get(end), end);
		
		if (list.get(start) < list.get(mid) && !isWithin(list, num, start, mid)) {
			// if left increasing and num is not in the range, we check only right
			return searchRecursive(list, num, mid + 1, end);
		} else if (list.get(mid) < list.get(end) && !isWithin(list, num, mid, end)) {
			// if right increasing and num is not in the range, we check only left
			return searchRecursive(list, num, start, mid - 1);
		} else if (list.get(start) > list.get(mid) && num < list.get(start) && num > list.get(mid)) {
			// if por is in left, and num < left-most number and num > right-most number, then check only right side
			return searchRecursive(list, num, mid + 1, end);
		} else if (list.get(mid) > list.get(end) && num < list.get(mid) && num > list.get(end)) {
			// if por is in right, and num < left-most number and num > right-most number, then check only left side
			return searchRecursive(list, num, start, mid - 1);
		} else
			return Math.max(searchRecursive(list, num, start, mid - 1), searchRecursive(list, num, mid + 1, end));
		
		// Check for duplicates at the start and end. If there is any, we
		// need to check both sides
//		if (list.get(start) == list.get(mid) || list.get(mid) == list.get(end)) {
//			
//
//			// If there is no duplicate at the start and at the end, then either
//			// side must be increasing
//		} else if () {
//			if (isWithin(list, num, start, mid)) {
//				// left size is increasing and the number we need to find is in
//				// the range
//				return searchRecursive(list, num, start, mid - 1);
//			} else {
//				return searchRecursive(list, num, mid + 1, end);
//			}
//		} else if (list.get(mid) < list.get(end)) {
//			if (isWithin(list, num, mid, end)) {
//				// right size is increasing and the number we need to find is in
//				// the range
//				return searchRecursive(list, num, mid + 1, end);
//			} else {
//				return searchRecursive(list, num, start, mid - 1);
//			}
//		}
//
//		// Should never come down to here
//		System.out.println("Expected unreachable reached!");
//		return -1;
	}

	private static boolean isWithin(final List<Integer> list, int num, int start, int end) {
		return list.get(start) < num && num < list.get(end);
	}

}
