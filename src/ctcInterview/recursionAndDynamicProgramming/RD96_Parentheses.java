package ctcInterview.recursionAndDynamicProgramming;

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

		for (String combi : p.getCombinations(4)) {
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
	//TODO: Implement the algorithm.

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
