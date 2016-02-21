package hackerrank.contest.worldCodeSpirit;

import java.io.*;
import java.util.*;

public class AlienFlowers {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num11 = sc.nextInt();
		int num10 = sc.nextInt();
		int num00 = sc.nextInt();
		int num01 = sc.nextInt();
		
		if (num11 == 0 && num10 == 0 && num00 == 0 && num01 == 0) {
			System.out.println(0);
			return;
		}
		
		int chains = 0, maxBits = 2 * (num11 + num10 + num00 + num01), minBits = num11 + num10 + num00 + num01;
		BitList list = new BitList();
		list.root = new Node(false);
		list.size = 1;
		
		while (list.size < minBits) {
			list.addNodeForce(new Node(false));
		}

		while (list.size <= maxBits) {

			int r11 = 0, r10 = 0, r00 = 0, r01 = 0;

			Node runner = list.root;
			while (runner.next != null) {
				if (runner.bit && runner.next.bit)
					r11++;
				else if (runner.bit && !runner.next.bit)
					r10++;
				else if (!runner.bit && !runner.next.bit)
					r00++;
				else if (!runner.bit && runner.next.bit)
					r01++;
				runner = runner.next;
			}
			if (r11 == num11 && r10 == num10 && r00 == num00 && r01 == num01) {
				chains++;
//				System.out.printf("Found! %d %d %d %d\n", r11, r10, r00, r01);
//				printList(list);
			}
				

			
			list.addNode();
		}
		
		System.out.println((int) (chains % (Math.pow(10, 9)+7)));
		sc.close();

	}
	 
	private static void printList(BitList list) {
		Node runner = list.root;
		while (runner != null) {
			System.out.printf("%d", runner.bit ? 1:0);
			runner = runner.next;
		}
		System.out.println();
	}
}

class BitList {

	Node root;
	int size;

	public BitList() {
		root = null;
		size = 0;
	}

	public void addNode() {
		if (root.add())
			size++;
	}
	
	public void addNodeForce(Node node) {
		Node runner = root;
		while (runner.next != null) {
			runner = runner.next;
		}
		runner.next = node;
		size++;
	}

}

class Node {

	Node next;
	boolean bit;

	public Node(boolean bit) {
		this.bit = bit;
	}

	// true if make new node
	public boolean add() {
		if (bit) {
			bit = false;
			if (next != null)
				return next.add();
			else {
				next = new Node(false);
				return true;
			}
		} else
			bit = true;
		return false;
	}

}
