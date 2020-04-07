package tic.tac.toe;

public class HumanPlayer implements Player{
    private boolean didMakeMove = false;
    private char symbol;
    private char opponentSymbol;
    private InputOutput inputOutput;
    HumanPlayer(char symbol) {
        System.setIn(System.in);
        this.symbol = symbol;
    }

    HumanPlayer(char symbol,char opponentSymbol, InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.symbol = symbol;
        this.opponentSymbol = opponentSymbol;

    }

    public char getOpponentSymbol() {
        return opponentSymbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean didMove() {
        return didMakeMove;
    }

    public void makeAMove(Board board) {
        didMakeMove = false;
        int square = inputOutput.getInput();
        if (board.isCellEmpty(square)) {
            board.addMark(square, symbol);
            didMakeMove = true;
        }
    }
}
