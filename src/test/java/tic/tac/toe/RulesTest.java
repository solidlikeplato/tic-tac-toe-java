package tic.tac.toe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        assertEquals(GameStatus.IN_PROGRESS, status);
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
    public void determineStatusWhenPlayerWinsHorizontal() {
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
    public void determineStatusWhenPlayerWinsVertical() {
        Board board = buildBoard(new char[] {
            'X','O',' ',
            'X','O',' ',
            'X',' ',' '
        });
        Player player = new UnbeatableComputer('X','O');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.PLAYER_WINS, status);
    }

    @Test
    public void determineStatusWhenPlayerWinsDiagonal() {
        Board board = buildBoard(new char[] {
            'X','O',' ',
            'O','X',' ',
            ' ',' ','X'
        });
        Player player = new UnbeatableComputer('X','O');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.PLAYER_WINS, status);
    }

    @Test
    public void determineStatusWhenPlayerLosesHorizontal() {
        Board board = buildBoard(new char[] {
            'O','O',' ',
            'X','X','X',
            ' ',' ',' '
        });
        Player player = new UnbeatableComputer('O','X');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.PLAYER_LOSES, status);
    }

    @Test
    public void determineStatusWhenPlayerLosesVertical() {
        Board board = buildBoard(new char[] {
            ' ','X','O',
            'O','X',' ',
            ' ','X',' '
        });
        Player player = new UnbeatableComputer('O','X');

        GameStatus status = determineStatus(board, player);

        assertEquals(GameStatus.PLAYER_LOSES, status);
    }

    @Test
    public void determineStatusWhenPlayerLosesDiagonal() {
        Board board = buildBoard(new char[] {
            ' ','O','X',
            'O','X',' ',
            'X',' ',' '
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
