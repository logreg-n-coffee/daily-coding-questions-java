
/**
 * Snakes and Ladders is a game played on a 10 x 10 board, the goal of which is
 * get from square 1 to square 100. On each turn players will roll a six-sided
 * die and move forward a number of spaces equal to the result. If they land on
 * a square that represents a snake or ladder, they will be transported ahead or
 * behind, respectively, to a new square.
 * 
 * Find the smallest number of turns it takes to play snakes and ladders.
 * 
 * For convenience, here are the squares representing snakes and ladders, and
 * their outcomes:
 * 
 * snakes = {16: 6, 48: 26, 49: 11, 56: 53, 62: 19, 64: 60, 87: 24, 93: 73, 95:
 * 75, 98: 78}
 * ladders = {1: 38, 4: 14, 9: 31, 21: 42, 28: 84, 36: 44, 51: 67, 71: 91, 80:
 * 100}
 * 
 * Intuition: smallest number of turns => shortest path from 1 to 100 => use bfs
 */

import java.util.*;

public class Question229 {
    static class Square {
        int pos;
        int dist; // minimum distance from 1 (start) to this square

        Square(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        System.out.println("Minimum turns to reach the goal: " + findSmallestNumOfTurns());
    }

    public static int findSmallestNumOfTurns() {
        // initialize variables
        int N = 100;
        Map<Integer, Integer> snakesAndLadders = new HashMap<>();

        snakesAndLadders.put(16, 6);
        snakesAndLadders.put(48, 26);
        snakesAndLadders.put(49, 11);
        snakesAndLadders.put(56, 53);
        snakesAndLadders.put(62, 19);
        snakesAndLadders.put(64, 60);
        snakesAndLadders.put(87, 24);
        snakesAndLadders.put(93, 73);
        snakesAndLadders.put(95, 75);
        snakesAndLadders.put(98, 78);
        snakesAndLadders.put(1, 38);
        snakesAndLadders.put(4, 14);
        snakesAndLadders.put(9, 31);
        snakesAndLadders.put(21, 42);
        snakesAndLadders.put(28, 84);
        snakesAndLadders.put(36, 44);
        snakesAndLadders.put(51, 67);
        snakesAndLadders.put(71, 91);
        snakesAndLadders.put(80, 100);

        // visited array to keep track of visited squares
        boolean[] visited = new boolean[N + 1];

        // queue for bfs
        Queue<Square> queue = new LinkedList<>();

        // start at 1
        visited[1] = true;
        queue.add(new Square(1, 0));

        // bfs
        Square square = null;
        while (!queue.isEmpty()) {
            square = queue.poll();
            int pos = square.pos;

            // if we reach 100, we're done
            if (pos == N) {
                break;
            }

            // otherwise, add all possible moves to queue
            for (int i = pos + 1; i <= (pos + 6) && i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    Square next = new Square(i, square.dist + 1);

                    // if we land on a snake or ladder, add the destination to queue
                    if (snakesAndLadders.containsKey(i)) {
                        next.pos = snakesAndLadders.get(i);
                    }

                    queue.add(next);
                }
            }
        }

        return square.dist;
    }
}
