package tic.tac.toe;

public class App {
  public String getGreeting() {
    return new Game().getGreeting();
  }

  public static void main(String[] args) {
    Game game = new Game();
    System.out.println(game.getGreeting());
    System.out.println(game.displayGame());
    System.out.println(game.prompt());

  }
}
