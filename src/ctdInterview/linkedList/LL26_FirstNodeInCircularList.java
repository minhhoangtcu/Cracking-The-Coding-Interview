package ctdInterview.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a circular linked list, implement an algorithm which returns the node
 * at the beginning of the loop.
 * 
 * DEFINITION Circular linked list: A (corrupt) linked list in which a node's
 * next pointer points to an earlier node, so as to make a loop in the linked
 * list.
 * 
 * EXAMPLE Input A -> B -> C -> D -> E -> C [the same C as earlier]
 * 
 * Output: C
 * 
 * @author Minh
 *
 */
public class LL26_FirstNodeInCircularList {

	public static void main(String[] args) {

		BasicLinkedList list = new BasicLinkedList();
		list.root = new BasicNode("A");
		list.root.next = new BasicNode("B");
		list.root.next.next = new BasicNode("C");
		list.root.next.next.next = new BasicNode("D");
		list.root.next.next.next.next = new BasicNode("E");
		list.root.next.next.next.next = list.root.next.next;
		list.size = 5;

		System.out.println(getFirstNodeInCircularList(list).data);
		System.out.println(getFirstNodeInCircularListRunner(list).data);
		System.out.println("<<< PROGRAM TERMINATED >>>");
		
	}

	/**
	 * This algorithm find the first repeated node within the node. It does not
	 * require additional data structure. The program will not stop if the
	 * provided list is not a corrupted linked list.
	 * 
	 * @param list
	 * @return
	 */
	public static BasicNode getFirstNodeInCircularListRunner(BasicLinkedList list) {

		// A list with less than 3 nodes cannot be a corrupted circular list.
		if (list.size < 3)
			return new BasicNode("NOT FOUND");

		BasicNode fast = list.root;
		BasicNode slow = list.root;

		// Let fast node goes 2 steps and slow node goes 1 step at a time. If
		// the list is corrupted, then they have to collide at some point. At
		// this point, both nodes are k nodes away from the head of the loop
		do {
			slow = slow.next;
			fast = fast.next.next;
		} while (fast != slow);

		// root of list is also k nodes away from the the head of the loop. So,
		// if the two nodes both go k nodes, they will meet at the head of the
		// node
		slow = list.root;

		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	/**
	 * This algorithm find the first repeated node within the node. It requires
	 * O(n) worst case memory
	 * 
	 * @param list
	 * @return
	 */
	public static BasicNode getFirstNodeInCircularList(BasicLinkedList list) {

		Set<BasicNode> visisted = new HashSet<>();

		BasicNode current = list.root;

		while (current != null) {
			if (visisted.contains(current))
				return current;
			visisted.add(current);
			current = current.next;
		}

		// Found nothing
		return null;
	}

}
