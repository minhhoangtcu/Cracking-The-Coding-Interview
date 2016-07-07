package hackerrank.practice;

import java.util.HashMap;
import java.util.Scanner;

public class NonDivisableSubset {
	
	/*
	 * TEST CASES:
	 * 
	 * 2 2
	 * 1 3
	 * rem(0)=0 rem(1)=2
	 * --> get 1 because 2 in rem(1) result in %
	 * 
	 * 3 2
	 * 1 3 4
	 * rem(0)=1 rem(1)=2
	 * --> get 2
	 * 
	 * 5 3
	 * 2(2) 5(2) 7(1) 9(0) 10(1)
	 * rem(0)=1 rem(1)=2 rem(2)=2
	 * --> Get 3 because either rem1 or rem 2 (whatever larger)
	 * 
	 * 7 4
	 * 2(2) 4(0) 5(1) 7(3) 11(3) 17(1) 20(0)
	 * rem(0)=2 rem(1)=2 rem(2)=1 rem(3)=2
	 * Get 4
	 * 
	 * 3 2
	 * 1000000000 999999999 999999998
	 * 
	 */

	public static void main(String[] args) {

		// Input
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] nums = new int[n];
		HashMap<Integer, Integer> remainderCounter = new HashMap<>();

		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			int rem = nums[i] % k;
			if (remainderCounter.containsKey(rem)) {
				// System.out.println(rem);
				// System.out.println(remainderCounter.get(rem));
				remainderCounter.put(rem, remainderCounter.get(rem) + 1); // Increase
																			// counter
			} else
				remainderCounter.put(rem, 1);
		}
		
		int max = 0;

		int i = 1;
		for (i = 1; i <= k / 2; i++) {

			// Check for middle. If even -> mid pick 1 if exist. If odd --> same
			// logic.
			if (k == 2 * i) {
				if (remainderCounter.containsKey(i))
					max++;
				break;
			}

			if (remainderCounter.containsKey(i) && remainderCounter.containsKey(k - i)) {
				max += Math.max(remainderCounter.get(i), remainderCounter.get(k - i));
			} else if (!remainderCounter.containsKey(i) && remainderCounter.containsKey(k - i)) {
				max += remainderCounter.get(i);
			} else if (remainderCounter.containsKey(i) && !remainderCounter.containsKey(k - i)) {
				max += remainderCounter.get(k - i);
			}
		}

		System.out.println(max);
	}
}
