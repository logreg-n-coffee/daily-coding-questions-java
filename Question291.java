
/**
 * An imminent hurricane threatens the coastal town of Codeville. If at most two
 * people can fit in a rescue boat, and the maximum weight limit for a given
 * boat is k, determine how many boats will be needed to save everyone.
 * 
 * For example, given a population with weights [100, 200, 150, 80] and a boat
 * limit of 200, the smallest number of boats required will be three.
 */

import java.util.Arrays;

class Question291 {
    // solve the problem with 2-pointer technique
    public static void main(String[] args) {
        int[] people = { 100, 200, 150, 80 };
        int limit = 200;
        System.out.println(numBoats(people, limit));
    }

    public static int numBoats(int[] people, int limit) {
        int boats = 0;
        int left = 0;
        int right = people.length - 1;
        Arrays.sort(people);

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boats++;
        }

        return boats;
    }
}