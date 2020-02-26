package tic.tac.toe;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {  
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
}

