package dataStructures.linkedList;

/**
 * Prompt:
 * 
 * Write code to remove duplicates from an unsorted linked list.
 * 
 * FOLLOW UP
 * 
 * How would you solve this problem if a temporary buffer is not allowed?
 * 
 * Questions that came to my mind:
 * 	- is white spaces ' ' significant, do I need to remove that?
 *  - is case significant? How about a and A.
 * 
 * @author Minh
 *
 */
public class DuplicatesRemoval {
	
	public static void main(String[] args) {
		
		// Test the list
		System.out.println("List test");
		DuplicatesRemoval removal = new DuplicatesRemoval();
		LinkedList list = removal.new LinkedList();
		list.add("C");
		list.add("B");
		list.add("A");
		System.out.println(list);
		
		list.add("A");
		list.add("0");
		list.add("-1");
		System.out.println(list);
		
		// Test the remove
		System.out.println("Removing duplicates from FOLLOW UP");
		removal.remove("FOLLOW UP");
	}
	
	public String remove(String input) {
		
		// convert the provided input into a linked list

		char[] charArray = input.toCharArray();
		LinkedList list = new LinkedList();
		for (int i = charArray.length-1; i >= 0; i--) {
			list.add(String.valueOf(charArray[i]));
		}
		
		System.out.println(list);
		
		// remove duplicates
		WordNode pointer = list.root;
		WordNode runner = pointer.next;
		WordNode previous = pointer;
		
		while (pointer != null){
			
			while (runner != null) {
				if (runner.data.equals(pointer.data)) {
					previous.next = runner.next;
				}
				else {
					previous = runner;
				}
				runner = runner.next;
			}
			
			pointer = pointer.next;
			if (pointer != null)
				runner = pointer.next;
			previous = pointer;
		}
		
		System.out.println(list);
		
		return null;
	}

	class LinkedList {
		
		public WordNode root;
		public int size;
		
		public LinkedList() {
			root = null;
			size = 0;
		}
		
		public void add(String data) {
			if (isEmpty())
				root = new WordNode(data);
			else {
				WordNode node = new WordNode(data);
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
			
			WordNode runner = root;
			while (runner != null) {
				sb.append(runner.data + " ");
				runner = runner.next;
			}
			
			return sb.toString();
		}
		
	}
	
	class WordNode {
		
		public String data;
		public WordNode next;
		
		public WordNode(String data) {
			this.data = data;
		}
	}
	
}
