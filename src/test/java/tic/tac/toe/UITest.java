package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class UITest {
  private Board board;
  private UI ui;

  @Before
  public void setUp() {
    board = new Board();
    ui = new UI();
  }

  @Test
  public void uiWithNewGameDisplaysEmptyBoard() {
    assertEquals("A new board in a UI should display as empty", ui.displayBoard(board),
        "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
    
  @Test
  public void uiPromptsForInputSymbol() {
    assertEquals("Initially should prompt for X to move", ui.prompt('X'), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?");
    assertEquals("Initially should prompt for X to move", ui.prompt('O'), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your O?");
  }

  @Test
  public void uiGivesCorrectWinnerMessages() {
    assertEquals(ui.displayOutcome('X'), "Congratulations to X, our winner!");
    assertEquals(ui.displayOutcome('O'), "Congratulations to O, our winner!");
    assertEquals(ui.displayOutcome(' '), "There is no winner! The only winning move is not to play!");
  }
}
