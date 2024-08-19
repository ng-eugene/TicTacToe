package test.model;

import main.model.Board;
import main.model.exceptions.FullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = Board.getBoard();
        board.resetBoard();
    }

    @Test
    public void testConstructor() {
        Board board2 = Board.getBoard();
        assertEquals(board, board2);

        assertEquals(0, board.getValue(0, 0));
        assertEquals(0, board.getValue(0, 1));
        assertEquals(0, board.getValue(2, 1));
    }

    @Test
    public void setValTest() {
        try {
            board.setValue(2, 0, 0);
            board.setValue(1, 1, 2);
            board.setValue(2, 2, 2);
        } catch (FullException | IllegalArgumentException e) {
            fail("Unexpected exception thrown");
        }

        assertEquals(2, board.getValue(0, 0));
        assertEquals(1, board.getValue(1, 2));
        assertEquals(2, board.getValue(2, 2));


        try {
            board.setValue(1, 0, 0);
            fail("Expected exception was not thrown");
        } catch (FullException e) {
            // pass
        } catch (IllegalArgumentException e) {
            fail("Unexpected IllegalArgumentException thrown");
        }

        assertEquals(2, board.getValue(0, 0));

        try {
            board.setValue(3, 1, 1);
            fail("Expected exception was not thrown");
        } catch (FullException e) {
            fail("Unexpected FullException thrown");
        } catch (IllegalArgumentException e) {
            // pass
        }

        assertEquals(0, board.getValue(1, 1));

    }

    @Test
    public void iteratorTest() {
        try {
            board.setValue(1, 0, 0);
            board.setValue(2, 0, 1);
        } catch (FullException | IllegalArgumentException e) {
            fail("Unexpected exception thrown");
        }

        Iterator<Integer> it = board.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());

        int sum = 0;
        for (int i : board) {
            sum++;
        }

        assertEquals(9, sum);
    }

}