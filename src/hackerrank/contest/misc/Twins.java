package hackerrank.contest.misc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Lia is fascinated by anything she considers to be a twin. She calls a pairs
 * of positive integers, and , twins if:
 * 
 * They are both prime. A prime number is an integer greater than that has no
 * positive divisors other than and itself.
 * 
 * Their absolute difference is exactly equal to (i.e., ).
 * 
 * Given an inclusive interval of integers from to , can you help Lia find the
 * number of pairs of twins there are in the interval (i.e., )? Note that pairs
 * and are considered to be the same pair.
 * 
 * Input Format
 * 
 * Two space-separated integers describing the respective values of and .
 * 
 * https://www.hackerrank.com/contests/w26/challenges/twins
 * http://www.geeksforgeeks.org/segmented-sieve/
 * 
 * @author minhhoang
 *
 */
public class Twins {

	/*
	 * According to the Sieve of Eratosthenes, if we eliminate all multiples of
	 * primes, then we are left with all prime numbers.
	 * 
	 * If n is not a prime, then it must be divisible by a number from
	 * [2,sqrt(n)]. But, any number n can be represented as a * b. So, to
	 * eliminate all non-prime numbers from 0 - n, we only have to eliminate all
	 * multiples of primes from 0 -> sqrt(n).
	 * 
	 */
	
	public static void main(String[] args) {
		
		Twins twins = new Twins();
		
		twins.findNumberOfTwins(999000000, 1000000000);
		
	}
	
	// Get the number of twins within the range n and m
	private void findNumberOfTwins(int n, int m) {
		
		int size = m - n + 1;
		
		int[] sqrtPrimes = seive((int) Math.sqrt(m));
		boolean[] isPrime = new boolean[size]; // index 0 will be number n
		Arrays.fill(isPrime, true);
		
		// corner case
		if (n == 1)
			isPrime[0] = false;
		
		for (int i = 0; i < sqrtPrimes.length; i++) {
			// Mark all multiples of prime in the list
			int prime = sqrtPrimes[i];
			
			// We mark beginning with the first smallest multiple bigger than n
			int firstSmallestMultipleOfPrime = (n / prime) * prime;
			if (firstSmallestMultipleOfPrime < n)
				firstSmallestMultipleOfPrime += prime;
			
			// we don't do this together because if the smallest is smaller than n, it can become prime
			if (firstSmallestMultipleOfPrime == prime)
				firstSmallestMultipleOfPrime += prime;
			
			 // index accessing the array is going from 0 -> m - n, so we will never have error
			for (int j = firstSmallestMultipleOfPrime; j <= m; j += prime) {
				isPrime[j - n] = false; // j - n to convert it back into base 0
			}
		}
		
		// count the number of twins
		int numOfTwins = 0;
		
		for (int i = 2; i < size; i++) {
			if (isPrime[i] && isPrime[i - 2])
				numOfTwins++;
		}
		
//		for (int i = 0; i < size; i++) {
//			if (isPrime[i])
//				System.out.println(i + n);
//		}
		
		System.out.println(numOfTwins);
	}
	
	// Generates all primes from [2, limit]
	private int[] seive(int limit) {
		
		List<Integer> primes = new LinkedList<>();
		boolean[] isPrime = new boolean[limit + 1];
		Arrays.fill(isPrime, true);
		
		for (int i = 2; i <= limit; i++) {
			
			if (isPrime[i]) {
				primes.add(i);
				
				// mark all multiples of a prime
				for (int j = i*2; j <= limit; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		return primes.stream().mapToInt(i -> i).toArray();
		
	}

}
