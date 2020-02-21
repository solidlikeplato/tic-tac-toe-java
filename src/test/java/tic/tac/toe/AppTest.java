package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;

public class AppTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  Game mockedGame;

  @Before
  public void setUp() {
    testOut = new ByteArrayOutputStream();

  }
  
  public void RunApp(String input) {
    testIn = new ByteArrayInputStream(input.getBytes());
    mockedGame = mock(Game.class);
    App app = new App(testIn, testOut, mockedGame);
    app.run();
  }

  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void testUpdateBoardWithSingleUserCorrectInput() {
    RunApp("5");
    verify(mockedGame).getGreeting();
    verify(mockedGame, atLeastOnce()).displayGame();
    verify(mockedGame).addMark(5);
  }

  @Test
  public void testDoesntUpdateBoardWithNonNumericInput() {
    RunApp("A");
    verify(mockedGame, never()).addMark(anyInt());
  }

  @Test
  public void testDoesntUpdateBoardWithOutOfRangeInput() {
    RunApp("10");
    verify(mockedGame).addMark(10);
  }
}
