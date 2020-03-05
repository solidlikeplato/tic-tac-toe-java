package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {
  private Game game;
  private UI mockedUI;
  private Board mockedBoard;
  private Player player1 = new HumanPlayer('X');
  private Player player2 = new HumanPlayer('O');


  @Before
  public void setUp() {
    mockedUI = mock(UI.class);
    mockedBoard = mock(Board.class);
    game = new Game(mockedUI, mockedBoard, player1, player2);
  }

  @Test
  public void gameLoopsUntilGameIsOver() {
    when(mockedBoard.isBoardFull())
      .thenReturn(false)
      .thenReturn(false)
      .thenReturn(true);
    game.run();
    verify(mockedUI, times(3)).prompt(anyChar());
  }

  @Test
  public void gameValidatesIfPlayerMadeMove() {
    when(mockedBoard.isBoardFull())
            .thenReturn(true);
    when(mockedBoard.isCellEmpty(anyInt()))
            .thenReturn(false);
    game.run();

    assertEquals(game.getCurrentPlayerSymbol(), 'X');
  }
}
