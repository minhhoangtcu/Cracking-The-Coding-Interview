package hackerrank.contest.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SatisfactoryPairs {

	public static void main(String[] args) {

		// Get input
		int n = 100;
		
		// Get all primes until n
		boolean[] seive = seive(n);

		Map<Integer, Set<Integer>> solutions = new HashMap<>();

		// Find (a, b) such that xa + yb = n for any positive x and y and a < b
		int count = 0;
		for (int a = 1; a <= n / 2; a++) {		// go maximum to /2
			for (int x = 1; x * a <= n; x++) { 	// go maximum to sqrt of n
				
				// If prime -> only 1 b and no y -> we don't need to check for everything
				int primeB = n - x*a;
				if (primeB > a && seive[primeB] && (!solutions.containsKey(a) || !solutions.get(a).contains(primeB))) {
					count++;
					System.out.printf("%d*%d + %d*%d\n", a, x, primeB, 1);
					
					if (!solutions.containsKey(a)) {
						solutions.put(a, new HashSet<Integer>());
					}
					solutions.get(a).add(primeB);
					continue;
				}
				
				// If not prime, we can just count the number of multiples of n - x*a
				for (int b = 1; b*b <= n; b++) {

					// If there exist an integer y -> good solution
					double y = ((double) n - x * a) / ((double) b);
					if (y == Math.floor(y) && !Double.isInfinite(y)) {
						
						int yInt = (int) y;
						
						// possible result can be b itself, y, and b*y
						int[] results = {b, yInt, b*yInt};
//						System.out.printf("> %d %d %d\n", b, yInt, b*yInt);
						
						for (int result: results) {
							if (result <= a)
								continue;
							
							if (!solutions.containsKey(a) || !solutions.get(a).contains(result)) {
								count++;
								System.out.printf("%d*%d + %d*%d\n", a, x, result, yInt);
								if (!solutions.containsKey(a)) {
									solutions.put(a, new HashSet<Integer>());
								}
								solutions.get(a).add(result);
							}
						}
					}
				}
			}
		}

		System.out.println(count);

	}
	
	// Generates all primes from [2, limit]
	private static boolean[] seive(int limit) {
		
		boolean[] isPrime = new boolean[limit + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for (int i = 2; i <= limit; i++) {
			
			if (isPrime[i]) {
				// mark all multiples of a prime
				for (int j = i*2; j <= limit; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		return isPrime;
		
	}

}
