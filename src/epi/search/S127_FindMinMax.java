package epi.search;

/**
 * Design an algorithm to find the min and max elements in an array with less than 2(n - 1)
 * comparisons.
 * 
 * @author minhhoang
 *
 */
public class S127_FindMinMax {
  
  public static void main(String[] args) {
    System.out.println(findMinMax(new int[] {5, 2, 6, 1, 8, 10, 1, 2, -1, 0}));
    System.out.println(findMinMax(new int[] {1, 2, 3, 4}));
    System.out.println(findMinMax(new int[] {4, 3, 2, 1}));
    System.out.println(findMinMax(new int[] {1, 2, 3, 4, 5}));
    System.out.println(findMinMax(new int[] {5, 4, 3, 2, 1}));
  }

  public static MinMax findMinMax(int[] arr) {
    /*
     * If we always compare the incoming number with max, then we have 2 possibilities. The first
     * one is that the incoming number is greater than max. Thus, we just need to set max to that
     * number. Else, if need to compare the number with min, if it is higher than min, we can safely
     * ignore and set to min if it is smaller. However, this naive algorithm in worse case has the
     * first incoming number be the max already, thus for all other numbers, we always need 2
     * comparisons each.
     * 
     * However, if we split all numbers into two groups, lower and higher (n/2
     * comparisons required). Then we can compare all lower numbers with min, and all higher numbers
     * with max (n/2 - 1 comparisons for each group). We are guaranteed to have 3n/2 - 2 comparisons
     * in total.
     */
    
    MinMax globalMinMax = MinMax.getMinMax(arr[0], arr[1]);
    
    for (int i = 2; i < arr.length - 1; i += 2) {
      MinMax currentMinMax = MinMax.getMinMax(arr[i], arr[i + 1]); // split into categories
      globalMinMax.updateMinMax(currentMinMax);
    }
    
    // odd length array, we need to make one/two last compare
    if (arr.length % 2 == 1) {
      globalMinMax.updateMinMax(arr[arr.length - 1]);
    }

    return globalMinMax;
  }
}

class MinMax {
  int min;
  int max;

  private MinMax(int min, int max) {
    this.min = min;
    this.max = max;
  }
  
  void updateMinMax(MinMax minmax) {
    min = Math.min(min, minmax.min);
    max = Math.max(max, minmax.max);
  }
  
  void updateMinMax(int num) {
    if (num >= max) {
      max = num;
    } else if (num <= min) {
      min = num;
    }
  }
  
  static MinMax getMinMax(int first, int second) {
    return first > second ? new MinMax(second, first) : new MinMax(first, second);
  }
  
  @Override
  public String toString() {
    return String.format("Min: %d\tMax: %d", min, max);
  }
}
