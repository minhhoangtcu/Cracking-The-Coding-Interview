package dataStructures.arraysAndString;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * A method to decide if one string is a permutation of the other.
 * 
 * @author Minh Hoang
 *
 */
public class PermutationStrings {

	/**
	 * Return true if a string is a permutation of another. This method assumes
	 * that white space is important and case sensitive
	 * 
	 * @param a
	 *            the first string
	 * @param b
	 *            the second string
	 * @return true if a is a permutation of b, false if otherwise
	 */
	public static boolean isPermutation(String a, String b) {
		if (a.length() != b.length())
			return false;
		else {
			HashMap<Character, Byte> aTable = new HashMap<Character, Byte>(a.length() / 2, 0.5f);
			HashMap<Character, Byte> bTable = new HashMap<Character, Byte>(b.length() / 2, 0.5f);
			for (int i = 0; i < a.length(); i++) {
				char current = a.charAt(i);
				if (aTable.containsKey(current)) {
					byte count = aTable.get(current);
					count++;
					aTable.put(current, count);
				} else {
					aTable.put(current, (byte) 1);
				}
			}

			for (int i = 0; i < b.length(); i++) {
				char current = b.charAt(i);
				if (!aTable.containsKey(current))
					return false;
				else if (bTable.containsKey(current)) {
					byte count = bTable.get(current);
					count++;
					bTable.put(current, count);
				} else {
					bTable.put(current, (byte) 1);
				}
			}

			for (Entry<Character, Byte> set : aTable.entrySet())
				if (set.getValue() != bTable.get(set.getKey()))
					return false;

			return true;
		}
	}

	public static void test(boolean expected, String a, String b) {
		if (isPermutation(a, b) == expected)
			System.out.println("Test correct!");
		else
			System.out.println("Incorrect for " + a + " and " + b + "\tExpected: " + expected + "\tActual: "
					+ isPermutation(a, b));
	}

	public static void main(String[] args) {
		test(false, "a", "b");
		test(true, "a", "a");
		test(false, "a", "ba");
		test(true, "aaaa", "aaaa");
		test(true, "abba", "bbaa");
		test(true, "to be or not to be", "not to be or be to");
		test(false, "123", "asd");
		test(true, "", "");
		test(true, "12345", "12345");
	}
}
