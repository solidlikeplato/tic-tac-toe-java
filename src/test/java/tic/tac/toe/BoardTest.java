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
    for (int cell = 1; cell <= board.getBoardSize(); cell++) {
      assertTrue("Each Cell Should Be Empty", board.isCellEmpty(cell));
    }
  }

  @Test
  public void addXToBoardOnValidInput() {
    board.addMark(2, 'X');
    assertEquals("board should Add an 'X' to cell 2", board.getMarkAt(2), 'X');
  }

  @Test
  public void doesntAddSymbolOnOutOfRangeInput() {
    board.addMark(10, 'X');
    board.addMark(0, 'X');
    for (int cell = 1; cell <= board.getBoardSize(); cell++) {
      assertTrue("Each Cell Should Be Empty", board.isCellEmpty(cell));
    }
  }

  @Test
  public void addMultipleSymbolsToBoard() {
    board.addMark(2, 'X');
    board.addMark(3, 'O');
    board.addMark(4, 'X');
    assertEquals("Board should Add an 'X' to cell 2", board.getMarkAt(2), 'X');
    assertEquals("Board should Add an 'O' to cell 3", board.getMarkAt(3), 'O');
    assertEquals("Board should Add an 'X' to cell 4", board.getMarkAt(4), 'X');
  }

  @Test
  public void newGameEmptyBoardNotEnded() {
    assertFalse("New board should not be over immediately", board.isBoardFull());
  }

  @Test
  public void boardWithNoEmptySpacesIsFull() {
    for (int i = 1; i <= board.getBoardSize(); i++) {
      board.addMark(i, 'X');
    }
    assertTrue("Full board ends game", board.isBoardFull());
  }

  @Test
  public void addingOneSymbolAtLastCellDoesntEndGame() {
    board.addMark(9, 'X');
    assertFalse("Adding a symbol only to last cell doesn't end game", board.isBoardFull());
  }

  @Test
  public void outOfRangeCellsAreNotEmpty() {
    assertFalse(board.isCellEmpty(0));
    assertFalse(board.isCellEmpty(10));
  }
}
