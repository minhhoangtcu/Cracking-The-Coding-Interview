package leetCode;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * https://leetcode.com/contest/leetcode-weekly-contest-25/problems/complex-number-multiplication/
 * 
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * 
 * http://stackoverflow.com/questions/12260849/read-values-from-formatted-string-java-groovy
 * 
 * @author minhhoang
 *
 */
public class ComplexNumberMultiplication {
  public static void main(String[] args) {
    ComplexNumberMultiplication cnm = new ComplexNumberMultiplication();
    
    // Test
    System.out.println(cnm.complexNumberMultiply("1+-1i", "2+3i"));
    System.out.println(cnm.complexNumberMultiply("78+-76i", "-86+72i"));
  }
  
  public String complexNumberMultiply(String a, String b) {
    MatchResult resultA = getData(a);
    MatchResult resultB = getData(b);
    
    int realA = Integer.parseInt(resultA.group(1));
    int realB = Integer.parseInt(resultB.group(1));
    
    int imaginaryA = Integer.parseInt(resultA.group(2));
    int imaginaryB = Integer.parseInt(resultB.group(2));
    
    int real = realA*realB - imaginaryA*imaginaryB;
    int imaginary = realA*imaginaryB + imaginaryA*realB;
    
    StringBuilder sb = new StringBuilder();
    sb.append(real);
    sb.append("+");
    sb.append(imaginary);
    sb.append("i");
    
    return sb.toString();
  }
  
  private MatchResult getData(String info) {
    Scanner sc = new Scanner(info);
    sc.findInLine("(-?\\d+)\\+(-?\\d+)");
    sc.close();
    return sc.match();
  }
}
