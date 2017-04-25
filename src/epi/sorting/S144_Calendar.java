package epi.sorting;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * @author minhhoang
 *
 */
public class S144_Calendar {
  
  // Determines an event for starting time till ending time exclusively 
  public static class Event {
    int start;
    int end;
  }
  
  private static class EndPoint implements Comparable<EndPoint> {
    int time;
    boolean isStart;
    
    public EndPoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public int compareTo(EndPoint o) {
      if (time != o.time) {
        return Integer.compare(time, o.time);
      } else if (isStart && !o.isStart) {
        return 1; // end before start because end is exclusive
      } else if (!isStart && o.isStart) {
        return -1;
      } else {
        return 0;
      }
    }
  }
  
  public static int findMaxNumberOfEventsOnSamePeriod(List<Event> events) {
    List<EndPoint> endpoints = new LinkedList<>();
    
    // Convert to end points
    for (Event event : events) {
      endpoints.add(new EndPoint(event.start, true));
      endpoints.add(new EndPoint(event.end, false));
    }
    
    Collections.sort(endpoints);
    int maxNumberOfEventsOnSamePeriod = Integer.MIN_VALUE;
    int currentMax = 0;
    
    for (EndPoint endPoint : endpoints) {
      if (endPoint.isStart) {
        currentMax++;
        maxNumberOfEventsOnSamePeriod = Math.max(maxNumberOfEventsOnSamePeriod, currentMax);
      } else {
        currentMax--;
      }
    }
    
    return maxNumberOfEventsOnSamePeriod;
  }
}
