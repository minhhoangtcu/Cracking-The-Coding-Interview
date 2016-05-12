package ctcInterview.sortingAndSearching;

/**
 * Given a sorted array of strings which is interspersed with empty strings,
 * write a method to find the location of a given string.
 * 
 * EXAMPLE
 * 
 * Input: find "ball" in {"at", "", "", "", "ball", "", "", "car", "", "",
 * "dad", "", ""}
 * 
 * Output: 4
 * 
 * @author minhhoang
 *
 */
public class SS1105_InterspersedArray {

	public static void main(String[] args) {
		
		SS1105_InterspersedArray ia = new SS1105_InterspersedArray();
		
		String[] dirtyArray = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "", "wow", "wow"};
		
		System.out.println(ia.search(dirtyArray, "ball"));
		System.out.println(ia.search(dirtyArray, "dad"));
		System.out.println(ia.search(dirtyArray, "at"));
		System.out.println(ia.search(dirtyArray, "car"));
		System.out.println(ia.search(dirtyArray, "wow"));
		
	}
	
	/**
	 * We can modify the binary search. Instead of comparing the middle element,
	 * which might be an empty String with the key, we can compare the nearest
	 * valid String near the middle with the key.
	 */

	public int search(String[] dirtyArray, String key) {
		return searchHelper(dirtyArray, key, 0, dirtyArray.length-1);
	}

	public int searchHelper(String[] dirtyArray, String key, int left, int right) {

		if (left > right)
			return -1;

		int mid = (left + right) / 2;
		String midElement = dirtyArray[mid];

		if (dirtyArray[mid].equals(key))
			return mid; // Found the key!

		else if (dirtyArray[mid].equals("")) {

			int leftRunner = mid;
			int rightRunner = mid;

			while (true) {
				if (leftRunner >= left && !dirtyArray[--leftRunner].equals("")) {
					midElement = dirtyArray[leftRunner];
					mid = leftRunner;
					break;
				} else if (rightRunner <= right && !dirtyArray[++rightRunner].equals("")) {
					midElement = dirtyArray[rightRunner];
					mid = rightRunner;
					break;
				} else if (leftRunner < left && rightRunner < right)
					return -1; // Found nothing on both left and right
			}

			// After finding a valid String, we check it. We have to do this
			// because the code where we found a valid String below neglect
			// checking the mid element.
			if (dirtyArray[mid].equals(key))
				return mid;
		}

		if (key.compareTo(midElement) > 0)
			return searchHelper(dirtyArray, key, mid + 1, right);
		else
			return searchHelper(dirtyArray, key, left, mid - 1);

	}

}
