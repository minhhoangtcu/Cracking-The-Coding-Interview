package hackerrank.contest.misc;

import java.io.*;
import java.util.*;

public class WhatAreTheOdds {
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] aa = new int[n];
        int x = 0;
        
        /*
         * At the end, x will be the sum XOR of all piles
         */
        for (int i = 0; i < n; i++) {
            x ^= aa[i] = Integer.parseInt(st.nextToken());
        }
            
        HashMap<Integer,Integer> map = new HashMap<>();
        int y = 0;
        long cnt = 0;
        
        /*
         * 
         */
        for (int i = 0; i < n; i++) {
            map.merge(y, 1, Integer::sum); // increase the value at y by 1
            x ^= aa[i];
            cnt += map.getOrDefault(x, 0);
            y ^= aa[i];
        }
        
        System.out.println(cnt);
    }
}
