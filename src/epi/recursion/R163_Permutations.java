package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Write a program which takes as input an array of distinct integers and generates all permutations
 * of that array. No permutation of the array may appear more than once.
 * 
 * @author minhhoang
 *
 */
public class R163_Permutations {
  
  public static void main(String[] args) {
    for (List<Integer> permu: getPermutations(new int[] {1, 2, 3, 4})) {
      System.out.println(permu.toString());
    }
  }  
  
  public static List<List<Integer>> getPermutations(int[] arr) {
    List<List<Integer>> result = new LinkedList<>();
    addPermutation(0, Arrays.stream(arr).boxed().collect(Collectors.toList()), result);
    return result;
  }
  
  private static void addPermutation(int i, List<Integer> Arr, List<List<Integer>> result) {
    if (i == Arr.size() - 1) {
      result.add(new ArrayList<Integer>(Arr));
    }
    
    for (int j = i; j < Arr.size(); j++) {
      Collections.swap(Arr, i, j);
      addPermutation(i + 1, Arr, result);
      Collections.swap(Arr, i, j); // maintain for the swap
    }
  }
}
