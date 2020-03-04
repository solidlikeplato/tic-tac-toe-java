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

  public boolean isCellEmpty(int cell) {
    if (cell < 1 || cell > board.length) {
      return false; // non-existant cells are not empty
    }
    return (board[cell-1] == ' ');
  }

  public void setBoard(char[] board) {
    this.board = board;
  }

  public boolean isBoardFull() {
    boolean boardFull = true;
    for (int i = 0; i < board.length && boardFull; i++) {
      boardFull = board[i] !=' ';
    }
    return boardFull;
  }

  public void addMark(int position, char symbol) {
    if (position > 0 && position <= board.length && isCellEmpty(position)) {
      board[position - 1] = symbol;
    }
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }
}
