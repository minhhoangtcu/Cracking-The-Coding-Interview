package epi.arrays;

import java.util.Arrays;

/**
 * Write a program to compute the minimum number of steps needed to advance to
 * the last location.
 * 
 * @author minhhoang
 *
 */
public class A64_AdvancingThroughAnArrayVariant {

	// Build minJumps[] such that minJump[i] indicates the minimum number of
	// jumps needed to reach arr[i] from arr[0]
	
	// [3, 3, 1, 0, 2, 0, 1]
	//  0  1  1  1            lastFurthest = 0
	//        2  2  2         lastFurthest = 3 -> to reach 4, we add 1
	//           3            lastFurthest = 4
	//                 3  3
	
	//  0  1  1  1  2  3  3
	
	public static int getMinSteps(int[] reaches) {
		A64_AdvancingThroughAnArray ataa = new A64_AdvancingThroughAnArray();
		if (!ataa.canReachEnd(reaches)) {
		  return -1;
		}
		
		int[] minSteps = new int[reaches.length];
		Arrays.fill(minSteps, Integer.MAX_VALUE);
		minSteps[0] = 0;
		
		// Build minSteps. For each position i, we go from i+1 
		// to i + reaches[i] (inclusive). Each following min steps will be 
		// the min of (current min) or (min step to get to i) + 1
		for (int i = 0; i < reaches.length; i++) {
			for (int j = i + 1; j <= i + reaches[i] && j < reaches.length; j++) {
				minSteps[j] = Math.min(minSteps[j], minSteps[i] + 1);
			}
		}
		
		return minSteps[reaches.length - 1];
	}
	
	public static int getMinStepsVariant(int[] reaches) {
	  int[] minStepsToIth = new int[reaches.length];
	  Arrays.fill(minStepsToIth, Integer.MAX_VALUE);
	  minStepsToIth[0] = 0;
	  int furthestReach = 0;
	  
	  for (int i = 0; i <= furthestReach && i < reaches.length; i++) {
	    int currentReach = reaches[i] + i;
	    furthestReach = Math.max(furthestReach, currentReach);
	    for (int j = i + 1; j <= currentReach && j < reaches.length; j++) {
	      minStepsToIth[j] = Math.min(minStepsToIth[j], minStepsToIth[i] + 1);
	    }
	  }

	  return furthestReach < reaches.length - 1 ? -1 : minStepsToIth[reaches.length - 1];
	}
	
	public static void main(String[] args) {
		assert(getMinSteps(new int[] {3, 3, 1, 0, 2, 0, 1}) == 3);
		assert(getMinSteps(new int[] {3, 2, 0, 0, 2, 0, 1}) == -1);
		assert(getMinSteps(new int[] {0}) == 0); // already there
		assert(getMinSteps(new int[] {1, 0}) == 1);
		
		assert(getMinStepsVariant(new int[] {3, 3, 1, 0, 2, 0, 1}) == 3);
        assert(getMinStepsVariant(new int[] {3, 2, 0, 0, 2, 0, 1}) == -1);
        assert(getMinStepsVariant(new int[] {0}) == 0); // already there
        assert(getMinStepsVariant(new int[] {1, 0}) == 1);
	}
}
