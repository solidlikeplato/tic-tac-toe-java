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
  private UI mockedUI;

  @Before
  public void setUp() {
    testOut = new ByteArrayOutputStream();
    mockedUI = mock(UI.class);
  }
  
  public void RunGame(String input) {
    testIn = new ByteArrayInputStream(input.getBytes());
    Game game = new Game(testIn, testOut, mockedUI);
    game.run();
  }
  
  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void gameDoesntUpdateBoardWithNonNumericInput() {
    when(mockedUI.isBoardFull()).thenReturn(true);
    RunGame("A");
    verify(mockedUI, never()).addMark(anyInt());
  }

  @Test
  public void updateBoardWithSingleUserCorrectInput() {
    when(mockedUI.isBoardFull()).thenReturn(true);
    RunGame("5");
    verify(mockedUI).getGreeting();
    verify(mockedUI, atLeastOnce()).displayBoard();
    verify(mockedUI).addMark(5);
  }

  @Test
  public void gameLoopsUntilGameIsOver() {
    when(mockedUI.isBoardFull())
      .thenReturn(false)
      .thenReturn(false)
      .thenReturn(true);
    RunGame("9\n6\n3\n4\n");
    verify(mockedUI, times(3)).prompt();
  }
}
