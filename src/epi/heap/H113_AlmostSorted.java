package epi.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Write a program which takes as input a very long sequence of numbers and prints the numbers in
 * sorted order. Each number is at most k away from its correctly sorted position (k-sorted).
 * 
 * @author minhhoang
 *
 */
public class H113_AlmostSorted {
  
  public static void main(String[] args) {
    int[] arr = {3, -1, 2, 6, 4, 5, 8};
    sort(arr, 2);
    System.out.println(Arrays.toString(arr));
  }

  public static void sort(int[] arr, int k) {
    // If a[i] is at most k away, then it can be within the range of a[i-k] and a[i+k] inclusive
    // Thus, we need a heap with the size of k + 1 to ensure that we will get the correct result
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);
    
    // Init heap
    int addingIndex = 0;
    while (addingIndex < k + 1) {
      minHeap.add(arr[addingIndex++]);
    }

    for (int i = 0; i < arr.length; i++) {
      arr[i] = minHeap.poll();
      
      if (addingIndex < arr.length) {
        minHeap.add(arr[addingIndex++]);
      }
    }
  }
}
