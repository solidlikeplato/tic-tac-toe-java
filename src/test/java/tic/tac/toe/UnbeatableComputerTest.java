package tic.tac.toe;

import org.hamcrest.core.AnyOf;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UnbeatableComputerTest {
    private Player computer = new UnbeatableComputer('O');
    private Board testBoard = new Board();

    @Test
    public void computerPlayerHasASymbol() {
        assertNotNull(computer.getSymbol());
    }

    @Test
    public void computerTakesNoMoveIfGivenFullBoard() {
        char[] newBoard = { 'X','X','X',
                            'X','X','X',
                            'X','X','X'};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertFalse(computer.didMove());
    }

    @Test
    public void computerWinsWhenGivenChance() {
        char[] newBoard = { 'O',' ',' ',
                            'O',' ',' ',
                            ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(7), computer.getSymbol());
    }

    @Test
    public void computerWinsWhenGivenAnotherChance() {
        char[] newBoard = { 'O',' ',' ',
                            ' ','O',' ',
                            ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(9), computer.getSymbol());
    }

    @Test
    public void computerBlocksOpponentWin() {
        char[] newBoard = {  ' ',' ','X',
                             ' ','X',' ',
                             ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(7), computer.getSymbol());
    }

    @Test
    public void computerBlocksAnotherOpponentWin() {
        char[] newBoard = {  ' ',' ',' ',
                             'X','X',' ',
                             ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(7), computer.getSymbol());
    }

}
