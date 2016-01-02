package dataStructures.linkedList;

public class BasicLinkedList {
	
	public BasicNode root;
	public BasicNode tail;
	public int size;

	public BasicLinkedList() { }

	public void addStart(String data) {
		BasicNode node = new BasicNode(data);
		if (isEmpty()) {
			root = node;
			tail = node;
		}
		else {
			node.next = root;
			root = node;
		}
		size++;
	}
	
	public void addStart(BasicNode node) {
		if (isEmpty()) {
			root = node;
			tail = node;
		}
		else {
			node.next = root;
			root = node;
		}
		size++;
	}
	
	public void addEnd(String data) {
		BasicNode node = new BasicNode(data);
		if (isEmpty()) {
			root = node;
			tail = node;
		}
		else {
			tail.next = node;
			tail = node;
		}
		size++;
	}
	
	public void addEnd(BasicNode node) {
		if (isEmpty()) {
			root = node;
			tail = node;
		}
		else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		BasicNode runner = root;
		while (runner != null) {
			sb.append(runner.data);
			runner = runner.next;
		}

		return sb.toString();
	}
	
	public BasicNode getNode(String index) {
		
		BasicNode runner = root;
		while (runner != null && !runner.data.equals(index)) {
			runner = runner.next;
		}
		
		return runner;
	}

}
