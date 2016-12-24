package epi.strings;

/**
 * Define a palindromic string to be a string which when all the nonalphanumeric
 * are removed it reads the same front to back ignoring case. For example,
 * "A man, a plan, a canal, Panama." are palindromic, but "Ray a Ray" is not.
 */
public class S75_TestPalindromicity {
	
	public static void main(String[] args) {
		
		boolean pali = false;
		String text = "";
		S75_TestPalindromicity tp = new S75_TestPalindromicity();
		
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text="A man, a plan, a canal, Panama.", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text="Ray a Ray", false, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text="  ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" a ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" a a ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" a b 1 ", false, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" 1 b 1 ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text="1 b 1 ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" 1 b 1", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" 1 bb 1 ", true, pali=tp.isPalindromic(text));
		System.out.printf("'%s'\tExpected: %s\tActual: %s\n", text=" 1 babab      1 ", true, pali=tp.isPalindromic(text));
		
	}
	
	public boolean isPalindromic(String text) {
		
		boolean isPalindromic = true;
		
		// Corner case
		if (text == null || text.length() == 0) {
			return true;
		}
		
		// Use two pointers to check if front and back are the same, ignore nonalphanumeric
		int left = 0;
		int right = text.length() - 1;
		
		while (left < right) {
			
			char leftChar = Character.toLowerCase(text.charAt(left));
			char rightChar = Character.toLowerCase(text.charAt(right));
			
			if (!Character.isLetterOrDigit(leftChar)) {
				left++;
			} else if (!Character.isLetterOrDigit(rightChar)) {
				right--;
			} else if (leftChar != rightChar) {
				isPalindromic = false;
				break;
			} else {
				left++;
				right--;
			}
		}
		
		return isPalindromic;
		
	}
	
}
