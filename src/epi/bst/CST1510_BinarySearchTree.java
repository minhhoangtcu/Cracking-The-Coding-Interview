package epi.bst;

public class CST1510_BinarySearchTree {
  
  Node root;
  int size;

  public boolean insert(int value) {
    Node inserting = new Node(value);
    
    if (isEmpty()) {
      root = inserting;
    } else {
      Node parent = root;
      Node runner = root;
      
      // Find the parent for the inserting node
      while (runner != null) {
        parent = runner;
        int comp = inserting.compareTo(runner);
        
        if (comp == 0) {
          return false; // tree cannot has duplicate
        } else {
          runner = comp < 0 ? runner.left : runner.right;
        }
      }
      
      // Insert the node
      if (inserting.compareTo(runner) < 0) {
        parent.left = inserting;
      } else {
        parent.right = inserting;
      }
    }
    
    size++;
    return true;
  }
  
  public boolean delete(int value) {
    if (isEmpty()) {
      return false;
    }
    
    Node parent = root;
    Node runner = root;
    
    // Find the parent for the deleting node
    while (runner != null) {
      parent = runner;
      int comp = Integer.compare(value, runner.value);
      
      if (comp == 0) {
        break; // found it
      } else {
        runner = comp < 0 ? runner.left : runner.right;
      }
    }
    
    if (runner == null) {
      return false; // cannot find the node to delete
    }
    
    if (runner.left != null && runner.right != null) {
      // rotate right
      Node pushingDown = runner.left.right; 
      addToLeftMost(runner.right, pushingDown);
      runner.left.right = runner.right;
      
      if (parent.left == runner) {
        parent.left = runner.left;
      } else {
        parent.right = runner.left;
      }
    } else if (runner.left == null && runner.right != null) {
      if (parent.left == runner) {
        parent.left = runner.right;
      } else {
        parent.right = runner.right;
      }
    } else if (runner.left != null && runner.right == null) {
      if (parent.left == runner) {
        parent.left = runner.left;
      } else {
        parent.right = runner.left;
      }
    } else {
      if (parent.left == runner) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    }
    
    size--;
    return true;
  }
  
  private void addToLeftMost(Node rootForSubtree, Node pushingDown) {
    Node runner = rootForSubtree;
    
    while (runner.left != null) {
      runner = runner.left;
    }
    
    runner.left = pushingDown;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  private static class Node implements Comparable<Node> {
    Node left;
    Node right;
    int value;
    
    public Node(int value) {
      this.value = value;
    }
    
    @Override
    public int compareTo(Node o) {
      return Integer.compare(value, o.value);
    }
  }
}
