package ctcInterview.sortingAndSearching;

/**
 * Given an MxN matrix in which each row and each column is sorted in ascending
 * order, write a method to find an element.
 * 
 * @author minhhoang
 *
 */
public class SS1106_MatrixSearch {

	public static void main(String[] args) {

		SS1106_MatrixSearch ms = new SS1106_MatrixSearch();

		System.out.println("<<< 3x3 MATRIX TEST >>>");
		int[][] matrix = { { 1, 2, 2 }, { 3, 3, 4 }, { 5, 8, 10 } };

		System.out.println(ms.find(matrix, 1));
		System.out.println(ms.find(matrix, 4));
		System.out.println(ms.find(matrix, 10));

		System.out.println("<<< 5x5 MATRIX TEST >>>");
		int[][] matrix2 = { { 0, 2, 5, 7, 9 }, { 1, 3, 6, 7, 9 }, { 3, 5, 6, 7, 11 }, { 4, 7, 7, 9, 19 },
				{ 5, 8, 8, 11, 20 } };

		System.out.println(ms.find(matrix2, 0));
		System.out.println(ms.find(matrix2, 9));
		System.out.println(ms.find(matrix2, 5));
		System.out.println(ms.find(matrix2, 20));
		System.out.println(ms.find(matrix2, 11));
		System.out.println(ms.find(matrix2, 19));
		System.out.println(ms.find(matrix2, 3));
		System.out.println(ms.find(matrix2, 8));
		System.out.println(ms.find(matrix2, 100));
		System.out.println(ms.find(matrix2, -1));
		
	}

	/**
	 * 1 3 5
	 * 
	 * 2 3 8
	 * 
	 * 2 4 10
	 * 
	 */

	public Position find(int[][] matrix, int key) {
		return findHelper(matrix, key, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}

	private Position findHelper(int[][] matrix, int key, int left, int right, int top, int down) {

		if ((left > right) || (top > down))
			return null;

		// Divide similarly to binary search
		int midX = (left + right) / 2;
		int midY = (top + down) / 2;

		// If the problem is shorten to just a col or row, we do simple binary
		// search instead.
		if (left == right) {
			if (key == matrix[left][midY])
				return new Position(left, midY);
			else if (key < matrix[left][midY])
				return findHelper(matrix, key, left, right, top, midY - 1);
			else
				return findHelper(matrix, key, left, right, midY + 1, down);
		} else if (top == down) {
			if (key == matrix[midX][top])
				return new Position(midX, top);
			else if (key < matrix[midX][top])
				return findHelper(matrix, key, left, midX - 1, top, down);
			else
				return findHelper(matrix, key, midX + 1, right, top, down);
		}

		int endQuad1 = matrix[midX][midY];
		int startQuad4 = matrix[midX + 1][midY + 1];

		// Found the key
		if (key == endQuad1)
			return new Position(midX, midY);
		else if (key == startQuad4)
			return new Position(midX + 1, midY + 1);

		Position keyInQuad1, keyInQuad2, keyInQuad3, keyInQuad4;

		// TODO: fix redundant logic
		if (key > endQuad1 && key < startQuad4) {
			// If key is bigger than the max number in quad 1 -> it cannot be
			// inside quad 1. Also, if key is smaller than smallest key in quad
			// 4 -> it cannot be inside quad 4
			keyInQuad2 = findHelper(matrix, key, midX + 1, right, top, midY);
			if (keyInQuad2 != null)
				return keyInQuad2;
			
			keyInQuad3 = findHelper(matrix, key, left, midX, midY + 1, down);
			if (keyInQuad3 != null)
				return keyInQuad3;
			
		} else if (key < startQuad4) {
			keyInQuad1 = findHelper(matrix, key, left, midX, top, midY);
			if (keyInQuad1 != null)
				return keyInQuad1;
			
			keyInQuad2 = findHelper(matrix, key, midX + 1, right, top, midY);
			if (keyInQuad2 != null)
				return keyInQuad2;
			
			keyInQuad3 = findHelper(matrix, key, left, midX, midY + 1, down);
			if (keyInQuad3 != null)
				return keyInQuad3;
			
		} else if (key > endQuad1) {
			keyInQuad2 = findHelper(matrix, key, midX + 1, right, top, midY);
			if (keyInQuad2 != null)
				return keyInQuad2;
			
			keyInQuad3 = findHelper(matrix, key, left, midX, midY + 1, down);
			if (keyInQuad3 != null)
				return keyInQuad3;
			
			keyInQuad4 = findHelper(matrix, key, midX + 1, right, midY + 1, down);
			if (keyInQuad4 != null)
				return keyInQuad4;
		}
		
		return null;

	}

	class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("x: %d\ty: %d", x, y);
		}
	}

}
