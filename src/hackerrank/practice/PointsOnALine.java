package hackerrank.practice;

import java.util.Scanner;

public class PointsOnALine {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

		int fX, fY;
		boolean isV = true;
		boolean isH = true;

		for (int a0 = 0; a0 < n; a0++) {

			if (a0 == 0) {
				fX = in.nextInt();
				fY = in.nextInt();
			} else {
				int x = in.nextInt();
				int y = in.nextInt();
//				if (x != fX)
//					isV = false;
//				if (y != fY)
//					isH = false;
//				if ((!fX) && (!fY)) {
//					System.out.println("NO");
//					break;
//				}
			}
		}

		System.out.println("YES");
	}
}
