package ctdInterview.linkedList;

import java.util.Stack;

/**
 * Implement a function to check if a linked list is a palindrome
 * 
 * @author minhhoang
 *
 */
public class LL27_Palindrome {
	
	public static void main(String[] args) {
		
		BasicLinkedList list = new BasicLinkedList();
		list.addEnd("A");
		list.addEnd("B");
		list.addEnd("C");
		list.addEnd("B");
		list.addEnd("A");
		
		System.out.println(isPalendrome(list));
		
		list.addEnd("A");
		
		System.out.println(isPalendrome(list));
		
		BasicLinkedList list2 = new BasicLinkedList();
		list2.addEnd("A");
		list2.addEnd("B");
		list2.addEnd("C");
		list2.addEnd("C");
		list2.addEnd("B");
		list2.addEnd("A");
		
		System.out.println(isPalendrome(list2));
		
		list2.addEnd("C");
		
		System.out.println(isPalendrome(list2));
	}

	/**
	 * Return true if the list is a palindrome. A list is a palindrome is the
	 * reverse of the list is similar to the list or if the reverse of the first
	 * half of the list similar to the second half.
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isPalendrome(BasicLinkedList list) {
		
		if (list.size <= 1)
			return true;
		
		Stack<BasicNode> reverse = new Stack<>();
		
		BasicNode fast = list.root;
		BasicNode slow = list.root;
		
		// Add the first half of the list into the stack with fast/slow runner technique (reversing it in the process)
		do {
			reverse.add(slow);
			
			fast = fast.next.next;
			slow = slow.next;
		} while (fast != null && fast.next != null);
		
		// If the length of the list is odd, fast has to be null. Else, if the list is odd, we can ignore the middle node
		if (fast != null)
			slow = slow.next;
		
		while (!reverse.isEmpty()) {
			if (!slow.data.equals(reverse.pop().data))
				return false;
			slow = slow.next;
		}
		
		return true;
	}

}
