package epi.bst;

import java.util.LinkedList;
import java.util.List;

/**
 * Design an algorithm for efficiently computing the k smallest numbers of the form a + b*sqrt(2)
 * for nonnegative integers a and b.
 * 
 * @author minhhoang
 *
 */
public class BST157_EnumeratePoints {
  
  public static void main(String[] args) {
    for (SpecialNumber n: getKSmallestNumber(10)) {
      System.out.println(n.getValue());
    }
  }
  
  static class SpecialNumber implements Comparable<SpecialNumber> {
    int a;
    int b;
    
    public SpecialNumber(int a, int b) {
      this.a = a;
      this.b = b;
    }
    
    public static SpecialNumber getIncreseByOne(SpecialNumber o) {
      return new SpecialNumber(o.a + 1, o.b);
    }
    
    public static SpecialNumber getIncreseBySqrt(SpecialNumber o) {
      return new SpecialNumber(o.a, o.b + 1);
    }
    
    public double getValue() {
      return a + b*Math.sqrt(2);
    }
    
    @Override
    public int compareTo(SpecialNumber o) {
      if (a == o.a && b == o.b) {
        return 0;
      }
      
      return Double.compare(getValue(), o.getValue());
    }
  }

  public static List<SpecialNumber> getKSmallestNumber(int k) {
    List<SpecialNumber> result = new LinkedList<>();
    result.add(new SpecialNumber(0, 0));
    
    int i = 0;
    int j = 0;
    
    while (result.size() < k) {
      SpecialNumber incrementByOne = SpecialNumber.getIncreseByOne(result.get(i));
      SpecialNumber incrementBySqrt = SpecialNumber.getIncreseBySqrt(result.get(j));
      
      int comparing = incrementByOne.compareTo(incrementBySqrt); 
      if (comparing == 0) {
        i++;
        j++;
        result.add(incrementByOne);
      } else if (comparing < 0) {
        result.add(incrementByOne);
        i++;
      } else {
        result.add(incrementBySqrt);
        j++;
      }
    }
    
    return result;
  }
}
