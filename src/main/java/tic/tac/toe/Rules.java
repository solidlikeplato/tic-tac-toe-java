package tic.tac.toe;

import java.util.Arrays;
import java.util.List;

public class Rules {
    public static final Integer[][] LINES = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};
    public GameStatus determineStatus(Board board, Player player) {
        if (isPlayerWinner(board, player.getSymbol())) {
            return GameStatus.PLAYER_WINS;
        } else if (isPlayerWinner(board, player.getOpponentSymbol())) {
            return GameStatus.PLAYER_LOSES;
        } else if (board.isBoardFull()) {
            return GameStatus.TIE_GAME;
        } else {
            return GameStatus.IN_PROGRESS;
        }
    }

    private boolean isPlayerWinner(Board board, char playerSymbol) {
        boolean winnerFound = false;
        int lineToCheck = 0;
        while (!winnerFound && lineToCheck < LINES.length) {
            winnerFound = isWinner(board, playerSymbol, Arrays.asList(LINES[lineToCheck]));
            lineToCheck++;
        }
        return winnerFound;
    }

    private boolean isWinner(Board board, char symbol, List<Integer> positions) {
        char markOne = board.getMarkAt(positions.get(0));
        char markTwo = board.getMarkAt(positions.get(1));
        char markThree = board.getMarkAt(positions.get(2));
        return markOne == symbol && markTwo == symbol && markThree == symbol;
    }

}
