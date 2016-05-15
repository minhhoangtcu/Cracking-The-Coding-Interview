package ctcInterview.moderate;

/**
 * Write a function to swap a number in place (that is, without temporary
 * variables)
 * 
 * @author minhhoang
 *
 */
public class M1701_SwapInPlace {
	
	public static void main(String[] args) {
		
		int a = 15;
		int b = 10;
		
		swap(a, b);
		
		a = 5;
		b = 20;
		
		swap(a, b);
		
	}
	
	public static void swap(int a, int b) {
		
		/*
		 * |aaaaaaaaaa|
		 * |bbbbb|diff|
		 * 
		 * Let num1 > num2 -> num1 - num2 = diff 
		 * 
		 * num1 <- num1 - num2 							= diff
		 * num2 <- diff + num2 = (num1 - num2) + num2 	= num1
		 * num1 <- num2 - diff = num1 - (num1 - num2) 	= num2
		 * 
		 */
		
		System.out.printf("a = %d\tb = %d\n", a, b);
		
		a = a - b;
		b = a + b;
		a = b - a;
		
		System.out.printf("a = %d\tb = %d\n", a, b);
		
	}

}
