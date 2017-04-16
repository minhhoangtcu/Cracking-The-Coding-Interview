package epi.heap;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Implements stack from heap.
 * 
 * @author minhhoang
 *
 */
public class H117_Stack {
  
  // We need a wrapper to keep track of the elements inserted into the stack.
  class HeapStack {
    private int timestamp = 0;
    private PriorityQueue<WrappedElement> heap = new PriorityQueue<WrappedElement>(10, Compare.COMPARATOR_ORDER_DESCENDINGLY);
    
    public void push(int element) {
      heap.add(new WrappedElement(element, timestamp++));
    }
    
    public int pop() throws NoSuchElementException {
      return heap.poll().value;
    }
    
    public int peek() {
      return heap.peek().value;
    }
  }

  private class WrappedElement {
    private int value;
    private int order;
    
    WrappedElement(int value, int order) {
      this.value = value;
      this.order = order;
    }
  }
  
  private static class Compare implements Comparator<WrappedElement> {
    @Override
    public int compare(WrappedElement o1, WrappedElement o2) {
      return Integer.compare(o2.order, o1.order);
    }
    
    public static final Compare COMPARATOR_ORDER_DESCENDINGLY = new Compare();
  }
}
