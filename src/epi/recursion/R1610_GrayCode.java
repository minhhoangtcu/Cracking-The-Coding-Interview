package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a program which takes n as input and returns an n-bit Gray code.
 * 
 * @author minhhoang
 *
 */
public class R1610_GrayCode {
  public static void main(String[] args) {
    for (int num: getGrayCode(4)) {
      System.out.printf("%s: %d\n", Integer.toBinaryString(num), num);
    }
  }
  
  public static List<Integer> getGrayCode(int n) {
    if (n == 0) {
      return Arrays.asList(0);
    } else if (n == 1) {
      return Arrays.asList(0, 1);
    }
    
    List<Integer> previousAnswer = getGrayCode(n - 1);
    
    List<Integer> reflection = new ArrayList<>();
    int prependingOne = 1 << (n - 1);
    
    for (int i = previousAnswer.size() - 1; i >= 0; i--) {
      reflection.add(prependingOne | previousAnswer.get(i));
    }
    
    List<Integer> result = new ArrayList<>(previousAnswer);
    result.addAll(reflection);
    return result;
  }
}
