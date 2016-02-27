package ctdInterview.stacksAndQueues;

import java.util.Stack;

/**
 * Design a stack which, in addition to push and pop, also has a function min
 * which returns the minimum element. Push, pop and min should all perate in
 * O(1) time.
 * 
 * @author Minh
 *
 */
public class StackMinConstantTime extends Stack<Node> {
	
	public static void main(String[] args) {
		
		StackMinConstantTime stack = new StackMinConstantTime();
		stack.push(new Node(5));
		stack.push(new Node(10));
		stack.push(new Node(1));
		stack.push(new Node(3));
		stack.push(new Node(7));
		stack.push(new Node(100));
		
		System.out.println(stack.min().data);
		
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		
		System.out.println(stack.min().data);
		
	}
	

	Stack<Node> minimums;
	
	public StackMinConstantTime() {
		minimums = new Stack<Node>();
	}
	
	public Node push(Node node) {
		if (node.data < min().data)
			minimums.push(node);
		super.push(node);
		return node;
	}
	
	public Node pop() {
		if (super.peek().data == min().data)
			minimums.pop();
		return super.pop();
	}
	
	public Node min() {
		if (minimums.isEmpty())
			return new Node(Integer.MAX_VALUE); // Error value
		else
			return minimums.peek();
	}
	
}

class Node {
	
	int data;
	
	public Node(int data) {
		this.data = data;
	}
	
}

