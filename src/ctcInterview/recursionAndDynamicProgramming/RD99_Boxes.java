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
		
		System.out.println("<<< BOX STACK TEST STARTED FOR 3 BOXES >>>");
		Box b1 = bb.new Box(1, 2, 1); // Will not get added because not strictly smaller
		Box b2 = bb.new Box(2, 2, 2);
		Box b3 = bb.new Box(3, 3, 3);
		BoxStack bs = bb.new BoxStack();
		
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
			
			if (root == null) {
				root = box;
				return;
			}
			
			Box runner = root;
			
			while (runner != null) {
				try {
					if (runner.next == null && runner.compareTo(box) > 0) {
						runner.next = box;
						height += box.height;
						return;
					}
					else if (runner.next != null && runner.next.compareTo(box) < 0) {
						box.next = runner.next.next;
						runner.next = box;
						height += box.height;
						return;
					}
				}
				catch (IllegalArgumentException e) {
					notFit.add(box);
					return;
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
