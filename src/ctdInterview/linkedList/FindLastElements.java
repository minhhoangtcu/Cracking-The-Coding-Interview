package ctdInterview.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implement an algorithm to find the kth to last element of a singly linked
 * list
 * 
 * @author Minh Hoang
 *
 */
public class FindLastElements {

	public static void main(String[] args) {

		FindLastElements find = new FindLastElements();
		List<Node> input = new LinkedList<>();
		input.add(find.new Node("A"));
		input.add(find.new Node("B"));
		input.add(find.new Node("C"));
		input.add(find.new Node("D"));
		input.add(find.new Node("E"));
		input.add(find.new Node("F"));
		System.out.println(printList(find.getKthLast(input, 2)));

	}

	public static String printList(List<Node> list) {
		StringBuilder sb = new StringBuilder();
		for (Node node : list) {
			sb.append(node.data + " ");
		}
		return sb.toString();
	}

	public List<Node> getKthLast(List<Node> list, int k) {

		if (list.size() <= k) {
			return list;
		}

		// we will maintain the distance of these 2 pointers by k
		Iterator<Node> iterRunner = list.iterator();
		Node runner = iterRunner.next();
		Iterator<Node> iterBehind = list.iterator();
		Node behind = iterBehind.next(); // set to the first node

		// advanced the first pointer by kth nodes. If k=2, then runner will
		// point to 2, while previous will point to 0
		for (int i = 0; i < k; i++) {
			runner = iterRunner.next();
		}

		// run the pointers till the end of the list. Runner is going to be the
		// last node n-1 for n be the number of nodes. And, if k=2 then previous is going to be n-3
		while (iterRunner.hasNext()) {
			behind = iterBehind.next();
			runner = iterRunner.next(); 
		}

		// add the nodes after previous to a result list. So, if k=2, we need 2 last nodes which is (n-1) and (n-2)
		behind = iterBehind.next(); // because previous is currently at n-3, so we need to advance it by 1 to get to (n-2) if k=2
		
		List<Node> result = new LinkedList<>();
		while (iterBehind.hasNext()) {
			result.add(behind);
			behind = iterBehind.next();
		}
		result.add(behind); // add the last node to the resulting list

		return result;
	}

	class Node {

		String data;

		public Node(String data) {
			this.data = data;
		}
	}
}
