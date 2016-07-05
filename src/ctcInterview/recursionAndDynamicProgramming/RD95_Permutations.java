package ctcInterview.recursionAndDynamicProgramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Compute all permutations of a string of unique characters
 * 
 * @author minhhoang
 *
 */
public class RD95_Permutations {
	
	public static void main(String[] args) {
		
		RD95_Permutations p = new RD95_Permutations();
		
		String input = "1234";
		
		int i = 0;
		for (String permu: p.getPermutations(input)) {
			System.out.printf("%d: %s\n", ++i, permu);
		}
		
	}
	
	public Set<String> getPermutations(String input) {

		Set<String> result = new HashSet<>();
		
		if (input.length() <= 1) {
			result.add(input);
			return result;
		}
		
		char firstChar = input.charAt(0);
		for (String smallerPermu: getPermutations(input.substring(1))) {
			for (int index = 0; index < input.length(); index++) {
				result.add(smallerPermu.substring(0, index) + firstChar + smallerPermu.substring(index, smallerPermu.length()));
			}
		}
		
		return result;
	}
}
