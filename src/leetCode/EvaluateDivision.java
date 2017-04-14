package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

  public static void main(String[] args) {
    EvaluateDivision ed = new EvaluateDivision();

    String[][] equations = {{"a", "b"}, {"b", "c"}};
    double[] values = {2.0, 3.0};
    String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

//    for (double queryResult : ed.calcEquation(equations, values, queries)) {
//      System.out.println(queryResult);
//    }
    
    String[][] equations2 = {{"x1","x2"}, {"x2","x3"},{"x3","x4"},{"x4","x5"}};
    double[] values2 = {3.0,4.0,5.0,6.0};
    String[][] queries2 = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
    
//    for (double queryResult : ed.calcEquation(equations2, values2, queries2)) {
//      System.out.println(queryResult);
//    }
    
    String[][] equations3 = {{"a","b"},{"c","d"},{"e","f"},{"g","h"}};
    double[] values3 = {4.5,2.3,8.9,0.44};
    String[][] queries3 = {{"a","c"},{"d","f"},{"h","e"},{"b","e"},{"d","h"},{"g","f"},{"c","g"}};
    
    for (double queryResult : ed.calcEquation(equations3, values3, queries3)) {
      System.out.println(queryResult);
    }
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    EvaluateGraph graph = new EvaluateGraph();
    graph.buildGraph(equations, values);
    double[] result = new double[queries.length];

    for (int i = 0; i < result.length; i++) {
      result[i] = graph.query(queries[i]);
    }

    return result;
  }


  private class EvaluateGraph {

    Map<String, Map<String, Edge>> edges = new HashMap<>();

    void buildGraph(String[][] equations, double[] values) {
      // Build the graph
      for (int i = 0; i < equations.length; i++) {
        String vertex1 = equations[i][0];
        String vertex2 = equations[i][1];

        Map<String, Edge> toEdgesFrom1 = edges.getOrDefault(vertex1, new HashMap<String, Edge>());
        toEdgesFrom1.put(vertex2, new Edge(values[i], 1.0));
        edges.put(vertex1, toEdgesFrom1);

        Map<String, Edge> toEdgesFrom2 = edges.getOrDefault(vertex2, new HashMap<String, Edge>());
        toEdgesFrom2.put(vertex1, new Edge(1.0, values[i]));
        edges.put(vertex2, toEdgesFrom2);
      }
    }

    double query(String[] query) {
      String vertex1 = query[0];
      String vertex2 = query[1];
      
      // No edges coming out of these two vertices -> cannot compute
      if (!edges.containsKey(vertex1) || !edges.containsKey(vertex2)) {
        return -1.0;
      }
      
      if (vertex1.equals(vertex2)) {
        return 1;
      }

      if (edges.get(vertex1).containsKey(vertex2)) {
        return edges.get(vertex1).get(vertex2).getValue();
      } else {
        // Need to find a path from the first vertex to the second vertex
        // We can do a depth first search until found vertex 2
        Set<String> visitedVertices = new HashSet<>();
        visitedVertices.add(vertex1);
        
        double result = findVertex(vertex1, vertex2, 1, visitedVertices);
        
        if (result < 0) {
          return -1;
        }
        
        // DP
        Map<String, Edge> fromVertex1 = edges.getOrDefault(vertex1, new HashMap<String, Edge>());
        fromVertex1.put(vertex2, new Edge(result, 1.0));
        
        Map<String, Edge> fromVertex2 = edges.getOrDefault(vertex2, new HashMap<String, Edge>());
        fromVertex2.put(vertex1, new Edge(1.0, result));
        
        return result;
      }
    }

    // should only be called by query
    private double findVertex(String startingVertex, String endingVertex, double currentProd,
        Set<String> visitedVertices) {
      if (edges.get(startingVertex).containsKey(endingVertex)) {
        return currentProd * edges.get(startingVertex).get(endingVertex).getValue();
      }

      for (String target : edges.get(startingVertex).keySet()) {
        if (visitedVertices.contains(target)) {
          continue;
        }
        
        visitedVertices.add(target);
        double prod = findVertex(target, endingVertex,
            currentProd * edges.get(startingVertex).get(target).getValue(), visitedVertices);
        
        if (prod >= 0) {
          return prod;
        }
      }

      return -1; // cannot find it
    }
  }

  // Result of a division from vertice to vertice
  private class Edge {
    double numerator, denominator;

    Edge(double numerator, double denominator) {
      this.numerator = numerator;
      this.denominator = denominator;
    }

    double getValue() {
      return numerator / denominator;
    }
  }
}
