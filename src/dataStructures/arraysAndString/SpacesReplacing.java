package dataStructures.arraysAndString;

import java.util.Arrays;

/**
 * <p>
 * Replace all spaces in a string with '%20'. You may assume that the string has
 * sufficient space at the end of the string to hold the additional characters,
 * and that you are given the 'true' length of the string.
 * </p>
 * <p>
 * Example: input: 'Mr John Smith ', 13 (note that there are 13 slots from M to
 * t; and we should ignore the ending spaces)<br>
 * output: 'Mr%20John%20Smith' (note that the length of this String is 17)
 * </p>
 * 
 * @author Minh Hoang
 *
 */
public class SpacesReplacing {
	
	public static void main(String[] args) {
		char[] input = {48,49,50,51,52,53};
		System.out.println(Arrays.toString(shiftRight(input, 2, 2)));
	}
	
	public static String replace(String text) {
		
		char[] charArray = text.toCharArray();
		
		
		
		return null;
	}
	
	/**
	 * Shift an array of char to the right a number of shifts and begin to shift at the provided index. 
	 * 
	 * input: [0,1,2,3,4,5], 2, 2
	 * output: [0,1,0,0,2,3,4,5]
	 * 
	 * @param charArray
	 * @param numberOfShift
	 * @param beginIndexToShift
	 * @return
	 */
	public static char[] shiftRight(char[] charArray, int numberOfShift, int beginIndexToShift) {
		
		int originalLength = charArray.length;
		
		// Append empty slots to the right of the array
		charArray = Arrays.copyOf(charArray, originalLength + numberOfShift);
		
		// Shift each element to the right with a specified number of slots
		int newPointer = charArray.length-1;
		for (int oldPointer = originalLength-1; oldPointer >= beginIndexToShift; oldPointer--) {
			charArray[newPointer] = charArray[oldPointer];
			newPointer--;
		}
		
		// Fill up the elements with 0 after shifting
		for (int i = beginIndexToShift; i < beginIndexToShift + numberOfShift; i++) {
			charArray[i] = 0; // filled up with 0 in ASCII which is NUL
		}
		
		return charArray;
	}
	
}
