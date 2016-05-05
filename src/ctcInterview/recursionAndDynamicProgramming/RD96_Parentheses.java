package ctcInterview.recursionAndDynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to print all valid (e.g., properly opened and closed
 * combinations of n-pairs of parentheses.)
 * 
 * EXAMPLE
 * 
 * Input: 3
 * 
 * Output: ((())), (()()), (())(), ()(()), ()()()
 * 
 * @author minhhoang
 *
 */
public class RD96_Parentheses {

	public static void main(String[] args) {

		RD96_Parentheses p = new RD96_Parentheses();

		System.out.println("<<< GENERATE COMBINATIONS WITH DUPLICATE COMPUTATIONS >>>");
		for (String combi : p.getCombinations(3)) {
			System.out.println(combi);
		}
		
		System.out.println("<<< GENERATE COMBINATIONS BOTTUM UP >>>");
		for (String combi : p.getCombinationsBottomUp(3)) {
			System.out.println(combi);
		}

	}

	/**
	 * Recursively build combinations of parentheses using an array. Each
	 * recursive call will put either left or right parenthesis at an index of
	 * the mentioned array. This way, we can prevent computation time for
	 * duplicate strings.
	 * 
	 */
	public Set<String> getCombinationsBottomUp(int n) {
		
		String[] combi = new String[n*2];
		Set<String> result = new HashSet<>();
		
		addCombinations(result, combi, 0, n, n);
		
		return result;
		
	}

	private void addCombinations(Set<String> combinations, String[] combi, int index, int left, int right) {

		if (index == 0) {
			combi[index++] = "(";
			left--;
			right--; // reserved for last char
		}
		
		// No left and right to insert
		if (index == combi.length-1) {
			combi[index] = ")";
			combinations.add(toStringFromArrays(combi));
			return;
		}

		// Still has left.
		if (left > 0) {
			combi[index] = "(";
			addCombinations(combinations, combi, index + 1, left - 1, right);
		}

		// Still has right.
		if (right > 0) {
			combi[index] = ")";
			addCombinations(combinations, combi, index + 1, left, right - 1);
		}

	}
	
	private String toStringFromArrays(String[] array) {
		String result = "";
		for (String character: array) {
			result += character;
		}
		return result;
	}

	/**
	 * <p>
	 * Base case: ()<br>
	 * For n = 2: ()(), (())<br>
	 * For n = 3: ()()(), (())(), ()(()), (()()), ((()))
	 * </p>
	 * 
	 * <p>
	 * For case n, we add () to each position within a String s in case n-1.
	 * Repeating this process ignoring what we already generated will give us
	 * all valid parentheses.
	 * </p>
	 * 
	 */
	public Set<String> getCombinations(int n) {

		Set<String> result = new HashSet<>();

		if (n < 1)
			return null;
		else if (n == 1) {
			result.add("()");
			return result;
		}

		for (String smaller : getCombinations(n - 1)) {

			for (int i = 0; i < smaller.length(); i++) {

				String newCombi = smaller.substring(0, i) + "()" + smaller.substring(i);
				if (!result.contains(newCombi))
					result.add(newCombi);

			}
		}

		return result;
	}
}
