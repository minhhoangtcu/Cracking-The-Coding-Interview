package epi.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a program which takes as input an array of digits encoding a decimal
 * number D and updates the array to represent the number D + 1. For example, if
 * the input is (1,2,9) then you should update the array to (1,3,0). Your
 * algorithm should work even if it is implemented in a language that has
 * finite-precision arithmetic.
 * 
 * @author minhhoang
 *
 */
public class A62_Increment {
	
	public static void main(String[] args) {
		
		A62_Increment inc = new A62_Increment();
		
		System.out.println(Arrays.toString(inc.increase(new int[] {1, 2, 9})));
		System.out.println(Arrays.toString(inc.increase(new int[] {1, 0, 0})));
		System.out.println(Arrays.toString(inc.increase(new int[] {9, 9})));
		System.out.println(Arrays.toString(inc.increase(new int[] {0})));
		System.out.println(Arrays.toString(inc.increase(new int[] {9})));
		System.out.println(Arrays.toString(inc.increase(new int[] {})));
		System.out.println(Arrays.toString(inc.increase(new int[] {9, 9, 9})));
		
	}
	
	public int[] increase(int[] number) {
		if (number == null || number.length == 0) {
			return new int[] {1};
		}
		
		List<Integer> result = new LinkedList<>();
		boolean isCarry = true;
		
		for (int i = number.length - 1; i >= 0; i--) {
			// Get the result for the digit and sum with the carry
			int sum = number[i];
			if (isCarry) sum++;
			
			result.add(0, sum % 10);
			isCarry = sum >= 10;
		}
		
		if (isCarry) result.add(0, 1);
		
		return result.stream().mapToInt(i -> i).toArray();
		
	}

}
