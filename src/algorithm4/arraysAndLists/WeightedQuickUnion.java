package algorithm4.arraysAndLists;

public class WeightedQuickUnion {
	
	private int[] sites;
	private int[] size;
	private int numberOfComponents;
	
	public static void main(String[] args) {
		
		WeightedQuickUnion uf = new WeightedQuickUnion(10);
		
		// 9-7 2-0 0-7 5-1 1-6 3-5 1-4 5-2 8-6 
		uf.union(9, 7);
		uf.union(2, 0);
		uf.union(0, 7);
		uf.union(5, 1);
		uf.union(1, 6);
		uf.union(3, 5);
		uf.union(1, 4);
		uf.union(5, 2);
		uf.union(8, 6);
		uf.printSites();
		
	}
	
	public WeightedQuickUnion(int size) {
		sites = new int[size];
		this.size = new int[size];
		numberOfComponents = size;
		for (int i = 0; i < size; i++) {
			sites[i] = i;
			this.size[i] = 1;
		}
	}
	
	public int find(int p) {
		while (p != sites[p])
			p = sites[p];
		return p;
	}
	
	public void union(int p, int q) {
		int rootQ = find(q);
		int rootP = find(p);
		
		if (rootQ == rootP)
			return;
		
		if (size[rootQ] > size[rootP]) {
			sites[rootP] = sites[rootQ];
			size[rootQ] += size[rootP];
		}
		else {
			sites[rootQ] = sites[rootP];
			size[rootP] += size[rootQ];
		}
		numberOfComponents--;
	}
	
	public void printSites() {
		for (int i = 0; i < sites.length; i++) {
			System.out.printf("%d ", sites[i]);
		}
		System.out.println();
	}

}
