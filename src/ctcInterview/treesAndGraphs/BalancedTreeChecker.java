package ctcInterview.treesAndGraphs;

public class BalancedTreeChecker {

	public static void main(String[] args) {

		Tree tree = new Tree();
		tree.add(5);
		tree.add(3);
		tree.add(2);
		tree.add(1);
		tree.add(4);
		tree.add(9);
		tree.add(7);
		tree.add(11);
		System.out.printf("Tree is%sbalaned\n", isBalanced(tree) ? " " : " not ");

		Tree treeNull = new Tree();
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeNull) ? " " : " not ");

		Tree treeOnlyLeft = new Tree();
		treeOnlyLeft.add(5);
		treeOnlyLeft.add(3);
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeOnlyLeft) ? " " : " not ");
		
		Tree treeOnlyRight = new Tree();
		treeOnlyRight.add(5);
		treeOnlyRight.add(7);
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeOnlyRight) ? " " : " not ");
		
		Tree treeBalanced = new Tree();
		treeBalanced.add(5);
		treeBalanced.add(3);
		treeBalanced.add(7);
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeBalanced) ? " " : " not ");
		
		Tree treeRightRight = new Tree();
		treeRightRight.add(5);
		treeRightRight.add(7);
		treeRightRight.add(8);
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeRightRight) ? " " : " not ");
		
		Tree treeLeftLeft = new Tree();
		treeLeftLeft.add(5);
		treeLeftLeft.add(3);
		treeLeftLeft.add(1);
		System.out.printf("Tree is%sbalaned\n", isBalanced(treeLeftLeft) ? " " : " not ");
	}

	public static boolean isBalanced(Tree tree) {

		Node root = tree.root;

		if (root == null)
			return false;
		else
			return (isBalanced(root) == -1) ? false : true;

	}

	public static int isBalanced(Node root) {
		if (root == null)
			return 0;
		else if (root.left == null && root.right == null)
			return 1;
		else {
			int left = isBalanced(root.left);
			int right = isBalanced(root.right);
			if (left == -1 || right == -1)
				return -1;
			else if (Math.abs(left - right) > 1)
				return -1;
			else
				return 1 + left + right;
		}

	}

}

class Tree {

	Node root;
	int size;

	public Tree() {
	}

	public void add(int id) {
		if (isEmpty()) {
			root = new Node(id);
//			System.out.printf("Added first node with id: %d\n", id);
		} else {
			Node runner = root;
			while (runner != null) {

				if (runner.id == id) {
//					System.out.printf("Node with id: %d is already in the tree\n", id);
					break;
				} else if (id > runner.id) {
					if (runner.right != null)
						runner = runner.right;
					else {
//						System.out.printf("Added %d to right of %d\n", id, runner.id);
						runner.right = new Node(id);
						break;
					}

				} else {
					if (runner.left != null)
						runner = runner.left;
					else {
//						System.out.printf("Added %d to left of %d\n", id, runner.id);
						runner.left = new Node(id);
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

class Node {

	int id;
	Node left;
	Node right;

	public Node(int id) {
		this.id = id;
	}

}
