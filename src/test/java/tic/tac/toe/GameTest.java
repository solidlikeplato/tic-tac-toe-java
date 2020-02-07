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
}
