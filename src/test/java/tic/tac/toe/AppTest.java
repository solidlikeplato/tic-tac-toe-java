package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class AppTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  @Before
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
  }
  
  public void RunApp(String input) {
    final String UserInput = input;
    provideInput(UserInput);
    App app = new App(testIn, testOut);
    app.run();
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
  }

  private String getOutput() {
    return testOut.toString();
  }

  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void testUpdateBoardWithSingleUserCorrectInput() {
    RunApp("5");
    String consoleOutput = getOutput();
    String finalBoard = consoleOutput.substring(consoleOutput.length() - 60);

    assertEquals("Should put update board based on a correct user input", "   |   |   \n---|---|---\n   | X |   \n---|---|---\n   |   |   \n", finalBoard);
  }

  @Test
  public void testDoesntUpdateBoardWithNonNumericInput() {
    RunApp("A");
    String consoleOutput = getOutput();
    String finalBoard = consoleOutput.substring(consoleOutput.length() - 60);
    assertEquals("Should not update board when given non numeric user input", "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   \n", finalBoard);
  }

  @Test
  public void testDoesntUpdateBoardWithOutOfRangeInput() {
    RunApp("10");
    String consoleOutput = getOutput();
    String finalBoard = consoleOutput.substring(consoleOutput.length() - 60);
    assertEquals("Should put update board when given numeric input outside 1-9", "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   \n", finalBoard);
  }
}
