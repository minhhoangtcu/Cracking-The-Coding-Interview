package leetCode;

public class MergeTwoSortedList {
	
	public static void main(String[] args) {
		
		MergeTwoSortedList mtsl = new MergeTwoSortedList();
		ListNode l1 = mtsl.new ListNode(1);
		l1.next = mtsl.new ListNode(5);
		l1.next.next = mtsl.new ListNode(8);
		
		ListNode l2 = mtsl.new ListNode(3);
		l2.next = mtsl.new ListNode(4);
		
		ListNode root = mtsl.mergeTwoLists(l1, l2);
		while (root != null) {
			System.out.printf(root.val + " ");
			root = root.next;
		}
		System.out.println();
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode root = l1.val < l2.val ? l1 : l2;
		ListNode runner1 = root; // starts from the smaller node
		ListNode runner2 = l1 == runner1 ? l2 : l1; // the second runner starts
													// from the other node

		// run while still have more elements
		while (runner2 != null) {
			
			while (runner1.next != null && runner1.val == runner1.next.val) {
				runner1 = runner1.next; // run through all duplicates
			}

			// compare and append the smaller one
			if (runner1.val < runner2.val) {
				ListNode nextOfRunner2 = runner2.next;
				runner2.next = runner1.next;
				runner1.next = runner2;
				runner2 = nextOfRunner2;
				runner1 = runner1.next;
			} else if (runner1.val == runner2.val) {
				runner1 = runner1.next;
			} else {
				break; // this never happens
			}

		}

		return root;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}
