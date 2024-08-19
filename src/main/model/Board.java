package main.model;

import main.model.exceptions.FullException;

import java.util.Iterator;

public class Board implements Iterable<Integer> {

    private static Board instance;
    private int[][] board;

    private Board() {
        board = new int[3][3];
    }

    public static Board getBoard() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void resetBoard() {
        board = new int[3][3];
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int val, int row, int col) throws FullException {
        if (getValue(row, col) != 0) {
            throw new FullException();
        } else if (val != 1 && val != 2) {
            throw new IllegalArgumentException();
        }
        board[row][col] = val;
    }

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
