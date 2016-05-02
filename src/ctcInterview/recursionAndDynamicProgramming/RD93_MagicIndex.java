package ctcInterview.recursionAndDynamicProgramming;

/**
 * A magic index in an array A[0...n-1] is defined to be an index such that A[i]
 * = i. Given a sorted array of distinct integers, write a method to find a
 * magic index, if one exists, in array A.
 * 
 * FOLLOW UP
 * 
 * What if the values are not distinct?
 * 
 * @author minhhoang
 *
 */
public class RD93_MagicIndex {

	public static void main(String[] args) {

		RD93_MagicIndex mi = new RD93_MagicIndex();

		System.out.println("<<< TEST MAGIC NUMBER AT RANDOM PLACE >>>");
		int[] allNum = { -5, -4, 1, 3, 5, 6, 7, 10, 50, 100 };
		System.out.println(mi.getMagicNumber(allNum));
		System.out.println(mi.getMagicNumberFollowUp(allNum));

		System.out.println("<<< TEST MAGIC NUMBER AT BEGINNING >>>");
		int[] magicAtBeginning = { 0, 5, 10, 15, 23 };
		System.out.println(mi.getMagicNumber(magicAtBeginning));
		System.out.println(mi.getMagicNumberFollowUp(magicAtBeginning));

		System.out.println("<<< TEST MAGIC NUMBER AT ENDING >>>");
		int[] magicAtEnding = { -5, -4, 1, 3 };
		System.out.println(mi.getMagicNumber(magicAtEnding));
		System.out.println(mi.getMagicNumberFollowUp(magicAtEnding));

		System.out.println("<<< TEST MAGIC NUMBER FOR DISTINCT INTEGER ARRAY >>>");
		int[] distinct = {-1, 0, 3, 4, 7, 7, 7, 7, 9};
		System.out.println(mi.getMagicNumberFollowUp(distinct));
		
	}
	
	public int getMagicNumberFollowUp(int[] allNum) {
		if (allNum.length == 0) {
			System.err.println("Empty array input!");
			return -1;
		}

		int result;
		if ((result = getMagicNumberFollowUpHelper(allNum, 0, allNum.length)) == -1)
			System.err.println("No magic number in provided array!");
		return result;
	}
	
	private int getMagicNumberFollowUpHelper(int[] allNum, int start, int end) {
		
		if (start > end)
			return -1;
		
		int mid = (start + end) / 2;

		int left, right, dif;
		if ((dif = allNum[mid] - mid) == 0)
			return mid;
		else if (allNum[mid] > mid) {
			if ((left = getMagicNumberHelper(allNum, start, mid - 1)) != -1)
				return left;
			if ((right = getMagicNumberHelper(allNum, mid+dif, end)) != -1)
				return right;
		}
		else {
			if ((left = getMagicNumberHelper(allNum, mid-dif, end)) != -1)
				return left;
			if ((right = getMagicNumberHelper(allNum, mid+1, end)) != -1)
				return right;
		}
		
		return -1;
	}

	/**
	 * Find the magic number in a provided array. A magic number is k for k =
	 * array[k]. The method does a special binary search to find the number. It
	 * compares the index k at the middle with the value at the middle instead.
	 * If k > a[k], then the magic number cannot be on the left of k (because
	 * all numbers need to be distinct). For k < a[k], the same logic holds
	 * true.
	 * 
	 * @param allNum an array of sorted distinct integers 
	 * @return the magic number
	 */
	public int getMagicNumber(int[] allNum) {

		if (allNum.length == 0) {
			System.err.println("Empty array input!");
			return -1;
		}

		int result;
		if ((result = getMagicNumberHelper(allNum, 0, allNum.length)) == -1)
			System.err.println("No magic number in provided array!");
		return result;
	}

	private int getMagicNumberHelper(int[] allNum, int start, int end) {

		if (start > end)
			return -1;

		int mid = (start + end) / 2;

		if (allNum[mid] == mid)
			return mid;
		else if (allNum[mid] > mid)
			return getMagicNumberHelper(allNum, start, mid - 1);
		else
			return getMagicNumberHelper(allNum, mid + 1, end);
	}

}
