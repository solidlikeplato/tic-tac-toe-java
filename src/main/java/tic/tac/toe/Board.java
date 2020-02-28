package tic.tac.toe;
import java.util.Arrays;

public class Board {
  private char[] board;
  private char currentPlayerSymbol = 'X';

  public Board() {
    board = new char[9];
    Arrays.fill(board, ' ');
  }

  public int getBoardSize() {
    return board.length;
  }
  
  public char getCurrentPlayerSymbol() {
    return currentPlayerSymbol;
  }

  public boolean isCellEmpty(int cell) {
    return (board[cell-1] == ' ');
  }
  
  public boolean isBoardFull() {
    boolean boardFull = true;
    for (int i = 0; i < board.length && boardFull; i++) {
      boardFull = board[i] !=' ';
    }
    return boardFull;
  }
  // will be removed once all code has been changed to use new interface
  public void addMark(int position) {
    if (position > 0 && position <= getBoardSize() && isCellEmpty(position)) {
      board[position - 1] = currentPlayerSymbol;
      currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }
  }

  public void addMark(int position, char symbol) {
    if (position > 0 && position <= getBoardSize() && isCellEmpty(position)) {
      board[position - 1] = symbol;
      currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }
}
