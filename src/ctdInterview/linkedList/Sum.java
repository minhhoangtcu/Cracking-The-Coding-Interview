package ctdInterview.linkedList;

import java.util.HashMap;

/**
 * <p>
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1's digit
 * is at the head of the list. Write a function that adds the two numbers and
 * returns the sum as a linked list.
 * </p>
 * 
 * EXAMPLE:
 * <ul>
 * <li>Input: (7->1->6) + (5->9->2).
 * <li>Output: 2->1->9.
 * </ul>
 * 
 * FOLLOW UP<br>
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * 
 * @author Minh Hoang
 *
 */
public class Sum {
	
	public static void main(String[] args) {
		
		Sum sum = new Sum();
		
		System.out.println("Test sum lists reverse");
		
		BasicLinkedList list1 = sum.getListReversed("123"); //321
		BasicLinkedList list2 = sum.getListReversed("123"); //321
		System.out.println(String.format("Expected %d \t Actual: %s", 642, sum.sumReverse(list1, list2)));
		
		list1 = sum.getList("716");
		list2 = sum.getList("592");
		System.out.println(String.format("Expected %d \t Actual: %s", 219, sum.sumReverse(list1, list2)));
		
		list1 = sum.getList("1000");
		list2 = sum.getList("10000");
		System.out.println(String.format("Expected %s \t Actual: %s", "20000", sum.sumReverse(list1, list2)));
		
		list1 = sum.getList("5959");
		list2 = sum.getList("1919");
		System.out.println(String.format("Expected %s \t Actual: %s", "68781", sum.sumReverse(list1, list2)));
		
		list1 = sum.getList("0001");
		list2 = sum.getList("00001");
		System.out.println(String.format("Expected %s \t Actual: %s", "00011", sum.sumReverse(list1, list2)));
		
		System.out.println("\nTest reverse lists");
		
		String actual = sum.reverseList(sum.getList("1234")).toString();
		String expected = "4321";
		System.out.println(String.format("Expected %s \t Actual: %s", expected, actual));
		
		actual = sum.reverseList(sum.getList("10000")).toString();
		expected = "00001";
		System.out.println(String.format("Expected %s \t Actual: %s", expected, actual));
		
		actual = sum.reverseList(sum.getList("")).toString();
		expected = "";
		System.out.println(String.format("Expected %s \t Actual: %s", expected, actual));
		
		System.out.println("\nTest sum normal 2 lists");
		
		list1 = sum.getList("5959");
		list2 = sum.getList("1919");
		System.out.println(String.format("Expected %s \t Actual: %s", "7878", sum.sumNormal(list1, list2)));
		
		list1 = sum.getList("100");
		list2 = sum.getList("1011");
		System.out.println(String.format("Expected %s \t Actual: %s", "1111", sum.sumNormal(list1, list2)));
		
		list1 = sum.getList("2");
		list2 = sum.getList("9");
		System.out.println(String.format("Expected %s \t Actual: %s", "11", sum.sumNormal(list1, list2)));
		
	}

	/** Sum up 2 linked list in normal order **/
	public BasicLinkedList sumNormal(BasicLinkedList firstList, BasicLinkedList secondList) {
		
		firstList = reverseList(firstList);
		secondList = reverseList(secondList);
		
		return reverseList(sumReverse(firstList, secondList));
	}
	
	/** Sum up 2 linked list in reverse order **/
	public BasicLinkedList sumReverse(BasicLinkedList firstList, BasicLinkedList secondList) {
		
		BasicNode firstListPointer = firstList.root;
		BasicNode secondListPointer = secondList.root;
		BasicLinkedList resultingList = new BasicLinkedList();
		boolean flagEnd = false;
		int save = 0;
		
		while (!flagEnd) {
			
			if (firstListPointer == null && secondListPointer != null) {
				if (save != 0) {
					int resultingInt = Integer.parseInt(secondListPointer.data) + save;
					resultingList.addEnd(new BasicNode(resultingInt+""));
					save = 0;
				}
				else
					resultingList.addEnd(secondListPointer);
				secondListPointer = secondListPointer.next;
			}
			else if (secondListPointer == null && firstListPointer != null) {
				if (save != 0) {
					int resultingInt = Integer.parseInt(firstListPointer.data) + save;
					resultingList.addEnd(new BasicNode(resultingInt+""));
					save = 0;
				}
				else
					resultingList.addEnd(firstListPointer);
				firstListPointer = firstListPointer.next;
			}
			else if (firstListPointer != null && secondListPointer != null) {
				int a = Integer.parseInt(firstListPointer.data);
				int b = Integer.parseInt(secondListPointer.data);
				
				int resultingInt = a + b + save;
				save = 0;
				if (resultingInt > 9) {
					save = resultingInt / 10;
					resultingInt = resultingInt % 10;
				}
				
				resultingList.addEnd(new BasicNode(resultingInt+""));
				
				firstListPointer = firstListPointer.next;
				secondListPointer = secondListPointer.next;
			}
			else if (firstListPointer == null && secondListPointer == null && save != 0) {
				resultingList.addEnd(new BasicNode(save+""));
				save = 0;
			}
			else if (firstListPointer == null && secondListPointer == null && save == 0) {
				flagEnd = true;
			}
		}
		
		return resultingList;
	}
	
	/** Get a linked list in normal order **/
	public BasicLinkedList getList(String input) {
		
		char[] charArray = input.toCharArray();
		BasicLinkedList output = new BasicLinkedList();
		
		for (int i = charArray.length-1; i >= 0; i--) {
			output.addStart(String.valueOf(charArray[i]));
		}
		
		return output;
	}
	
	public BasicLinkedList getListReversed(String input) {
		char[] charArray = input.toCharArray();
		BasicLinkedList output = new BasicLinkedList();
		
		for (int i = charArray.length-1; i >= 0; i--) {
			output.addEnd(String.valueOf(charArray[i]));
		}
		
		return output;
	}
	
	/** Reverse a singly linked list using back tracking with hash map **/
	public BasicLinkedList reverseList(BasicLinkedList list) {
		
		HashMap<BasicNode, BasicNode> backTrack = new HashMap<>();
		BasicLinkedList result = new BasicLinkedList();
		BasicNode current = list.root;
		BasicNode previous = null;
		
		// Build backtracking path
		while (current != null) {
			backTrack.put(current, previous);
			previous = current;
			current = current.next;
		}
		
		// Work from the end node, build path
		while (previous != null) {
			result.addEnd(previous.data);
			previous = backTrack.get(previous);
		}
		
		return result;
	}
}
