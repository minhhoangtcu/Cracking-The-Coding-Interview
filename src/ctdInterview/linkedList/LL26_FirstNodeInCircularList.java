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

		System.out.println(getFirstNodeInCircularList(list).data);
	}

	/**
	 * This algorithm find the first repeated node within the node. It does not
	 * require additional 
	 * 
	 * @param list
	 * @return
	 */
	public static BasicNode getFirstNodeInCircularListRunner(BasicLinkedList list) {

		return null;
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
