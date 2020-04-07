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

    private Player createPlayer(char playerSymbol, char opponentSymbol, int numberOfHumanPlayers) {
        Player player;
        if (numberOfHumanPlayers == 1 && playerSymbol != 'X') {
            player = new UnbeatableComputer(playerSymbol, opponentSymbol);
        } else {
            player = new HumanPlayer(playerSymbol, opponentSymbol, new ConsoleInputOutput());
        }
        return player;
    }

    public Game createGame() {
        inputOutput.sendOutput(messages.greeting());
        int numberOfHumanPlayers = getNumberOfHumanPlayers();
        Player player1 = createPlayer('X', 'O', numberOfHumanPlayers);
        Player player2 = createPlayer('O', 'X', numberOfHumanPlayers);
        Board board = new Board();
        Messages messages = new Messages();
        return makeGame(messages, inputOutput, board, player1, player2);
    }

    private int getNumberOfHumanPlayers() {
        boolean validInput = false;
        int numberOfHumanPlayers = 0;
        while (!validInput) {
            inputOutput.sendOutput(messages.promptForNumberOfPlayers());
            numberOfHumanPlayers = inputOutput.getInput();
            if (numberOfHumanPlayers == 1 || numberOfHumanPlayers == 2){
                validInput = true;
            } else {
                inputOutput.sendOutput(messages.informPlayerInvalidInput());
            }
        }
        return numberOfHumanPlayers;
    }
}
