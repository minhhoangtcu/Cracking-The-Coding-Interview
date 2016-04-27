package ctcInterview.recursionAndDynamicProgramming;

/**
 * A child is running up a staircase with n steps, and can hop either 1 step, 2
 * steps, or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 * 
 * @author minhhoang
 *
 */
public class RD91_StairRunning {
	
	public static void main(String[] args) {
		
		int n;
		System.out.printf("Height: %d --> %d possible ways\n", n=4, getWays(n));
		System.out.printf("Height: %d --> %d possible ways\n", n=4, getWaysDynamic(n));
		
		System.out.printf("Height: %d --> %d possible ways\n", n=20, getWays(n));
		System.out.printf("Height: %d --> %d possible ways\n", n=20, getWaysDynamic(n));
		
		long tStart = System.currentTimeMillis();
		System.out.printf("Height: %d --> %d possible ways\n", n=35, getWays(n));
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.printf("Elapsed time: %.5f seconds\n", elapsedSeconds);
		
		tStart = System.currentTimeMillis();
		System.out.printf("Height: %d --> %d possible ways\n", n=35, getWaysDynamic(n));
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		elapsedSeconds = tDelta / 1000.0;
		System.out.printf("Elapsed time: %.5f seconds\n", elapsedSeconds);
		
		tStart = System.currentTimeMillis();
		System.out.printf("Height: %d --> %d possible ways\n", n=1000, getWaysDynamic(n));
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		elapsedSeconds = tDelta / 1000.0;
		System.out.printf("Elapsed time: %.5f seconds\n", elapsedSeconds);
	}
	
	public static int getWays(int n) {
		if (n <= 3)
			return n;
		
		int upOne = 1 + getWays(n-1);
		int upTwo = 1 + getWays(n-2);
		int upThree = 1 + getWays(n-3);
		
		return upOne+upTwo+upThree;
		
	}
	
	public static int getWaysDynamic(int n) {
		int[] stair = new int[n+1]; // +1 because the stair starts with 1 instead of 0.
		return getWaysDynamicHelper(n, stair);
	}
	
	private static int getWaysDynamicHelper(int n, int[] stair) {
		
		if (n <= 3)
			return n;
		else if (stair[n] != 0)
			return stair[n];
		
		int upOne = 1 + getWaysDynamicHelper(n-1, stair);
		int upTwo = 1 + getWaysDynamicHelper(n-2, stair);
		int upThree = 1 + getWaysDynamicHelper(n-3, stair);
		
		stair[n] = upOne+upTwo+upThree;
		
		return stair[n];
	}

}
