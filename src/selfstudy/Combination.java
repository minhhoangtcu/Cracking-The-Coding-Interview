package selfstudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * return a list of string combinations from a list[list[char]]
 *
 * [a, b, c]
 * [d, e, f]
 * [g, h]
 * 
 * -> [adg, adh, aeg, aeh, afg, afh, bdg, bdh, beg, beh, bfg, bfg, etc.]
 * 
 * - Go through every elements in the list
 *   - Inspect the next list, go through element in the list. Do this, until we are at the ending list.
 *     + Append from the prefix to every element in the ending list.
 * 
 */
public class Combination {
	
	public static void main(String[] args) {
		
		Combination combination = new Combination();
		
		List<Character> l1 = Arrays.asList('a', 'b', 'c');
	    List<Character> l2 = Arrays.asList('d', 'e');
	    List<Character> l3 = Arrays.asList('f', 'g');
	    
	    List<List<Character>> lists = new ArrayList<>();
	    lists.add(l1);
	    lists.add(l2);
	    lists.add(l3);
	    
	    for (String combi: combination.getCombinations(lists)) {
	    	System.out.println(combi);
	    }
		
	}
	
	public List<String> getCombinations(List<List<Character>> allChars) {
		
		List<String> combinations = new LinkedList<>();
		
		addCombinations(combinations, allChars, 0, "");
		
		return combinations;
		
	}
	
	private void addCombinations(List<String> combinations, List<List<Character>> allChars, int depth, String prefix) {
		
		// base case, at last index
		if (depth >= allChars.size() - 1) {
			for (Character c: allChars.get(depth)) {
				combinations.add(prefix + c);
			}
			return;
		}
		
		for (int i = 0; i < allChars.get(depth).size(); i++) {
			addCombinations(combinations, allChars, depth + 1, prefix + allChars.get(depth).get(i));
		}
	}

}
