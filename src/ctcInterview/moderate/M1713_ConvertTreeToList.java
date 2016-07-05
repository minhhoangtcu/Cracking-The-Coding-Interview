package ctcInterview.moderate;

/**
 * Consider a simple node-like data structure called BiNode, which has pointers
 * to two other nodes.
 * 
 * public class BiNode { public BiNode node1, node2; public int data; }
 * 
 * The data structure BiNode could be used to represent both a binary tree
 * (where node1 is the left node and node 2 is the right node) or a doubly
 * linked list (where node1 is the previous node and node 2 is the next node).
 * Implement a method to convert a binary search tree (implemented with BiNode)
 * into a doubly linked list. The values should be kept in order and the
 * operation should be performed in place (that is, on the original data
 * structure).
 * 
 * @author minhhoang
 *
 */
public class M1713_ConvertTreeToList {
	
	public static void main(String[] args) {
		
		M1713_ConvertTreeToList cttl = new M1713_ConvertTreeToList();
		
		System.out.println("<<< FINISHED BUILDING TREE >>>");
		Tree tree = cttl.new Tree(); 
		tree.add(6);
		tree.add(3);
		tree.add(8);
		tree.add(1);
		tree.add(5);
		tree.add(2);
		tree.add(4);
		tree.add(9);
		tree.add(7);
		
		System.out.println("<<< CONVERTING LINKED LIST >>>");
		cttl.printAll(cttl.convert(tree.root));
		
	}
	
	public BiNode convert(BiNode root) {
		BiNode leftMost = root;
		while (leftMost.node1 != null)
			leftMost = leftMost.node1;
		
		convertHelper(root);
		
		return leftMost;
	}
	
	BiNode lastPrinted = null;
	
	private void convertHelper(BiNode root) {
		
		if (root == null)
			return;
		
		convertHelper(root.node1);
		if (lastPrinted != null) {
			lastPrinted.node2 = root;
			root.node1 = lastPrinted;
		}
		lastPrinted = root;
		
		convertHelper(root.node2);
	}

	public class BiNode {
		public BiNode node1, node2;
		public int data;
		public BiNode(int data) {
			this.data = data;
		}
	}
	
	private void printAll(BiNode root) {
		while (root != null) {
			System.out.printf("%d ", root.data);
			root = root.node2;
		}
	}
	
	public class Tree {

		BiNode root;
		int size;

		public Tree() {
		}
		
		public void add(int id) {
			if (isEmpty()) {
				root = new BiNode(id);
			} else {
				BiNode runner = root;
				while (runner != null) {

					if (runner.data == id) {
						break;
					} else if (id > runner.data) {
						if (runner.node2 != null)
							runner = runner.node2;
						else {
							runner.node2 = new BiNode(id);
							break;
						}

					} else {
						if (runner.node1 != null)
							runner = runner.node1;
						else {
							runner.node1 = new BiNode(id);
							break;
						}

					}
				}

			}
			size++;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
	}
}
