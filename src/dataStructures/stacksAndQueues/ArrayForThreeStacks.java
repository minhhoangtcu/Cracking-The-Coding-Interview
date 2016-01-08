package dataStructures.stacksAndQueues;

import java.util.Arrays;

/**
 * Describe how to use a single array to implement three stacks
 * 
 * @author Minh Hoang
 *
 */
public class ArrayForThreeStacks {

	int[] maxSizes;
	int[] currentSizes;
	int[] pointers;
	String[] content;

	public static void main(String[] args) {
		
		System.out.println("Check for pushing then peeking immediately");
		ArrayForThreeStacks stacks = new ArrayForThreeStacks();
		stacks.push(1, "Minh11");
		System.out.println(stacks.peek(1));
		stacks.push(1, "Minh12");
		System.out.println(stacks.peek(1));
		stacks.push(2, "Minh21");
		System.out.println(stacks.peek(2));
		stacks.push(2, "Minh22");
		System.out.println(stacks.peek(2));
		stacks.push(3, "Minh31");
		System.out.println(stacks.peek(3));
		stacks.push(3, "Minh32");
		System.out.println(stacks.peek(3));
		System.out.println();
		
		System.out.println("Check for auto expanding");
		ArrayForThreeStacks smallStacks = new ArrayForThreeStacks(3);
		smallStacks.push(1, "Minh1");
		smallStacks.push(1, "Minh2");
		smallStacks.push(1, "Minh3");
		smallStacks.push(1, "Minh4");
		smallStacks.push(1, "Minh5");
		smallStacks.push(1, "Minh6");
		smallStacks.push(1, "Minh7");
		smallStacks.push(1, "Minh8");
		smallStacks.push(1, "Minh9");
		smallStacks.push(1, "Minh10");
		smallStacks.push(1, "Minh11");
		smallStacks.push(1, "Minh12");
		smallStacks.push(1, "Minh13");
		smallStacks.push(1, "Minh14");
		smallStacks.push(1, "Minh15");
		smallStacks.push(1, "Minh16");
		System.out.println(smallStacks.peek(1));
		
		smallStacks.push(2, "Minh1");
		smallStacks.push(2, "Minh2");
		smallStacks.push(2, "Minh3");
		smallStacks.push(2, "Minh4");
		smallStacks.push(2, "Minh5");
		smallStacks.push(2, "Minh6");
		System.out.println(smallStacks.peek(2));
		
		smallStacks.push(3, "Minh1");
		smallStacks.push(3, "Minh2");
		smallStacks.push(3, "Minh3");
		smallStacks.push(3, "Minh4");
		smallStacks.push(3, "Minh5");
		smallStacks.push(3, "Minh6");
		smallStacks.push(3, "Minh7");
		smallStacks.push(3, "Minh8");
		System.out.println(smallStacks.peek(3));
		
		System.out.println(smallStacks.peek(1));
		System.out.println(smallStacks.peek(2));
		System.out.println(smallStacks.peek(3));
	}
	
	public ArrayForThreeStacks(int defaultSize) {
		maxSizes = new int[3];
		currentSizes = new int[3];
		pointers = new int[3];
		content = new String[defaultSize * 3];
		for (int i = 0; i < 3; i++) {
			maxSizes[i] = defaultSize;
			pointers[i] = i * defaultSize;
		}
	}

	public ArrayForThreeStacks() {
		this(10);
	}
	
	public String peek(int stackNum) {
		int stackIndex = stackNum-1;
		return content[pointers[stackIndex]-1];
	}

	public void push(int stackNum, String item) {
		int stackIndex = stackNum-1;
		if (!isValidStackNum(stackNum))
			throw new IllegalArgumentException("Provided stack number is not valid. Please enter 1, 2 or 3.");
		else if (isFull(stackNum)) {
			expand(stackNum);
		}
		// Add the item into the staack
		
		content[pointers[stackIndex]] = item;
		pointers[stackIndex]++;
		currentSizes[stackIndex]++;

	}

	private void expand(int stackNum) {
		// Make a new array
		int newSize = content.length + maxSizes[stackNum-1];
		String[] newContent = new String[newSize];
		
		// Copy all elements from the left of the expanding stack to the new array
		// Copy all elements from the expanding stack to the new array. Fix the new max size.
		// Copy all elements from the right of the expanding stack to the new array. Fix the new pointer.
		// Replace the original stacks with the new one.
		if (stackNum == 1) {
			int newTempPointer = 0;
			for (int i = 0; i < content.length; i++) {
				newContent[newTempPointer] = content[i];
				newTempPointer++;
				if (newTempPointer == maxSizes[stackNum-1]) {
					newTempPointer += maxSizes[stackNum-1];
				}
			}
			pointers[stackNum] += maxSizes[stackNum-1];
			pointers[stackNum+1] += maxSizes[stackNum-1];
			maxSizes[stackNum-1] = maxSizes[stackNum-1] * 2;
					
		}
		else if (stackNum == 2) {
			int newTempPointer = 0;
			for (int i = 0; i < content.length; i++) {
				newContent[newTempPointer] = content[i];
				newTempPointer++;
				if (newTempPointer == maxSizes[stackNum-1] + maxSizes[stackNum-2]) {
					newTempPointer += maxSizes[stackNum-1];
				}
			}
			pointers[stackNum] += maxSizes[stackNum-1];
			maxSizes[stackNum-1] = maxSizes[stackNum-1] * 2;
		}
		else if (stackNum == 3) {
			for (int i = 0; i < content.length; i++) {
				newContent[i] = content[i];
			}
			maxSizes[stackNum-1] = maxSizes[stackNum-1] * 2;
		}
		
		content = newContent;
	}

	private boolean isFull(int stackIndex) {
		return currentSizes[stackIndex-1] == maxSizes[stackIndex-1];
	}

	private boolean isValidStackNum(int stackNum) {
		if (stackNum >= 1 && stackNum <= 3)
			return true;
		else
			return false;
	}

}
