package interviewBit;

import java.util.LinkedList;

public class ZigZagString {

	public static void main(String[] args) {
		ZigZagString zzs = new ZigZagString();
		System.out.println(zzs.convert("123456789abcdefghiklmn", 5));
	}

	public String convert(String a, int b) {

		if (b <= 1 || a.length() <= b)
	        return a;
		
		LinkedList<LinkedList<Character>> rows = new LinkedList<>();

		for (int row = 0; row < b; row++) {

			rows.add(new LinkedList<Character>());

			// Spaces between 2 characters on the same line
			int space = 0;
			int j = row;
			int rowSwap = row;
			
			// Put the char into the rows
			while (j < a.length()) {
				rows.get(row).add(a.charAt(j));

				if (rowSwap < b - 1 && rowSwap > 0)
					space = 2 * (b - rowSwap) - 2;
				else
					space = 2 * b - 2;
				rowSwap = (b - 1) - rowSwap; // swap row
				
				j += space;
			}

		}

		// Output
		StringBuilder sb = new StringBuilder();
		for (LinkedList<Character> row : rows) {
			for (Character c : row) {
				sb.append(c);
			}
			// sb.append('\n');
		}

		return sb.toString();
	}
}
