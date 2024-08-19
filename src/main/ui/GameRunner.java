package main.ui;

import main.model.Board;

import java.util.Iterator;

public class GameRunner {

    private Board board;

    public GameRunner() {
        runInstance();
    }

    private void runInstance() {
        init();
    }

    private void init() {
        board = Board.getBoard();
        printBoard();
    }

    private void printBoard() {
        Iterator<Integer> it = board.iterator();

        for (int i = 0; i < 9; i++) {
            System.out.print(it.next());

            if (i % 3 < 2) {
                System.out.print("|");
            } else if (it.hasNext()){
                System.out.println();
                System.out.println("-----");
            }
        }
    }

}
