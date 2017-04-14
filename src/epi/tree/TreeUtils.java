package epi.tree;

import epi.tree.Node;

public class TreeUtils {
  public void printInorder(Node n) {
    if (n == null) {
      return;
    }
    
    printInorder(n.left);
    System.out.print(n);
    printInorder(n.right);
  }
}
