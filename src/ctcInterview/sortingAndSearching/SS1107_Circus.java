package ctcInterview.sortingAndSearching;

import java.util.Hashtable;

/**
 * A circus is designing a tower routine consisting of people standing atop one
 * another's shoulders. For practical and aesthetic reasons, each person must be
 * both shorter and lighter than the person below him or her. Given the heights
 * and weights of each person in the circus, write a method to compute the
 * largest possible number of people in such a tower.
 * 
 * EXAMPLE:
 * 
 * Input (ht, wt): (65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
 * 
 * Output: The longest tower is length 6 and includes from top to bottom:
 * 
 * (56, 90) (60, 95) (65, 100) (68, 110) (70, 150) (75, 190)
 * 
 * @author minhhoang
 *
 */
public class SS1107_Circus {

	public static void main(String[] args) {

		SS1107_Circus c = new SS1107_Circus();

		Person p1 = c.new Person(65, 100);
		Person p2 = c.new Person(70, 150);
		Person p3 = c.new Person(56, 90);
		Person p4 = c.new Person(75, 190);
		Person p5 = c.new Person(60, 95);
		Person p6 = c.new Person(68, 110);
		Person p7 = c.new Person(50, 120);

		Person[] people1 = { p1, p3 };
		Person[] people2 = { p3, p1 };
		Person[] people3 = { p1, p2, p3, p4, p5, p6 };
		Person[] people4 = { p7, p6 };
		Person[] people5 = { p1, p2, p3, p4, p5, p6, p7 };
		Person[] people6 = { p1, p2, p3, p4, p5, p7 };

		System.out.println(c.getLargest(people1));
		System.out.println(c.getLargest(people2));
		System.out.println(c.getLargest(people3));
		System.out.println(c.getLargest(people4));
		System.out.println(c.getLargest(people5));
		System.out.println(c.getLargest(people6));

	}

	public int getLargest(Person[] people) {
		int max = Integer.MIN_VALUE;
		for (Person person : people) {
			int largestWithPersonAsBase = getLargestHelper(people, person, new Hashtable<>());
			if (largestWithPersonAsBase > max)
				max = largestWithPersonAsBase;
		}
		return max;
	}

	private int getLargestHelper(Person[] people, Person base, Hashtable<Person, Integer> maxWithBase) {

		if (maxWithBase.containsKey(base))
			return maxWithBase.get(base);

		int max = Integer.MIN_VALUE;
		boolean isTop = true;

		for (Person person : people) {
			if (person != base && person.compareTo(base) < 0) {
				// Try and stack everyone on the provided base person
				isTop = false;
				int largestWithPersonAsBase = getLargestHelper(people, person, maxWithBase);
				if (largestWithPersonAsBase > max)
					max = largestWithPersonAsBase;
			}
		}

		if (isTop) { // If we cannot put any other person on top of base
			maxWithBase.put(base, 1);
			return 1;
		} else {
			maxWithBase.put(base, max + 1);
			return max + 1;
		}
	}

	public class Person implements Comparable<Person> {
		int height;
		int weight;

		public Person(int height, int weight) {
			this.height = height;
			this.weight = weight;
		}

		@Override
		public int compareTo(Person o) {
			if (o.height == height && o.weight == weight)
				return 0;
			else if (o.height > height && o.weight > weight)
				return -1;
			else
				return 1;
		}
	}
}
