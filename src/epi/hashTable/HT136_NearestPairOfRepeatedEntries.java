package epi.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a program which takes as input an array and finds the distance between a closest pair of
 * equal entries.
 * 
 * @author minhhoang
 *
 */
public class HT136_NearestPairOfRepeatedEntries {

  public static void main(String[] args) {
    System.out.println(getNearestDistance(new String[] {"All", "work", "and", "no", "play", "makes",
        "for", "no", "work", "no", "fun", "and", "no", "result"}));
  }

  public static int getNearestDistance(String[] words) {
    int minDistance = Integer.MAX_VALUE;
    Map<String, Integer> lastSeenIndexOfWord = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      Integer lastSeenIndex = lastSeenIndexOfWord.get(words[i]);
      if (lastSeenIndex != null) {
        minDistance = Math.min(i - lastSeenIndex.intValue(), minDistance);
      }

      lastSeenIndexOfWord.put(words[i], i);
    }

    return minDistance;
  }
}
