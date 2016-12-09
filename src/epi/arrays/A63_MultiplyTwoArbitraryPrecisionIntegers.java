package epi.arrays;

/**
 * Certain applications require arbitrary precision arithmetic. One way to
 * achieve this is to use arrays to represent integers, e.g., with one digit per
 * array entry, with the most significant digit appearing first, and a negative
 * leading digit denoting a negative integer.
 * 
 * Write a program that takes two arrays representing integers, and returns an
 * integer representing their product.
 * 
 * For example:
 * 
 * [1,2,0,0]
 * 
 * [2,3]
 * 
 * -> 3600 + 24000 = 27600
 * 
 * @author minhhoang
 *
 */
public class A63_MultiplyTwoArbitraryPrecisionIntegers {
	
	public static void main(String[] args) {
		
		A63_MultiplyTwoArbitraryPrecisionIntegers multiply = new A63_MultiplyTwoArbitraryPrecisionIntegers();
		
		System.out.println(multiply.multiply(new int[] {1, 2, 0, 0}, new int[] {2, 3}));
		
	}
	
	public long multiply(int[] a, int[] b) {
		
		long result = 0;
		int base = 0;
		
		for (int belowIndex = b.length - 1; belowIndex >= 0; belowIndex--) {
			
			int tempProduct = 0;
			int tempBase = 0;
			int belowNum = b[belowIndex]; // 3
			
			for (int upperIndex = a.length - 1; upperIndex >= 0; upperIndex--) {
				tempProduct += Math.pow(10, tempBase++) * belowNum * a[upperIndex]; // TODO: overflow possible?
			}
			
			tempProduct *= Math.pow(10, base++);
			result += tempProduct;
		}
		
		return result;
		
	}

}
