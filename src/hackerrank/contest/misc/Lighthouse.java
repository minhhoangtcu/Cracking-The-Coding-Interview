package hackerrank.contest.misc;

public class Lighthouse {

	public static void main(String[] args) {

		Lighthouse lh = new Lighthouse();

		lh.drawCircle(9, 4, 4, 3);

	}

	public void drawCircle(int n, int cX, int cY, int r) {

		Drawer d = new Drawer(n);
		
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (isWithin(r, cX, cY, x, y))
					d.addPoint(new Point(x, y));
			}
		}
		
		d.draw();
		
	}
	
	public static boolean isWithin(int r, int cX, int cY, int x, int y) {
		return Math.pow(cX-x, 2) + Math.pow(cY-y, 2) <= r * r;
	}

	class Drawer {
		boolean[][] board;
		int n, r, cX, cY;

		public Drawer(int n) {
			this.n = n;
			board = new boolean[n][n];
		}

		public void addPoint(Point p) {
			if (p.x > 0 && p.x < n && p.y > 0 && p.y < n /*&& isWithin(r, p.x, p.y)*/)
				board[p.x][p.y] = true;
		}
		
		public void draw() {
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board.length; y++) {
					System.out.print(board[x][y] ? "." : " ");
				}
				System.out.println();
			}
		}
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

}
