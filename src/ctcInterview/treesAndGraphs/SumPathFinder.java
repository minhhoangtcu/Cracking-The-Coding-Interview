package ctcInterview.treesAndGraphs;

import java.util.LinkedList;

/**
 * You are given a binary tree in which each node contains a value. Design an
 * algorithm to print all paths which sum to a given value. The path does not
 * need to start or end at the root or a leaf, but it must go in a straight line
 * down
 * 
 * @author minhhoang
 *
 */
public class SumPathFinder {

	public static void main(String[] args) {

		Tree tree = new Tree();
		tree.root = new Node(3);
		tree.root.left = new Node(2);
		tree.root.right = new Node(5);
		printPath(tree, 5);

	}

	/**
	 * This algorithm is very ineffective and non-functional. A better way is
	 * step to each node and look back to see if it add up to sum or not
	 * 
	 * @param tree
	 * @param sum
	 */
	public static void printPath(Tree tree, int sum) {
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
