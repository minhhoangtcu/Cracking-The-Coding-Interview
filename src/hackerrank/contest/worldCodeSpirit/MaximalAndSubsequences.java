package hackerrank.contest.worldCodeSpirit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MaximalAndSubsequences {

  public static void main(String[] args) {
    // System.out.println(isIthBitSet(-1, 60));
    // System.out.println(isIthBitSet(-1, 0));
    // System.out.println(isIthBitSet(-1, 63));
    // System.out.println(isIthBitSet(-1, 64));
    // System.out.println(isIthBitSet(-2, 63));
    // System.out.println(isIthBitSet(0b1111, 0));
    // System.out.println(isIthBitSet(0b1111, 4));
    // System.out.println(isIthBitSet(0b1111, 7));
    // System.out.println(isIthBitSet(0b1111, 59));
    // System.out.println(isIthBitSet(0b1111, 60));
    // System.out.println(isIthBitSet(0b1111, 61));
    // System.out.println(isIthBitSet(0b1111, 62));
    // System.out.println(isIthBitSet(0b1111, 63));

    // test(3, 2, new long[] {3, 5, 6});
    // test(4, 2, new long[] {5, 4, 3, 8});
    // test(4, 3, new long[] {5, 4, 3, 8});
    // test(4, 1, new long[] {5, 4, 3, 8});
    // test(4, 4, new long[] {5, 4, 3, 8});
    // test(4, 2, new long[] {21, 19, 22, 20});
    // test(4, 3, new long[] {9, 15, 27, 14});

    // test(4, 1, new long[] {5, 5, 5, 5});
    // test(4, 2, new long[] {5, 5, 5, 5});
    // test(4, 3, new long[] {5, 5, 5, 5});
    // test(4, 4, new long[] {5, 5, 5, 5});

    // test(4, 2, new long[] {3, 7, 16, 32});
    // test(3, 2, new long[] {1, 4, 4});

//    System.out.println(combinationFast(3, 1) + " == 3");
//    System.out.println(combinationFast(3, 2) + " == 3");
//    System.out.println(combinationFast(3, 3) + " == 1");
    System.out.println(combinationFast(6, 3) + " == 3");
    // System.out.println(combination(10, 3) + " == 120");
    System.out.println(combinationFast(10, 3) + " == 120");
    // System.out.println(combination(10, 5) + " == 252");
    System.out.println(combinationFast(10, 5) + " == 252");
    // System.out.println(combination(50, 5) + " == 2118760");
    System.out.println(combinationFast(50, 5) + " == 2118760");
    // System.out.println(combination(50, 7) + " == 99884400");
    // System.out.println(combination(50, 9) + " == 505433686");
    // System.out.println(combination(50, 10) + " == 272278100");
    System.out.println(combinationFast(50, 10) + " == 272278100");
    System.out.println(binomMod(50, 10) + " == 272278100");
    // System.out.println(combination(100, 2) + " == 4950");
    // System.out.println(combination(100, 10) + " == 309335270");
    // System.out.println(combination(100, 12) + " == 43753753");
    System.out.println(combinationFast(100, 12) + " == 43753753");
    System.out.println(binomMod(100, 12) + " == 43753753");
    
    // test(3, 2, new long[] {1, 2, 4});
    // test(4, 2, new long[] {1, 2, 4, 8});
    // test(5, 2, new long[] {0, 0, 0});
    // test(5, 2, new long[] {0, 1, 3});
    // test(5, 3, new long[] {1, 2, 4, 8, 16});
    // test(5, 2, new long[] {1, 2, 4, 8, 16, 16});
    // test(5, 2, new long[] {1, 2, 4, 8, 16, 16, 17});

    // 001100100
    // 011001000
    // 100101100
    // 110010000
    // 111110100
    // test(5, 2, new long[] {100, 200, 300, 400});
//    test(5, 2, new long[] {100, 200, 300, 400, 500});
    // test(5, 2, new long[] {100, 200, 300, 400, 500, 150, 175, 126});
  }

  static void test(int n, int k, long[] a) {
    long[] result = solve(n, k, a);
    System.out.println("Sum: " + result[0]);
    System.out.println("Num: " + result[1]);
    System.out.println();
  }

  static long[] solve(int n, int k, long[] a) {
    List<Integer> indexesToValidNumbers = new ArrayList<>();

    for (int i = 0; i < 64; i++) {
      List<Integer> tempIndexes = new ArrayList<>();

      if (indexesToValidNumbers.isEmpty()) {
        for (int j = 0; j < a.length; j++) {
          if (isIthBitSet(a[j], i)) {
            tempIndexes.add(j);
          }
        }
      } else {
        for (int j = 0; j < indexesToValidNumbers.size(); j++) {
          if (isIthBitSet(a[indexesToValidNumbers.get(j)], i)) {
            tempIndexes.add(indexesToValidNumbers.get(j));
          }
        }
      }

      if (tempIndexes.size() >= k) {
        if (indexesToValidNumbers.isEmpty()) {
          indexesToValidNumbers = tempIndexes;
        } else {
          indexesToValidNumbers.retainAll(tempIndexes);
        }
      }
    }

    if (indexesToValidNumbers.isEmpty()) {
      return new long[] {0, combination(a.length, k)};
    }

    long result = a[indexesToValidNumbers.get(0)];
    for (int i = 1; i < indexesToValidNumbers.size(); i++) {
      result &= a[indexesToValidNumbers.get(i)];
    }

    return new long[] {result, combination(indexesToValidNumbers.size(), k)};
  }

  // find out if ith bit is set or not for ith be the bit from msb
  // to lsb. For example, 0 would be the msb.
  private static boolean isIthBitSet(long num, int i) {
    return (num >> (63 - i) & 1) == 1;
  }

  private static long combination(int N, int K) {
    BigInteger result = BigInteger.ONE;

    for (int k = 0; k < K; k++) {
      result = result.multiply(BigInteger.valueOf(N - k)).divide(BigInteger.valueOf(k + 1));
    }

    return result.mod(BigInteger.valueOf((long) (Math.pow(10, 9) + 7))).longValueExact();
  }
  
  private static BigInteger Mod = BigInteger.valueOf((long) (Math.pow(10, 9) + 7));
  
  private static long combinationFast(int N, int K) {
    BigInteger result = BigInteger.ONE;

    int bigFact = Math.max(K, N - K);
    int smallFact = Math.min(K, N - K);
    
    for (int factUp = bigFact + 1, factDown = smallFact;
        factUp <= N || factDown > 1;) {
      if (factUp <= N) {
        result = result.multiply(BigInteger.valueOf(factUp++));
      }
      
      while (factDown > 1) {
        result = result.multiply(BigInteger.valueOf(factDown--)
            .modInverse(Mod));
      }
    }
    
    return result.mod(Mod).longValueExact();
  }

  static double nCr(int n, int r) {
    int rfact = 1, nfact = 1, nrfact = 1, temp1 = n - r, temp2 = r;
    if (r > n - r) {
      temp1 = r;
      temp2 = n - r;
    }
    for (int i = 1; i <= n; i++) {
      if (i <= temp2) {
        rfact *= i;
        nrfact *= i;
      } else if (i <= temp1) {
        nrfact *= i;
      }
      nfact *= i;
    }
    return nfact / (double) (rfact * nrfact);
  }
  
  public static int[] factorials = new int[100001];
  public static int mod = 1000000007;
  public static BigInteger MOD = BigInteger.valueOf(1000000007);

  public static void calculateFactorials() {

      long f = 1;

      for (int i = 1; i < factorials.length; i++) {
          f = (f * i) % mod;
          factorials[i] = (int) f;
      }

  }

  // Choose(n, k) = n! / (k! * (n-k)!)
  public static long nCk(int n, int k) {

      if (n < k) {
          return 0;
      }

      long a = BigInteger.valueOf(factorials[k]).modInverse(MOD).longValue();
      long b = BigInteger.valueOf(factorials[n - k]).modInverse(MOD).longValue();

      // Left to right associativity between * and %
      return factorials[n] * a % mod * b % mod;

  }
  
  private static int p = (int) (Math.pow(10, 9) + 7);
  
  public static long binomMod(long n, long r) {
    long retVal = 1;
    
    //If n and r are less than p, then we just use the standard process
    if (n < p && r < p) {      
      //Computes the denominator
      for (long i = n; i > n-r; i--)
        retVal = (retVal * i) % p;

      //Now, compute the denominator
      for (long i = r; i > 0; i--)
        retVal = (retVal * modInverse(i, p)) % p;
      //Note: "denominator" is a figure of speech--
      //  really, I'm multiplying by the modular inverse
    }
    else { //We use Lucas' Theorem!
      long nCurr, rCurr; //The current digit of n and r in base p.
      while(r > 0 || n > 0) {
        //Grab the digits
        nCurr = n % p; 
        rCurr = r % p;
        
        //The return value is the product (mod p) of binom(nCurr, rCurr)
        retVal = (retVal * binomMod(nCurr, rCurr)) % p;
        
        //Strip off the digits we've just used
        n /= p; 
        r /= p;
      }
    }
    return retVal;
  }
  
  public static long modInverse(long n, int p) {    
    long q, r0, r1, r2, s0, s1, s2, t0, t1, t2;
    
    if (n > p) n %= p; //Save some time
    
    //Initial conditions
    r0 = p; r1 = n;
    s0 = 1; s1 = 0;
    t0 = 0; t1 = 1;
    
    //Now, we use the extended euclidean algorithm
    while(r1 > 0) { //r2 is the "current r," r1 is "one r ago," and r0 is "two r's ago"
      q = r0 / r1;
      r2 = r0 - r1*q;
      
      s2 = s0 - q*s1;
      t2 = t0 - q*t1;
      
      s0 = s1; s1 = s2;
      t0 = t1; t1 = t2;
      r0 = r1; r1 = r2;
    }
    
    if (t0 < 0) t0 += p; //Make positive!
    
    return t0; //Coefficient of n in the equation "xn+yp = 1"
  } 
}
