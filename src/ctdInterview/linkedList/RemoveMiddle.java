package ctdInterview.linkedList;

/**
 * <p>
 * An algorithm to delete a node in the middle of a singly linked list, given
 * only access to that node.<br>
 * Example:<br>
 * Input: the node c from the linked list a->b->d->d->e<br>
 * Result: nothing is returned, but the new linked list looks like a->b->d->e
 * <br>
 * </p>
 *
 * @author Minh Hoang
 *
 */
public class RemoveMiddle {

	public static void main(String[] args) {
		
		RemoveMiddle removal = new RemoveMiddle();
		LinkedList list = removal.new LinkedList();
		
		list.add("F");
		list.add("E");
		list.add("D");
		list.add("C");
		list.add("B");
		list.add("A");
		
		System.out.println(list);
		removal.remove(list.getNode("C"));
		System.out.println(list);
		
	}
	
	public void remove(Node middle) {
		
		// copy the data of the node after the middle node to the middle one
		middle.data = middle.next.data;
		
		// remove the next word after the middle word
		middle.next = middle.next.next;
		
		
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
