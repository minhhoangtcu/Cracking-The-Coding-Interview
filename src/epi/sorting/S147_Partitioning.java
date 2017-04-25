package epi.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * You are given an array of student objects. Each student has an integer-valued age field that is
 * to be treated as a key. Rearrange the elements of the array so that students of equal age appear
 * together. The order in which different ages appear is not important. How would your solution
 * change if ages have to appear in sorted order?
 * 
 * @author minhhoang
 *
 */
public class S147_Partitioning {
  
  public static void main(String[] args) {
    Student[] students1 = {new Student("Minh", 1), new Student("Quang", 2)};
    partition(students1);
    System.out.println(Arrays.toString(students1));
    
    Student[] students2 = {new Student("Minh", 1), new Student("Quang", 2), new Student("Minh2", 1)};
    partition(students2);
    System.out.println(Arrays.toString(students2));
    
    Student[] students3 = {new Student("Greg", 14), new Student("John", 12), 
        new Student("Andy", 11), new Student("Jim", 13), new Student("Phil", 12),
        new Student("Bob", 13), new Student("Chip", 13), new Student("Tim", 14)};
    partition(students3);
    System.out.println(Arrays.toString(students3));
  }

  static class Student {
    int age; // should be treated as key
    String name;

    public Student(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return String.format("%s - %d", name, age);
    }
  }

  /**
   * Partitions an array of students so that all students with the same age are next to each other.
   * This method keeps track of two hash maps, the first one links a certain age to the number of
   * students sharing that age. The second map takes a certain age and point to the offset in the
   * array. For example, if we have 3 students with age 1, and 2 students with age 2. Then the first
   * 3 students should be in the index 0, 1, 2 and the latter 2 should be in 3 and 4. The first map
   * will be {1: 3, 2: 2} and the second will be {1:0, 2: 3}. In execution, the method will take a
   * student, Replacing, with a certain age then get the student that will be replaced by Replacing.
   * Getting Replacing is done by getting an offset from the second map with any age. Then, we just
   * need to get Replacing's age and find the offset, which is its correct place. Then, we just need
   * to swap them, decrement the count in the first map, and update the offset in the second map.
   * 
   * @param students
   */
  public static void partition(Student[] students) {
    Map<Integer, Integer> ageToSize = new HashMap<>();
    Map<Integer, Integer> ageToOffset = new HashMap<>();

    // Prepare the two maps
    for (Student s : students) {
      ageToSize.merge(s.age, 1, Integer::sum);
    }

    int offset = 0;
    for (Entry<Integer, Integer> e : ageToSize.entrySet()) {
      ageToOffset.put(e.getKey(), offset);
      offset += e.getValue();
    }

    // Partition
    while (!ageToOffset.isEmpty()) {
      Entry<Integer, Integer> currentAgeToOffset = ageToOffset.entrySet().iterator().next();

      int replacingIndex = currentAgeToOffset.getValue();
      Student replacing = students[replacingIndex];

      int correctPlaceForReplacing = ageToOffset.get(replacing.age);
      swap(students, replacingIndex, correctPlaceForReplacing);
      
      // Update maps
      ageToOffset.merge(replacing.age, 1, Integer::sum);
      
      int newSize = ageToSize.get(replacing.age) - 1;
      if (newSize == 0) {
        ageToOffset.remove(replacing.age); // Finished partitioning in this age group 
      }
      ageToSize.put(replacing.age, newSize);
    }
  }
  
  private static void swap(Student[] students, int a, int b) {
    Student temp = students[a];
    students[a] = students[b];
    students[b] = temp;
  }
}
