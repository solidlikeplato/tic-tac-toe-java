package tic.tac.toe;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App {

  public App() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  public App(InputStream in, ByteArrayOutputStream out) {
    System.setIn(in);
    System.setOut(new PrintStream(out));
  }
  public static void main(String[] args) {
    Game game = new Game();
    // System.out.println(game.getGreeting());
    // System.out.println(game.displayGame());
    // System.out.println(game.prompt());
    Scanner sc = new Scanner(System.in);
    int square = sc.nextInt();
    game.addMark(square);
    System.out.println(game.displayGame());
    sc.close();
  }
}
