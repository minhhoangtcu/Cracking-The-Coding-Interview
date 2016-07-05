package ctcInterview.recursionAndDynamicProgramming;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a method to return all subsets of a set
 * 
 * @author minhhoang
 *
 */
public class RD94_Subsets {

	public static void main(String[] args) {
		
		RD94_Subsets ss = new RD94_Subsets();
		
		int[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		ss.printAllSet(ss.getAllSubSets(set));
		
	}
	
	public Set<Set<Integer>> getAllSubSets(int[] set) {
		
		Hashtable<Integer, Set<Set<Integer>>> numOfElementsToSet = new Hashtable<>();
		
		// Add first set with only 1 element in each set
		Set<Set<Integer>> initial = new HashSet<>();
		for (int num: set) {
			Set<Integer> temp = new HashSet<>();
			temp.add(num);
			initial.add(temp);
		}
		numOfElementsToSet.put(1, initial);
		
		// For a set to have k integers, the maximum size of a subset is n-1.
		for (int numberOfSets = 2; numberOfSets <= set.length-1; numberOfSets++) {
			Set<Set<Integer>> thisLevelSet = new HashSet<>();
			numOfElementsToSet.put(numberOfSets, thisLevelSet);
			
			for (int num: set) {
				for (Set<Integer> smaller: numOfElementsToSet.get(numberOfSets-1)) {
					Set<Integer> newSet = (Set<Integer>) ((HashSet<Integer>) smaller).clone();
					newSet.add(num);
					thisLevelSet.add(newSet);
				}
			}
		}
		
		// Return everything back
		Set<Set<Integer>> result = new HashSet<>();
		for (Set<Set<Integer>> currentAllSets: numOfElementsToSet.values()) {
			for (Set<Integer> currentSet: currentAllSets) {
				result.add(currentSet);
			}
		}
		return result;
	}
	
	private void printAllSet(Set<Set<Integer>> allSubSets) {
		int count = 2; // Can have the whole set or empty set
		for (Set<Integer> set: allSubSets) {
			System.out.printf("{");
			Iterator<Integer> iter = set.iterator();
			count++;
			while (iter.hasNext()) {
				int num = iter.next();
				if (iter.hasNext())
					System.out.printf("%d, ", num);
				else
					System.out.printf("%d", num);
			}
			System.out.printf("}\n");
		}
		System.out.printf("TOTAL: %d\n", count);
	}

}
