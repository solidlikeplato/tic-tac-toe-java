package tic.tac.toe;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {  
  @Test
  public void testNewGameBoardIsEmpty() {
    Game game = new Game();
    for (int cell = 1; cell <= game.getBoardSize(); cell++) {
      assertTrue("Each Cell Should Be Empty", game.isCellEmpty(cell));
    }
  }

  @Test
  public void testAddXToBoard() {
    Game game = new Game();
    game.addMark(2);
    assertEquals("Game should Add an 'X' to cell 2", game.getMarkAt(2), 'X');
  }
}

