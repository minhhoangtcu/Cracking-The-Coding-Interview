package ctcInterview.moderate;

/**
 * Write a method which finds the maximum of two numbers. You should not use
 * if-else or any other comparison operator.
 * 
 * @author minhhoang
 *
 */
public class M1704_Max {
	
	public static void main(String[] args) {
		
		M1704_Max m = new M1704_Max();
		
		System.out.println(m.max(1, 5));
		System.out.println(m.max(5, 1));
		System.out.println(m.max(5, 5));
		System.out.println(m.max(10, 5));
		System.out.println(m.max(-1, 5));
		
	}
	
	// TODO: 
	public int max(int a, int b) {
		int sign = a - b; // 1 will be at most significant bit
		int negMask = sign >> 31;
		/*
		 * If a >= b then negMask is all 0s -> return a & ~negMask
		 * If b > a then negMask is all 1s  -> return b & negMask 
		 */
		return (a & ~negMask) | (b & negMask); 
	}

}
