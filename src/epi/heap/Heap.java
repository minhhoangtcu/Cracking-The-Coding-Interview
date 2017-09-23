package epi.heap;

import java.util.Arrays;

public class Heap {
  public static void main(String[] args) {
    testSink();
    testBuild();
    testSort();
  }
  
  private static void testSink() {
    System.out.println("Test Sink");
    int[] heap = {0, 1, 2, 3};
    System.out.println(Arrays.toString(heap));
    sink(heap, 1, 3);
    System.out.println(Arrays.toString(heap));
    System.out.println();
  }
  
  private static void testBuild() {
    System.out.println("Test Build");
    int[] heap = {0, 1, 2, 3, 0, 4};
    System.out.println(Arrays.toString(heap));
    build(heap);
    System.out.println(Arrays.toString(heap));
    System.out.println();
  }
  
  private static void testSort() {
    System.out.println("Test Sort");
    int[] heap = {0, 1, 2, 3, 0, 4};
    System.out.println(Arrays.toString(heap));
    sort(heap);
    System.out.println(Arrays.toString(heap));
    System.out.println();
  }

  /**
   * Returns the index of the number, heap[parent], after restoring the heap invariant.
   * 
   * @param heap an array of numbers with base 1 index.
   * @param parent the index of the number to restore the heap invariant.
   * @param size the actual size of the heap, or the ending index of the index.
   */
  public static int sink(int[] heap, int parent, int size) {
    int child = parent*2;
    if (child > size) {
      return parent; // heap[parent] does not have any child.
    }
    
    if (child+1 <= size && heap[child] < heap[child+1]) {
      child++;
    }
    if (heap[parent] < heap[child]) {
      swap(heap, parent, child);
      return sink(heap, child, size);
    }
    return parent;
  }
  
  public static void sort(int[] heap) {
    build(heap);
    int size = heap.length-1;
    for (int i = heap.length-1; i >= 1; i--) {
      swap(heap, 1, i);
      size--;
      sink(heap, 1, size);
    }
  }
  
  public static void build(int[] heap) {
    for (int i = heap.length/2; i >= 1; i--) {
      sink(heap, i, heap.length-1);
    }
  }
  
  private static void swap(int[] heap, int a, int b) {
    int temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
  }
}
