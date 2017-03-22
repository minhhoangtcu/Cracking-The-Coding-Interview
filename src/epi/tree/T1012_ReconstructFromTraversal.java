package epi.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Many different binary trees yield the same sequence of keys in an inorder, preorder, or postorder
 * traversal. However, 2 traversals combined make an unique tree.
 * 
 * Given an inorder traversal sequence and a preorder traversal sequence of a binary tree, write a
 * program to reconstruct the tree. Assume each node has a unique key.
 * 
 * @author minhhoang
 *
 */
public class T1012_ReconstructFromTraversal {
  
  private void printInorder(Node n) {
    if (n == null) {
      return;
    }
    
    printInorder(n.left);
    System.out.print(n.data + " ");
    printInorder(n.right);
  }
  
  private void printPreorder(Node n) {
    if (n == null) {
      return;
    }
    
    System.out.print(n.data + " ");
    printPreorder(n.left);
    printPreorder(n.right);
  }
  
  private void printInfo(Node n) {
    if (n == null) {
      return;
    }
    
    String left = n.left == null ? "null" : Character.toString(n.left.data);
    String right =  n.right == null ? "null" : Character.toString(n.right.data);
    
    System.out.println(n.data + ": " + left + "\t" + right);
    printInfo(n.left);
    printInfo(n.right);
  }
  
  public static void main(String[] args) {
    T1012_ReconstructFromTraversal reconstructor = new T1012_ReconstructFromTraversal();
    reconstructor.test(new char[] {'b', 'c', 'd', 'e', 'f', 'h'},
        new char[] {'e', 'c', 'b', 'd', 'h', 'f'});
    
    reconstructor.test(new char[] {'f', 'b', 'a', 'e', 'h', 'c', 'd', 'i', 'g'},
        new char[] {'h', 'b', 'f', 'e', 'a', 'c', 'd', 'g', 'i'});
  }
  
  private void test(char[] inorderSequence, char[] preorderSequence) {
    System.out.println("Input inorder :" + Arrays.toString(inorderSequence));
    System.out.println("Input preorder:" + Arrays.toString(preorderSequence));
    
    Node head = getTreeFromOrders(inorderSequence, preorderSequence);
    
    System.out.println("Output inorder: ");
    printInorder(head);
    System.out.println("\n");
    
    System.out.println("Output preorder: ");
    printPreorder(head);
    System.out.println("\n");
    printInfo(head);
  }
  
  /**
   * Brain storm:
   * - In-order is ascending
   * - Pre-order: from any node, we always have the root node then a child.
   * 
   * For the sake of simplicity, we have this binary tree 
   * 
   *     5
   *   3   8 
   * 2  4 6
   * 
   * in-order : 2 3 4 5 6 8
   * pre-order: 5 3 2 4 8 6
   * 
   * Check out the pre-order sequence. The first element is always going to be the root.
   * The next element is going to be either the left or right child of the previous node we are 
   * considering. But, then we always have to keep track of our boundary.
   */

    public Node getTreeFromOrders(char[] inorderSequence, char[] preorderSequence) {
      Map<Character, Integer> orderMap = getOrderMap(inorderSequence);
      
      Node head = new Node();
      head.data = preorderSequence[0];
      head.parent = null;
      head.lowerBound = Integer.MIN_VALUE;
      head.upperBound = Integer.MAX_VALUE;

      Node previous = head;
      
      for (int i = 1; i < preorderSequence.length && previous != null;) {
        // Consider a node in pre-order, if it is bigger than the previous node, and smaller than
        // the upper bound -> it has to be a right child. Smaller than previous, bigger than lower
        // bound -> has to be a left child. Else, we have to back track to the parents.
        Node considering = new Node();
        considering.data = preorderSequence[i];
        
        if (previous.right == null &&
            compareOrder(considering, previous, orderMap) > 0 && 
            compareOrderWithBound(considering, previous.upperBound, orderMap) < 0) {
          considering.parent = previous;
          considering.lowerBound = orderMap.get(previous.data);
          considering.upperBound = previous.upperBound;
          previous.right = considering;
          previous = considering;
          i++; // consider next node
          
        } else if (previous.left == null &&
            compareOrder(considering, previous, orderMap) < 0 &&
            compareOrderWithBound(considering, previous.lowerBound, orderMap) > 0) {
          considering.parent = previous;
          considering.lowerBound = previous.lowerBound;
          considering.upperBound = orderMap.get(previous.data);
          previous.left = considering;
          previous = considering;
          i++; // consider next node
          
        } else {
          // Backtrack until either condition above satisfies
          previous = previous.parent;
        }
      }
      
      return head;
    }
    
    private int compareOrder(Node n1, Node n2, Map<Character, Integer> orderMap) {
      return Integer.compare(orderMap.get(n1.data).intValue(), orderMap.get(n2.data).intValue());
    }
    
    private int compareOrderWithBound(Node n, int bound, Map<Character, Integer> orderMap) {
      return Integer.compare(orderMap.get(n.data).intValue(), bound);
    }
    
    /**
     * Constructs an order map, that is for a given character as key, its order is going to be the
     * value in the map. 
     * 
     * @param ascendingSequence
     * @return
     */
    private Map<Character, Integer> getOrderMap(char[] ascendingSequence) {
      Map<Character, Integer> orderOfNode = new HashMap<>();
      
      for (int order = 0; order < ascendingSequence.length; order++) {
        orderOfNode.put(ascendingSequence[order], order);
      }
      
      return orderOfNode;
    }
    
    class Node {
      char data;
      Node left, right, parent;
      int lowerBound, upperBound;
    }
}
