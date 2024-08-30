package main.ui;

import main.model.Board;

import java.util.Arrays;

public class GameRunnerUtil {

    // looks for a full board
    public static boolean checkFull() {
        for (int i : Board.getBoard()) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    // checks if either player or program has won
    // returns 0 if no winner, 1 if player win, 2 if program win
    public static int getWinner() {
        Board board = Board.getBoard();
        for (int i = 0; i < 3; i++) {
            int[] arr = board.getRow(i);
            if (arr[0] == arr[1] && arr[1] == arr[2]) {
                return arr[0];
            }
        }
        for (int i = 0; i < 3; i++) {
            int[] arr = board.getCol(i);
            if (arr[0] == arr[1] && arr[1] == arr[2]) {
                return arr[0];
            }
        }
        for (int i = 0; i < 2; i++) {
            int[] arr = board.getDiag(i);
            if (arr[0] == arr[1] && arr[1] == arr[2]) {
                return arr[0];
            }
        }

        return 0;
    }

    // look for a winning move first in rows, then cols, then diagonals
    // look for a denying move in case no winning move is found
    public static int[] goodMove() {
        int[] move = null;
        Board board = Board.getBoard();

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] row = board.getRow(j);
                if (Arrays.equals(row, new int[]{0, i, i})) {
                    move = new int[]{j, 0};
                } else if (Arrays.equals(row, new int[]{i, 0, i})) {
                    move = new int[]{j, 1};
                } else if (Arrays.equals(row, new int[]{i, i, 0})) {
                    move = new int[]{j, 2};
                }
            }

            for (int j = 0; j < 3; j++) {
                int[] col = board.getCol(j);
                if (Arrays.equals(col, new int[]{0, i, i})) {
                    move = new int[]{0, j};
                } else if (Arrays.equals(col, new int[]{i, 0, i})) {
                    move = new int[]{1, j};
                } else if (Arrays.equals(col, new int[]{i, i, 0})) {
                    move = new int[]{2, j};
                }
            }

            int[] diag = board.getDiag(0);
            if (Arrays.equals(diag, new int[]{0, i, i})) {
                move = new int[]{0, 0};
            } else if (Arrays.equals(diag, new int[]{i, 0, i})) {
                move = new int[]{1, 1};
            } else if (Arrays.equals(diag, new int[]{i, i, 0})) {
                move = new int[]{2, 2};
            }

            int[] antidiag = board.getDiag(1);
            if (Arrays.equals(antidiag, new int[]{0, i, i})) {
                move = new int[]{2, 0};
            } else if (Arrays.equals(antidiag, new int[]{i, 0, i})) {
                move = new int[]{1, 1};
            } else if (Arrays.equals(antidiag, new int[]{i, i, 0})) {
                move = new int[]{0, 2};
            }
        }

        return move;
    }
}
