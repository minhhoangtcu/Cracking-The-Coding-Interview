package epi.linkedLists;

public class LL802_ReverseSingleSublist {
  public static void main(String[] args) {
    Node root = BasicList.create(new int[] {1, 2, 3, 4, 5});
    BasicList.print(root);
//    reverse(root, 1, 3);
//    BasicList.print(root);
//    reverse(root, 1, 3);
//    BasicList.print(root);
//    reverse(root, 1, 4);
//    BasicList.print(root);
//    reverse(root, 1, 4);
//    BasicList.print(root);
    BasicList.print(reverse(root, 0, 3));
//    reverse(root, 0, 3);
//    BasicList.print(root);
//    reverse(root, 0, 4);
//    BasicList.print(root);
  }
  
  public static Node reverse(Node root, int s, int e) {
    // Store the node before the start node.
    Node sentinel = new Node();
    sentinel.next = root;
    Node beforeStart = sentinel;
    Node runner = root;
    for (int i = 0; i < s; i++) {
      beforeStart = runner;
      runner = runner.next;
    }
    
    // Reverse k nodes (end - start + 1).
    Node previous = beforeStart;
    Node start = runner;
    for (int i = 0; i < e - s + 1; i++) {
      Node next = runner.next;
      runner.next = previous;
      previous = runner;
      runner = next;
    }
    
    // At the end, runner will be at the Node after the end node.
    beforeStart.next = previous;
    start.next = runner;
    
    return sentinel.next;
  }
}
