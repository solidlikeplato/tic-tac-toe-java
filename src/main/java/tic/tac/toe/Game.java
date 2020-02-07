package tic.tac.toe;
import java.util.Arrays;

public class Game {
  private char[] board;
  Game() {
    board = new char[9];
    Arrays.fill(board, ' ');
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