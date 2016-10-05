package selfstudy;

import java.util.ArrayList;
import java.util.Arrays;

public class CutTheSticks {
	
	public static void main(String[] args) {
		
		//int[] lengths = {5, 4, 4, 2, 2, 8};
		//int[] lengths = {1, 2, 3, 4, 3, 3, 2, 1};
		int[] lengths = {12};
		
		for (int num: cutSticks(lengths)) {
			System.out.print(num + " ");
		}
		System.out.println();
		
	}
	
	static int[] cutSticks(int[] lengths) {
		
		if (lengths.length == 0)
			return new int[] {};
		else if (lengths.length == 1)
			return new int[] {1};
        
		ArrayList<Integer> cuts = new ArrayList<>();
        Arrays.sort(lengths);
        
        int lastNumber = lengths[0];
        
        // cut for the first number
        cuts.add(lengths.length);
        
        // cut for the remaining number
        for (int i = 1; i < lengths.length; i++) {
        	if (lengths[i] != lastNumber) {
        		lastNumber = lengths[i];
        		cuts.add(lengths.length -  i);
        	}
        }
        
        // return result
        int[] result = new int[cuts.size()];
        for (int i = 0; i < cuts.size(); i++) {
        	result[i] = cuts.get(i);
        }
        
        return result;
    }

}
