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
//		System.out.println(countBruteForce(2));
//		System.out.println(countBruteForce(10));
//		System.out.println(countBruteForce(12));
//		System.out.println(countBruteForce(20));
//		System.out.println(countBruteForce(30));
//		System.out.println(countBruteForce(500));
//		System.out.println(countBruteForce(5000));
//		
//		System.out.println(countDynamic(2));
//		System.out.println(countDynamic(10));
//		System.out.println(countDynamic(12));
//		System.out.println(countDynamic(20));
//		System.out.println(countDynamic(30));
//		System.out.println(countDynamic(500));
//		System.out.println(countDynamic(5000));
		
		// Test speed
		StopWatch sw = new StopWatch();
		int n = 1000000;
		
		sw.start();
		System.out.println(countBruteForce(n));
		sw.stop();
		System.out.println("Brute Force Consumed (ns): \t\t" + sw.getNanoTime());
		
		sw.reset();
		
		sw.start();
		System.out.println(countDynamic(n));
		sw.stop();
		System.out.println("Recursion/Dynamic Consumed (ns): \t" + sw.getNanoTime());

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
