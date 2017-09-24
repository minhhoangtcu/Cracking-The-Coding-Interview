package epi.dynamicProgramming;

import java.util.Set;
import java.util.HashSet;

/**
 * Given a dictionary, i.e., a set of strings, and a name, design an efficient algorithm that checks
 * whether the name is the concatenation of a sequence of dictionary words. If such a concatenation
 * exists, return it. A dictionary word may appear more than once in the sequence. e.g., "a", "man",
 * "a", "plan", "a", "canal" is a valid sequence for "amanaplanacanal".
 * 
 * @author minhhoang
 *
 */
public class DD07_BedbathAndBeyond {
  public static void main(String[] args) {
    Set<String> dictionary = new HashSet<>();
    dictionary.add("a");
    dictionary.add("bath");
    dictionary.add("bed");
    dictionary.add("man");
    dictionary.add("plan");
    dictionary.add("canal");
    
    System.out.println(isConcatenation("abathbed", dictionary));
    System.out.println(isConcatenation("bedabath", dictionary));
    System.out.println(isConcatenation("aabedbath", dictionary));
    System.out.println(isConcatenation("abathbeda", dictionary));
    System.out.println(isConcatenation("abathbed", dictionary));
    System.out.println(isConcatenation("amanaplanacanal", dictionary));
    System.out.println(isConcatenation("amanaplanacana", dictionary));
    System.out.println(isConcatenation("manaplanacanal", dictionary));
    System.out.println(isConcatenation("amanapanacanal", dictionary));
  }
  
  public static boolean isConcatenation(String str, Set<String> dictionary) {
    if (str.isEmpty()) {
      return false;
    } else if (dictionary.contains(str)) {
      return true;
    }
    
    // canEndHere[i] indicates str.substring(0, i+1) has a valid decomposition. For a 
    // decomposition, be a concatenation of a sequence of dictionary words. 
    boolean[] canEndHere = new boolean[str.length()];
    
    for (int i = 0; i < str.length(); i++) {
      if (dictionary.contains(str.substring(0, i+1))) {
        canEndHere[i] = true;
      } else {
        // Try to break the current decomposition into dictionary words.
        for (int j = i - 1; j >= 0; j--) {
          if (canEndHere[j] && dictionary.contains(str.substring(j+1, i+1))) {
            canEndHere[i] = true;
            break;
          }
        }
      }
    }
    
    return canEndHere[str.length() - 1];
  }
}
