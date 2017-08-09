package epi.strings;

/**
 * Encode successive repeated characters by the repretition count and the character.
 * @author minhhoang
 *
 */
public class S712_RunLengthEncoding {
  public static void main(String[] args) {
    System.out.println(encode("aaabb"));
    System.out.println(encode("a"));
    System.out.println(encode(""));
    
    System.out.println(decode("3e"));
    System.out.println(decode("1e"));
    System.out.println(decode("0e"));
    System.out.println(decode("3e2r5t"));
    System.out.println(decode("30e"));
  }
  
  public static String encode(String s) {
    StringBuffer result = new StringBuffer();
    int count = 1;
    
    for (int i = 1; i <= s.length(); i++) {
      if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
        result.append(count);
        result.append(s.charAt(i - 1));
        count = 0;
      }
      count++;
    }
    
    return result.toString();
  }
  
  public static String decode(String s) {
    StringBuffer result = new StringBuffer();
    int num = 0;
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        num = num*10 + Character.digit(c, 10);
      } else {
        while (num > 0) {
          result.append(c);
          num--;
        }
      }
    }
    
    return result.toString();
  }
}