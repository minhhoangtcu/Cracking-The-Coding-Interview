package epi.stacksAndQueues;

import java.util.EmptyStackException;

public class SQ98_CircularQueue extends CircularQueue {
  
  public static void main(String[] args) {
    SQ98_CircularQueue queue = new SQ98_CircularQueue(10);
    queue.dumpMemory();
    System.out.println(queue + "\n");
    
    // Fill the queue
    for (int i = 0; i < 10; i++) {
      queue.add(i);
    }
    queue.dumpMemory();
    System.out.println(queue + "\n");
    
    // Remove from beginning
    for (int i = 0; i < 5; i++) {
      queue.remove();
    }
    queue.dumpMemory();
    System.out.println(queue + "\n");
    
    // Overflow
    queue.add(10);
    queue.dumpMemory();
    System.out.println(queue + "\n");
    
    // Remove the remaining elements
    for (int i = 0; i < 6; i++) {
      queue.remove();
    }
    queue.dumpMemory();
    System.out.println(queue + "\n");
    
    // Should start again at the beginning
    queue.add(10);
    queue.dumpMemory();
    System.out.println(queue + "\n");
  }
  
  private int[] elements;
  
  public SQ98_CircularQueue(int size) {
    super(size);
    elements = new int[size];
  }

  @Override
  public void add(int element) {
    if (size == numOfElements) {
      throw new IllegalStateException("Queue is full");
    } else {
      if (!isEmpty()) {
        tail = getNextRightIndex(tail);
      }
      elements[tail] = element;
    }
    
    numOfElements++;
  }
  
  private int getNextRightIndex(int index) {
    return index == size - 1 ? 0 : index + 1;
  }
  
  @Override
  public int element() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return elements[head];
  }

  @Override
  public int remove() {
    int result = element(); // call element so we don't have to check for isEmpty
    elements[head] = 0; // clear out
    head = getNextRightIndex(head);
    numOfElements--;
    
    // Resizing would become much cleaner
    if (isEmpty()) {
      clear();
    }
    
    return result;
  }
  
  private void clear() {
    head = 0;
    tail = 0;
  }
  
  public boolean isEmpty() {
    return numOfElements == 0;
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append('[');
    
    if (!isEmpty()) {
      int runner = head;
      while (runner != tail) {
        sb.append(elements[runner] + ", ");
        runner = getNextRightIndex(runner);
      }
      sb.append(elements[runner]); // add the last element
    }
    
    sb.append("]");
    return sb.toString();
  }
  
  public void dumpMemory() {
    for (int i = 0; i < size; i++) {
      System.out.printf("%03d ", elements[i]);
    }
    System.out.println();
  }
}

abstract class CircularQueue {
  protected int size;
  protected int head;
  protected int tail;
  protected int numOfElements;
  
  public CircularQueue(int size) {
    this.size = size;
  }
  
  public abstract void add(int element);
  
  public abstract int element();
  
  public abstract int remove();
}
