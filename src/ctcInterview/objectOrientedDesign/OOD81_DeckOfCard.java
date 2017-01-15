package ctcInterview.objectOrientedDesign;

import java.util.List;

/**
 * Design the data structures for a generic deck of cards. Explain how you would
 * subclass the data structures to implement blackjack.
 * 
 * @author minhhoang
 *
 */
public class OOD81_DeckOfCard {
	
	/*
	 * 1. Handle Ambiguity
	 * 2. Define the Core Objects
	 * 3. Analyze Relationships
	 * 4. Investigate Actions
	 * 
	 * - What is the deck used for?
	 *   + Normal card game? Tiến lên   
	 *   + Poker
	 *   + Black Jack
	 *   + Uno? <-- ignore this for now
	 * --> So we assume that we are programming only the 52 card deck
	 * 
	 * - What do these games have in common?
	 *   + Each card has a value (ace in black jack is weird)
	 *   + Each card has a suite and a rank
	 *   + Must be able to get a value from a hand
	 */

}

enum Suite {
	Club, Diamond, Heart, Spade
}

class Deck {
	
}

abstract class Card {
	
	private int rank;
	private Suite suite;
	
	public Card(int rank, Suite suite) {
		this.rank = rank;
		this.suite = suite;
	}
	
	public abstract int getValue();
	
	public int getRank() {
		return rank;
	}
	
	public Suite getSuite() {
		return suite;
	}
	
}

class Hand <T extends Card> {
	
	protected List<T> allCards;
	
	public int getScore() {
		return allCards.stream().mapToInt(T::getValue).sum();
	}
}

class BJCard extends Card {
	
	public BJCard(int rank, Suite suite) {
		super(rank, suite);
	}
	
	public int getValue() {
		return getRank(); // Hand will handle it 
	}
	
	public boolean isAce() {
		return getRank() == 14;
	}
	
}

class BJHand extends Hand<BJCard> {
	
	public int getScore() {
		if (containsAce()) {
			return getMax(0, 0);
		} else {
			return allCards
				     .stream()
				     .mapToInt(card -> {
				    	 return card.getRank() > 10 && card.getRank() < 14 
				    			? 10
				    		    : card.getRank();
				     })
				     .sum();
		}
	}
	
	// Get the max value for a hand under 21. Returns -1 if busted.
	private int getMax(int currentMax, int index) {
		
		if (currentMax > 21) {
			return -1; // busted
		} else if (index >= allCards.size()) {
			return currentMax;
		}
		
		if (allCards.get(index).isAce()) {
			return Math.max(
					 getMax(currentMax + 10, index+1),
					 getMax(currentMax + 1, index+1)
				   );
		} else {
			return getMax(currentMax + allCards.get(index).getValue(), index+1);
		}
		
	}
	
	private boolean containsAce() {
		
		for (BJCard c: allCards) {
			if (c.isAce()) {
				return true;
			}
		}
		
		return false;
	}
}