package leetCode;

/*
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * https://leetcode.com/problems/add-digits/
 * 
 *        26 8421
 * 38 = 0010 0110
 * 
 * 3  = 0000 0011
 * 8  = 0000 1000
 * 3 OR 8 -> 11
 * 
 * 11 = 0000 1011
 * 1  = 0000 0001
 * 1  = 0000 0001
 * 
 * 2  = 0000 0010
 * 
 * 10 -> 1
 * 11 -> 2
 * 12 -> 3
 * 21 -> 3
 * 22 -> 4
 * ...
 * 
 * 1 2 3 4 5 6 7 8 9  10
 * 2 3 4 5 6 7 8 9 10 11
 * 
 */
public class AddDigits {
	
	public static void main(String[] args) {
		System.out.println(getSingleDigit(38));
		System.out.println(getSingleDigit(20));
	}

	private static int getSingleDigit(int num) {
		
		if (num / 10 == 0)
			return num;
		
		int result = 0;
		while (num != 0) {
			result += num % 10;
			num /= 10;
		}
		
		return getSingleDigit(result);
	}
	
}
