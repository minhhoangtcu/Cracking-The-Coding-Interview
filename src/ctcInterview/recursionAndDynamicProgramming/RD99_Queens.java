package ctcInterview.recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.Hashtable;

import ctcInterview.arraysAndString.MatrixPrinter;

/**
 * Write an algorithm to print all ways of arranging eight queens on an 8x8
 * chess board so that none of them share the same row, column or diagonal. In
 * this case, "diagonal" means all diagonals, not just the two that bisect the
 * board.
 * 
 * @author minhhoang
 *
 */
public class RD99_Queens {

	/**
	 * If we have 1 queen -> we  an place 64 possible positions
	 * If we have 2 queens
	 *  - Place first one on 64 possible positions then mark out impossible positions for future queens
	 *  - Place second one on above possible solution then also mark out impossible positions.
	 * If we have 3 queens
	 *  - Same algorithm (somewhat similar to DFS).
	 * How to avoid repetitions?
	 *  - Can keep track with a set of placed queens.   
	 */
	
	public static void main(String[] args) {
		
		RD99_Queens q = new RD99_Queens();
		int x, y;
		Integer[][] board;
		
		System.out.printf("<<< TEST MARKING FOR X = %d AND Y = %d >>>\n", x=1, y=1);
		board = new Integer[8][8];
		MatrixPrinter.printAlternate(q.mark(board, x, y));
		
		System.out.printf("<<< TEST MARKING FOR X = %d AND Y = %d >>>\n", x=5, y=5);
		board = new Integer[8][8];
		MatrixPrinter.printAlternate(q.mark(board, 5, 5));
		
		System.out.println("<<< PRINT OUT ALL ARRANGEMENTS >>>");
		q.printAllArrangements();
	}
	
	public void printAllArrangements() {
		Integer[][] board = new Integer[8][8];
		printArrangement(new Hashtable<>(), board, 8);
	}
	
	public void printArrangement(Hashtable<Integer, ArrayList<Integer[][]>> visited, Integer[][] board, int numOfQueens) {
		
//		System.out.printf("For number of queens: %d\n", numOfQueens);
//		MatrixPrinter.print(board);
		
		if (contain(visited, board, numOfQueens)) // if already visited
			return;
		else if (numOfQueens <= 0) {
			MatrixPrinter.printAlternate(board);
			System.out.println();
		}
		
		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				if (board[col][row] == null) { // null means possible positions for a queen
					printArrangement(visited, mark(board, col, row), numOfQueens-1);
					if (visited.containsKey(numOfQueens))
						visited.get(numOfQueens).add(ArrayCloner.cloneArray(board));
					else {
						ArrayList<Integer[][]> temp = new ArrayList<>();
						temp.add(ArrayCloner.cloneArray(board));
						visited.put(numOfQueens, temp);
					}
//					System.out.println("return from " + numOfQueens);
//					MatrixPrinter.print(board);
				}
			}
		}
	}
	
	private boolean contain(Hashtable<Integer, ArrayList<Integer[][]>> visited, Integer[][] board, int numOfQueens) {
		
		ArrayList<Integer[][]> listVisited = visited.get(numOfQueens);
		
		if (listVisited == null || listVisited.isEmpty())
			return false;
		
		for (Integer[][] visitedBoard : listVisited) {
			
			boolean isSame = true;
			for (int col = 0; col < visitedBoard.length; col++) {
				for (int row = 0; row < visitedBoard[col].length; row++) {
					if (visitedBoard[col][row] != board[col][row]) {
						isSame = false;
						break;
					}
				}
				if (!isSame)
					break;
			}
			if (isSame) // After checking a whole array in the list, if everything is same -> contains
				return true;
		}
		
		return false;
	}
	
	private Integer[][] mark(Integer[][] inBoard, int col, int row) {
		
		Integer[][] board = ArrayCloner.cloneArray(inBoard);
		
		board[col][row] = 2; 	// 2 means queen
		
		// Clean up col
		for (int cRow = 0; cRow < board[col].length; cRow++) {
			if (cRow == row)
				continue;
			board[col][cRow] = -1; // -1 means impossible positions for a queen
		}
		
		// Clean up row
		for (int cCol = 0; cCol < board.length; cCol++) {
			if (cCol == col)
				continue;
			board[cCol][row] = -1;
		}
		
		// Clean up diagonals
		// North-East
		int x = col+1, y = row-1;
		while (x < board.length && y >= 0) {
			board[x][y] = -1;
			x++;
			y--;
		}
		
		// North-West
		x = col-1; y = row-1;
		while (x >= 0 && y >= 0) {
			board[x][y] = -1;
			x--;
			y--;
		}
		
		// South-East
		x = col+1; y = row+1;
		while (x < board.length && y < board[board.length-1].length) {
			board[x][y] = -1;
			x++;
			y++;
		}
		
		// South-West
		x = col-1; y = row+1;
		while (x >= 0 && y < board[0].length) {
			board[x][y] = -1;
			x--;
			y++;
		}
		
		return board;
	}
}
