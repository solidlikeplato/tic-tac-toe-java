package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class MessagesTest {
  private Board board;
  private Messages messages;

  @Before
  public void setUp() {
    board = new Board();
    messages = new Messages();
  }

  @Test
  public void formatsEmptyBoardCorrectly() {
    assertEquals(messages.formattedBoard(board),
        "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
    
  @Test
  public void promptForX() {
    assertEquals(messages.prompt('X'), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?");
  }

  @Test
  public void promptForO() {
    assertEquals(messages.prompt('O'), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your O?");
  }

  @Test
  public void xIsWinner() {
    assertEquals(messages.outcome('X'), "Congratulations to X, our winner!");
  }

  @Test
  public void oIsWinner() {
    assertEquals(messages.outcome('O'), "Congratulations to O, our winner!");
  }

  @Test
  public void tieGame() {
    assertEquals(messages.outcome(' '), "There is no winner! The only winning move is not to play!");
  }
}
