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
      assertTrue("Each Cell Should Be Empty", board.canCellTakeMark(cell));
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
      assertTrue("Each Cell Should Be Empty", board.canCellTakeMark(cell));
    }
  }

  @Test
  public void addsSymbolOverExistingSymbol() {
    char[] newBoard = {'x','x','x',' ',' ',' ',' ',' ',' '};
    board.setBoard(newBoard);

    board.addMark(2, 'O');
    assertEquals(board.getMarkAt(2), 'O');
  }

  @Test
  public void addMultipleSymbolsToBoard() {
    board.addMark(2, 'X');
    board.addMark(3, 'O');
    assertEquals("Board should Add an 'X' to cell 2", board.getMarkAt(2), 'X');
    assertEquals("Board should Add an 'O' to cell 3", board.getMarkAt(3), 'O');
  }

  @Test
  public void newGameEmptyBoardNotEnded() {
    assertFalse("New board should not be full", board.isBoardFull());
  }

  @Test
  public void boardWithNoEmptySpacesIsFull() {
    char[] fullBoard = {'x','x','x','x','x','x','x','x','x'};
    board.setBoard(fullBoard);
    assertTrue("Full board", board.isBoardFull());
  }

  @Test
  public void addingOneSymbolAtLastCellDoesntFillBoard() {
    board.addMark(9, 'X');
    assertFalse("Adding a symbol only to last cell doesn't result in full board", board.isBoardFull());
  }

  @Test
  public void outOfRangeCellsCantTakeMark() {
    assertFalse(board.canCellTakeMark(0));
    assertFalse(board.canCellTakeMark(10));
  }

  @Test
  public void setBoardToFullBoard() {
    char[] newBoard = {'X','X','X','X','X','X','X','X','X'};
    board.setBoard(newBoard);
    int[] xSquares = {1,2,3,4,5,6,7,8,9};
    assertTrue(board.isBoardFull());
    for (int square: xSquares) {
      assertEquals(board.getMarkAt(square), 'X');
    }
  }

  @Test
  public void setBoardToPartialBoard() {
    char[] newBoard = {' ','X',' ','X',' ','X','X','X','X'};
    board.setBoard(newBoard);
    int[] emptySquares = {1,3,5};
    int[] xSquares = {2,4,6,7,8,9};
    assertFalse(board.isBoardFull());
    for (int square: emptySquares) {
      assertTrue(board.canCellTakeMark(square));
      assertEquals(board.getMarkAt(square), ' ');
    }
    for (int square: xSquares) {
      assertFalse(board.canCellTakeMark(square));
      assertEquals(board.getMarkAt(square), 'X');
    }
  }

  @Test
  public void outOfRangeCellsAreEmpty() {
    assertFalse(board.isCellEmpty(0));
    assertFalse(board.isCellEmpty(10));
  }
}
