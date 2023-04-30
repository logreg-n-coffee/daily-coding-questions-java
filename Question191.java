/*
Given a collection of intervals, find the minimum number of intervals you need to
remove to make the rest of the intervals non-overlapping.

Intervals can "touch", such as [0, 1] and [1, 2], but they won't be considered overlapping.

For example, given the intervals (7, 9), (2, 4), (5, 8),
return 1 as the last interval can be removed and the first two won't overlap.

The intervals are not necessarily sorted in any order.
 */

import java.util.Arrays;
import java.util.Comparator;

public class Question191 {
    public static void main(String[] args) {
        int[][] intervals = {{7, 9}, {2, 4}, {5, 8}};
        System.out.println(minIntervalsToRemove(intervals));  // Output: 1
    }

    public static int minIntervalsToRemove(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // sort the intervals based on their end times
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));

        int count = 0;
        int prevEndTime = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEndTime) {
                // The current interval (interval[i][0]) doesn't overlap with the previous non-overlapping interval
                prevEndTime = intervals[i][1];
            } else {
                // The current interval overlaps, so remove it
                count++;
            }
        }

        return count;
    }
}
