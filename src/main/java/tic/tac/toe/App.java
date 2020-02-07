package tic.tac.toe;

public class App {
  private Game game;
  App() {
    game = new Game();
  }

  public String displayGame() {
    String board = game.displayGame();
    System.out.println(board);
    return board;
  }

  public String getGreeting() {
    return new Game().getGreeting();
  }

  public static void main(String[] args) {
    Game game = new Game();
    System.out.println(game.getGreeting());
    System.out.println(game.displayGame());
  }
}
