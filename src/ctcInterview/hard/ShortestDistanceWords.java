package ctcInterview.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * You have a large text file containing words. Given any two words, find the
 * shortest distance (in terms of number of words) between them in the file. If
 * the operation will be repeated many times for the same file (but different
 * pairs of words), can you optimize your solution?
 * 
 * @author minhhoang
 *
 */
public class ShortestDistanceWords {

	public static void main(String[] args) {

		String text = "You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in the file. If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?";
		ShortestDistanceWords sdw = new ShortestDistanceWords(text);
		
		System.out.println(sdw.minDistance("you", "a"));
		System.out.println(sdw.minDistance("large", "a"));
		System.out.println(sdw.minDistance("have", "words."));

	}

	String text;
	Hashtable<String, ArrayList<Integer>> wordPositions;

	public ShortestDistanceWords(String text) {
		this.text = text;
		wordPositions = new Hashtable<>();
		processWords();
	}

	private void processWords() {
		String[] words = text.split(" ");
		int pos = 0;

		for (String w : words) {
			w = w.toLowerCase();
			if (!wordPositions.containsKey(w)) {
				wordPositions.put(w, new ArrayList<Integer>());
			}
			wordPositions.get(w).add(pos);
			pos++;
		}
	}

	public int minDistance(String a, String b) {
		// assume both a and b are within text

		a = a.toLowerCase();
		b = b.toLowerCase();
		
		ArrayList<Integer> posOfA = wordPositions.get(a);
		ArrayList<Integer> posOfB = wordPositions.get(b);
		return getMinDistanceBetweenTwoList(posOfA, posOfB);

	}

	private int getMinDistanceBetweenTwoList(ArrayList<Integer> posOfA, ArrayList<Integer> posOfB) {
		ArrayList<Integer> merged = new ArrayList<>();
		merged.addAll(posOfA);
		merged.addAll(posOfB);
		Collections.sort(merged);

		for (int i = 1; i < merged.size(); i++) {
			int current = merged.get(i);
			int previous = merged.get(i - 1);
			boolean isCurrentInA = posOfA.contains(current);
			boolean isPreviousInA = posOfA.contains(previous);
			if (isCurrentInA ^ isPreviousInA) {
				return current - previous - 1;
			}
		}

		return -1;
	}

}
