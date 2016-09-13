package hackerrank.contest.misc;

public class Lighthouse {

	public static void main(String[] args) {

		Lighthouse lh = new Lighthouse();

		lh.drawCircle(9, 4, 4, 2);

	}

	public void drawCircle(int n, int cX, int cY, int r) {

		int x = 0;
		int y = r;
		int d = 3 - 2 * r;

		Drawer drawer = new Drawer(n, r, cX, cY);
		while (x < y) {
			drawCircle(drawer, r, cX, cY, x, y);
			x++;
			if (d < 0) {
				d = d + 4 * x + 6;
			} else {
				y--;
				d = d + 4 * (x - y) + 10;
			}
			drawCircle(drawer, r, cX, cY, x, y);
		}
		drawer.draw();
	}

	public void drawCircle(Drawer d, int r, int cX, int cY, int x, int y) {
		d.addPoint(new Point(cX + x, cY + y));
		d.addPoint(new Point(cX - x, cY + y));
		d.addPoint(new Point(cX + x, cY - y));
		d.addPoint(new Point(cX - x, cY - y));
		d.addPoint(new Point(cX + y, cY + x));
		d.addPoint(new Point(cX - y, cY + x));
		d.addPoint(new Point(cX + y, cY - x));
		d.addPoint(new Point(cX - y, cY - x));
	}

	

	class Drawer {
		boolean[][] board;
		int n, r, cX, cY;

		public Drawer(int n, int r, int cX, int cY) {
			this.n = n;
//			this.r = r; 
//			this.cX = cX;
//			this.cY = cY;
			board = new boolean[n][n];
		}

		public void addPoint(Point p) {
			if (p.x > 0 && p.x < n && p.y > 0 && p.y < n /*&& isWithin(r, p.x, p.y)*/)
				board[p.x][p.y] = true;
		}
		
//		public boolean isWithin(int r, int x, int y) {
//			return Math.pow(cX-x, 2) + Math.pow(cY-y, 2) <= r * r;
//		}

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
