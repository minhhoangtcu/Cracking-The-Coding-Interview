package selfstudy;

import java.util.LinkedList;
import java.util.List;

public class DiskScheduling {

  public static void main(String[] args) {
    System.out.println("FCFS : " + getTotalDistanceFromMovements(
        new int[] {2150, 2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681}));
    System.out.println("FCFS : " + getTotalTime(
        new int[] {2150, 2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681}));
    
    System.out.println("SSTF : " + getTotalDistance(getArrangementSSTF(2150,
        new int[] {2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681})));
    System.out.println("SSTF : " + getTotalTime(getArrangementSSTF(2150,
        new int[] {2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681})));

    System.out.println("SCAN : " + getTotalDistanceFromMovements(
        new int[] {2150, 2296, 2800, 3681, 4965, 4999, 2069, 1618, 1523, 1212, 544, 356}));
    System.out.println("SCAN : " + getTotalTime(getArrangementSSTF(2150,
        new int[] {2150, 2296, 2800, 3681, 4965, 4999, 2069, 1618, 1523, 1212, 544, 356})));
    
    System.out.println("LOOK : " + getTotalDistanceFromMovements(
        new int[] {2150, 2296, 2800, 3681, 4965, 2069, 1618, 1523, 1212, 544, 356}));
    System.out.println("LOOK : " + getTotalTime(getArrangementSSTF(2150,
        new int[] {2150, 2296, 2800, 3681, 4965, 2069, 1618, 1523, 1212, 544, 356})));
    
    System.out.println("C-SCAN: " + getTotalDistanceFromMovements(
        new int[] {2150, 2296, 2800, 3681, 4965, 4999, 0, 356, 544, 1212, 1523, 1618, 2069}));
    System.out.println("C-SCAN : " + getTotalTime(getArrangementSSTF(2150,
        new int[] {2150, 2296, 2800, 3681, 4965, 4999, 0, 356, 544, 1212, 1523, 1618, 2069})));
    
    System.out.println("C-LOOK: " + getTotalDistanceFromMovements(
        new int[] {2150, 2296, 2800, 3681, 4965, 356, 544, 1212, 1523, 1618, 2069}));
    System.out.println("C-LOOK : " + getTotalTime(getArrangementSSTF(2150,
        new int[] {2150, 2296, 2800, 3681, 4965, 356, 544, 1212, 1523, 1618, 2069})));
  }

  private static int[] getArrangementSSTF(int start, int[] requests) {
    List<Integer> distances = new LinkedList<>();
    int last = start;

    for (int i = 0; i < requests.length; i++) {
      int shortestIndex = getShortestDistanceIndex(last, requests);
      distances.add(Math.abs(last - requests[shortestIndex]));
      last = requests[shortestIndex];
      requests[shortestIndex] = Integer.MAX_VALUE;
    }

    return distances.stream().mapToInt(i -> i).toArray();
  }

  private static int getShortestDistanceIndex(int from, int[] to) {
    int shortest = Integer.MAX_VALUE;
    int index = -1;

    for (int i = 0; i < to.length; i++) {
      int currentDistance = Math.abs(to[i] - from);
      if (currentDistance < shortest) {
        index = i;
        shortest = currentDistance;
      }
    }

    return index;
  }

  private static int getTotalDistanceFromMovements(int[] moves) {
    List<Integer> distances = new LinkedList<>();

    for (int i = 1; i < moves.length; i++) {
      distances.add(Math.abs(moves[i] - moves[i - 1]));
    }

    return getTotalDistance(distances.stream().mapToInt(i -> i).toArray());
  }

  private static int getTotalDistance(int[] distances) {
    int total = 0;

    for (int d : distances) {
      total += d;
    }

    return total;
  }
  
  private static double getTotalTime(int[] distances) {
    double total = 0;

    for (int d : distances) {
      total += .7561 + .2438 * Math.sqrt(d);
    }

    return total;
  }
}
