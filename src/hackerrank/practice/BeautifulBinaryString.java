package hackerrank.practice;

import java.util.Scanner;

public class BeautifulBinaryString {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numOfChars = sc.nextInt();

		if (numOfChars <= 2) {
			System.out.println(0);
			sc.close();
			return;
		}

		int steps = 0;
		sc.nextLine(); // read first line
		char[] characters = sc.nextLine().toCharArray();

		// Go through the array for the first time, changing all 01010 to 01110
		for (int i = 4; i < numOfChars; i++) {
			char previous1 = characters[i - 4];
			char previous2 = characters[i - 3];
			char previous3 = characters[i - 2];
			char previous4 = characters[i - 1];
			char current = characters[i];

			if (previous1 == 0 && previous2 == 1 && previous3 == 0 && previous4 == 1 && current == 0) {
				characters[i - 2] = '1';
				steps++;
			}
		}

		// Go through the array again, changing all 010 to 000
		for (int i = 2; i < numOfChars; i++) {
			char previous1 = characters[i - 2];
			char previous2 = characters[i - 1];
			char current = characters[i];

			if (previous1 == 0 && previous2 == 1 && current == 0) {
				characters[i - 1] = '0';
				steps++;
			}
		}

		System.out.println(steps);
		
		sc.close();
	}

}
