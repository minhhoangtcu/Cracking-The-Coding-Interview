package ctcInterview.hard;

import java.util.Hashtable;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Write a method to count the number of 2s that appear in all the numbers
 * between 0 and n (inclusive).
 * 
 * EXAMPLE:
 * 
 * Input: 25
 * 
 * Output: 9 (2, 12, 20, 21, 22, 23, 24, and 25. Note that 22 counts for two
 * 2s.)
 * 
 * @author minhhoang
 *
 */
public class CountTwo {

	public static void main(String[] args) {

		// Test accuracy
		System.out.println(countBruteForce(2));
		System.out.println(countBruteForce(10));
		System.out.println(countBruteForce(12));
		System.out.println(countBruteForce(20));
		System.out.println(countBruteForce(30));
		System.out.println(countBruteForce(500));
		System.out.println(countBruteForce(5000));

		System.out.println(countMathematically(2));
		System.out.println(countMathematically(10));
		System.out.println(countMathematically(12));
		System.out.println(countMathematically(20));
		System.out.println(countMathematically(30));
		System.out.println(countMathematically(500));
		System.out.println(countMathematically(5000));
		
		// System.out.println(countDynamic(2));
		// System.out.println(countDynamic(10));
		// System.out.println(countDynamic(12));
		// System.out.println(countDynamic(20));
		// System.out.println(countDynamic(30));
		// System.out.println(countDynamic(500));
		// System.out.println(countDynamic(5000));

		// Test speed
		// StopWatch sw = new StopWatch();
		// int n = 1000000;
		//
		// sw.start();
		// System.out.println(countBruteForce(n));
		// sw.stop();
		// System.out.println("Brute Force Consumed (ns): \t\t" +
		// sw.getNanoTime());
		//
		// sw.reset();
		//
		// sw.start();
		// System.out.println(countDynamic(n));
		// sw.stop();
		// System.out.println("Recursion/Dynamic Consumed (ns): \t" +
		// sw.getNanoTime());

	}

	/**
	 * Get the number of 2s in numbers from 0 to N. This method compute the
	 * result mathematically: it will look at each digit within the number. For
	 * example, for a = 1321, we look at pos = 2, which is 3. This digit becomes
	 * a 2 for 1200-1299 and 200-299. Thus at pos = 2, the digit becomes 2 for
	 * 200 times, which is the same number of times for a = 2000. We can just
	 * divide this number by 10, then we get the number of times, which is 200.
	 * 
	 * For pos = 1, which is a 2, the digit becomes a 2 for (20-29, 120-129,
	 * 1020-1029, ...), 1320-1321. So from, 0 - 1320, it is straight forward
	 * that the number of times is 132, and for the last bits, it is 1321 - 1320
	 * + 1.
	 * 
	 * For pos = 0, which is a 1, the digit becomes a 2 for 2, 12, 22, ..., 92,
	 * 102, ..., 1312. So basically, the digit becomes 2 for the same number of
	 * times for a = 1320. Again, this is 132.
	 * 
	 * @param n
	 * @return
	 */
	public static int countMathematically(int n) {

		int[] number = new int[10];
		int numOfDigits = 0;

		int temp = n;
		while (temp != 0) {
			number[numOfDigits] = temp % 10;
			numOfDigits++;
			temp /= 10;
		}

		int count = 0;

		for (int i = 0; i < numOfDigits; i++) {

			int d = number[i];
			int roundedDown = roundDown(n, i + 1);
			int roundedUp = roundUp(n, i + 1);
			int right = n % (int) Math.pow(10, i);

			if (d == 2) {
				count += roundedDown / 10;
				count += right + 1;
			} else if (d < 2) {
				// get round up of number at pos+1
				count += roundedDown / 10;
			} else if (d > 2) {
				count += roundedUp / 10;
			}

		}

		return count;

	}

	/// round up a number at specified pos. For example, 6232 at pos 2 to 6300
	/// or 6023 to 6100.
	public static int roundUp(int number, int pos) {
		int powerOf10 = (int) Math.pow(10, pos); // for 0, we have 1, for 1, we
													// have 10, etc.
		int result = roundDown(number, pos) + powerOf10; // ((12345)->12000) +
															// 1000 -> 13000 for
															// pos == 3
		return result;
	}

	/// round down a number at specified pos. For example, 6232 at pos 2 to 6200
	/// or 6023 to 6000.
	public static int roundDown(int number, int pos) {
		int powerOf10 = (int) Math.pow(10, pos); // for 0, we have 1, for 1, we
													// have 10, etc.
		int result = number - (number % powerOf10); // 12345 - (12345 % 1000) ->
													// 12000 for pos == 3
		return result;
	}

	public static int countDynamic(int n) {
		int count = 0;
		Hashtable<Integer, Integer> numOf2s = new Hashtable<>();
		for (int i = n; i >= 0; i--) {
			count += countDynamicHelper(numOf2s, i);
		}
		return count;
	}

	public static int countDynamicHelper(Hashtable<Integer, Integer> numOf2s, int n) {

		if (n == 0) {
			return 0;
		} else if (numOf2s.containsKey(n)) {
			return numOf2s.get(n);
		}

		int count = 0;
		if (n % 10 == 2) {
			count = 1 + countDynamicHelper(numOf2s, n / 10);
		} else {
			count = countDynamicHelper(numOf2s, n / 10);
		}
		numOf2s.put(n, count);
		return count;

	}

	/**
	 * Count the number of 2s that appear in all the numbers between 0 and n
	 * (inclusive). This method utilizes a brutal force approach. For each
	 * number a, we will take the remainder of a with 10, if the remainder is 2,
	 * then we increase the count of 2s. Then, we divide a with 10 and repeat
	 * the same process until a equals 0.
	 * 
	 * @param n
	 * @return
	 */
	public static int countBruteForce(int n) {

		int count = 0;

		for (int i = 0; i <= n; i++) {

			int a = i;
			while (a != 0) {
				if (a % 10 == 2)
					count++;
				a /= 10;
			}

		}

		return count;
	}

}
