package ctcInterview.hard;

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
		
		System.out.println(countBruteForce(2));
		System.out.println(countBruteForce(10));
		System.out.println(countBruteForce(12));
		System.out.println(countBruteForce(20));
		System.out.println(countBruteForce(30));
		
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
