package epi.recursion;

import java.util.List;

public class ListUtils {
  
  static void printListofListOfString(List<List<String>> ll) {
    for (List<String> set: ll) {
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

  static void printListofList(List<List<Integer>> ll) {
    for (List<Integer> set: ll) {
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
}
