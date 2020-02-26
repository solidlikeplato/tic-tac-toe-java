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
  private UI mockedUI;

  @Before
  public void setUp() {
    testOut = new ByteArrayOutputStream();
  }
  
  public void RunApp(String input) {
    testIn = new ByteArrayInputStream(input.getBytes());
    mockedUI = mock(UI.class);
    App app = new App(testIn, testOut, mockedUI);
    app.run();
  }
  
  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  @Test
  public void appDoesntUpdateBoardWithNonNumericInput() {
    RunApp("A");
    verify(mockedUI, never()).addMark(anyInt());
  }

  @Test
  public void updateBoardWithSingleUserCorrectInput() {
    RunApp("5");
    verify(mockedUI).getGreeting();
    verify(mockedUI, atLeastOnce()).displayGame();
    verify(mockedUI).addMark(5);
  }

}
