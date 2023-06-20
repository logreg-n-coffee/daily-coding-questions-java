import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * The Sieve of Eratosthenes is an algorithm used to generate all prime numbers
 * smaller than N. The method is to take increasingly larger prime numbers, and
 * mark their multiples as composite.
 * 
 * For example, to find all primes less than 100, we would first mark [4, 6, 8,
 * ...] (multiples of two), then [6, 9, 12, ...] (multiples of three), and so
 * on. Once we have done this for all primes less than N, the unmarked numbers
 * that remain will be prime.
 * 
 * Implement this algorithm.
 * 
 * Bonus: Create a generator that produces primes indefinitely (that is, without
 * taking N as an input).
 */

public class Question244 {
    public static void main(String[] args) {
        SieveOfEratosthenes.generatePrimes(100);
        System.out.println();
        PrimeGenerator primeGenerator = new PrimeGenerator();
        for (int i = 0; i < 100; i++) {
            System.out.println(primeGenerator.next());
        }
    }

    public static class SieveOfEratosthenes {
        public static void generatePrimes(int n) {
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);

            isPrime[0] = isPrime[1] = false;

            for (int i = 2; i * i <= n; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    System.out.println(i);
                }
            }
        }
    }

    public static class PrimeGenerator implements Iterator<Integer> {
        private List<Integer> primes = new ArrayList<>();
        private int current = 2;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            int nextPrime = current;
            while (!isPrime(nextPrime)) {
                nextPrime++;
            }
            primes.add(nextPrime);
            current = nextPrime + 1;
            return nextPrime;
        }

        private boolean isPrime(int num) {
            if (num <= 1)
                return false;
            for (Integer prime : primes) {
                if (prime * prime > num)
                    break; // optimization
                if (num % prime == 0)
                    return false;
            }
            return true;
        }
    }
}
