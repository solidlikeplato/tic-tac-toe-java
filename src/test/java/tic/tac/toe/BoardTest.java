package tic.tac.toe;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  public void addsSymbolOverExistingSymbol() {
    char[] newBoard = { 'x','x','x',
                        ' ',' ',' ',
                        ' ',' ',' '};
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
    char[] fullBoard = {'x','x','x',
                        'x','x','x',
                        'x','x','x'};
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
    assertFalse(board.isCellEmpty(0));
    assertFalse(board.isCellEmpty(10));
  }

  @Test
  public void setBoardToFullBoard() {
    char[] newBoard = { 'X','X','X',
                        'X','X','X',
                        'X','X','X'};
    board.setBoard(newBoard);
    int[] xSquares = {1,2,3,4,5,6,7,8,9};
    assertTrue(board.isBoardFull());
    for (int square: xSquares) {
      assertEquals(board.getMarkAt(square), 'X');
    }
  }

  @Test
  public void setBoardToPartialBoard() {
    char[] newBoard = { ' ','X',' ',
                        'X',' ','X',
                        'X','X','X'};
    board.setBoard(newBoard);
    int[] emptySquares = {1,3,5};
    int[] xSquares = {2,4,6,7,8,9};
    assertFalse(board.isBoardFull());
    for (int square: emptySquares) {
      assertTrue(board.isCellEmpty(square));
      assertEquals(board.getMarkAt(square), ' ');
    }
    for (int square: xSquares) {
      assertFalse(board.isCellEmpty(square));
      assertEquals(board.getMarkAt(square), 'X');
    }
  }

  @Test
  public void outOfRangeCellsAreEmpty() {
    assertFalse(board.isCellEmpty(0));
    assertFalse(board.isCellEmpty(10));
  }

  @Test
  public void returnsListOfEmptyCells() {
    char[] newBoard = {'X','X','X',
                       ' ',' ','X',
                       'O',' ',' '};
    board.setBoard(newBoard);
    List<Integer> emptyCells = new ArrayList<>(Arrays.asList(4,5,8,9));
    assertEquals(board.getEmptyCells(), emptyCells);
  }

  @Test
  public void returnsADifferentListOfEmptyCells() {
    char[] newBoard = {' ','X','X',
                       ' ','O','X',
                       'O',' ','X'};
    board.setBoard(newBoard);
    List<Integer> expectedEmptyCells = Arrays.asList(1, 4, 8);
    assertEquals(board.getEmptyCells(), expectedEmptyCells);
  }

  @Test
  public void returnsListOfCells() {
    char[] newBoard = {'X','X','X',
                       ' ',' ','X',
                       'O',' ',' '};
    board.setBoard(newBoard);
    List<Integer> cellsToGet = new ArrayList<>(Arrays.asList(1,4,7));
    List<Character> valuesOfCells = new ArrayList<>(Arrays.asList('X', ' ', 'O'));
    assertEquals(board.getCells(cellsToGet), valuesOfCells);
  }

  @Test
  public void returnsAnotherListOfCells() {
    char[] newBoard = {'X','X','X',
                       ' ',' ','X',
                       'O',' ',' '};
    board.setBoard(newBoard);
    List<Integer> cellsToGet = new ArrayList<>(Arrays.asList(1,5,9));
    List<Character> valuesOfCells = new ArrayList<>(Arrays.asList('X', ' ', ' '));
    assertEquals(board.getCells(cellsToGet), valuesOfCells);
  }
}
