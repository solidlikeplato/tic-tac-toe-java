package tic.tac.toe;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
  @Test
  public void testGameHasAGreeting() {
    Game game = new Game();
    assertNotNull("game should have a greeting", game.getGreeting());
  }
  
  @Test
  public void testNewGameBoardIsEmpty() {
    Game game = new Game();
    assertEquals("game should have an empty board", game.displayGame(), "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }

  @Test
  public void testFullBoardDisplay() {
    char x = 'X';
    char o = 'O';
    char[] board = new char[] { x,o,x,x,o,x,o,x,o };
    Game game = new Game(board);
    assertEquals("full board should display correctly", game.displayGame(), " X | O | X \n---|---|---\n X | O | X \n---|---|---\n O | X | O ");
  }

  @Test
  public void testGameDoneBoardFull() {
    char x = 'X';
    char o = 'O';
    char[] board = new char[] { x,o,x,x,o,x,o,x,o };
    Game game = new Game(board);
    assertTrue("full board should lead to game over condition", game.gameOver());
  }

  @Test
  public void testGameDoneWinningBoard() {
    char x = 'X';
    char o = 'O';
    char[] board = new char[] { x,x,x,o,' ',o,o,' ',' ' };
    Game game = new Game(board);
    assertTrue("winning board should lead to game over condition", game.gameOver());
  }
}

