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
  public void testNewGameDisplaysEmptyBoard() {
    Game game = new Game();
    assertEquals("A new game should display an empty board", game.displayGame(), "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
  
  @Test
  public void testGamePromptsUser() {
    Game game = new Game();
    assertEquals("Game should prompt user with available squares", game.prompt(), "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?");
  }

  @Test
  public void testAddXToBoard() {
    Game game = new Game();
    game.addMark(2);
    assertEquals("Game should Add an 'X'", game.displayGame(),  "   | X |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
}

