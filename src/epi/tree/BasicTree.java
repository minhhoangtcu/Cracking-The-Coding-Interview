package epi.tree;

public class BasicTree {
  
  // create a list level order
  public static Node create(Integer[] ids) {
    Node[] heap = new Node[ids.length];
    heap[0] = new Node(ids[0]);
    
    for (int i = 0; i < ids.length; i++) {
      int leftChildIndex = 2*i + 1;
      int rightChildIndex = leftChildIndex + 1;
      
      if (leftChildIndex < ids.length && ids[leftChildIndex] != null) {
        heap[leftChildIndex] = new Node(ids[leftChildIndex]);
        heap[i].left = heap[leftChildIndex];
      }
      
      if (rightChildIndex < ids.length && ids[rightChildIndex] != null) {
        heap[rightChildIndex] = new Node(ids[rightChildIndex]);
        heap[i].right = heap[rightChildIndex];
      }
    }
    
    return heap[0];
  }
  
  /*
   * pip install drawtree
   * 
   * from drawtree import draw_level_order
   * 
   * then paste the output String of this function into python.
   * 
   * the format: draw_level_order('{2,1,3,0,7,9,1,2,#,1,0,#,#,8,8,#,#,#,#,7}')
   */
  public static String getPythonPrintTreeCommand(Integer[] ids) {
    StringBuilder sb = new StringBuilder();
    sb.append("draw_level_order('{");
    
    for (int i = 0; i < ids.length; i++) {
      sb.append(ids[i] == null ? "#" : ids[i].intValue());
      if (i != ids.length - 1) {
        sb.append(',');
      }
    }
    
    sb.append("}')");
    return sb.toString();
  }
}
