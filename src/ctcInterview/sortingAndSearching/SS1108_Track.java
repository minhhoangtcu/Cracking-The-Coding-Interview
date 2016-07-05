package ctcInterview.sortingAndSearching;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish to be
 * able to look up the rank of a number x (the number of values less than or
 * equal to x). Implement the data structures and algorithms to support these
 * operations. That is, implement the method track(int x), which is called when
 * each number is generated, and the method getRankOfNumber(int x), which
 * returns the number of values less than or equal to x (not including x itself)
 * 
 * EXAMPLE:
 * 
 * Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
 * 
 * getRankOfNumber(1) = 0
 * 
 * getRankOfNumber(3) = 1
 * 
 * getRankOfNumber(4) = 3
 * 
 * @author minhhoang
 *
 */
public class SS1108_Track {
	
	public static void main(String[] args) {
		
		SS1108_Track t = new SS1108_Track();
		
		TrackTree tree = t.new TrackTree();
		
		tree.track(5);
		tree.track(1);
		tree.track(4);
		tree.track(4);
		tree.track(5);
		tree.track(9);
		tree.track(7);
		tree.track(13);
		tree.track(3);
		
		System.out.println(tree.getRankOfNumber(1));
		System.out.println(tree.getRankOfNumber(3));
		System.out.println(tree.getRankOfNumber(4));
		
	}

	class TrackTree {

		int size;
		TrackNode root;

		public TrackTree() {
		}
		
		public void track(int id) {
			insert(id);
		}

		private void insert(int id) {

			if (isEmpty())
				root = new TrackNode(id);
			else {
				TrackNode runner = root;

				while (runner != null) {
					if (id > runner.id) {
						if (runner.right == null) {
							runner.right = new TrackNode(id);
							break;
						}
						runner = runner.right;
					} else if (id < runner.id) {
						if (runner.left == null) {
							runner.left = new TrackNode(id);
							break;
						}
						runner.incLowerOrEqual();
						runner = runner.left;
					} else {
						runner.addDuplicate();
						break;
					}
				}
			}

			size++;
		}

		public int getRankOfNumber(int id) {

			if (isEmpty())
				throw new IllegalArgumentException("Empty tree!");
			else {
				TrackNode runner = root;
				int numOfLessOrEqual = 0;

				while (runner != null) {
					if (id > runner.id) {
						if (runner.right == null)
							return -1;
						// Keep track of the rank of nodes that are smaller than
						// what we are looking for
						numOfLessOrEqual += runner.getNumOnLeftAndSelf();
						runner = runner.right;
					} else if (id < runner.id) {
 						if (runner.left == null)
							return -1;
						runner = runner.left;
					} else {
						return numOfLessOrEqual + (runner.getNumOnLeftAndSelf()-1); // Does not count itself
					}
				}

				return -1; // Never reach this statement
			}

		}

		public boolean isEmpty() {
			return size == 0;
		}

	}

	class TrackNode {

		private int numOnLeft;
		int id;
		private int weight; // Easily keep track of duplicates
		TrackNode left, right;

		public TrackNode(int id) {
			this.id = id;
			weight = 1;
		}

		public int getNumOnLeftAndSelf() {
			return numOnLeft + weight;
		}

		public void addDuplicate() {
			numOnLeft++;
			weight++;
		}

		public void incLowerOrEqual() {
			numOnLeft++;
		}

	}

}
