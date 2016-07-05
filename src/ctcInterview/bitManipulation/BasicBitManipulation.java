package ctcInterview.bitManipulation;

public class BasicBitManipulation {
	
	public static void main(String[] args) {
		
		byte a = 0b0001_0100;
		System.out.println(getBit(a, 4));
		
		printByte(setBit(a, 3));
		printByte(setBit(a, 7));
		printByte(clearBit(a, 2));
		printByte(clearBit(a, 7));
		
		byte b = 0b0110_1110;
		printByte(clearBitsMSBThroughI(b, 3));
		printByte(clearBitsIThrough0(b, 3));
		printByte(updateBit(b, false, 3));
		printByte(updateBit(b, true, 0));
		
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
	
	// Clear all bits from the left through i (inclusive)
	static byte clearBitsMSBThroughI(byte num, int i) {
		byte mask = (byte) ((1 << i)-1);
		return (byte) (num & mask);
	}
	
	// Clear all bits from i (inclusive) to 0
	static byte clearBitsIThrough0(byte num, int i) {
		byte mask = (byte) (-1 << (i+1));
		return (byte) (num & mask);
	}
	
	static byte updateBit(byte num, boolean bit, int i) {
		num = clearBit(num, i);
		return (byte) (num | ((bit ? 0b01 : 0b00) << i));
	}

	static void printByte(byte num) {
		System.out.println(Integer.toBinaryString(num & 0xFF));
	}
}
