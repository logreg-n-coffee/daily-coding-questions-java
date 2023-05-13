/*
Suppose you are given two lists of n points, one list p1, p2, ..., pn on the line y = 0 and the other list q1, q2, ..., qn on the line y = 1. 
Imagine a set of n line segments connecting each point pi to qi. Write an algorithm to determine how many pairs of the line segments intersect.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Question194 {
    // solve the question with sweep line algorithm
    public static void main(String[] args) {
        List<Double> p = List.of(0.0, 1.0, 3.0, 5.0);
        List<Double> q = List.of(4.0, 2.0, 6.0, 7.0);

        System.out.println("Number of intersecting pairs: " + countIntersectionsNSquare(p, q));
        System.out.println("Number of intersecting pairs: " + countIntersections(p, q));
    }

    // solve the question in O(n log n) time
    public static int countIntersections(List<Double> p, List<Double> q) {
        int n = p.size();
        List<Segment> segments = new ArrayList<>();

        // build segments
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(p.get(i), q.get(i)));
        }

        // sort segments
        segments.sort(Comparator.comparingDouble(seg -> seg.p));

        // organize the treeset - balanced binary search tree (BST)
        TreeSet<Segment> activeSegments = new TreeSet<>(Comparator.comparingDouble(seg -> seg.q));

        // find the intersections
        int intersections = 0;

        for (Segment segment : segments) {
            // find the subset of segments that could possibly intersect with the current
            // segment
            SortedSet<Segment> candidateIntersections = activeSegments.subSet(
                    new Segment(segment.p, segment.q - 1),
                    new Segment(segment.p, segment.q + 1));

            // count the intersections and remove segments that are no longer active
            for (Segment candidate : candidateIntersections) {
                if (candidate.q < segment.q) {
                    intersections++;
                }
                activeSegments.remove(candidate);
            }

            // add the current segment to the active segments
            activeSegments.add(segment);
        }

        return intersections;
    }

    // solve the question in O(n^2) time and O(n) space [needed to store the
    // segments]
    public static int countIntersectionsNSquare(List<Double> p, List<Double> q) {
        int n = p.size();
        List<Segment> segments = new ArrayList<>();

        // build segments
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(p.get(i), q.get(i)));
        }

        // sort segments - the segments are sorted by their p coordinates, so if segment
        // i has a higher q coordinate than segment j, they must cross each other.
        segments.sort(Comparator.comparingDouble(seg -> seg.p));

        // loop through segments to caculate intersections
        int intersections = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((segments.get(i).q > segments.get(j).q) != (segments.get(i).p > segments.get(j).p)) {
                    intersections++;
                }
            }
        }

        return intersections;
    }

    public static class Segment {
        Double p, q;

        Segment(Double p, Double q) {
            this.p = p;
            this.q = q;
        }
    }
}
