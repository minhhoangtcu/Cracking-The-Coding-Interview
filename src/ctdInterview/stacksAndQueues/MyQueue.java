package ctdInterview.stacksAndQueues;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implement a class which implements a queue using two stacks
 * 
 * @author Minh
 *
 */
public class MyQueue {

	public static void main(String[] args) {
		
		MyQueue queue = new MyQueue();
		
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("D");
		queue.enqueue("E");
		queue.enqueue("F");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
		queue.enqueue("G");
		queue.enqueue("H");
		queue.enqueue("I");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}
	
	Stack<String> stackOldest;
	Stack<String> stackNewest;
	int size;
	
	public MyQueue() {
		stackOldest = new Stack<>();
		stackNewest = new Stack<>();
		size = 0;
	}
	
	public void enqueue(String input) {
		stackNewest.push(input);
		size++;
	}
	
	public String dequeue() {
		if (isEmpty())
			throw new EmptyStackException();
		if (stackOldest.isEmpty())
			shift();
		size--;
		return stackOldest.pop();
	}
	
	public String peek() {
		if (isEmpty())
			throw new EmptyStackException();
		if (stackOldest.isEmpty())
			shift();
		return stackOldest.peek();
	}
	
	private void shift() {
		while (!stackNewest.isEmpty()) {
			stackOldest.push(stackNewest.pop());
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
}
