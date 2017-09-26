package selfstudy;

import java.util.*;

public class TaskScheduler {
  public static void main(String[] args) {
//    System.out.println(leastInterval(new char[] {}, 2));
//    System.out.println(leastInterval(new char[] {'A'}, 2));
//    System.out.println(leastInterval(new char[] {'A', 'A'}, 4));
//    System.out.println(leastInterval(new char[] {'A'}, 10));
//    System.out.println(leastInterval(new char[] {'A', 'B'}, 2));
//    System.out.println(leastInterval(new char[] {'A', 'B', 'B'}, 2));
//    System.out.println(leastInterval(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    
    System.out.println(leastInterval(new char[] {'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
  }

  public static int leastInterval(char[] tasks, int coolDownTime) {
    Map<Character, Task> mapping = new HashMap<>();
    for (char taskID: tasks) {
      if (!mapping.containsKey(taskID)) {
        mapping.put(taskID, new Task(taskID));
      }
      mapping.get(taskID).increaseCount();
    }
    
    PriorityQueue<Task> queue = new PriorityQueue<>();
    queue.addAll(mapping.values());
    
    int time = 0;
    while (!queue.isEmpty()) {
      int numTaskToExecuteCurrentCycle = coolDownTime + 1;
      List<Task> executedTasksCurrentCycle = new ArrayList<>();
      
      while (numTaskToExecuteCurrentCycle > 0 && !queue.isEmpty()) {
        Task next = queue.poll();
        next.decreaseCount();
        executedTasksCurrentCycle.add(next);
        numTaskToExecuteCurrentCycle--;
        time++; //successfully executed task
      }
      
      for (Task task: executedTasksCurrentCycle) {
        if (task.getCount() > 0) {
          queue.add(task);
        }
      }
      
      time += queue.isEmpty() ? 0 : numTaskToExecuteCurrentCycle;
    }
    
    return time;
  }
}

class Task implements Comparable<Task> {
  private char taskID;
  private int repeats;
  
  public Task(char taskID) {
    this.taskID = taskID;
  }
  
  @Override
  public int compareTo(Task o) {
    return Integer.compare(o.repeats, repeats);
  }
  
  public int getCount() {
    return repeats;
  }
  
  public void increaseCount() {
    repeats++;
  }
  
  public void decreaseCount() {
    repeats--;
  }
  
  public char getTaskID() {
    return taskID;
  }
}