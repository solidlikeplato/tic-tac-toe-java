package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class UITest {
  private Game game;
  private UI ui;
  @Before
  public void setUp() {
    game = new Game();
    ui = new UI(game);
  }

  @Test
  public void testUIWithNewGameDisplaysEmptyBoard() {
    assertEquals("A ui with a new game should display an empty board", ui.displayGame(),
        "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   ");
  }
    
  @Test
  public void testUIPromptsUser() {
    assertNotNull("A UI should have a prompt", ui.prompt());
  }
}