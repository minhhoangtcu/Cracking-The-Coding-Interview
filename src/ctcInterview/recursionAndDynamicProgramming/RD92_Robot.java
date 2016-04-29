package ctcInterview.recursionAndDynamicProgramming;

/**
 * Imagine a robot sitting on the upper left corner of an X by Y grid. The robot
 * can only move in two directions: right and down. How many possible paths are
 * there for the robot to go from (0, 0) to (X, Y)?
 * 
 * FOLLOW UP
 * 
 * Imagine certain spots are "off limits," such that the robot cannot step on
 * them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 * 
 * @author minhhoang
 *
 */
public class RD92_Robot {

	public static void main(String[] args) {

		// NORMAL VERSION
		int x, y;
		Timer t = new Timer();

		t.start();
		System.out.println(countNumberOfPaths(0, 0, x = 10, y = 10));
		t.stopAndPrint();

		int[][] paths = new int[x = 10][y = 10];
		t.start();
		System.out.println(countNumberOfPathsDynamically(paths, 0, 0, x - 1, y - 1));
		t.stopAndPrint();

		// Time consuming version
		// paths = new int[x=20][y=20];
		// t.start();
		// System.out.println(countNumberOfPathsDynamically(paths, 0, 0, x-1,
		// y-1));
		// t.stopAndPrint();

		// FOLLOW UP VERSION
		paths = new int[x = 3][y = 3];
		paths[1][1] = -1; // off-limit
		System.out.println(countNumberOfPathsDynamically(paths, 0, 0, x - 1, y - 1));
	}

	/**
	 * Count the number of paths from (currentX, currentY) to (x, y). Also,
	 * assign the number of paths from to the array for each (currentX,
	 * currentY). 
	 * 
	 * @param computedPaths
	 * @param currentX
	 * @param currentY
	 * @param x
	 * @param y
	 * @return
	 */
	public static int countNumberOfPathsDynamically(int[][] computedPaths, int currentX, int currentY, int x, int y) {

		// Computed position
		if (computedPaths[currentX][currentY] > 0)
			return computedPaths[currentX][currentY];

		// Found the position, returning 1 path. Or if it reaches the end of row
		// of the end of a col, then there is only 1 possible way
		if (currentX == x || currentY == y)
			return 1;

		// Because we cannot go to left, if current X > X, then there is no way
		// after this one. Same for going up with Y.
		if (currentX > x || currentY > y)
			return 0;

		if (computedPaths[currentX + 1][currentY] != -1)
			computedPaths[currentX][currentY] += countNumberOfPathsDynamically(computedPaths, currentX + 1, currentY, x, y);
		if (computedPaths[currentX][currentY + 1] != -1)
			computedPaths[currentX][currentY] += countNumberOfPathsDynamically(computedPaths, currentX, currentY + 1, x, y);

		return computedPaths[currentX][currentY];
	}

	public static int countNumberOfPaths(int currentX, int currentY, int x, int y) {

		// Found the position, returning 1 path.
		if (currentX == x && currentY == y)
			return 1;

		// Because we cannot go to left, if current X > X, then there is no way
		// after this one. Same for going up with Y.
		if (currentX > x || currentY > y)
			return 0;

		int numberOfPaths = countNumberOfPaths(currentX + 1, currentY, x, y)
				+ countNumberOfPaths(currentX, currentY + 1, x, y);

		return numberOfPaths;
	}
}
