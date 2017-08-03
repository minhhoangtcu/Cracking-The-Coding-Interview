package epi.strings;

public class S706_ReverseWordsInSentence {
	
	public static void main(String[] args) {
		
		S706_ReverseWordsInSentence reverse = new S706_ReverseWordsInSentence();
		String text = "";
		
		System.out.printf("'%s' -> '%s'\n", text="a b c", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="abd b c", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text=" abd   b   c ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text=" abd  as 1 4 sad b   c ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text=" abd  as 1 4 sad b   c", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="abd  as 1 4 sad b   c ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="  abd    as 1 4 sad b   c      ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="a", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text=" b ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="  c  ", reverse.reverse(text));
		System.out.printf("'%s' -> '%s'\n", text="   ", reverse.reverse(text));
		
	}
	
	public String reverse(String text) {
		
		StringBuilder sb = new StringBuilder();
		
		// Go from the end to the front. As soon as we hit a white space -> we found a word
		StringBuilder buffer = new StringBuilder();
		int i = text.length() - 1;
		boolean isLastAChar = false;
		
		while (i >= 0) {
			char currentChar = text.charAt(i);
			
			if (currentChar == ' ' && isLastAChar) {
				// Reached the end of a word
				sb.append(buffer);
				sb.append(' ');
				buffer = new StringBuilder();
			} else if (Character.isLetterOrDigit(currentChar)) {
				buffer.insert(0, currentChar);
			}
			
			i--;
			isLastAChar = Character.isLetterOrDigit(currentChar);
		}
		
		// dump out last word
		if (buffer.length() != 0) {
			sb.append(buffer);
		}
		
		return sb.toString();
		
	}
}
