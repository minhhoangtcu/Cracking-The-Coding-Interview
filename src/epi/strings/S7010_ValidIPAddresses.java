package epi.strings;

import java.util.LinkedList;
import java.util.List;

/**
 * Write a program that determines where to add periods to a decimal string so that the resulting
 * string is a valid IP address. There may be more than one valid IP address corresponding to a
 * string, in which case you should print all possibilities.
 * 
 * @author minhhoang
 *
 */
public class S7010_ValidIPAddresses {
  
  public static void main(String[] args) {
    for (String s: getPossibleIPs("19216811")) {
      System.out.println(s);
    }
  }
  
  public static List<String> getPossibleIPs(String baseAddress) {
    List<String> out = new LinkedList<>();
    addValidIP(out, toIntArray(baseAddress.toCharArray()), new int[3], 0, 0);
    return out;
  }
  
  private static int[] toIntArray(char[] in) {
    int[] out = new int[in.length];
    for (int i = 0; i < out.length; i++) {
      out[i] = Character.digit(in[i], 10);
    }
    return out;
  }
  
  /**
   * 
   * 
   * @param addressDigits
   * @param placements
   * @param index the digit that we are considering, and right after a seperation dot.
   * @param count
   */
  private static void addValidIP(List<String> out, int[] addressDigits, int[] placements, int index, int count) {
    if (count == 3) {
      if (isValidPart(addressDigits, index, addressDigits.length)) {
        out.add(getAddress(addressDigits, placements));
      }
      return; 
    }
    
    for (int i = index; i < addressDigits.length - 1 && i < index+3; i++) {
      int oldPlacement = placements[count];
      placements[count] = i;
      addValidIP(out, addressDigits, placements, i+1, count+1);
      placements[count] = oldPlacement;
    }
  }
  
  private static String getAddress(int[] addressDigits, int[] placements) {
    StringBuffer sb = new StringBuffer();
    
    for (int i = 0, p = 0; i < addressDigits.length; i++) {
      sb.append(addressDigits[i]);
      if (p < 3 && placements[p] == i) {
        sb.append('.');
        p++;
      }
    }
    
    return sb.toString();
  }
  
  private static boolean isValidPart(int[] addressDigits, int startingIndex, int endingIndex) {
    if (endingIndex - startingIndex == 1) {
      return addressDigits[startingIndex] == 0;
    } else if (endingIndex - startingIndex > 3) {
      return false;
    }
    
    int address = 0;
    for (int i = startingIndex; i < endingIndex; i++) {
      address *= 10;
      address += addressDigits[i];
    }
    return address > 0 && address <= 255; 
  }
}
