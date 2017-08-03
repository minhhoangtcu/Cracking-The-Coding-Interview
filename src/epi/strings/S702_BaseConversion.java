package epi.strings;

/**
 * Write a program that performs base conversion.
 * 
 * @author minhhoang
 *
 */
public class S702_BaseConversion {
  
  public static void main(String[] args) {
    System.out.println(convertBase(10, 2));
    System.out.println(convertBase(10, 16));
    System.out.println(convertBase(20, 16));
    
    System.out.println(parseInt("1010", 2));
    System.out.println(parseInt("A", 16));
    System.out.println(parseInt("14", 16));
    
    System.out.println(convertBase("14", 10, 16));
    System.out.println(convertBase("1010", 2, 16));
  }

  public static String convertBase(String representation, int baseA, int baseB) {
    return convertBase(parseInt(representation, baseA), baseB);
  }
  
  private static int parseInt(String representation, int radix) {
    final String VALUES = "0123456789ABCDEF";
    boolean isNeg = representation.startsWith("-");
    int scanner = isNeg ? 1 : 0;
    int out = 0;
    
    for (;scanner < representation.length(); scanner++) {
      int value = VALUES.indexOf(representation.charAt(scanner));
      out *= radix;
      out += value;
    }
    
    return out;
  }
  
  private static String convertBase(int num, int radix) {
    final String VALUES = "0123456789ABCDEF";
    StringBuffer sb = new StringBuffer();
    boolean isNeg = num < 0;
    
    while (num != 0) {
      sb.append(VALUES.charAt(num % radix));
      num /= radix;
    }
    
    if (isNeg) {
      sb.append('-');
    }
    
    return sb.reverse().toString();
  }
}
