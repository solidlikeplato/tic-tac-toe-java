package tic.tac.toe;
import java.util.Arrays;

public class Game {
  private char[] board;
  public Game() {
    this.board = new char[9];
    Arrays.fill(board, ' ');
  }

  public char winner() {
    int[][] winningMoves = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    char winningPlayer = ' ';
    for (int i = 0; i < winningMoves.length; i++){
      int[] threeToCheck = winningMoves[i];

      char[] moves = { board[threeToCheck[0]], board[threeToCheck[1]], board[threeToCheck[2]] };
      
      if (moves[0] == moves[1] && moves[1] == moves[2]) {
        winningPlayer = moves[0];
      }
    }
    return winningPlayer;
  }
  public boolean isBoardFull() {
    boolean boardFull = true;
    int i = 0;
    while (boardFull && i < board.length) {
      if (board[i] == ' ') {
        boardFull = false;
      }
      i++;
    }
    return boardFull;
  }

  public boolean gameOver() {
    
    boolean isWinner = winner() == 'X' || winner() == 'O';
    
    return isBoardFull() || isWinner;
  }

  public Game(char[] newBoard) {
    this.board = newBoard;
  }
  
  public String displayGame() {
    String firstRow = " " + board[0] + " | " + board[1] + " | "+ board[2]+ " \n";
    String horizontalRule = "---|---|---\n";
    String secondRow = " " + board[3] + " | " + board[4] + " | "+ board[5]+ " \n";
    String thirdRow = " " + board[6] + " | " + board[7] + " | "+ board[8]+ " ";
    return firstRow + horizontalRule + secondRow + horizontalRule + thirdRow;
  }

  public String getGreeting() {
    return "Welcome to Tic Tac Toe";
  }
}