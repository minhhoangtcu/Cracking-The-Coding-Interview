package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HotelBooking {

	/*
	 * Slow algorithm. Visit: http://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
	 */
	public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {

		int numOfDays = Math.max(Collections.max(arrive), Collections.max(depart));

		int[] rooms = new int[numOfDays + 1]; // Start indexing with 1
		Arrays.fill(rooms, K);

		for (int i = 0; i < arrive.size(); i++) {

			// Deduce rooms from arrive -> depart-1
			for (int day = arrive.get(i); day < depart.get(i); day++) {
				rooms[day]--;
				if (rooms[day] < 0)
					return false;
			}

		}

		return true;
	}
}
