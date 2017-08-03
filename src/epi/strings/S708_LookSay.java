package epi.strings;

/**
 * Write a function that takes a number i and returns the ith number in the sequence. 
 * 
 * @author minhhoang
 *
 */
public class S708_LookSay {

  public static void main(String[] args) {
    System.out.println(getIthInSequence(0));
    System.out.println(getIthInSequence(1));
    System.out.println(getIthInSequence(2));
    System.out.println(getIthInSequence(5));
    System.out.println(getIthInSequence(10));
  }
  
  public static String getIthInSequence(int i) {
    StringBuffer b = new StringBuffer("1"); 
    for (int j = 0; j < i; j++) {
      b = next(b);
    }
    return b.toString();
  }
  
  private static StringBuffer next(StringBuffer buffer) {
    StringBuffer out = new StringBuffer();
    
    int scanner = 0;
    while (scanner < buffer.length()) {
      SequenceInfo info = parseAhead(buffer, scanner);
      out.append(info.count);
      out.append(info.c);
      scanner += info.count;
    }
    
    return out;
  }
  
  private static SequenceInfo parseAhead(StringBuffer buffer, int startingIndex) {
    if (startingIndex > buffer.length()) {
      return null;
    }
    
    SequenceInfo out = new SequenceInfo();
    out.c = buffer.charAt(startingIndex);
    out.count = 1;
    for (int scanner = startingIndex + 1; scanner < buffer.length() && buffer.charAt(scanner) == out.c; scanner++) {
      out.count++;
    }
    
    return out;
  }
  
  static class SequenceInfo {
    char c;
    int count;
    public String toString() {
      return String.format("%c: %d", c, count);
    }
  }
}
