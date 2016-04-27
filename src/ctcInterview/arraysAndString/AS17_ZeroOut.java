package ctcInterview.arraysAndString;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire
 * row and column are set to 0.
 * 
 * @author minhhoang
 *
 */
public class AS17_ZeroOut {
	
	public static void main(String[] args) {
		
		int[][] matrix = {{1, 1, 1, 1, 1},
						  {1, 0, 1, 1, 1},
						  {1, 1, 1, 1, 1},
						  {1, 1, 1, 0, 1},
						  {0, 1, 1, 1, 1}};
		
		System.out.println("<<< BEFORE ZERO-ING OUT >>>");
		MatrixPrinter.print(matrix);
		
		zeroOut(matrix);
		
		System.out.println("<<< AFTER ZERO-ING OUT >>>");
		MatrixPrinter.print(matrix);
		
	}
	
	
	
	public static void zeroOut(int[][] matrix) {
		Set<Integer> zeroCols = new HashSet<>();
		Set<Integer> zeroRows = new HashSet<>();
		
		for (int col = 0; col < matrix.length; col++) {
			for (int row = 0; row < matrix[col].length; row++) {
				if (matrix[col][row] == 0) {
					zeroCols.add(col);
					zeroRows.add(row);
				}
			}
		}
		
		for (int col: zeroCols) {
			for(int i = 0; i < matrix[col].length; i++) {
				matrix[col][i] = 0;
			}
		}
		
		for (int row: zeroRows) {
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][row] = 0;
			}
		}
	}

}
