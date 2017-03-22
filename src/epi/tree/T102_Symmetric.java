package epi.tree;

public class T102_Symmetric {
  
  public static void main(String[] args) {
    test(new Integer[] {0, 1, null, 2, 3});
    test(new Integer[] {0, 1, 1});
    test(new Integer[] {0, 1, 1, 2});
    test(new Integer[] {0});
  }
  
  public static void test(Integer[] list) {
    Node root1 = BasicTree.create(list);
    System.out.println(BasicTree.getPythonPrintTreeCommand(list));
    checkSymmetric(root1);
    System.out.println();
  }
  
  public static void checkSymmetric(Node root) {
    if (root == null) {
      throw new IllegalArgumentException();
    }
    
    if (isSymmetric(root.left, root.right)) {
      System.out.println("Tree is symmetric!");
    } else {
      System.out.println("Tree is NOT symmetric!");
    }
  }
  
  public static boolean isSymmetric(Node left, Node right) {
    // Base case
    if (left == null && right == null) {
      return true;
    } else if (left == null || right == null) {
      return false;
    }
    
    if (left.id != right.id) {
      return false;
    }
    
    if (!isSymmetric(left.left, right.right) || !isSymmetric(left.right, right.left)) {
      return false;
    } else {
      return true;
    }
  }
}
