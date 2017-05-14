package hackerrank.contest.worldCodeSpirit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CultureConference {
  
  public static void main(String[] args) {
    int[][] e1 = {{0, 1}, {1, 1}, {1, 1}}; // all sick
    System.out.println(getMinimumEmployees(e1));
  }
  
  private static int getMinimumEmployees(int[][] e){
    Person[] people = new Person[e.length + 1]; // +1 for CEO
    boolean[] burnout = new boolean[e.length + 1];
    
    for (int i = 1; i <= e.length; i++) {
      int parent = e[i - 1][0];
      
      // Add the person
      people[i] = new Person(parent);
      burnout[i] = e[i - 1][1] == 1;
      
      // Add this person as child
      people[parent].children.add(i);
    }
    
    return getMin(0, 0, people, burnout);
  }
  
  private static int getMin(int i, int sentPeople, Person[] people, boolean[] burnout) {
    // base case: leaf node. 
    // If the leaf is burn out -> before was not send to vacation -> has to send to vacation
    if (people[i].children.isEmpty()) {
      return burnout[i] ? sentPeople + 1 : sentPeople;
    }
    
    // find optimal solution if send this person
    boolean[] restore = Arrays.copyOf(burnout, burnout.length);
    
    burnout[i] = false;
    burnout[people[i].parent] = false;
    for (Integer c: people[i].children) {
      burnout[c] = false;
    }
    
    int minFromSent = Integer.MAX_VALUE;
    
    for (Integer c: people[i].children) {
      minFromSent = Math.min(minFromSent, getMin(c, sentPeople + 1, people, burnout));
    }
    
    // find optimal solution if not send this person
    int minFromNotSent = Integer.MAX_VALUE;
    
    for (Integer c: people[i].children) {
      minFromNotSent = Math.min(minFromNotSent, getMin(c, sentPeople, people, restore));
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
}
