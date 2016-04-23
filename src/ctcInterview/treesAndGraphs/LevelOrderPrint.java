package ctcInterview.treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderPrint {

	public static void printLevels(Tree tree) {
		
		if (tree.isEmpty())
			System.err.println("Empty tree!");
		
		System.out.println("<<< Start printing tree >>>");
		
		Queue<Node> currentLevel = new LinkedList<>();
		Queue<Node> nextLevel = new LinkedList<>();
		
		currentLevel.add(tree.root);
		
		while (!currentLevel.isEmpty()) {
			
			while (!currentLevel.isEmpty()) {
				Node node = currentLevel.remove();
				System.out.printf("%s ", node);
				if (node.left != null) nextLevel.add(node.left);
				if (node.right != null) nextLevel.add(node.right);
			}
			
			System.out.println();
			currentLevel = nextLevel;
			nextLevel = new LinkedList<>();
			
		}
		
		System.out.println("<<< Ended printing tree >>>");
	}
	
}
