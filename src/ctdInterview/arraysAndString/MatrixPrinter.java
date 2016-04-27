package ctdInterview.arraysAndString;

public class MatrixPrinter {
	
	public static void print(int[][] matrix) {
		for (int row = 0; row < matrix[0].length; row++) {
			
			for (int col = 0; col < matrix[0].length; col++) {
				System.out.printf("%2d ", matrix[col][row]);
			}
			System.out.println();
		}
	}

}
