package epi.strings;

/**
 * Implement a function that converts a spreadsheet column id to the corresponding integer, with "A"
 * corresponding to 1. For example, you should return 4 for "D", 27 for "AA", 702 for "ZZ", etc. How
 * would you test your code?
 * 
 * @author minhhoang
 *
 */
public class S703_SpreadsheetColumnEncoding {
  public int columnID(String representation) {
    int out = 0;
    for (char c: representation.toCharArray()) {
      out *= 26;
      out += c - 'A' + 1; // For A to be 1;
    }
    return out;
  }
}
