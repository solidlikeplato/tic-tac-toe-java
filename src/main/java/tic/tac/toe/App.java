package tic.tac.toe;
import java.util.Scanner;

public class App {
  public String getGreeting() {
    return new Game().getGreeting();
  }

  public static void main(String[] args) {
    Game game = new Game();
    System.out.println(game.getGreeting());
    System.out.println(game.displayGame());
    System.out.println(game.prompt());
    Scanner sc = new Scanner(System.in);
    int square = sc.nextInt();
    sc.close();
    game.addMark(square);
    System.out.println(game.displayGame());
  }
}
