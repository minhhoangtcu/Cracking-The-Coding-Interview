package selfstudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Biocolor {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean isBicolor = true;
		
		while(sc.hasNextLine()) {
			
			isBicolor = true;
			int numOfVertices = sc.nextInt();
			int numOfEdges = sc.nextInt();
			int[][] matrix = new int[numOfVertices][numOfVertices];
			
			for (int j = 0; j < numOfEdges; j++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				matrix[from][to] = 1;
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			
			int[] color = new int[numOfVertices];
			Arrays.fill(color, -1);
			color[0] = 0; // fill first node with 0
			
			while (!q.isEmpty()) {
				int currentNode = q.remove();
				for(int j = 0; j < numOfVertices; j++) {
					// if there is an edge
					if (matrix[currentNode][j] == 1) {
						// if we have not visited the node
						if (color[j] == -1) {
							color[j] = color[currentNode] == 0 ? 1 : 0;
							q.add(j);
						} else {
							if (color[j] == color[currentNode]) {
								isBicolor = false;
								break;
							}
						}
					}
				}
				
				if (!isBicolor)
					break;
			}
			
			if (isBicolor)
				System.out.println("BICOLOR");
			else
				System.out.printf("NOTBICOLOR");
			
			sc.nextLine(); // read the last line of the input
		}
		sc.close();
	}
}
