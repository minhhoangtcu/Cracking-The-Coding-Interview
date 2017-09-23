package epi.dynamicProgramming;

import java.util.Arrays;

/**
 * Write a program that takes two strings and computes the minimum number of edits needed to
 * transform the first string into the second string.
 * 
 * @author minhhoang
 *
 */
public class DD02_Levenshtein {
  public static void main(String[] args) {
    System.out.println(levenshsteinDistance("", "") == 0);
    System.out.println(levenshsteinDistance("a", "") == 1);
    System.out.println(levenshsteinDistance("", "a") == 1);
    System.out.println(levenshsteinDistance("a", "a") == 0);
    System.out.println(levenshsteinDistance("b", "a") == 1);
    System.out.println(levenshsteinDistance("ab", "ba") == 2);
    System.out.println(levenshsteinDistance("ab", "a") == 1);
    System.out.println(levenshsteinDistance("Saturday", "Sundays") == 4);
  }
  
  /*
   * Let L(A[:a], B[:b]) be the levenshtein distance between two strings, A and B. If the last 
   * character, at a-1 and b-1 are similar. Then, we just need to return L(A[:a-1], B[:b]).
   * However, if the two characters are different, then there are three cases.
   * 
   */
  public static int levenshsteinDistance(String A, String B) {
    int[][] distancesAtPrefixes = new int[A.length()+1][B.length()+1];
    for (int row = 0; row < distancesAtPrefixes.length; row++) {
      for (int col = 0; col < distancesAtPrefixes[row].length; col++) {
        if (row == 0) {
          distancesAtPrefixes[row][col] = col;
        } else if (col == 0) {
          distancesAtPrefixes[row][col] = row;
        } else {
          distancesAtPrefixes[row][col] = -1;          
        }
      }
    }
    return levenshsteinDistance(distancesAtPrefixes, A.toCharArray(), B.toCharArray(), A.length(), B.length());
  }
  
  public static int levenshsteinDistance(int[][] distancesAtPrefixes, char[] A, char[] B, int endingA, int endingB) {
    // System.out.printf("A:%s\tB:%s\n", new String(A).substring(0, endingA), new String(B).substring(0, endingB));
    if (distancesAtPrefixes[endingA][endingB] != -1) {
      return distancesAtPrefixes[endingA][endingB];
    }
    
    if (A[endingA-1] == B[endingB-1]) {
      distancesAtPrefixes[endingA][endingB] = levenshsteinDistance(distancesAtPrefixes, A, B, endingA-1, endingB-1);
    } else {
      int substitute = levenshsteinDistance(distancesAtPrefixes, A, B, endingA-1, endingB-1);
      int removeFromA = levenshsteinDistance(distancesAtPrefixes, A, B, endingA-1, endingB);
      int removeFromB = levenshsteinDistance(distancesAtPrefixes, A, B, endingA, endingB-1);
      distancesAtPrefixes[endingA][endingB] = 1 + Math.min(Math.min(substitute, removeFromA), removeFromB);
    }
    
    return distancesAtPrefixes[endingA][endingB];
  }
}
