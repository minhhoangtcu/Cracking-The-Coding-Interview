package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a function that takes as input a set and returns its power set
 * 
 * @author minhhoang
 *
 */
public class R164_PowerSet {
  
  public static void main(String[] args) {
    
    // [] -> [1] [2] [3] -> [1 2] [1 3] -> [2 3] -> cannot satisfy -> [1 2 3]
    for (List<Integer> set: getPowerSet(Arrays.asList(1, 2, 3))) {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      
      for (int i = 0; i < set.size(); i++) {
        sb.append(set.get(i));
        if (i != set.size() - 1) {
          sb.append(",");
        }
      }
      
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static List<List<Integer>> getPowerSet(List<Integer> baseset) {
    List<List<Integer>> powerset = new LinkedList<>();
    List<Integer> set = new ArrayList<>(baseset);
    
    addSet(set.size() - 1, set, powerset);
    
    return powerset;
  }
  
  public static void addSet(int i, List<Integer> set, List<List<Integer>> powerset) {
    if (i == -1) {
      powerset.add(new ArrayList<>(set));
      return;
    }
    
    // Get all subsets without element ith
    int removed = set.remove(i);
    addSet(i - 1, set, powerset);
    
    // Get all subsets with element ith
    set.add(i, removed);
    addSet(i - 1, set, powerset);
  }
}
