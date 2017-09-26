package selfstudy;

/**
 * Given a score, returns number of ways to sum up to the score with 2, 3, and 7.
 * 
 * @author minhhoang
 *
 */
public class ScoreToSum {
  public int numWays(int score) {
    if (score == 0 || score == 2 || score == 3 || score == 7) {
      return 1;
    }
    return numWays();
  }
}
