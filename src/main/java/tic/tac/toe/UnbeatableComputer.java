package tic.tac.toe;

import java.util.*;

public class UnbeatableComputer implements Player {
    public static final int NO_MOVE = 0;
    public static final int[] MOVE_PRIORITY = {5,1,3,7,9,2,4,6,8};
    public static final int[][][] OTHER_TWO_IN_EVERY_LINE = { {},
            { {2,3},{5,9},{4,7} }, { {1,4},{5,8} }, { {1,2},{5,7},{6,9} },
            { {1,7},{5,6} }, { {1,9},{2,8},{3,7},{4,6} }, { {4,5},{3,9} },
            { {1,4},{3,5},{8,9} }, { {2,5},{7,9} }, { {1,5},{3,6},{6,7} }
    };
    private char symbol;
    private char opponentSymbol;
    private boolean didMakeMove = false;

    UnbeatableComputer(char symbol, char opponentSymbol) {
        this.symbol = symbol;
        this.opponentSymbol = opponentSymbol;
    }

    public char getSymbol() {
        return symbol;
    }

    boolean isWin(Board board, char symbolToCheck, int cell) {
        for (int[] two: OTHER_TWO_IN_EVERY_LINE[cell]) {
            if (board.getMarkAt(two[0]) == symbolToCheck && board.getMarkAt(two[1]) == symbolToCheck) {
                return true;
            }
        }
        return false;
    }

    int[] find2EmptyWith1Symbol(Board board, char symbolToCheck, int[] threeCellsToCheck) {
        int[] emptyCells = {};
        for (int cellIndex = 0; cellIndex < 3 && Arrays.equals(emptyCells, new int[0]); cellIndex++) {
            if (    board.isCellEmpty(threeCellsToCheck[cellIndex]) &&
                    board.isCellEmpty(threeCellsToCheck[(cellIndex + 1) % 3]) &&
                    board.getMarkAt(threeCellsToCheck[(cellIndex + 2) % 3]) == symbolToCheck) {
                emptyCells = new int[] {threeCellsToCheck[cellIndex], threeCellsToCheck[(cellIndex + 1) % 3]};
            }
        }
        return emptyCells;
    }

    int findWin(Board board, char symbolToCheck) {
        int move = NO_MOVE;
        for (int cell: board.getEmptyCells()) {
            if ( isWin(board, symbolToCheck, cell) ) {
                move = cell;
            }
        }
        return move;
    }

    int blockOpponentNextTurnWin(Board board) {
        int move = NO_MOVE;
        for(int[] combination: Game.WINNING_COMBINATIONS) {
            int[] twoEmptyCells = find2EmptyWith1Symbol(board, symbol, combination);
            if (twoEmptyCells.length != 0) {
                return twoEmptyCells[0];
            }
        }
        return move;
    }

    int makePriorityMove(Board board) {
        for (int moveToCheck: MOVE_PRIORITY) {
            if (board.isCellEmpty(moveToCheck)) {
                return moveToCheck;
            }
        }
        return NO_MOVE;
    }

    int findNextTurnWin(Board board, char symbolToWin) {
        int moveToMake = NO_MOVE;
        int[] possiblePivots = new int[10];
        Arrays.fill(possiblePivots, 0);
        for (int[] combination: Game.WINNING_COMBINATIONS) {
            for (int cell: find2EmptyWith1Symbol(board, symbolToWin, combination)){
                possiblePivots[cell] += 1;
                if (possiblePivots[cell] == 2) { return cell; }
            }
        }
        return moveToMake;
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
            move = findNextTurnWin(board, symbol);
        }
        // block opponent future win if possible
        if (move == NO_MOVE && findNextTurnWin(board, opponentSymbol) != NO_MOVE) {
            move = blockOpponentNextTurnWin(board);
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
