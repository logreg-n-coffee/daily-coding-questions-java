
/**
 * Connect 4 is a game where opponents take turns dropping red or black discs into a 7 x 6 
 * vertically suspended grid. The game ends either when one player creates a line of four 
 * consecutive discs of their color (horizontally, vertically, or diagonally), or when there
 * are no more spots left in the grid.
 * 
 * Design and implement Connect 4.
 */

import java.util.Random;

public class Question219 {
    private final int rows = 6;
    private final int columns = 7;
    private char[][] board;
    private char currentPlayer;

    // constructor
    public Question219() {
        board = new char[rows][columns];
        currentPlayer = 'R';
        initializeBoard();
    }

    // main method
    public static void main(String[] args) {
        Question219 game = new Question219();
        Random rand = new Random();

        for (int turn = 0; turn < game.rows * game.columns; turn++) {
            int column;
            do {
                column = rand.nextInt(game.columns);
            } while (!game.dropDisc(column));

            if (game.checkWin()) {
                System.out.println(game.currentPlayer + " wins!");
                break;
            }

            if (turn == game.rows * game.columns - 1) {
                System.out.println("The game is a tie!");
            }

            System.out.println("Turn: " + (turn + 1) + ", dropped disc in column: " + column);
            game.printBoard();
        }

    }

    public void initializeBoard() {
        // initialize board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    public boolean dropDisc(int column) {
        if (column < 0 || column >= columns) {
            System.out.println("Invalid column");
            return false;
        }

        // check if the column is full
        if (board[0][column] != '-') {
            System.out.println("Column is full");
            return false;
        }

        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == '-') {
                board[i][column] = currentPlayer;
                currentPlayer = currentPlayer == 'R' ? 'B' : 'R';
                return true;
            }
        }

        return false;
    }

    private boolean checkWin() {
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private boolean checkHorizontalWin() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == currentPlayer && board[row][col + 1] == currentPlayer
                        && board[row][col + 2] == currentPlayer && board[row][col + 3] == currentPlayer)
                    return true;
            }
        }

        return false;
    }

    private boolean checkVerticalWin() {
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns; col++) {
                if (board[row][col] == currentPlayer && board[row + 1][col] == currentPlayer
                        && board[row + 2][col] == currentPlayer && board[row + 3][col] == currentPlayer)
                    return true;
            }
        }

        return false;
    }

    private boolean checkDiagonalWin() {
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == currentPlayer && board[row - 1][col + 1] == currentPlayer
                        && board[row - 2][col + 2] == currentPlayer && board[row - 3][col + 3] == currentPlayer)
                    return true;
            }
        }

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == currentPlayer && board[row + 1][col + 1] == currentPlayer
                        && board[row + 2][col + 2] == currentPlayer && board[row + 3][col + 3] == currentPlayer)
                    return true;
            }
        }

        return false;
    }
}
