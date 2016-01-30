package dataStructures.bitManipulation;

public class BasicBitManipulation {
	
	public static void main(String[] args) {
		
		byte a = 0b0001_0100;
		System.out.println(getBit(a, 4));
		
		printByte(setBit(a, 3));
		printByte(setBit(a, 7));
		printByte(clearBit(a, 2));
		printByte(clearBit(a, 7));
	}
	
	static byte setBit(byte num, int i) {
		return (byte) (num | (1 << i));
	}
	
	static byte clearBit(byte num, int i) {
		byte mask = (byte) ~(1 << i);
		return (byte) (num & mask);
	}
	
	static boolean getBit(byte num, int i) {
		return (num & (1 << i)) != 0;
	}

	static void printByte(byte num) {
		System.out.println(Integer.toBinaryString(num & 0xFF));
	}
}
