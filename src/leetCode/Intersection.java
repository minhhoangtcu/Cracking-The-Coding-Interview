package leetCode;

public class Intersection {

	public static void main(String[] args) {
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		int sizeA = getSize(headA);
		int sizeB = getSize(headB);
		int diff = Math.max(sizeA, sizeB) - Math.min(sizeA, sizeB);

		ListNode runnerA = headA;
		ListNode runnerB = headB;

		if (sizeA > sizeB) {
			while (runnerA != null && diff != 0) {
				runnerA = runnerA.next;
				diff--;
			}
		} else if (sizeA < sizeB) {
			while (runnerB != null && diff != 0) {
				runnerB = runnerB.next;
				diff--;
			}
		}

		while (runnerA != null && runnerB != null && runnerA != runnerB) {
			runnerA = runnerA.next;
			runnerB = runnerB.next;
		}

		return runnerA; // stop at intersection or return null

	}

	private int getSize(ListNode head) {

		ListNode runner = head;
		int count = 0;

		while (runner != null) {
			count++;
			runner = runner.next;
		}

		return count;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}
