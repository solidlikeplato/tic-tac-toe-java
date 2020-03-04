package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {
  private Game game;
  private UI ui;
  private Board board;
  private char[] fullBoard = {'a', 'b', 'c', 'd', 'e','f', 'g','h','i'};
  private Player player1 = new HumanPlayer('X');
  private Player player2 = new HumanPlayer('O');


  @Before
  public void setUp() {
    ui = mock(UI.class);
    board = new Board();
    game = new Game(ui, board, player1, player2);
  }

  @Test
  public void gameDoesntContinueWhenBoardIsFull() {
    board.setBoard(fullBoard);
    game.run();
    verify(ui).prompt(anyChar());
  }

  @Test
  public void gameDoesntContinueWhenGameIsWon() {
    char[] topRowWinner = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
    board = new Board();
    board.setBoard(topRowWinner);
    game = new Game(ui, board, player1, player2);
    HumanPlayer mockPlayer1 = mock(HumanPlayer.class);
    game.setPlayer1(mockPlayer1);
    game.run();
    verify(ui).prompt(anyChar());
  }

  @Test
  public void gameChangesPlayerIfMadeMove() {
    HumanPlayer mockPlayer1 = mock(HumanPlayer.class);
    board.setBoard(fullBoard);
    game = new Game(ui, board, player1, player2);
    game.setPlayer1(mockPlayer1);
    when(mockPlayer1.didMove()).thenReturn(true);
    game.run();
    assertEquals(game.getCurrentPlayerSymbol(), 'O');
  }

  @Test
  public void gameDoesntChangePlayerIfNoMoveMade() {
    HumanPlayer player1 = mock(HumanPlayer.class);
    board.setBoard(fullBoard);
    game.setPlayer1(player1);
    when(player1.didMove()).thenReturn(false);
    when(player1.getSymbol()).thenReturn('X');
    game.run();
    assertEquals(game.getCurrentPlayerSymbol(), 'X');
  }

  @Test
  public void newGameDoesntHaveWinner() {
    assertFalse(game.isWinner());
  }

  @Test
  public void gameRecognizesWinnerColumn() {
    char[] rightColWinner = {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '};
    Board board = new Board();
    board.setBoard(rightColWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerRow() {
    char[] topRowWinner = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
    Board board = new Board();
    board.setBoard(topRowWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerDiagonal() {
    char[] backSlashDiagonalWinner = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
    Board board = new Board();
    board.setBoard(backSlashDiagonalWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }
}
