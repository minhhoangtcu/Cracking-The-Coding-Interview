package leetCode;

import java.util.ArrayList;
import java.util.List;

public class BrickWall {
  
  public static void main(String[] args) {
//    int[][] stuff = {[1,2,2,1], [3,1,2], [1,3,2], [2,4], [3,1,2], [1,3,1,1]};
    
    List<List<Integer>> wall = new ArrayList<>();
    wall.add(new ArrayList<Integer>());
  }

  public int leastBricks(List<List<Integer>> wall) {
    // Count the width of the wall
    List<Integer> firstRow = wall.get(0);
    int width = 0;

    for (int i = 0; i < firstRow.size(); i++) {
      width += firstRow.get(i);
    }

    // Build a map of intersection 0 is between brick 0 and 1,
    // 1 means between brick 1 and 2... n-1 means between brick
    // brick n-1 and n for n be the last index of the list
    int[] intersections = new int[width];

    for (int row = 0; row < wall.size(); row++) {
      List<Integer> currentRow = wall.get(row);
      int currentIndex = -1; // start from the intersection between
                             // air and first brick

      // go from the first brick to last brick (exlusive), because
      // the last one doesn't matter
      for (int brick = 0; brick < currentRow.size() - 1; brick++) {
        currentIndex += currentRow.get(brick);
        intersections[currentIndex]++;
      }
    }

    int minIntersection = Integer.MAX_VALUE;
    for (int i = 0; i < width; i++) {
      minIntersection = Math.min(minIntersection, intersections[i]);
    }
    
    return minIntersection;
  }


}
