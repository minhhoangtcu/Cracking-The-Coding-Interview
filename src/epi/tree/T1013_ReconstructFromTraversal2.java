package epi.tree;

import epi.tree.Node;

/**
 * Design an algorithm for reconstructing a binary tree from a preorder traversal visit sequence
 * that uses null to mark empty children.
 * 
 * @author minhhoang
 *
 */
public class T1013_ReconstructFromTraversal2 {

  private static int currentIndex;
  
  public static void main(String[] args) {
    Node root = getTreeFromOrders(new char[] {'H', 'B', 'F', 0, 0, 'E', 'A', 0, 0, 0,
        'C', 0, 'D', 0, 'G', 'I', 0, 0, 0});
    printInorder(root);
  }
  
  public static Node getTreeFromOrders(char[] preorderSequence) {
    currentIndex = 0;
    return buildTree(preorderSequence);
  }
  
  // Returns the index after the node's left and right subtrees
  private static Node buildTree(char[] preorderSequence) {
    char currentCharNode = preorderSequence[currentIndex++];
    if (currentCharNode == 0) {
      return null;
    }
    
    Node result = new Node((int) currentCharNode);
    
    result.left = buildTree(preorderSequence);
    result.right = buildTree(preorderSequence);
    
    return result;
  }
  
  private static void printInorder(Node n) {
    if (n == null) {
      return;
    }
    
    printInorder(n.left);
    System.out.print(Character.toString((char) n.id) + " ");
    printInorder(n.right);
  }
}
