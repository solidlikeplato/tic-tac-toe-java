package tic.tac.toe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
/*
Other potential ideas:
rules.hasWinner(board); // true / false
rules.isOver(board); // true / false
rules.isInProgress(board); // true / false
rules.isTie(board); // true / false

GameStatus.IN_PROGRESS / TIE_GAME / HAS_WINNER
GameStatus.IN_PROGRESS / TIE_GAME / PLAYER_ONE_WINS
 */
public class RulesTest {
    @Test
    public void determineStatus_WhenBoardIsEmpty_ReturnsInProgress() {
        Board board = buildBoard(new char[] {
            ' ',' ',' ',
            ' ',' ',' ',
            ' ',' ',' '
        });

        GameStatus status = determineStatus(board);

        assertEquals(GameStatus.IN_PROGRESS, status);
    }

    @Test
    public void determineStatusFullBoardTieGame() {
        Board board = buildBoard(new char[]{
            'X', 'O', 'X',
            'X', 'O', 'X',
            'O', 'X', 'O'
        });

        GameStatus status = determineStatus(board);

        assertEquals(GameStatus.TIE_GAME, status);
    }

    @Test
    public void determineStatusWhenPlayerOneWins() {
        Board board = buildBoard(new char[] {
            'X','X','X',
            'O','O',' ',
            ' ',' ',' '
        });

        GameStatus status = determineStatus(board);

        assertEquals(GameStatus.PLAYER_ONE_WINS, status);
    }

    @Test
    public void determineStatusWhenPlayerTwoWins() {
        Board board = buildBoard(new char[] {
            'X',' ','X',
            'O','O','O',
            ' ',' ',' '
        });

        GameStatus status = determineStatus(board);

        assertEquals(GameStatus.PLAYER_TWO_WINS, status);
    }

    private Board buildBoard(char[] marks) {
        Board board = new Board();
        board.setBoard(marks);
        return board;
    }

    private GameStatus determineStatus(Board board) {
        return new Rules().determineStatus(board);
    }
}
