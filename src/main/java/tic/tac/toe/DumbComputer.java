package tic.tac.toe;

public class DumbComputer implements Player{
    private char symbol;
    private boolean didMakeMove = false;
    DumbComputer(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
    public boolean didMove(){
        return didMakeMove;
    }
    public void makeAMove(Board board) {
        didMakeMove = false;
        int currentCell = 1;
        while (!didMakeMove && currentCell <= board.getBoardSize()) {
            if (board.isCellEmpty(currentCell)) {
                board.addMark(currentCell, symbol);
                didMakeMove = true;
            }
            currentCell++;
        }
    }
}
