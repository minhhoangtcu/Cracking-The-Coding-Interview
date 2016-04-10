package ctcInterview.treesAndGraphs;

import java.util.ArrayList;
import java.util.Stack;

/*
 * Check for binary tree
 */
public class BinaryTreeChecker {

	public static void main(String[] args) {

		System.out.println("<<< TEST BALANCED TREE >>>");
		int[] in1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		BalancedTreeCreator creator = new BalancedTreeCreator();
		Tree treeBalanced = creator.createBalancedTree(in1);
		System.out.println(isBinaryTree(treeBalanced));
		System.out.println(isBinaryTreeRecursively(treeBalanced));
		System.out.println(isBinaryTreeMinMax(treeBalanced));

		System.out.println("<<< TEST NOT BINARY TREE WITH 3 NODES >>>");
		Tree tree = new Tree();
		tree.root = new Node(5);
		tree.root.left = new Node(10);
		tree.root.right = new Node(15);
		System.out.println(isBinaryTree(tree));
		System.out.println(isBinaryTreeRecursively(tree));
		System.out.println(isBinaryTreeMinMax(tree));

		System.out.println("<<< TEST NOT BINARY TREE WITH 5 NODES >>>");
		Tree tree2 = new Tree();
		tree2.root = new Node(5);
		tree2.root.left = new Node(3);
		tree2.root.left.left = new Node(1);
		tree2.root.left.right = new Node(6);
		tree2.root.right = new Node(15);
		System.out.println(isBinaryTree(tree2));
		System.out.println(isBinaryTreeRecursively(tree2));
		System.out.println(isBinaryTreeMinMax(tree2));
		
		System.out.println("<<< TEST NULL TREE >>>");
		Tree treeNull = null;
		System.out.println(isBinaryTree(treeNull));
		System.out.println(isBinaryTreeRecursively(treeNull));
		System.out.println(isBinaryTreeMinMax(treeNull));
		
		System.out.println("<<< TEST BINARY TREE 1 NODE >>>");
		Tree treeOneNode = new Tree();
		treeOneNode.root = new Node(5);
		System.out.println(isBinaryTree(treeOneNode));
		System.out.println(isBinaryTreeRecursively(treeOneNode));
		System.out.println(isBinaryTreeMinMax(treeOneNode));
	}

	public static void printInOrder(Tree tree) {

		System.out.println("-- Started printing");

		Stack<Node> stack = new Stack<>();

		stack.add(tree.root);

		while (!stack.isEmpty()) {

			Node runner = stack.pop();
			while (runner.left != null) {
				stack.push(runner);
				runner = runner.left;
			}

			System.out.printf(runner + " ");

			if (!stack.isEmpty()) {
				Node mid = stack.pop();
				System.out.printf(mid + " ");
				if (mid.right != null)
					stack.push(mid.right);
			}
		}

		System.out.println("\n-- Finished printing");
	}

	public static boolean isBinaryTreeMinMax(Tree tree) {
		if (tree == null)
			return false;
			
		return isBinarySubTreeMinMax(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean isBinarySubTreeMinMax(Node node, int min, int max) {

		if (node == null)
			return true;
		
		if (node.id <= min || node.id > max)
			return false;
		
		// Check left + right
		if (!isBinarySubTreeMinMax(node.left, min, node.id) || !isBinarySubTreeMinMax(node.right, node.id, max))
			return false;
		else		
			return true;
	}
	
	/**
	 * Check for binary tree in in-order traversal. This method will do it
	 * recursively by keeping track of the address to the last number visited.
	 * 
	 * @param tree
	 * @return
	 */
	public static boolean isBinaryTreeRecursively(Tree tree) {
		if (tree == null)
			return false;
		
		Node lastNode = new Node(-1); // -1 as there is nothing in the node.
										// This will never act weird because it
										// will have the value of the left-most
										// node in the tree
		return isBinaryTreeRecursivelyNode(tree.root, lastNode);

	}

	public static boolean isBinaryTreeRecursivelyNode(Node node, Node lastNode) {
		if (node == null)
			return true;

		// Check left sub-tree
		if (!isBinaryTreeRecursivelyNode(node.left, lastNode))
			return false;

		// Check current node
		if (lastNode.id != -1 && node.compareTo(lastNode) <= 0)
			return false;
		lastNode.id = node.id;

		// Check right sub-tree
		if (!isBinaryTreeRecursivelyNode(node.right, lastNode))
			return false;

		return true;
	}

	/**
	 * Check for binary tree in in-order traversal (because in-order will always
	 * smallest -> biggest). This method will do it iteratively, because we need
	 * to keep track of the most recent visited node. Although, such node can be
	 * store with a static variable, we should avoid this.
	 * 
	 * Space: O(d) for d be the depth of the tree Speed: O(N)
	 */
	public static boolean isBinaryTree(Tree tree) {
		/*
		 * while the stack is not empty -> push all left node into the stack.
		 * (1) -> when we reach the left-most node, print it out (left) -> pop
		 * the stack, or go up the tree, print the node out (mid) -> add the
		 * right child of the node into the program (right) -> push all left
		 * node into the stack like in (1) then repeat.
		 */

		if (tree == null)
			return false;
		
		Stack<Node> stack = new Stack<>();

		Node lastVisited = null;

		stack.add(tree.root);

		while (!stack.isEmpty()) {

			Node runner = stack.pop();
			while (runner.left != null) {
				stack.push(runner);
				runner = runner.left;
			}

			if (lastVisited == null)
				lastVisited = runner;
			else if (runner.compareTo(lastVisited) < 0)
				return false;
			else
				lastVisited = runner;

			if (!stack.isEmpty()) {
				Node mid = stack.pop();
				if (mid.compareTo(lastVisited) < 0)
					return false;
				else
					lastVisited = mid;
				if (mid.right != null)
					stack.push(mid.right);
			}

		}

		return true;
	}

}
