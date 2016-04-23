package ctcInterview.treesAndGraphs;

import java.util.LinkedList;

/**
 * You are given a binary tree in which each node contains a value. Design an
 * algorithm to print all paths which sum to a given value. The path does not
 * need to start or end at the root or a leaf, but it must go in a straight line
 * down
 * 
 * 4.9
 * 
 * @author minhhoang
 *
 */
public class TG49_SumPathFinder {

	public static void main(String[] args) {

		Tree tree = new Tree();
		tree.root = new Node(3);
		tree.root.left = new Node(2);
		tree.root.left.left = new Node(1);
		tree.root.left.left.left = new Node(-1);
		tree.root.left.right = new Node(3);
		tree.root.right = new Node(5);
		tree.root.right.right = new Node(0);
		tree.root.right.right.right = new Node(-3);
		printPathBotUp(tree, 5);

	}

	/**
	 * The start can start anywhere with in the tree. Thus, we must check every
	 * node because it can possibly be the beginning of a path. However, getting
	 * the beginning of a path and then recursively check down requires a lot of
	 * space and even performance time. It is best to check for the end of a
	 * path and go from bot to top.
	 */
	public static void printPathBotUp(Tree tree, int sum) {
		int depth = tree.getDepth();
		int[] path = new int[depth];
		
		System.out.println("<<< STARTED FINDING ALL PATH >>>");
		findSum(tree.root, 0, path, sum);
		System.out.println("<<< FINISHED FINDING ALL PATH >>>");
	}
	
	public static void findSum(Node node, int depth, int[] path, int sum) {
		
		if (node == null)
			return;
		
		path[depth] = node.id;
		
		// Check bottom up for a path
		int sumPath = 0;
		for (int i = depth; i >= 0; i--) {
			sumPath += path[i];
			if (sumPath == sum)
				printPath(path, i, depth); // we still keep going up because the next 2 nodes may be -1 then 1, which still sum up to correct path.
		}
		
		// Go to its children and check again
		findSum(node.left, depth+1, path, sum);
		findSum(node.right, depth+1, path, sum);
		
		// Clean up this depth after all children is done
		path[depth] = Integer.MIN_VALUE;
	}
	
	private static void printPath(int[] path, int start, int end) {
		System.out.printf("Path: ");
		for (int i = start; i <= end; i++) {
			System.out.printf("%d ", path[i]);
		}
		System.out.println();
	}

	/**
	 * This algorithm is very ineffective and non-functional. A better way is
	 * step to each node and look back to see if it add up to sum or not
	 * 
	 * @param tree
	 * @param sum
	 */
	public static void printPathTopDown(Tree tree, int sum) {
		for (LinkedList<Node> path : findPath(new LinkedList<>(), new LinkedList<>(), tree.root, sum, sum)) {

			for (Node node : path) {
				if (node != path.peekLast())
					System.out.printf("%d -> ", node.id);
				else
					System.out.printf("%d\n", node.id);
			}

		}
	}

	/**
	 * Go down the tree. If node is bigger than sum, check its children because
	 * this node cannot be a part of the sum. If node is smaller than sum, start
	 * recursively check its children, pass down the subtract of sum - node.
	 * When sub = 0, we found the path. Because we need all path, we must start
	 * new recursive checking for every node that is smaller than original sum.
	 */
	public static LinkedList<LinkedList<Node>> findPath(LinkedList<LinkedList<Node>> allPaths,
			LinkedList<Node> currentPath, Node node, int sum, int sub) {

		if (node == null)
			return new LinkedList<>(); // empty

		if (node.id < sum) {
			// Every time we find a node that is smaller than sum, it can be the
			// beginning of a new path.
			LinkedList<Node> currentPathNewLeft = new LinkedList<>();
			currentPathNewLeft.add(node);
			LinkedList<Node> currentPathNewRight = new LinkedList<>();
			currentPathNewRight.add(node);
			allPaths.addAll(findPath(allPaths, currentPathNewLeft, node.left, sum, sum - node.id));
			allPaths.addAll(findPath(allPaths, currentPathNewRight, node.right, sum, sum - node.id));

			// Only when we are on a path, we need to check for sub
			if (!currentPath.isEmpty()) {
				if (sub - node.id == 0) {
					// If we are on a path and current node equals to sub, then
					// this has to be the last node of the path
					currentPath.add(node);
					allPaths.add(currentPath);
				} else if (sub - node.id > 0) {
					// If we are on a path and current node is smaller than sub,
					// then this is a part of the path, we must then have to
					// check for left and right
					currentPath.add(node);
					allPaths.addAll(
							findPath(allPaths, (LinkedList<Node>) currentPath.clone(), node.left, sum, sub - node.id));
					allPaths.addAll(
							findPath(allPaths, (LinkedList<Node>) currentPath.clone(), node.right, sum, sub - node.id));
				} else {
					// If node > sub, the current path failed to be a valid one.
					// Thus, we do not need to do anything with this case
					return new LinkedList<>(); // empty
				}
			}

		} else {
			// If node is bigger or equal to sum, it cannot be the beginning of
			// a new path, thus, we must continue searching its child
			allPaths.addAll(findPath(allPaths, new LinkedList<>(), node.left, sum, sum));
			allPaths.addAll(findPath(allPaths, new LinkedList<>(), node.right, sum, sum));
		}

		return allPaths;

	}

}
