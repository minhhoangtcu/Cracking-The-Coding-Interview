package epi.strings;

/**
 * Write a program which takes as input a valid Roman number string s and returns the integer it
 * corresponds to
 * 
 * @author minhhoang
 *
 */
public class S79_RomanToDecimal {
  
  public static void main(String[] args) {
    assert(getValueFromSymbols("I") == 1);
    assert(getValueFromSymbols("II") == 2);
    assert(getValueFromSymbols("III") == 3);
    assert(getValueFromSymbols("IV") == 4);
    assert(getValueFromSymbols("V") == 5);
    assert(getValueFromSymbols("VI") == 6);
    assert(getValueFromSymbols("VII") == 7);
    assert(getValueFromSymbols("VIII") == 8);
    assert(getValueFromSymbols("IX") == 9);
    assert(getValueFromSymbols("X") == 10);
    assert(getValueFromSymbols("XVI") == 16);
    assert(getValueFromSymbols("XVIII") == 18);
    assert(getValueFromSymbols("XXIV") == 24);
  }

  public static int getValueFromSymbols(String symbols) {
    int value = 0;

    /*
     * Go backward from right to back, considering 2 elements at i and i+1 in each iteration.
     * Because a large number can have multiple smaller numbers on its right (special case), we can
     * always increase our return value by the small number. However, the large number can only have
     * 1 smaller number on its left, as soon as we find this case, we decrease the return value by
     * the smaller number.
     */
    int lastSymbolValue = Converter.getValue(symbols.charAt(symbols.length() - 1));
    value += lastSymbolValue;
    
    for (int i = symbols.length() - 2; i >= 0; i--) {
      int currentSymbolValue = Converter.getValue(symbols.charAt(i));
      if (currentSymbolValue < lastSymbolValue) {
        value -= currentSymbolValue;
      } else {
        value += currentSymbolValue;
      }
      lastSymbolValue = currentSymbolValue;
    }

    return value;
  }

}


class Converter {
  private final static String REPRESENTATIONS = "IVXLCDM";
  private final static int[] SYMBOL_VALUES = {1, 5, 10, 50, 100, 500, 1000};

  public static int getValue(char symbol) {
    int index = REPRESENTATIONS.indexOf(symbol);

    if (index == -1) {
      throw new IllegalArgumentException("Invalid symbol.");
    }

    return SYMBOL_VALUES[index];
  }
}
