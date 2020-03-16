package tic.tac.toe;

import java.util.*;

public class UnbeatableComputer implements Player {
    public static final int NO_MOVE = 0;
    private char symbol;
    private boolean didMakeMove = false;

    UnbeatableComputer(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    private int findWin(int[] combination, char symbolToMatch, Board board) {
        List<Character> threeSymbols = board.getCells(combination);


        return 0;
    }

    private int findWinningMove(Board board) {
        int winningMove = NO_MOVE;
        for (int combination = 0;
             combination < Game.WINNING_COMBINATIONS.length && winningMove == NO_MOVE;
             combination++) {
            winningMove = findWin(Game.WINNING_COMBINATIONS[combination], symbol, board);
        }
        return winningMove;
    }

    public void makeAMove(Board board) {
        int move = NO_MOVE;
        // win if possible
        move = findWinningMove(board);
        if (move != NO_MOVE) {
            board.addMark(move, symbol);
            didMakeMove = true;
        }
    }

    public boolean didMove() {
        return didMakeMove;
    }
}
