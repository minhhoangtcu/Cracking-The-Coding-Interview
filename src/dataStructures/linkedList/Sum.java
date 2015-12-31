package dataStructures.linkedList;

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
		
		BasicLinkedList list1 = sum.getListReversed("123");
		BasicLinkedList list2 = sum.getListReversed("123");
		
		System.out.println(sum.sum(list1, list2));
		
	}

	/** Sum up 2 linked list in reverse order **/
	public BasicLinkedList sum(BasicLinkedList firstList, BasicLinkedList secondList) {
		
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
}
