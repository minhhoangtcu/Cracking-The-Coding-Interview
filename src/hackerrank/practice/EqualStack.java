package hackerrank.practice;

import java.util.*;

public class EqualStack {

    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        
        int totalH1 = 0;
        int h1[] = new int[n1];
        for(int h1_i=0; h1_i < n1; h1_i++){
            h1[h1_i] = in.nextInt();
            totalH1 += h1[h1_i];
        }
        
        int totalH2 = 0;
        int h2[] = new int[n2];
        for(int h2_i=0; h2_i < n2; h2_i++){
            h2[h2_i] = in.nextInt();
            totalH2 += h2[h2_i];
        }
        
        int totalH3 = 0;
        int h3[] = new int[n3];
        for(int h3_i=0; h3_i < n3; h3_i++){
            h3[h3_i] = in.nextInt();
            totalH3 += h3[h3_i];
        }
        
        EqualStack es = new EqualStack();
        State state = es.new State();
        state.totalHeights[0] = totalH1;
        state.totalHeights[1] = totalH2;
        state.totalHeights[2] = totalH3;
        state.numOfItems[0] = n1;
        state.numOfItems[1] = n2;
        state.numOfItems[2] = n3;
        state.h1 = h1;
        state.h2 = h2;
        state.h3 = h3;
        
        reduce(state, state.getStackHigest());
        
        in.close();
        
    }
    
    public static void reduce(State state, int stack) {
        
        // reduce the height of two stacks to as near as the third stack
        if (stack != 1) {
            while (state.hasMore(1) && state.getHeight(1) - state.getTop(1) >= state.getHeight(stack)) {
                state.removeTop(1);
            }   
        }
        
        if (stack != 2) {
            while (state.hasMore(2) && state.getHeight(2) - state.getTop(2) >= state.getHeight(stack)) {
                state.removeTop(2);
            }   
        }
        
        if (stack != 3) {
            while (state.hasMore(3) && state.getHeight(3) - state.getTop(3) >= state.getHeight(stack)) {
                state.removeTop(3);
            }   
        }
        
        // check if they are the same!
        if (state.isSame()) {
            System.out.println(state.getHeight(1));
            return;   
        }
        
        // reduce the height of the tallest stack
        int tallest = state.getStackHigest();
        state.removeTop(tallest);
        
        reduce(state, tallest);
    }
    
    class State {
        
        int[] totalHeights;
        int[] removed;
        int[] numOfItems;
        int[] h1, h2, h3;
        
        State() {
            totalHeights = new int[3];
            removed = new int[3];
            numOfItems = new int[3];
        }
        
        boolean isSame() {
            return (totalHeights[0] == totalHeights[1] && totalHeights[0] == totalHeights[2]);
        }
        
        int[] getStack(int i) {
            if (i == 1)
                return h1;
            else if (i == 2)
                return h2;
            else if (i == 3)
                return h3;
            else
                return null;
        }
        
        int getHeight(int i) {
            return totalHeights[i-1]; // i-1 because index start at 0
        }
        
        int getTop(int i) {
            return getStack(i)[removed[i-1]]; // removed[i-1] because index start at 0
        }
        
        boolean hasMore(int i) {
            return removed[i-1] < numOfItems[i-1];
        }
        
        void removeTop(int i) {
            totalHeights[i-1] -= getTop(i);
            removed[i-1] += 1;
        }
        
        int getStackLowest() {
            int top1 = getTop(1);
            int top2 = getTop(2);
            int top3 = getTop(3);
            
            if (top1 <= top2 && top1 <= top3)
                return 1;
            else if (top2 <= top1 && top2 <= top3)
                return 2;
            else
                return 3;
        }
        
        int getStackHigest() {
            int top1 = getTop(1);
            int top2 = getTop(2);
            int top3 = getTop(3);
            
            if (top1 >= top2 && top1 >= top3)
                return 1;
            else if (top2 >= top1 && top2 >= top3)
                return 2;
            else
                return 3;
        }
    }
}
