package selfstudy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Tips {
  
  public static void main(String[] args) {
    // Array of int to Set
    Set<Integer> s = Arrays.stream(new int[] {}).boxed().collect(Collectors.toSet());
    
    // Array of int to List
    List<Integer> l = Arrays.stream(new int[] {}).boxed().collect(Collectors.toList());
    
    // List to array of int
    int[] arr = l.stream().mapToInt(i->i).toArray();
  }
}
