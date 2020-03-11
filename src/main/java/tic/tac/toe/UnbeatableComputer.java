package tic.tac.toe;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class UnbeatableComputer implements Player {
    public static final int NO_MOVE = 0;
    public static final char NON_EMPTY_NON_PLAYER = '*';
    public static final int[][] WINNING_COMBINATIONS = {{1,4,7}, {2,5,8}, {3,6,9}, {1,2,3}, {4,5,6}, {7,8,9}, {1,5,9}, {3,5,7}};
    public static final int[] MOVE_PREFERENCE_IN_ORDER = {5,1,3,7,9,2,4,6,8};

    private char symbol;
    private boolean didMakeMove = false;

    UnbeatableComputer(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    private int findNowWinningMove(int[] cellsToCheck, Board board) {
        int winningMove = NO_MOVE;
        for (int cell = 0; cell < cellsToCheck.length && winningMove == NO_MOVE; cell++) {
            if (    board.isCellEmpty(cellsToCheck[cell]) &&
                    board.getMarkAt(cellsToCheck[(cell + 1) % 3]) == symbol &&
                    board.getMarkAt(cellsToCheck[(cell + 2) % 3]) == symbol) {
                winningMove = cellsToCheck[cell];
            }
        }
        return winningMove;
    }

    private int findNowOpponentWinningMove(int[] cellsToCheck, Board board) {
        int winningMove = NO_MOVE;
        for (int cell = 0; cell < cellsToCheck.length && winningMove == NO_MOVE; cell++) {
            if (    board.isCellEmpty(cellsToCheck[cell]) &&
                    board.getMarkAt(cellsToCheck[(cell + 1) % 3]) == board.getMarkAt(cellsToCheck[(cell + 2) % 3]) &&
                    board.getMarkAt(cellsToCheck[(cell + 2) % 3]) != symbol) {
                winningMove = cellsToCheck[cell];
            }
        }
        return winningMove;
    }

    private int findNextTurnWin(Board board) {
        int moveToMake = 0;
        int[] possiblePivots = new int[10];
        Arrays.fill(possiblePivots, 0);
        for (int[] combination: WINNING_COMBINATIONS) {
            boolean keepGoing = true;
            for (int cell = 0; cell < combination.length && keepGoing; cell++) {
                if (    board.isCellEmpty(combination[cell]) && board.isCellEmpty(combination[(cell+1) % 3])&&
                        board.getMarkAt(combination[(cell+2) % 3]) == symbol) {
                    possiblePivots[combination[cell]] += 1;
                    possiblePivots[combination[(cell+1) % 3]] += 1;
                    keepGoing = false;
                }
            }
        }

        for(int i = 1; i < possiblePivots.length && moveToMake == 0; i++) {
            if (possiblePivots[i] >= 2) {
                moveToMake = i;
            }
        }
        return moveToMake;

    }

    private int preventNextTurnLoss(Board board) {
        int moveToMake = NO_MOVE;
        int[] possibleOpponentPivots = new int[10];
        Arrays.fill(possibleOpponentPivots, 0);
        for (int[] combination: WINNING_COMBINATIONS) {
            boolean keepGoing = true;
            for (int cell = 0; cell < combination.length && keepGoing; cell++) {
                if (    board.isCellEmpty(combination[cell]) && board.isCellEmpty(combination[(cell+1) % 3])&&
                        board.getMarkAt(combination[(cell+2) % 3]) != symbol && board.isCellEmpty(combination[(cell+2) % 3])) {
                    possibleOpponentPivots[combination[cell]] += 1;
                    possibleOpponentPivots[combination[(cell+1) % 3]] += 1;
                    keepGoing = false;
                }
            }
        }

        return moveToMake;

    }

    public void makeAMove(Board board) {
        didMakeMove = false;
        int move = NO_MOVE;
        boolean shouldMove = !board.isBoardFull();

        // check for a winning move for self if found, make it
        for (int moveToCheck = 0; moveToCheck < WINNING_COMBINATIONS.length && move == NO_MOVE; moveToCheck++ ) {
            move = findNowWinningMove(WINNING_COMBINATIONS[moveToCheck], board);
        }

        // check for a winning move for my opponent, if found block it
        for (int moveToCheck = 0; moveToCheck < WINNING_COMBINATIONS.length && move == NO_MOVE; moveToCheck++ ) {
            move = findNowOpponentWinningMove(WINNING_COMBINATIONS[moveToCheck], board);
        }

        // check for move with a guaranteed win for self next turn if found make it
        if (move == NO_MOVE && !board.isBoardFull()) { move = findNextTurnWin(board); }

        // check for potential move for opponent for guaranteed win if found prevent it

        // if no other move made choose from available moves in priority order of center, corners, edges
        if (move == NO_MOVE && shouldMove) {
            move = 8;
        }
//        int indexOfMoveToTry = 0;
//        while (move == NO_MOVE && shouldMove) {
//            int moveToTry = MOVE_PREFERENCE_IN_ORDER[indexOfMoveToTry];
//            if(board.isCellEmpty(moveToTry)) {
//                move = moveToTry;
//            }
//            indexOfMoveToTry++;
//        }

        if (move != NO_MOVE) {
            didMakeMove = true;
            board.addMark(move, symbol);
        }
    }

    public boolean didMove() {
        return didMakeMove; // This will probably have to change
    }
}
