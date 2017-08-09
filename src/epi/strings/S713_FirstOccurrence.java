package epi.strings;

/**
 * Given two strings s (the "search string") and t (the "text"), find the first occurence of s in t.
 * 
 * @author minhhoang
 *
 */
public class S713_FirstOccurrence {
  public static void main(String[] args) {
    System.out.println(indexOf("aa", "abcbc") == -1);
    System.out.println(indexOf("aa", "abcbcaasd") == 5);
    System.out.println(indexOf("aa", "abcbcaa") == 5);
    System.out.println(indexOf("aa", "aabcbc") == 0);
    System.out.println(indexOf("a", "aa") == 0);
    System.out.println(indexOf("a", "bb") == -1);
    System.out.println(indexOf("a", "ba") == 1);
    System.out.println(indexOf("a", "a") == 0);
    System.out.println(indexOf("a", "b") == -1);
    System.out.println(indexOf("a", "") == -1);
  }
  
  public static int indexOf(String s, String text) {
    if (text.length() < s.length()) {
      return -1; // Search string cannot be smaller than provided text.
    }
    
    int hashS = 0;
    int hashT = 0;
    
    for (int i = 0; i < s.length(); i++) {
      hashS += s.charAt(i);
      hashT += text.charAt(i);
    }
    for (int i = s.length(); i < text.length(); i++) {
      if (hashS == hashT && text.subSequence(i - s.length(), i).equals(s)) {
        return i - s.length();
      }
      hashT += text.charAt(i) - text.charAt(i - s.length());
    }
    
    if (text.subSequence(text.length() - s.length(), text.length()).equals(s)) {
      return text.length() - s.length();
    }
    return -1;
  }
}
