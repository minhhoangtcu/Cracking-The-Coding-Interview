package leetCode;

/*
 * Find the sum of all left leaves in a given binary tree.
 * https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {
	
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;
		
		if (root.left != null)
			return root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
		else
			return sumOfLeftLeaves(root.right);
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
