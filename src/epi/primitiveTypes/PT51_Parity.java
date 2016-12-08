package epi.primitiveTypes;

import java.util.HashMap;
import java.util.Map;

/*
 * How to compute the parity of a very large number of 64-bit words?
 * 
 * A word is parity when there are odd number of 1s.
 * 
 */

public class PT51_Parity {
	
	public static void main(String[] args) {
		
		PT51_Parity pp = new PT51_Parity();
		int num = 0;
		
		System.out.println("\n>> Test Optimized Parity\n");
		
		System.out.println("For num = " + (num = 1) + "\tExpect: true\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = 0) + "\tExpect: false\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = 8) + "\tExpect: true\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = 9) + "\tExpect: false\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = 10) + "\tExpect: false\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = 11) + "\tExpect: true\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = -1) + "\tExpect: false\tActual: " + pp.isParity(num));
		System.out.println("For num = " + (num = -2) + "\tExpect: true\tActual: " + pp.isParity(num));
		
		System.out.println("\n>> Test Parity with Cache\n");
		
		System.out.println("For num = " + (num = 1) + "\tExpect: true\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = 0) + "\tExpect: false\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = 8) + "\tExpect: true\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = 9) + "\tExpect: false\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = 10) + "\tExpect: false\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = 11) + "\tExpect: true\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = -1) + "\tExpect: false\tActual: " + pp.isParityWithCache(num));
		System.out.println("For num = " + (num = -2) + "\tExpect: true\tActual: " + pp.isParityWithCache(num));
		
	}
	
	Map<Integer, Boolean> byteParityMap = new HashMap<>();

	public boolean isParityWithCache(int num) {
		
		int numOfBitsToXOR = 16;
		int numOfBitsToStop = 4;
		int numToStore = 0;
		
		// XOR a number with half of itself until we get down to only 1 bit
		while (numOfBitsToXOR > 0 && num != 0) {
			int msb = num >>> numOfBitsToXOR;
			
			int mask = (1 << numOfBitsToXOR) - 1;
			int lsb = num & mask;
			
			num = msb ^ lsb;
			numOfBitsToXOR /= 2;
			
			// If the cache has pre-calculated result, we just need to return 
			if (numOfBitsToXOR == numOfBitsToStop) {
				Boolean isParity = byteParityMap.get(num);
				if (isParity != null) return isParity;
			} else {
				numToStore = num;
			}
		}
		
		boolean isParity = num == 1;
		byteParityMap.put(numToStore, isParity);
		return isParity;
		
	}
	
	/*
	 * If we split a 8-bit number in half, the parity of that number is the same
	 * as the XOR of the 2 halfs. (most and least significant side of the
	 * number).
	 * 
	 * This algorithm takes logn time for n be the length of the number
	 */
	public boolean isParity(int num) {
		
		int numOfBitsToXOR = 16;
		
		// XOR a number with half of itself until we get down to only 1 bit
		while (numOfBitsToXOR > 0 && num != 0) {
			int msb = num >>> numOfBitsToXOR;
			
			int mask = (1 << numOfBitsToXOR) - 1;
			int lsb = num & mask;
			
			num = msb ^ lsb;
			numOfBitsToXOR /= 2;
		}
		
		return num == 1;
	}

}
