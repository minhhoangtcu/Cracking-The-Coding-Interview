package epi.strings;

import java.util.LinkedList;
import java.util.List;

/**
 * Write a program which takes as input a phone number, specified as a string of digits, and returns
 * all possible character sequences that correspond to the phone number. The cell phone keypad is
 * specified by a mapping that takes a digit and returns the corresponding set of characters. The
 * character sequences do not have to be legal words or phrases.
 * 
 * @author minhhoang
 *
 */
public class S707_AllMnemonicsPhoneNumbers {
  
  
  public static void main(String[] args) {
    printResult("0000000");
    printResult("1111111");
    printResult("22");
  }
  
  private static void printResult(String representation) {
    System.out.format("Base: %s\n", representation);
    for (String permu: getPermutations(representation)) {
      System.out.println(permu);
    }
    System.out.println();
  }
  
  private static final String[] mapping = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
  
  private static List<String> getPermutations(String representation) {
    List<String> out = new LinkedList<>();
    char[] buffer = new char[representation.length()];
    addSequence(representation, buffer, 0, out);
    return out;
  }
  
  private static void addSequence(String representation, char[] buffer, int i, List<String> result) {
    if (i >= representation.length()) {
      result.add(new String(buffer));
      return;
    }
    
    for (char c: getOptions(representation, i)) {
      char oldOption = buffer[i];
      buffer[i] = c;
      addSequence(representation, buffer, i + 1, result);
      buffer[i] = oldOption;
    }
  }
  
  private static char[] getOptions(String representation, int i) {
    return mapping[Character.digit(representation.charAt(i), 10)].toCharArray();
  }
}
