package ctcInterview.recursionAndDynamicProgramming;

import java.util.LinkedList;

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

//		t.start();
//		System.out.println(countNumberOfPaths(0, 0, x = 10, y = 10));
//		t.stopAndPrint();
//
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
		System.out.println("<<< FIND PATH 3x3 >>>");
		int[][] map = new int[x = 3][y = 3];
		map[1][1] = OFFLIMIT; // off-limit
//		System.out.println(countNumberOfPathsDynamically(paths, 0, 0, x - 1, y - 1));
		
		RD92_Robot robot = new RD92_Robot();
		
		LinkedList<Position> path = robot.findPath(map, x-1, y-1);
		
		if (path != null) {
			for (Position position : path) {
				System.out.printf("x: %d\ty: %d\n", position.x, position.y);
			}
		}
		
		System.out.println("<<< FIND PATH 5x4 >>>");
		map = new int[x=5][y=4];
		map[2][0] = OFFLIMIT;
		map[2][1] = OFFLIMIT;
		map[4][2] = OFFLIMIT;
		
		path = robot.findPath(map, x-1, y-1);
		
		if (path != null) {
			for (Position position : path) {
				System.out.printf("x: %d\ty: %d\n", position.x, position.y);
			}
		}
		
	}

	/**
	 * Find a path for the rebot from the top left to the bottom right.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @return
	 */
	public LinkedList<Position> findPath(int[][] map, int x, int y) {
		LinkedList<Position> path = new LinkedList<>();
		findPathHelper(map, 0, 0, x, y, path);
		return path;
	}

	private static int VISITED = 1;
	private static int OFFLIMIT = -1;

	private boolean findPathHelper(int[][] map, int currentX, int currentY, int x, int y,
			LinkedList<Position> path) {

		// got to the position
		if (currentX == x && currentY == y) {
			path.add(new Position(currentX, currentY));
			return true;
		}
			

		// off range
		if (currentX > x || currentY > y)
			return false;

		// Go either right or down, if from here we can reach the end, add the position to path.
		if (currentX+1 <= x && map[currentX + 1][currentY] != OFFLIMIT && map[currentX + 1][currentY] != VISITED
				&& findPathHelper(map, currentX + 1, currentY, x, y, path)) {
			path.add(new Position(currentX, currentY));
		} else if (currentY <= y && map[currentX][currentY + 1] != OFFLIMIT && map[currentX][currentY + 1] != VISITED
				&& findPathHelper(map, currentX, currentY + 1, x, y, path)) {
			path.add(new Position(currentX, currentY));
		}

		return !path.isEmpty();
		
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

		if (currentX == x && currentY == y)
			return 1;

		// Because we cannot go to left, if current X > X, then there is no way
		// after this one. Same for going up with Y.
		if (currentX > x || currentY > y)
			return 0;

		if (computedPaths[currentX + 1][currentY] != OFFLIMIT)
			computedPaths[currentX][currentY] += countNumberOfPathsDynamically(computedPaths, currentX + 1, currentY, x,
					y);
		if (computedPaths[currentX][currentY + 1] != OFFLIMIT)
			computedPaths[currentX][currentY] += countNumberOfPathsDynamically(computedPaths, currentX, currentY + 1, x,
					y);

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

	class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
