package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a program which computes all size k subsets of {1, 2, ..., n}, where k and n are program
 * inputs. For example, if k = 2 and n = 5, then the result is the following: {{1, 2}, {1, 3}, etc.}
 * 
 * @author minhhoang
 *
 */
public class R165_SubsetK {
  
  public static void main(String[] args) {
    ListUtils.printListofList(getSubsetsK(Arrays.asList(1, 2, 3, 4, 5), 3));
  }

  public static List<List<Integer>> getSubsetsK(List<Integer> baseset, int k) {
    List<List<Integer>> subsets = new LinkedList<>();
    
    addSubsetUp(0, 0, k, new LinkedList<>(), baseset, subsets);
    
    return subsets;
  }
  
  // Build the set based on baseset
  private static void addSubsetUp(int buildingIndex, int gettingIndex, int k, List<Integer> set, 
      List<Integer> baseset, List<List<Integer>> subsets) {
    if (buildingIndex == k) {
      subsets.add(new ArrayList<>(set));
      return;
    } else if (gettingIndex == baseset.size()) {
      return; // if not in correct size and out of element to build
    }
      
    for (int b = gettingIndex; b < baseset.size(); b++) {
      set.add(baseset.get(b));
      addSubsetUp(buildingIndex + 1, b + 1, k, set, baseset, subsets);
      set.remove(set.size() - 1); // remove last added
    }
  }
}
