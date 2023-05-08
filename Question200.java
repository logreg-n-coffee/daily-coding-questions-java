/*
 * Let X be a set of n intervals on the real line. We say that a set of points P "stabs" X if every interval in X contains at least one point in P. 
 * Compute the smallest set of points that stabs X.
 * 
 * For example, given the intervals [(1, 4), (4, 5), (7, 9), (9, 12)], you should return [4, 9].
 * 
 * A point "stabs" an interval if the point is contained in the interval. An interval is defined by two endpoints, say start and end. 
 * A point p stabs an interval if start <= p <= end. 
 * If a set of points P stabs a set of intervals X, then for each interval in X, there is at least one point in P that stabs the interval.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Question200 {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[] {
                new Interval(1, 4),
                new Interval(4, 5),
                new Interval(7, 9),
                new Interval(9, 12)
        };

        Set<Integer> stabbingPoints = computeMinimumStabbingPoints(intervals);
        System.out.println("Minimum stabbing points: " + stabbingPoints);
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Set<Integer> computeMinimumStabbingPoints(Interval[] intervals) {
        // Sort intervals by end point
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));

        Set<Integer> stabbingPoints = new HashSet<>();

        for (Interval interval : intervals) {
            boolean stabbed = false;

            // check if interval is stabbed by any point in stabbingPoints
            for (int point : stabbingPoints) {
                if (point >= interval.start && point <= interval.end) {
                    stabbed = true;
                    break;
                }
            }

            // if interval is not stabbed, add its end point to stabbingPoints
            if (!stabbed) {
                stabbingPoints.add(interval.end);
            }
        }

        return stabbingPoints;
    }
}
