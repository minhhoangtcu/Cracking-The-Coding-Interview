package interviewBit;

import java.util.ArrayList;

public class RearrangeArray {
	
	public static void main(String[] args) {
		
		RearrangeArray ra = new RearrangeArray();
		ArrayList<Integer> a = new ArrayList<>();
		a.add(4);
		a.add(0);
		a.add(2);
		a.add(1);
		a.add(3);
		ra.arrange(a);
		
	}
    
	public void arrange(ArrayList<Integer> a) {
	    
	    int index = 0;
	    int value = a.get(0);
	    
	    while (index < a.size()) {
	        
	        // Let negative number be visited/changed number
	        while (value >= 0) {
	            int newValue = a.get(value);
	            
	            if (newValue != 0)
	                a.set(value, -newValue); // Set as visited
	            else
	                a.set(value, Integer.MIN_VALUE); // Because there is no neg of 0, set it as min value
	            
	            a.set(newValue, value); // Rearrange
	            value = newValue;
	        }
	        
	        // If we came into a visited number -> look for nearest unvisited
	        index++;
	        if (a.get(value) < 0 && a.get(index) > 0)
	            value = index;
	        
	    }
	    
	    // If the algorithm is correct, then all number should be negative
	    for (int i = 0; i < a.size(); i++) {
	        if (a.get(i) == Integer.MIN_VALUE)
	            a.set(i, 0);
	        else
	            a.set(i, -a.get(i));
	        
	    }
	    
	}
}