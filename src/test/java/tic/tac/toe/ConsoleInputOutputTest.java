package tic.tac.toe;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;

public class ConsoleInputOutputTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private InputOutput ioUnderTest;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        ioUnderTest = new ConsoleInputOutput();
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
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
    public void outputIsSentToDesignatedOutputStream() {
        ioUnderTest.sendOutput("Hello World!");
        assertEquals("Hello World!\n", getOutput());
    }

    @Test
    public void inputReturnsZeroOnNonIntegerInput() {
        final String testString = "FakeInputHere";
        provideInput(testString);
        int value = ioUnderTest.getInput();
        assertEquals(0, value);
    }

    @Test
    public void inputReturnsCorrectlyForIntegerInput() {
        final String testString = "4";
        provideInput(testString);
        int value = ioUnderTest.getInput();
        assertEquals(4, value);
    }
}
