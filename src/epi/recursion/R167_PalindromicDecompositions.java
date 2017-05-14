package epi.recursion;
import java.util.LinkedList;
import java.util.List;

/**
 * Compute all palindromic decompositions of a given string.
 * 
 * @author minhhoang
 *
 */
public class R167_PalindromicDecompositions {

  /*
   * [0,2,0,4,4,5,1,8,8,1]
[0,2,0,4,4,5,1,88,8,1]
[0,2,0,4,4,5,1881,8,8,1]
[0,2,0,4,4,5,1881,88,8,1]
[0,2,0,44,4,5,1,8,8,1]
[0,2,0,44,4,5,1,88,8,1]
[0,2,0,44,4,5,1881,8,8,1]
[0,2,0,44,4,5,1881,88,8,1]
[020,2,0,4,4,5,1,8,8,1]
[020,2,0,4,4,5,1,88,8,1]
[020,2,0,4,4,5,1881,8,8,1]
[020,2,0,4,4,5,1881,88,8,1]
[020,2,0,44,4,5,1,8,8,1]
[020,2,0,44,4,5,1,88,8,1]
[020,2,0,44,4,5,1881,8,8,1]
[020,2,0,44,4,5,1881,88,8,1]

[020,44,5,1881]
[020,44,5,1,88,1]
[020,44,5,1,8,8,1]
[020,4,4,5,1881]
[020,4,4,5,1,88,1]
[020,4,4,5,1,8,8,1]
[0,2,0,44,5,1881]
[0,2,0,44,5,1,88,1]
[0,2,0,44,5,1,8,8,1]
[0,2,0,4,4,5,1881]
[0,2,0,4,4,5,1,88,1]
[0,2,0,4,4,5,1,8,8,1]

   * 
   * 
   */
  
  public static void main(String[] args) {
    ListUtils.printListofListOfString(getDecompositions("0204451881"));
  }

  public static List<List<String>> getDecompositions(String s) {
    List<List<String>> decompositions = new LinkedList<>();
    List<String> currentDecomposition = new LinkedList<>();

    add(0, new StringBuffer(), currentDecomposition, s, decompositions);
//    add(0, currentDecomposition, s, decompositions);

    return decompositions;
  }
  
  private static void add(int i, List<String> partialSolution, String s, List<List<String>> decompositions) {
    if (i == s.length()) {
      decompositions.add(new LinkedList<>(partialSolution));
      return;
    }
    
    for (int j = i + 1; j <= s.length(); j++) {
      String prefix = s.substring(i, j);
      
      if (isPalindromic(prefix)) {
        partialSolution.add(prefix);
        add(i + 1, partialSolution, s, decompositions);
        partialSolution.remove(partialSolution.size() - 1);
      }
    }
  }

  private static void add(int i, StringBuffer currentPalindromic, List<String> currentDecomposition,
      String s, List<List<String>> decompositions) {
//    System.out.println(currentPalindromic.toString());
    if (i == s.length()) {
      if (isPalindromic(currentPalindromic)) {
        currentDecomposition.add(currentPalindromic.toString());
        decompositions.add(new LinkedList<>(currentDecomposition));
        currentDecomposition.remove(currentDecomposition.size() - 1);
      }

      return;
    }

    // For the ith element, we can decide to either include it in the currentPalindromic or break it
    // into another group. However, we can only break it if the current group is palindromic
    currentPalindromic.append(s.charAt(i));
    add(i + 1, currentPalindromic, currentDecomposition, s, decompositions);
    
    if (isPalindromic(currentPalindromic)) {
      // Break into new group
      currentDecomposition.add(currentPalindromic.toString());
      add(i + 1, new StringBuffer(), currentDecomposition, s, decompositions);
      currentDecomposition.remove(currentDecomposition.size() - 1);
    }
    
    currentPalindromic.deleteCharAt(currentPalindromic.length() - 1);
  }

  private static boolean isPalindromic(StringBuffer sb) {
    if (sb.length() == 0) {
      return false;
    }
    
    for (int start = 0, end = sb.length() - 1; start < end; start++, end--) {
      if (sb.charAt(start) != sb.charAt(end)) {
        return false;
      }
    }

    return true;
  }
  
  private static boolean isPalindromic(String s) {
    if (s.length() == 0) {
      return false;
    }
    
    for (int start = 0, end = s.length() - 1; start < end; start++, end--) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
    }

    return true;
  }
}
