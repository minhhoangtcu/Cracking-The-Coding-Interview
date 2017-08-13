package selfstudy;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {
  public static void main(String[] args) {
    System.out.println(getTotalTime(new int[] {1, 1, 2, 1}, 1));
    System.out.println(getTotalTime(new int[] {1, 1, 2, 1}, 2));
    System.out.println(getTotalTime(new int[] {1, 1, 2, 1}, 3));
    System.out.println(getTotalTime(new int[] {1, 1, 2, 3}, 3));
  }

  public static int getTotalTime(int[] tasks, int coolDownTime) {
    int time = 1;
    Map<Integer, Integer> taskIDToLastExecutionTime = new HashMap<>();
    
    for (int taskID: tasks) {
      if (taskIDToLastExecutionTime.containsKey(taskID)) {
        int nextPossibleExecutionTime = taskIDToLastExecutionTime.get(taskID) + coolDownTime + 1;
        if (nextPossibleExecutionTime > time) {
          time = nextPossibleExecutionTime;
        }
      }
      
      taskIDToLastExecutionTime.put(taskID, time);
      time++;
    }
    
    return time - 1; // Because always add additional at the end.
  }
}
