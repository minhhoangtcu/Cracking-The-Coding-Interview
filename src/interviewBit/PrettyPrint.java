package interviewBit;

import java.util.ArrayList;

/**
 * Print concentric rectangular pattern in a 2d matrix. Let us show you some
 * examples to clarify what we mean.
 * 
 * Example 1:
 * 
 * Input: A = 4. Output:
 * 
 * 4 4 4 4 4 4 4
 * 4 3 3 3 3 3 4
 * 4 3 2 2 2 3 4 
 * 4 3 2 1 2 3 4 
 * 4 3 2 2 2 3 4 
 * 4 3 3 3 3 3 4 
 * 4 4 4 4 4 4 4 
 * 
 * Example 2:
 * 
 * Input: A = 3. Output:
 * 
 * 3 3 3 3 3 
 * 3 2 2 2 3 
 * 3 2 1 2 3 
 * 3 2 2 2 3 
 * 3 3 3 3 3 
 * 
 * The outermost rectangle is
 * formed by A, then the next outermost is formed by A-1 and so on.
 * 
 * You will be given A as an argument to the function you need to implement, and
 * you need to return a 2D array.
 * 
 * https://www.interviewbit.com/courses/programming/topics/arrays/problems/
 * prettyprint/
 * 
 * @author Minh
 *
 */
public class PrettyPrint {

	public ArrayList<ArrayList<Integer>> prettyPrint(int a) {

		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		int edge = a * 2 - 1;
		int lastRow = edge - 1;

		for (int row = 0; row < edge; row++) {
			ArrayList<Integer> newRow = new ArrayList<>();

			int middle = a - 1;
			int reduce = 0;
			int maxReduce;
			int colToIncrease;

			if (row <= middle) {
				maxReduce = row;
				colToIncrease = lastRow - row;
			} else {
				maxReduce = lastRow - row;
				colToIncrease = row;
			}

			for (int col = 0; col < edge; col++) {

				newRow.add(a - reduce);

				if (col <= middle && reduce < maxReduce) {
					reduce++;
				} else if (col >= middle && col >= colToIncrease && reduce > 0) {
					reduce--;
				}
			}

			output.add(newRow);
		}

		return output;

	}

}
