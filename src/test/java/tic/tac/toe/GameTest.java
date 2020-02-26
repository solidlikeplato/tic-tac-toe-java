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
  }
  
  public void RunGame(String input) {
    testIn = new ByteArrayInputStream(input.getBytes());
    mockedUI = mock(UI.class);
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
    RunGame("A");
    verify(mockedUI, never()).addMark(anyInt());
  }

  @Test
  public void updateBoardWithSingleUserCorrectInput() {
    RunGame("5");
    verify(mockedUI).getGreeting();
    verify(mockedUI, atLeastOnce()).displayBoard();
    verify(mockedUI).addMark(5);
  }

}
