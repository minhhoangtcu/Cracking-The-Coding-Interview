package epi.stacksAndQueues;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. number
 * 2. a, b, operation
 *  
 * @author minhhoang
 *
 */
public class SQ92_RPN {

  private static Map<Character, Integer> variables = new HashMap<>();
  
  public static int evaluate(String expression) {
    String[] textElements = expression.split(" ");
    
    // Process the expression and put the data into an array of Element
    
    return 0;
  }
  
  private static int evaluate(Element[] elements) {
    
    // Go through the elements one by one. If we get an operand, push it into the stack
    // If we get an operator, pop 2 elements out of the stack, and push the result back in
    
    return 0;
  }
  
}

abstract class Element {
  
}

abstract class Operand extends Element {
  abstract public int getValue();
}

class Number extends Operand {
  private int value;
  
  public Number(int value) {
    this.value = value;
  }
  
  @Override
  public int getValue() {
    return value;
  }
}

class Variable extends Operand {
  private Map<Character, Integer> variables;
  private char var;
  
  public Variable(char var, Map<Character, Integer> variables) {
    this.var = var;
    this.variables = variables;
  }
  
  @Override
  public int getValue() {
    return variables.get(var);
  }
}

class Operation extends Element {
  enum Operators {
    PLUS, MINUS, MULTIPLY, DIVIDE
  }
}