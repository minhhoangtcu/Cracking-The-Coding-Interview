package epi.tree;

public class Node {
  public Node left;
  public Node right;
  public int id;
  
  public Node(int id) {
    this.id = id;
  }
 
  @Override
  public String toString() {
    return Integer.toString(id);
  }
}
