package interviewBit;

import java.util.ArrayList;

public class PrettyPrint {

	public ArrayList<ArrayList<Integer>> prettyPrint(int a) {

		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		int edge = a * 2 - 1;
		int lastRow = edge - 1;

		for (int row = 0; row < edge; row++) {
			ArrayList<Integer> newRow = new ArrayList<>();

			int middle = a - 1;
			int reduce = 0;
			int maxReduce;
			int colToIncrease;

			if (row <= middle) {
				maxReduce = row;
				colToIncrease = lastRow - row;
			} else {
				maxReduce = lastRow - row;
				colToIncrease = row;
			}

			for (int col = 0; col < edge; col++) {

				newRow.add(a - reduce);

				if (col <= middle && reduce < maxReduce) {
					reduce++;
				} else if (col >= middle && col >= colToIncrease && reduce > 0) {
					reduce--;
				}
			}

			output.add(newRow);
		}

		return output;

	}

}
