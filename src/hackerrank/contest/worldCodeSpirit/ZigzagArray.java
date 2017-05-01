package hackerrank.contest.worldCodeSpirit;

public abstract class ZigzagArray {
  
  public static void main(String[] args) {
    System.out.println(minimumDeletions(new int[] {4, 2, 6, 3, 10, 1}));
  }

  static int minimumDeletions(int[] a) {
    int minimumDeletion = 0;

    if (a.length < 3) {
      return minimumDeletion; // already zig zag
    }

    // skip first two numbers, because they are also in the same sequence
    int currentSequenceSize = 2;
    // neg for descending, 0 for equal, pos for ascending
    int lastSequence = Integer.compare(a[1], a[0]);

    for (int i = 2; i < a.length; i++) {
      int currentSequence = Integer.compare(a[i], a[i - 1]);

      // If were in an equal sequence, we should start anew because we only need
      // to care about ascending and descending
      if (lastSequence == 0 || !isSameSequence(lastSequence, currentSequence)) {
        if (currentSequenceSize > 2) {
          minimumDeletion += (currentSequenceSize - 2); // must remove all except the two ends
        }

        currentSequenceSize = 1; // comparing with the previous -> already had 1
      }

      currentSequenceSize++;
    }

    // Last sequence
    if (currentSequenceSize > 2) {
      minimumDeletion += (currentSequenceSize - 2);
    }

    return minimumDeletion;
  }

  private static boolean isSameSequence(int a, int b) {
    return (a < 0) == (b < 0);
  }

}
