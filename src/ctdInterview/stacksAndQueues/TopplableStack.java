package ctdInterview.stacksAndQueues;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 * <p>
 * Imagine a (literal) stack of plates. If the stack gets too high, it might
 * topple. Therefore, in real life, we would likely start a new stack when the
 * previous stack exceeds some threshold. Implement a data structure SetOfStacks
 * that mimics this. SetOfStacks should be composed of several stacks and should
 * create a new stack once the previous one exceeds capacity. SetOfStacks.push()
 * and pop() should behave identically to a single stack (that is, pop() should
 * return the same values as it would if there were just a single stack).
 * </p>
 * <p>
 * FOLLOW UP Implement a function popAt(int index) which performs a pop
 * operation on a specific sub-stack.
 * </p>
 * 
 * @author Minh Hoang
 *
 */
public class TopplableStack {

	public static void main(String[] args) {
		
		TopplableStack stacks = new TopplableStack(5);
		stacks.push("1");
		stacks.push("2");
		stacks.push("3");
		stacks.push("4");
		stacks.push("5");
		stacks.push("6");
		stacks.push("7");
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		System.out.println(stacks.pop());
		
		stacks.push("1");
		stacks.push("2");
		stacks.push("3");
		stacks.push("4");
		stacks.push("5");
		stacks.push("6");
		stacks.push("7");
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		
	}
	
	LinkedList<Stack<String>> setOfStacks;
	int size;
	int max;

	public TopplableStack(int max) {
		setOfStacks = new LinkedList<>();
		size = 0;
		this.max = max;
	}

	public void push(String text) {
		if (size == 0) {
			Stack<String> newStack = new Stack<>();
			newStack.push(text);
			setOfStacks.add(newStack);
		}
		else {
			Stack<String> currentStack = setOfStacks.getLast();
			if (currentStack.size() == max) {
				Stack<String> newStack = new Stack<>();
				newStack.push(text);
				setOfStacks.add(newStack);
			}
			else {
				currentStack.push(text);
			}
		}
		size++;
	}
	
	public String pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		else {
			Stack<String> currentStack = setOfStacks.getLast();
			String output = currentStack.pop();
			if (currentStack.isEmpty()) {
				setOfStacks.removeLast();
			}
			size--;
			return output;
		}
	}
	
	public String pop(int stackNum) {
		
		Stack<String> currentStack = setOfStacks.get(stackNum);
		String output = currentStack.pop();
		
		if (currentStack.isEmpty())
			setOfStacks.remove(currentStack);
		
		return output;
	}

	public int size() {
		return size;
	}

}
