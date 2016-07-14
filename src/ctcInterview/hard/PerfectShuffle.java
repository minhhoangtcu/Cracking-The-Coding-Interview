package ctcInterview.hard;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle - in
 * other words, each of the 52! permutations of the deck has to be equally
 * likely. Assume that you are given a random number generator which is perfect.
 * 
 * @author minhhoang
 *
 */
public class PerfectShuffle {
	
	public static void main(String[] args) {
		PerfectShuffle ps = new PerfectShuffle();
		
		int[] cards = new int[52];
		
		for (int i = 0; i < 52; i++) {
			cards[i] = i;
		}
		
		ps.shuffle(cards);
		
		for (int i = 0; i < 52; i++) {
			System.out.println(cards[i]);
		}
	}
	
	// assume that this random func is perfect and get a random number from lower-higher exlusive
	private int rand(int lower, int higher) {
		return lower + (int) (Math.random() * (higher - lower + 1));
	}

	/**
	 * If we want to shuffle n element, we just need to shuffle n-1 elements and
	 * swap the nth one with a random element in the already shuffled n-1
	 * elements
	 * 
	 * @param cards
	 */
	public void shuffle(int[] cards) {
		for (int nth = 1; nth < cards.length; nth++) {
			int randomCard = rand(0, nth);
			swap(cards, nth, randomCard);
		}
	}
	
	// assume that a and b are always valid
	private void swap(int[] cards, int a, int b) {
		int temp = cards[a];
		cards[a] = cards[b];
		cards[b] = temp;
	}

}
