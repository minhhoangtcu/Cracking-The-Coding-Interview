package hackerrank.practice;

import java.util.*;

public class MinimumDistance {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        // a hash table of an integer to all its position
        Hashtable<Integer, LinkedList<Integer>> position = new Hashtable<>();
        
        for(int i =0; i < n; i++){
            int num = in.nextInt();
            if (position.containsKey(num))
                position.get(num).add(i);
            else {
                LinkedList<Integer> tempPos = new LinkedList<>();
                tempPos.add(i);
                position.put(num, tempPos);
            }
        }
        
        int minDistant = Integer.MAX_VALUE;
        
        // go through the hash table of all integers and find min distant
        for (LinkedList<Integer> allPosOfInt: position.values()) {
            
            if (allPosOfInt.size() >= 2) {
                for (int i = 1; i < allPosOfInt.size(); i++) {
                    int a = allPosOfInt.get(i-1);
                    int b = allPosOfInt.get(i);
                    if (Math.abs(a-b) < minDistant)
                        minDistant = Math.abs(a-b);
                }
            }
            
        }
        
        if (minDistant != Integer.MAX_VALUE)
            System.out.println(minDistant);
        else
            System.out.println(-1);
        
        in.close();
    }
}
