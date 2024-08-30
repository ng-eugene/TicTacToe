package main.ui;

import main.model.Board;
import main.model.exceptions.FullException;

import java.util.*;

public class GameRunner {

    private Board board;
    private Random random;
    private Scanner scanner;
    private Boolean playerTurn;
    private int winner;

    public GameRunner() {
        runInstance();
    }

    private void runInstance() {
        init();

        while(winner == 0) {
            if (playerTurn) {
                System.out.println("Your turn");
                handlePlayer();
            } else {
                System.out.println("Our turn");
                makeMove();
            }
            playerTurn = !playerTurn;
            checkState();
            printBoard();
        }

        switch (winner) {
            case 1:
                System.out.println("You win");
                break;
            case 2:
                System.out.println("You lose");
                break;
            case 3:
                System.out.println("Draw");
                break;
        }
    }

    // randomly decides if the player or the program will start
    private void init() {
        board = Board.getBoard();
        random = new Random();
        scanner = new Scanner(System.in);
        playerTurn = random.nextBoolean();
        winner = 0;
        printBoard();
    }

    // checks board state, setting winner to 1 if the player wins, 2 if the program wins, or 3 if stalemated
    private void checkState() {
        boolean full = GameRunnerUtil.checkFull();
        winner = GameRunnerUtil.getWinner();
        if (winner == 0 && full) {
            winner = 3;
        }
    }

    // handle player's move
    private void handlePlayer() {
        int[] input = new int[2];
        System.out.println("Which row would you like to make your turn in?");
        input[0] = Integer.parseInt(scanner.nextLine())-1;
        System.out.println("Which column would you like to make your turn in?");
        input[1] = Integer.parseInt(scanner.nextLine())-1;
        try {
            board.setValue(1, input[0], input[1]);
        } catch (FullException e) {
            System.out.println("That box is already filled.");
            handlePlayer();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
            handlePlayer();
        }
    }

    // make a move, looking first for a winning move, then a move to deny player win, then a random move
    private void makeMove() {
        int[] move = GameRunnerUtil.goodMove();
        if (move == null) {
            move = randomMove();
        }

        try {
            board.setValue(2, move[0], move[1]);
            System.out.println("Making our move...\n");
        } catch (FullException e) {
            System.out.println("Unexpected error.");
            System.exit(0);
        }
    }

    // selects a random move from available slots
    private int[] randomMove() {
        ArrayList<int[]> poss = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getValue(i, j) == 0) {
                    poss.add(new int[]{i,j});
                }
            }
        }
        return poss.get(random.nextInt(poss.size()));
    }

    private void printBoard() {
        Iterator<Integer> it = board.iterator();

        for (int i = 0; i < 9; i++) {
            int val = it.next();
            switch (val) {
                case 0:
                    System.out.print(" ");
                    break;
                case 1:
                    System.out.print("X");
                    break;
                case 2:
                    System.out.print("O");
                    break;
            }

            if (i % 3 < 2) {
                System.out.print("|");
            } else if (it.hasNext()){
                System.out.println("\n-----");
            }
        }
        System.out.println("\n\n");
    }

}
