package ctcInterview.sortingAndSearching;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Write a method to sort an array of strings so that all the anagrams are next
 * to each other.
 * 
 * @author minhhoang
 *
 */
public class SS1102_AnagramsSort {
	
	public static void main(String[] args) {
		
		SS1102_AnagramsSort s = new SS1102_AnagramsSort();
		
		String[] input = {"ice", "pow", "iec", "ab", "bc", "ba", "bucket", "ba", "wop"};
		s.sort(input);
		System.out.println(Arrays.toString(input));
		
	}

	/**
	 * Two anagrams are the same when they are sorted -> We can use bucket sort
	 * to sort the strings.
	 */
	
	public void sort(String[] input) {
		
		Hashtable<String, LinkedList<String>> buckets = new Hashtable<>();
		
		// Put the strings into buckets
		for (String text: input) {
			String key = sortChars(text);
			if (!buckets.containsKey(key))
				buckets.put(key, new LinkedList<String>());
			buckets.get(key).add(text);
		}
		
		int i = 0;
		// Convert the buckets back into an array
		for (LinkedList<String> anagrams: buckets.values()) {
			for (String text: anagrams) {
				input[i++] = text;
			}
		}
		
	}
	
	private String sortChars(String input) {
		char[] splitted = input.toCharArray();
		Arrays.sort(splitted);
		return new String(splitted);
	}

}
