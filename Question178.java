/*
Alice wants to join her school's Probability Student Club.
Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly.
Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter?
Write a program to simulate the two games and calculate their expected value.
 */

import java.util.Random;

public class Question178 {
    public static void main(String[] args) {
        int numSimulations = 1000000;
        double expectedValueGame1 = simulateGame(numSimulations, 6);
        double expectedValueGame2 = simulateGame(numSimulations, 5);

        System.out.printf("Expected value for game 1 (5 then 6): %.2f%n", expectedValueGame1);
        System.out.printf("Expected value for game 1 (5 then 5): %.2f%n", expectedValueGame2);

        if (expectedValueGame1 < expectedValueGame2) {
            System.out.println("Alice should choose game 1.");
        } else if (expectedValueGame1 > expectedValueGame2) {
            System.out.println("Alice should choose game 2.");
        } else {
            System.out.println("Both games have the same expected value. Alice can choose either.");
        }

    }

    // time complexity is O(numSimulations) - space complexity is O(1)
    private static double simulateGame(int numSimulations, int nextTarget) {
        int totalRolls = 0;
        Random random = new Random();

        for (int i = 0; i < numSimulations; i++) {
            int prevRoll = -1;
            int currRoll;
            int rolls = 0;

            while (true) {
                currRoll = random.nextInt(6) + 1;  // 1,2,3,4,5,6
                rolls++;

                if (prevRoll == 5 && currRoll == nextTarget) {
                    break;
                }

                prevRoll = currRoll;
            }
            totalRolls += rolls;
        }

        return (double) totalRolls / numSimulations;
    }
}
