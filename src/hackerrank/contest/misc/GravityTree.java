package hackerrank.contest.misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GravityTree {
	
	public static void main(String[] args) {
		
		GravityTree gt = new GravityTree();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// because we are going to do many operations on the data structure, we should not make a seperate class and change it in anyway
		HashMap<Integer, Integer> parents = new HashMap<>();
		HashMap<Integer, LinkedList<Integer>> children = new HashMap<>();
		
		// add in the root
		parents.put(1, null);
		children.put(1, new LinkedList<Integer>());
		
		// read in n-1 elements
		for (int child = 2; child <= n; child++) {
			int parent = sc.nextInt();
			parents.put(child, parent);
			if (!children.containsKey(parent)) {
				children.put(parent, new LinkedList<Integer>());
			}
			children.get(parent).add(child);
		}
		
		// calculate q gravity measurements
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			
			LCA lca = gt.getLowestCommonAncestor(parents, u, v);
			
//			if (lca != null) {
//				System.out.printf("DEBUG: LCA is: %d\n", lca.lca);
//				System.out.printf("DEBUG: Distance from %d to lca is: %d\n", u, lca.distanceToLCAFromU);
//				System.out.printf("DEBUG: Distance from %d to lca is: %d\n", v, lca.distanceToLCAFromV);
//			} else
//				System.out.println("DEBUG: NO LCA!");
			
			int currentDistance = gt.getLowestCommonAncestor(parents, u, v).getDistanceFromUToV(); 
			System.out.println(gt.getSumDistance(parents, children, v, u, currentDistance));
			
		}
		
		sc.close();
	}
	
	/*
	 * Check if current node is u or not. If u -> distance is 0 -> continue exploring children 
	 * Check for the node's parent. 
	 *  - If there isn't any -> it has to be the root -> we apply lca to get the distance to u.
	 *  - Else -> get the distance of parent to u -> +1 -> explore children
	 */
	public int getSumDistance(HashMap<Integer, Integer> parents, HashMap<Integer, LinkedList<Integer>> children, int parent, int u, int currentDistance) {
		// if the parent has no children -> this has to be the end node. This serve as a base case
		if (!children.containsKey(parent)) {
			//System.out.printf("DEBUG: Ending Child %d has gravity: %d\n", parent, currentDistance*currentDistance);
			return currentDistance*currentDistance;
		}

		int sum;
		if (parents.get(parent) == null)
			sum = getLowestCommonAncestor(parents, parent, u).getDistanceSquaredFromUToV();
		else if (parent == u) {
			currentDistance = 0;
			sum = 0;
		} else {
			sum = currentDistance*currentDistance;
		}
		//System.out.printf("DEBUG: Node %d has gravity: %d\n", parent, sum);
		
		for (Integer child: children.get(parent)) {
			sum += getSumDistance(parents, children, child, u, currentDistance + 1);
		}
		
		return sum;
	}
	
	public LCA getLowestCommonAncestor(HashMap<Integer, Integer> parents, int u, int v) {
		// create a table to keep track of visited node
		HashMap<Integer, Integer> distanceFromUToVisitedNode = new HashMap<>();
		distanceFromUToVisitedNode.put(u, 0);
		
		// go through all parents of u and mark them
		Integer runnerUp1 = u;
		int distance = 1;
		while (parents.get(runnerUp1) != null) {
			int parent = parents.get(runnerUp1);
			distanceFromUToVisitedNode.put(parent, distance);
			runnerUp1 = parent;
			distance++;
		}
		
		// check v, then go through all parents of v and check for marked node
		LCA lca = new LCA();
		Integer runnerUp2 = v;
		while (runnerUp2 != null) {
			if (distanceFromUToVisitedNode.containsKey(runnerUp2)) {
				lca.distanceToLCAFromU = distanceFromUToVisitedNode.get(runnerUp2);
				lca.lca = runnerUp2;
				return lca;
			}
			runnerUp2 = parents.get(runnerUp2);
			lca.distanceToLCAFromV++;			
		}
		
		// u and v is not in the same tree
		return null; // no available common ancestor
	}
	
	class LCA {
		int lca, distanceToLCAFromU, distanceToLCAFromV;
		int getDistanceFromUToV() {
			return distanceToLCAFromU + distanceToLCAFromV;
		}
		int getDistanceSquaredFromUToV() {
			return (int) Math.pow(getDistanceFromUToV(), 2);
		}
	}
}
