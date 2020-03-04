package tic.tac.toe;
import java.util.Arrays;

public class Board {
  private char[] board;

  public Board() {
    board = new char[9];
    Arrays.fill(board, ' ');
  }

  public int getBoardSize() {
    return board.length;
  }

  public void setBoard(char[] board) {
    this.board = board;
  }

  public boolean canCellTakeMark(int cell) {
    if (cell < 1 || cell > board.length) {
      return false;
    }
    return (board[cell-1] == ' ');
  }
  
  public boolean isBoardFull() {
    boolean boardFull = true;
    for (int i = 0; i < board.length && boardFull; i++) {
      boardFull = board[i] !=' ';
    }
    return boardFull;
  }

  public void addMark(int position, char symbol) {
    if (canCellTakeMark(position)) {
      board[position - 1] = symbol;
    }
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }
}
