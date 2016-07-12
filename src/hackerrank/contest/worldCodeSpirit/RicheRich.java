package hackerrank.contest.worldCodeSpirit;

import java.util.Scanner;

public class RicheRich {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int digits = sc.nextInt();
		int maxChanges = sc.nextInt();
		int num = sc.nextInt();

		int endIndex = digits / 2;

		String straight = Integer.toString(num);
		String reversed = reverse(straight);

		int changes = 0;
		StringBuilder result = new StringBuilder();

		// Check for changes and build up palimdrome
		for (int i = 0; i < endIndex; i++) {
			int sC = Character.getNumericValue(straight.charAt(i));
			int rC = Character.getNumericValue(reversed.charAt(i));
			if (sC != rC) {
				changes++;
				result.append(Math.max(sC, rC));
			} else {
				result.append(sC);
			}

			if (changes > maxChanges) {
				System.out.println(-1);
			}
		}

		String remainingReversed = reverse(result.toString());

		// Get the remaining character
		if (digits % 2 == 1)
			result.append(straight.charAt(endIndex));

		result.append(remainingReversed);

		System.out.println(result.toString());
	}

	private static String reverse(String input) {
		return new StringBuilder(input).reverse().toString();
	}
}
