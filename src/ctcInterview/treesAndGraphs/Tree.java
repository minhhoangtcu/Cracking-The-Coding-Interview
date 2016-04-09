package ctcInterview.treesAndGraphs;

public class Tree {

	Node root;
	int size;
	boolean isDebug;

	public Tree() {
	}
	
	public Tree(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public void add(int id) {
		if (isEmpty()) {
			root = new Node(id);
			if (isDebug) System.out.printf("Added first node with id: %d\n", id);
		} else {
			Node runner = root;
			while (runner != null) {

				if (runner.id == id) {
					if (isDebug) System.out.printf("Node with id: %d is already in the tree\n", id);
					break;
				} else if (id > runner.id) {
					if (runner.right != null)
						runner = runner.right;
					else {
						if (isDebug) System.out.printf("Added %d to right of %d\n", id, runner.id);
						runner.right = new Node(id);
						runner.right.parent = runner;
						break;
					}

				} else {
					if (runner.left != null)
						runner = runner.left;
					else {
						if (isDebug) System.out.printf("Added %d to left of %d\n", id, runner.id);
						runner.left = new Node(id);
						runner.left.parent = runner;
						break;
					}

				}
			}

		}
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}