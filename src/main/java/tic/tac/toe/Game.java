package tic.tac.toe;
import java.util.Arrays;

public class Game {
  private char[] board;
  public Game() {
    this.board = new char[9];
    Arrays.fill(board, ' ');
  }
  
  public void addMark(int position) {
    board[position - 1] = 'X';
  }
  
  public String prompt() {
    return "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?";
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