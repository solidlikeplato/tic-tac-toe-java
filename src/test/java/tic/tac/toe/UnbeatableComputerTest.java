package tic.tac.toe;

import org.hamcrest.core.AnyOf;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UnbeatableComputerTest {
    private Player computer = new UnbeatableComputer('O', 'X');
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
        assertEquals(computer.getSymbol(), testBoard.getMarkAt(7));
    }

    @Test
    public void computerBlocksAnotherOpponentWin() {
        char[] newBoard = {  ' ',' ',' ',
                             'X','X',' ',
                             ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(computer.getSymbol(), testBoard.getMarkAt(6));
    }

    @Test
    public void computerGuaranteesNextTurnWin() {
        char[] newBoard = { ' ',' ','O',
                            ' ','X',' ',
                            'O',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertThat(computer.getSymbol(), either(is(testBoard.getMarkAt(1))).or(is(testBoard.getMarkAt(9))));
    }

   @Test
    public void computerBlocksOpponentNextTurnWin() {
        char[] newBoard = { ' ',' ','X',
                            ' ','O',' ',
                            'X',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertThat(computer.getSymbol(), either(is(testBoard.getMarkAt(2)))
                                            .or(is(testBoard.getMarkAt(4)))
                                            .or(is(testBoard.getMarkAt(6)))
                                            .or(is(testBoard.getMarkAt(8))));
    }

    @Test
    public void computerMovesInCenterIfAvailable() {
        testBoard = new Board();
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(5), computer.getSymbol());
    }

    @Test
    public void computerGoesInCornerIfAvailable() {
        char[] newBoard = { ' ',' ',' ',
                            ' ','X',' ',
                            ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertThat(computer.getSymbol(), either(is(testBoard.getMarkAt(1)))
                                            .or(is(testBoard.getMarkAt(3)))
                                            .or(is(testBoard.getMarkAt(7)))
                                            .or(is(testBoard.getMarkAt(9))));
    }
}
