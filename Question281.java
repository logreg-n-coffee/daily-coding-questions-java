
/**
 * A wall consists of several rows of bricks of various integer lengths and
 * uniform height. Your goal is to find a vertical line going from the top to
 * the bottom of the wall that cuts through the fewest number of bricks. If the
 * line goes through the edge between two bricks, this does not count as a cut.
 * 
 * For example, suppose the input is as follows, where values in each row
 * represent the lengths of bricks in that row:
 * 
 * [[3, 5, 1, 1],
 * [2, 3, 3, 2],
 * [5, 5],
 * [4, 4, 2],
 * [1, 3, 3, 3],
 * [1, 1, 6, 1, 1]]
 * 
 * The best we can we do here is to draw a line after the eighth brick, which
 * will only require cutting through the bricks in the third and fifth row.
 * 
 * Given an input consisting of brick lengths for each row such as the one
 * above, return the fewest number of bricks that must be cut to create a
 * vertical line.
 * 
 * We want to find a vertical line (edge between bricks) which crosses the least number of bricks. 
 * 
 * Row 1:  |3--|8-------|9-|10|
 * Row 2:  |2--|5-------|8-|10|
 * Row 3:  |5--|10------|  X (cut needed)
 * Row 4:  |4--|8-------|10|
 * Row 5:  |1--|4-------|7-|10| X (cut needed)
 * Row 6:  |1--|2-------|8-|9-|10|
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Question281 {
    public static void main(String[] args) {
        Question281 solution = new Question281();

        // Create a list to store our wall
        List<List<Integer>> wall = new ArrayList<>();

        // Add rows to our wall
        wall.add(Arrays.asList(3, 5, 1, 1));
        wall.add(Arrays.asList(2, 3, 3, 2));
        wall.add(Arrays.asList(5, 5));
        wall.add(Arrays.asList(4, 4, 2));
        wall.add(Arrays.asList(1, 3, 3, 3));
        wall.add(Arrays.asList(1, 1, 6, 1, 1));

        // Find the least number of bricks to be cut and print the result
        int result = solution.leastBricks(wall);
        System.out.println("The fewest number of bricks that must be cut to create a vertical line is: " + result);
    }

    // find common cumulative length
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }

        int count = 0;
        // map to keep track of the counts of each ending point
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                // exclude the last brick - a vertical line at the end of the wall does not
                // require any cuts
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                count = Math.max(count, map.get(sum));
            }
        }

        return wall.size() - count; // subtract from total rows to get the count of bricks that have to be cut
    }
}
