package tic.tac.toe;
import java.util.Arrays;

public class Game {
  private char[] board;
  public Game() {
    this.board = new char[9];
    Arrays.fill(board, ' ');
  }

  public int getBoardSize() {
    return board.length;
  }
  
  public boolean isCellEmpty(int cell) {
    return (board[cell-1] == ' ');
  }
  
  public void addMark(int position) {
    board[position - 1] = 'X';
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }
}