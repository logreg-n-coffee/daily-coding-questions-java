/**
 * You have N stones in a row, and would like to create from them a pyramid.
 * This pyramid should be constructed such that the height of each stone
 * increases by one until reaching the tallest stone, after which the heights
 * decrease by one. In addition, the start and end stones of the pyramid should
 * each be one stone high.
 * 
 * You can change the height of any stone by paying a cost of 1 unit to lower
 * its height by 1, as many times as necessary. Given this information,
 * determine the lowest cost method to produce this pyramid.
 * 
 * For example, given the stones [1, 1, 3, 3, 2, 1], the optimal solution is to
 * pay 2 to create [0, 1, 2, 3, 2, 1].
 * 
 * [1, 1, 3, 3, 2, 1]
 * -1....-1..........
 * [0, 1, 2, 3, 2, 1]
 */
public class Question293 {
    public static void main(String[] args) {
        int[] stones = { 1, 1, 3, 3, 2, 1 };
        System.out.println(pyramidCost(stones)); // Expected output: 2
    }

    public static int pyramidCost(int[] stones) {
        int n = stones.length;
        int cost = 0;

        // Find the index of the tallest stone
        int peakIndex = 0;
        for (int i = 1; i < n; i++) {
            if (stones[i] > stones[peakIndex]) {
                peakIndex = i;
            } else if (stones[i] == stones[peakIndex] && Math.abs(n / 2 - i) < Math.abs(n / 2 - peakIndex)) {
                // If heights are equal, choose the one closer to center
                peakIndex = i;
            }
        }

        // Adjust stones to the left of the peak
        int expectedHeight = stones[peakIndex] - 1;
        for (int i = peakIndex - 1; i >= 0; i--) {
            if (stones[i] > expectedHeight) {
                cost += stones[i] - expectedHeight;
                stones[i] = expectedHeight;
            }
            expectedHeight--;
        }

        // Adjust stones to the right of the peak
        expectedHeight = stones[peakIndex] - 1;
        for (int i = peakIndex + 1; i < n; i++) {
            if (stones[i] > expectedHeight) {
                cost += stones[i] - expectedHeight;
                stones[i] = expectedHeight;
            }
            expectedHeight--;
        }

        return cost;
    }
}
