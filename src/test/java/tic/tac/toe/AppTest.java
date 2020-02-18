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
  public void testTakesUserInput() {
    final String UserInput = "5";
    provideInput(UserInput);
    App app = new App(testIn, testOut);
    app.main(new String[0]);
    assertEquals("Should put symbol in correct place", "   |   |   \n---|---|---\n   | X |   \n---|---|---\n   |   |   \n", getOutput());
  }
}
