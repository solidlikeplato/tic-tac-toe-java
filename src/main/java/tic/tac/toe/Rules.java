package tic.tac.toe;

import java.util.Arrays;
import java.util.List;

public class Rules {
    public GameStatus determineStatus(Board board) {
        if (isPlayerOneWinner(board)) {
            return GameStatus.PLAYER_ONE_WINS;
        } else if (isPlayerTwoWinner(board)) {
            return GameStatus.PLAYER_TWO_WINS;
        } else if (board.isBoardFull()) {
            return GameStatus.TIE_GAME;
        } else {
            return GameStatus.IN_PROGRESS;
        }
    }

    private boolean isPlayerTwoWinner(Board board) {
        return isWinner(board, 'O', Arrays.asList(4, 5, 6));
    }

    private boolean isPlayerOneWinner(Board board) {
        return isWinner(board, 'X', Arrays.asList(1, 2, 3));
    }

    private boolean isWinner(Board board, char symbol, List<Integer> positions) {
        char markOne = board.getMarkAt(positions.get(0));
        char markTwo = board.getMarkAt(positions.get(1));
        char markThree = board.getMarkAt(positions.get(2));
        return markOne == symbol && markTwo == symbol && markThree == symbol;
    }
}
