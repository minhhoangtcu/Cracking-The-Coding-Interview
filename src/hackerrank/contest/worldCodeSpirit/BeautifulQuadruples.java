package hackerrank.contest.worldCodeSpirit;

import java.util.Arrays;

public class BeautifulQuadruples {
	
	static boolean isDebug = false;
	
	public static void main(String[] args) {
		
		System.out.println("<<< TEST STARTED >>>");
		
		int[] input = new int[4];
		for (int i = 0; i < 10; i++) {
			
//			System.out.println("\n\n\n");
			
			for (int j = 0; j < 4; j++) {
				input[j] = (int) (Math.random()*10+1);
			}
			
			int eff = calculateEfficient(input);
			int bru = calculateBruteForce(input);
			if (eff != bru) {
				System.out.printf("NOT SAME: %s for eff=%d and bru=%d\n", Arrays.toString(input), eff, bru);
			}
		}
		
		System.out.println("<<< TEST ENDED >>>");
		
	}

	public static int calculateEfficient(int[] num) {
		
		Arrays.sort(num);
        int bQuads = 0;
        for (int i = 1; i <= num[0]; i++) { // Go through only first 3 num
            for (int j = i; j <= num[1]; j++) {
                for (int k = j; k <= num[2]; k++) {
                	if (i != j && j != k) {
                		for (int l = k; l <= num[3]; l++) {
                            if ((i ^ j ^ k ^ l) != 0)
                                bQuads++;
                        }
                	}
                    // Possible to have pair
                	else if (i == j) {
                        bQuads += num[3] - k;
                        if (isDebug) System.out.printf("eff: %d %d %d %d adding: %d\n", i, j, k, num[3], num[3]-k);
                    }
                    else {
                        bQuads += num[3] - k + 1;
                        if (isDebug) System.out.printf("eff: %d %d %d %d adding: %d\n", i, j, k, num[3], num[3]-k+1);
                    }
                    
                }
            }
        }
        
        return bQuads;
		
	}
	
	public static int calculateBruteForce(int[] num) {
		
		Arrays.sort(num);
        int bQuads = 0;
        for (int i = 1; i <= num[0]; i++) { // Go through only first 3 num
            for (int j = i; j <= num[1]; j++) {
                for (int k = j; k <= num[2]; k++) {
                	int sum = 0;
                    for (int l = k; l <= num[3]; l++) {
                        if ((i ^ j ^ k ^ l) != 0) {
                            bQuads++;
                            sum++;
                            if (isDebug) System.out.printf("bru: %d %d %d %d\n", i, j, k, l);
                        }
                    }
                    if (isDebug) System.out.printf("bru: %d added\n", sum);
                }
            }
        }
        
        return bQuads;
	}

}
