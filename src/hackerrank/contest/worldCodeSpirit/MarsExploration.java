// https://www.hackerrank.com/contests/worldcodesprint/challenges/save-our-ship

package hackerrank.contest.worldCodeSpirit;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MarsExploration {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        
        if (S.length() < 3)
            System.out.printf("0\n");
        else {
            int changed = 0;
            for (int i = 0; i < S.length(); i+=3) {
                
                if (S.charAt(i) != 'S')
                    changed++;
                if (S.charAt(i+1) != 'O')
                    changed++;
                if (S.charAt(i+2) != 'S')
                    changed++;
                
            }
            System.out.printf("%d\n", changed);
        }
    }
}
