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
        Player player = new UnbeatableComputer('X','O');

        GameStatus status = determineStatus(board, player);

    }

    @Test
    public void determineStatusFullBoardTieGame() {
        Board board = buildBoard(new char[]{
            'X', 'O', 'X',
            'X', 'O', 'X',
            'O', 'X', 'O'
        });
        Player player = new UnbeatableComputer('X','O');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.TIE_GAME, status);
    }

    @Test
    public void determineStatusWhenPlayerWins() {
        Board board = buildBoard(new char[] {
            'X','X','X',
            'O','O',' ',
            ' ',' ',' '
        });
        Player player = new UnbeatableComputer('X','O');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.PLAYER_WINS, status);
    }

    @Test
    public void determineStatusWhenPlayerLoses() {
        Board board = buildBoard(new char[] {
            'X','X','X',
            'O','O',' ',
            ' ',' ',' '
        });
        Player player = new UnbeatableComputer('O','X');

        GameStatus status = determineStatus(board, player);


        assertEquals(GameStatus.PLAYER_LOSES, status);
    }

    private Board buildBoard(char[] marks) {
        Board board = new Board();
        board.setBoard(marks);
        return board;
    }

    private GameStatus determineStatus(Board board, Player player) {
        return new Rules().determineStatus(board, player);
    }
}
