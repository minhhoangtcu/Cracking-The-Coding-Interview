package ctcInterview.recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;

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
public class RD910_Boxes {

	public static void main(String[] args) {

		RD910_Boxes bb = new RD910_Boxes();
		Box b1, b2, b3, b4, b5, b6, b7;
		BoxStack bs;

//		System.out.println("<<< BOX STACK TEST STARTED FOR 3 BOXES >>>");
//		b1 = bb.new Box(1, 2, 1); // Will not get added because not strictly
//									// smaller
//		b2 = bb.new Box(2, 2, 2);
//		b3 = bb.new Box(3, 3, 3);
//		bs = bb.new BoxStack();
//
//		bs.add(b3);
//		bs.add(b2);
//		bs.add(b1);
//
//		bs.print();
//
//		System.out.println("<<< BOX STACK TEST STARTED FOR 5 BOXES >>>");
//		b1 = bb.new Box(1, 2, 1);
//		b2 = bb.new Box(2, 3, 2);
//		b3 = bb.new Box(3, 3, 3);
//		b4 = bb.new Box(3, 4, 2);
//		b5 = bb.new Box(4, 4, 4);
//
//		bs = bb.new BoxStack();
//		bs.add(b5);
//		bs.add(b4);
//		bs.add(b3);
//		bs.add(b2);
//		bs.add(b1);
//
//		bs.print();
//		
//		System.out.println("<<< BOX STACK TEST STARTED FOR 7 BOXES >>>");
//		/**
//		 * Two possible stacks:
//		 * b7 -> b4 -> b2 -> b1 -> height: 24 -> win!
//		 * b6 -> b5 -> b3 -> b1 -> height: 19
//		 * But, how do know which stack to choose?
//		 * 
//		 * Find max?
//		 *  -> impossible because cannot compare b6 and b7.
//		 * Loop through everything and set as base?
//		 *  -> impossible because we also have to loop through all other to choose the 2nd one
//		 * Recursive approach:
//		 *  - 1 box: must be highest
//		 *  - 2 boxes: can we stack them? if yes return, else return the max of the two
//		 *   + b1 -> b2
//		 *   + b2 -> b1
//		 *   + b1
//		 *   + b2
//		 *  - 3 boxes: we have 3 ways to put the 3rd box, before, between, or after the last config ().  
//		 *   + if previously we have a stack and b3 > b1 then b3 -> b1 -> b2 is bigger than just b1 -> we never have to worry about smaller case
//		 *   + if we don't have a stack, we have to try b3, b1 and b3, b2.
//		 *  - 4 boxes:
//		 *   + if previously we have a stack
//		 */
		b1 = bb.new Box(1, 2, 1);
		b2 = bb.new Box(2, 3, 2);
		b3 = bb.new Box(3, 3, 3);
		b4 = bb.new Box(3, 4, 2);
		b5 = bb.new Box(4, 4, 4);
		b6 = bb.new Box(5, 10, 5); // b5 can be on top of b6, which would result in 10 + 4
		b7 = bb.new Box(4, 15, 4); // b5 cannot be on top of b7, but b4 can, which would result in 15 + 4

//		bs = bb.new BoxStack();
//		bs.add(b5);
//		bs.add(b4);
//		bs.add(b3);
//		bs.add(b2);
//		bs.add(b1);
//
//		bs.print();
		
		System.out.println("<<< Get Highest Stack With 7 Boxes >>>");
		LinkedList<Box> allBoxes = new LinkedList<>();
		allBoxes.add(b1);
		allBoxes.add(b2);
		allBoxes.add(b3);
		allBoxes.add(b4);
		allBoxes.add(b5);
		allBoxes.add(b6);
		allBoxes.add(b7);
		
		bb.getHighest(allBoxes).print();
	}
	
	public BoxStack getHighest(LinkedList<Box> allBoxes) {
		return getHighestHelper(allBoxes, new BoxStack(), allBoxes.getFirst());
	}
	
	public BoxStack getHighestHelper(LinkedList<Box> allBoxes, BoxStack currentStack, Box base) {
		
		if (allBoxes.isEmpty())
			return currentStack;
		
		BoxStack highest = null;
		allBoxes.remove(base);
		try {
			currentStack.add(base);
		} catch (IllegalArgumentException e) {
			return currentStack; // Cannot put base on top
		}
		
		for (Box box: allBoxes) {
			BoxStack temp = getHighestHelper((LinkedList<Box>) allBoxes.clone(), currentStack, box);
			if (highest == null || highest.height < temp.height)
				highest = temp;
		}
		
		if (highest != null)
			return highest;
		else
			return new BoxStack();
		
	}

	public class BoxStack {

		int height;
		Box root;
		ArrayList<Box> notFit;

		public BoxStack() {
			height = 0;
			root = null;
			notFit = new ArrayList<>();
		}

		public void add(Box box) {

			// Check if first box
			if (root == null) {
				root = box;
				height = box.height;
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
				} // Does nothing, check other boxes
				
				runner = runner.next;
			}
			
			throw new IllegalArgumentException("Cannot add into the stack");
		}

		public void print() {
			System.out.printf("Box Stack Height: %d\n", height);
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
