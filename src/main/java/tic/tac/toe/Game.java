package tic.tac.toe;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
  private UI ui;
  private Board board;
  private Player player1 = new HumanPlayer('X');
  private Player player2 = new HumanPlayer('O');
  private Player currentPlayer = player1;

  public Game() {
    board = new Board();
    ui = new UI(board);
  }

  public Game(UI mockedUI, Board mockedBoard) {
    ui = mockedUI;
    board = mockedBoard;
  }

  private boolean isWinner() {
    return false; // Winner calculation to be implemented here
  }

  private void changePlayer() {
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
  }

  public char getCurrentPlayer() {
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
      keepPlaying = !board.isBoardFull() && !isWinner();
    }
    // insert output here of winner / tie state
  }
}
