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
    public void computerBlocksOpponentWin() {
        char[] newBoard = { 'X',' ',' ',
                            'X',' ',' ',
                            ' ',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertEquals(testBoard.getMarkAt(7), computer.getSymbol());
    }

    @Test
    public void computerSetsUpFutureWin() {
        char[] newBoard = { ' ','X','O',
                            ' ','O',' ',
                            'X',' ',' '};
        testBoard.setBoard(newBoard);
        computer.makeAMove(testBoard);
        assertTrue(computer.didMove());
        assertThat(computer.getSymbol(), either(is(testBoard.getMarkAt(6))).or(is(testBoard.getMarkAt(9))));
    }

    // next test to make pass
//    @Test
//    public void computerBlocksOpponentFutureWin() {
//        char[] newBoard = { ' ',' ','X',
//                            ' ','O',' ',
//                            'X',' ',' '};
//        testBoard.setBoard(newBoard);
//        computer.makeAMove(testBoard);
//        assertTrue(computer.didMove());
//        assertThat(computer.getSymbol(), either(is(testBoard.getMarkAt(2)))
//                                            .or(is(testBoard.getMarkAt(4)))
//                                            .or(is(testBoard.getMarkAt(6)))
//                                            .or(is(testBoard.getMarkAt(8))));
//    }
}
