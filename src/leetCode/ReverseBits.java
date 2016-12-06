package leetCode;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits {
	
	public static void main(String[] args) {
		
		ReverseBits rb = new ReverseBits();
		
		System.out.println(Integer.toBinaryString(rb.reverseBits(0b11)));
		System.out.println(rb.reverseBits(43261596));
		System.out.println(Integer.toBinaryString(rb.reverseBits(0b0)));
		System.out.println(Integer.toBinaryString(rb.reverseBits(0b1)));
		System.out.println(Integer.toBinaryString(rb.reverseBits(0b010)));
		
	}

	Map<Byte, Integer> cache = new HashMap<>(); 
	
	// you need treat n as an unsigned value
	public int reverseBits(int n) {

		// split an int into 4 bytes
		int[] reversedBytes = new int[4];
		
		for (int i = 0; i < 4; i++) {
			byte b = (byte) (n >>> i*8);
			reversedBytes[i] = reverseByte(b);
		}

		// merge the bytes together
		int result = 0;
		
		for (int i = 0; i < 4; i++) {
			result += reversedBytes[i];
			if (i < 3) result = result << 8; // shift left only 3 times
		}
		
		return result;
		
	}

	private int reverseByte(byte b) {
	        
	        // Check cache for optimization
	        if (cache.containsKey(b))
	        	return cache.get(b);
		
	        // Reverse
	        int reversed = 0;
	        int i = 0;
	        
	        while (i < 8) {
	            int lsb = (b >>> i) & 0b01;
	            if (i < 7) reversed = (reversed + lsb) << 1; // shift left 7 times only
	            i++;
	        }
	        
	        cache.put(b, reversed);
	        return reversed;
	    }
}