package epi.sorting;

import java.util.LinkedList;
import java.util.List;

public class S1401_IntersectionSorted {
  public static void main(String[] args) {
    test(new int[] {2, 3, 3, 5, 5, 6, 7, 7, 8, 12}, new int[] {5, 5, 6, 8, 8, 9, 10, 10});
  }
  
  private static void test(int[] arrA, int[] arrB) {
    for (int e: intersect(arrA, arrB).stream().mapToInt(i->i).toArray()) {
      System.out.print(e + " ");
    }
    System.out.println();
  }
  
  public static List<Integer> intersect(int[] arrA, int[] arrB) {
    List<Integer> result = new LinkedList<>();
    if (arrA.length <= 1 || arrB.length <= 1) {
      return result;
    }
    
    int pA = 0;
    int pB = 0;
    while (pA < arrA.length && pB < arrB.length) {
      if ((pA > 0 && arrA[pA - 1] == arrA[pA]) || arrA[pA] < arrB[pB]) {
        pA++;
        continue;
      } else if ((pB > 0 && arrB[pB - 1] == arrB[pB]) || arrB[pB] < arrA[pA]) {
        pB++;
        continue;
      }
      
      if (arrA[pA] == arrB[pB]) {
        result.add(arrA[pA]);
        pA++;
        pB++;
      }
    }
    
    return result;
  }
}
