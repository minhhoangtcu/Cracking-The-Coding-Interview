package hackerrank.contest.worldCodeSpirit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GenaPlayingHanoi {

	public static void main(String[] args) {

		GenaPlayingHanoi gph = new GenaPlayingHanoi();
		int[] initialState = {1, 4, 1};
		int initialEncode = gph.encode(initialState);
		
		State initS = gph.new State(initialEncode, 3, 0);
		
		System.out.println(gph.getFewestMoves(initS));

	}

	public int getFewestMoves(State initS) {
		
		Queue<State> toExplore = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		toExplore.add(initS);
		int n = initS.n;
		
		while (!toExplore.isEmpty()) {
			
			State state = toExplore.remove();
			if (state.isFinished())
				return state.depth;
			visited.add(state.encoded);
//			System.out.println(Integer.toBinaryString(state.encoded));
			
			for (int peg = 1; peg < 5; peg++) {
				for (int pegDes = 1; pegDes < 5; pegDes++) {
					if (peg != pegDes) {
						int belongToPeg = state.move(peg, pegDes);
						if (belongToPeg != -1) {
							if (!visited.contains(belongToPeg))
								toExplore.add(new State(belongToPeg, n, state.depth+1));
						}
						
					}
				}
			}
		}
		
		// Explored everything. Found nothing.
		return -1;
	}
	
	private int encode(int[] array) {
		int result = 0;
		
		for (int i = array.length-1; i >= 0; i--) {
			result = result << 2;
			result += array[i]-1;
		}
		
		return result;
	}
	
//	private int[] decode(int encoded, int n) {
//		int[] result = new int[n];
//		
//		for (int i = n-1; i >= 0; i--) {
//			result[i] = encoded & 0b11;
//			encoded = encoded >>> 2;
//		}
//		
//		return result;
//	}
//	
//	private void printArray(Integer[] array) {
//		for (Integer num: array) {
//			System.out.printf("%d ", num);
//		}
////		System.out.println();
//	}
//	
//	private boolean contains(Set<Integer[]> visited, Integer[] visiting) {
//		for (Integer[] current: visited) {
//			if (Arrays.equals(current, visiting))
//				return true;
//		}
//		return false;
//	}

	class State {

		int encoded;
		int n;
		int depth;

		public State(int encoded, int n, int depth) {
			this.encoded = encoded;
			this.n = n;
			this.depth = depth;
		}

		/**
		 * Look at the size of top disk of provided peg.
		 * 
		 * @param peg
		 * @return
		 */
		public int peek(int peg) {
			int temp = encoded;
			
			for (int i = 0; i < n; i++) {
				
				int currentPeg = temp & 0b11;
				temp = temp >>> 2;
				
				if (getPeg(currentPeg) == peg)
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
		public int move(int peg, int pegDes) {
			
			int desSize = peek(pegDes);
			int temp = encoded;
			
			if (desSize == -1) {
				for (int i = 0; i < n; i++) {
					int currentPeg = temp & 0b11;
					temp = temp >>> 2;
					if (getPeg(currentPeg) == peg) {
						pegDes = getEncode(pegDes) << 2*i;
						int mask = ~(0b11 << 2*i);
						return (encoded & mask) | pegDes;
					}
				}
			}
			else {
				// Move smallest disk of provided disk. Only take small peg on top.
				for (int i = 0; i < desSize; i++) {
					int currentPeg = temp & 0b11;
					temp = temp >>> 2;
					if (getPeg(currentPeg) == peg) {
						pegDes = getEncode(pegDes) << 2*i;
						int mask = ~(0b11 << 2*i);
						return (encoded & mask) | pegDes;
					}
				}
			}
			
			// Illegal move, returning -1
			return -1;
		}
		
		public boolean isFinished() {
			return encoded == 0;
		}

		private int getPeg(int encoded) {
			if (encoded >= 0 && encoded <= 3)
				return encoded+1;
			else {
				System.err.println("Cannot get Peg");
				return -1;
			}
		}
		
		private int getEncode(int peg) {
			if (peg >= 1 && peg <= 4)
				return peg-1;
			else {
				System.err.println("Cannot get Encode");
				return -1;
			}
		}
	}
}
