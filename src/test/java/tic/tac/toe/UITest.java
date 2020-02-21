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
  public void uiPromptsForX() {
    assertEquals("Initially should prompt for X to move", ui.prompt(), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?");
  }

  @Test
  public void testUIPromptChangesAfterMove() {
    board.addMark(5);
    assertEquals("After Making A Move the prompt should prompt for O to move", ui.prompt(), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your O?");
  }
}