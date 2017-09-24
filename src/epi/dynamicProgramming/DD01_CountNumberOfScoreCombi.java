package epi.dynamicProgramming;

import dnl.utils.text.table.TextTable;

/**
 * In an American football game, a play can lead to 2 points (safety), 3 points
 * (field goal), or 7 points (touchdown). Many different combi of 2, 3, and 7
 * point plays can make up a final score.
 * 
 * For example: for 12 scores, we have: 6 safeties; 3s 2fg; 1s 1fg 1td; 4fg
 * 
 * Write a program that takes a final score and scores for individual plays, and
 * returns the number of combinations of plays that result in the final score.
 * 
 * @author minhhoang
 *
 */
public class DD01_CountNumberOfScoreCombi {
	
	public static void main(String[] args) {
		DD01_CountNumberOfScoreCombi program = new DD01_CountNumberOfScoreCombi();
		System.out.println(program.getCombi(30, 2, 3, 7));
	}
	
	/*
	 * Conventional recursive approach involves reducing final score by each
	 * scoring method. We then reduce all combi at the end to find all distinct 
	 * play. This takes time -> we can list out all combi for a final score and 
	 * types of goal used.
	 * 
	 */
	public int getCombi(int finalScore, int safetyScore, int fieldGoalScore, int touchdownScore) {
		int[] points = new int[] {touchdownScore, fieldGoalScore, safetyScore};
		int[][] combi = new int[3][finalScore + 1];
		
		// if we use a point and the resulting score becomes 0, there has to be 1 combi
		for (int i = 0; i < 3; i++) { combi[i][0] = 1; };
		
		for (int score = 1; score <= finalScore; score++) {
			
			for (int pointIndex = 1; pointIndex >= 0; pointIndex--) {
				// For each point (ignore right-most), calculate the number of combinations 
				int point = points[pointIndex];
				
				// Number of combinations for using this type of point
				int combiUsingThisPoint = (score - point < 0)
										  ? 0
										  : combi[pointIndex][score - point];
				
				// Number of combinations for not using this type of point, but do use the remaining
				int combiOnRight = (pointIndex == 1) 
						           ? (score % points[2] == 0) ? 1 : 0 // use only 1 type -> either 1 or 0
						           : combi[1][score];
				
				combi[pointIndex][score] = combiUsingThisPoint + combiOnRight;
			}
		}
		
		printTable(combi, safetyScore, fieldGoalScore, touchdownScore); // for debugging purpose
		return combi[0][finalScore];
	}
	
	public void printTable(int[][] combi, int safetyScore, int fieldGoalScore, int touchdownScore) {
		
		String[] columnNames = {String.format("%d,%d,%d", safetyScore, fieldGoalScore, touchdownScore),
								String.format("%d,%d", safetyScore, fieldGoalScore),
								String.format("%d", safetyScore)};
		
		int rowLength = combi[0].length;
		int colLength = combi.length;
		
		Object[][] data = new Object[rowLength][colLength];
		
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				data[i][j] = new Integer(combi[j][i]);
			}
		}
		
		System.out.println(">> Third column is incorrect because we never check it");
		TextTable tt = new TextTable(columnNames, data);
		tt.setAddRowNumbering(true);
		tt.printTable();
	}

}