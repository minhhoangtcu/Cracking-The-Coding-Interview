package leetCode;

public class LowestCommonAncestor {
	
	public static void main(String[] args) {
		
		LowestCommonAncestor lca = new LowestCommonAncestor();
		
		TreeNode root = lca.new TreeNode(6);
		root.left = lca.new TreeNode(2);
		root.right = lca.new TreeNode(8);
		
		root.left.left = lca.new TreeNode(0);
		root.left.right = lca.new TreeNode(4);
		root.left.right.left = lca.new TreeNode(3);
		root.left.right.right = lca.new TreeNode(5);
		
		root.right.left = lca.new TreeNode(7);
		root.right.right = lca.new TreeNode(9);
		
		System.out.println(lca.lowestCommonAncestor(root, root.left, root.right));
		System.out.println(lca.lowestCommonAncestor(root, root.left, root.left.right));
		System.out.println(lca.lowestCommonAncestor(root, root.left.left, root.right.right));
		System.out.println(lca.lowestCommonAncestor(root, root.left.right.right, root.right.left));
		System.out.println(lca.lowestCommonAncestor(root.left, root.left, root.left.left));
		
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if (root == null)
			return null; // no such common ancestor

		boolean isOnLeft = isEitherAChild(root.left, p, q);
		if (isOnLeft && (root == p || root == q))
			return root;
		
		boolean isOnRight = isEitherAChild(root.right, p, q);
		if (isOnRight && (root == p || root == q))
			return root;

		if (isOnLeft && isOnRight)
			return root;
		else
			// tail recursion
			return isOnLeft ? lowestCommonAncestor(root.left, p, q) : lowestCommonAncestor(root.right, p, q); 
	}

	public boolean isEitherAChild(TreeNode root, TreeNode node1, TreeNode node2) {

		// base case
		if (root == null)
			return false;
		else if (root == node1 || root == node2)
			return true;

		return isEitherAChild(root.left, node1, node2) || isEitherAChild(root.right, node1, node2);

	}
	
	class TreeNode {
		int val;
		TreeNode left, right;
		
		public TreeNode(int val) {
			this.val = val;
		}
		
		public String toString() {
			return Integer.toString(val);
		}
	}
	
}
