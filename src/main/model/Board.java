package main.model;

import main.model.exceptions.FullException;

import java.util.Iterator;

public class Board implements Iterable<Integer> {

    private static Board instance;
    private int[][] board;

    // instantiates 2D array 3x3 with all 0s
    private Board() {
        board = new int[3][3];
    }

    // creates a new board if it does not already exist
    public static Board getBoard() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // resets board to be all 0s
    public void resetBoard() {
        board = new int[3][3];
    }

    // returns int at given row/col indices
    public int getValue(int row, int col) {
        return board[row][col];
    }

    // sets a value at given row/col
    // throws FullException if index is not empty (0)
    // throws IllegalArgumentException if val is not valid (1 | 2)
    public void setValue(int val, int row, int col) throws FullException {
        if (getValue(row, col) != 0) {
            throw new FullException();
        } else if (val != 1 && val != 2) {
            throw new IllegalArgumentException("Value must be 1 or 2");
        }
        board[row][col] = val;
    }

    // returns row at given index
    public int[] getRow(int row) {
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = getValue(row, i);
        }
        return arr;
    }

    // returns col at given index
    public int[] getCol(int col) {
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = getValue(i, col);
        }
        return arr;
    }

    // returns diagonal given sel 0, returns anti-diagonal given sel 1
    public int[] getDiag(int sel) {
        if (sel == 0) {
            return new int[]{getValue(0, 0), getValue(1, 1), getValue(2, 2)};
        } else if (sel == 1) {
            return new int[]{getValue(2, 0), getValue(1, 1), getValue(0, 2)};
        }
        throw new IllegalArgumentException("Selection must be 0 or 1");
    }

    // iterates through board left-right, top-down
    public Iterator<Integer> iterator() {
        return new BoardIterator();
    }

    private class BoardIterator implements Iterator<Integer> {

        int rowIndex = 0;
        int colIndex = 0;

        @Override
        public boolean hasNext() {
            return (rowIndex <= 2 && colIndex <= 2);
        }

        @Override
        public Integer next() {
            int curr = getValue(rowIndex, colIndex);

            if (colIndex == 2) {
                rowIndex++;
                colIndex = 0;
            } else {
                colIndex++;
            }

            return curr;
        }

    }

}
