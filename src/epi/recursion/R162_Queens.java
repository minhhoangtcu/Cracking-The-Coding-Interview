package epi.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a program, which returns all distinct nonattacking plcements of n queens on an nxn
 * chessboard, where n is an input to the program.
 * 
 * @author minhhoang
 *
 */
public class R162_Queens {
  private List<String> allCombinations;

  private Map<Integer, List<Character>> charactersOnPhoneButtons;

  private void init() {
    charactersOnPhoneButtons = new HashMap<>();
    charactersOnPhoneButtons.put(2, Arrays.asList('a', 'b', 'c'));
    charactersOnPhoneButtons.put(3, Arrays.asList('d', 'e', 'f'));
    charactersOnPhoneButtons.put(4, Arrays.asList('g', 'h', 'i'));
    charactersOnPhoneButtons.put(5, Arrays.asList('j', 'k', 'l'));
    charactersOnPhoneButtons.put(6, Arrays.asList('m', 'n', 'o'));
    charactersOnPhoneButtons.put(7, Arrays.asList('p', 'q', 'r', 's'));
    charactersOnPhoneButtons.put(8, Arrays.asList('t', 'u', 'v'));
    charactersOnPhoneButtons.put(9, Arrays.asList('w', 'x', 'y', 'z'));
  }

  public List<String> letterCombinations(String digits) {
    init();
    
    char[] combination = new char[digits.length()];
    addCombination(0, combination, digits);
    
    return allCombinations;
  }

  private void addCombination(int i, char[] combination, String digits) {
    if (i == combination.length) {
      allCombinations.add(new String(combination));
    }

    char oldChar = combination[i];
    
    for (Character c : charactersOnPhoneButtons.get(Character.getNumericValue(digits.charAt(i)))) {
      combination[i] = c;
      addCombination(i + 1, combination, digits);
    }
    
    combination[i] = oldChar;
  }
}
