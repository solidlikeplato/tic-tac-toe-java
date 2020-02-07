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
    return game.getGreeting();
  }

  public static void main(String[] args) {    
    System.out.println(new Game().getGreeting());
    System.out.println(new Game().displayGame());
  }
}
