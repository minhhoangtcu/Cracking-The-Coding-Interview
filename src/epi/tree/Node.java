package epi.tree;

public class Node {
  Node left;
  Node right;
  int id;
  
  public Node(int id) {
    this.id = id;
  }
 
  @Override
  public String toString() {
    return Integer.toString(id);
  }
}
