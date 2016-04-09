package ctcInterview.treesAndGraphs;

/*
 * Write an algorithm to find the 'next' node (i.e., in-order successor) of a given node in 
 * a binary search tree. You may assume that each node has a link to its parent.
 */
public class InOrderSuccessorFinder {

	public static void main(String[] args) {

		int[] in1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BalancedTreeCreator creator = new BalancedTreeCreator();

		Tree tree = creator.createBalancedTree(in1);
		LevelOrderPrint.printLevels(tree);

		System.out.println(find(tree.root)); // 6
		System.out.println(find(tree.root.left)); // 3
		System.out.println(find(tree.root.right)); // 9
		System.out.println(find(tree.root.right.left)); // 7
		System.out.println(find(tree.root.right.right)); // 10
		System.out.println(find(tree.root.right.right.right)); // null
		System.out.println(find(tree.root.right.left.right)); // 8

	}

	/**
	 * Find the in-order successor of the given node. The in-order successor of
	 * a node is its left-most node in its right sub tree. If the given node
	 * does not have a right sub tree and the node is the left child of its
	 * parent, then the in-order successor is the parent of the given node If
	 * the given node does not have a right sub tree and the node is the right
	 * child of its parent, then the in-order successor is the nearest ancestor
	 * that has a left child, which is a subtree with the given node.
	 * 
	 * @param node
	 * @return
	 */
	public static Node find(Node node) {

		if (node == null)
			return null;
		else if (node.right != null || node.parent == null)
			return leftmost(node.right);

		try {
			Node runner = node.parent;
			while (!isLeftChild(runner)) {
				runner = runner.parent;
			}

			return runner.parent;
		} catch (IllegalArgumentException e) {
			// The node is right-most node of the tree -> no in-order successor.
			return null;
		}

	}

	private static boolean isLeftChild(Node node) {
		if (node.parent != null)
			return node.parent.left == node;
		else
			throw new IllegalArgumentException("Node does not have parent");
	}

	private static Node leftmost(Node node) {

		if (node == null)
			return null;

		Node runner = node;

		while (runner.left != null) {
			runner = runner.left;
		}

		return runner;

	}

}
