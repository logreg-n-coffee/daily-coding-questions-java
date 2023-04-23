/*
You are given a starting state start, a list of transition probabilities for a Markov chain,
and a number of steps num_steps. Run the Markov chain starting from start for num_steps and
compute the number of times we visited each state.

For example, given the starting state a, number of steps 5000, and the following transition probabilities:

[
  ('a', 'a', 0.9),
  ('a', 'b', 0.075),
  ('a', 'c', 0.025),
  ('b', 'a', 0.15),
  ('b', 'b', 0.8),
  ('b', 'c', 0.05),
  ('c', 'a', 0.25),
  ('c', 'b', 0.25),
  ('c', 'c', 0.5)
]
One instance of running this Markov chain might produce { 'a': 3012, 'b': 1656, 'c': 332 }.

P = | 0.9   0.075  0.025 |
    | 0.15  0.8    0.05  |
    | 0.25  0.25   0.5   |

This matrix represents the probabilities of transitioning between the states a, b, and c.
For example, P(a, b) = 0.075, which is the probability of transitioning from state a to state b.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Question175 {
    public static void main(String[] args) {
        char start = 'a';
        int num_steps = 5000;
        String[][] transitions = {
                {"a", "a", "0.9"},
                {"a", "b", "0.075"},
                {"a", "c", "0.025"},
                {"b", "a", "0.15"},
                {"b", "b", "0.8"},
                {"b", "c", "0.05"},
                {"c", "a", "0.25"},
                {"c", "b", "0.25"},
                {"c", "c", "0.5"}
        };

        Map<Character, Integer> stateCounts = runMarkovChain(start, num_steps, transitions);
        System.out.println(stateCounts);
    }

    // Time Complexity: O(numSteps * T). the number of transitions as T
    // Space Complexity: If there are S unique states, the space complexity would be O(S) for the stateCounts HashMap.
    // The space complexity for the input transitions array is O(T).
    // The total space complexity of the algorithm is O(S + T).
    public static Map<Character, Integer> runMarkovChain(char start, int numSteps, String[][] transitions) {
        Map<Character, Integer> stateCounts = new HashMap<>();
        char currentState = start;
        Random random = new Random();

        for (int i = 0; i < numSteps; i++) {
            stateCounts.put(currentState, stateCounts.getOrDefault(currentState, 0) + 1);

            double randomValue = random.nextDouble();
            double cumulativeProbability = 0.0;

            for (String[] transition : transitions) {
                char fromState = transition[0].charAt(0);
                char toState = transition[1].charAt(0);
                double probability = Double.parseDouble(transition[2]);

                if (currentState == fromState) {
                    cumulativeProbability += probability;

                    if (randomValue <= cumulativeProbability) {
                        currentState = toState;
                        break;
                    }
                }
            }
        }

        return stateCounts;
    }
}
