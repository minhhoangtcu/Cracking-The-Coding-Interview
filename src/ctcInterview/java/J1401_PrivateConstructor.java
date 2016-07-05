package ctcInterview.java;

/**
 * In terms of inheritance, what is the effect of keeping a constructor private?
 * 
 * @author minhhoang
 *
 */
public class J1401_PrivateConstructor /*extends J1401_PrivateParent*/ {
	
	/**
	 * In general, the effect of keeping a constructor private is:
	 * 1. For a class containing only static utility methods
	 * 2. For a class containing constants
	 * 3. For a singleton class
	 * 
	 * In terms of inheritance, 
	 */
	
	private J1401_PrivateConstructor() { }
	
	private void doStuff() {
		
	}

	class SubClass {
		
		private void doStuff() {
			
			// A sub class can access everything!
			J1401_PrivateConstructor pc = new J1401_PrivateConstructor();
			pc.doStuff();
			/**
			 * Conclusion:
			 * 
			 * Let A be a main class and B, C, D be inner classes of A
			 * If B has private constructor, then only C and D can access A.
			 * --> how is this useful? I am not sure to be honest...
			 * 
			 */
			
		}
		
	}
	
}
