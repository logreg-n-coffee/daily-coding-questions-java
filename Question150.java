/*
 * Given a list of points, a central point, and an integer k, find the nearest k
 * points from the central point.
 * 
 * For example, given the list of points [(0, 0), (5, 4), (3, 1)], the central
 * point (1, 2), and k = 2, return [(0, 0), (3, 1)].
 */

import java.util.*;

public class Question150 {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {5, 4}, {3, 1}};
        int[] center = {1, 2};
        int k = 2;

        List<int[]> result = nearestKPoints(points, center, k);

        System.out.println(Arrays.deepToString(result.toArray()));
    }

    // solve the question with priority queue - O(n log k) time and O(k) space
    public static List<int[]> nearestKPoints(int[][] points, int[] center, int k) {
        if (points.length <= k) {
            return Arrays.asList(points);
        }

        // instantiate a priority queue of point(s) and use lambda function to stipulate the order
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            double dist1 = getDistance(p1, center);
            double dist2 = getDistance(p2, center);

            return Double.compare(dist2, dist1);  // the smaller, the better
        });

        // go through the points list
        for (int[] point : points) {
            // if pq size is less than k, push (offer) the point to pq
            if (pq.size() < k) {
                pq.offer(point);
            } else {
                // if pq is full but there are more points, compare the new point with the head of the q
                double dist = getDistance(point, center);
                assert pq.peek() != null;
                if (dist < getDistance(pq.peek(), center)) {
                    pq.poll();  // retrieve and remove head of the q
                    pq.offer(point);  // offer the new point
                }
            }
        }

        // create a list to store the result and clear out the pq
        List<int[]> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }

    public static double getDistance(int[] p1, int[] p2) {
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];

        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}