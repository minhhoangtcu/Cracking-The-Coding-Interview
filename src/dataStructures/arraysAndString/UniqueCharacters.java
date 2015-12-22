package dataStructures.arraysAndString;

/**
 * An algorithm to determine if a string has all unique characters without
 * additional data structures.
 * 
 * @author Minh Hoang
 */
public class UniqueCharacters {

	/**
	 * Return true if the provided string has all unique characters by using an
	 * array as a filter. Assuming that the input String is in ASCII. This is similar to a hashing to a table.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isUnique(String input) {
		if (!isASCII(input))
			throw new IllegalArgumentException("This method only handle input in ASCII format");
		else if (input.length() > 128)
			return false;
		else {
			boolean[] filter = new boolean[128];
			for (int i = 0; i < input.length(); i++) {
				byte index = (byte) input.charAt(i);
				if (filter[index] == true)
					return false;
				else
					filter[index] = true;
			}
			return true;
		}
	}

	public static boolean isASCII(String input) {
		boolean isCorrect = true;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) > 128)
				isCorrect = false;
		}
		return isCorrect;
	}
	
	public static void test(boolean expected, String input) {
		try {
			if (isUnique(input) == expected)
				System.out.println("Test correct!");
			else
				System.out.println(
						"Incorrect for " + input + "\tExpected: " + expected + "\tActual: " + isUnique(input));
		} catch (IllegalArgumentException e) {
			System.err.println("The input " + input + " is not in ASCII");
		}
	}
	
	public static void main(String[] args) {
		test(true, "TCU");
		test(false, "TCC");
		test(false, "TTTTTTTT");
		test(true, "asdfwqety");
		test(false, "tobeornottobe");
		test(false, "  ");
		test(true, "");
		test(true, "!@#$%^&*()-=");
		test(false, "𐌼𐌰𐌲");
	}
}
