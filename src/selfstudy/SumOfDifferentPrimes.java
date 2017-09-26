package selfstudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SumOfDifferentPrimes {
  public static void main(String[] args) {
//    for (Integer prime: getAllPrimes(100)) {
//      System.out.print(prime + " ");
//    }
    
    sumDifferentPrimes(24, 3);
  }
  
  public static int sumDifferentPrimes(int n, int k) {
    List<List<Integer>> result = new LinkedList<>();
    List<Integer> primes = getAllPrimes(n);
    List<Integer> currentPrimes = new LinkedList<>();
    Map<Integer, Map<Integer, List<Integer>>> dp = new HashMap<>();
    
    addSum(n, k, dp, result, primes, currentPrimes, 0);
    
    printDP(dp);
    System.out.println();
    
    for (List<Integer> combination: result) {
      for (Integer c: combination) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
    
    return result.size();
  }
  
  private static void printDP(Map<Integer, Map<Integer, List<Integer>>> dp) {
    for (Integer n: dp.keySet()) {
      for (Integer k: dp.get(n).keySet()) {
        System.out.printf("n=%d\tk=%d:\t", n, k);
        for (Integer c: dp.get(n).get(k)) {
          System.out.print(c + " ");
        }
        System.out.println();
      }
    }
  }
  
  private static boolean addSum(int n, int k, Map<Integer, Map<Integer, List<Integer>>> dp, List<List<Integer>> result, List<Integer> primes, List<Integer> currentPrimes, int currentPrimeIndex) {
    if (dp.containsKey(n) && dp.get(n).containsKey(k)) {
      currentPrimes.addAll(dp.get(n).get(k));
      result.add(new LinkedList<>(currentPrimes));
      currentPrimes.removeAll(dp.get(n).get(k));
      return true;
    }
    
    if (k == 1) {
      if (!currentPrimes.contains(n) && primes.contains(n)) {
        currentPrimes.add(n);
        result.add(new LinkedList<>(currentPrimes));
        currentPrimes.remove(currentPrimes.size() - 1);
      }
      return true;
    }
    
    for (int i = currentPrimeIndex; i < primes.size() && primes.get(i) <= n; i++) {
      if (currentPrimes.contains(primes.get(i))) {
        continue;
      }
      
      currentPrimes.add(primes.get(i));
      if (addSum(n - primes.get(i), k-1, dp, result, primes, currentPrimes, currentPrimeIndex+1)) {
        if (!dp.containsKey(n)) {
          dp.put(n - primes.get(i), new HashMap<>());
        }
        dp.get(n - primes.get(i)).put(k-1, new LinkedList<>(currentPrimes));
      }
      currentPrimes.remove(currentPrimes.size() - 1);
    }
    
    return false;
  }
  
  private static List<Integer> getAllPrimes(int n) {
    boolean[] isPrime = new boolean[n+1];
    Arrays.fill(isPrime, true);
    List<Integer> result = new ArrayList<>();
    
    for (int i = 2; i <= n; i++) {
      if (isPrime[i]) {
        result.add(i);
        for (int j = 2*i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    
    return result;
  }
}
