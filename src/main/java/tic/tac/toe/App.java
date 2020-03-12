package tic.tac.toe;

public class App {
    public static void main(String[] args) {
        InputOutput inputOutput = new ConsoleInputOutput();
        Player player1 = new HumanPlayer('X', inputOutput);
        Player player2 = new DumbComputer('O');
        Board board = new Board();
        Messages messages = new Messages();
        Game game = new Game(messages, inputOutput, board, player1, player2);
        game.run();
    }
}