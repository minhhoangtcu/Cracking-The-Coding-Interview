package hackerrank.contest.worldCodeSpirit;

/*
 * PROBLEM HERE: https://www.hackerrank.com/contests/stryker-codesprint/challenges/point-filtering
 */

import java.util.*;
import java.math.*;

public class PointFiltering {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        PointFiltering pf = new PointFiltering();
        
        int n = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine(); // read the \n of last line
        
        // populate list of points
        LinkedList<Point> points = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            points.add(pf.new Point(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
        }
        
        // sort the list
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                float dif = p1.z.compareTo(p2.z); // we do this instead of just returning dif because 0.1 means 0 in int
                if (dif < 0)
                    return 1; // descending order
                else if (dif > 0)
                    return -1;
                else
                    return 0;
            }
        });
        
        // populate the bucket
        HashMap<Integer, Point> bucket = new HashMap<>();
        for (int i = 0; i < b; i++) {
            Point p = points.remove();
            bucket.put(p.id, p);
        }
        
        // execute unknown queries
        while (sc.hasNextLine()) {
            String query = sc.nextLine();
            char function = query.charAt(0);
            int id = Integer.parseInt(query.substring(2));
            switch (function) {
                case 'f':
                case 'F':
                    if (bucket.containsKey(id)) {
                        Point p = bucket.get(id);
                        //System.out.printf("%d = (%.3f,%.3f,%.3f)\n", id, p.x, p.y, p.z);
                        System.out.println(p); // printf costs a lot of time, so avoid it
                    } else {
                        System.out.println("Point doesn't exist in the bucket.");
                    }
                    break;
                case 'r':
                case 'R':
                    if (bucket.containsKey(id)) {
                        if (points.size() == 0) {
                            System.out.println("No more points can be deleted.");
                        } else {
                            bucket.remove(id);
                            Point p = points.remove();
                            bucket.put(p.id, p); 
                            // System.out.printf("Point id %d removed.\n", id);
                            System.out.println("Point id " + id + " removed.");
                        }
                    } else {
                        System.out.println("Point doesn't exist in the bucket.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown query");
            }
        }
        
        sc.close();
    }
    
    class Point {
        int id;
        BigDecimal x, y, z;
        public Point(String id, String x, String y, String z) {
            this.id = Integer.parseInt(id);
            this.x = new BigDecimal(x);
            this.y = new BigDecimal(y);
            this.z = new BigDecimal(z);
        }
        public String toString() {
            return id + " = (" + x.setScale(3, BigDecimal.ROUND_HALF_UP) + "," + y.setScale(3, BigDecimal.ROUND_HALF_UP) + ","  + z.setScale(3, BigDecimal.ROUND_HALF_UP) + ")";
        }
    }
}
