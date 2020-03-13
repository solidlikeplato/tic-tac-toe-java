package tic.tac.toe;

import java.util.List;

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
        List<Integer> emptyCells = board.getEmptyCells();
        if (!emptyCells.isEmpty()) {
            board.addMark(emptyCells.get(0), symbol);
            didMakeMove = true;
        }
    }
}
