/*
 * The skyline of a city is composed of several buildings of various widths and
 * heights, possibly overlapping one another when viewed from a distance. We can
 * represent the buildings using an array of (left, right, height) tuples, which
 * tell us where on an imaginary x-axis a building begins and ends, and how tall
 * it is. The skyline itself can be described by a list of (x, height) tuples,
 * giving the locations at which the height visible to a distant observer
 * changes, and each new height.
 * 
 * Given an array of buildings as described above, create a function that
 * returns the skyline.
 * 
 * For example, suppose the input consists of the buildings [(0, 15, 3), (4, 11,
 * 5), (19, 23, 4)]. In aggregate, these buildings would create a skyline that
 * looks like the one below.
 * 
 * .....______..
 * ....|......|........___
 * .___|......|___....|...|.
 * |...|...B..|...|...|.C.|
 * |.A.|......|.A.|...|...|
 * |...|......|...|...|...|
 * ------------------------
 * 
 * As a result, your function should return [(0, 3), (4, 5), (11, 3), (15, 0),
 * (19, 4), (23, 0)]
 */

import java.util.*;

class Question286 {
    /**
     * go through the building list, and for each building, when its left side
     * touches the ground, we push it into a priority queue. When its right side
     * touches the ground, we remove it from the heap. During this process, we keep
     * track of the current highest building in the heap and whenever it changes, we
     * add the change point and the new height into the result.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Create the array of buildings
        // Each building is represented by a triplet [left, right, height]
        int[][] buildings = {
                { 0, 15, 3 },
                { 4, 11, 5 },
                { 19, 23, 4 }
        };

        // Call the function and get the skyline
        List<List<Integer>> skyline = getSkyline(buildings);

        // Print the skyline
        for (List<Integer> point : skyline) {
            System.out.println(point.get(0) + ", " + point.get(1));
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> height = new ArrayList<>();

        for (int[] b : buildings) {
            // height: [position, +/-height]
            height.add(Arrays.asList(b[0], -b[2])); // Negative height indicates start of a building
            height.add(Arrays.asList(b[1], b[2])); // Positive height indicates end of a building
        }

        // We then sort the height list. We sort by the x position first, and then by
        // height.
        // For the same position, start point should come before end point.
        // If two buildings starts/ends at the same position, the taller one should
        // cover the shorter one.
        height.sort((a, b) -> {
            if (a.get(0) != b.get(0)) {
                // sort by position
                return a.get(0) - b.get(0);
            }
            // sort by height
            return a.get(1) - b.get(1);
        });

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a)); // max heap, storing the currently 'active' building
                                                                    // heights in descending order.
        pq.offer(0); // This serves to provide a ground level in the skyline

        int prev = 0; // This variable holds the height of the last building we added to the result

        for (List<Integer> h : height) {
            if (h.get(1) < 0) { // If the height is negative, it's a start of a buidlding
                pq.offer(-h.get(1)); // Add building height to max heap
            } else { // Otherwise, it's an end of a building
                pq.remove(h.get(1)); // Remove building height from heap
            }

            // Get the current highest building in the heap
            int curr = pq.peek();

            // If the current highest building is different from the last one in the result,
            // it means we've reached a "turning point" in the skyline
            if (prev != curr) {
                result.add(Arrays.asList(h.get(0), curr));
                prev = curr;
            }
        }

        return result;
    }
}