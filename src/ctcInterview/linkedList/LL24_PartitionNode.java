package ctcInterview.linkedList;

/**
 * 
 * Write code to partition a linked list around a value x, such that all nodes
 * less than x come before all nodes greater than or equal to x
 * 
 * @author Minh Hoang
 *
 */
public class LL24_PartitionNode {

	public static void main(String[] args) {
		
		LL24_PartitionNode partition = new LL24_PartitionNode();
		
		LinkedList list = partition.new LinkedList();
		list.add("F");
		list.add("R");
		list.add("B");
		list.add("C");
		list.add("B");
		list.add("A");
		list.add("X");
		list.add("U");
		list.add("W");
		list.add("L");
		
		System.out.println(partition.partition(list, "C"));
		
	}
	
	
	public LinkedList partition(LinkedList list, String partition) {

		LinkedList smaller = new LinkedList();
		LinkedList bigger = new LinkedList();
		LinkedList similar = new LinkedList();
		
		Node runner = list.root;
		while (runner != null) {
			if (runner.data.compareTo(partition) >0) {
				bigger.add(runner.data);
			}
			else if (runner.data.compareTo(partition) <0) {
				smaller.add(runner.data);
			}
			else
				similar.add(runner.data);
			
			runner = runner.next;
		}
		
		LinkedList result = new LinkedList();
		
		runner = smaller.root;
		while (runner != null) {
			result.add(runner.data);
			runner = runner.next;
		}
		
		runner = similar.root;
		while (runner != null) {
			result.add(runner.data);
			runner = runner.next;
		}
		
		runner = bigger.root;
		while (runner != null) {
			result.add(runner.data);
			runner = runner.next;
		}
		
		return result;
		
	}
	
	class LinkedList {

		public Node root;
		public int size;

		public LinkedList() {
			root = null;
			size = 0;
		}

		public void add(String data) {
			if (isEmpty())
				root = new Node(data);
			else {
				Node node = new Node(data);
				node.next = root;
				root = node;
			}
			size++;
		}

		public boolean isEmpty() {
			return (size == 0);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();

			Node runner = root;
			while (runner != null) {
				sb.append(runner.data + " ");
				runner = runner.next;
			}

			return sb.toString();
		}
		
		public Node getNode(String index) {
			
			Node runner = root;
			while (runner != null && !runner.data.equals(index)) {
				runner = runner.next;
			}
			
			return runner;
		}
	}
	
	class Node {

		String data;
		Node next;

		public Node(String data) {
			this.data = data;
		}
	}

	
}
