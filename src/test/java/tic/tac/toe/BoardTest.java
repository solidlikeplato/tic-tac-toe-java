package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {  
  private Board board;
  @Before
  public void setUp() {
    board = new Board();
  }

  @Test
  public void newBoardIsEmpty() {
    Board board = new Board();
    for (int cell = 1; cell <= board.getBoardSize(); cell++) {
      assertTrue("Each Cell Should Be Empty", board.isCellEmpty(cell));
    }
  }

  @Test
  public void addXToBoardOnValidInput() {
    Board board = new Board();
    board.addMark(2);
    assertEquals("board should Add an 'X' to cell 2", board.getMarkAt(2), 'X');
  }

  @Test
  public void doesntAddSymbolOnOutOfRangeInput() {
    Board board = new Board();
    board.addMark(10);
    board.addMark(0);
    for (int cell = 1; cell <= board.getBoardSize(); cell++) {
      assertTrue("Each Cell Should Be Empty", board.isCellEmpty(cell));
    }
  }

  @Test
  public void addMultipleSymbolsToBoard() {
    Board board = new Board();
    board.addMark(2);
    board.addMark(3);
    board.addMark(4);
    assertEquals("Board should Add an 'X' to cell 2", board.getMarkAt(2), 'X');
    assertEquals("Board should Add an 'O' to cell 3", board.getMarkAt(3), 'O');
    assertEquals("Board should Add an 'X' to cell 4", board.getMarkAt(4), 'X');
  }

  @Test
  public void newGameEmptyBoardNotEnded() {
    Board board = new Board();
    assertFalse("New board should not be over immediately", board.isGameOver());
  }

  @Test
  public void testFullBoardEndsGame() {
    Board board = new Board();
    for (int i = 1; i <= board.getBoardSize(); i++) {
      board.addMark(i);
    }
    assertTrue("Full board ends game", board.isGameOver());
  }

  @Test
  public void boardWontAddSymbolToFilledCell() {
    board.addMark(2);
    board.addMark(2);
    assertEquals("Current player should still be O", board.getCurrentPlayerSymbol(), 'O');
    assertEquals("Cell 2 should still be X", board.getMarkAt(2), 'X');
  }
}
