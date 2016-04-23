package ctdInterview.arraysAndString;

import java.util.Scanner;

/**
 * A METHOD TO PERFORM BASIC STRING COMPRESSION USING THE COUNTS OF REPEATED
 * CHARACTERS. fOR EXAMPLE, THE STRING AABCCCCCAAA WOULD BECOME A2B1C5A3. IF THE
 * "COMPRESSED" STRING WOULD NOT BECOME SMALLER THAN THE ORIGINAL STRING, YOU
 * METHOD SHOULD RETURN THE ORIGINAL STRING. YOU CAN ASSUME THE STRING HAS ONLY
 * UPPER AND LOWER CASE LETTERS (A-Z).
 * 
 * @author Minh
 *
 */
public class AS15_Compression {
	
	public static void main(String[] args) {
		
		System.out.println(compress("AABCCCCCAAA"));
		System.out.println(compress("abc"));
		System.out.println(compress("aabbcc"));
		System.out.println(compress("aaaaaa"));
		System.out.println(compress("a"));
		System.out.println(compress(""));
		System.out.println(compress("aac"));
		System.out.println(compress("av"));
		
	}
	
	public static String compress(String input) {
		
		// If the length of the word is 2, we cannot make it any smaller. ex: "aa" -> "a2" which is not smaller.
		if (input.length() <= 2)
			return input;
		
		char[] charArray = input.toCharArray();
		int[] count = new int[charArray.length];
		char[] seperatedWords = new char[input.length()];
		int numberOfSeperations = 0;
		
		// Start with the first char in the array
		char lastWordVisisted = charArray[0];
		seperatedWords[numberOfSeperations] = charArray[0];
		count[numberOfSeperations]++;
		
		// Go through the remaining chars in the array.
		for (int i = 0; i < charArray.length; i++) {
			
			// If the visiting char is different from the last char, then there has to be a new separation ( ab )
			if (charArray[i] != lastWordVisisted) {
				numberOfSeperations++;
				seperatedWords[numberOfSeperations] = charArray[i];
				lastWordVisisted = charArray[i];
				count[numberOfSeperations]++;
			} else {
				count[numberOfSeperations]++;
			}
		}
		
		// Go through all the separations we found earlier and turn them into a String
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= numberOfSeperations; i++) {
			sb.append(String.valueOf(seperatedWords[i]));
			sb.append(count[i]);
		}
		
		if (sb.toString().length() < input.length())
			return sb.toString();
		else
			return input;
	}
}
