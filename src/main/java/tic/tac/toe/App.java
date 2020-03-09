package tic.tac.toe;

public class App {
    public static void main(String[] args) {
        Player player1 = new HumanPlayer('X');
        Player player2 = new HumanPlayer('O');
        Board board = new Board();
        UI ui = new UI();
        Game game = new Game(ui, board, player1, player2);
        game.run();
    }
}