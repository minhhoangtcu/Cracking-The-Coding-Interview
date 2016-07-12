package hackerrank.practice;

import java.util.Arrays;
import java.util.Scanner;

import hackerrank.practice.DynamicArray.SeqList;

public class DynamicArray {
	
	public static void main(String[] args) {
        
		Scanner sc = new Scanner(System.in);
        int numOfSeqs = sc.nextInt();
        int numOfQueries = sc.nextInt();
        
        DynamicArray s = new DynamicArray();
        SeqList sl = s.new SeqList(numOfSeqs);
        
        // sc.nextLine(); // read through first line
        int lastAns = 0;
        
        for (int i = 0; i < numOfQueries; i++) {
            
            int mode = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            
            if (mode == 1) {
                int seq = (x ^ lastAns) % numOfSeqs;
                sl.append(seq, y);
            } else {
                int seq = (x ^ lastAns) % numOfSeqs;
                int index = y % sl.getSize(seq);
                lastAns = sl.get(seq, index);
                System.out.println(lastAns);
            }
            
        }
        
    }
    
    class SeqList {
        
        int[][] seqList;
        int[] size; // number of elements in each seq
        
        SeqList(int numOfSeqs) {
            seqList = new int[numOfSeqs][5];
            size = new int[numOfSeqs]; // start with 0 elements
        }
        
        public void append(int seq, int number) {
            
            if (isFull(seq)) {
                expand(seq);
            }
            
            int lastIndex = size[seq];
            seqList[seq][lastIndex] = number;
            size[seq]++;
        }
        
        public int get(int seq, int index) {
            return seqList[seq][index];
        }
        
        public int getSize(int seq) {
            return size[seq];
        }
        
        /// double the size of specified seq
        private void expand(int seq) {
            int oldSize = size[seq];
            seqList[seq] = Arrays.copyOf(seqList[seq], oldSize*2);
        }
        
        private boolean isFull(int seq) {
            if (seqList[seq].length == size[seq]) {
                return true;
            } else {
                return false;
            }
        }
    }
}