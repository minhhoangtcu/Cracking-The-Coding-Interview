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

	public void push(int stackNum, String item) {
		if (!isValidStackNum(stackNum))
			throw new IllegalArgumentException("Provided stack number is not valid. Please enter 1, 2 or 3.");
		else if (isFull(stackNum)) {
			expand(stackNum);
		}
		// Add the item into the staack
		content[pointers[stackNum]] = item;
		pointers[stackNum]++;
		currentSizes[stackNum]++;

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

	private boolean isFull(int stackNum) {
		return currentSizes[stackNum] == maxSizes[stackNum];
	}

	private boolean isValidStackNum(int stackNum) {
		if (stackNum >= 1 && stackNum <= 3)
			return true;
		else
			return false;
	}

}
