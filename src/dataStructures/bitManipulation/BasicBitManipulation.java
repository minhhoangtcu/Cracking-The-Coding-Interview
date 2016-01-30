package dataStructures.bitManipulation;

public class BasicBitManipulation {
	
	public static void main(String[] args) {
		
		byte a = 0b0001_0100;
		System.out.println(getBit(a, 4));
		System.out.println(Integer.toBinaryString(setBit(a, 3)));
		System.out.println(Byte.toString(setBit(a, 7)));
	}
	
	static byte setBit(byte num, int i) {
		return (byte) (num | (1 << i));
	}
	
	static boolean getBit(byte num, int i) {
		return (num & (1 << i)) != 0;
	}

}
