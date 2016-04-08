package ctcInterview.treesAndGraphs;

public class Node {

	int id;
	Node left;
	Node right;

	public Node(int id) {
		this.id = id;
	}
	
	public int compareTo(Node other) {
		if (id < other.id)
			return -1;
		else if (id > other.id)
			return 1;
		else
			return 0;
	}
	
	public String toString() {
		return Integer.toString(id);
	}

}