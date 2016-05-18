package ctcInterview.moderate;

/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum.
 *
 * EXAMPLE
 * 
 * Input: 2, -8, 3, -2, 4, -10
 * 
 * Output: 5 (i.e., {3, -2, 4})
 * 
 * @author minhhoang
 *
 */
public class M1708_ContiguousLargestSum {
	
	public static void main(String[] args) {
		
		M1708_ContiguousLargestSum cls = new M1708_ContiguousLargestSum();
		
		int[] small = {2, -8, 3, -2, 4, -10};
		System.out.println(cls.getLargesetSum(small));
		
		int[] small2 = {2, -8, -15, -2, 4, -10};
		System.out.println(cls.getLargesetSum(small2));
		
		int[] small3 = {2, 10, 3, -10, -3, 3};
		System.out.println(cls.getLargesetSum(small3));
		
		int[] small4 = {-2, 10, 3, -1, 3, -1, 4};
		System.out.println(cls.getLargesetSum(small4));
		
	}

	public int getLargesetSum(int[] nums) {

		/*
		 * Because we are looking for a contiguous sequence with largest sum, we
		 * can simplify the array into subsections of alternating signs. For
		 * example, {1, 2, -2, -5, 1} can be treated as {3, -7, 1}. We can just
		 * go through the whole the whole array and add up the number. However,
		 * we need to be selective. If we face a negative number, we should
		 * consider if the next value (positive integer) will help make the
		 * whole sum go up or not.
		 */

		int i = 0;
		int max = Integer.MIN_VALUE, maxSquence = 0;
		boolean isContinue = false;
		while (i < nums.length) {

			int posSec = 0;
			int negSec = 0;

			// Start of pos section, then end with nums[i] < 0
			if (i < nums.length && nums[i] >= 0) {
				while (i < nums.length && nums[i] >= 0) {
					posSec += nums[i];
					i++;
				}
			}

			// Start of neg section, then end with nums[i] > 0 (beginning of
			// another pos section)
			if (i < nums.length && nums[i] <= 0) {
				while (i < nums.length && nums[i] <= 0) {
					negSec += nums[i];
					i++;
				}
			}

			if (posSec > max)
				max = posSec;
			
			// If pos & neg pair still pos and can add up with following pair,
			// the sequence of largest sum continue
			int dif = posSec + negSec;
			if (dif > 0 && !isContinue) {
				maxSquence = dif;
				isContinue = true;
			} else if (dif > 0 && isContinue)
				maxSquence += dif;
			// Else it is better off just to get to pos sec
			else if (dif < 0 && isContinue) {
				maxSquence += posSec;
				isContinue = false;
			}
			else {
				maxSquence = posSec;
				isContinue = false;
			}
			
			if (maxSquence > max)
				max = maxSquence;

		}
		
		return max;

	}

}
