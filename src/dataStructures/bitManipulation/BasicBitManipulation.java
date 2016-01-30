package dataStructures.bitManipulation;

public class BasicBitManipulation {
	
	public static void main(String[] args) {
		
		byte a = 0b0001_0100;
		System.out.println(getBit(a, 4));
		
	}
	
	static boolean getBit(byte num, int i) {
		return (num & (1 << i)) != 0;
	}

}
