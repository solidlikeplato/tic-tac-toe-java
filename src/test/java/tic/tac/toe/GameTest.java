package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;

public class GameTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  private Game game;
  private UI mockedUI;
  private Board mockedBoard;
  

  @Before
  public void setUp() {
    testOut = new ByteArrayOutputStream();
    mockedUI = mock(UI.class);
    mockedBoard = mock(Board.class);
    game = new Game();
  }
  
  public void RunGame(String input) {
    testIn = new ByteArrayInputStream(input.getBytes());
    game = new Game(testIn, testOut, mockedUI, mockedBoard);
    game.run();
  }
  
  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void gameDoesntUpdateBoardWithNonNumericInput() {
    when(mockedBoard.isBoardFull()).thenReturn(true);
    RunGame("A");
    verify(mockedBoard, never()).addMark(anyInt(), anyChar());
  }

  @Test
  public void updateBoardWithSingleUserCorrectInput() {
    when(mockedBoard.isBoardFull()).thenReturn(true);
    when(mockedBoard.isCellEmpty(anyInt())).thenReturn(true);
    RunGame("5");
    verify(mockedUI).getGreeting();
    verify(mockedUI, atLeastOnce()).displayBoard();
    verify(mockedBoard).addMark(anyInt(), anyChar()); // mockito only allows all args as matchers or none
  }

  @Test
  public void gameLoopsUntilGameIsOver() {
    when(mockedBoard.isBoardFull())
      .thenReturn(false)
      .thenReturn(false)
      .thenReturn(true);
    RunGame("9\n6\n3\n4\n");
    verify(mockedUI, times(3)).prompt(anyChar());
  }


  @Test
  public void gameWontAddSymbolToFilledCell() {
    char player1 = game.getCurrentPlayer();
    when(mockedBoard.isBoardFull()).thenReturn(true);
    when(mockedBoard.isCellEmpty(anyInt())).thenReturn(false);
    RunGame("5");
    assertEquals("trying to add to a filled cell doesn't change the player", game.getCurrentPlayer(), player1);
  }
}
