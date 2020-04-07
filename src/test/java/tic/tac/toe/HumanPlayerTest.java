package tic.tac.toe;

import org.junit.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HumanPlayerTest {

    private Player player;
    private Board mockedBoard;
    private InputOutput inputOutput;

    @Before
    public void setUp() {
        mockedBoard = mock(Board.class);
        inputOutput = mock(ConsoleInputOutput.class);
        player = new HumanPlayer('X', 'O', inputOutput);
    }

    @Test
    public void humanPlayerHasASymbol() {
        assertNotNull(player.getSymbol());
    }

    @Test
    public void humanPlayerDoesNotUpdateBoardWithZeroAsInput() {
        when(inputOutput.getInput()).thenReturn(0);
        player.makeAMove(mockedBoard);
        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
    }

    @Test
    public void humanPlayerMakesMoveWhenGivenValidInput() {
        when(inputOutput.getInput()).thenReturn(5);
        when(mockedBoard.isBoardFull()).thenReturn(true);
        when(mockedBoard.isCellEmpty(anyInt())).thenReturn(true);
        player.makeAMove(mockedBoard);
        verify(mockedBoard).addMark(5,'X');
        assertTrue(player.didMove());
    }

    @Test public void humanPlayerWontAddSymbolToFilledCell() {
        when(inputOutput.getInput()).thenReturn(0);
        when(mockedBoard.isCellEmpty(anyInt())).thenReturn(false);

        player.makeAMove(mockedBoard);

        verify(mockedBoard, never()).addMark(anyInt(), anyChar());
        assertFalse(player.didMove());
    }
}
