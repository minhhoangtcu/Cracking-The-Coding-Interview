package ctcInterview.treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a binary tree, design an algorithm which creates a linked 
 * list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists)
 */
public class LinkedListFromTreeDepthCreator {
	
	public static void main(String[] args) {
		
		int[] in1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		BalancedTreeCreator creator = new BalancedTreeCreator();
		Tree tree = creator.createBalancedTree(in1);
		printDebug(create(tree));
		
	}

	/*
	 * This algorithm actually does not need level-order transversal.
	 * A breath-first or depth-first way is sufficient because we always keep track of the linked list.
	 */
	public static LinkedList<LinkedList<Node>> create(Tree tree) {
		
		Queue<Node> listOfNodesToExploreInCurrentOrder = new LinkedList<>();
		Queue<Node> listOfNodesToExploreInNextOrder = new LinkedList<>();
		LinkedList<LinkedList<Node>> set = new LinkedList<>();
		
		listOfNodesToExploreInCurrentOrder.add(tree.root);
		
		while (!listOfNodesToExploreInCurrentOrder.isEmpty()) {
			
			LinkedList<Node> listCurrentLevel = new LinkedList<>();
			
			while (!listOfNodesToExploreInCurrentOrder.isEmpty()) {
				Node node = listOfNodesToExploreInCurrentOrder.remove();
				listCurrentLevel.add(node);
				
				if (node.left != null)
					listOfNodesToExploreInNextOrder.add(node.left);
				if (node.right != null)
					listOfNodesToExploreInNextOrder.add(node.right);
			}
			
			set.add(listCurrentLevel);
			
			listOfNodesToExploreInCurrentOrder = listOfNodesToExploreInNextOrder;
			listOfNodesToExploreInNextOrder = new LinkedList<>();
		}

		return set;
	}

	public static void printDebug(LinkedList<LinkedList<Node>> set) {
		for (LinkedList<Node> linkedList : set) {
			for (Node node : linkedList) {
				System.out.printf("%d ", node.id);
			}
			System.out.println();
		}
	}
	
}
