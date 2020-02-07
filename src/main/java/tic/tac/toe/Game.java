package tic.tac.toe;
import java.util.Arrays;

public class Game {
  private char[] board;

  Game() {
    board = new char[9];
    Arrays.fill(board, ' ');
  }

  public String displayGame() {
    String firstRow = "   |   |   \n";
    String horizontalRule = "---|---|---\n";
    String secondRow = "   |   |   \n";
    String thirdRow = "   |   |   ";
    
    return firstRow + horizontalRule + secondRow + horizontalRule + thirdRow;
  }

  public String getGreeting() {
    return "Welcome to Tic Tac Toe";
  }

}