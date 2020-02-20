package tic.tac.toe;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App {
  private Game game;

  public App() {
    System.setIn(System.in);
    System.setOut(System.out);
    game = new Game();
  }

  public App(InputStream in, ByteArrayOutputStream out, Game mockedGame) {
    System.setIn(in);
    System.setOut(new PrintStream(out));
    game = mockedGame;
  }

  public void run() {
    System.out.println(game.getGreeting());
    System.out.println(game.displayGame());
    System.out.println(game.prompt());
    Scanner sc = new Scanner(System.in);

    // Should this try block be inside the game class -- 2 possible points of exception: extracting int from input, out of range int from game.addMark()
    try {
      int square = sc.nextInt();
      game.addMark(square);
    }
    catch (Exception e) {
      // If input isn't valid we want it to do nothing
    }
    System.out.println(game.displayGame());
    sc.close();
  }

  public static void main(String[] args) {
    App app = new App();
    app.run();
  }
}
