package interviewBit;

import java.util.ArrayList;

public class MergeTwoSortedList {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> a = new ArrayList<>();
		a.add(-4); a.add(3);
		ArrayList<Integer> b = new ArrayList<>();
		b.add(-2); b.add(-2);
		
		merge(a, b);
		for(Integer num: a) {
			System.out.print(num + " ");
		}
		
	}
	
	public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
	    
	    int pointerA = 0;
	    int pointerB = 0;
	    int m = a.size();
	    int n = b.size();
	    
	    while (m > 0 || n > 0) {
	        if (m == 0) {
	            a.add(b.get(pointerB++)); // a is empty, put all leftover of b into a
	            n--;
	        } else if (n == 0) {
	            // b is empty, completed
	            break;
	        } else if (a.get(pointerA) > b.get(pointerB)) {
	            a.add(pointerA++, b.get(pointerB++));
	            n--;
	        } else { //if (a.get(pointerA) <= b.get(pointerB)) {
	            pointerA++;
	            m--; // pass through an ele in a
	        }
	    }
	    
	}

}
