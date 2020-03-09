package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {
  private Game game;
  private UI ui;
  private Board board;
  private char[] fullBoardNoWinner = {'a','b','c','d','e','f','g','h','i'};
  private char[] notQuiteFullBoardOneSymbol = {'a','a','a','a','a','a','a','a',' '};
  private Player player1 = new HumanPlayer('X');
  private Player player2 = new HumanPlayer('O');
  private Player mockPlayer1 = mock(HumanPlayer.class);


  @Before
  public void setUp() {
    ui = mock(UI.class);
    board = new Board();
    game = new Game(ui, board, player1, player2);
  }

  @Test
  public void gameDoesntPromptWhenBoardIsFull() {
    board.setBoard(fullBoardNoWinner);
    game.run();
    verify(ui, never()).prompt(anyChar());
  }

  @Test
  public void gameDoesntPromptAfterGameIsWon() {
    char[] topRowWinner = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
    board.setBoard(topRowWinner);
    game = new Game(ui, board, mockPlayer1, player2);
    game.run();
    verify(ui, never()).prompt(anyChar());
  }

  @Test
  public void gameChangesPlayerIfMadeMove() {
    Board mockedBoard = mock(Board.class);
    game = new Game(ui, mockedBoard, mockPlayer1, player2);
    when(mockedBoard.isBoardFull()).thenReturn(false).thenReturn(true);
    when(mockedBoard.isCellEmpty(anyInt())).thenReturn(true);
    when(mockPlayer1.didMove()).thenReturn(true);
    game.run();
    assertEquals(game.getCurrentPlayerSymbol(), player2.getSymbol());
  }

  @Test
  public void gameDoesntChangePlayerIfNoMoveMade() {
    Board mockedBoard = mock(Board.class);
    game = new Game(ui, mockedBoard, mockPlayer1, player2);
    when(mockedBoard.isBoardFull()).thenReturn(false).thenReturn(true);
    when(mockedBoard.isCellEmpty(anyInt())).thenReturn(true);
    when(mockPlayer1.didMove()).thenReturn(false);
    when(mockPlayer1.getSymbol()).thenReturn('&');
    game.run();
    assertEquals(game.getCurrentPlayerSymbol(), mockPlayer1.getSymbol());
  }

  @Test
  public void newGameDoesntHaveWinner() {
    assertFalse(game.isWinner());
  }

  @Test
  public void gameRecognizesWinnerColumn() {
    char[] rightColWinner = {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '};
    board.setBoard(rightColWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerRow() {
    char[] topRowWinner = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
    board.setBoard(topRowWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerDiagonal() {
    char[] backSlashDiagonalWinner = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
    board.setBoard(backSlashDiagonalWinner);
    game = new Game(ui, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameDisplaysWinner() {
    board.setBoard(notQuiteFullBoardOneSymbol);
    game = new Game(ui, board, mockPlayer1, player2);
    when(mockPlayer1.getSymbol()).thenReturn('&');
    game.run();
    verify(ui).displayOutcome(mockPlayer1.getSymbol());
  }

  @Test
  public void gameDisplaysTieMessage() {
    board.setBoard(fullBoardNoWinner);
    game = new Game(ui, board, mockPlayer1, player2);
    when(mockPlayer1.getSymbol()).thenReturn('&');
    game.run();
    verify(ui).displayOutcome(' ');
  }
}
