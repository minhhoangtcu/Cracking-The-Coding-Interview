package pramp;

import java.util.Arrays;

public class AwardBudgetCut {
	
	public static void main(String[] args) {
		
		AwardBudgetCut abc = new AwardBudgetCut();
		int[] grants = {1, 2, 4, 10, 15};
		int targetedBudget = -40;
		
		System.out.println(abc.findGrantCap(grants, targetedBudget));
		
	}

	public double findGrantCap(int[] grants, int targetedBudget) {
		
		if (targetedBudget <= 0) {
			return 0;
		}

		Arrays.sort(grants);

		// init
		int numOfElements = 1;
		int totalLeftGrants = 0;

		// init total left with everything so it can be reduced for the first iteration of the loop
		for (int i = 0; i < grants.length; i++) {
			totalLeftGrants += grants[i];
		}
		
		if (totalLeftGrants <= targetedBudget) {
			return grants[grants.length - 1];
		}
		
		// going backward and checking
		for (int i = grants.length - 1; i >= 0; i--) {

			// get current budget
			int currentCap = grants[i];
			totalLeftGrants = totalLeftGrants - currentCap;
			int sumOfGrants = totalLeftGrants + currentCap * numOfElements;

			// compare
			if (sumOfGrants == targetedBudget) {
				return currentCap;
			} else if (sumOfGrants < targetedBudget) {
				return ((double) targetedBudget - totalLeftGrants) / numOfElements;
			}

			numOfElements++;
		}

		return ((double) targetedBudget) / numOfElements;
	}
}
