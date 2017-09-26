package selfstudy;

import java.util.LinkedList;
import java.util.List;

/*
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. Give
 * a count as well as print the strings.
 * 
 * For example: Input: "1123". You need to general all valid alphabet codes from this string.
 * 
 * Output List:
 * aabc //a = 1, a = 1, b = 2, c = 3 
 * kbc // since k is 11, b = 2, c= 3 
 * alc // a = 1, l = 12, c = 3 
 * aaw // a= 1, a =1, w= 23 
 * kw // k = 11, w = 23
 * 
 */
public class DecodeWays {
  public static void main(String[] args) {
    for (String s: decode("1123")) {
      System.out.println(s);
    }
  }
  
  public static List<String> decode(String txt) {
    List<String> result = new LinkedList<>();
    add(result, txt, new StringBuilder(), 0);
    return result;
  }
  
  public static void add(List<String> result, String txt, StringBuilder decodedTxtSoFar, int i) {
    if (i == txt.length()) {
      result.add(decodedTxtSoFar.toString());
      return;
    }
    
    int currentNumber = getNumber(txt, i, i+1);
    if (currentNumber > 0) {
      decodedTxtSoFar.append(decode(currentNumber));
      add(result, txt, decodedTxtSoFar, i+1);
      decodedTxtSoFar.deleteCharAt(decodedTxtSoFar.length() - 1);
    }
    
    int currentNumberWithNext = getNumber(txt, i, i + 2);
    if (currentNumberWithNext > 0 && currentNumberWithNext <= 26) {
      decodedTxtSoFar.append(decode(currentNumberWithNext));
      add(result, txt, decodedTxtSoFar, i+2);
      decodedTxtSoFar.deleteCharAt(decodedTxtSoFar.length() - 1);
    }
  }
  
  private static int getNumber(String txt, int start, int end) {
    if (start < 0 || end > txt.length()) {
      return -1;
    }
    return Integer.parseInt(txt.substring(start, end));
  }
  
  private static char decode(int v) {
    return (char) (v - 1 + 'a');
  }
}
