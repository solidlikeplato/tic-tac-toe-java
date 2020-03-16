package tic.tac.toe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
  public static final char EMPTY_CELL = ' ';
  private char[] board;

  public Board() {
    board = new char[9];
    Arrays.fill(board, EMPTY_CELL);
  }

  public int getBoardSize() {
    return board.length;
  }

  public void setBoard(char[] board) {
    this.board = board;
  }

  public List<Integer> getEmptyCells() {
    List<Integer> emptyCells = new ArrayList<Integer>();
    for (int cell = 1; cell <= 9; cell++) {
      if (isCellEmpty(cell)) {
        emptyCells.add(cell);
      }
    }
    return emptyCells;
  }

  public List<Character> getCells(List<Integer> cellsToGet) {
    List<Character> cells = cellsToGet.stream()
            .map(cell -> getMarkAt(cell))
            .collect(Collectors.toList());
    return cells;
  }

  public boolean isCellInRange(int cell) {
    return cell >= 1 && cell <= board.length;
  }

  public boolean isCellEmpty(int cell) {
    return isCellInRange(cell) && board[cell-1] == EMPTY_CELL;
  }

  public boolean isBoardFull() {
    boolean boardFull = true;
    for (int i = 0; i < board.length && boardFull; i++) {
      boardFull = board[i] !=' ';
    }
    return boardFull;
  }

  public void addMark(int position, char symbol) {
    if (isCellInRange(position)) {
      board[position - 1] = symbol;
    }
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }

}
