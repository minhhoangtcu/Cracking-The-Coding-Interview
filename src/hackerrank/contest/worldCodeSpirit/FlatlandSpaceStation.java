// https://www.hackerrank.com/contests/worldcodesprint/challenges/powerplants-in-flatland

package hackerrank.contest.worldCodeSpirit;

import java.io.*;
import java.util.*;

public class FlatlandSpaceStation {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int cities = sc.nextInt();
        int stations = sc.nextInt();
        int max = 0;
        
        boolean[] hasStation = new boolean[cities];
        for (int i = 0; i < stations; i++) {
            int indexCity = sc.nextInt();
            hasStation[indexCity] = true;
        }
        
        for (int i = 0; i < cities; i++) {
            int distance = getDistanceToNearest(hasStation, i);
            if (distance > max)
                max = distance;
        }
        
        System.out.println(max);
        
    }
    
    private static int getDistanceToNearest(boolean[] hasStation, int pos) {
        
        if (hasStation[pos])
            return 0;
        
        // Because there has to be a station, this loop will always terminate
        int left = pos, right = pos;
        while (true) {
            if (--left >= 0) {
                if (hasStation[left])
                    return pos-left;
            }
            if (++right < hasStation.length) {
                if (hasStation[right])
                    return right-pos;
            }
        }
    }
}
