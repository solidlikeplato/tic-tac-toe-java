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
    public void humanPlayerHasASymbol() {
        HumanPlayer player = new HumanPlayer('X');
        assertNotNull(player.getSymbol());
    }

    @Test
    public void humanPlayerDoesNotUpdateBoardWithNonNumericInput() {
        testIn = new ByteArrayInputStream("A".getBytes());
        player = new HumanPlayer('X', testIn);
        player.makeAMove(mockedBoard);
        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
    }

    @Test
    public void humanPlayerMakesMoveWhenGivenValidInput() {
        testIn = new ByteArrayInputStream("5".getBytes());
        player = new HumanPlayer('X', testIn);
        when(mockedBoard.isBoardFull()).thenReturn(true);
        when(mockedBoard.canCellTakeMark(anyInt())).thenReturn(true);

        player.makeAMove(mockedBoard);

        verify(mockedBoard).addMark(5,'X');
        assertTrue(player.didMove());
    }

    @Test public void humanPlayerWontAddSymbolToFilledCell() {
        testIn = new ByteArrayInputStream("5".getBytes());
        when(mockedBoard.canCellTakeMark(anyInt())).thenReturn(false);
        player = new HumanPlayer('X', testIn);

        player.makeAMove(mockedBoard);

        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
        assertFalse(player.didMove());
    }
}
