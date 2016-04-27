package ctdInterview.arraysAndString;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in
 * place?
 * 
 * @author minhhoang
 *
 */
public class AS16_Rotate {
	
	public static void main(String[] args) {
		
		int[][] matrix = {{1, 2, 3},
						  {4, 5, 6},
						  {7, 8, 9}};
		
		System.out.println("<<< BEFORE ROTATING >>>");
		MatrixPrinter.print(matrix);
		
		rotate(matrix, 2, 0);
		
		System.out.println("<<< AFTER ROTATING >>>");
		MatrixPrinter.print(matrix);
		
		int[][] bigMatrix = {{1, 2, 3, 4, 5, 6},
							 {7, 8, 9, 10, 11, 12},
							 {13, 14, 15, 16, 17, 18},
							 {19, 20, 21, 22, 23, 24},
							 {25, 26, 27, 28, 29, 30},
							 {31, 32, 33, 34, 35, 36}};
		
		System.out.println("<<< BEFORE ROTATING >>>");
		MatrixPrinter.print(bigMatrix);
		
		rotate(bigMatrix, 5, 0);
		
		System.out.println("<<< AFTER ROTATING >>>");
		MatrixPrinter.print(bigMatrix);
		
	}
	
	public static void rotate(int[][] image, int length, int depth) {
		
		if (length < 1)
			return;
		
		int n = image[0].length-1; // last index
		int start = depth+1;
		int end = start+length-1;
		
		for (int i = start; i <= end; i++) {
			int top = image[i][depth]; 						// [1][0] [2][0] ... [5][0] --> [2][1] [3][1] [4][1]
			image[i][depth] = image[depth][n-i];			// [0][4] [0][3] ... [0][2] --> [1][3] [1][2] [1][1]
			image[depth][n-i] = image[n-i][n-depth];		// [4][5] [3][5] ... [1][5] --> [3][4] [2][4] [1][4]
			image[n-i][n-depth] = image[n-depth][i];		// [5][1] [5][2] ... [5][4] --> [4][2] [4][3] [4][4]
			image[n-depth][i] = top;
		}
		
		rotate(image, length-2, depth+1);
		
	}
	
	

}
