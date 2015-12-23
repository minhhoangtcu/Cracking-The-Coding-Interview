package dataStructures.arraysAndString;

import java.util.Arrays;
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
	public static boolean isPermutationHashTable(String a, String b) {
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
	
	public static boolean isPermutationSort(String a, String b) {
		if (a.length() != b.length())
			return false;
		else {
			return (sort(a).equals(sort(b)));
		}
	}
	
	public static boolean isPermutationArrayHash(String a, String b) {
		if (a.length() != b.length())
			return false;
		else {
			byte[] filter = new byte[128];
			
			for (int i = 0; i < a.length(); i++) {
				byte c = (byte) a.charAt(i);
				filter[c]++;
			}
			
			for (int i = 0; i < b.length(); i++) {
				byte c = (byte) b.charAt(i);
				filter[c]--;
				if (filter[c] < 0)
					return false;
			}
			return true;
		}
	}
	
	public static String sort(String input) {
		char[] sorting = input.toCharArray();
		Arrays.sort(sorting);
		return new String(sorting);
	}

	public static void testHashTable(boolean expected, String a, String b) {
		if (isPermutationHashTable(a, b) == expected)
			System.out.println("Test correct!");
		else
			System.out.println("Incorrect for " + a + " and " + b + "\tExpected: " + expected + "\tActual: "
					+ isPermutationHashTable(a, b));
	}
	
	public static void testSort(boolean expected, String a, String b) {
		if (isPermutationSort(a, b) == expected)
			System.out.println("Test correct!");
		else
			System.out.println("Incorrect for " + a + " and " + b + "\tExpected: " + expected + "\tActual: "
					+ isPermutationSort(a, b));
	}
	
	public static void testArrayHash(boolean expected, String a, String b) {
		if (isPermutationArrayHash(a, b) == expected)
			System.out.println("Test correct!");
		else
			System.out.println("Incorrect for " + a + " and " + b + "\tExpected: " + expected + "\tActual: "
					+ isPermutationArrayHash(a, b));
	}

	public static void main(String[] args) {
		System.out.println("Check hash tables algorithm");
		testHashTable(false, "a", "b");
		testHashTable(true, "a", "a");
		testHashTable(false, "a", "ba");
		testHashTable(true, "aaaa", "aaaa");
		testHashTable(true, "abba", "bbaa");
		testHashTable(true, "to be or not to be", "not to be or be to");
		testHashTable(false, "123", "asd");
		testHashTable(true, "", "");
		testHashTable(true, "12345", "12345");
		System.out.println();
		
		System.out.println("Check sorting algorithm");
		testSort(false, "a", "b");
		testSort(true, "a", "a");
		testSort(false, "a", "ba");
		testSort(true, "aaaa", "aaaa");
		testSort(true, "abba", "bbaa");
		testSort(true, "to be or not to be", "not to be or be to");
		testSort(false, "123", "asd");
		testSort(true, "", "");
		testSort(true, "12345", "12345");
		System.out.println();
		
		System.out.println("Check array hash algorithm");
		testArrayHash(false, "a", "b");
		testArrayHash(true, "a", "a");
		testArrayHash(false, "a", "ba");
		testArrayHash(true, "aaaa", "aaaa");
		testArrayHash(true, "abba", "bbaa");
		testArrayHash(true, "to be or not to be", "not to be or be to");
		testArrayHash(false, "123", "asd");
		testArrayHash(true, "", "");
		testArrayHash(true, "12345", "12345");
		System.out.println();
	}
}
