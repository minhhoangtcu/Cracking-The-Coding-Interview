package ctcInterview.treesAndGraphs;

/*
 * Given a sorted ascendingly array with unique integer elements, 
 * write an algorithm to create a binary search tree with minimal height.
 */
public class BalancedTreeCreator {
	
	public static void main(String[] args) {
		
		int[] in1 = {1, 2, 3, 4, 5, 6};
		BalancedTreeCreator creator = new BalancedTreeCreator();
		creator.createBalancedTree(in1);
		
		int[] in2 = {1, 2, 3, 4, 5, 6, 7};
		creator.createBalancedTree(in2);
	}
	
	public Tree createBalancedTreeNode(int[] elements) {
		
		Tree tree = new Tree(true);
		
		int start = 0;
		int end = elements.length-1;
		
		addNode(tree.root, elements, start, end);
		
		return tree;
		
	}
	
	public Node addNode(Node node, int[] elements, int start, int end) {
		
		if (start > end)
			return null;
		
		int mid = (start + end)/2;
		
		node.left = addNode(node.left, elements, start, mid-1);
		node.right = addNode(node.right, elements, start, mid+1);
		
		return node;
	}

	/*
	 * Inefficient because calling tree.add method would make the tree go down searching for
	 * the right position to put the node.
	 * 
	 * We can greatly improve this algorithm by recursively add elements to the nodes.
	 */
	public Tree createBalancedTree(int[] elements) {
		
		Tree tree = new Tree(true);
		
		int start = 0;
		int end = elements.length-1;
		
		addNode(tree, elements, start, end);
		
		return tree;
	}
	
	public void addNode(Tree tree, int[] elements, int start, int end) {
		
		if (start > end)
			return;
		else {
			
			int mid = (start + end)/2;
			tree.add(elements[mid]);
			
			addNode(tree, elements, start, mid-1);
			addNode(tree, elements, mid+1, end);
		}
		
	}
}
