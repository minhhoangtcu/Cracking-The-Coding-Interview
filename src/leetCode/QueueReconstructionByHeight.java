package leetCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionByHeight {

	public static void main(String[] args) {

		// input: [[0, 6], [7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
		int[][] people = { {0, 6}, { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
		QueueReconstructionByHeight blah = new QueueReconstructionByHeight();
		blah.reconstructQueue(people);

	}

	public int[][] reconstructQueue(int[][] people) {

		// corner cases
		if (people == null || people.length == 0)
			return null;

		// initialization

		List<Person> original = new LinkedList<>();
		for (int i = 0; i < people.length; i++) {
			original.add(new Person(people[i][0], people[i][1]));
		}

		// sort the original list in DESC height order, if we do this then when
		// we greedily pick person to put into the list, we will not break the
		// current orderings of the list
		Collections.sort(original, (a, b) -> {
			if (a.height > b.height)
				return -1;
			else if (b.height > a.height)
				return 1;
			else {
				if (a.numOfHigherPeople < b.height)
					return 1;
				else if (a.numOfHigherPeople > b.height)
					return -1;
				else
					return 0;
			}
		});
		
//		printList(original);
		
		List<Person> result = new LinkedList<>();
		result.add(original.remove(0)); // get the first person in original

		// populate the result
		int index = 0;
		while (!original.isEmpty()) {

			if (index >= original.size())
				index = 0; // whenever out of bound, reset to the beginning of
							// the list

			if (greedyPut(result, original.get(index))) {
				original.remove(index);
			} else {
				index++;
			}

		}

		for (int i = 0; i < people.length; i++) {
			people[i][0] = result.get(i).height;
			people[i][1] = result.get(i).numOfHigherPeople;
		}
		
		printList(result);

		return people;

	}

	// true if was able to put into the list
	private boolean greedyPut(List<Person> list, Person person) {

		int numOfHigher = 0;
		for (int i = 0; i <= list.size(); i++) { // allow to insert at the end too

			if (person.numOfHigherPeople == numOfHigher) {
				list.add(i, person);
				return true;
			} else if (list.get(i).height >= person.height) {
				numOfHigher++;
			}
		}

		return false;
	}

	private void printList(List<Person> people) {
		for (Person p : people) {
			System.out.printf(p + " ");
		}
		System.out.println();
	}

	class Person {
		int height;
		int numOfHigherPeople;

		Person(int height, int numOfHigherPeople) {
			this.height = height;
			this.numOfHigherPeople = numOfHigherPeople;
		}

		public String toString() {
			return String.format("[%d, %d] ", height, numOfHigherPeople);
		}
	}

}
