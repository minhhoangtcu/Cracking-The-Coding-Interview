package hackerrank.contest.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LeonardosPrimeFactor {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int queries = sc.nextInt();

		// Create a sieve for all primes
		int sizeOfSieve = 50000;
		boolean[] isPrimes = new boolean[sizeOfSieve];
		Arrays.fill(isPrimes, Boolean.TRUE);
		ArrayList<Integer> primesList = new ArrayList<>();
		for (int i = 2; i < sizeOfSieve; i++) {
			if (isPrimes[i]) {
				primesList.add(i);
				int runner = i;
				while (runner < sizeOfSieve) {
					isPrimes[runner] = false;
					runner += i;
				}
			}
		}
		Integer[] primes = primesList.toArray(new Integer[primesList.size()]);
		//System.out.println("Finished creating primes seive");

		for (int i = 0; i < queries; i++) {

			long n = sc.nextLong(); // signed int can only contains 2^32-1, which
									// is way way way less than 10^18
			int max = 0;
			for (long j = 1; j <= n; j++) {
				int cMax = getNumOfUniquePrimesFactor(j, primes);
				if (cMax > max)
					max = cMax;
			}

			System.out.println(max);
		}
		
		sc.close();

	}

	private static int getNumOfUniquePrimesFactor(long num, Integer[] primes) {
		int numOfUniquePrimesFactor = 0;
		System.out.printf("%d: ", num);
		for (int i = 0; i < primes.length && num != 1; i++) {
			if (num % primes[i] == 0) {
				numOfUniquePrimesFactor++;
				num = num / primes[i];
				while (num % primes[i] == 0) {
					num = num / primes[i];
				}
				System.out.printf("%d ", primes[i]);
			} else if (primes[i] > num) {
				break;
			}
		}
		System.out.println();
		return numOfUniquePrimesFactor;
	}
}
