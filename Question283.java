
/**
 * A regular number in mathematics is defined as one which evenly divides some
 * power of 60. Equivalently, we can say that a regular number is one whose only
 * prime divisors are 2, 3, and 5.
 * 
 * These numbers have had many applications, from helping ancient Babylonians
 * keep time to tuning instruments according to the diatonic scale.
 * 
 * Given an integer N, write a program that returns, in order, the first N
 * regular numbers.
 * 
 * 
 * A simpler way to understand this is that a regular number is a number whose
 * prime factors are only 2, 3, or 5. For example, the number 6 is a regular
 * number because its prime factors are 2 and 3. However, the number 7 is not a
 * regular number because its only prime factor is 7.
 */

import java.util.*;

/**
 * Time Complexity:
 * 
 * The program runs a loop for n iterations. Within each iteration, it performs
 * operations that are in constant time such as poll operation from a
 * PriorityQueue (O(logn)), adding elements to a PriorityQueue (O(logn)), and
 * checking and adding elements in a HashSet (O(1)). So, the time complexity
 * would be O(nlogn).
 * 
 * Space Complexity:
 * 
 * The PriorityQueue and HashSet used in the program store at most 3n elements,
 * because in each of n iterations, up to 3 new numbers can be added to the
 * queue and set. Therefore, the space complexity is O(n).
 * So, the overall complexity of the program is O(nlogn) time complexity and
 * O(n) space complexity.
 */
public class Question283 {
    public static void main(String[] args) {
        int n = 10;
        List<Long> regularNumbers = findRegularNums(n);
        System.out.println("The first " + n + " regular numbers are: " + regularNumbers);
    }

    public static List<Long> findRegularNums(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        // init
        Long[] primes = { 2L, 3L, 5L };
        for (Long prime : primes) {
            pq.add(prime);
            set.add(prime);
        }

        Long currLong = 1L;
        for (int i = 1; i < n; i++) {
            currLong = pq.poll();
            for (Long prime : primes) {
                Long newLong = currLong * prime;
                if (!set.contains(newLong)) {
                    pq.add(newLong);
                    set.add(newLong);
                }
            }
        }

        List<Long> regularNums = new ArrayList<>(set);
        regularNums.sort(Comparator.naturalOrder());

        return regularNums;
    }
}