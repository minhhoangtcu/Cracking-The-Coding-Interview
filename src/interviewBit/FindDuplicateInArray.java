package interviewBit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindDuplicateInArray {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int num: new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 1}) {
			list.add(num);
		}
		
		System.out.println(repeatedNumber(list));
		
	}

	// DO NOT MODIFY THE LIST
	public static int repeatedNumber(final List<Integer> a) {

		int numOfSects = (int) Math.sqrt(a.size());
		System.out.println("Num of sects: " + numOfSects);
		int indexInArray = 0;

		int startIndexWithDuplicates = 0;
		int endIndexWithDuplicates = -1; // inclusive

//		// Seperate into sections. Loop through each section and examine number
//		// of elements
//		for (int i = 0; i < numOfSects; i++) {
//
//			int start = numOfSects * i + 1; // 1 4 7
//			int end = numOfSects * (i + 1) + 1; // 4 7 10
//			int numOfExpectedElements = end - start;
//			int numOfElements = 0;
//			int startIndex = indexInArray;
//
//			// Loop through the array, counting numbers within a certain range
//			while (indexInArray < a.size() /*&& a.get(indexInArray) < end*/) {
//				numOfElements++;
//				indexInArray++;
//			}
//
//			if (numOfElements > numOfExpectedElements) {
//				startIndexWithDuplicates = startIndex;
//				endIndexWithDuplicates = indexInArray - 1; // inclusive
//			}
//		}
		
		int[] numOfElements = new int[numOfSects];
		int numOfExpectedElements = numOfSects;
		int lastSection = 0;
		
		for (int i = 0; i < a.size(); i++) {
			int num = a.get(i);
			int indexFromNum = ((int) Math.ceil(((double) num / numOfSects))) - 1;
			if (indexFromNum != lastSection) {
				// came into new section
				lastSection = indexFromNum;
				startIndexWithDuplicates = i;
			}
			numOfElements[indexFromNum]++;
			
			if (numOfElements[indexFromNum] > numOfExpectedElements) {
				endIndexWithDuplicates = i;
				System.out.println(startIndexWithDuplicates);
				System.out.println(endIndexWithDuplicates);
				break;
			}
		}
		
		for (int num: numOfElements) {
			System.out.print(num + " ");
		} System.out.println();

		if (endIndexWithDuplicates != -1) {
			// found the duplicate part. Init hash map to find duplicate
			HashSet<Integer> set = new HashSet<>();
			for (int i = startIndexWithDuplicates; i <= endIndexWithDuplicates; i++) {
				int num = a.get(i);
				if (set.contains(num))
					return num;
				else
					set.add(num);
			}
		}

		return -1;
	}
	
	// 10 elements
	// only goes to 9
	// 9/3 -> 3
	// ceil(1/3 -> 1

}
