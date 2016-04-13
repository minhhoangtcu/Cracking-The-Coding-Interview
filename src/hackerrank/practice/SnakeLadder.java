package hackerrank.practice;

import java.util.*;

public class SnakeLadder {

    public static void main(String[] args) {
    	
    	SnakeLadder sl = new SnakeLadder();
    	
        Scanner scanner = new Scanner(System.in);
        int numOfTests = scanner.nextInt();
        
        for (int i = 0; i < numOfTests; i++) {
            
            // Set the board with empty node
            Node[] board = new Node[101];
            for (int j = 1; j < 101; j++) {
                board[j] = sl.new Node(j, false);
            }
            
            // Put the ladders into the board
            int numOfLadders = scanner.nextInt();
            for (int j = 0; j < numOfLadders; j++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                board[start].setSpecial(end);
            }
            
            // Put the snakes into the board
            int numOfSnakes = scanner.nextInt();
            for (int j = 0; j < numOfSnakes; j++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                board[start].setSpecial(end);
            }
            
            // Put adjacents into nodes in the board
            for (int j = 1; j < 101; j++) {
                if (!board[j].isSpecial) {
                    int next = j + 1;
                    while (next <= 100 && next <= j + 6) { // add only upto 100 and 6 next stuff
                        if (board[next].isSpecial)
                            board[j].adjacents.add(board[board[next].gotoPos]); // if we found a ladder/snake, add the end point instead
                        else
                            board[j].adjacents.add(board[next]);
                        next++;
                    }
                }
            }
            
            // Breadth-first search from 1 to get to 100
            Queue<Node> queue = new LinkedList<>();
            boolean isWin = false;
            
            queue.add(board[1]);
            board[1].distance = 0;
            
            while (!queue.isEmpty()) {
                
                Node current = queue.remove();
                
                // End game condition. Found the shortest way, according to breath-first search, this should be the shortest route.
                if (current.position == 100) {
                    System.out.println(current.distance);
                    isWin = true;
                }
                
                for (Node adj: current.adjacents) {
                    if (adj.distance == Integer.MAX_VALUE) { // Make sure that we do not go through visited nodes
                        adj.distance = current.distance+1;
                        queue.add(adj);
                    }                    
                }
            }
            
            if (!isWin)
                System.out.println(-1);
            
            scanner.close();
        }
        

    }
    
    class Node {
        
        int distance;
        int position;
        LinkedList<Node> adjacents;
        boolean isSpecial;
        int gotoPos;
        
        public Node(int position, boolean isSpecial) {
            this.position = position;
            this.isSpecial = isSpecial;
            this.distance = Integer.MAX_VALUE;
            if (!isSpecial)
                adjacents = new LinkedList<>();
        }
        
        public void setSpecial(int gotoPos) {
            isSpecial = true;
            this.gotoPos = gotoPos;
            adjacents = null;
        }
    }
}
