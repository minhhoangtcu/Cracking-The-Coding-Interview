package hackerrank.contest.worldCodeSpirit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GenaPlayingHanoi {

	public static void main(String[] args) {

		GenaPlayingHanoi gph = new GenaPlayingHanoi();
		Integer[] initialState = {1, 4, 1};
		
		State initS = gph.new State(initialState, 0);
		
		System.out.println(gph.getFewestMoves(initS));

	}

	public int getFewestMoves(State initS) {
		
		Queue<State> toExplore = new LinkedList<>();
		Set<Integer[]> visited = new HashSet<>();
		toExplore.add(initS);
		
		while (!toExplore.isEmpty()) {
			
			State state = toExplore.remove();
			if (state.isFinished())
				return state.depth;
			visited.add(state.belongToPeg);
			printArray(state.belongToPeg); System.out.printf(" depth: %d\n", state.depth);
			
			for (int peg = 1; peg < 5; peg++) {
				for (int pegDes = 1; pegDes < 5; pegDes++) {
					if (peg != pegDes) {
						Integer[] belongToPeg = state.move(peg, pegDes);
						if (belongToPeg != null && !contains(visited, belongToPeg)) {
							toExplore.add(new State(belongToPeg, state.depth+1));
						}
					}
				}
			}
		}
		
		// Explored everything. Found nothing.
		return -1;
	}
	
	private void printArray(Integer[] array) {
		for (Integer num: array) {
			System.out.printf("%d ", num);
		}
//		System.out.println();
	}
	
	private boolean contains(Set<Integer[]> visited, Integer[] visiting) {
		for (Integer[] current: visited) {
			if (Arrays.equals(current, visiting))
				return true;
		}
		return false;
	}

	class State {

		Integer[] belongToPeg;
		int depth;

		public State(Integer[] state, int depth) {
			this.belongToPeg = state;
			this.depth = depth;
		}

		/**
		 * Look at the size of top disk of provided peg.
		 * 
		 * @param peg
		 * @return
		 */
		public int peek(int peg) {
			for (int i = 0; i < belongToPeg.length; i++) {
				if (belongToPeg[i] == peg)
					return i;
			}
			return -1; // Nothing at provided peg
		}

		/**
		 * Move top disk of provided peg to destination peg.
		 * 
		 * @param peg
		 * @param pegDes
		 * @return new state array
		 */
		public Integer[] move(int peg, int pegDes) {
			
			int desSize = peek(pegDes);
			
			if (desSize == -1) {
				for (int i = 0; i < belongToPeg.length; i++) {
					if (belongToPeg[i] == peg) {
						Integer[] result = belongToPeg.clone();
						result[i] = pegDes;
						return result;
					}
				}
			}
			else {
				// Move smallest disk of provided disk. Only take small peg on top.
				for (int i = 0; i < desSize; i++) {
					if (belongToPeg[i] == peg) {
						Integer[] result = belongToPeg.clone();
						result[i] = pegDes;
						return result;
					}
				}
			}
			
			// Illegal move, returning -1
			return null;
		}
		
		public boolean isFinished() {
			for (int i = 0; i < belongToPeg.length; i++) {
				if (belongToPeg[i] != 1)
					return false;
			}
			return true;
		}

	}

}
