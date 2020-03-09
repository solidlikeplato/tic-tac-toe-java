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

  private boolean isWinner() {
    return false; // Winner calculation to be implemented here
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

  public void takeATurn() {
    System.out.println(ui.prompt(currentPlayer.getSymbol()));
    currentPlayer.makeAMove(board);
    if (currentPlayer.didMove()) {
      changePlayer();
    }
    System.out.println(ui.displayBoard());
  }

  public void run() {
    boolean keepPlaying = true;
    System.out.println(ui.getGreeting());
    System.out.println(ui.displayBoard());
    while (keepPlaying) {
      takeATurn();
      keepPlaying = !isGameOver();
    }
    // insert output here of winner / tie state
  }
}
