package ctcInterview.treesAndGraphs;

import java.util.LinkedList;

public class Digraph {
	
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;
	
	public Digraph(int V) {
		this.V = V;
		E = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<>();
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	/**
	 * Add edge v->w to this digraph
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	
	public LinkedList<Integer> adj(int v) {
		return adj[v];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Digraph has %d edges and %d vertices.\n", E, V));
		
		for(int v = 0; v < V; v++) {
			sb.append(String.format("%d:", v));
			for(Integer w: adj[v]) {
				sb.append(String.format(" %d", w));
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
