package hackerrank.practice;

public class Xor {
	
	public static void main(String[] args) {
		
		System.out.println(1 ^ 2);
		System.out.println(2 ^ 1);

		
		
		
		System.out.println(2 ^ 3 ^ 4 ^ 5); // 14 010 011 100 101 -> 001 100 101 -> 101 101 -> 000
		
		System.out.println(4 ^ 5 ^ 6 ^ 7); // 22 100 101 110 111
		
		System.out.println(6 ^ 7 ^ 8 ^ 9);
		
		System.out.println(8 ^ 9 ^ 10 ^ 11); // 0
		
		System.out.println(1 ^ 2 ^ 5 ^ 6); // why is this right?
	}

}
