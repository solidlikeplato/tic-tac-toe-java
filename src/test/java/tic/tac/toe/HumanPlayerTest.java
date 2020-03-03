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
    private Board mockedBoard;

    @Before
    public void setUp() {
        mockedBoard = mock(Board.class);
        player = new HumanPlayer('X');
    }
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    @Test
    public void HumanPlayerHasASymbol() {
        HumanPlayer player = new HumanPlayer('X');
        assertEquals(player.getSymbol(), 'X');
    }

    @Test
    public void playerDoesNotUpdateBoardWithNonNumericInput() {
        testIn = new ByteArrayInputStream("A".getBytes());
        player = new HumanPlayer('X', testIn);
        player.makeAMove(mockedBoard);
        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
    }

    @Test
    public void playerMakesMoveWhenGivenValidInput() {
        testIn = new ByteArrayInputStream("5".getBytes());
        player = new HumanPlayer('X', testIn);
        when(mockedBoard.isBoardFull()).thenReturn(true);
        when(mockedBoard.isCellEmpty(anyInt())).thenReturn(true);
        player.makeAMove(mockedBoard);
        verify(mockedBoard).addMark(5,'X');
    }

    @Test public void playerWontAddSymbolToFilledCell() {
        testIn = new ByteArrayInputStream("5".getBytes());
        when(mockedBoard.isCellEmpty(anyInt())).thenReturn(false);
        player = new HumanPlayer('X', testIn);
        player.makeAMove(mockedBoard);
        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
    }
}
