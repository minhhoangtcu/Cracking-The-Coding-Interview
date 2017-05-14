package hackerrank.contest.worldCodeSpirit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CultureConferenceBrute {

  public static void main(String[] args) {
    test(new int[][] {{0, 0}});
    test(new int[][] {{0, 1}}); // 1 sick
    
    test(new int[][] {{0, 0}, {1, 0}});
    test(new int[][] {{0, 0}, {1, 1}});
    
    test(new int[][] {{0, 1}, {1, 1}, {1, 1}}); // all sick
    
    test(new int[][] {{0, 0}, {1, 1}, {1, 1}}); // parent sick
    
    test(new int[][] {{0, 1}, {1, 0}, {1, 0}}); 
    
    test(new int[][] {{0, 1}, {1, 1}, {1, 0}});
    
    test(new int[][] {{0, 1}, {1, 0}, {1, 1}});
  }
  
  private static void test(int[][] e) {
    System.out.println(getMinimumEmployees(e));
  }

  private static int getMinimumEmployees(int[][] e) {
    Person[] people = new Person[e.length + 1]; // +1 for CEO
    people[0] = new Person(0);
    boolean[] burnout = new boolean[e.length + 1];

    for (int i = 1; i <= e.length; i++) {
      int parent = e[i - 1][0];

      // Add the person
      people[i] = new Person(parent);
      burnout[i] = e[i - 1][1] == 1;

      // Add this person as child
      people[parent].children.add(i);
    }

    return getMin(1, people, burnout);
  }

  private static int getMin(int i, Person[] people, boolean[] burnout) {
    // base case: leaf node.
    // If the leaf is burn out -> before was not send to vacation -> has to send to vacation
    if (people[i].children.isEmpty()) {
      return burnout[i] ? 1 : 0;
    }

    // find optimal solution if send this person
    boolean[] burnout1 = Arrays.copyOf(burnout, burnout.length);
    int minFromSent = Integer.MAX_VALUE;

    burnout1[i] = false;
    burnout1[people[i].parent] = false;
    for (Integer c : people[i].children) {
      burnout1[c] = false;
    }

    for (Integer c : people[i].children) {
      boolean[] tempBurnout = Arrays.copyOf(burnout1, burnout.length);
      int tempMin = getMin(c, people, tempBurnout);
      if (hasBurnout(i, people, tempBurnout)) {
        minFromSent = Math.min(minFromSent, 1 + tempMin);
      }
    }
    
    minFromSent = hasBurnout(i, people, burnout1) ? Integer.MAX_VALUE : minFromSent;

    // find optimal solution if not send this person
    boolean[] burnout2 = Arrays.copyOf(burnout, burnout.length);
    int minFromNotSent = Integer.MAX_VALUE;

    for (Integer c : people[i].children) {
      boolean[] tempBurnout = Arrays.copyOf(burnout1, burnout.length);
      int tempMin = getMin(c, people, burnout2);
      if (hasBurnout(i, people, burnout2)) {
        minFromNotSent = Math.min(minFromNotSent, tempMin);
      }
    }

    return Math.min(minFromSent + 1, minFromNotSent);
  }

  static class Person {
    int parent;
    List<Integer> children;

    public Person(int parent) {
      this.parent = parent;
      children = new LinkedList<>();
    }
  }
  
  static boolean hasBurnout(int i, Person[] people, boolean[] burnout) {
    if (people[i].children.isEmpty()) {
      return burnout[i];
    }
    
    for (Integer c: people[i].children) {
      if (hasBurnout(c, people, burnout)) {
        return true;
      }
    }
    
    return false;
  }
}
