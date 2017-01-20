package epi.strings;

/**
 * Write a program which takes as input a string s and returns the snakestring of s
 * 
 * "Hello World!"
 *  0123456789
 * 
 *  e       l
 * H l o W r d
 *    l   o   !
 *    
 * first row : e _ l        indexes: 1 5 9
 * middle row: H l o W r d  indexes: 0 2 4 6 8 10
 * last row  : l o !        indexes: 3 7 11
 * 
 * @author minhhoang
 *
 */
public class S711_StringSinusoidally {
  
  public static void main(String[] args) {
    System.out.println(getSnake("Hello World!"));
  }
  
  public static String getSnake(String input) {
    
    /*
     * Maintain 3 buckets for 3 rows. For all even indexed characters, we put it in the middle
     * bucket. Else, we alternatively put the character in either the first or last bucket.
     */
    
    SnakeCreator creator = new SnakeCreator(3);
    boolean nextIsFirst = true;
    
    for (int i = 0; i < input.length(); i++) {
      if (i % 2 == 0) {
        creator.putCharInRow(input.charAt(i), 1);
      } else {
        if (nextIsFirst) {
          creator.putCharInRow(input.charAt(i), 0);
        } else {
          creator.putCharInRow(input.charAt(i), 2);
        }
        
        nextIsFirst = !nextIsFirst;
      }
    }
    
    return creator.getSnake();
  }
}

class SnakeCreator {
  private StringBucket[] buckets;
  private int rows;
  
  public SnakeCreator(int rows) {
    this.rows = rows;
    buckets = new StringBucket[rows];
   
    for (int i = 0; i < rows; i++) {
      buckets[i] = new StringBucket();
    }
  }
  
  public void putCharInRow(char c, int row) {
    for (int i = 0; i < rows; i++) {
      if (i == row) {
        buckets[i].putChar(c);
      } else {
        buckets[i].putSpace();
      }
    }
  }
  
  public String getSnake() {
    StringBuffer temp = new StringBuffer();
    
    for (StringBucket bucket : buckets) {
      temp.append(bucket.getRow());
    }
    
    return temp.toString();
  }
}

class StringBucket {
  private StringBuffer sb;
  
  public StringBucket() {
    sb = new StringBuffer();
  }
  
  public void putChar(char c) {
    sb.append(c + " ");
  }
  
  public void putSpace() {
    sb.append("  ");
  }
  
  public String getRow() {
    return sb.toString() + "\n";
  }
}
