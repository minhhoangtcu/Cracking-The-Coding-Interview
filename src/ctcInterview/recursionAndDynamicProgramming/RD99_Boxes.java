package ctcInterview.recursionAndDynamicProgramming;

import java.util.ArrayList;

/**
 * You have a stack of n boxes, with widths w, heights h, and depths d. The
 * boxes cannot be rotated and can only be stacked on top of one another if each
 * box in the stack is strictly larger than the box above it in width, height,
 * and depth. Implement a method to build the tallest stack possible, where the
 * height of a stack is the sum of the heights of each box.
 * 
 * @author minhhoang
 *
 */
public class RD99_Boxes {

	public static void main(String[] args) {

		RD99_Boxes bb = new RD99_Boxes();
		Box b1, b2, b3, b4, b5;
		BoxStack bs;

		System.out.println("<<< BOX STACK TEST STARTED FOR 3 BOXES >>>");
		b1 = bb.new Box(1, 2, 1); // Will not get added because not strictly
									// smaller
		b2 = bb.new Box(2, 2, 2);
		b3 = bb.new Box(3, 3, 3);
		bs = bb.new BoxStack();

		bs.add(b3);
		bs.add(b2);
		bs.add(b1);

		bs.print();

		System.out.println("<<< BOX STACK TEST STARTED FOR 5 BOXES >>>");
		b1 = bb.new Box(1, 2, 1);
		b2 = bb.new Box(2, 3, 2);
		b3 = bb.new Box(3, 3, 3);
		b4 = bb.new Box(3, 4, 2);
		b5 = bb.new Box(4, 4, 4);

		bs = bb.new BoxStack();
		bs.add(b5);
		bs.add(b4);
		bs.add(b3);
		bs.add(b2);
		bs.add(b1);

		bs.print();
	}

	public class BoxStack {

		int height;
		Box root;
		ArrayList<Box> notFit;

		public BoxStack() {
			notFit = new ArrayList<>();
		}

		public void add(Box box) {

			// Check if first box
			if (root == null) {
				root = box;
				return;
			}

			try {
				// Check if larger than first box
				if (box.compareTo(root) > 0) {
					box.next = root;
					root = box;
					height += box.height;
					return;
				}
			} catch (IllegalArgumentException e) {
			} // Does nothing, check other boxes

			Box runner = root;

			while (runner != null) {

				try {
					// Check for last box
					if (runner.next == null && runner.compareTo(box) > 0) {
						runner.next = box;
						height += box.height;
						return;
					} else if (runner.next != null && runner.next.compareTo(box) < 0) {
						box.next = runner.next.next;
						runner.next = box;
						height += box.height;
						return;
					}
				} catch (IllegalArgumentException e) {
				}
				
				runner = runner.next;
			}
		}

		public void print() {
			Box runner = root;
			while (runner != null) {
				System.out.println(runner);
				runner = runner.next;
			}
		}
	}

	public class Box implements Comparable<Box> {

		int width, height, depth;
		Box next;

		public Box(int width, int height, int depth) {
			this.width = width;
			this.height = height;
			this.depth = depth;
		}

		public String toString() {
			return String.format("width: %d\theight: %d\tdepth: %d", width, height, depth);
		}

		@Override
		public int compareTo(Box o) {
			if (width > o.width && height > o.height && depth > o.depth)
				return 1;
			else if (width == o.width && height == o.height && depth == o.depth)
				return 0;
			else if (width < o.width && height < o.height && depth < o.depth)
				return -1;
			else
				throw new IllegalArgumentException("Cannot compare!");
		}
	}
}
