package ctcInterview.recursionAndDynamicProgramming;

public class Timer {
	
	long tStart, tEnd;
	
	public Timer() {}
	
	public void start() {
		tStart = System.currentTimeMillis();
	}
	
	public void stopAndPrint() {
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.printf("Elapsed time: %.5f seconds\n", elapsedSeconds);
	}

}
