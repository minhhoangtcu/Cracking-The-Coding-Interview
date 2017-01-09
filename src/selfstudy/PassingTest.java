package selfstudy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PassingTest {
	
	public static void main(String[] args) {
		
		List<Integer> input = Arrays.asList(1, 2);
		
		print(input);
		change(input);
		print(input);
		change(input);
		
		PassingTest pt = new PassingTest();
		ListTest lt = pt.new ListTest(input);
		lt.print();
		lt.change();
		lt.print();
	}
	
	public static void change(List<Integer> input) {
		Collections.swap(input, 0, 1);
	}
	
	public static void print(List<Integer> input) {
		for (Integer i : input) { System.out.print(i + " "); }
		System.out.println();
	}
	
	class ListTest {
		
		List<Integer> input;
		
		public ListTest(List<Integer> input) {
			this.input = input;
		}
		
		public void change() {
			Collections.swap(input, 0, 1);
		}
		
		public void print() {
			for (Integer i : input) { System.out.print(i + " "); }
			System.out.println();
		}
		
	}

}
