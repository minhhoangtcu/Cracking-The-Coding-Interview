package ctcInterview.hard;

/**
 * Write a function that adds two numbers. You should not use + or any
 * arithmetic operators.
 * 
 * @author minhhoang
 *
 */
public class SumWithoutArithmetic {
	
	public static void main(String[] args) {
		SumWithoutArithmetic swa = new SumWithoutArithmetic();
		System.out.println(swa.sum(5, 5));
		System.out.println(swa.sum(10, 15));
		System.out.println(swa.sum(0, 0));
		System.out.println(swa.sum(12, 3));
		System.out.println(swa.sum(0, 1000));
	}

	/**
	 * We need to separate the addition and carry parts. In binary system,
	 * addition without carry is similar to XOR. And, carry without addition is
	 * similar to AND, shifted left by 1.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int sum(int a, int b) {
		
		if (b == 0)
			return a;
		else if (a == 0)
			return b;
		
		int addition = a ^ b; // addition bits
		int carry = (a & b) << 1; // carry bits
		
		return sum(addition, carry);
	}

}
