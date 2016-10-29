package leetCode;

/*
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */

public class MaximumDepthBinaryTree {

	public static void main(String[] args) {
		
		MaximumDepthBinaryTree mdbt = new MaximumDepthBinaryTree();
		TreeNode n1 = mdbt.new TreeNode(5);
		n1.left = mdbt.new TreeNode(5);
		n1.right = mdbt.new TreeNode(5);
		n1.left.left = mdbt.new TreeNode(5);
		n1.left.left.left = mdbt.new TreeNode(5);
		System.out.println(mdbt.maxDepth(n1));
		
	}
	
	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
