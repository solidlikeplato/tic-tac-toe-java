package tic.tac.toe;

import java.util.*;

public class UnbeatableComputer implements Player {
    public static final int NO_MOVE = 0;
    public static final int[] MOVE_PRIORITY = {5,1,3,7,9,2,4,6,8};
    private char symbol;
    private char opponentSymbol;
    private boolean didMakeMove = false;
    private Rules rules = new Rules();

    UnbeatableComputer(char symbol, char opponentSymbol) {
        this.symbol = symbol;
        this.opponentSymbol = opponentSymbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public char getOpponentSymbol() {
        return opponentSymbol;
    }

    private GameStatus boardStatusAfterMove(Board board, char symbolToCheck, int cell) {
        board.addMark(cell, symbolToCheck);
        GameStatus status = rules.determineStatus(board, this);
        board.addMark(cell, board.EMPTY_CELL);
        return status;
    }

    private int findWin(Board board, char symbolToCheck) {
        int move = NO_MOVE;
        GameStatus status = (symbolToCheck == symbol) ? GameStatus.PLAYER_WINS: GameStatus.PLAYER_LOSES;
        for (int cell: board.getEmptyCells()) {
            if ( boardStatusAfterMove(board, symbolToCheck, cell) == status ) {
                move = cell;
            }
        }
        return move;
    }

    private List<Integer> findPivots(Board board, char symbolToCheck) {
        List<Integer> pivots = new ArrayList<>();
        GameStatus status = (symbolToCheck == symbol) ? GameStatus.PLAYER_WINS: GameStatus.PLAYER_LOSES;
        for (int cell: board.getEmptyCells()) {
            int winCount = 0;
            board.addMark(cell, symbolToCheck);
            for (int nextMoveCell: board.getEmptyCells()) {
                if (boardStatusAfterMove(board, symbolToCheck, nextMoveCell) == status) {
                    winCount++;
                }
            }
            if (winCount >= 2) {
                pivots.add(cell);
            }
            board.addMark(cell, board.EMPTY_CELL);
        }
        return pivots;
    }

    private int makePriorityMove(Board board) {
        for (int moveToCheck: MOVE_PRIORITY) {
            if (board.isCellEmpty(moveToCheck)) {
                return moveToCheck;
            }
        }
        return NO_MOVE;
    }

    private int blockOpponentPivots(Board board, List<Integer> pivots) {
        int move = NO_MOVE;
        for (int cell: board.getEmptyCells()) {
            board.addMark(cell, symbol);

            for (int nextMoveCell: board.getEmptyCells()) {
                if (boardStatusAfterMove(board,symbol,nextMoveCell) == GameStatus.PLAYER_WINS &&
                        !pivots.contains(nextMoveCell)){
                    move = cell;
                }
            }
            board.addMark(cell, board.EMPTY_CELL);
        }
        return move;
    }

    public void makeAMove(Board board) {
        int move = NO_MOVE;
        // win if possible
        move = findWin(board, symbol);

        // block opponent win if possible
        if (move == NO_MOVE) {
            move = findWin(board, opponentSymbol);
        }
        // set up future win if possible
        if (move == NO_MOVE) {
            List<Integer> pivots = findPivots(board, symbol);
            if (pivots.size() > 0) {
                move = pivots.get(0);
            }
        }

        // block opponent future win if possible
        if (move == NO_MOVE) {
            List<Integer> pivots = findPivots(board, opponentSymbol);
            move = blockOpponentPivots(board, pivots);
        }

        // choose move by priority
        if (move == NO_MOVE) {
            move = makePriorityMove(board);
        }

        if (move != NO_MOVE) {
            board.addMark(move, symbol);
            didMakeMove = true;
        }
    }

    public boolean didMove() {
        return didMakeMove;
    }
}
