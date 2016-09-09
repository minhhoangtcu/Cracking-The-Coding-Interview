package selfstudy;

import java.util.Scanner;

public class LineIntersection {

	public static void main(String[] args) {

		LineIntersection li = new LineIntersection();

		Scanner sc = new Scanner(System.in);

		while (true) {
			Point[] points = new Point[4];

			for (int i = 0; i < 4; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				points[i] = li.new Point(x, y);
			}

			System.out.println(li.isIntersect(points[0], points[1], points[2], points[3]));
			System.out.println();
		}

	}

	/**
	 * 2 lines intersect if their orientation is different. Also, for special
	 * cases, if they are collinear with each other, and the point of the second
	 * line is within the second line, then they must intersect also.
	 */
	private boolean isIntersect(Point x1, Point y1, Point x2, Point y2) {

		// basic cases
		Orientation x1y1x2 = getOrientation(x1, y1, x2);
		Orientation x1y1y2 = getOrientation(x1, y1, y2);
		if (x1y1x2 != x1y1y2)
			return true;

		Orientation x2y2x1 = getOrientation(x2, y2, x1);
		Orientation x2y2y1 = getOrientation(x2, y2, y1);
		if (x1y1x2 != x1y1y2)
			return true;

		// special cases
		if (x1y1x2 == Orientation.Collinear && isInBetween(x1, x2, y1))
			return true;
		if (x1y1y2 == Orientation.Collinear && isInBetween(x1, y2, y1))
			return true;
		if (x2y2x1 == Orientation.Collinear && isInBetween(x2, x1, y2))
			return true;
		if (x2y2y1 == Orientation.Collinear && isInBetween(x2, y1, y2))
			return true;

		return false;
	}

	/**
	 * Returns true if point b is within a and c. Note that they do not have to be collinear.  
	 */
	private boolean isInBetween(Point a, Point b, Point c) {
		if (b.x <= Math.max(a.x, c.x) && b.x >= Math.min(a.x, c.x) && b.y <= Math.max(a.y, c.y)
				&& b.y >= Math.min(a.y, c.y)) {
			return true;
		} else
			return false;
	}

	private enum Orientation {
		ClockWise, CounterClockWise, Collinear
	}

	private Orientation getOrientation(Point a, Point b, Point c) {
		int slopeAB = getSlope(a, b);
		int slopeBC = getSlope(a, b);
		if (slopeAB < slopeBC)
			return Orientation.CounterClockWise;
		else if (slopeAB > slopeBC)
			return Orientation.ClockWise;
		else
			return Orientation.Collinear;
	}

	private int getSlope(Point a, Point b) {
		return (a.y - b.y) / (a.x - b.x);
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
