package ctcInterview.recursionAndDynamicProgramming;

import ctcInterview.arraysAndString.MatrixPrinter;

/**
 * Implement the "paint fill" function that one might see on many image editing
 * programs. That is, given a screen (represented by a two-dimensional array of
 * colors), a point, and a new color, fill in the surrounding area until the
 * color changes from the original color
 * 
 * @author minhhoang
 *
 */
public class RD97_PaintFill {
	
	public static void main(String[] args) {
		
		int[][] image = {{1, 1, 2, 3, 1, 2},
						 {1, 1, 1, 2, 3, 2},
						 {1, 2, 2, 2, 2, 3},
						 {4, 5, 2, 2, 1, 1},
						 {4, 1, 1, 1, 3, 3}};
		
		RD97_PaintFill f = new RD97_PaintFill();
		
		System.out.println("<<< BEFORE FILLING >>>");
		MatrixPrinter.print(image);
		
		System.out.println("<<< AFTER FILLING >>>");
		f.fill(image, 2, 9, 2, 2);
		MatrixPrinter.print(image);
	}

	/**
	 * Fill up the image recursively with desired color.
	 * 
	 * @param image
	 * @param x
	 * @param y
	 */
	public void fill(int[][] image, int originColor, int newColor, int x, int y) {

		if (x<0 || y <0 || x >= image.length || y >= image[x].length || image[x][y] == newColor || image[x][y] != originColor)
			return;
		
		image[x][y] = newColor;
		fill(image, originColor, newColor, x, y-1); 	// Up
		fill(image, originColor, newColor, x, y+1); 	// Down
		fill(image, originColor, newColor, x-1, y); 	// Left
		fill(image, originColor, newColor, x+1, y); 	// Right

	}

}
