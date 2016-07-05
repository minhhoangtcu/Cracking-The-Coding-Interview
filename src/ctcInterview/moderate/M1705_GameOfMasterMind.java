package ctcInterview.moderate;

import java.util.HashMap;

/**
 * The Game of Master Mind is played as follows
 * 
 * The computer has four slots, and each slot will contain a ball that is red
 * (R), yellow (Y), green (G), or blue. For example, the computer might have
 * RGGB (Slot #1 is red, ...)
 * 
 * You, the user, are trying to guess the solution, you might for example, guess
 * YRGB.
 * 
 * When you guess the correct color for the correct slot, you get a "hit". If
 * you guess a color that exists but is in the wrong slot, you get a
 * "pseudo-hit." Note that a slot that is a hit can never count as a pseudo-hit.
 * 
 * @author minhhoang
 *
 */
public class M1705_GameOfMasterMind {
	
	public static void main(String[] args) {
		
		M1705_GameOfMasterMind game = new M1705_GameOfMasterMind();
		
		System.out.println(game.calculate("GGRR", "RGBY"));
		System.out.println(game.calculate("GGGG", "GGGG"));
		System.out.println(game.calculate("GGGG", "BBBB"));
		System.out.println(game.calculate("GGGG", "BBBG"));
	}

	enum Status {
		CONTAINED, COUNTED;
	}
	
	final char IGNORED = 1;

	public Result calculate(String guess, String solution) {

		int hits = 0, pseudoHits = 0;
		HashMap<Character, Status> solutionContains = new HashMap<>();
		char[] guessArray = guess.toCharArray();
		char[] solutionArray = solution.toCharArray();

		for (int i = 0; i < solution.length(); i++) {
			if (solutionArray[i] == guessArray[i]) {
				hits++;
				solutionArray[i] = IGNORED;
				guessArray[i] = IGNORED;
			}
				
			if (!solutionContains.containsKey(solutionArray[i]))
				solutionContains.put(solutionArray[i], Status.CONTAINED);
		}
		
		for (int i = 0; i < guess.length(); i++) {
			if (guessArray[i] == IGNORED)
				continue;
			else if (solutionContains.containsKey(guessArray[i]) && solutionContains.get(guessArray[i]) == Status.CONTAINED) {
				pseudoHits++;
				solutionContains.put(guessArray[i], Status.COUNTED);
			}
		}
		
		return new Result(hits, pseudoHits);

	}

	class Result {
		int numOfHits, numOfPseudoHits;
		public Result(int numOfHits, int numOfPseudoHits) {
			this.numOfHits = numOfHits;
			this.numOfPseudoHits = numOfPseudoHits;
		}
		@Override
		public String toString() {
			return String.format("Number of hits: %d\tNumber of pseudo-hits: %d", numOfHits, numOfPseudoHits);
		}
	}

}
