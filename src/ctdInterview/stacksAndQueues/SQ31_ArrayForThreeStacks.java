package ctdInterview.stacksAndQueues;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Describe how to use a single array to implement three stacks
 * 
 * @author Minh Hoang
 *
 */
public class SQ31_ArrayForThreeStacks {

	int[] maxSizes;
	int[] currentSizes;
	int[] pointers;
	String[] content;

	public static void main(String[] args) {
		
		System.out.println("Check for pushing then peeking immediately");
		SQ31_ArrayForThreeStacks peekingStacks = new SQ31_ArrayForThreeStacks();
		peekingStacks.push(1, "Minh11");
		System.out.println(peekingStacks.peek(1));
		peekingStacks.push(1, "Minh12");
		System.out.println(peekingStacks.peek(1));
		peekingStacks.push(2, "Minh21");
		System.out.println(peekingStacks.peek(2));
		peekingStacks.push(2, "Minh22");
		System.out.println(peekingStacks.peek(2));
		peekingStacks.push(3, "Minh31");
		System.out.println(peekingStacks.peek(3));
		peekingStacks.push(3, "Minh32");
		System.out.println(peekingStacks.peek(3));
		System.out.println();
		
		System.out.println("Check for auto expanding");
		SQ31_ArrayForThreeStacks autoExpandStacks = new SQ31_ArrayForThreeStacks(3);
		autoExpandStacks.push(1, "Minh1");
		autoExpandStacks.push(1, "Minh2");
		autoExpandStacks.push(1, "Minh3");
		autoExpandStacks.push(1, "Minh4");
		autoExpandStacks.push(1, "Minh5");
		autoExpandStacks.push(1, "Minh6");
		autoExpandStacks.push(1, "Minh7");
		autoExpandStacks.push(1, "Minh8");
		autoExpandStacks.push(1, "Minh9");
		autoExpandStacks.push(1, "Minh10");
		autoExpandStacks.push(1, "Minh11");
		autoExpandStacks.push(1, "Minh12");
		autoExpandStacks.push(1, "Minh13");
		autoExpandStacks.push(1, "Minh14");
		autoExpandStacks.push(1, "Minh15");
		autoExpandStacks.push(1, "Minh16");
		System.out.println(autoExpandStacks.peek(1));
		
		autoExpandStacks.push(2, "Minh1");
		autoExpandStacks.push(2, "Minh2");
		autoExpandStacks.push(2, "Minh3");
		autoExpandStacks.push(2, "Minh4");
		autoExpandStacks.push(2, "Minh5");
		autoExpandStacks.push(2, "Minh6");
		System.out.println(autoExpandStacks.peek(2));
		
		autoExpandStacks.push(3, "Minh1");
		autoExpandStacks.push(3, "Minh2");
		autoExpandStacks.push(3, "Minh3");
		autoExpandStacks.push(3, "Minh4");
		autoExpandStacks.push(3, "Minh5");
		autoExpandStacks.push(3, "Minh6");
		autoExpandStacks.push(3, "Minh7");
		autoExpandStacks.push(3, "Minh8");
		System.out.println(autoExpandStacks.peek(3));
		
		System.out.println(autoExpandStacks.peek(1));
		System.out.println(autoExpandStacks.peek(2));
		System.out.println(autoExpandStacks.peek(3));
		System.out.println();
		
		System.out.println("Check for elements popping");
		SQ31_ArrayForThreeStacks smallStacks = new SQ31_ArrayForThreeStacks(5);
		smallStacks.push(1, "Minh1");
		System.out.println(smallStacks.pop(1));
		smallStacks.push(1, "Minh1");
		smallStacks.push(1, "Minh2");
		System.out.println(smallStacks.pop(1));
		System.out.println(smallStacks.pop(1));
		
	}
	
	public SQ31_ArrayForThreeStacks(int defaultSize) {
		maxSizes = new int[3];
		currentSizes = new int[3];
		pointers = new int[3];
		content = new String[defaultSize * 3];
		for (int i = 0; i < 3; i++) {
			maxSizes[i] = defaultSize;
			pointers[i] = i * defaultSize;
		}
	}

	public SQ31_ArrayForThreeStacks() {
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
	
	public String pop(int stackNum) {
		int stackIndex = stackNum-1;
		if (!isValidStackNum(stackNum))
			throw new IllegalArgumentException("Provided stack number is not valid. Please enter 1, 2 or 3.");
		else if (isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		
		pointers[stackIndex]--;
		String output = content[pointers[stackIndex]]; 
		content[pointers[stackIndex]] = null;
		currentSizes[stackIndex]--;
		return output;
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
		return currentSizes[stackNum-1] == maxSizes[stackNum-1];
	}
	
	private boolean isEmpty(int stackNum) {
		return currentSizes[stackNum-1] == 0;
	}

	private boolean isValidStackNum(int stackNum) {
		if (stackNum >= 1 && stackNum <= 3)
			return true;
		else
			return false;
	}

}
