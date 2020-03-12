package tic.tac.toe;

public class App {
    public static void main(String[] args) {
        InputOutput inputOutput = new ConsoleInputOutput();
        Menu menu = new Menu(inputOutput);
        Game game = menu.createGame();
        game.run();
    }
}
