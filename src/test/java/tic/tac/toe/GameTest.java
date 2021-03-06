package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {
  private Game game;
  private Messages messages;
  private Board board;
  private char[] fullBoardNoWinner = {'a','b','c',
                                      'd','e','f',
                                      'g','h','i'};
  private char[] notQuiteFullBoardOneSymbol = { 'a','a','a',
                                                'a','a','a',
                                                'a','a',' '};
  private Player mockPlayer1 = mock(HumanPlayer.class);
  private InputOutput inputOutput = mock(ConsoleInputOutput.class);
  private Player player1 = new HumanPlayer('X', 'O', inputOutput);
  private Player player2 = new HumanPlayer('O', 'X', inputOutput);
  private Rules rules = mock(Rules.class);

  @Before
  public void setUp() {
    messages = mock(Messages.class);
    board = new Board();
    inputOutput = new ConsoleInputOutput();
    game = new Game(messages, inputOutput, board, player1, player2);

  }

  @Test
  public void gameDoesntPromptWhenBoardIsFull() {
    board.setBoard(fullBoardNoWinner);
    game.run();
    verify(messages, never()).prompt(anyChar());
  }

  @Test
  public void gameDoesntPromptAfterGameIsWon() {
    char[] topRowWinner = { 'X','X','X',
                            ' ',' ',' ',
                            ' ',' ',' '};
    board.setBoard(topRowWinner);
    game = new Game(messages, inputOutput, board, player1, player2);
    game.run();
    verify(messages, never()).prompt(anyChar());
  }

  @Test
  public void gameChangesPlayerIfMadeMove() {
    Board mockedBoard = mock(Board.class);
    game = new Game(messages, inputOutput, mockedBoard, mockPlayer1, player2, rules);
    when(rules.determineStatus(any(), any()))
            .thenReturn(GameStatus.IN_PROGRESS)
            .thenReturn(GameStatus.IN_PROGRESS)
            .thenReturn(GameStatus.PLAYER_WINS);
    when(mockPlayer1.didMove()).thenReturn(true);
    game.run();
    assertEquals(game.getCurrentPlayerSymbol(), player2.getSymbol());
  }


  @Test
  public void newGameDoesntHaveWinner() {
    assertFalse(game.isWinner());
  }

  @Test
  public void gameRecognizesWinnerColumn() {
    char[] rightColWinner = { 'X',' ',' ',
                              'X',' ',' ',
                              'X',' ',' '};
    board.setBoard(rightColWinner);
    game = new Game(messages, inputOutput, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerRow() {
    char[] topRowWinner = { 'X','X','X',
                            ' ',' ',' ',
                            ' ',' ',' '};
    board.setBoard(topRowWinner);
    game = new Game(messages, inputOutput, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameRecoginzesWinnerDiagonal() {
    char[] backSlashDiagonalWinner = {'X',' ',' ',
                                      ' ','X',' ',
                                      ' ',' ','X'};
    board.setBoard(backSlashDiagonalWinner);
    game = new Game(messages, inputOutput, board, player1, player2);
    assertTrue(game.isWinner());
  }

  @Test
  public void gameDisplaysTieMessage() {
    board.setBoard(fullBoardNoWinner);
    game = new Game(messages, inputOutput, board, mockPlayer1, player2);
    when(mockPlayer1.getSymbol()).thenReturn('&');
    game.run();
    verify(messages).outcome(' ');
  }

  @Test
  public void gameDisplaysWinner() {
    char[] backSlashDiagonalWinner = {
            'X',' ',' ',
            ' ','X',' ',
            ' ',' ','X'
    };
    board.setBoard(backSlashDiagonalWinner);
    game = new Game(messages, inputOutput, board, player1, player2);

    game.run();

    verify(messages).outcome(player1.getSymbol());

    // winner is set in the game loop, bypassing it doesn't set winner -- fix this
  }

  @Test
  public void gameDoesntChangePlayerIfNoMoveMade() {
    game = new Game(messages, inputOutput, board, mockPlayer1, player2, rules);
    when(rules.determineStatus(any(), any()))
                .thenReturn(GameStatus.IN_PROGRESS)
                .thenReturn(GameStatus.IN_PROGRESS)
                .thenReturn(GameStatus.PLAYER_WINS);
     when(mockPlayer1.getSymbol()).thenReturn('&');
     when (mockPlayer1.didMove()).thenReturn(false);

     assertEquals(mockPlayer1.getSymbol(), game.getCurrentPlayerSymbol());

  }

}
