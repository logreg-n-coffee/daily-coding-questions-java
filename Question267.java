/**
 * You are presented with an 8 by 8 matrix representing the positions of pieces
 * on a chess board. The only pieces on the board are the black king and various
 * white pieces. Given this matrix, determine whether the king is in check.
 * 
 * For details on how each piece moves, see here.
 * 
 * For example, given the following matrix:
 * 
 * ...K....
 * ........
 * .B......
 * ......P.
 * .......R
 * ..N.....
 * ........
 * .....Q..
 * You should return True, since the bishop is attacking the king diagonally.
 */

public class Question267 {
    public static void main(String[] args) {
        char[][] board = {
                { '.', '.', '.', 'K', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', 'B', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', 'P', '.' },
                { '.', '.', '.', '.', '.', '.', '.', 'R' },
                { '.', '.', 'N', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', 'Q', '.', '.' }
        };

        System.out.println(isKingInCheck(board)); // Expected output: true
    }

    public static boolean isKingInCheck(char[][] board) {
        // king position
        int kingRow = -1;
        int kingCol = -1;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 'K') {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            if (kingRow != -1) {
                break;
            }
        }

        // check for pawns
        if (kingRow > 0 && kingCol > 0 && board[kingRow - 1][kingCol - 1] == 'P') {
            return true;
        }
        if (kingRow > 0 && kingCol < 7 && board[kingRow - 1][kingCol + 1] == 'P') {
            return true;
        }

        // check for knights
        int[][] knightMoves = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 },
                { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };
        for (int[] move : knightMoves) {
            int newRow = kingRow + move[0];
            int newCol = kingCol + move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && board[newRow][newCol] == 'N') {
                return true;
            }
        }

        // check for rooks, bishops and queens
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
                { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

        for (int[] direction : directions) {
            int row = kingRow;
            int col = kingCol;

            while (true) {
                row += direction[0];
                col += direction[1];

                if (row < 0 || row >= 8 || col < 0 || col >= 8) {
                    break;
                }

                if (board[row][col] == 'Q' ||
                        (board[row][col] == 'R' && (direction[0] == 0 || direction[1] == 0)) ||
                        (board[row][col] == 'B' && direction[0] != 0 && direction[1] != 0)) {
                    return true;
                }

                if (board[row][col] != '.') {
                    break;
                }
            }
        }

        return false;
    }
}
