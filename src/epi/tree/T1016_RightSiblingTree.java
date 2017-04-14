package epi.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a program that takes a perfect binary tree, and sets each node's level-next field to the
 * node on its right, if one exists.
 * 
 * @author minhhoang
 *
 */
public class T1016_RightSiblingTree {
  
  // returns a right sibling tree provided with a perfect tree
  public static Node getRightSiblingTree(Node root) {
    if (root == null) {
      return null;
    }
    
    // Level-order traversal
    Queue<Node> nodesInLevel = new LinkedList<>();
    nodesInLevel.add(root);
    
    while (!nodesInLevel.isEmpty()) {
      int currentLevelSize = nodesInLevel.size();
      Node previous = null; // start with nothing on the left
      
      while (currentLevelSize > 0) {
        Node current = nodesInLevel.poll();
        
        if (previous != null) {
          previous.sibling = current;
        }
        
        if (current.left != null) nodesInLevel.add(current.left);
        if (current.right != null) nodesInLevel.add(current.right);
        
        previous = current;
      }
    }
    
    return root;
  }
  
  private class Node {
    private Node left, right, sibling;
    private char id;
  }
}
