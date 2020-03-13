package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class DumbComputerTest {
    @Test
    public void computerDoesntMoveOnFullBoard() {
        Player computer = new DumbComputer('O');
        Board board = new Board();
        char[] fullBoard = {'X','O','X',
                            'X','O','X',
                            'O','X','O'};
        board.setBoard(fullBoard);
        computer.makeAMove(board);

        assertFalse(computer.didMove());
    }

    @Test
    public void computerTakesFirstEmptySquare() {
        Player computer = new DumbComputer('O');
        Board board = new Board();
        char[] fullBoard = {'X',' ',' ',
                            ' ','X','O',
                            'X',' ','O'};
        board.setBoard(fullBoard);
        computer.makeAMove(board);

        assertEquals(computer.getSymbol(), board.getMarkAt(2));
        assertTrue(computer.didMove());
    }
}
