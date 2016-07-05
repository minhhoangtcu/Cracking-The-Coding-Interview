package ctcInterview.stacksAndQueues;

import java.util.Iterator;
import java.util.Stack;

public class SQ34_TowerOfHanoi {
	
	public static void main(String[] args) {
		
		int numOfDisks = 3; 
		
		SQ34_TowerOfHanoi toh = new SQ34_TowerOfHanoi();
		Tower[] towers = new Tower[3];
		
		for (int i = 0; i < 3; i++) {
			towers[i] = toh.new Tower(i+1);
		}
		
		for (int i = numOfDisks-1; i >= 0; i--) {
			towers[0].put(i);
		}
		
		System.out.printf("<<< STARTING TOWER OF HANOI FOR %d DISKS >>>\n", numOfDisks);
		towers[0].move(numOfDisks, towers[2], towers[1]);
		for (int i = 0; i < 3; i++) {
			towers[i].printTower();
		}
		System.out.println("<<< FINISHED TOWER OF HANOI >>>");
	}
	
	class Tower {
		
		int size;
		Stack<Integer> stack; // Assume that the disks are integers and BIGGER number means bigger disk.
		int id;
		
		public Tower(int id) {
			this.id = id;
			size = 0;
			stack = new Stack<>();
		}
		
		public void put(int disk) {
			if (!stack.isEmpty() && stack.peek() <= disk)
				System.err.println("Cannot put bigger disk on smaller disk!");
			stack.push(disk);
			size++;
		}
		
		public int take() {
			return stack.pop();
		}
		
		public void move(int numOfDisks, Tower destination, Tower buffer) {
			
			if (numOfDisks > size)
				System.err.println("Cannot move more disks than what the tower has!");
			else if (numOfDisks == 1) {
				System.out.printf("%d -> %d\n", id, destination.id);
				destination.put(take());
			}
			else {
				move(numOfDisks-1, buffer, destination);
				move(1, destination, null);
				buffer.move(numOfDisks-1, destination, this);
			}
		}
		
		public void printTower() {
			Iterator<Integer> iter = stack.iterator();
			
			System.out.printf("ID %d: ", id);
			while (iter.hasNext()) {
				System.out.printf("%d ", iter.next());
			}
			System.out.println();
		}
	}

}
