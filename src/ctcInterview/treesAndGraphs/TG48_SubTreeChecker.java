package ctcInterview.treesAndGraphs;

/**
 * <p>
 * You have two very large binary trees: T1, with millions of nodes, and T2,
 * with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of
 * T1. <br>
 * A tree T2 is a subtree of T1 if there exists a node n in T1 such that the
 * subtree of n is identical to T2. That is, if you cut off the tree at node n,
 * the two trees would be identical.
 * </p>
 * 
 * @author minhhoang
 *
 */
public class TG48_SubTreeChecker {
	
	public static void main(String[] args) {
		
		int[] in1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TG43_BalancedTreeCreator creator = new TG43_BalancedTreeCreator();
		Tree tree = creator.createBalancedTree(in1);
		
		int[] in2 = { 1, 2, 3};
		Tree subTree = creator.createBalancedTree(in2);
		System.out.println(isSubTree(tree, subTree));
		
		Tree subTree2 = new Tree();
		subTree2.root = new Node(3);
		subTree2.root.right = new Node(4);
		System.out.println(isSubTree(tree, subTree2));
		
		int[] in3 = { 6, 7, 8, 9, 10};
		Tree subTree3 = creator.createBalancedTree(in3);
		System.out.println(isSubTree(tree, subTree3));
	}
	
	public static boolean isSubTree(Tree t1, Tree t2) {
		if (t1 == null) // No tree can be a sub-tree of an empty tree
			return false;
		else if (t2 == null) // A empty tree is a sub-tree of any non-empty tree
			return true;
		
		return isSubTreeHelper(t1.root, t2.root);
	}
	
	private static boolean isSubTreeHelper(Node n1, Node n2) {
		
		// Return false if one is null
		if (n1 == null || n2 == null)
			return false;
		
		if (n1.compareTo(n2) == 0)
			return isMatchTree(n1, n2);
		else
			return isSubTreeHelper(n1.left, n2) || isSubTreeHelper(n1.right, n2);
	}
	
	private static boolean isMatchTree(Node n1, Node n2) {

		// Return true if both are similar
		if (n1 == null && n2 == null)
			return true;
		
		// Return false if one is null
		if (n1 == null || n2 == null)
			return false;
		
		if (n1.compareTo(n2) != 0)
			return false;
		else
			return isMatchTree(n1.left, n2.left) && isMatchTree(n1.right, n2.right);
	}

}
