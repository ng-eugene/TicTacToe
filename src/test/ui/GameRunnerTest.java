package test.ui;

import main.model.Board;
import main.model.exceptions.FullException;
import main.ui.GameRunnerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = Board.getBoard();
        board.resetBoard();
    }

    @Test
    public void checkEmptyBoardTest() {
        assertFalse(GameRunnerUtil.checkFull());
    }

    @Test
    public void checkNotFullBoardTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(1, 0, 2);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 1);
            board.setValue(2, 2, 0);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertFalse(GameRunnerUtil.checkFull());
    }

    @Test
    public void checkFullBoardTest() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    board.setValue(1, i, j);
                } catch (FullException e) {
                    fail("Unexpected exception thrown");
                }
            }
        }
        assertTrue(GameRunnerUtil.checkFull());
    }

    @Test
    public void noWinnerTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(1, 0, 2);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 1);
            board.setValue(2, 2, 0);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertEquals(0, GameRunnerUtil.getWinner());
    }

    @Test
    public void playerWinnerTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(1, 0, 1);
            board.setValue(1, 0, 2);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 1);
            board.setValue(2, 2, 0);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertEquals(1, GameRunnerUtil.getWinner());
    }

    @Test
    public void programWinnerTest() {
        try {
            board.setValue(2, 0, 0);
            board.setValue(1, 0, 2);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 1);
            board.setValue(2, 2, 0);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertEquals(2, GameRunnerUtil.getWinner());
    }

    @Test
    public void diagWinnerTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(2, 0, 1);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 1);
            board.setValue(1, 2, 2);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertEquals(1, GameRunnerUtil.getWinner());
    }

    @Test
    public void noGoodMoveTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(2, 0, 1);
            board.setValue(1, 1, 2);
            board.setValue(2, 1, 0);
            board.setValue(2, 2, 0);
            board.setValue(1, 2, 1);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertNull(GameRunnerUtil.goodMove());
    }

    @Test
    public void stopLoseTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(2, 0, 1);
            board.setValue(1, 1, 0);
            board.setValue(1, 1, 2);
            board.setValue(2, 2, 0);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertArrayEquals(new int[]{1, 1}, GameRunnerUtil.goodMove());
    }

    @Test
    public void winMoveTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(2, 1, 1);
            board.setValue(1, 1, 2);
            board.setValue(2, 2, 0);
            board.setValue(1, 2, 1);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertArrayEquals(new int[]{0, 2}, GameRunnerUtil.goodMove());
    }

    @Test
    public void prioritiseWinTest() {
        try {
            board.setValue(2, 0, 0);
            board.setValue(2, 1, 0);
            board.setValue(1, 1, 2);
            board.setValue(1, 2, 2);
        } catch (FullException e) {
            fail("Unexpected exception thrown");
        }
        assertArrayEquals(new int[]{2, 0}, GameRunnerUtil.goodMove());
    }
}
