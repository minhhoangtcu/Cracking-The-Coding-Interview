package ctcInterview.moderate;

import ctcInterview.recursionAndDynamicProgramming.ArrayCloner;

/**
 * Write an algorithm to determine who has won a game of Tic-Tac-Toe
 * 
 * Questions: 
 * - Do we assume that players are rational?
 * 
 * @author minhhoang
 *
 */
public class M1702_TicTacToe {
	
	/**
	 * A player wins when we get 3 on the same board, which is 3 vert, horz, or dia.
	 * -> Within his moves: all 3 x equals, 3 y equals or 3 pair of equal x and y.
	 * If false, then draw
	 * 
	 * Recursive approach:
	 * 
	 * If 0 legal move -> check end game condition
	 * If 1 legal move -> place move depends on player's turn -> check end game condition
	 * If 2 legal moves -> 2x places then calls to smaller case for each moves
	 * ... same for 3-9 legal moves
	 * 
	 * How to know which person wins?
	 * 
	 * Assume that players are rational -> when player B has 3 choices of move which may 
	 * produce these following results: B wins, A wins, draw, and not determined -> B chooses B wins
	 * 
	 * But, if B passes control to A for the not determined state and the results are draw,
	 * and not determined -> A should explore what option not determined gives. 
	 * 
	 * Design:
	 *  - Method to check end game condition.
	 *  - Write recursive method to check all possible moves
	 *  - Fasten the algorithm with dynamic programming
	 * 
	 */
	
	enum State {
		A_WIN("A wins"),
		B_WIN("B wins"),
		DRAW("draw"),
		ND("not determined");
		String message;
		private State(String message) {
			this.message = message;
		}
		public String toString() {
			return message;
		}
	}
	
	public static void main(String[] args) {
		
		M1702_TicTacToe ttt = new M1702_TicTacToe();
		
		System.out.println("<<< STATE CHECKING METHOD TESTING >>>");
		
		int[][] board1 = {{1, 0, 0},
						 {2, 0, 0},
						 {0, 0, 0}};
		
		System.out.println(ttt.checkWin(board1));
		
		int[][] board2 = {{1, 1, 1},
				 		  {2, 0, 2},
				 		  {0, 2, 0}};
		
		System.out.println(ttt.checkWin(board2));
		
		int[][] board3 = {{1, 2, 2},
		 		  		  {1, 0, 2},
		 		  		  {1, 2, 0}};

		System.out.println(ttt.checkWin(board3));
		
		int[][] board4 = {{1, 0, 2},
		  		  		  {2, 1, 0},
		  		  		  {0, 2, 1}};

		System.out.println(ttt.checkWin(board4));
		
		int[][] board5 = {{1, 2, 1},
		  		  		  {1, 2, 1},
		  		  		  {2, 1, 2}};
		
		System.out.println(ttt.checkWin(board5));
		
		System.out.println("<<< WHO WINS METHOD TESTING >>>");
		
		int[][] board6 = {{1, 2, 1},
		  		  		  {1, 2, 1},
		  		  		  {2, 0, 2}};
		
		System.out.println(ttt.whoWins(board6, 1, 1));
		System.out.println(ttt.whoWins(board6, 2, 1)); // although impossible! B cannot make a total of 5 moves
		
		int[][] board7 = {{1, 2, 2},
						  {1, 1, 2},
						  {0, 0, 0}};
		
		System.out.println(ttt.whoWins(board7, 1, 3));
		System.out.println(ttt.whoWins(board7, 2, 3));
		
		int[][] board8 = {{1, 1, 2},
				  		  {0, 2, 2},
				  		  {0, 1, 0}};
		
		System.out.println(ttt.whoWins(board8, 1, 3));
		
		int[][] board9 = {{2, 0, 1},
		  		  		  {0, 1, 0},
		  		  		  {0, 0, 0}};

		System.out.println(ttt.whoWins(board9, 2, 8));
		
	}
	
	/**
	 * 
	 * @param board the current state of the game, 0 means available cell, 1 means placed by A, 2 means placed by B
	 * @return
	 */
	public State whoWins(int[][] board, int playerTurn, int availableMoves) {
		
		State currentState = checkWin(board); 
		
		if (availableMoves == 0 || currentState != State.ND)
			return currentState; // either Draw/A/B
		
		boolean canDraw = false;
		
		if (currentState == State.ND) {
			
			for (int col = 0; col < board.length; col++) {
				for (int row = 0; row < board.length; row++) {
					if (board[col][row] == 0) {
						int[][] moved = ArrayCloner.cloneArray(board);
						moved[col][row] = playerTurn;
						State nextState = whoWins(moved, (playerTurn == 1) ? 2 : 1, availableMoves-1);
						
						/* If current turn belongs to player A and next turn will result in winning,
						 * then obviously rational player A will always make this move. Else, he finds
						 * the best alternative, which is to have a draw. 
						 */
						if (nextState == State.A_WIN && playerTurn == 1)
							return State.A_WIN;
						else if (nextState == State.B_WIN && playerTurn == 2)
							return State.B_WIN;
						else if (nextState == State.DRAW)
							canDraw = true;
					}
				}
			}
			
			// If it is impossible to have a draw, then current player loses the game
			if (!canDraw)
				return playerTurn == 1 ? State.B_WIN : State.A_WIN;
			else
				return State.DRAW;
			
		} else return currentState;
		
	}
	
	private State checkWin(int[][] board) {
		
		boolean isAWins = false;
		boolean isBWins = false;
		
		int numOfMoves = 9;
		
		int[] aVerticles = new int[3];
		int[] aHorizontal = new int[3];
		int[] aDiagonal = new int[2];
		
		int[] bVerticles = new int[3];
		int[] bHorizontal = new int[3];
		int[] bDiagonal = new int[2];
		
		// Read in coordinates
		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board.length; row++) {
				if (board[col][row] == 1) {
					aVerticles[col] += 1;
					aHorizontal[row] += 1;
					if (col == row)
						aDiagonal[0] += 1;
					if (col + row == 2)
						aDiagonal[1] += 1;
				} else if (board[col][row] == 2) {
					bVerticles[col] += 1;
					bHorizontal[row] += 1;
					if (col == row)
						bDiagonal[0] += 1;
					if (col + row == 2)
						bDiagonal[1] += 1;
				} else
					numOfMoves--;
			}
		}
		
		// Check coordinates
		for (int i = 0; i < 3; i++) {
			if (aVerticles[i] == 3 || aHorizontal[i] == 3 || (i < 2 && aDiagonal[i] == 3))
				isAWins = true;
			if (bVerticles[i] == 3 || bHorizontal[i] == 3 || (i < 2 && bDiagonal[i] == 3))
				isBWins = true;
		}
		
		if (isAWins && isBWins)
			throw new IllegalArgumentException("The board is corrupted. Cannot have 2 winning players at the same time!");
		else if (isAWins)
			return State.A_WIN;
		else if (isBWins)
			return State.B_WIN;
		else if (numOfMoves == 9)
			return State.DRAW;
		else
			return State.ND;
	}

}
