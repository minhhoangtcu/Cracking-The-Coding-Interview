package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;

public class MinStepsInInfinityGrid {

	// X and Y co-ordinates of the points in order.
	// Each point is represented by (X.get(i), Y.get(i))
	public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {

		Integer[] xArray = X.toArray(new Integer[X.size()]);
		Integer[] yArray = Y.toArray(new Integer[Y.size()]);
		Arrays.sort(xArray);
		Arrays.sort(yArray);
		if (xArray[0] == xArray[xArray.length - 1] && yArray[0] == yArray[yArray.length - 1])
			return 0;

		boolean isFound = false;
		int currentX, currentY;
		int numberOfSteps = 0;

		while (!isFound) {

			if (X.size() < 2 || Y.size() < 2)
				isFound = true;
			else {
				currentX = X.remove(0);
				currentY = Y.remove(0);
				int nextX = X.get(0);
				int nextY = Y.get(0);

				if (nextX == currentX && nextY == currentY)
					continue;

				int disX = Math.abs(nextX - currentX);
				int disY = Math.abs(nextY - currentY);
				numberOfSteps += Math.max(disX, disY);
			}
		}

		return numberOfSteps;
	}
}
