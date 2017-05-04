package epi.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Write a program which prints a sequence of operations that transfers n rings from one peg to
 * another. You have a third peg, which is initially empty. The only operation you can perform is
 * taking a single ring from the top of one peg and placing it on the top of another peg. You must
 * never place a larger ring above a smallest ring.
 * 
 * @author minhhoang
 *
 */
public class R161_TowersOfHanoi {
  
  private static int numberOfTowers = 3;
  
  public static void main(String[] args) {
    move(5, 0, 2);
  }

  public static void move(int numberOfRings, int origin, int des) {
    List<Stack<Integer>> towers = new ArrayList<>();
    
    for (int i = 0; i < numberOfTowers; i++) {
      towers.add(new Stack<Integer>());
    }
    
    for (int item = numberOfRings; item > 0; item--) {
      towers.get(0).push(item);
    }
    
    move(numberOfRings, towers, origin, des, numberOfTowers - origin - des);
  }

  private static void move(int ringsToMove, List<Stack<Integer>> towers, 
      int origin, int des, int buffer) {
    if (ringsToMove == 1) {
      System.out.printf("Move %d from %d to %d\n", towers.get(origin).peek(), origin, des);
      towers.get(des).push(towers.get(origin).pop());
      return;
    }
    
    move(ringsToMove - 1, towers, origin, buffer, des);
    move(1, towers, origin, des, buffer);
    move(ringsToMove - 1, towers, buffer, des, origin);
  }
}
