package dataStructures.stacksAndQueues;

import java.util.Arrays;
import java.util.Stack;

/**
 * Write a program to sort a stack in ascending order (with biggest items on
 * top). You may use at most one additional stack to hold items, but you may not
 * copy the elements into any other data structure (such as an array). The stack
 * supports the following operations: push, pop, peek, and isEmpty.
 * 
 * @author Minh
 *
 */
public class SortInStack {

	public static void main(String[] args) {

		Stack<Integer> input = new Stack<>();
		input.push(0);
		input.push(9);
		input.push(1);
		input.push(6);
		input.push(5);
		input.push(3);
		Stack<Integer> output = SortInStack.sort(input);
		System.out.println(output.pop());
		System.out.println(Arrays.toString(output.toArray()));
		
		input = new Stack<>();
		input.push(1);
		input.push(1);
		input.push(1);
		input.push(2);
		input.push(3);
		input.push(2);
		input.push(3);
		input.push(2);
		input.push(4);
		input.push(5);
		output = SortInStack.sort(input);
		System.out.println(output.pop());
		System.out.println(Arrays.toString(output.toArray()));
	}

	/*
	 * Keep popping elements of stack A and pushing into stack B while keeping
	 * track of smallest element. Now, we pop everything in B and put them back
	 * into A except for the smallest element we kept track. Push, the smallest
	 * element into B. Now, this element is going to sit at the bottom of the
	 * stack and we will never pop it out.
	 */
	public static Stack<Integer> sort(Stack<Integer> stackA) {
		Stack<Integer> stackB = new Stack<>();
		int numberOfElementsToConsider = stackA.size();

		do {
			int smallest = Integer.MAX_VALUE;

			while (!stackA.isEmpty()) {
				if (stackA.peek() <= smallest)
					smallest = stackA.peek();
				stackB.push(stackA.pop());
			}

			boolean isIgnoredAlready = false;
			for (int i = 0; i < numberOfElementsToConsider; i++) {
				if (stackB.peek() == smallest && !isIgnoredAlready) {
					isIgnoredAlready = true;
					stackB.pop();
				}
				else 
					stackA.push(stackB.pop());
			}

			stackB.push(smallest);
			numberOfElementsToConsider--;

		} while (numberOfElementsToConsider > 0);

		return stackB;
	}
}
