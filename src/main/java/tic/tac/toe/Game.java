package tic.tac.toe;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
  private UI ui;
  private Board board;
  private Player player1;
  private Player player2;
  private Player currentPlayer;

  public Game(UI ui, Board board, Player player1, Player player2) {
    this.ui = ui;
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
  }

  public void setPlayer1(Player newPlayer) {
    player1 = newPlayer;
    currentPlayer = player1;
  }

  public void setPlayer2(Player newPlayer) {
    player2 = newPlayer;
  }

  public boolean isWinner() {
    int[][] columnWinningCombinations = {{1,4,7}, {2,5,8}, {3,6,9}, {1,2,3}, {4,5,6}, {7,8,9}, {1,5,9}, {3,5,7}};
    for (int[] column: columnWinningCombinations) {
      char mark1 = board.getMarkAt(column[0]);
      char mark2 = board.getMarkAt(column[1]);
      char mark3 = board.getMarkAt(column[2]);
      if (mark1 == mark2 && mark2 == mark3) {
        if (!(board.canCellTakeMark(column[0]) || board.canCellTakeMark(column[1]) || board.canCellTakeMark(column[2]))) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isGameOver() {
    return board.isBoardFull() || isWinner();
  }

  private void changePlayer() {
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
  }

  public char getCurrentPlayerSymbol() {
    return currentPlayer.getSymbol();
  }

  // this method is probably doing too much -- displayPromptANDMakeMoveANDDisplayBoard -- move more to run?
  public void takeATurn() {
    System.out.println(ui.prompt(currentPlayer.getSymbol()));
    currentPlayer.makeAMove(board);
    System.out.println(ui.displayBoard());
  }

  public void run() {
    boolean keepPlaying = true;
    System.out.println(ui.getGreeting());
    System.out.println(ui.displayBoard());
    while (keepPlaying) {
      takeATurn();
      // Check if Winner here, if winner set winner to current player

      if (currentPlayer.didMove()) {
        changePlayer();
      }
      keepPlaying = !board.isBoardFull() && !isWinner();
    }
    // insert output here of winner / tie state
  }
}
