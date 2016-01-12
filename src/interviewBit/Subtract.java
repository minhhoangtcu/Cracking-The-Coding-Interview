package interviewBit;

import java.util.Stack;

/**
 * https://www.interviewbit.com/courses/programming/topics/linked-lists/problems/subtract/
 * 
 * @author Minh
 *
 */
public class Subtract {
	public ListNode subtract(ListNode a) {

		// build up a stack for backtracking
		int numberOfNodes = 0;
		Stack<ListNode> backtracking = new Stack<>();
		ListNode current = a;
		while (current != null) {
			backtracking.push(current);
			numberOfNodes++;
			current = current.next;
		}

		// change the node from start to middle
		int middle = numberOfNodes / 2;
		current = a;
		for (int i = 0; i < middle; i++) {
			current.val = backtracking.pop().val - current.val;
			current = current.next;
		}

		return a;
	}
}

class ListNode {
	public int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}
