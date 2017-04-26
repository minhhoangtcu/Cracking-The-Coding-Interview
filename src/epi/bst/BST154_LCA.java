package epi.bst;

import epi.tree.Node;

/**
 * Design an algorithm that takes as input a BST and two nodes, and returns the LCA of the two
 * nodes. Assume all keys are distinct. Nodes do not have references to their parents.
 * 
 * @author minhhoang
 *
 */
public class BST154_LCA {

  public Node getLowestCommonAncestor(Node root, Node n1, Node n2) {
     if (root == null) {
       return null;
     }
     
     if (root.id < n1.id && root.id < n2.id) {
       return getLowestCommonAncestor(root.right, n1, n2);
     } else if (root.id > n1.id && root.id > n2.id) {
       return getLowestCommonAncestor(root.left, n1, n2);
     } else {
       // either root is n1 or n2, or n1 and n2 are in different sides.
       return root; 
     }
  }
}
