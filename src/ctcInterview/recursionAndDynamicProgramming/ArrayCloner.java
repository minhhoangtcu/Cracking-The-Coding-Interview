package ctcInterview.recursionAndDynamicProgramming;

public class ArrayCloner {
	
	public static int[][] cloneArray(int[][] array) {
		int[][] result = new int[array.length][array[0].length];
		for (int col = 0; col < result.length; col++) {
			for (int row = 0; row < result[col].length; row++) {
				result[col][row] = array[col][row];
			}
		}
		return result;
	}
	
	public static Integer[][] cloneArray(Integer[][] array) {
		Integer[][] result = new Integer[array.length][array[0].length];
		for (int col = 0; col < result.length; col++) {
			for (int row = 0; row < result[col].length; row++) {
				result[col][row] = array[col][row];
			}
		}
		return result;
	}

}
