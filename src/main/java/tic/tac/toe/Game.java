package tic.tac.toe;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
  private UI ui;
  private Board board;
  private char player1 = 'X';
  private char player2 = 'O';
  private char currentPlayer = player1;

  public Game() {
    System.setIn(System.in);
    System.setOut(System.out);
    board = new Board();
    ui = new UI(board);
  }

  public Game(InputStream in, ByteArrayOutputStream out, UI mockedUI, Board mockedBoard) {
    System.setIn(in);
    System.setOut(new PrintStream(out));
    ui = mockedUI;
    board = mockedBoard;
  }

  public char getCurrentPlayer() {
    return currentPlayer;
  }

  public void takeATurn(Scanner sc) {
    System.out.println(ui.prompt(currentPlayer));
    try {
      int square = sc.nextInt();
      if (board.isCellEmpty(square)) {
        board.addMark(square, currentPlayer);
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
      }
    }
    catch (Exception e) {
      // If input isn't an int we want it to do nothing
    }
    System.out.println(ui.displayBoard());
  }

  public void run() {
    boolean keepPlaying = true;
    Scanner sc = new Scanner(System.in);
    System.out.println(ui.getGreeting());
    System.out.println(ui.displayBoard());
    while (keepPlaying) {
      takeATurn(sc);
      keepPlaying = !board.isBoardFull();
    }
    sc.close();
  }
}
