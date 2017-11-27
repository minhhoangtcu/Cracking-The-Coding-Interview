package selfstudy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Hello Minh!
// I am here.

// Purpose?
// Equal ratio between all functions.

// Snacks
// Roasted Seaweed
// Travel
// Structured data - diff structures - keep track - make transparent

// vhu14@bloomberg.net

//1. Insert - amotized O(1)
//2. Retrieve (contains) - O(1)
//3. Delete - O(1)
//4. Get a random element from your data structure - O(1)

/*

0 x
1 x
2
3
4 x
5 x
6
7 x

70% full

Solution 1:
random(0, 7 inclusively) -> 2
1. Linear search up and down - O(n)
2. Generate another random - O(n)

Solution 2:
int[] - auto scaling
Set<Integer>

*/

class ConstantTime {
    private int[] nums;
    private Map<Integer, List<Integer>> numToIndexes;
    private int realSize;
    
    public ConstantTime(int size) {
        nums = new int[size];
        set = new HashSet<>();
        realSize = 0;
    }
    
    public void insert(int num) throws MemoryExceededException {
        nums[realSize] = num;
        List<Integer> indexes = numToIndexes.getOrDefault(num, new LinkedList<>());
        indexes.add(realSize);
        numToIndexes.put(num, indexes);
        realSize++;
        
        if (realSize == nums.length) {
            try {
                int[] temp = new int[realSize*2];
            } catch (SomeSystemError e) {
                throw MemoryExceededException("Cannot allocate more memory.");
            }
            
            Arrays.copy(nums, temp, 0, realSize);
            nums = temp;
        }
    }
    
    public boolean retrieve(int num) {
        return numToIndexes.containsKey(num);
    }
    
    // Delete all num from our list, returns true if the provided number is
    // in the list.
    public boolean delete(int num) {
        List<Integer> indexes = numToIndexes.getOrDefault(num, new LinkedList<>());
        for (Integer index: indexes) {
            swap(nums, index, realSize-1);
            realSize--;
        }
        numToIndexes.remove(num);
        return !indexes.isEmpty();
    }
    
    public int getRandom() {
        return nums[random(0, realSize)];
    }
    
    private void swap(int[] arr, int a, int b);
    private int random(int begin, int exclusiveEnd);
}