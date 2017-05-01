package epi.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * Write a program that takes as input a number and returns all the strings with that number of
 * matched pairs of parens.
 * 
 * @author minhhoang
 *
 */
public class R166_MatchedParens {
  
  public static void main(String[] args) {
    for (String parenthesis: getAllValidParenthesis(4)) {
      System.out.println(parenthesis);
    }
  }
  
  private static List<String> result;
  
  public static List<String> getAllValidParenthesis(int numOfMatched) {
    result = new LinkedList<>();
    addParenthesis(0, new boolean[numOfMatched*2], numOfMatched, numOfMatched);
    return result;
  }
  
  private static void addParenthesis(int i, boolean[] isOpening, int openLeft, int closeLeft) {
    if (i == isOpening.length) {
      result.add(convertToParenthesis(isOpening));
      return;
    }
    
    // For the next position, there are two cases: an open parenthesis or closing parenthesis.
    // Open is always possible as long as we still have enough open parenthesis left, denoted by
    // "openLeft". Close is possible as long as there is a valid open before that next position
    // or when closeLeft > openLeft
    
    if (openLeft > 0) {
      isOpening[i] = true;
      addParenthesis(i + 1, isOpening, openLeft - 1, closeLeft);
    }
    
    if (closeLeft > openLeft) {
      isOpening[i] = false;
      addParenthesis(i + 1, isOpening, openLeft, closeLeft - 1);
    }
  }
  
  private static String convertToParenthesis(boolean[] isOpening) {
    StringBuilder sb = new StringBuilder();
    
    for (boolean b : isOpening) {
      sb.append(b ? "(" : ")");
    }
    
    return sb.toString();
  }
}
