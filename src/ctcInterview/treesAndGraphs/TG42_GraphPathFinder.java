package ctcInterview.treesAndGraphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a directed graph, design an algorithm to find out whether these is a
 * route between two nodes.
 * 
 * 4.2
 * 
 * @author minhhoang
 *
 */
public class TG42_GraphPathFinder {
	
	public static void main(String[] args) {
		
		Digraph graph = new Digraph(5);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		System.out.println(graph.toString());
		
		System.out.println(hasPath(graph, 0, 3));
		System.out.println(hasPath(graph, 0, 4));
		
		graph.addEdge(3, 4);
		System.out.println(hasPath(graph, 0, 4));
		System.out.println(hasPath(graph, 0, 0));
		
		graph.addEdge(0, 0);
		System.out.println(hasPath(graph, 0, 0));
	}

	/**
	 * Find out whether these is a route between two nodes with DFS
	 * 
	 * @param graph
	 * @param v
	 * @param w
	 * @return
	 */
	public static boolean hasPath(Digraph graph, int v, int w) {
		return hasPathHelper(graph, v, w, new HashSet<>());
	}

	public static boolean hasPathHelper(Digraph graph, int v, int w, Set<Integer> visited) {
		
		// If these is an edge from v to w, return true
		if (graph.adj(v).contains(w))
			return true;
		visited.add(v);

		// Go through all edges of v
		for (Integer to : graph.adj(v)) {
			if (!visited.contains(to))
				return hasPathHelper(graph, to, w, visited);
		}
		
		// If still cannot reach w, returns false
		return false;
	}

}
