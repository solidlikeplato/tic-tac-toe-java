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
  
  public boolean isGameOver() {
    boolean boardFull = true;
    for (int i = 0; i < board.length; i++){
      boardFull = board[i] !=' ';
    }
    return boardFull;
  }

  public void addMark(int position) {
    if (position > 0 && position <= getBoardSize() && isCellEmpty(position)){
      board[position - 1] = currentPlayerSymbol;
      currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }
  }

  public char getMarkAt(int position) {
    return board[position-1];
  }
}
