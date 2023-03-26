/*
You are given n numbers as well as n probabilities that sum up to 1.
Write a function to generate one of the numbers with its corresponding probability.

For example, given the numbers [1, 2, 3, 4] and probabilities [0.1, 0.5, 0.2, 0.2],
your function should return 1 10% of the time, 2 50% of the time, and 3 and 4 20% of the time.

You can generate random numbers between 0 and 1 uniformly.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Question152 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        double[] probs = {.1, .5, .2, .2};
        double sizeOfPoll = 1000;

        ArrayList<Integer> poll = new ArrayList<>();
        for (int i = 0; i < sizeOfPoll; i++) {
            poll.add(distribute(nums, probs));
        }

        ArrayList<Integer> filteredOnes = filter(poll, 1);
        ArrayList<Integer> filteredTwos = filter(poll, 2);
        ArrayList<Integer> filteredThrees = filter(poll, 3);
        ArrayList<Integer> filteredFours = filter(poll, 4);

        System.out.println(filteredOnes.size() / sizeOfPoll);
        System.out.println(filteredTwos.size() / sizeOfPoll);
        System.out.println(filteredThrees.size() / sizeOfPoll);
        System.out.println(filteredFours.size() / sizeOfPoll);

        System.out.println();

    }

    // O(n) time and O(1) space
    public static int distribute(int[] nums, double[] probs) {
        Random random = new Random();
        double r = random.nextDouble();

        double s = 0;
        for (int i = 0; i < nums.length; i++) {
            s += probs[i];
            if (s >= r) {
                return nums[i];
            }
        }
        // return -1 when no value is returned - unlikely but possible due to floating-point rounding errors
        return -1;
    }

    public static ArrayList<Integer> filter(ArrayList<Integer> source, int number) {
        return source.stream()
                .filter(n -> n == number)
                .collect(Collectors.toCollection(ArrayList::new));  // ArrayList::new equals to () -> new ArrayList<>()
    }
}