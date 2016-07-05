package ctcInterview.moderate;

/**
 * Write an algorithm which computes the number of trailing zeros in n factorial
 * 
 * @author minhhoang
 *
 */
public class M1703_TrailingZero {
	
	public static void main(String[] args) {
		
		M1703_TrailingZero tz = new M1703_TrailingZero();
		
		System.out.println(tz.countTrailingZeros(5));
		System.out.println(tz.countTrailingZeros(10));
		System.out.println(tz.countTrailingZeros(15));
		System.out.println(tz.countTrailingZeros(20));
		System.out.println(tz.countTrailingZeros(200));
		
	}

	/**
	 * Compute the number of trailing zeros in n factorial. For a number to have
	 * a trailing zero, it must have a multiple of 10. We can break down 10 into
	 * 2 and 5. Thus, we can just find the number of times the number can divide
	 * by 5 to get the number of trailing zeros. Note that, for a factorial,
	 * there are always more multiple of 2 than 5.
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int countTrailingZeros(int n) {
		
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			count += getNumOfFiveMultiple(i);
		}
		
		return count;
	}
	
	private int getNumOfFiveMultiple(int n) {
		
		if (n < 0)
			return -1;
		
		int count = 0;
		
		while ((n % 5) == 0) {
			n = n / 5;
			count++;
		}

		return count;
	}

}
