package tic.tac.toe;

public class Menu {
    private InputOutput inputOutput;
    private Messages messages = new Messages();
    Menu(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    Game makeGame(Messages messages, InputOutput inputOutput, Board board, Player player1, Player player2) {
        return new Game(messages, inputOutput, board, player1, player2);
    }

    private Player createPlayer(char playerSymbol, int numberOfHumanPlayers) {
        Player player;
        if (numberOfHumanPlayers == 1 && playerSymbol != 'X') {
            player = new UnbeatableComputer(playerSymbol, 'X');
        } else {
            player = new HumanPlayer(playerSymbol, new ConsoleInputOutput());
        }
        return player;
    }

    public Game createGame() {
        inputOutput.sendOutput(messages.greeting());
        int numberOfHumanPlayers = getNumberOfHumanPlayers();
        Player player1 = createPlayer('X', numberOfHumanPlayers);
        Player player2 = createPlayer('O', numberOfHumanPlayers);
        Board board = new Board();
        Messages messages = new Messages();
        return makeGame(messages, inputOutput, board, player1, player2);
    }

    private int getNumberOfHumanPlayers() {
        int numberOfHumanPlayers = 0;
        while (numberOfHumanPlayers > 2 || numberOfHumanPlayers < 1) {
            inputOutput.sendOutput(messages.promptForNumberOfPlayers());
            numberOfHumanPlayers = inputOutput.getInput();
        }
        return numberOfHumanPlayers;
    }
}
