package tic.tac.toe;

import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HumanPlayerTest {
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    private Player player;
    private Board testBoard;

    @Before
    public void setUp() {
        testBoard = new Board();
        player = new HumanPlayer('X');
    }
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    @Test
    public void humanPlayerHasASymbol() {
        HumanPlayer player = new HumanPlayer('X');
        assertNotNull(player.getSymbol());
    }

    @Test
    public void humanPlayerDoesNotUpdateBoardWithNonNumericInput() {
        testBoard = mock(Board.class);
        testIn = new ByteArrayInputStream("A".getBytes());
        player = new HumanPlayer('X', testIn);
        player.makeAMove(testBoard);
        verify(testBoard, never()).addMark(anyInt(), anyChar());
    }

    @Test
    public void humanPlayerMakesMoveWhenGivenValidInput() {
        testIn = new ByteArrayInputStream("5".getBytes());
        player = new HumanPlayer('X', testIn);
        char[] newBoard = {'X','X','X','X',' ','X','X','X','X'};
        testBoard.setBoard(newBoard);
        player.makeAMove(testBoard);
        assertEquals(testBoard.getMarkAt(5), 'X');
        assertTrue(player.didMove());
    }

    @Test public void humanPlayerWontAddSymbolToFilledCell() {
        testIn = new ByteArrayInputStream("7".getBytes());
        player = new HumanPlayer('O', testIn);
        char[] newBoard = {'X','X','X','X',' ','X','X','X','X'};
        testBoard.setBoard(newBoard);
        player.makeAMove(testBoard);
        assertEquals(testBoard.getMarkAt(7), 'X');
        assertFalse(player.didMove());
    }
}
