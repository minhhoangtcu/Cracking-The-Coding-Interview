package epi.primitiveTypes;

/*
 * Swap 2 specified bits of a 64 int
 */
public class PT52_SwapBits {
	
	// lsb is at 0 and msb is at 63.
	public long swap(long num, int i1, int i2) {
		
		long bitAtI1 = (num >>> i1) & 1;
		long bitAtI2 = (num >>> i2) & 1;
		
		if (bitAtI1 != bitAtI2) {
			// 0 XOR a will not change a. However, 1 XOR a will toggle a.
			long mask = (1L << i1) | (1L << i2);
			num ^= mask;
		}
		
		return num;
	}
}
