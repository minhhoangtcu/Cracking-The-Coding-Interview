package hackerrank.contest.worldCodeSpirit;

import java.util.*;

/**
 * https://www.hackerrank.com/contests/stryker-codesprint/challenges/julia-and-bst 
 */
public class JuliaSearchTree {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        JuliaSearchTree jst = new JuliaSearchTree();
        Tree tree = jst.new Tree(false);
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            tree.add(sc.nextInt());
        }
        
        int sum = 0;
        int i = 1;
        for (Integer count: tree.distancesCount.values()) {
            sum += count*i;
            i++;
        }
        
        System.out.println(sum);
        sc.close();
    }
    
    class Tree {
        
       HashMap<Integer, Integer> distancesCount = new HashMap<>();
       Node root;
	   int size;
	   boolean isDebug;
        
        public Tree(boolean isDebug) {
            this.isDebug = isDebug;
        }

        public void add(int id) {
            if (isEmpty()) {
                root = new Node(id, 0);
                if (isDebug) System.out.printf("Added first node with id: %d\n", id);
            } else {
                
                Node runner = root;
                int distance = 0;
                
                while (runner != null) {

                    if (runner.id == id) {
                        if (isDebug) System.out.printf("Node with id: %d is already in the tree\n", id);
                        break;
                    } else if (id > runner.id) {
                        if (runner.right != null)
                            runner = runner.right;
                        else {
                            if (isDebug) System.out.printf("Added %d to right of %d\n", id, runner.id);
                            runner.right = new Node(id, distance+1);
                            addDistance(distance+1);
                            break;
                        }

                    } else {
                        if (runner.left != null)
                            runner = runner.left;
                        else {
                            if (isDebug) System.out.printf("Added %d to left of %d\n", id, runner.id);
                            runner.left = new Node(id, distance+1);
                            addDistance(distance+1);
                            break;
                        }
                    }
                    
                    distance++;
                }
            }
            size++;
        }
        
        // increase to distance count
        public void addDistance(int distance) {
            if (!distancesCount.containsKey(distance))
                distancesCount.put(distance, 0);
            distancesCount.put(distance, distancesCount.get(distance) + 1);
        }
        
        public boolean isEmpty() {
		    return size == 0;
	    }
    }
    
    class Node {

        int id;
        Node left;
        Node right;
        int distanceFromRoot;

        public Node(int id, int distanceFromRoot) {
            this.id = id;
            this.distanceFromRoot = distanceFromRoot;
        }

        public int compareTo(Node other) {
            if (id < other.id)
                return -1;
            else if (id > other.id)
                return 1;
            else
                return 0;
        }
    }
}
