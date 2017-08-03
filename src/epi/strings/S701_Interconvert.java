package epi.strings;

/**
 * Implement string/integer inter-conversion functions.
 * 
 * @author minhhoang
 *
 */
public class S701_Interconvert {
  
  public int parseInt(String representation) {
    if (representation.length() == 0) {
      return 0;
    }
    
    int out = 0;
    int scanner = 0;
    boolean isNeg = false;
    if (representation.charAt(0) == '-') {
      isNeg = true;
      scanner++;
    }
    
    for (; scanner < representation.length(); scanner++) {
      char currentChar = representation.charAt(scanner);
      if (!Character.isDigit(currentChar)) {
        return -1;
      }
      out = out * 10 + Character.digit(currentChar, 10);
    }
    
    return isNeg ? -out : out;
  }

  public String toString(int num) {
    StringBuffer sb = new StringBuffer();
    boolean isNegative = num < 0;
    num = Math.abs(num);
    
    while (num != 0) {
      sb.append(num % 10);
      num /= 10;
    }
    
    if (isNegative) {
      sb.append('-');      
    }
    
    return sb.reverse().toString();
  }
}
