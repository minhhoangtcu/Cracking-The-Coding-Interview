package epi.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Write a program, which returns all distinct nonattacking plcements of n queens on an nxn
 * chessboard, where n is an input to the program.
 * 
 * @author minhhoang
 *
 */
public class R162_Queens {

  // Represents each nonattacking placement as an 1 dimensinal array
  // For the index i be the ith queen in the ith row (because there are n queens for nxn board).
  // There has to be each queen on each row. And let Q[i] be the column where ith queen is placed.
  // Thus, the position of the ith queen is in (row, col) = (i, Q[i])
  
  public static void main(String[] args) {
    for (List<Integer> placement: getNonattackingPlacements(4)) {
      for (Integer row: placement) {
        System.out.print(row + " ");
      }
      
      System.out.println();
    }
  }
  
  private static List<List<Integer>> solutions;
  
  public static List<List<Integer>> getNonattackingPlacements(int n) {
    solutions = new  LinkedList<>();
    
    List<Integer> placement = new ArrayList<>(n);
    
    for (int col = 0; col < n; col++) {
      placement.add(0);
    }
    
    addPlacement(0, placement, n);
    
    return solutions;
  }
  
  private static void addPlacement(int addingIndex, List<Integer> placement, int n) {
    if (addingIndex == placement.size()) {
      solutions.add(new ArrayList<>(placement));
      return;
    }
    
    int oldColumn = placement.get(addingIndex);
    
    for (int column = 0; column < n; column++) {
      placement.set(addingIndex, column);
      
      if (isPlacementValid(addingIndex, placement)) {
        addPlacement(addingIndex + 1, placement, n);
      }
    }
    
    placement.set(addingIndex, oldColumn); // restore original
  }
  
  // Assumption: addingIndex is also the row of the adding queen. 
  private static boolean isPlacementValid(int addingIndex, List<Integer> placement) {
    
    for (int i = 0; i < addingIndex; i++) {
      // They are diagonal when the vertical and horizontal distances between two queens are the same.
      int verticalDistance = addingIndex - i;
      int horizontalDistance = Math.abs(placement.get(i) - placement.get(addingIndex));
      
      // Placement is not valid when they are either in the same column or diagonally
      if (horizontalDistance == 0 || verticalDistance == horizontalDistance) {
        return false;
      }
    }
    
    return true;
  }
}
