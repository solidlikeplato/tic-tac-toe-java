package tic.tac.toe;

public class App {
  private Game game;
  App() {
    game = new Game();
  }
  
  App( Game mockedGame) {
    game = mockedGame;
  }

  public void run() {
    System.out.println(game.getGreeting());
    System.out.println(game.displayGame());
  }
  public static void main(String[] args) {
    App app = new App();
    app.run();
  }
}
