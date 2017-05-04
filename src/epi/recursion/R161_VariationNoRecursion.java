package epi.recursion;

public class R161_VariationNoRecursion {
  
  private static int numberOfTowers = 3;
  
  public static void move(int numberOfRings, int origin, int des) {
    // Moving disk (binary solution)
    int numberOfMoves = 1 << numberOfRings; // proven that for 3 towers, we need 2^n - 1 moves
  }
}
