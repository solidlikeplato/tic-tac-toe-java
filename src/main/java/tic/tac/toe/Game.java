package tic.tac.toe;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
  private UI ui;

  public Game() {
    System.setIn(System.in);
    System.setOut(System.out);
    ui = new UI(new Board());
  }

  public Game(InputStream in, ByteArrayOutputStream out, UI mockedUI) {
    System.setIn(in);
    System.setOut(new PrintStream(out));
    ui = mockedUI;
  }

  public void run() {
    System.out.println(ui.getGreeting());
    System.out.println(ui.displayBoard());
    System.out.println(ui.prompt());
    Scanner sc = new Scanner(System.in);
    try {
      int square = sc.nextInt();
      ui.addMark(square);
    }
    catch (Exception e) {
      // If input isn't an int we want it to do nothing
    }
    System.out.println(ui.displayBoard());
    sc.close();
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.run();
  }
}
