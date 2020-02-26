package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class UITest {
  private Board board;
  private UI ui;
  @Before
  public void setUp() {
    board = new Board();
    ui = new UI(board);
  }

  @Test
  public void uiWithNewGameDisplaysEmptyBoard() {
    assertEquals("A new board in a UI should display as empty", ui.displayBoard(),
        "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
    
  @Test
  public void uiPromptsUser() {
    assertNotNull("A UI should have a prompt", ui.prompt());
  }
}