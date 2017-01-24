package epi.stacksAndQueues;

import java.util.EmptyStackException;

public class SQ98_CircularQueue extends CircularQueue {
  
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
      tail = getNextRightIndex(tail);
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
    int result = element();
    head = getNextLeftIndex(head);
    numOfElements--;
    
    // Resizing would become much cleaner
    if (isEmpty()) {
      clear();
    }
    
    return result;
  }
  
  private int getNextLeftIndex(int index) {
    return index == 0 ? size - 1 : index - 1;
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
    
    sb.append("]\n");
    return sb.toString();
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
