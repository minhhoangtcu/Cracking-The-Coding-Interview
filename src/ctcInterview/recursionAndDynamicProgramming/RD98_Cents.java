package ctcInterview.recursionAndDynamicProgramming;

import java.util.ArrayList;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5
 * cents) and pennies (1 cent), write code to calculate the number of ways of
 * representing n cents.
 * 
 * @author minhhoang
 *
 */
public class RD98_Cents {

	private static int[] coins = { 25, 10, 5, 1 };
	
	public static void main(String[] args) {
		
		RD98_Cents c = new RD98_Cents();
		
		int n = 0;
		
		System.out.printf("<<< ALL COMBINATIONS OF COINS WITH N = %d >>>\n", n=5);
		print(c.getCombinations(n));
		
		System.out.printf("<<< ALL COMBINATIONS OF COINS WITH N = %d >>>\n", n=10);
		print(c.getCombinations(n));
		
		System.out.printf("<<< ALL COMBINATIONS OF COINS WITH N = %d >>>\n", n=15);
		print(c.getCombinations(n));
		
		System.out.printf("<<< NUMBER OF COMBINATIONS OF COINS WITH N = %d >>>\n", n=5);
		System.out.println(c.getNumOfCombination(n));
		
		System.out.printf("<<< NUMBER OF COMBINATIONS OF COINS WITH N = %d >>>\n", n=10);
		System.out.println(c.getNumOfCombination(n));
		
		System.out.printf("<<< NUMBER OF COMBINATIONS OF COINS WITH N = %d >>>\n", n=15);
		System.out.println(c.getNumOfCombination(n));
		
		System.out.printf("<<< NUMBER OF COMBINATIONS OF COINS WITH N = %d >>>\n", n=100);
		System.out.println(c.getNumOfCombination(n));
		
		System.out.printf("<<< NUMBER OF COMBINATIONS OF COINS WITH N = %d >>>\n", n=1000);
		System.out.println(c.getNumOfCombination(n));
	}
	
	public int getNumOfCombination(int n) {
		int[] combination = {0, 0, 0, 0};
		int[][] computed = new int[n+1][4];
		return getNumOfCombinationHelper(n, combination, 0, computed);
	}
	
	private int getNumOfCombinationHelper(int ammount, int[] combination, int currentCoin, int[][] computed) {
		
		if (currentCoin == 4 && ammount > 0)
			return 0;
		else if (ammount == 0)
			return 1;
		else if (computed[ammount][currentCoin] > 0)
			return computed[ammount][currentCoin]; // Dynamic checking for computed ways
		
		int ways = 0;
		for(int numOfCoins = 0; numOfCoins * coins[currentCoin] <= ammount; numOfCoins++) {
			int remaining = ammount - numOfCoins * coins[currentCoin];
			combination[currentCoin] = numOfCoins;
			ways += getNumOfCombinationHelper(remaining, combination, currentCoin + 1, computed);
		}
		
		computed[ammount][currentCoin] = ways;
		return ways;
	}

	public ArrayList<Integer[]> getCombinations(int n) {
		ArrayList<Integer[]> allCombinations = new ArrayList<>();
		Integer[] combination = {0, 0, 0, 0};
		addCombinations(allCombinations, combination, n, -1);
		return allCombinations;
	}

	private void addCombinations(ArrayList<Integer[]> allCombinations, Integer[] combination,
			int ammount, int lastCoin) {
		
//		System.out.printf("%s \tAmmount: %d\tLC: %d\n" ,Arrays.toString(combination), ammount, lastCoin);
		
		if (lastCoin == 3 && ammount > 0)
			return;
		else if (ammount == 0) {
			allCombinations.add(combination);
			return;
		}
		
		for(int numOfCoins = 0; numOfCoins * coins[lastCoin + 1] <= ammount; numOfCoins++) {
			int remaining = ammount - numOfCoins * coins[lastCoin + 1];
			combination[lastCoin + 1] = numOfCoins;
			addCombinations(allCombinations, combination.clone(), remaining, lastCoin + 1);
		}
	}
	
	private static void print(ArrayList<Integer[]> list) {
		for (Integer[] integers : list) {
			for (Integer integer : integers) {
				System.out.printf("%d ", integer);
			}
			System.out.println();
		}
	}

}
