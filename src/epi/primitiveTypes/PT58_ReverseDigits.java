package epi.primitiveTypes;

/**
 * Write a program which takes an integer and returns the integer corresponding
 * to the digits of the input written in reverse order. For example, the reverse
 * of 42 is 24, and the reverse of -314 is -413.
 */
public class PT58_ReverseDigits {
	
	public static void main(String[] args) {
		
		System.out.println(reverse(1));
		System.out.println(reverse(12));
		System.out.println(reverse(-12));
		System.out.println(reverse(314));
		System.out.println(reverse(1000));
		System.out.println(reverse(2010));
		System.out.println(reverse(-2010));
		
	}
	
	/* 1 -> 1
	 * 12 -> 21
	 * -12 -> -21
	 * 314 -> 413
	 * 1000 -> 1?
	 * 2010 -> 102
	 *
	 * Can we ever have overflow?
	 * > 7.5 billion -> something > 2^31 - 1, which is 2147483647
	 * But, input cannot be larger than an int -> impossible to have overflow
	 */
	public static int reverse(int num) {
		
		int result = 0;
		int numRemaining = Math.abs(num);
		boolean isSigned = num < 0; 
		
		while (numRemaining != 0) {
			result = result * 10 + numRemaining % 10;
			numRemaining /= 10;
		}
		
		return isSigned ? -result : result;
		
	}

}
