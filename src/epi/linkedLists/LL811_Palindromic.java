package epi.linkedLists;

/**
 * Test for palindromic in singly linked list
 * 
 * @author minhhoang
 *
 */
public class LL811_Palindromic {
  
  // Use HashMap to trace back. O(n) extra space.
  // Or, reverse the second half of the array -> O(n) time, O(1) space

  public static void main(String[] args) {
    Node root = BasicList.create(new int[] {1, 2, 3, 4});
    printList(root);
    
    printList(root = reverse(root));
    printList(root = reverse(root));
    
    printList(root = reverse(root.next));
    
    Node root2 = BasicList.create(new int[] {1, 2});
    printList(root2 = reverse(root2));
    printList(root2 = reverse(root2));
    
    System.out.println(isPalindromic(root2));
    
    Node root3 = BasicList.create(new int[] {1, 1});
    System.out.println(isPalindromic(root3));
    
    Node root4 = BasicList.create(new int[] {1, 1, 1});
    System.out.println(isPalindromic(root4));
    
    Node root5 = BasicList.create(new int[] {1, 2, 2, 1});
    System.out.println(isPalindromic(root5));
  }
  
  public static boolean isPalindromic(Node root) {
    Node mid = getAfterMiddleNode(root);
    Node reversedRoot = reverse(mid);
    
    Node runner = root;
    while (reversedRoot != null) {
      if (runner.id != reversedRoot.id) {
        return false;
      }
      runner = runner.next;
      reversedRoot = reversedRoot.next;
    }
    
    return true;
  }
  
  private static Node getAfterMiddleNode(Node root) {
    Node runner = root;
    Node walker = root;
    
    while (runner != null && runner.next != null) {
      runner = runner.next.next;
      walker = walker.next;
    }
    
    // if there are odd number of elements, we want the node after the middle node
    if (runner != null) {
      walker = walker.next;
    }
    
    return walker;
  }
  
  private static void printList(Node root) {
    Node runner = root;
    while (runner != null) {
      System.out.print(runner.id + " ");
      runner = runner.next;
    }
    System.out.println();
  }
  
  // Reverse a singly linked list starting with the provided Node
  private static Node reverse(Node root) {
    Node runner = root;
    Node previous = null;
    
    while (runner != null) {
      Node next = runner.next;
      runner.next = previous;
      previous = runner;
      runner = next;
    }
    
    return previous;
  }

}

