
/**
 * Blackjack is a two player card game whose rules are as follows:
 * 
 * The player and then the dealer are each given two cards.
 * 
 * The player can then "hit", or ask for arbitrarily many additional cards, so
 * long as their total does not exceed 21.
 * 
 * The dealer must then hit if their total is 16 or lower, otherwise pass.
 * Finally, the two compare totals, and the one with the greatest sum not
 * exceeding 21 is the winner.
 * 
 * For this problem, cards values are counted as follows: each card between 2
 * and 10 counts as their face value, face cards count as 10, and aces count as
 * 1.
 * 
 * Given perfect knowledge of the sequence of cards in the deck, implement a
 * blackjack solver that maximizes the player's score (that is, wins minus
 * losses).
 */

import java.util.*;

public class Question238 {
    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        int bet = 10;
        int numGames = 1000000;
        int totalWinnings = 0;
        for (int i = 0; i < numGames; i++) {
            totalWinnings += blackJack.playGame(bet);
        }
        System.out.println("Total winnings: " + totalWinnings);
        System.out.println("Percent win: " + (double) totalWinnings / (numGames * bet));
    }

    static class BlackJack {
        private Queue<Integer> deck;
        private Queue<Integer> playerHand;
        private Queue<Integer> dealerHand;

        public BlackJack() {
            deck = new LinkedList<>();
            playerHand = new LinkedList<>();
            dealerHand = new LinkedList<>();
            resetDeck();
        }

        // reset the deck
        private void resetDeck() {
            deck.clear();
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j <= 10; j++) {
                    deck.add(j);
                }
                deck.add(10); // J == 10
                deck.add(10); // Q == 10
                deck.add(10); // K == 10
                deck.add(1); // A == 1
            }

            // Collections.shuffle() expects a List, so we cast deck to List<?>
            Collections.shuffle((List<?>) deck);
        }

        // get the value of a hand
        private int handValue(Queue<Integer> hand) {
            int value = 0;
            for (int card : hand) {
                value += card;
            }
            return value;
        }

        // deal one card from the deck to a hand
        private void dealCard(Queue<Integer> hand) {
            hand.add(deck.poll());
        }

        public int playGame(int bet) {
            // reset deck and hands
            resetDeck();
            playerHand.clear();
            dealerHand.clear();

            // initial dealing
            dealCard(playerHand);
            dealCard(dealerHand);
            dealCard(playerHand);
            dealCard(dealerHand);

            // player's turn
            while (handValue(playerHand) < 21) {
                dealCard(playerHand);
            }

            while (handValue(dealerHand) <= 16) {
                dealCard(dealerHand);
            }

            // calculate the scores
            int playerScore = handValue(playerHand);
            int dealerScore = handValue(dealerHand);

            // Determine the winner
            if (playerScore > 21) {
                if (dealerScore > 21) {
                    return 0; // Tie - both busted
                } else {
                    return -bet; // Player busted, dealer wins
                }
            } else {
                if (dealerScore > 21) {
                    return bet; // Dealer busted, player wins
                } else {
                    // Compare scores if neither player nor dealer busted
                    if (playerScore > dealerScore) {
                        return bet; // Player wins
                    } else if (dealerScore > playerScore) {
                        return -bet; // Dealer wins
                    } else {
                        return 0; // Tie
                    }
                }
            }
        }
    }
}
