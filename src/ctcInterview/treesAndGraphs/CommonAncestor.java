package ctcInterview.treesAndGraphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * Find the first common ancestor of two nodes in a binary tree. Avoid storing
 * additional nodes in a data structure. NOTE: This is not necessarily a binary
 * search tree. <br>
 * So, if it is not a binary search tree, it can be a node with multiple
 * branches. It can also be just a binary tree (lower number does not have to be
 * on the left). For the sake of simplicity, this problem will deal with the
 * latter one.
 * </p>
 * 
 * @author minhhoang
 *
 */
public class CommonAncestor {

	public static void main(String[] args) {

		int first = 0;
		int second = 0;

		System.out.println("<<< TEST BALANCED TREE >>>");
		int[] in1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		BalancedTreeCreator creator = new BalancedTreeCreator();
		Tree treeBalanced = creator.createBalancedTree(in1);
		LevelOrderPrint.printLevels(treeBalanced);
		System.out.println("<<< COMMON ANCESTOR - DEFAULT >>>");
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 9,
				findCommonAncestor(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 3,
				findCommonAncestor(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 4,
				findCommonAncestor(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 8, second = 2,
				findCommonAncestor(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 6, second = 5,
				findCommonAncestor(treeBalanced, first, second).id);
		
		System.out.println("<<< COMMON ANCESTOR - IMPROVED >>>");
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 9,
				findCommonAncestorImproved(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 3,
				findCommonAncestorImproved(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 1, second = 4,
				findCommonAncestorImproved(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 8, second = 2,
				findCommonAncestorImproved(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 6, second = 5,
				findCommonAncestorImproved(treeBalanced, first, second).id);
		System.out.printf("Common ancestor of %d and %d is %d\n", first = 6, second = 99,
				findCommonAncestorImproved(treeBalanced, first, second).id);
	}

	/**
	 * <p>
	 * Find the nearest common ancestor of two given nodes by recursively
	 * traverse the graph and return the two provided node. We check the return
	 * node from both the left sub-tree and right sub-tree of a given node, if
	 * both are the nodes we are looking for, then the root has to be their
	 * nearest ancestor.
	 * </p>
	 * 
	 * @param tree
	 * @param first
	 * @param second
	 * @return
	 */
	public static Node findCommonAncestorImproved(Tree tree, int first, int second) {
		Node out;
		if ((out = findCommonAncestorImprovedHelper(tree.root, first, second)) != null && out.isAncestor)
			return out;
		else
			return new Node(-1); // For the sake of easy debugging, just print out -1
	}
	
	private static Node findCommonAncestorImprovedHelper(Node root, int first, int second) {
		if (root == null)
			return null;
		
		// Found either the first or second node
		if (root.id == first || root.id == second)
			return root;
		
		// Find first, if we already found an ancestor, return it right up
		Node firstNode = findCommonAncestorImprovedHelper(root.left, first, second);
		if (firstNode != null && firstNode.isAncestor)
			return firstNode;
		
		// Find second, if we already found an ancestor, return it right up		
		Node secondNode = findCommonAncestorImprovedHelper(root.right, first, second);
		if (secondNode != null && secondNode.isAncestor)
			return secondNode;
		
		// If the last check cannot find ancestor, they have to be the nodes that we are looking for or nothing at all
		if (firstNode != null && secondNode == null)
			return firstNode;
		else if (firstNode == null && secondNode != null)
			return secondNode;
		else if (firstNode != null && secondNode != null) {
			root.isAncestor = true;
			return root; // If we find both the first on left and second on right, then the root has to be an ancestor
		}
		else
			return null;
	}

	/**
	 * <p>
	 * Find the nearest common ancestor of two given nodes by tracing back
	 * through parent link and keeping track of visited node with a HashSet.
	 * Although this violate the provided rule, more efficient algorithms are
	 * implemented below. <br>
	 * If there is no parent link for the node, this algorithm works fine if we
	 * have a HashTable to keep track of previous node
	 * </p>
	 * 
	 * @param tree
	 * @param first
	 * @param second
	 * @return
	 */
	public static Node findCommonAncestor(Tree tree, int first, int second) {

		// Search for the two node with breadth-first search
		HashSet<Node> visited = new HashSet<>();
		Node firstNode = null, secondNode = null;
		boolean foundFirst = false, foundSecond = false;

		Queue<Node> queue = new LinkedList<>();
		queue.add(tree.root);

		while (!queue.isEmpty()) {
			Node temp = queue.remove();
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);

			if (!foundFirst && temp.id == first) {
				firstNode = temp;
				foundFirst = true;
			}

			if (!foundSecond && temp.id == second) {
				secondNode = temp;
				foundSecond = true;
			}

			if (foundFirst && foundSecond)
				break;
		}

		Node firstRunner = firstNode, secondRunner = secondNode;
		visited.add(firstNode);
		visited.add(secondNode);

		while (firstRunner != null || secondRunner != null) {
			firstRunner = firstRunner.parent;
			if (firstRunner != null) {
				if (visited.contains(firstRunner))
					return firstRunner;
				visited.add(firstRunner);
			}

			secondRunner = secondRunner.parent;
			if (secondRunner != null) {
				if (visited.contains(secondRunner))
					return secondRunner;
				visited.add(secondRunner);
			}
		}

		return null;
	}
}
